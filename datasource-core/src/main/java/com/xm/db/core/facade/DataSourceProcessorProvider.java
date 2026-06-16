package com.xm.db.core.facade;

import com.xm.db.common.enums.DbType;
import com.xm.db.core.processor.DataSourceProcessor;
import com.xm.db.core.processor.DataSourceProcessorManager;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author xiap
 * @since 2025-11-10-16:21
 */
@Slf4j
public class DataSourceProcessorProvider {
    private static final DataSourceProcessorManager DATA_SOURCE_PLUGIN_MANAGER = new DataSourceProcessorManager();

    static {
        try {
            DATA_SOURCE_PLUGIN_MANAGER.installProcessor();
            log.info("DataSourceProcessorProvider initialized successfully.");
        } catch (Exception e) {
            log.error("Failed to initialize DataSourceProcessorProvider", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private DataSourceProcessorProvider() {
    }


    public static DataSourceProcessor getDataSourceProcessor(@NonNull DbType dbType) {
        DataSourceProcessor processor = getDataSourceProcessorMap().get(dbType.name());
        if (processor == null) {
            throw new IllegalArgumentException("No DataSourceProcessor found for DbType: " + dbType);
        }
        return processor;

    }

    public static Map<String, DataSourceProcessor> getDataSourceProcessorMap() {
        return DATA_SOURCE_PLUGIN_MANAGER.getDataSourceProcessorMap();
    }
}
