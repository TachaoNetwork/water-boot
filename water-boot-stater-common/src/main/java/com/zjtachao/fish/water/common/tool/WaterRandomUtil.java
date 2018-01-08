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
 * 随机数
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterRandomUtil {

    /**
     *
     * 生成六位随机数
     * @return
     */
    public static String random(){

        StringBuffer buffer = new StringBuffer();
        //添加三位随机数
        //生成三个 0-9
        int num1, num2, num3, num4, num5, num6;
        Random rnd = new Random();
        num1 = rnd.nextInt(9);
        num2 = rnd.nextInt(9);
        num3 = rnd.nextInt(9);
        num4 = rnd.nextInt(9);
        num5 = rnd.nextInt(9);
        num6 = rnd.nextInt(9);

        String num=num1+""+num2+""+num3+""+num4+""+num5+""+num6;
        buffer.append(num);

        return buffer.toString();
    }

    /**
     *
     * 获得指定长度随机数
     * @param length
     * @return
     */
    public static String random(int length){
        String result = "";
        if(length > 0){
            StringBuffer sb = new StringBuffer();
            Random rnd = new Random();
            for(int i =0 ;i<length;i++){
                int num = rnd.nextInt(9);
                sb.append(num);
            }
            result = sb.toString();
        }
        return result;
    }

    public static int randomSet(int lastNumber,int number){
        Random random = new Random();
        int num = random.nextInt(number);

        while(num == lastNumber){
            num = random.nextInt(number);
        }

        return num;
    }

    public static void main(String args[]){
        System.out.println(randomSet(1,5));
    }

}
