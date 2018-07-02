package com.blog.sjh.config;

import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * 配置 duid 数据源
 * @author ex-sujh
 * @date 2018年6月5日18:06:16
 */
@Configuration
@PropertySources({
	@PropertySource(value = "classpath:druid.properties", ignoreResourceNotFound = true)
})
@Slf4j
public class DruidConfig {

	@Value("${druid.datasource.url}")
	private String url;
	
	@Value("${druid.datasource.username}")
	private String username;
	
	@Value("${druid.datasource.password}")
	private String password;
	
	@Value("${druid.datasource.type}")
	private String type;
	
	@Value("${druid.datasource.driverClassName}")
	private String driverClassName;
	
	@Value("${druid.datasource.filters}")
	private String filters;
	
	@Value("${druid.datasource.maxActive}")
	private Integer maxActive;
	
	@Value("${druid.datasource.initialSize}")
	private Integer initialSize;
	
	@Value("${druid.datasource.maxWait}")
	private Integer maxWait;
	
	@Value("${druid.datasource.minIdle}")
	private Integer minIdle;
	
	@Value("${druid.datasource.timeBetweenEvictionRunsMillis}")
	private Integer timeBetweenEvictionRunsMillis;
	
	@Value("${druid.datasource.minEvictableIdleTimeMillis}")
	private Integer minEvictableIdleTimeMillis;
	
	@Value("${druid.datasource.queryTimeout}")
	private Integer queryTimeout;
	
	@Value("${druid.datasource.validationQuery}")
	private String validationQuery;
	
	@Value("${druid.datasource.testWhileIdle}")
	private boolean testWhileIdle;
	
	@Value("${druid.datasource.testOnBorrow}")
	private boolean testOnBorrow;
	
	@Value("${druid.datasource.testOnReturn}")
	private boolean testOnReturn;
	
	@Value("${druid.datasource.poolPreparedStatements}")
	private boolean poolPreparedStatements;
	
	@Value("${druid.datasource.maxPoolPreparedStatementPerConnectionSize}")
	private Integer maxPoolPreparedStatementPerConnectionSize;
	
	@Value("${druid.datasource.logSlowSql}")
	private String logSlowSql;
	
	@Value("${druid.datasource.connectionProperties}")
	private String connectionProperties;
	
	@Bean
	public ServletRegistrationBean<Servlet> druidServlet() {
		log.error("=== Regist ServletRegistrationBean ===");
		ServletRegistrationBean<Servlet> reg = new ServletRegistrationBean<>();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		// 设置白名单, 不设置则允许所有
		reg.addInitParameter("allow", "127.0.0.1");
		// 设置黑名单, 优先级高于白名单
		reg.addInitParameter("deny", "192.168.8.164");
		reg.addInitParameter("loginUsername", "sjh");
		reg.addInitParameter("loginPassword", "123456");
		reg.addInitParameter("logSlowSql", logSlowSql);
		return reg;
	}
	
	@Bean
	public FilterRegistrationBean<Filter> druidFilter() {
		log.error("=== Regist FilterRegistrationBean ===");
		FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
		filter.setFilter(new WebStatFilter());
		filter.addUrlPatterns("/");
		filter.addInitParameter("exclusions", "*.js,*.gif,*.jgp,*.png,*.css,*.ico,/druid/*");
		filter.addInitParameter("profileEnable", "true");
		return filter;
	}
	
	@Bean
	@Primary
	public DataSource druidDataSource() {
		log.debug("===== 加载 DruidDataSource =====");
		DruidDataSource druidDataSource = new DruidDataSource();
		try {
			druidDataSource.setUrl(url);
			druidDataSource.setUsername(username);
			druidDataSource.setPassword(password);
			// 如果不配置，druid 会根据 url 自动识别dbType,并选择相应的 driverClassName
			druidDataSource.setDriverClassName(driverClassName);
		
			druidDataSource.setFilters(filters);
			druidDataSource.setMaxActive(maxActive);
			druidDataSource.setInitialSize(initialSize);
			druidDataSource.setMaxWait(maxWait);
			druidDataSource.setMinIdle(minIdle);
			druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
			druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
			druidDataSource.setQueryTimeout(queryTimeout);
			druidDataSource.setValidationQuery(validationQuery);
			druidDataSource.setTestWhileIdle(testWhileIdle);
			druidDataSource.setTestOnBorrow(testOnBorrow);
			druidDataSource.setTestOnReturn(testOnReturn);
			druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
			druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
			druidDataSource.setConnectionProperties(connectionProperties);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return druidDataSource;
	}
	
}
