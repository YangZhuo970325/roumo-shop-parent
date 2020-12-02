/**
 * 文件名：SnowflakeIdUtils.java
 * 描述：
 **/
package com.hand.twitter;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/3/6
 * @date 2020/3/6 15:26
 */
public class SnowflakeIdUtils {
    private static SnowflakeIdWorker idWorker;
    static {
        // 使用静态代码块初始化 SnowflakeIdWorker
        idWorker = new SnowflakeIdWorker(1, 1);
    }

    public static String nextId() {
        return idWorker.nextId() + "";
    }
}
