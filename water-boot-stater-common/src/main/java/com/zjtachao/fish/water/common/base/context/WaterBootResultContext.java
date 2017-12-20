/**************************************************************************
 * Copyright (c) 2016-2017 Zhejiang TaChao Network Technology Co.,Ltd.
 * All rights reserved.
 *
 * 项目名称：浙江踏潮-基础架构
 * 版权说明：本软件属浙江踏潮网络科技有限公司所有，在未获得浙江踏潮网络科技有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.zjtachao.fish.water.common.base.context;

/**
 * 通用返回值枚举类
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterBootResultContext {

    public enum ResultCode{
        /** SUCCESS **/
        SUCCESS(0,"SUCCESS"),
        /** ERROR **/
        ERROR(2,"ERROR"),
        /** VALID_NO_PASS **/
        VALID_NO_PASS(1,"VALID_NO_PASS");

        /** 编码 **/
        private int code;

        /** 名称 **/
        private String name;

        /**
         * 构造函数
         * @param code
         * @param name
         */
        ResultCode(int code , String name){
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

}
