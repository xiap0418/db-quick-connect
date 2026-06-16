package com.xm.db.core.client;

import com.xm.db.common.enums.DbType;
import com.xm.db.core.param.BaseConnectionParam;

import javax.sql.DataSource;

/**
 * @author xiap
 * @since 2025-11-10-15:49
 */
public interface PooledDataSourceClient extends DataSourceClient {
    /**
     * create a data source pool
     *
     * @param baseConnectionParam base connection param
     * @param dbType              db type
     * @return data source pool
     */
    DataSource createDataSourcePool(BaseConnectionParam baseConnectionParam, DbType dbType);
}
