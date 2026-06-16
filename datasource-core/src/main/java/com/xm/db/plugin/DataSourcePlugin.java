package com.xm.db.plugin;

import com.xm.db.common.config.PoolConfig;
import com.xm.db.common.enums.DbType;
import com.xm.db.core.client.AdHocDataSourceClient;
import com.xm.db.core.client.PooledDataSourceClient;
import com.xm.db.core.param.BaseConnectionParam;

/**
 * @author xiap
 * @since 2025-11-10-15:56
 */
public interface DataSourcePlugin {
    /**
     * Create a AdHocDataSourceClient, this client should not be pooled.
     *
     * @param baseConnectionParam base connection param
     * @param dbType              db type
     * @return AdHocDataSourceClient
     */
    AdHocDataSourceClient createAdHocDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType);

    /**
     * Create a PooledDataSourceClient, this client should be pooled.
     *
     * @param baseConnectionParam base connection param
     * @param dbType              db type
     * @param poolConfig          pool config
     * @return PooledDataSourceClient
     */
    PooledDataSourceClient createPooledDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType, PoolConfig poolConfig);
}
