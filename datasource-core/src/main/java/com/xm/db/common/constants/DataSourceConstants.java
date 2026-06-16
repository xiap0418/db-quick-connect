package com.xm.db.common.constants;

import lombok.experimental.UtilityClass;

/**
 * @author xiap
 * @since 2025-11-10-18:07
 */
@UtilityClass
public class DataSourceConstants {

    /**
     * 数据库驱动类
     */
    public static class Driver {
        public static final String POSTGRESQL = "org.postgresql.Driver";
        public static final String MYSQL_CJ = "com.mysql.cj.jdbc.Driver";
        public static final String MYSQL_LEGACY = "com.mysql.jdbc.Driver";
        public static final String HIVE = "org.apache.hive.jdbc.HiveDriver";
        public static final String CLICKHOUSE = "com.clickhouse.jdbc.ClickHouseDriver";
        public static final String DATABEND = "com.databend.jdbc.DatabendDriver";
        public static final String ORACLE = "oracle.jdbc.OracleDriver";
        public static final String SQLSERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        public static final String DB2 = "com.ibm.db2.jcc.DB2Driver";
        public static final String DAMENG = "dm.jdbc.driver.DmDriver";
        public static final String KINGBASE = "com.kingbase8.Driver";
        public static final String OCEANBASE = "com.oceanbase.jdbc.Driver";
    }

    /**
     * jdbc URL prefix
     */
    public static class JdbcUrl {
        public static final String MYSQL = "jdbc:mysql://";
        public static final String MYSQL_LOADBALANCE = "jdbc:mysql:loadbalance://";
        public static final String POSTGRESQL = "jdbc:postgresql://";
        public static final String HIVE = "jdbc:hive2://";
        public static final String KYUUBI = "jdbc:kyuubi://";
        public static final String CLICKHOUSE = "jdbc:clickhouse://";
        public static final String DATABEND = "jdbc:databend://";
        public static final String ORACLE_SID = "jdbc:oracle:thin:@";
        public static final String ORACLE_SERVICE = "jdbc:oracle:thin:@//";
        public static final String SQLSERVER = "jdbc:sqlserver://";
        public static final String DB2 = "jdbc:db2://";
        public static final String DAMENG = "jdbc:dm://";
        public static final String KINGBASE = "jdbc:kingbase8://";
        public static final String OCEANBASE = "jdbc:oceanbase://";
    }

}
