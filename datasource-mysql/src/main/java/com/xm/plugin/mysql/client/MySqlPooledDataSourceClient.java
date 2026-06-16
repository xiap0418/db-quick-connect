package com.xm.plugin.mysql.client;

import com.xm.db.common.config.PoolConfig;
import com.xm.db.common.enums.DbType;
import com.xm.db.core.client.BasePooledDataSourceClient;
import com.xm.db.core.param.BaseConnectionParam;

/**
 * @author xiap
 * @since 2025-11-10-18:11
 */
public class MySqlPooledDataSourceClient extends BasePooledDataSourceClient {
    public MySqlPooledDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType, PoolConfig poolConfig) {
        super(baseConnectionParam, dbType, poolConfig);
    }
}
