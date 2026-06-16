package com.xm.db.common.config;

import lombok.Data;

/**
 * @author xiap
 * @since 2025-11-20-18:00
 */
@Data
public class PoolConfig {
    /**
     * 最小空闲连接数
     */
    private int minIdle = 5;
    /**
     * 最大连接数
     */
    private int maxPoolSize = 10;
    /**
     * 等待获取连接超时时间，单位毫秒
     */
    private long connectionTimeoutMs = 10_000L;

    /**
     * 连接泄漏检测阈值，单位毫秒
     * 0 表示禁用泄漏检测
     * 建议设置为 60_000L（60秒）便于排查
     */
    private long leakDetectionThresholdMs = 0L;

    public static final PoolConfig DEFAULT = new PoolConfig();
}
