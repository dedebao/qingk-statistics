package com.allook.statistics.redis.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

/**
 * Redis服务类
 * 
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年5月9日
 */
public interface RedisService {

    /* ****************************** 地理位置(geo) ****************************** */

    /* ****************************** 键(key) ****************************** */

    /**
     * 【键(Key)命令】该命令用于在 key 存在是删除 key
     * 
     * @param key
     *            键，不能为 null
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/del">Redis Documentation: DEL</a>
     */
    public void del(String key);

    /**
     * 【键(Key)命令】序列化给定 key，并返回被序列化的值
     * 
     * @param key
     *            键，不能为 null
     * @return 返回被序列化的值
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/dump">Redis Documentation: DUMP</a>
     */
    public byte[] dump(String key);

    /**
     * 【键(Key)命令】检查给定 key 是否存在
     * 
     * @param key
     *            键，不能为 null
     * @return true：存在，false：不存在
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/exists">Redis Documentation: EXISTS</a>
     */
    public Boolean exists(String key);

    /**
     * 【键(Key)命令】设置 key 的过期时间（单位：秒）
     * 
     * @param key
     *            键，不能为 null
     * @param timeout
     *            过期时间
     * @return true：成功，false：失败
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/expire">Redis Documentation: EXPIRE</a>
     */
    public Boolean expire(String key, long timeout);

    /**
     * 【键(Key)命令】设置 key 的过期时间（单位：需指定）
     * 
     * @param key
     *            键，不能为 null
     * @param timeout
     *            过期时间
     * @param unit
     *            时间单位
     * @return true：成功，false：失败
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/expire">Redis Documentation: EXPIRE</a>
     */
    public Boolean expire(String key, long timeout, TimeUnit unit);

    /**
     * 【键(Key)命令】设置 key 的在哪一时刻过期
     * 
     * @param key
     *            键，不能为 null
     * @param date
     *            日期
     * @return true：成功，false：失败
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/expireat">Redis Documentation: EXPIREAT</a>
     */
    public Boolean expireAt(String key, Date date);

    /**
     * 【键(Key)命令】查找所有符合给定模式( pattern)的 key
     * 
     * @param pattern
     *            键模式，不能为 null
     * @return true：成功，false：失败
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/keys">Redis Documentation: KEYS</a>
     */
    public Set<String> keys(String pattern);

    /**
     * 【键(Key)命令】将当前数据库的 key 移动到给定的数据库 db 当中
     * 
     * @param key
     *            键，不能为 null
     * @param dbIndex
     *            数据库
     * @return true：成功，false：失败
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/move">Redis Documentation: MOVE</a>
     */
    public Boolean move(String key, int dbIndex);

    /**
     * 【键(Key)命令】移除 key 的过期时间，key 将持久保持
     * 
     * @param key
     *            键，不能为 null
     * @return true：成功，false：失败
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/persist">Redis Documentation: PERSIST</a>
     */
    public Boolean persist(String key);

    /**
     * 【键(Key)命令】以毫秒为单位返回 key 的剩余的过期时间
     * 
     * @param key
     *            键，不能为 null
     * @return 剩余生存时间
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/pttl">Redis Documentation: PTTL</a>
     */
    public Long pttl(String key);

    /**
     * 【键(Key)命令】以秒为单位，返回给定 key 的剩余生存时间
     * 
     * @param key
     *            键，不能为 null
     * @return 剩余生存时间
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/ttl">Redis Documentation: TTL</a>
     */
    public Long ttl(String key);

    /**
     * 【键(Key)命令】以秒为单位，返回给定 key 的剩余生存时间（单位：需指定）
     * 
     * @param key
     *            键，不能为 null
     * @param unit
     *            时间单位
     * @return 剩余生存时间
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/ttl">Redis Documentation: TTL</a>
     */
    public Long ttl(String key, TimeUnit unit);

    /**
     * 【键(Key)命令】修改 key 的名称
     * 
     * @param oldKey
     *            旧键名，不能为 null
     * @param newKey
     *            新键名，不能为 null
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/rename">Redis Documentation: RENAME</a>
     */
    public void rename(String oldKey, String newKey);

    /**
     * 【键(Key)命令】仅当 newkey 不存在时，将 key 改名为 newkey
     * 
     * @param oldKey
     *            旧键名，不能为 null
     * @param newKey
     *            新键名，不能为 null
     * @return true：成功，false：失败
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/renamenx">Redis Documentation: RENAMENX</a>
     */
    public Boolean renameNx(String oldKey, String newKey);

