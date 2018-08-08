/**************************************************************************
 * Copyright (c) 2016-2018 Zhejiang TaChao Network Technology Co.,Ltd.
 * All rights reserved.
 *
 * 项目名称：浙江踏潮-基础项目
 * 版权说明：本软件属浙江踏潮网络科技有限公司所有，在未获得浙江踏潮网络科技有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.zjtachao.fish.water.common.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 路径加密和解密处理
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterUrlUtil {

    /** 日志 **/
    private static final Logger logger = LoggerFactory.getLogger(WaterUrlUtil.class);

    /**
     * 解密
     * @param data
     * @return
     */
    public static String decode(String data){
        String result = null;
        try {
            result = URLDecoder.decode(data , "UTF-8");
        }catch (Exception ex){
            logger.error("解密错误，"+data);
        }
        return result;
    }

    /**
     * 解密
     * @param data
     * @return
     */
    public static String encode(String data){
        String result = null;
        try {
            result = URLEncoder.encode(data , "UTF-8");
        }catch (Exception ex){
            logger.error("解密错误，"+data);
        }
        return result;
    }

}
