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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证码
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterRegexValidateUtil {

    /**
     *
     * 验证是否是邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        if(email.contains("@")){
            flag = true;
        }
        return flag;
    }

    /**
     *
     * 检测是否为手机
     *
     * @param mobile
     * @return
     */
    public static boolean checkMobile(String mobile) {
        boolean flag = false;
        String check = "^1\\d{10}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(mobile);
        flag = matcher.matches();
        return flag;
    }

    /**
     *
     * 验证字符是否是数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        boolean flag = false;
        Pattern pattern = Pattern.compile("[0-9]+");
        if(null != str){
            Matcher match = pattern.matcher(str);
            if (match.matches()) {
                flag = true;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        String mobile = "12325433607";
        System.out.println(checkMobile(mobile));

        String email = "121@11.s";
        System.out.println(checkEmail(email));
    }

}
