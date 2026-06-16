package com.xm.db.core.client;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.Scheduler;
import com.xm.db.common.config.HikariPoolMetrics;
import com.xm.db.common.config.PoolConfig;
import com.xm.db.common.enums.DbType;
import com.xm.db.common.utils.DataSourceUtils;
import com.xm.db.core.param.BaseConnectionParam;
import com.xm.db.core.param.ConnectionParam;
import com.xm.db.plugin.DataSourcePlugin;
import com.xm.db.plugin.DataSourcePluginManager;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;

/**
 * @author xiap
 * @since 2025-11-26-10:58
 */
@Slf4j
public class PooledClientManager {
    private static final DataSourcePluginManager DATA_SOURCE_PLUGIN_MANAGER = DataSourcePluginManager.getInstance();

    private static final Cache<String, PooledDataSourceClient> POOLED_DATASOURCE_CLIENT_CACHE =
            Caffeine.newBuilder()
                    .expireAfterWrite(Duration.ofHours(24))
                    .maximumSize(100)
                    .scheduler(Scheduler.systemScheduler())
                    .removalListener((String key, PooledDataSourceClient client, RemovalCause cause) -> {
                        if (client != null) {
                            try (PooledDataSourceClient closed = client) {
                                closed.close();
                                log.info("Closed datasource client: {}, reason: {}", key, cause);
                            } catch (Exception e) {
                                log.warn("Error closing client for key: {}", key, e);
                            }
                        }
                    })
                    .build();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(POOLED_DATASOURCE_CLIENT_CACHE::invalidateAll));
    }

    public Connection getConnection(DbType dbType, ConnectionParam param, PoolConfig poolConfig)
            throws SQLException {
        PooledDataSourceClient client = getClient(dbType, param, poolConfig);
        return client.getConnection();
    }

    public PooledDataSourceClient getClient(DbType dbType, ConnectionParam param, PoolConfig poolConfig) {
        BaseConnectionParam baseParam = (BaseConnectionParam) param;

        String key = DataSourceUtils.getDatasourceUniqueId(baseParam, dbType);
        return POOLED_DATASOURCE_CLIENT_CACHE.get(key, k -> {
            DataSourcePlugin plugin = DATA_SOURCE_PLUGIN_MANAGER.getDataSourcePlugin(dbType);
            if (plugin == null) {
                throw new RuntimeException("Plugin not found for: " + dbType.getName());
            }
            return plugin.createPooledDataSourceClient(baseParam, dbType, poolConfig);
        });
    }

    public void closeClient(DbType dbType, ConnectionParam param) {
        BaseConnectionParam baseParam = (BaseConnectionParam) param;
        String key = DataSourceUtils.getDatasourceUniqueId(baseParam, dbType);
        POOLED_DATASOURCE_CLIENT_CACHE.invalidate(key);
    }

    public HikariPoolMetrics getMetrics(DbType dbType, ConnectionParam param) {
        BasePooledDataSourceClient client = (BasePooledDataSourceClient) getClient(dbType, param, PoolConfig.DEFAULT);
        HikariDataSource ds = client.dataSource;
        HikariPoolMXBean bean = ds.getHikariPoolMXBean();
        return HikariPoolMetrics.builder()
                .poolName(ds.getPoolName())
                .total(bean.getTotalConnections())
                .active(bean.getActiveConnections())
                .idle(bean.getIdleConnections())
                .waiting(bean.getThreadsAwaitingConnection())
                .build();
    }
}
