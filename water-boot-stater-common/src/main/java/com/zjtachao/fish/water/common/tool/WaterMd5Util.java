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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Md5加密类
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterMd5Util {

    /**
     * 16位加密
     * @param plainText plainText
     * @return String
     */
    public static String Md516(String plainText) {
        return Md516(plainText, StandardCharsets.UTF_8.name());
    }

    /**
     * 16位加密
     * @param plainText plainText
     * @return String
     */
    public static String Md516(String plainText , String charSet) {
        String result = null;
        try {
            byte[] ptBytes = plainText.getBytes(charSet);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(ptBytes);
            byte []b = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0){
                    i += 256;
                }
                if (i < 16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            // result = buf.toString(); //md5 32bit
            // result = buf.toString().substring(8, 24))); //md5 16bit
            result = buf.toString().substring(8, 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 32位加密
     * @param plainText plainText
     * @return String
     */
    public static String Md532(String plainText) {
        return Md532(plainText, StandardCharsets.UTF_8.name());
    }

    /**
     * 32位加密
     * @param plainText plainText
     * @return String
     */
    public static String Md532(String plainText , String charSet) {
        String result = null;
        try {
            byte[] ptBytes = plainText.getBytes(charSet);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(ptBytes);
            byte[] b = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0){
                    i += 256;
                }
                if (i < 16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            // result = buf.toString(); //md5 32bit
            // result = buf.toString().substring(8, 24))); //md5 16bit
            result = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
