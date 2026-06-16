package com.xm.plugin.kingbase.impl;

import com.xm.db.common.config.PoolConfig;
import com.xm.db.common.enums.DbType;
import com.xm.db.core.client.AdHocDataSourceClient;
import com.xm.db.core.client.PooledDataSourceClient;
import com.xm.db.core.param.BaseConnectionParam;
import com.xm.db.plugin.DataSourcePlugin;
import com.xm.plugin.kingbase.client.KingBaseAdHocDataSourceClient;
import com.xm.plugin.kingbase.client.KingBasePooledDataSourceClient;

/**
 * @author xiap
 * @since 2025-11-27-14:40
 */
public class KingBaseDataSourcePlugin implements DataSourcePlugin {

    @Override
    public AdHocDataSourceClient createAdHocDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType) {
        return new KingBaseAdHocDataSourceClient(baseConnectionParam, dbType);
    }

    @Override
    public PooledDataSourceClient createPooledDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType,
                                                               PoolConfig poolConfig) {
        return new KingBasePooledDataSourceClient(baseConnectionParam, dbType, poolConfig);
    }
}
