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
 * 请在此处添加注释
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterBootCommonContext {

    /**
     *
     * 删除标志枚举
     * @author <a href="mailto:dh@zjtachao.com">duhao</a>
     * @version $Id$
     * @since 2.0
     */
    public enum DeleteFlagContext{
        /** 正常 **/
        DELETE_NO("0" , "否"),
        /** 已删除 **/
        DELETE_YES("1" , "是");

        /** 编码 **/
        private String code;
        /** 名称 **/
        private String name;

        /**
         * 构造方法
         *@param name name
         *@param code code
         */
        private DeleteFlagContext(String code, String name) {
            this.name = name;
            this.code = code;
        }

        /**
         *
         * 获得名称
         * @param code code
         * @return String 名称
         */
        public static String getName(String code) {
            String name = null;
            for (DeleteFlagContext c : DeleteFlagContext.values()) {
                if (c.getCode().equals(code)) {
                    name = c.getName();
                }
            }
            return name;
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
