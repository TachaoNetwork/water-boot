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

import java.io.Serializable;

/**
 * 搜索条件
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterBootBaseSo implements Serializable{

    /**  序列化  */
    private static final long serialVersionUID = 1L;

    /** 默认每页条数 **/
    public static final int DEFAULT_PAGE_SIZE = 15;

    /** 页码 **/
    public static final int DEFAULT_CURRENT_PAGE_INDEX = 1;

    /** 每页条数 **/
    protected int pageSize = DEFAULT_PAGE_SIZE;

    /** 页码 **/
    public long pageIndex = DEFAULT_CURRENT_PAGE_INDEX;

    /** 页面总数 **/
    public int pageCount = 0;

    /** 数据量 **/
    public long dataCount = 0;

    /** 开始条数 **/
    public long startNum = 0;

    /** 结束条数 **/
    public long endNum = 0;

    /**
     *@return  the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    /**
     *@return  the pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     *@return  the dataCount
     */

    public long getDataCount() {
        return dataCount;
    }

    /**
     * @param dataCount the dataCount to set
     */
    public void setDataCount(long dataCount) {
        this.dataCount = dataCount;
    }

    /**
     *@return  the startNum
     */

    public long getStartNum() {
        startNum = (getPageIndex()-1) * pageSize;
        return startNum;
    }

    /**
     * @param startNum the startNum to set
     */
    public void setStartNum(long startNum) {

        this.startNum = startNum;
    }

    /**
     *@return  the endNum
     */

    public long getEndNum() {
        endNum = getPageIndex() * pageSize;
        return endNum;
    }

    /**
     * @param endNum the endNum to set
     */
    public void setEndNum(long endNum) {
        this.endNum = endNum;
    }

    /**
     *@return  the pageIndex
     */

    public long getPageIndex() {
        if(dataCount > 0){
            long pageMaxIndex = dataCount/pageSize;
            long pageMod = dataCount%pageSize;
            pageMaxIndex = pageMod>0 ? (pageMaxIndex + 1) : pageMaxIndex;
            pageIndex = pageIndex>pageMaxIndex?pageMaxIndex:pageIndex;
        }
        if(pageIndex <1){
            pageIndex = 1;
        }
        return pageIndex;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

}