    /**
     * 【键(Key)命令】返回 key 所储存的值的类型
     * 
     * @param key
     *            键，不能为 null
     * @return true：成功，false：失败
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/type">Redis Documentation: TYPE</a>
     */
    public DataType type(String key);

    /* ****************************** 字符串(String) ****************************** */

    /**
     * 【字符串(String)命令】设置指定 key 的值
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/set">Redis Documentation: SET</a>
     */
    public <V> void set(String key, V value);

    /**
     * 【字符串(String)命令】获取指定 key 的值
     * 
     * @param key
     *            键，不能为 null
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/get">Redis Documentation: GET</a>
     */
    public <V> V get(String key);

    /**
     * 【字符串(String)命令】获取指定 key 的值的子串（偏移量从1开始，包括起始和终止偏移量）
     * 
     * @param key
     *            键，不能为 null
     * @param start
     *            子串起始偏移量
     * @param end
     *            子串终止偏移量
     * @return 返回 key 中字符串值的子串
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/getrange">Redis Documentation: GETRANGE</a>
     */
    public String getRange(String key, long start, long end);

    /**
     * 【字符串(String)命令】将给定 key 的值设为新值，并返回旧值
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            新值
     * @return 返回 key 的旧值
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/getset">Redis Documentation: GETSET</a>
     */
    public <V> V getSet(String key, V value);

    /**
     * 【字符串(String)命令】对 key 所储存的字符串值，获取指定偏移量上的位(bit)
     *
     * @param key
     *            键，不能为 null
     * @param offset
     *            位的偏移量
     * @return 返回 key 的字符串值指定偏移量上的位(bit)
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/setbit">Redis Documentation: GETBIT</a>
     */
    public Boolean getBit(String key, long offset);

    /**
     * 【字符串(String)命令】获取所有(一个或多个)给定 key 的值
     *
     * @param keys
     *            键的数组，不能为 null
     * @return 返回 一个包含所有给定 key 的值的列表
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/mget">Redis Documentation: MGET</a>
     */
    public <V> List<V> mget(List<String> keys);

    /**
     * 【字符串(String)命令】对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)
     *
     * @param key
     *            键，不能为 null
     * @param offset
     *            位的偏移量
     * @param value
     *            位的新值
     * @return 返回 key 的字符串值指定偏移量原来储存的位
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/setbit">Redis Documentation: SETBIT</a>
     */
    public Boolean setBit(String key, long offset, boolean value);

    /**
     * 【字符串(String)命令】将值 value 关联到 key ，并将 key 的过期时间设为 timeout（单位：秒）
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @param timeout
     *            过期时间
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/setex">Redis Documentation: SETEX</a>
     */
    public <V> void setEx(String key, V value, long timeout);

    /**
     * 【字符串(String)命令】将值 value 关联到 key ，并将 key 的过期时间设为 timeout（单位：需指定）
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @param timeout
     *            过期时间
     * @param unit
     *            时间单位
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/setex">Redis Documentation: SETEX</a>
     */
    public <V> void setEx(String key, V value, long timeout, TimeUnit unit);

    /**
     * 【字符串(String)命令】只有在 key 不存在时设置 key 的值
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @return true：成功，false：失败
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/setnx">Redis Documentation: SETNX</a>
     */
    public <V> Boolean setNx(String key, V value);

    /**
     * 【字符串(String)命令】只有在 key 不存在时设置 key 的值
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @param offset
     *            位的偏移量
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/setrange">Redis Documentation: SETRANGE</a>
     */
    public <V> void setRange(String key, V value, long offset);

    /**
     * 【字符串(String)命令】返回 key 所储存的字符串值的长度
     * 
     * @param key
     *            键，不能为 null
     * @return 返回 key 所储存的字符串值的长度
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/strlen">Redis Documentation: STRLEN</a>
     */
    public Long strLen(String key);

    /**
     * 【字符串(String)命令】同时设置一个或多个 key-value 对
     * 
     * @param map
     *            key-value 对，不能为 null
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/mset">Redis Documentation: MSET</a>
     */
    public <V> void mset(Map<String, V> map);

    /**
     * 【字符串(String)命令】同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在
     * 
     * @param map
     *            key-value 对，不能为 null
     * @return 当所有 key 都成功设置，返回 1。 如果所有给定 key 都设置失败(至少有一个 key 已经存在)，那么返回 0
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/msetnx">Redis Documentation: MSETNX</a>
     */
    public <V> Boolean msetNx(Map<String, V> map);

