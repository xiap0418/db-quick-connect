package com.xm.plugin.mysql.param;

import com.xm.db.common.constants.DataSourceConstants;
import com.xm.db.core.param.BaseConnectionParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * * MySQL 专用连接参数。
 * * 不新增字段，所有扩展参数通过 {@link #other} 传递。
 *
 * @author xiap
 * @since 2025-11-10-17:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MySqlConnectionParam extends BaseConnectionParam {

    @Override
    public String getDriverClassName() {
        // 优先使用显式设置的 driverClassName
        if (StringUtils.isNotEmpty(driverClassName)) {
            return this.driverClassName;
        }
        //默认使用新版驱动（com.mysql.cj.jdbc.Driver）
        return DataSourceConstants.Driver.MYSQL_CJ;
    }

}
