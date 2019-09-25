package com.sykean.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.Properties;

/**
 * 数据库连接池
 */
@Configuration
@MapperScan(basePackages = "com.sykean.mapper")
@Slf4j
public class DruidConfig {

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    private PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * 分页插件
     */
    private PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setProxyFilters(Lists.newArrayList(statFilter()));
        return dataSource;
    }

    @Bean
    public Filter statFilter() {
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(5000);
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }

    /**
     * 置SQL监控用户名和密码
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", "lee");
        reg.addInitParameter("loginPassword", "123456");
        return reg;
    }

    /**
     * 设置SQL监控管理平台访问地址
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    private GlobalConfiguration globalConfiguration() {
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        //主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
        globalConfiguration.setIdType(1);
        //字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
        globalConfiguration.setFieldStrategy(1);
        //驼峰下划线转换
        globalConfiguration.setDbColumnUnderline(true);
        //刷新mapper 调试神器
        globalConfiguration.setRefresh(true);
        return globalConfiguration;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        //设置分页插件
        sqlSessionFactory.setPlugins(new Interceptor[]{
//                performanceInterceptor(),
                pageHelper()
        });
        MybatisConfiguration configuration = new MybatisConfiguration();
        //转驼峰
        configuration.setMapUnderscoreToCamelCase(true);
        //全局配置
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:mappers/*Mapper.xml"));
        sqlSessionFactory.setGlobalConfig(globalConfiguration());
        return sqlSessionFactory.getObject();
    }
}