    /**
     * 【字符串(String)命令】这个命令和 SETEX 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 SETEX 命令那样，以秒为单位
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @param timeout
     *            超时时间
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/psetex">Redis Documentation: PSETEX</a>
     */
    public <V> void psetEx(String key, V value, long timeout);

    /**
     * 【字符串(String)命令】将 key 所储存的值增一
     * 
     * @param key
     *            键，不能为 null
     * @return 加上给定的增量值之后 key 的值。若 key 不存在，则直接设置 key 所存储的值为给定的增量值。
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/incr">Redis Documentation: INCR</a>
     */
    public Long incr(String key);

    /**
     * 【字符串(String)命令】将 key 所储存的值加上指定的增量值
     * 
     * @param key
     *            键，不能为 null
     * @param delta
     *            增量值
     * @return 加上指定的增量值之后 key 的值。若 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行加法操作
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/incrby">Redis Documentation: INCRBY</a>
     */
    public Long incrBy(String key, long delta);

    /**
     * 【字符串(String)命令】将 key 所储存的值加上给定的浮点增量值
     * 
     * @param key
     *            键，不能为 null
     * @param delta
     *            浮点增量值
     * @return 加上指定的浮点增量值之后 key 的值。若 key 不存在，则先将 key 的值设为 0，再执行加法操作
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/incrbyfloat">Redis Documentation: INCRBYFLOAT</a>
     */
    public Double incrByFloat(String key, Double delta);

    /**
     * 【字符串(String)命令】将 key 中储存的数字值减一
     * 
     * @param key
     *            键，不能为 null
     * @return 减去指定值之后 key 的值。若 key 不存在，则 key 的值会先被初始化为 0，然后再执行减法操作
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/decr">Redis Documentation: DECR</a>
     */
    public Long decr(String key);

    /**
     * 【字符串(String)命令】将 key 中储存的数字值减去指定值
     * 
     * @param key
     *            键，不能为 null
     * @param delta
     *            减量值
     * @return 减去指定值之后 key 的值。若 key 不存在，则 key 的值会先被初始化为 0，然后再执行减法操作
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/decrby">Redis Documentation: DECRBY</a>
     */
    public Long decrBy(String key, Long delta);

    /**
     * 【字符串(String)命令】将 key 中储存的数字值减去指定浮点值
     * 
     * @param key
     *            键，不能为 null
     * @param delta
     *            浮点减量值
     * @return 减去指定浮点值之后 key 的值。若 key 不存在，则 key 的值会先被初始化为 0，然后再执行减法操作
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/decrbyfloat">Redis Documentation: DECRBYFLOAT</a>
     */
    public Double decrByFloat(String key, Double delta);

    /**
     * 【字符串(String)命令】如果 key 已经存在并且是一个字符串， 将 value 追加到 key 原来的值的末尾
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @return 追加指定值之后， key 中字符串的长度
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/append">Redis Documentation: APPEND</a>
     */
    public Integer append(String key, String value);

    /* ****************************** 哈希(Hash) ****************************** */

    /**
     * 【哈希(Hash)命令】删除 key 存储的哈希表中一个或多个字段
     * 
     * @param key
     *            键，不能为 null
     * @param hashKeys
     *            哈希表字段，不能为 null
     * @return 被成功删除字段的数量，不包括被忽略的字段
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hdel">Redis Documentation: HDEL</a>
     */
    public Long hdel(String key, List<String> hashKeys);

    /**
     * 【哈希(Hash)命令】查看 key 存储的哈希表中，指定的字段 hashKey 是否存在
     * 
     * @param key
     *            键，不能为 null
     * @param hashKey
     *            哈希表字段，不能为 null
     * @return 被成功删除字段的数量，不包括被忽略的字段
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hdel">Redis Documentation: HDEL</a>
     */
    public Boolean hexists(String key, String hashKey);

    /**
     * 【哈希(Hash)命令】获取 key 存储的存储在哈希表中指定字段 hashKey 的值
     * 
     * @param key
     *            键，不能为 null
     * @param hashKey
     *            哈希表字段，不能为 null
     * @return 返回给定字段的值。如果给定的字段或 key 不存在时，返回 nil
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hget">Redis Documentation: HGET</a>
     */
    public <HV> HV hget(String key, String hashKey);

