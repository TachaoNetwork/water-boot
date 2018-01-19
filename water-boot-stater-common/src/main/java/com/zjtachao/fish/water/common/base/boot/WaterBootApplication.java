/**************************************************************************
 * Copyright (c) 2016-2017 Zhejiang TaChao Network Technology Co.,Ltd.
 * All rights reserved.
 *
 * 项目名称：浙江踏潮-基础架构
 * 版权说明：本软件属浙江踏潮网络科技有限公司所有，在未获得浙江踏潮网络科技有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.zjtachao.fish.water.common.base.boot;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;

/**
 * Spring Boot 启动类
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages={"com.zjtachao.fish"})
@MapperScan("com.zjtachao.fish.**.mapper")
@EnableAsync
@EnableScheduling
public class WaterBootApplication implements TransactionManagementConfigurer{

    /** 日志 **/
    public static final Logger logger = LoggerFactory.getLogger(WaterBootApplication.class);

    /** 事务 **/
    @Resource
    private PlatformTransactionManager txManager;

    /** 默认事务 **/
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager;
    }

    /**
     * 注册Jersey Bean
     * @return
     */
    @Bean
    public ServletRegistrationBean jerseyServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new ServletContainer() ,"/*");
        registrationBean.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS , WaterBootJerseyConfig.class.getName());
        return registrationBean;
    }

    /**
     * 初始化bean
     * @return
     */
    @Bean
    public DozerBeanMapper dozerMapper(){
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }

    /**
     * 启动服务
     * @param args
     */
    public static void main(String[] args){
        SpringApplication.run(WaterBootApplication.class , args);
    }


}
