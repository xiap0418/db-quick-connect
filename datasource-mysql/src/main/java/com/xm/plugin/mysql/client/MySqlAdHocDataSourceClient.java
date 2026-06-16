package com.xm.plugin.mysql.client;

import com.xm.db.common.enums.DbType;
import com.xm.db.core.client.BaseAdHocDataSourceClient;
import com.xm.db.core.param.BaseConnectionParam;
import com.xm.plugin.mysql.param.MySqlConnectionParam;


/**
 * @author xiap
 * @since 2025-11-10-18:12
 */
public class MySqlAdHocDataSourceClient extends BaseAdHocDataSourceClient {
    public MySqlAdHocDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType) {
        super(baseConnectionParam, dbType);
        if (!(baseConnectionParam instanceof MySqlConnectionParam)) {
            throw new IllegalArgumentException("Expected MySqlConnectionParam for MySQL client");
        }
    }
}
