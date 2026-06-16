package com.xm.db.plugin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author xiap
 * @since 2025-11-10-16:00
 */
@Data
@Builder
@AllArgsConstructor
public class SpiIdentify {
    private static final int DEFAULT_PRIORITY = 0;

    private String name;

    @Builder.Default
    private int priority = DEFAULT_PRIORITY;
}
