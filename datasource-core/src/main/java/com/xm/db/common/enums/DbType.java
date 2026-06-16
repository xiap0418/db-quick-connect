package com.xm.db.common.enums;

import com.google.common.base.Functions;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toMap;

/**
 * 数据库类型枚举
 *
 * @author xiap
 * @since 2025-11-10-15:51
 */
@Getter
public enum DbType {
    // ==================== 已实现插件的数据库类型 ====================
    MYSQL(0, "mysql", "MySQL"),
    DAMENG(10, "dameng", "达梦数据库"),
    KINGBASE(15, "kingbase", "人大金仓"),

    // ==================== 预留但未实现插件的数据库类型 ====================
    /**
     * @deprecated 待实现 PostgreSQL 插件
     */
    @Deprecated
    POSTGRESQL(1, "postgresql", "PostgreSQL"),
    /**
     * @deprecated 待实现 Hive 插件
     */
    @Deprecated
    HIVE(2, "hive", "Hive"),
    /**
     * @deprecated 待实现 Spark 插件
     */
    @Deprecated
    SPARK(3, "spark", "Spark"),
    /**
     * @deprecated 待实现 ClickHouse 插件
     */
    @Deprecated
    CLICKHOUSE(4, "clickhouse", "ClickHouse"),
    /**
     * @deprecated 待实现 Oracle 插件
     */
    @Deprecated
    ORACLE(5, "oracle", "Oracle"),
    /**
     * @deprecated 待实现 SQL Server 插件
     */
    @Deprecated
    SQLSERVER(6, "sqlserver", "SQL Server"),
    /**
     * @deprecated 待实现 DB2 插件
     */
    @Deprecated
    DB2(7, "db2", "DB2"),
    /**
     * @deprecated 待实现 Presto 插件
     */
    @Deprecated
    PRESTO(8, "presto", "Presto"),
    /**
     * @deprecated 待实现 H2 插件
     */
    @Deprecated
    H2(9, "h2", "H2"),
    /**
     * @deprecated 待实现 OceanBase 插件
     */
    @Deprecated
    OCEANBASE(11, "oceanbase", "OceanBase"),
    /**
     * @deprecated 待实现 SSH 插件
     */
    @Deprecated
    SSH(12, "ssh", "SSH"),
    /**
     * @deprecated 待实现 Doris 插件
     */
    @Deprecated
    DORIS(13, "doris", "Doris"),
    /**
     * @deprecated 待实现 K8S 插件
     */
    @Deprecated
    K8S(14, "k8s", "Kubernetes"),
    ;

    private static final Map<Integer, DbType> DB_TYPE_MAP =
            Arrays.stream(DbType.values()).collect(toMap(DbType::getCode, Functions.identity()));
    private final int code;
    private final String name;
    private final String desc;

    DbType(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static DbType of(int type) {
        if (DB_TYPE_MAP.containsKey(type)) {
            return DB_TYPE_MAP.get(type);
        }
        return null;
    }

    public static DbType ofName(String name) {
        return Arrays.stream(DbType.values()).filter(e -> e.name().equals(name)).findFirst()
                .orElseThrow(() -> new NoSuchElementException("no such db type"));
    }

}
