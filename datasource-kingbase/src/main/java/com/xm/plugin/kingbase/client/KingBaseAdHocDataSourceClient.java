package com.xm.plugin.kingbase.client;

import com.xm.db.common.enums.DbType;
import com.xm.db.core.client.BaseAdHocDataSourceClient;
import com.xm.db.core.param.BaseConnectionParam;

/**
 * @author xiap
 * @since 2025-11-27-14:33
 */
public class KingBaseAdHocDataSourceClient extends BaseAdHocDataSourceClient {
    public KingBaseAdHocDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType) {
        super(baseConnectionParam, dbType);
    }
}