    /**
     * 【哈希(Hash)命令】获取 key 存储的哈希表中的所有字段和值
     * 
     * @param key
     *            键，不能为 null
     * @return 键-值对集合
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hgetall">Redis Documentation: HGETALL</a>
     */
    public <HV> Map<String, HV> hgetAll(String key);

    /**
     * 【哈希(Hash)命令】为 key 存储的哈希表中的指定字段 hashKey 存储的整数值加一
     * 
     * @param key
     *            键，不能为 null
     * @param hashKey
     *            哈希表字段，不能为 null
     * @return 返回操作后字段 hashKey 存储的整数值
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hincrby">Redis Documentation: HINCRBY</a>
     */
    public Long hincrBy(String key, String hashKey);

    /**
     * 【哈希(Hash)命令】为 key 存储的哈希表中的指定字段 hashKey 存储的整数值加上增量
     * 
     * @param key
     *            键，不能为 null
     * @param hashKey
     *            哈希表字段，不能为 null
     * @param delta
     *            增量
     * @return 返回操作后字段 hashKey 存储的整数值
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hincrby">Redis Documentation: HINCRBY</a>
     */
    public Long hincrBy(String key, String hashKey, long delta);

    /**
     * 【哈希(Hash)命令】为 key 存储的哈希表中的指定字段 hashKey 存储的浮点值加上增量
     * 
     * @param key
     *            键，不能为 null
     * @param hashKey
     *            哈希表字段，不能为 null
     * @param delta
     *            增量
     * @return 返回操作后字段 hashKey 存储的浮点值
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hincrbyfloat">Redis Documentation: HINCRBYFLOAT</a>
     */
    public Double hincrByFloat(String key, String hashKey, Double delta);

    /**
     * 【哈希(Hash)命令】获取 key 存储的哈希表中的所有字段
     * 
     * @param key
     *            键，不能为 null
     * @return 包含哈希表中所有字段的列表。 当 key 不存在时，返回一个空列表
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hkeys">Redis Documentation: HKEYS</a>
     */
    public Set<String> hkeys(String key);

    /**
     * 【哈希(Hash)命令】获取 key 存储的哈希表中的字段的数量
     * 
     * @param key
     *            键，不能为 null
     * @return 哈希表中的字段的数量
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hlen">Redis Documentation: HLEN</a>
     */
    public Long hlen(String key);

    /**
     * 【哈希(Hash)命令】获取 key 存储的哈希表中所有给定字段的值
     * 
     * @param key
     *            键，不能为 null
     * @param hashKeys
     *            哈希表字段，不能为 null
     * @return 哈希表中的字段的数量
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hmget">Redis Documentation: HMGET</a>
     */
    public <HV> List<HV> hmGet(String key, List<Object> hashKeys);

    /**
     * 【哈希(Hash)命令】同时将多个 field-value (域-值)对设置到 key 存储的哈希表中
     * 
     * @param key
     *            键，不能为 null
     * @param map
     *            键-值对
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hmset">Redis Documentation: HMSET</a>
     */
    public <HV> void hmSet(String key, Map<String, HV> map);

    /**
     * 【哈希(Hash)命令】将 key 存储的哈希表中的字段 hashKey 的值设为 value
     * 
     * @param key
     *            键，不能为 null
     * @param hashKey
     *            哈希表中的字段
     * @param value
     *            值
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hset">Redis Documentation: HSET</a>
     */
    public <HV> void hset(String key, String hashKey, HV value);

    /**
     * 【哈希(Hash)命令】将 key 存储的哈希表中的字段 hashKey (只有该字段不存在时)的值设为 value
     * 
     * @param key
     *            键，不能为 null
     * @param hashKey
     *            哈希表中的字段
     * @param value
     *            值
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hsetnx">Redis Documentation: HSETNX</a>
     */
    public <HV> Boolean hsetNx(String key, String hashKey, HV value);

    /**
     * 【哈希(Hash)命令】将 key 存储的哈希表中所有的值
     * 
     * @param key
     *            键，不能为 null
     * @return 一个包含哈希表中所有值的列表
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/hvals">Redis Documentation: HVALS</a>
     */
    public <HV> List<HV> hvals(String key);

    /* ****************************** 列表(List) ****************************** */

    /**
     * 【列表(List)命令】移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * 
     * @param key
     *            键，不能为 null
     * @param timeout
     *            超时时间
     * @param unit
     *            时间单位
     * @return 如果列表为空，返回一个 nil 。 否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/blpop">Redis Documentation: BLPOP</a>
     */
    public <V> V blPop(String key, long timeout, TimeUnit unit);

