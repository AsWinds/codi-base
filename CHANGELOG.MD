## CHANGELOG

### 1.8.0
- 基于poi的Excel导入导出

### 1.7.2
- 添加MDC支持
- MDC filter for dubbo

### 1.6.7
- log4j 可选

### 1.6.3
- 签名函数

### 1.6.2
- 补充全局异常

### 1.6.0
- 显示`BaseAppException`异常内容

### 1.5.0
- 新增如下DAO基类 
````
com.codi.base.dao.v2.BaseDao
com.codi.base.dao.v2.BaseDaoImpl
com.codi.base.dao.v2.GeneratedBaseDao
com.codi.base.dao.v2.GeneratedBaseDaoImpl
````
- CollectionResult
- PageResult

### 1.4.0
- spring包修改为可选依赖
- apache-commons, mybatis包修改为为默认依赖
- 不再使用数据库监控, 如果需要监控, 请使用阿里云

### 1.3.6
- BaseResult新增方法，MapUtils新增方法

### 1.3.5 
- remove @Autowired RedisTemplate

### 1.3.3.fix 
- 初始版本导入  
