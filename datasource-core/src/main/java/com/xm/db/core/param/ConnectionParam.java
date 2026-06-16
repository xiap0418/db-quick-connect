package com.xm.db.core.param;

import java.io.Serializable;

/**
 * @author xiap
 * @since 2025-11-10-14:29
 */
public interface ConnectionParam extends Serializable {
    /**
     * 不包含敏感参数
     *
     * @return jdbcUrl
     */
    String getJdbcUrl();

    /**
     * get username
     *
     * @return username
     */
    String getUsername();

    /**
     * get password
     *
     * @return password
     */
    String getPassword();

}
