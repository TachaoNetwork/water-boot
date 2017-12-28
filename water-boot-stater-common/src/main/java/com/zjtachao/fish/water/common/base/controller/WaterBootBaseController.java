/**************************************************************************
 * Copyright (c) 2016-2017 Zhejiang TaChao Network Technology Co.,Ltd.
 * All rights reserved.
 *
 * 项目名称：浙江踏潮-基础架构
 * 版权说明：本软件属浙江踏潮网络科技有限公司所有，在未获得浙江踏潮网络科技有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.zjtachao.fish.water.common.base.controller;

import com.zjtachao.fish.water.common.data.WaterRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Rest服务基类
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public abstract class WaterBootBaseController {

    /** 日志 **/
    public static final Logger logger = LoggerFactory.getLogger(WaterBootBaseController.class);

    /** Redis **/
    @Autowired
    public WaterRedis waterRedis;

    /** 配置参数 **/
    @Autowired
    public Environment waterEnv;


    /**
     * 添加cookie
     * @param cookieid
     * @param value
     * @param time 时间以秒为单位
     * @param response
     * @return
     */
    public HttpServletResponse addCookie(String cookieid, String value , int time , HttpServletResponse response){
        Cookie cookie = new Cookie(cookieid,value);
        cookie.setMaxAge(time);
        cookie.setPath("/");
        response.addCookie(cookie);
        return response;
    }


}
