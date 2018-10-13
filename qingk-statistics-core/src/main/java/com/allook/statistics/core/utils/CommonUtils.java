package com.allook.statistics.core.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.TreeMap;
import java.util.UUID;

/**
 * 常用工具类
 * 
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年5月10日
 */
public final class CommonUtils {
    private static final String HEX_STR = "0123456789abcdef";// 十六进制字符
    private static final float HASH_MAP_DEFAULT_LOAD_FACTOR = 0.75f;

    /** 不允许实例化 **/
    private CommonUtils() {
    }

    /**
     * 计算HashMap|HashSet的初始化容量，防止重新扩容(resize)
     * 
     * @param size
     * @return
     * @since v1.0.0
     */
    public static int initCapacity(int size) {
        return ((int) (size / HASH_MAP_DEFAULT_LOAD_FACTOR)) + 1;
    }

    /**
     * 生成32位UUID字符串
     * 
     * @return
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年6月11日
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 签名算法
     * <p>
     * 加密串:qingkintegrateA1B2C3_timestamp12345678qingkintegrate<br>
     * 1. qingkintegrate固定参数，添加在加密串前后<br>
     * 2. A1B1C1表示传递参数组合（所有参数除公共参数外，按照字母升序排列后拼接），参数名称A+参数值1—+参数名称B+参数值2...<br>
     * 3. _timestamp+时间戳<br>
     * 4. MD5方式加密 MD5(qingkintegrateA1B2C3_timestamp12345678qingkintegrate);<br>
     * 
     * @param timestamp
     *            时间戳
     * @param params
     *            参数
     * @return 签名串
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年6月7日
     */
    public static String sign(Long timestamp, TreeMap<String, String> params) {
        StringBuilder buf = new StringBuilder();

        buf.append("qingkintegrate");

        if (null != params) {
            params.entrySet().forEach(param -> {
                buf.append(param.getKey()).append(param.getValue());
            });
        }

        buf.append("_timestamp").append(timestamp);

        buf.append("qingkintegrate");

        return CommonUtils.md5(buf.toString());
    }

    /**
     * MD5加密
     * 
     * @param message
     *            消息
     * @return 小写十六进制的MD5加密串
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年6月7日
     */
    public static String md5(String message) {
        if (null == message) {
            return null;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(message.getBytes());
            byte[] bytes = md5.digest();

            int len = bytes.length;
            StringBuilder buf = new StringBuilder(len * 2);
            for (int i = 0; i < len; i++) {
                buf.append(HEX_STR.charAt(bytes[i] >>> 4 & 0x0F));// 高4位转换
                buf.append(HEX_STR.charAt(bytes[i] & 0x0F));// 低4位转换
            }
            return buf.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取本机IP地址
     *
     * @return
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年6月19日
     */
    public static String getIp() {
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

}
