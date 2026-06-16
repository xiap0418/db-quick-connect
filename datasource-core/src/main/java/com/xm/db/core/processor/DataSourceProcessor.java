package com.xm.db.core.processor;

import com.xm.db.common.enums.DbType;
import com.xm.db.core.param.ConnectionParam;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * @author xiap
 * @since 2025-11-10-16:22
 */
public interface DataSourceProcessor {

    /**
     * get dbType
     *
     * @return dbType
     */
    DbType getDbType();

    /**
     * get connection properties used in DriverManager.getConnection(url, props)
     * properties 优先级比jdbc中参数高
     *
     * @param connectionParam connection param
     * @return Properties
     */
    Properties getConnectionProperties(ConnectionParam connectionParam);


    /**
     * Validate connection parameters before establishing a connection.
     *
     * @param connectionParam connection param
     */
    default void validateConnectionParam(ConnectionParam connectionParam) {
        // no-op by default
    }

    /**
     * Provide a human-readable JDBC URL template (optional, for UI/docs).
     *
     * @return jdbcUrlTemplate
     */
    default String getDefaultJdbcUrlTemplate() {
        return StringUtils.EMPTY;
    }

}
