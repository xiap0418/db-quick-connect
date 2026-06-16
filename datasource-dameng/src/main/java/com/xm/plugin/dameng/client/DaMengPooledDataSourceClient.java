package com.xm.plugin.dameng.client;

import com.xm.db.common.config.PoolConfig;
import com.xm.db.common.enums.DbType;
import com.xm.db.core.client.BasePooledDataSourceClient;
import com.xm.db.core.param.BaseConnectionParam;

/**
 * @author xiap
 * @since 2025-11-27-10:01
 */
public class DaMengPooledDataSourceClient extends BasePooledDataSourceClient {
    public DaMengPooledDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType, PoolConfig poolConfig) {
        super(baseConnectionParam, dbType, poolConfig);
    }
}
