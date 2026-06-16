package com.xm.plugin.dameng.impl;

import com.xm.db.common.config.PoolConfig;
import com.xm.db.common.enums.DbType;
import com.xm.db.core.client.AdHocDataSourceClient;
import com.xm.db.core.client.PooledDataSourceClient;
import com.xm.db.core.param.BaseConnectionParam;
import com.xm.db.plugin.DataSourcePlugin;
import com.xm.plugin.dameng.client.DaMengAdHocDataSourceClient;
import com.xm.plugin.dameng.client.DaMengPooledDataSourceClient;

/**
 * @author xiap
 * @since 2025-11-27-10:05
 */
public class DaMengDataSourcePlugin implements DataSourcePlugin {
    @Override
    public AdHocDataSourceClient createAdHocDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType) {
        return new DaMengAdHocDataSourceClient(baseConnectionParam, dbType);
    }

    @Override
    public PooledDataSourceClient createPooledDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType,
                                                               PoolConfig poolConfig) {
        return new DaMengPooledDataSourceClient(baseConnectionParam, dbType, poolConfig);
    }
}
