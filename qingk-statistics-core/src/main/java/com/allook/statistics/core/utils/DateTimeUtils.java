package com.allook.statistics.core.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 日期时间工具类
 * 
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年5月22日
 */
public final class DateTimeUtils {
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_PATTERN = DEFAULT_PATTERN;
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String YYYYMMDD_PATTERN = "yyyyMMdd";
    public static final String EVERY_DAY_BEGIN_PATTERN = "yyyy-MM-dd 00:00:00";
    public static final String EVERY_DAY_END_PATTERN = "yyyy-MM-dd 23:59:59";

    // 禁止实例化
    private DateTimeUtils() {
    }

    /**
     * 获取当前时刻的时间戳
     * 
     * @return 当前时刻的时间戳
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年5月22日
     */
    public static long timestamp() {
        return new DateTime().getMillis();
    }

    /**
     * 格式化日期时间
     * 
     * @param dateTimeStr
     *            日期时间
     * @param srcPattern
     *            格式化字符串
     * @param dstPattern
     *            格式化字符串
     * @return 格式化后的字符串
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年5月22日
     */
    public static String format(String dateTimeStr, String srcPattern, String dstPattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(srcPattern);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toString(dstPattern);
    }

    /**
     * 格式化日期时间
     * 
     * @param dateTime
     *            日期时间
     * @param pattern
     *            格式化字符串
     * @return 格式化后的字符串
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年5月22日
     */
    private static String format(DateTime dateTime, String pattern) {
        return dateTime.toString(pattern);
    }

    /**
     * 获取当前时刻日期时间字符串
     * 
     * @return 当前时刻日期时间字符串（yyyy-MM-dd HH:mm:ss）
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年5月24日
     */
    public static String now() {
        return now(DATETIME_PATTERN);
    }

    /**
     * 当前时刻日期时间字符串
     * 
     * @param pattern
     *            格式化字符串
     * @return 当前时刻日期时间字符串（给定格式）
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年5月24日
     */
    public static String now(String pattern) {
        return format(new DateTime(), pattern);
    }

    /**
     * 获取昨天日期字符串（yyyyMMdd）
     * 
     * @return 格式化后的日期时间字符串
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年5月22日
     */
    public static String getDateStrOfYesterday() {
        return format(new DateTime().minusDays(1), YYYYMMDD_PATTERN);
    }

    /**
     * 获取相对于当前时刻之前或之后days天的日期时间字符串
     * 
     * @param days
     *            正数：相对于当前时刻之后的天数；负数：相对于当前时刻之前的天数
     * @param pattern
     *            格式化字符串
     * @return 格式化后的字符串
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年5月22日
     */
    public static String getDateTimeStrOfRelativeDays(final int days, final String pattern) {
        return format(new DateTime().plusDays(days), pattern);
    }

    /**
     * 获取相对于当前时刻之前或之后hours小时的日期时间字符串
     * 
     * @param hours
     *            正数：相对于当前时刻之后的小时数；负数：相对于当前时刻之前的小时数
     * @param pattern
     *            格式化字符串
     * @return 格式化后的字符串
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年5月22日
     */
    public static String getDateTimeStrOfRelativeHours(final int hours, final String pattern) {
        return format(new DateTime().plusHours(hours), pattern);
    }

    /**
     * 获取相对于当前时刻之前或之后minutes分钟的日期时间字符串
     * 
     * @param minutes
     *            正数：相对于当前时刻之后的分钟数；负数：相对于当前时刻之前的分钟数
     * @param pattern
     *            格式化字符串
     * @return 格式化后的字符串
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年5月22日
     */
    public static String getDateTimeStrOfRelativeMinutes(final int minutes, final String pattern) {
        return format(new DateTime().plusMinutes(minutes), pattern);
    }

    /**
     * 获取相对于当前时刻之前或之后seconds秒的日期时间字符串
     * 
     * @param seconds
     *            正数：相对于当前时刻之后的秒数；负数：相对于当前时刻之前的秒数
     * @param pattern
     *            格式化字符串
     * @return 格式化后的字符串
     * @since v1.0.0
     * @author zhuguoxiao<br>
     *         Created on 2018年5月22日
     */
    public static String getDateTimeStrOfRelativeSeconds(final int seconds, final String pattern) {
        return format(new DateTime().plusSeconds(seconds), pattern);
    }

}
