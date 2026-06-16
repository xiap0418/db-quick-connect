package com.xm.db.core.processor;

import com.xm.db.common.enums.DbType;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiap
 * @since 2025-11-10-16:21
 */
@Slf4j
public class DataSourceProcessorManager {
    private static final Map<String, DataSourceProcessor> DATA_SOURCE_PROCESSOR_MAP = new ConcurrentHashMap<>();

    private static volatile boolean initialized = false;

    public Map<String, DataSourceProcessor> getDataSourceProcessorMap() {
        return Collections.unmodifiableMap(DATA_SOURCE_PROCESSOR_MAP);
    }

    public void installProcessor() {
        if (initialized) {
            return;
        }

        synchronized (DataSourceProcessorManager.class) {
            if (initialized) {
                return;
            }

            ServiceLoader.load(DataSourceProcessor.class)
                    .forEach(processor -> {
                        DbType dbType = processor.getDbType();
                        if (dbType == null) {
                            throw new IllegalStateException("Processor " + processor.getClass().getName() + " returns null DbType");
                        }

                        String key = dbType.name();
                        DataSourceProcessor existing = DATA_SOURCE_PROCESSOR_MAP.putIfAbsent(key, processor);
                        if (existing != null) {
                            throw new IllegalStateException(
                                    String.format("Duplicate DataSourceProcessor for DbType '%s': existing=%s, new=%s",
                                            key, existing.getClass().getName(), processor.getClass().getName())
                            );
                        }
                        log.info("Successfully registered DataSourceProcessor: {} -> {}", key, processor.getClass().getSimpleName());
                    });

            initialized = true;
        }

    }

}
