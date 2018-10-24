/**************************************************************************
 * Copyright (c) 2016-2017 Zhejiang TaChao Network Technology Co.,Ltd.
 * All rights reserved.
 *
 * 项目名称：浙江踏潮-基础架构
 * 版权说明：本软件属浙江踏潮网络科技有限公司所有，在未获得浙江踏潮网络科技有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.zjtachao.fish.water.common.base.bean;

import com.zjtachao.fish.water.common.base.context.WaterBootResultContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回值对象
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterBootResultBean<E> implements java.io.Serializable{

    /** 编码 **/
    public Integer code = WaterBootResultContext.ResultCode.VALID_NO_PASS.getCode();

    /** 消息 **/
    public String msg;

    /** 返回对象 **/
    public List<E> rst;

    /** 返回数量 **/
    public Long count = 0l;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<E> getRst() {
        return rst;
    }

    public void setRst(E rst) {
        if(null != rst){
            List<E> list = new ArrayList<E>();
            list.add(rst);
            this.rst = list;
        }
    }

    public void setRst(List<E> rst) {
        this.rst = rst;
    }
}
