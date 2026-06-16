package com.xm.plugin.kingbase.client;

import com.xm.db.common.config.PoolConfig;
import com.xm.db.common.enums.DbType;
import com.xm.db.core.client.BasePooledDataSourceClient;
import com.xm.db.core.param.BaseConnectionParam;

/**
 * @author xiap
 * @since 2025-11-27-14:34
 */
public class KingBasePooledDataSourceClient extends BasePooledDataSourceClient {
    public KingBasePooledDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType, PoolConfig poolConfig) {
        super(baseConnectionParam, dbType, poolConfig);
    }
}
