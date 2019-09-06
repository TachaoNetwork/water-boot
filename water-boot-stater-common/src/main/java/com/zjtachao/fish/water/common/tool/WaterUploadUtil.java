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

import com.zjtachao.fish.water.common.base.bean.WaterBootUploadBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * 文件上传类
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterUploadUtil {

    /**
     *
     * 上传图片的方法
     * @param path
     * @param inputStream
     * @param imageName
     * @return
     * @throws IOException
     */
    public static String uploadFile(String path, InputStream inputStream, String imageName , String folderPrefix) throws IOException {
        String result = null;
        WaterBootUploadBean bean = handleFile(path, inputStream, imageName, folderPrefix);
        if(null != bean && null != bean.getPath()){
            result = bean.getPath();
        }
        return result;
    }

    /**
     * 存储文件
     * @param path
     * @param inputStream
     * @param imageName
     * @param folderPrefix
     * @return
     * @throws IOException
     */
    public static File storageFile(String path, InputStream inputStream, String imageName , String folderPrefix) throws IOException {
        File result = null;
        WaterBootUploadBean bean = handleFile(path, inputStream, imageName, folderPrefix);
        if(null != bean && null != bean.getFile()){
            result = bean.getFile();
        }
        return result;
    }

    /**
     * 存储文件
     * @param path
     * @param inputStream
     * @param imageName
     * @param folderPrefix
     * @return
     * @throws IOException
     */
    public static WaterBootUploadBean storageFileBean(String path, InputStream inputStream, String imageName , String folderPrefix) throws IOException {
        WaterBootUploadBean bean = handleFile(path, inputStream, imageName, folderPrefix);
        return bean;
    }


    /**
     * 返回对象
     * @param path
     * @param inputStream
     * @param imageName
     * @param folderPrefix
     * @return
     * @throws IOException
     */
    private static WaterBootUploadBean handleFile(String path, InputStream inputStream, String imageName , String folderPrefix) throws IOException {
        int random = (int)(Math.random()*900)+100;
        /*设置上传目录*/
        Calendar cal=Calendar.getInstance();
        String month = String.valueOf(cal.get(Calendar.MONTH)+1);
        month = month.length() == 1 ? "0" + month : month;
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        day = day.length() == 1 ? "0" + day : day;
        String imageUrlPath = folderPrefix + "/" + cal.get(Calendar.YEAR) + "/" + month + "/" + day;
        String imagePath = path + imageUrlPath;
        File file = new File(imagePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        /*设置目标文件*/
        String targetImageName = "" + cal.get(Calendar.HOUR) + cal.get(Calendar.MINUTE) + cal.get(Calendar.SECOND)
                + cal.get(Calendar.MILLISECOND) + random + imageName.substring(imageName.lastIndexOf("."));
        File target = new File(imagePath + "/" + targetImageName);
        /*创建输出流*/
        OutputStream os = new FileOutputStream(target);
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        inputStream.close();
        os.close();
        String key = "/" + imageUrlPath + "/" + targetImageName;

        WaterBootUploadBean uploadBean = new WaterBootUploadBean(key, target);
        return uploadBean;
    }



    /**
     * 上传图片的方法
     * @param imageFile
     * @param imageName
     * @return
     * @throws IOException
     */
    public static String uploadImage(String path, File imageFile, String imageName) throws IOException {
        /*创建输入流*/
        InputStream is = new FileInputStream(imageFile);
        return uploadImage(path, is, imageName);
    }

    /**
     *
     * 上传图片的方法
     * @param path
     * @param inputStream
     * @param imageName
     * @return
     * @throws IOException
     */
    public static String uploadImage(String path, InputStream inputStream, String imageName) throws IOException {
        return uploadFile(path, inputStream, imageName, "images");
    }

    /**
     *
     * 上传图片的方法
     * @return
     * @throws IOException
     */
    public static String uploadFile(InputStream inputStream,  String folderPrefix , String dateFormat , String fileName) throws IOException {
        String imagePath = folderPrefix;
        if(null != dateFormat && !"".equals(dateFormat)){
            Calendar cal = Calendar.getInstance();
            if(dateFormat.contains("Y")){
                imagePath += "/" + cal.get(Calendar.YEAR);
            }
            if(dateFormat.contains("M")){
                String month = String.valueOf(cal.get(Calendar.MONTH)+1);
                month = month.length() == 1 ? "0" + month : month;
                imagePath += "/" + month;
            }
            if(dateFormat.contains("D")){
                String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
                day = day.length() == 1 ? "0" + day : day;;
                imagePath += "/" + day;
            }
        }

        File file = new File(imagePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        /*设置目标文件*/
        String targetImageName = WaterUUIDUtil.getUUID() + fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        File target = new File(imagePath + "/" + targetImageName);
        /*创建输出流*/
        OutputStream os = new FileOutputStream(target);
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        inputStream.close();
        os.close();
        return imagePath + "/" + targetImageName;
    }

}
