package com.xm.db.common.utils;

import com.xm.db.common.enums.DbType;
import com.xm.db.core.param.BaseConnectionParam;
import com.xm.db.core.param.ConnectionParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiap
 * @since 2025-11-10-17:26
 */
@Slf4j
public class DataSourceUtils {
    public static String getDatasourceUniqueId(ConnectionParam connectionParam, DbType dbType) {
        BaseConnectionParam baseConnectionParam = (BaseConnectionParam) connectionParam;
        Map<String, Object> other = baseConnectionParam.getOther();
        if (MapUtils.isEmpty(other)) {
            return String.format("%s@%s@%s", dbType.getName(), baseConnectionParam.getUsername(),
                    baseConnectionParam.getJdbcUrl());
        } else {
            String otherStr = other.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            return String.format("%s@%s@%s@%s", dbType.getName(), baseConnectionParam.getUsername(),
                    baseConnectionParam.getJdbcUrl(), otherStr);
        }

    }

    private DataSourceUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

}