    /**
     * 【列表(List)命令】移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * 
     * @param key
     *            键，不能为 null
     * @param timeout
     *            超时时间
     * @param unit
     *            时间单位
     * @return 假如在指定时间内没有任何元素被弹出，则返回一个 nil 和等待时长。 反之，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key，第二个元素是被弹出元素的值
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/brpop">Redis Documentation: BRPOP</a>
     */
    public <V> V brPop(String key, long timeout, TimeUnit unit);

    /**
     * 【列表(List)命令】从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * 
     * @param sourceKey
     *            源键，不能为 null
     * @param destinationKey
     *            目的键，不能为 null
     * @param timeout
     *            超时时间
     * @param unit
     *            时间单位
     * @return 假如在指定时间内没有任何元素被弹出，则返回一个 nil 和等待时长。 反之，返回一个含有两个元素的列表，第一个元素是被弹出元素的值，第二个元素是等待时长
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/brpoplpush">Redis Documentation: BRPOPLPUSH</a>
     */
    public <V> V brPoplPush(String sourceKey, String destinationKey, long timeout, TimeUnit unit);

    /**
     * 【列表(List)命令】通过索引获取列表中的元素
     * 
     * @param key
     *            键，不能为 null
     * @param index
     *            索引
     * @return 列表中下标为指定索引值的元素。 如果指定索引值不在列表的区间范围内，返回 nil
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/lindex">Redis Documentation: LINDEX</a>
     */
    public <V> V lindex(String key, long index);

    /**
     * 【列表(List)命令】获取列表长度
     * 
     * @param key
     *            键，不能为 null
     * @return 列表的长度
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/llen">Redis Documentation: LLEN</a>
     */
    public Long llen(String key);

    /**
     * 【列表(List)命令】移出并获取列表的第一个元素
     * 
     * @param key
     *            键，不能为 null
     * @return 列表的第一个元素。 当列表 key 不存在时，返回 nil
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/lpop">Redis Documentation: LPOP</a>
     */
    public <V> V lpop(String key);

    /**
     * 【列表(List)命令】将一个值插入到列表头部
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @return 执行命令后列表的长度
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/lpush">Redis Documentation: LPUSH</a>
     */
    public <V> Long lpush(String key, V value);

    /**
     * 【列表(List)命令】将多个值插入到列表头部
     * 
     * @param key
     *            键，不能为 null
     * @param values
     *            值
     * @return 执行命令后列表的长度
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/lpush">Redis Documentation: LPUSH</a>
     */
    public <V> Long lpush(String key, List<V> values);

    /**
     * 【列表(List)命令】将一个或多个值插入到已存在的列表头部
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @return 执行命令后列表的长度
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/lpushx">Redis Documentation: LPUSHX</a>
     */
    public <V> Long lpushx(String key, V value);

    /**
     * 【列表(List)命令】获取列表指定范围内的元素
     * <p>
     * 区间以偏移量 START 和 END 指定。 其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * 
     * @param key
     *            键，不能为 null
     * @param start
     *            起始索引
     * @param end
     *            终止索引
     * @return 一个列表，包含指定区间内的元素
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/lrange">Redis Documentation: LRANGE</a>
     */
    public <V> List<V> lrange(String key, long start, long end);

    /**
     * 【列表(List)命令】根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素
     * 
     * @param key
     *            键，不能为 null
     * @param count
     *            count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT;<br>
     *            count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值;<br>
     *            count = 0 : 移除表中所有与 VALUE 相等的值;<br>
     * @param value
     *            值
     * @return 被移除元素的数量。 列表不存在时返回 0
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/lrem">Redis Documentation: LREM</a>
     */
    public <V> Long lrem(String key, long count, V value);

    /**
     * 【列表(List)命令】通过索引设置列表元素的值
     * 
     * @param key
     *            键，不能为 null
     * @param index
     *            索引
     * @param value
     *            值
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/lset">Redis Documentation: LSET</a>
     */
    public <V> void lset(String key, long index, V value);

    /**
     * 【列表(List)命令】对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除
     * 
     * @param key
     *            键，不能为 null
     * @param start
     *            起始索引
     * @param end
     *            终止索引
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/ltrim">Redis Documentation: LTRIM</a>
     */
    public void ltrim(String key, long start, long end);

