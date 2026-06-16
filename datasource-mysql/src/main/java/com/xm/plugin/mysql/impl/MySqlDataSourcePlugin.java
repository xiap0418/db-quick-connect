package com.xm.plugin.mysql.impl;

import com.xm.db.common.config.PoolConfig;
import com.xm.db.common.enums.DbType;
import com.xm.db.core.client.AdHocDataSourceClient;
import com.xm.db.core.client.PooledDataSourceClient;
import com.xm.db.core.param.BaseConnectionParam;
import com.xm.db.plugin.DataSourcePlugin;
import com.xm.plugin.mysql.client.MySqlAdHocDataSourceClient;
import com.xm.plugin.mysql.client.MySqlPooledDataSourceClient;

/**
 * @author xiap
 * @since 2025-11-10-18:11
 */
public class MySqlDataSourcePlugin implements DataSourcePlugin {
    @Override
    public AdHocDataSourceClient createAdHocDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType) {
        return new MySqlAdHocDataSourceClient(baseConnectionParam, dbType);
    }

    @Override
    public PooledDataSourceClient createPooledDataSourceClient(BaseConnectionParam baseConnectionParam,
                                                               DbType dbType, PoolConfig poolConfig) {
        return new MySqlPooledDataSourceClient(baseConnectionParam, dbType, poolConfig);
    }
}
