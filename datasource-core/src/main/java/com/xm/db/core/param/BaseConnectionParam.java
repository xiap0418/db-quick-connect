package com.xm.db.core.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * base connection param
 * ignore null value in json
 *
 * @author xiap
 * @since 2025-11-10-14:28
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseConnectionParam implements ConnectionParam {

    protected String jdbcUrl;

    protected String username;

    protected String password;

    /**
     * other used by driverManager and HikariConfig
     */
    protected Map<String, Object> other = new HashMap<>();

    /**
     * solve multi version driverClassName
     */
    protected String driverClassName;

}
