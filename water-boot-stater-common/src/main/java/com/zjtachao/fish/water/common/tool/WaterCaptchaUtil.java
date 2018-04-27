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


import java.util.Random;

/**
 * 随机生成验证码类
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterCaptchaUtil {

    /**
     * 生成随机数
     * @param length int 长度
     * @param isAllowRepeat 是否允许重复 true：允许，false：不允许 （注：需要的长度必须小于基数值：10，才生效）
     * @return
     */
    public static String generateRandom(int length , boolean isAllowRepeat){
        String result = "";
        int radix = 10;
        while(result.length()<length){
            Random random = new Random();
            String number = String.valueOf(random.nextInt(radix));
            if(!isAllowRepeat && (radix>=length)){
                if(result.contains(number)){
                    continue;
                }else{
                    result+=number;
                }
            }else{
                result+=number;
            }
        }
        return result;
    }

    /**
     *
     * 获取不重复的编号
     * @return
     */
    public static String getMillisecondNotRepcetStr(){
        String result = WaterDateUtil.getMillisecondStr()+generateRandom(6, false);
        return result;
    }

    /**
     *
     * 生成验证码
     * @return
     */
    public static String generateCaptcha(){
        String result = "";
        //获取4个不重复的数字
        String firstCode = generateRandom(4, false);
        String secondCode = generateRandom(1, true);
        String thirdCode = generateRandom(1, true);
        result = insertRandomStr(firstCode, secondCode);
        result = insertRandomStr(result, thirdCode);
        //去除0的影响
        String start = result.substring(0,1);
        if("0".equals(start)){
            String tmp = result;
            String end = result.substring(1);
            result = "8"+end;
            System.out.println("start:"+tmp+" end:"+result);
        }
        return result;
    }

    /**
     *
     * 随机查询字符串
     * @param sourceStr
     * @param insertStr
     * @return
     */
    public static String insertRandomStr(String sourceStr , String insertStr){
        Random random = new Random();
        int number = random.nextInt(sourceStr.length());
        String startStr = sourceStr.substring(0, number);
        String endStr = sourceStr.substring(number);
        String result = startStr + insertStr + endStr;
        return result;
    }
}
