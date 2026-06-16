package com.xm.plugin.kingbase.param;

import com.xm.db.common.constants.DataSourceConstants;
import com.xm.db.core.param.BaseConnectionParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @author xiap
 * @since 2025-11-27-14:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class KingBaseConnectionParam extends BaseConnectionParam {
    @Override
    public String getDriverClassName() {
        if (StringUtils.isNotEmpty(driverClassName)) {
            return this.driverClassName;
        }
        return DataSourceConstants.Driver.KINGBASE;
    }
}
