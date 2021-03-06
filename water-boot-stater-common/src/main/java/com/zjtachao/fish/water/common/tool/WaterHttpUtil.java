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

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static String httpPost(String url , String content , String format , String resultFormat , Integer timeout , Map<String, String> headerMap) {
        String result = null;
        HttpPost request = null;
        CloseableHttpResponse response = null;
        try{
            StringBuffer buffer = new StringBuffer();
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            request = new HttpPost(url);
            request.setHeader("Accept-Charset", resultFormat);
            request.setHeader("Content-Type", format);// 设置编码

            if(null != headerMap && !headerMap.isEmpty()){
                for(String key : headerMap.keySet()){
                    request.addHeader(key , headerMap.get(key));
                }
            }

            StringEntity entity = new StringEntity(content , resultFormat);
            entity.setContentType(format);
            entity.setContentEncoding(resultFormat);
            request.setEntity(entity);

            //设置请求超时时间和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(timeout)
                    .setConnectionRequestTimeout(timeout)
                    .build();
            request.setConfig(requestConfig);

            HttpContext context = new BasicHttpContext();
            response = httpClient.execute(request , context);

            if((null != response) && (null != response.getStatusLine())
                    && (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),resultFormat));
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
    public static String httpPost(String url , String content , String format , String resultFormat , Integer timeout) {
        return httpPost(url, content, format, resultFormat, 5000 , null);
    }

    /**
     * 提交Http请求 POST
     * @param url 地址
     * @param content 内容
     * @return
     */
    public static String httpPost(String url , String content , String format , String resultForamt) {
        return httpPost(url, content, format, resultForamt, 5000);
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
    public static String httpPost(String url , String content, Integer timeout) {
        return httpPost(url , content , "application/json;charset=UTF-8", "UTF-8" , timeout);
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
     * @param content 内容
     * @return
     */
    public static String httpPost(String url , String content, Map<String, String> headerMap) {
        return httpPost(url, content, "application/json;charset=UTF-8", "UTF-8", 5000 , headerMap);
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
     * @param paramMap 普通参数
     * @param fileMap 文件参数
     * @return
     */
    public static String httpPostFile(String url , Map<String, String> paramMap, Map<String, String> fileMap) {
        String result = null;
        HttpPost request = null;
        CloseableHttpResponse response = null;
        try{
            StringBuffer buffer = new StringBuffer();
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            request = new HttpPost(url);

            HttpEntity reqEntity = null;
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            //放置参数
            if(null != paramMap && !paramMap.isEmpty()){
                for(String key : paramMap.keySet()){
                    StringBody body = new StringBody(paramMap.get(key), ContentType.TEXT_PLAIN);
                    builder.addPart(key, body);
                }
            }
            //放置文件参数
            if(null != fileMap && !fileMap.isEmpty()){
                for(String key : fileMap.keySet()){
                    File file = new File(fileMap.get(key));
                    FileBody fileBody = new FileBody(file);
                    builder.addPart(key, fileBody);
                }
            }
            reqEntity = builder.build();
            request.setEntity(reqEntity);

            //设置请求超时时间和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .build();
            request.setConfig(requestConfig);

            response = httpClient.execute(request);

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
                        if(header.getValue().toLowerCase().contains("image/")){
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
    public static String httpGet(String url){
        return httpGet(url, null);
    }

    /**
     * 提交Http请求 Get
     * @param url 地址
     * @return
     */
    public static String httpGet(String url, Map<String, String> headerMap) {
        String result = null;
        HttpGet request = null;
        CloseableHttpResponse response = null;
        try{
            StringBuffer buffer = new StringBuffer();
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            request = new HttpGet(url);

            if(null != headerMap && !headerMap.isEmpty()){
                for(String key : headerMap.keySet()){
                    request.addHeader(key, headerMap.get(key));
                }
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
     * 提交Http请求 Get
     * @param url 地址
     * @return
     */
    public static boolean httpGetForImg(String url, String path) {
        boolean flag = false;
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
                boolean imgFlag = false;
                if(null != response.getAllHeaders() && response.getAllHeaders().length > 0){
                    for(Header header : response.getAllHeaders()){
                        if(header.getValue().toLowerCase().contains("image/")){
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

    public static void main(String [] args){
       String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=23_c6cKf3waBLq3y0g015gOHgTZ2QVzdpkM2KonrvVpHTYkjw4KRMvktngU5XoCELadY6D1Ayujl62QmCa6Cyy58M9LIbmQZRMUjNRX01UH30QTN0WiOLus80C66b4sk0qMI7RCrdAiwCoassysKJBfAAAZYQ&type=image";
       Map<String , String> fileMap = new HashMap<String , String>();
       fileMap.put("media", "/Users/duhao/tmp/eyou/img/product/2019/05/05/0134b6241a1f40ababe81d72b128b86b.jpg");
       String result = httpPostFile(url, null, fileMap);
       System.out.println(result);
    }

}
