package com.xm.db.core.client;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * get connection of a data source
 *
 * @author xiap
 * @since 2025-11-10-15:46
 */
public interface DataSourceClient extends AutoCloseable {
    /**
     * get connection
     *
     * @return connection
     * @throws SQLException           sql exception
     */
    Connection getConnection() throws SQLException;


}
