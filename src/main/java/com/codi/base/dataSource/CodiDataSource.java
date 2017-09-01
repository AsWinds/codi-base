package com.codi.base.dataSource;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallFilter;
import com.codi.base.config.ConfigurationMgr;
import com.codi.base.util.StringUtil;

public class CodiDataSource extends DruidDataSource {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(CodiDataSource.class);

    public CodiDataSource(String dataSourceSuffix) throws SQLException {
        super();

        logger.debug("---------init CodiDataSource----------");
        logger.debug("dataSourceSuffix={}", dataSourceSuffix);

        String suffix = "";
        if (StringUtil.isNotEmpty(dataSourceSuffix)) {
            suffix = "." + dataSourceSuffix;
        }

        ConfigurationMgr configMgr = ConfigurationMgr.getInstance();
        this.setName(dataSourceSuffix.toUpperCase());
        this.setDriverClassName(configMgr.getString("jdbc.drivername" + suffix));
        this.setUrl(configMgr.getString("jdbc.url" + suffix));
        this.setUsername(configMgr.getString("jdbc.username" + suffix));
        this.setPassword(configMgr.getString("jdbc.password" + suffix));
        this.setFilters(configMgr.getString("jdbc.filters" + suffix));

        StringBuffer sb = new StringBuffer();
        sb.append("config.decrypt=").append(configMgr.getString("jdbc.decrypt" + suffix)).append(";");
        sb.append("config.decrypt.key=").append(configMgr.getString("jdbc.publickey" + suffix));
        this.setConnectionProperties(sb.toString());

        this.setInitialSize(configMgr.getInt("jdbc.initialSize" + suffix, 10)); // 初始化连接大小
        this.setMaxActive(configMgr.getInt("jdbc.maxActive" + suffix, 300)); // 连接池最大使用连接数量
        this.setMinIdle(configMgr.getInt("jdbc.minIdle" + suffix, 10)); // 连接池最小空闲
        this.setMaxWait(configMgr.getInt("jdbc.maxWait" + suffix, 60000)); // 获取连接最大等待时间

        this.setValidationQuery(configMgr.getString("jdbc.validationQuery" + suffix));
        this.setTestOnBorrow(configMgr.getBoolean("jdbc.testOnBorrow" + suffix));
        this.setTestOnReturn(configMgr.getBoolean("jdbc.testOnReturn" + suffix));
        this.setTestWhileIdle(configMgr.getBoolean("jdbc.testWhileIdle" + suffix));

        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        this.setTimeBetweenEvictionRunsMillis(configMgr.getLong("jdbc.timeBetweenEvictionRunsMillis" + suffix));
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        this.setMinEvictableIdleTimeMillis(configMgr.getLong("jdbc.minEvictableIdleTimeMillis" + suffix));
        // 打开removeAbandoned功能
        this.setRemoveAbandoned(configMgr.getBoolean("jdbc.removeAbandoned" + suffix));
        this.setRemoveAbandonedTimeout(configMgr.getInt("jdbc.removeAbandonedTimeout" + suffix));
        // 关闭abanded连接时输出错误日志
        this.setLogAbandoned(configMgr.getBoolean("jdbc.logAbandoned" + suffix));
        
        // druid 监控配置
        if (configMgr.getBoolean("druid.monitor.enable", false)) {
        	ArrayList<Filter> filters = new ArrayList<Filter>(5);

        	// 配置 Log4jFilter
        	Slf4jLogFilter slf4jLogFilter = new Slf4jLogFilter();
        	filters.add(slf4jLogFilter);
        	
        	// 防范SQL注入
        	WallFilter wallFilter = new WallFilter();
        	filters.add(wallFilter);
        	
        	// 配置filters到druid
        	this.setProxyFilters(filters);
		}
    }
}
