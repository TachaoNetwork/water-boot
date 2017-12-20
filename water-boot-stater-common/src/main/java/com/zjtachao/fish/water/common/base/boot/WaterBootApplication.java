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

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot 启动类
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.zjtachao.fish"})
@MapperScan("com.zjtachao.fish")
public class WaterBootApplication {

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
     * 启动服务
     * @param args
     */
    public static void main(String[] args){
        SpringApplication.run(WaterBootApplication.class , args);
    }


}
