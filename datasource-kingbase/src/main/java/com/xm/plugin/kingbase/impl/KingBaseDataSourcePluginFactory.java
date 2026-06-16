package com.xm.plugin.kingbase.impl;

import com.google.auto.service.AutoService;
import com.xm.db.common.enums.DbType;
import com.xm.db.plugin.DataSourcePlugin;
import com.xm.db.plugin.DataSourcePluginFactory;
import com.xm.db.plugin.SpiIdentify;

/**
 * @author xiap
 * @since 2025-11-27-14:42
 */
@AutoService(DataSourcePluginFactory.class)
public class KingBaseDataSourcePluginFactory implements DataSourcePluginFactory {
    @Override
    public String getName() {
        return DbType.KINGBASE.getName();
    }

    @Override
    public DataSourcePlugin create() {
        return new KingBaseDataSourcePlugin();
    }

    @Override
    public SpiIdentify getIdentify() {
        return SpiIdentify.builder().name(getName()).build();
    }
}
