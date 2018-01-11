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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 字符串保存图片
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterImgUtil {

    /**
     * 日志
     **/
    public static final Logger logger = LoggerFactory.getLogger(WaterImgUtil.class);


    /**
     * 生成图片
     */
    public static int saveToImgByInputStream(InputStream instreams, String imgPath){

        int stateInt = 1;
        if(instreams != null){
            try {
                File file=new File(imgPath);//可以是任何图片格式.jpg,.png等
                if(file.exists()){
                    file.delete();
                }

                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                FileOutputStream fos=new FileOutputStream(file);

                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = instreams.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
                try {
                    instreams.close();
                } catch (IOException e) {
                    logger.error(e.getMessage() , e);
                }
            }
        }
        return stateInt;
    }

}
