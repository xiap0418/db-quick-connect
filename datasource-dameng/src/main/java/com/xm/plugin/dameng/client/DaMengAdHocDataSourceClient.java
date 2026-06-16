package com.xm.plugin.dameng.client;

import com.xm.db.common.enums.DbType;
import com.xm.db.core.client.BaseAdHocDataSourceClient;
import com.xm.db.core.param.BaseConnectionParam;

/**
 * @author xiap
 * @since 2025-11-27-10:00
 */
public class DaMengAdHocDataSourceClient extends BaseAdHocDataSourceClient {

    public DaMengAdHocDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType) {
        super(baseConnectionParam, dbType);
    }
}
