package com.xm.plugin.dameng.impl;

import com.google.auto.service.AutoService;
import com.xm.db.common.enums.DbType;
import com.xm.db.plugin.DataSourcePlugin;
import com.xm.db.plugin.DataSourcePluginFactory;
import com.xm.db.plugin.SpiIdentify;

/**
 * @author xiap
 * @since 2025-11-27-10:06
 */
@AutoService(DataSourcePluginFactory.class)
public class DaMengDataSourcePluginFactory implements DataSourcePluginFactory {
    @Override
    public String getName() {
        return DbType.DAMENG.getName();
    }

    @Override
    public DataSourcePlugin create() {
        return new DaMengDataSourcePlugin();
    }

    @Override
    public SpiIdentify getIdentify(){
        return SpiIdentify.builder().name(getName()).build();
    }
}
