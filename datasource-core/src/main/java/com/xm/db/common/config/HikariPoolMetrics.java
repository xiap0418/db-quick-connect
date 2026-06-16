package com.xm.db.common.config;

import lombok.Builder;
import lombok.Data;

/**
 * @author xiap
 * @since 2025-11-21-14:02
 */
@Data
@Builder
public class HikariPoolMetrics {
    private String poolName;

    private int idle;

    private int active;

    private int total;

    private int waiting;
}
