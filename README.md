# db-quick-connect

# 异构数据源连接组件（Heterogeneous DataSource Connector）

> 一个支持多数据库类型、动态加载、池化管理的 Java 数据源连接组件，适用于多租户、数据集成等需要按需连接异构数据库的场景。

---

## ✨ 特性

- **支持多种数据库**：MySQL、达梦（Dameng）、金仓（Kingbase）等（通过 SPI 扩展可支持更多）
- **连接池管理**：基于 HikariCP，支持默认/自定义池配置
- **临时连接支持**：适用于一次性查询或低频访问场景
- **自动缓存与清理**：Caffeine 缓存池化连接，24 小时自动过期；JVM 退出时自动清理
- **连通性测试 & 监控指标**：提供 `testConnection` 和 `getMetrics` 接口
- **线程安全**：适用于高并发环境

---

## ⚠️ 使用须知（重要）

1. **首次延迟**：连接在首次访问时按需创建，**不适合对首次响应延迟敏感的核心业务**（建议此类场景使用 Spring 预初始化数据源）。
2. **资源管理**：**必须使用 `try-with-resources`** 确保连接正确关闭。
3. **缓存机制**：池化连接会缓存于 Caffeine 中，24 小时后自动清除。
4. **扩展支持**：如需接入未内置的数据库，请参考《异构数据源连接组件开发指南》。

---

## 📦 Maven 依赖

在 `pom.xml` 中添加核心包及所需数据库插件：

```xml
<!-- 核心组件 -->
<dependency>
    <groupId>com.yitu</groupId>
    <artifactId>datasource-core</artifactId>
    <version>1.0.2</version>
</dependency>

        <!-- MySQL 支持 -->
<dependency>
    <groupId>com.yitu</groupId>
    <artifactId>datasource-mysql</artifactId>
    <version>1.0.2</version>
</dependency>

        <!-- 达梦数据库支持 -->
<dependency>
    <groupId>com.yitu</groupId>
    <artifactId>datasource-dameng</artifactId>
    <version>1.0.2</version>
</dependency>

        <!-- 金仓数据库支持 -->
<dependency>
    <groupId>com.yitu</groupId>
    <artifactId>datasource-kingbase</artifactId>
    <version>1.0.2</version>
</dependency>
```

组件通过 SPI 机制动态加载数据库插件，内置默认 JDBC 驱动版本，也可传入高版本驱动覆盖。
---

## 🚀 快速开始

### 1. 获取临时连接（Ad-hoc）

适用于一次性操作，不使用连接池：

```java
try(Connection connection = DataSourceClientProvider
        .getAdHocConnection(DbType.MYSQL, param)){
        log.info("get ad hoc connection");
}
```

### 2. 获取池化连接（推荐用于高频访问）

使用默认池配置（minIdle=5, maxPoolSize=10, timeout=10s）：

```java
try(Connection connection = DataSourceClientProvider
        .getPooledConnection(DbType.MYSQL, param)){
        log.info("get pooled connection");
}
```

自定义连接池配置：

```java
PoolConfig poolConfig = new PoolConfig();
poolConfig.setMinIdle(2);
poolConfig.setMaxPoolSize(4);
poolConfig.setConnectionTimeoutMs(5000);

try(
Connection connection = DataSourceClientProvider
        .getPooledConnection(DbType.MYSQL, param, poolConfig)){
        log.info("get pooled connection");
}
```

### 3. 测试连通性

```java
DataSourceClientProvider.testConnection(DbType.MYSQL, param);
```

### 4. 获取连接池监控指标

```java
DataSourceClientProvider.getMetrics(DbType.MYSQL, param);
```