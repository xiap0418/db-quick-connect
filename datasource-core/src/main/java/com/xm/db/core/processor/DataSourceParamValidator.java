package com.xm.db.core.processor;

import com.xm.db.core.param.BaseConnectionParam;

import java.util.Map;

/**
 * @author xiap
 * @since 2025-11-26-17:10
 */
public interface DataSourceParamValidator {
    /**
     * 校验主机地址（IP 或合法域名）
     *
     * @param host host
     */
    void validateHost(String host);

    /**
     * 校验数据库名格式
     *
     * @param database database
     *
     */
    void validateDatabase(String database);

    /**
     * 校验额外连接参数
     *
     * @param other {@link BaseConnectionParam#getOther()}
     *
     */
    void validateConnectionProperties(Map<String, Object> other);
}
