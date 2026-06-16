package com.xm.db.plugin;

/**
 * @author xiap
 * @since 2025-11-10-16:11
 */
public interface DataSourcePluginFactory extends PrioritySpi {
    /**
     * create datasource plugin
     *
     * @return datasource plugin
     */
    DataSourcePlugin create();

    /**
     * get registry component name
     *
     * @return registry component name
     */
    String getName();

    /**
     * get spi identify
     *
     * @return spi identify
     */
    @Override
    default SpiIdentify getIdentify() {
        return SpiIdentify.builder().name(getName()).build();
    }
}
