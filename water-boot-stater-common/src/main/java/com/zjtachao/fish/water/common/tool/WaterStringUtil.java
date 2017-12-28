/**************************************************************************
 * Copyright (c) 2016-2017 Zhejiang TaChao Network Technology Co.,Ltd.
 * All rights reserved.
 *
 * 项目名称：浙江踏潮-基础架构
 * 版权说明：本软件属浙江踏潮网络科技有限公司所有，在未获得浙江踏潮网络科技有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.zjtachao.fish.water.common.tool;

import java.util.List;

/**
 * 字符串类型处理
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterStringUtil {


    /**
     * 字符串列表转成字符串
     * @param list
     * @return
     */
    public static String listToString(List<String> list){
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append("'" +list.get(i) + "',");
                } else {
                    sb.append("'" +list.get(i) + "'");
                }
            }
        }
        return sb.toString();
    }

    /**
     *
     * 获得字符串长度
     * @param s
     * @return
     */
    public static int getStringLength(String s) {
        int length = 0;
        if((null != s) && (!"".equals(s))){
            for (int i = 0; i < s.length(); i++) {
                int ascii = Character.codePointAt(s, i);
                if (ascii >= 0 && ascii <= 255){
                    length++;
                }else{
                    length += 2;
                }
            }
        }
        return length;
    }

}
