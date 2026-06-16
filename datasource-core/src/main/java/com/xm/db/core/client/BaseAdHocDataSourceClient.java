package com.xm.db.core.client;

import com.xm.db.common.enums.DbType;
import com.xm.db.core.facade.DataSourceProcessorProvider;
import com.xm.db.core.param.BaseConnectionParam;
import com.xm.db.core.processor.DataSourceProcessor;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author xiap
 * @since 2025-11-10-16:20
 */
public abstract class BaseAdHocDataSourceClient implements AdHocDataSourceClient {
    protected final BaseConnectionParam baseConnectionParam;
    protected final DbType dbType;

    protected BaseAdHocDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType) {
        this.baseConnectionParam = baseConnectionParam;
        this.dbType = dbType;
    }

    @Override
    public Connection getConnection() throws SQLException {
        DataSourceProcessor dataSourceProcessor = DataSourceProcessorProvider.getDataSourceProcessor(dbType);
        String driverClassName = baseConnectionParam.getDriverClassName();
        if (StringUtils.isNotEmpty(driverClassName)) {
            try {
                Class.forName(driverClassName);
            } catch (ClassNotFoundException e) {
                throw new SQLException("JDBC driver not found: " + driverClassName, e);
            }
        }

        Properties props = dataSourceProcessor.getConnectionProperties(baseConnectionParam);

        return DriverManager.getConnection(baseConnectionParam.getJdbcUrl(), props);

    }

    @Override
    public void close() {
        // do nothing
    }
}