    /**
     * 【列表(List)命令】移除并获取列表最后一个元素
     * 
     * @param key
     *            键，不能为 null
     * @return 列表的最后一个元素。 当列表不存在时，返回 nil
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/rpop">Redis Documentation: RPOP</a>
     */
    public <V> V rpop(String key);

    /**
     * 【列表(List)命令】移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     * 
     * @param sourceKey
     *            源键，不能为 null
     * @param destinationKey
     *            目标键，不能为 null
     * @return 被弹出的元素
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/rpop">Redis Documentation: RPOP</a>
     */
    public <V> V rpoplPush(String sourceKey, String destinationKey);

    /**
     * 【列表(List)命令】在列表中添加一个值
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @return 执行操作后列表的长度
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/rpush">Redis Documentation: RPUSH</a>
     */
    public <V> Long rpush(String key, V value);

    /**
     * 【列表(List)命令】在列表中添加多个值
     * 
     * @param key
     *            键，不能为 null
     * @param values
     *            值
     * @return 执行操作后列表的长度
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/rpush">Redis Documentation: RPUSH</a>
     */
    public <V> Long rpush(String key, List<V> values);

    /**
     * 【列表(List)命令】将一个或多个值插入到已存在的列表尾部(最右边)。如果列表不存在，操作无效
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @return 执行操作后列表的长度
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/rpushx">Redis Documentation: RPUSHX</a>
     */
    public <V> Long rpushx(String key, V value);

    /* ****************************** 集合(Set) ****************************** */

    /**
     * 【集合(Set)命令】向集合添加一个成员
     * <p>
     * 已经存在于集合中的成员元素将被忽略，假如 key 存储的集合不存在，则创建一个只包含添加的元素作成员的集合。当集合 key 存储的不是集合类型时，返回一个错误。
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @return 被添加到集合中的新元素的数量，不包括被忽略的元素
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/sadd">Redis Documentation: SADD</a>
     */
    public <V> Long sadd(String key, V value);

    /**
     * 【集合(Set)命令】向集合添加一个或多个成员
     * <p>
     * 已经存在于集合中的成员元素将被忽略，假如 key 存储的集合不存在，则创建一个只包含添加的元素作成员的集合。当集合 key 存储的不是集合类型时，返回一个错误。
     * 
     * @param key
     *            键，不能为 null
     * @param values
     *            值
     * @return 被添加到集合中的新元素的数量，不包括被忽略的元素
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/sadd">Redis Documentation: SADD</a>
     */
    public <V> Long sadd(String key, Collection<V> values);

    /**
     * 【集合(Set)命令】获取集合的成员数
     * <p>
     * 当集合 key 不存在时，返回 0。
     * 
     * @param key
     *            键，不能为 null
     * @return 集合的数量
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/scard">Redis Documentation: SCARD</a>
     */
    public Long scard(String key);

    /**
     * 【集合(Set)命令】返回给定所有集合的差集
     * <p>
     * 不存在的集合 key 将视为空集。
     * 
     * @param key
     *            键，不能为 null
     * @param otherKey
     *            其他键
     * @return 包含差集成员的列表
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/sdiff">Redis Documentation: SDIFF</a>
     */
    public <V> Set<V> sdiff(String key, String otherKey);

    /**
     * 【集合(Set)命令】返回给定所有集合的差集
     * <p>
     * 不存在的集合 key 将视为空集。
     * 
     * @param key
     *            键，不能为 null
     * @param otherKeys
     *            其他键
     * @return 包含差集成员的列表
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/sdiff">Redis Documentation: SDIFF</a>
     */
    public <V> Set<V> sdiff(String key, List<String> otherKeys);

    /**
     * 【集合(Set)命令】将给定集合之间的差集存储在指定的集合中
     * <p>
     * 如果指定的集合 key 已存在，则会被覆盖。
     * 
     * @param key
     *            键，不能为 null
     * @param otherKey
     *            其他键
     * @param destKey
     *            目标键
     * @return 结果集中的元素数量
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/sdiffstore">Redis Documentation: SDIFFSTORE</a>
     */
    public Long sdiffStore(String key, String otherKey, String destKey);

    /**
     * 【集合(Set)命令】将给定集合之间的差集存储在指定的集合中
     * <p>
     * 如果指定的集合 key 已存在，则会被覆盖。
     * 
     * @param key
     *            键，不能为 null
     * @param otherKeys
     *            其他键
     * @param destKey
     *            目标键
     * @return 结果集中的元素数量
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/sdiffstore">Redis Documentation: SDIFFSTORE</a>
     */
    public Long sdiffStore(String key, List<String> otherKeys, String destKey);

