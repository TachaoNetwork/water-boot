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

/**
 * 雪花算法生成唯一编码 snowflake的结构如下(每部分用-分开):
 *
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 -
 * 000000000000
 *
 * 第一位为未使用，接下来的41位为毫秒级时间(41位的长度可以使用69年)，然后是5位datacenterId和5位workerId(10
 * 位的长度最多支持部署1024个节点） ，最后12位是毫秒内的计数（12位的计数顺序号支持每个节点每毫秒产生4096个ID序号）
 *
 * 一共加起来刚好64位，为一个Long型。(转换成字符串长度为18)
 *
 * snowflake生成的ID整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞（由datacenter和workerId作区分），并且效率较高。
 * 据说：snowflake每秒能够产生26万个ID。
 *
 * @author <a href="mailto:dh@zjtachao.com">duhao</a>
 * @since 2.0
 */
public class WaterSnowFlakeUtil {
    private final long twepoch = 1288834974657L;
    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private final long sequenceBits = 12L;
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits
            + datacenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public WaterSnowFlakeUtil(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format(
                    "worker Id can't be greater than %d or less than 0",
                    maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format(
                    "datacenter Id can't be greater than %d or less than 0",
                    maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format(
                            "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        WaterSnowFlakeUtil idWorker = new WaterSnowFlakeUtil(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
        }
    }

}
