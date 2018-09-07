/**************************************************************************
 * Copyright (c) 2016-2017 Zhejiang TaChao Network Technology Co.,Ltd.
 * All rights reserved.
 *
 * 项目名称：浙江踏潮-基础架构
 * 版权说明：本软件属浙江踏潮网络科技有限公司所有，在未获得浙江踏潮网络科技有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.zjtachao.fish.water.common.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis通用方法
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
@Component
public class WaterRedis {

    /** 日志 **/
    private static final Logger logger = LoggerFactory.getLogger(WaterRedis.class);

    /** redis templete **/
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 查询redis缓存字符串
     * @param key
     * @return
     */
    public String queryString(String key) {
        String result = null;
        try {
            result = stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            logger.error("查询redis缓存字符串出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }


    /**
     * 获取key集合
     * @param pattern
     */
    public Set<String> getKeys(String pattern) {
        Set<String> result = null;
        try {
            result = stringRedisTemplate.keys(pattern);
        } catch (Exception e) {
            logger.error("获取key集合出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     * 删除指定Key的缓存记录
     * @param key
     */
    public void delete(String key) {
        try {
            stringRedisTemplate.delete(key);
        } catch (Exception e) {
            logger.error("删除指定Key的缓存记录出错，错误信息为："+e.getMessage(), e);
        }
    }

    /**
     * 删除key集合对应的redis缓存
     * @param keys
     */
    public void delete(List<String> keys) {
        try {
            stringRedisTemplate.delete(keys);
        } catch (Exception e) {
            logger.error("删除key集合对应的redis缓存出错，错误信息为："+e.getMessage(), e);
        }
    }


    /**
     * 判断key是否存在
     * @param key
     */
    public boolean exists(String key) {
        boolean flag = false;
        try {
            flag = stringRedisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error("判断key是否存在出错，错误信息为："+e.getMessage(), e);
        }
        return flag;
    }


    /**
     * 设置过期时间
     * @param key
     * @param minute
     */
    public void expireTime(String key , long minute) {
        try {
            stringRedisTemplate.expire(key, minute, TimeUnit.MINUTES);
        } catch (Exception e){
            logger.error("设置过期时间出错，错误信息为："+e.getMessage(), e);
        }

    }

    /**
     *
     * 获得对象过期时间
     * @param key
     * @return
     */
    public Long getExpireTimeMinutes(String key){
        return stringRedisTemplate.getExpire(key, TimeUnit.MINUTES);
    }


    /**
     * 设置过期日期
     * @param key
     * @param date
     */
    public void expireDate(String key , Date date) {
        try {
            stringRedisTemplate.expireAt(key, date);
        }catch (Exception e){
            logger.error("过期时间key出错："+e.getMessage(), e);
        }
    }


    /**
     * 清空redis库
     * @return
     */
    public String flushDB() {
        String result = "fail";
        try {
            result = stringRedisTemplate.execute(new RedisCallback<String>() {
                public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    redisConnection.flushDb();
                    return "ok";
                }
            });
        } catch (Exception e) {
            logger.error("清空redis库出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }


    /**
     * 获取redis库中的数据数量
     */
    public long dbSize() {
        long result = 0l;
        try {
            result = stringRedisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.dbSize();
                }
            });
        } catch (Exception e) {
            logger.error("获取redis库中的数据数量出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * 获得redis的key类型
     * @param key
     * @return
     */
    public DataType type(String key){
        DataType dataType = null;
        try{
            dataType = stringRedisTemplate.type(key);
        }catch (Exception e){
            logger.error("获取key的类型出错，错误信息为："+e.getMessage(), e);
        }
        return dataType;
    }

    /**
     *
     * 设置普通对象
     * @param key
     * @param value
     */
    public void set(String key , String value){
        try {
            stringRedisTemplate.opsForValue().set(key, value);
        }catch (Exception e){
            logger.error("设置数据出错，错误信息为："+e.getMessage(), e);
        }
    }

    /** 过期时间参数
     * @param key
     * @param value
     * @param expireDate
     */
    public void set(String key , String value , Date expireDate){
        try {
            Date now = new Date();
            long seconds = expireDate.getTime()/1000 - now.getTime()/1000;
            stringRedisTemplate.opsForValue().set(key , value , seconds, TimeUnit.SECONDS);
        }catch (Exception e){
            logger.error("设置数据出错，错误信息为："+e.getMessage(), e);
        }
    }


    /**
     *
     * 过期时间参数
     * @param key
     * @param value
     * @param seconds
     */
    public void set(String key , String value , long seconds){
        try {
            Date now = new Date();
            stringRedisTemplate.opsForValue().set(key , value , seconds, TimeUnit.SECONDS);
        }catch (Exception e){
            logger.error("设置数据出错，错误信息为："+e.getMessage(), e);
        }
    }




    /**
     *
     * 设置普通对象
     * @param key
     */
    public String get(String key){
        String result = null;
        try {
            result = stringRedisTemplate.opsForValue().get(key);
        }catch (Exception e){
            logger.error("获取数据出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * 原子性处理数据
     * @param key
     * @param value
     * @return
     */
    public long increment(String key , long value){
        long result = 0;
        try {
            result = stringRedisTemplate.opsForValue().increment(key , value);
        }catch (Exception e){
            logger.error("原子性处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * 获得并设置
     * @param key
     * @param value
     * @return
     */
    public long getAndSetNumber(String key , long value){
        long result = 0;
        try{
            String redisStr = stringRedisTemplate.opsForValue().getAndSet(key , String.valueOf(value));
            if(null != redisStr){
                result = Long.valueOf(redisStr);
            }
        }catch (Exception e){
            logger.error("获得并设置数据处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * 原子性处理数据
     * @param key
     * @param value
     * @return
     */
    public long increment(String key , long value , long minute){
        long result = 0;
        try{
            result = stringRedisTemplate.opsForValue().increment(key,value);
            expireTime(key , minute);
        }catch (Exception e){
            logger.error("原子性数据处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * 原子性处理数据
     * @param key
     * @param value
     * @return
     */
    public long increment(String key , long value , Date date){
        long result = 0;
        try{
            result = stringRedisTemplate.opsForValue().increment(key , value);
            Date now = new Date();
            long seconds = date.getTime()/1000 - now.getTime()/1000;
            stringRedisTemplate.expire(key , seconds, TimeUnit.SECONDS);
        }catch (Exception e){
            logger.error("原子性数据处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * set数量
     * @param key
     * @return
     */
    public long ssize(String key){
        long result = 0;
        try{
            result = stringRedisTemplate.opsForSet().size(key);
        }catch (Exception e){
            logger.error("sadd处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * set新增
     * @param key
     * @param values
     * @return
     */
    public long sadd(String key , String... values){
        long result = 0;
        try{
            result = stringRedisTemplate.opsForSet().add(key , values);
        }catch (Exception e){
            logger.error("sadd处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * set新增
     * @param key
     * @param values
     * @return
     */
    public long sdel(String key , String... values){
        long result = 0;
        try{
            result = stringRedisTemplate.opsForSet().remove(key , values);
        }catch (Exception e){
            logger.error("sdel处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }


    /**
     *
     * 获得set的成员
     * @param key
     * @return
     */
    public Set<String> smembers(String key){
        Set<String> result = null;
        try {
            result = stringRedisTemplate.opsForSet().members(key);
        }catch (Exception e){
            logger.error("smembers处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * 移动数据
     * @param sourcekey
     * @param value
     * @param targetkey
     * @return
     */
    public boolean smove(String sourcekey , String value , String targetkey){
        boolean result = false;
        try {
            result = stringRedisTemplate.opsForSet().move(sourcekey , value ,targetkey);
        }catch (Exception e){
            logger.error("smove处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }


    /**
     *
     * set交集
     * @param key
     * @param otherkeys
     * @return
     */
    public Set<String > sinter(String key , List<String> otherkeys){
        Set<String> result = null;
        try {
            result = stringRedisTemplate.opsForSet().intersect(key , otherkeys);
        }catch (Exception e){
            logger.error("sinter处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * 随机获得一个元素
     * @param key
     * @return
     */
    public String srandom(String key){
        String result = null;
        try {
            result = stringRedisTemplate.opsForSet().randomMember(key);
        } catch (Exception e){
            logger.error("srandom处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * 随机获得数量的元素
     * @param key
     * @return
     */
    public List<String> srandom(String key , Integer count){
        List<String> result = null;
        try {
            result = stringRedisTemplate.opsForSet().randomMembers(key , count);
        } catch (Exception e){
            logger.error("srandom处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }


    /**
     *
     * Hash新增
     * @param key
     * @param hashKey
     * @param value
     */
    public void hset(String key, String hashKey , String value) {
        try {
            stringRedisTemplate.opsForHash().put(key , hashKey , value);
        }catch (Exception e){
            logger.error("hset处理出错，错误信息为："+e.getMessage(), e);
        }
    }

    /**
     * 获取Hash值
     * @param key
     * @param hashKey
     * @return
     */
    public Object hget(String key, String hashKey) {
        Object obj = null;
        try {
            obj = stringRedisTemplate.opsForHash().get(key , hashKey);
        } catch (Exception e){
            logger.error("hget处理出错，错误信息为："+e.getMessage(), e);
        }
        return obj;
    }


    /**
     *
     * 获得所有广告key
     * @param key
     * @return
     */
    public Set<Object> hkeys(String key){
        Set<Object> result = null;
        try {
           result = stringRedisTemplate.opsForHash().keys(key);
        } catch (Exception e){
          logger.error("Set<Object>处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * 获得所有广告key
     * @param key
     * @return
     */
    public Map<Object , Object> hall(String key){
        Map<Object , Object> result = null;
        try {
           result = stringRedisTemplate.opsForHash().entries(key);
        } catch (Exception e){
           logger.error("Map<Object , Object>处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * 删除数据
     * @param key
     * @param hashKey
     */
    public void hdel(String key , String hashKey){
        try {
           stringRedisTemplate.opsForHash().delete(key , hashKey);
        } catch (Exception e){
           logger.error("hdel处理出错，错误信息为："+e.getMessage(), e);
        }
    }


    /**
     *
     * 通过索引获取列表中的元素
     * @param key
     */
    public Object lindex(String key, int index) {
        Object obj = null;
        try {
            obj = stringRedisTemplate.opsForList().index(key ,index);
        }   catch (Exception e){
            logger.error("lindex处理出错，错误信息为："+e.getMessage(), e);
        }
        return obj;
    }

    /**
     *
     * 获取列表中元素个数
     * @param key
     */
    public Long lsize(String key) {
        long result = 0;
        try {
            result = stringRedisTemplate.opsForList().size(key);
        }   catch (Exception e){
            logger.error("lsize处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * 移除并获取列表的第一个元素
     * @param key
     */
    public String lpop(String key) {
        String result = null;
        try {
           result = stringRedisTemplate.opsForList().leftPop(key);
        }   catch (Exception e){
           logger.error("lpop处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * 获得过期时间
     * @param key
     * @return
     */
    public Long ttl(String key){
        long result = 0;
        try {
            result = stringRedisTemplate.getExpire(key , TimeUnit.SECONDS);
        }   catch (Exception e){
            logger.error("ttl处理出错，错误信息为："+e.getMessage(), e);
        }
        return result;
    }



}
