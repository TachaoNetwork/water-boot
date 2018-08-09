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

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 网络请求工具类
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterHttpUtil {


    /** 日志 **/
    public static final Logger logger = LoggerFactory.getLogger(WaterHttpUtil.class);

    /**
     * 提交Http请求 POST
     * @param url 地址
     * @param content 内容
     * @return
     */
    public static String httpPost(String url , String content , String format , String resultForamt) {
        String result = null;
        HttpPost request = null;
        CloseableHttpResponse response = null;
        try{
            StringBuffer buffer = new StringBuffer();
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            request = new HttpPost(url);
            StringEntity entity = new StringEntity(content , resultForamt);
            entity.setContentType(format);
            entity.setContentEncoding(resultForamt);
            request.setEntity(entity);
            request.setHeader("Accept-Charset" , resultForamt);
            request.setHeader("Content-Type",format);

            //设置请求超时时间和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .build();
            request.setConfig(requestConfig);

            HttpContext context = new BasicHttpContext();
            response = httpClient.execute(request , context);

            if((null != response) && (null != response.getStatusLine())
                    && (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),resultForamt));
                String output = null;
                while ((output = br.readLine()) != null){
                    buffer.append(output);
                }
                result = buffer.toString();
            }else {
                logger.warn("响应错误，状态码："+response.getStatusLine().getStatusCode()+" URL："+url);
            }
        }catch (MalformedURLException ex){
            logger.error("响应错误，URL："+url , ex);
        }catch (IOException ex){
            logger.error("IO错误，URL："+url , ex);
        }catch (Exception ex){
            logger.error("请求错误，URL："+url , ex);
        }finally {
            if(null != request){
                request.releaseConnection();;
            }
            if(null != response){
                try {
                    response.close();
                } catch (IOException ex) {
                    logger.error("关闭链接错误",ex);
                }
            }
        }
        return result;
    }

    /**
     * 提交Http请求 POST
     * @param url 地址
     * @param content 内容
     * @return
     */
    public static String httpPost(String url , String content , String format) {
        return httpPost(url , content , format ,"UTF-8");
    }


    /**
     * 提交Http请求 POST
     * @param url 地址
     * @param content 内容
     * @return
     */
    public static String httpPost(String url , String content) {
        return httpPost(url , content , "application/json;charset=UTF-8");
    }


    /**
     * 提交Http请求 POST
     * @param url 地址
     * @param pairs 内容
     * @return
     */
    public static String httpPost(String url , List<NameValuePair> pairs) {
        String result = null;
        HttpPost request = null;
        CloseableHttpResponse response = null;
        try{
            StringBuffer buffer = new StringBuffer();
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            request = new HttpPost(url);
            if(null != pairs && !pairs.isEmpty()){
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs);
                request.setEntity(urlEncodedFormEntity);
            }

            //设置请求超时时间和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .build();
            request.setConfig(requestConfig);

            HttpContext context = new BasicHttpContext();
            response = httpClient.execute(request , context);

            if((null != response) && (null != response.getStatusLine())
                    && (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),StandardCharsets.UTF_8.name()));
                String output = null;
                while ((output = br.readLine()) != null){
                    buffer.append(output);
                }
                result = buffer.toString();
            }else {
                logger.warn("响应错误，状态码："+response.getStatusLine().getStatusCode()+" URL："+url);
            }
        }catch (MalformedURLException ex){
            logger.error("响应错误，URL："+url , ex);
        }catch (IOException ex){
            logger.error("IO错误，URL："+url , ex);
        }catch (Exception ex){
            logger.error("请求错误，URL："+url , ex);
        }finally {
            if(null != request){
                request.releaseConnection();;
            }
            if(null != response){
                try {
                    response.close();
                } catch (IOException ex) {
                    logger.error("关闭链接错误",ex);
                }
            }
        }
        return result;
    }

    /**
     * 提交Http请求 POST
     * @param url 地址
     * @return
     */
    public static boolean httpPostForImg(String url , String content , String path) {
        boolean flag = false;
        HttpPost request = null;
        CloseableHttpResponse response = null;
        try{
            StringBuffer buffer = new StringBuffer();
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            request = new HttpPost(url);
            StringEntity entity = new StringEntity(content , StandardCharsets.UTF_8.name());
            entity.setContentType("application/json;charset=UTF-8");
            request.setEntity(entity);

            //设置请求超时时间和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .build();
            request.setConfig(requestConfig);

            HttpContext context = new BasicHttpContext();
            response = httpClient.execute(request , context);

            if((null != response) && (null != response.getStatusLine())
                    && (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) ) {
                boolean imgFlag = false;
                if(null != response.getAllHeaders() && response.getAllHeaders().length > 0){
                    for(Header header : response.getAllHeaders()){
                        if(header.getValue().equalsIgnoreCase("image/jpeg")){
                            imgFlag = true;
                            break;
                        }
                    }
                }
                if(imgFlag){
                    WaterImgUtil.saveToImgByInputStream(response.getEntity().getContent() , path);
                    flag = true;
                }else {
                    String result = null;
                    BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),StandardCharsets.UTF_8.name()));
                    String output = null;
                    while ((output = br.readLine()) != null){
                        buffer.append(output);
                    }
                    result = buffer.toString();
                    logger.warn("解析错误的图片："+result);
                    flag = false;
                }

            }else {
                logger.warn("响应错误，状态码："+response.getStatusLine().getStatusCode()+" URL："+url);
            }
        }catch (MalformedURLException ex){
            logger.error("响应错误，URL："+url , ex);
        }catch (IOException ex){
            logger.error("IO错误，URL："+url , ex);
        }catch (Exception ex){
            logger.error("请求错误，URL："+url , ex);
        }finally {
            if(null != request){
                request.releaseConnection();;
            }
            if(null != response){
                try {
                    response.close();
                } catch (IOException ex) {
                    logger.error("关闭链接错误",ex);
                }
            }
        }
        return flag;
    }

    /**
     * 提交Http请求 Get
     * @param url 地址
     * @return
     */
    public static String httpGet(String url) {
        String result = null;
        HttpGet request = null;
        CloseableHttpResponse response = null;
        try{
            StringBuffer buffer = new StringBuffer();
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            request = new HttpGet(url);


            //设置请求超时时间和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .build();
            request.setConfig(requestConfig);

            HttpContext context = new BasicHttpContext();
            response = httpClient.execute(request , context);

            if((null != response) && (null != response.getStatusLine())
                    && (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),StandardCharsets.UTF_8.name()));
                String output = null;
                while ((output = br.readLine()) != null){
                    buffer.append(output);
                }
                result = buffer.toString();
            }else {
                logger.warn("响应错误，状态码："+response.getStatusLine().getStatusCode()+" URL："+url);
            }
        }catch (MalformedURLException ex){
            logger.error("响应错误，URL："+url , ex);
        }catch (IOException ex){
            logger.error("IO错误，URL："+url , ex);
        }catch (Exception ex){
            logger.error("请求错误，URL："+url , ex);
        }finally {
            if(null != request){
                request.releaseConnection();;
            }
            if(null != response){
                try {
                    response.close();
                } catch (IOException ex) {
                    logger.error("关闭链接错误",ex);
                }
            }
        }
        return result;
    }

    public static void main(String [] args){
        String getUrl = "http://127.0.0.1:8080/water/demo/test/redis/hello/123121";
        System.out.print(httpGet(getUrl));
        String postUrl = "http://127.0.0.1:8080/water/demo/test/annotations/save";
        String content = "{\n" +
                "\t\"userCode\":\"123456\",\n" +
                "\t\"userNickName\":\"昵称\",\n" +
                "\t\"userAge\":20,\n" +
                "\t\"deleteFlag\":\"1\"\n" +
                "}";
        System.out.print(httpPost(postUrl , content));
    }

}
