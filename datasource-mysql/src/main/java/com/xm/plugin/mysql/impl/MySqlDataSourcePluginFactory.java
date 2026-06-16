package com.xm.plugin.mysql.impl;

import com.google.auto.service.AutoService;
import com.xm.db.common.enums.DbType;
import com.xm.db.plugin.DataSourcePlugin;
import com.xm.db.plugin.DataSourcePluginFactory;
import com.xm.db.plugin.SpiIdentify;

/**
 * @author xiap
 * @since 2025-11-10-18:13
 */
@AutoService(DataSourcePluginFactory.class)
public class MySqlDataSourcePluginFactory implements DataSourcePluginFactory {
    @Override
    public String getName() {
        return DbType.MYSQL.getName();
    }

    @Override
    public DataSourcePlugin create() {
        return new MySqlDataSourcePlugin();
    }

    @Override
    public SpiIdentify getIdentify(){
        return SpiIdentify.builder().name(getName()).build();
    }

}
