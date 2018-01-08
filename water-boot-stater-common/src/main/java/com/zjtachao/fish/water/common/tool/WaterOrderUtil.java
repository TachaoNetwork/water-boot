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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 订单util
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterOrderUtil {

    private static final AtomicInteger WORK_COUNT = new AtomicInteger(1);


    /**
     *
     * 获得数字的位数
     * @param num
     * @return
     */
    private static int calBitCount(int num){
        return getBitCount(num, 1);
    }

    /**
     *
     * 获取位数
     * @param num
     * @param count
     * @return
     */
    private static int getBitCount(int num , int count){
        int plus = num / 10;
        if(plus != 0){
            count ++ ;
            count = getBitCount(plus , count);
        }
        return count;
    }


    /**
     *
     * 获得workid
     * @return
     */
    private static String getWorkId(){
        int count = WORK_COUNT.getAndAdd(1);
        if(count >= 999999){
            WORK_COUNT.set(1);
        }
        //随机数补位数
        int length = calBitCount(count);
        String rndStr = WaterRandomUtil.random(12-length);
        String assembleStr = count + rndStr;
        return assembleStr;
    }

    /**
     *
     * 获得订单号
     * @return
     */
    public static String getOrderNo(){
        return WaterDateUtil.getNowtimeStr() + getWorkId();
    }

    public static void main(String[] args){
        for(int i=0;i<1000;i++){
            System.out.println(getOrderNo());
        }
    }

}