    /**
     * 【集合(Set)命令】返回给定所有集合的交集
     * <p>
     * 不存在的集合 key 被视为空集。 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)。
     * 
     * @param key
     *            键，不能为 null
     * @param otherKey
     *            其他键
     * @return 交集成员的列表
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/sinter">Redis Documentation: SINTER</a>
     */
    public <V> Set<V> sinter(String key, String otherKey);

    /**
     * 【集合(Set)命令】返回给定所有集合的交集
     * <p>
     * 不存在的集合 key 被视为空集。 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)。
     * 
     * @param key
     *            键，不能为 null
     * @param otherKeys
     *            其他键
     * @return 交集成员的列表
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/sinter">Redis Documentation: SINTER</a>
     */
    public <V> Set<V> sinter(String key, List<String> otherKeys);

    /**
     * 【集合(Set)命令】判断 value 元素是否是 key 存储的集合的成员
     * <p>
     * 不存在的集合 key 被视为空集。 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)。
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @return 交集成员的列表
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/sismember">Redis Documentation: SISMEMBER</a>
     */
    public <V> Boolean sisMember(String key, V value);

    /**
     * 【集合(Set)命令】返回集合中的所有成员
     * <p>
     * key 存储的集合不存时被视为空集合。
     * 
     * @param key
     *            键，不能为 null
     * @return 集合中的所有成员
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/smembers">Redis Documentation: SMEMBERS</a>
     */
    public <V> Set<V> smembers(String key);

    /**
     * 【集合(Set)命令】将 member 元素从 source 集合移动到 destination 集合
     * <p>
     * SMOVE 是原子性操作。<br>
     * 如果 source 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操作，仅返回 0 。否则， member 元素从 source 集合中被移除，并添加到 destination 集合中去。<br>
     * 当 destination 集合已经包含 member 元素时， SMOVE 命令只是简单地将 source 集合中的 member 元素删除。<br>
     * 当 source 或 destination 不是集合类型时，返回一个错误。<br>
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @param destKey
     *            目标键
     * @return 如果成员元素被成功移除，返回 true。 如果成员元素不是 source 集合的成员，并且没有任何操作对 destination 集合执行，那么返回 false。
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/smove">Redis Documentation: SMOVE</a>
     */
    public <V> Boolean smove(String key, V value, String destKey);

    /**
     * 【集合(Set)命令】移除并返回集合中的一个随机元素
     * <p>
     * 当集合不存在或是空集时，返回 nil。
     * 
     * @param key
     *            键，不能为 null
     * @return 被移除的随机元素
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/spop">Redis Documentation: SPOP</a>
     */
    public <V> V spop(String key);

    /**
     * 【集合(Set)命令】返回集合中的一个随机元素
     * <p>
     * 如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。如果 count 大于等于集合基数，那么返回整个集合。<br>
     * 如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值。<br>
     * 该操作和 SPOP 相似，但 SPOP 将随机元素从集合中移除并返回，而 Srandmember 则仅仅返回随机元素，而不对集合进行任何改动。<br>
     * 
     * @param key
     *            键，不能为 null
     * @return 返回一个元素；如果集合为空，返回 nil
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/srandmembers">Redis Documentation: SRANDMEMBERS</a>
     */
    public <V> V srandMember(String key);

    /**
     * 【集合(Set)命令】返回集合中多个随机数
     * <p>
     * 如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。如果 count 大于等于集合基数，那么返回整个集合。<br>
     * 如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值。<br>
     * 该操作和 SPOP 相似，但 SPOP 将随机元素从集合中移除并返回，而 Srandmember 则仅仅返回随机元素，而不对集合进行任何改动。<br>
     * 
     * @param key
     *            键，不能为 null
     * @param count
     *            数量
     * @return 返回一个元素列表；如果集合为空，返回 nil
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/srandmembers">Redis Documentation: SRANDMEMBERS</a>
     */
    public <V> List<V> srandMembers(String key, long count);

    /**
     * 【集合(Set)命令】移除集合中一个元素
     * <p>
     * 不存在的成员元素会被忽略<br>
     * 当 key 不是集合类型，返回一个错误<br>
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @return 被成功移除的元素的数量，不包括被忽略的元素
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/srandmembers">Redis Documentation: SRANDMEMBERS</a>
     */
    public <V> Long srem(String key, V value);

