package com.xm.db.core.processor;

import com.xm.db.core.param.ConnectionParam;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author xiap
 * @since 2025-11-10-16:55
 */
@Slf4j
public abstract class AbstractDataSourceProcessor implements DataSourceProcessor {

    @Override
    public Properties getConnectionProperties(ConnectionParam connectionParam) {
        Properties props = new Properties();
        String username = connectionParam.getUsername();
        String password = connectionParam.getPassword();
        if (username != null) {
            props.setProperty("user", username);
        }
        if (password != null) {
            props.setProperty("password", password);
        }
        return props;
    }

}
