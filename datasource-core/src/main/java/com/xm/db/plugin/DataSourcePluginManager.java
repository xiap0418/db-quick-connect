package com.xm.db.plugin;

import com.xm.db.common.enums.DbType;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

/**
 * @author xiap
 * @since 2025-11-10-17:51
 */

@Slf4j
public class DataSourcePluginManager {
    private static final DataSourcePluginManager INSTANCE = new DataSourcePluginManager();

    private final Map<String, DataSourcePlugin> dataSourcePluginMap = new ConcurrentHashMap<>();

    public static DataSourcePluginManager getInstance() {
        return INSTANCE;
    }

    private DataSourcePluginManager() {
        installPlugin();
    }

    public DataSourcePlugin getDataSourcePlugin(final DbType dbType) {
        return dataSourcePluginMap.get(dbType.getName());
    }

    public void installPlugin() {

        PrioritySpiFactory<DataSourcePluginFactory> prioritySpiFactory =
                new PrioritySpiFactory<>(DataSourcePluginFactory.class);
        for (Map.Entry<String, DataSourcePluginFactory> entry : prioritySpiFactory.getSpiMap().entrySet()) {
            final DataSourcePluginFactory factory = entry.getValue();
            final String name = entry.getKey();

            if (dataSourcePluginMap.containsKey(name)) {
                throw new IllegalStateException(format("Duplicate datasource plugins named '%s'", name));
            }

            loadDatasourceClient(factory);

            log.info("Registered datasource plugin: {}", name);
        }
    }

    private void loadDatasourceClient(DataSourcePluginFactory datasourcePluginFactory) {
        DataSourcePlugin datasourcePlugin = datasourcePluginFactory.create();
        dataSourcePluginMap.put(datasourcePluginFactory.getName(), datasourcePlugin);
    }
}
