package com.xm.db.core.client;

import com.xm.db.common.config.PoolConfig;
import com.xm.db.common.enums.DbType;
import com.xm.db.core.param.BaseConnectionParam;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;

import java.sql.Connection;
import java.sql.SQLException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author xiap
 * @since 2025-11-10-17:15
 */
@Slf4j
public abstract class BasePooledDataSourceClient implements PooledDataSourceClient {
    protected final BaseConnectionParam baseConnectionParam;
    protected final PoolConfig poolConfig;
    protected HikariDataSource dataSource;

    public BasePooledDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType, PoolConfig poolConfig) {
        this.poolConfig = poolConfig;
        this.baseConnectionParam = checkNotNull(baseConnectionParam, "baseConnectionParam is null");
        this.dataSource = createDataSourcePool(baseConnectionParam, checkNotNull(dbType, "dbType is null"));

    }

    @Override
    public HikariDataSource createDataSourcePool(BaseConnectionParam baseConnectionParam, DbType dbType) {
        String poolName = String.format("%s-pool-%d", dbType.name().toLowerCase(), System.currentTimeMillis());
        HikariConfig config = getHikariConfig(baseConnectionParam, poolName);

        if (MapUtils.isNotEmpty(baseConnectionParam.getOther())) {
            baseConnectionParam.getOther().forEach(config::addDataSourceProperty);
        }

        HikariDataSource dataSource = new HikariDataSource(config);
        log.info("Creating HikariDataSource for {} success.", dbType.name());
        return dataSource;
    }

    private HikariConfig getHikariConfig(BaseConnectionParam baseConnectionParam, String poolName) {
        HikariConfig config = new HikariConfig();
        config.setPoolName(poolName);
        config.setUsername(baseConnectionParam.getUsername());
        config.setPassword(baseConnectionParam.getPassword());
        config.setDriverClassName(baseConnectionParam.getDriverClassName());

        config.setJdbcUrl(baseConnectionParam.getJdbcUrl());

        // 核心池配置
        config.setMinimumIdle(poolConfig.getMinIdle());
        config.setMaximumPoolSize(poolConfig.getMaxPoolSize());
        config.setConnectionTimeout(poolConfig.getConnectionTimeoutMs());

        // 连接泄漏检测
        config.setLeakDetectionThreshold(poolConfig.getLeakDetectionThresholdMs());

        return config;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new IllegalStateException("DataSource has been closed");
        }
        return dataSource.getConnection();
    }

    @Override
    public void close() {
        if (dataSource != null) {
            log.info("Closing HikariDataSource for database: {}", baseConnectionParam.getJdbcUrl());
            dataSource.close();
            dataSource = null;
        }
    }
}