    /**
     * 【集合(Set)命令】移除集合中多个元素
     * <p>
     * 不存在的成员元素会被忽略<br>
     * 当 key 不是集合类型，返回一个错误<br>
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @return 被成功移除的元素的数量，不包括被忽略的元素
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/srem">Redis Documentation: SREM</a>
     */
    public <V> Long srem(String key, List<V> values);

    /**
     * 【集合(Set)命令】返回所有给定集合的并集
     * <p>
     * 不存在的集合 key 被视为空集<br>
     * 
     * @param key
     *            键，不能为 null
     * @param otherKey
     *            其他键
     * @return 并集成员的列表
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/sunion">Redis Documentation: SUNION</a>
     */
    public <V> Set<V> sunion(String key, String otherKey);

    /**
     * 【集合(Set)命令】返回所有给定集合的并集
     * <p>
     * 不存在的集合 key 被视为空集<br>
     * 
     * @param key
     *            键，不能为 null
     * @param otherKey
     *            其他键
     * @return 并集成员的列表
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/sunion">Redis Documentation: SUNION</a>
     */
    public <V> Set<V> sunion(String key, List<String> otherKeys);

    /**
     * 【集合(Set)命令】所有给定集合的并集存储在 destKey 集合中
     * <p>
     * 不存在的集合 key 被视为空集<br>
     * 
     * @param key
     *            键，不能为 null
     * @param otherKey
     *            其他键
     * @param destKey
     *            目标键
     * @return 结果集中的元素数量
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/sunionstore">Redis Documentation: SUNIONSTORE</a>
     */
    public <V> Long sunionStore(String key, String otherKeys, String destKey);

    /**
     * 【集合(Set)命令】所有给定集合的并集存储在 destKey 集合中
     * <p>
     * 不存在的集合 key 被视为空集<br>
     * 
     * @param key
     *            键，不能为 null
     * @param otherKeys
     *            其他键
     * @param destKey
     *            目标键
     * @return 结果集中的元素数量
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/sunionstore">Redis Documentation: SUNIONSTORE</a>
     */
    public <V> Long sunionStore(String key, List<String> otherKeys, String destKey);

    /* ****************************** 有序集合(sorted set) ****************************** */

    /**
     * 【有序集合(Sorted Set)命令】向有序集合添加一个成员，或者更新已存在成员的分数
     * <p>
     * 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上。<br>
     * 分数值可以是整数值或双精度浮点数。<br>
     * 如果有序集合 key 不存在，则创建一个空的有序集并执行 ZADD 操作。<br>
     * 当 key 存在但不是有序集类型时，返回一个错误。<br>
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @param score
     *            分数
     * @return true：成功，false：失败
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/zadd">Redis Documentation: ZADD</a>
     */
    public <V> Boolean zadd(String key, V value, double score);

    /**
     * 【有序集合(Sorted Set)命令】向有序集合添加多个成员，或者更新已存在成员的分数
     * <p>
     * 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上。<br>
     * 分数值可以是整数值或双精度浮点数。<br>
     * 如果有序集合 key 不存在，则创建一个空的有序集并执行 ZADD 操作。<br>
     * 当 key 存在但不是有序集类型时，返回一个错误。<br>
     * 
     * @param key
     *            键，不能为 null
     * @param value
     *            值
     * @param score
     *            分数
     * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/zadd">Redis Documentation: ZADD</a>
     */
    public Long zadd(String key, Set<TypedTuple<Object>> tuples);

    /**
     * 【有序集合(Sorted Set)命令】获取有序集合的成员数
     * 
     * @param key
     *            键，不能为 null
     * @return 当 key 存在且是有序集类型时，返回有序集的基数。 当 key 不存在时，返回 0
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/zcard">Redis Documentation: ZCARD</a>
     */
    public Long zcard(String key);

    /**
     * 【有序集合(Sorted Set)命令】计算在有序集合中指定区间分数的成员数
     * 
     * @param key
     *            键，不能为 null
     * @param min
     *            最低分
     * @param max
     *            最高分
     * @return 分数值在 min 和 max 之间的成员的数量
     * @since v1.0.0
     * @see <a href="http://redis.io/commands/zcount">Redis Documentation: ZCOUNT</a>
     */
    public Long zcount(String key, double min, double max);

    /* ****************************** HyperLogLog ****************************** */

    /* ****************************** 发布订阅 ****************************** */

    /* ****************************** 事务 ****************************** */
}
