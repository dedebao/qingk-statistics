package com.allook.statistics.redis.service.impl;



import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

import com.allook.statistics.redis.service.RedisService;

/**
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年5月9日
 */
@Service("redisService")
@SuppressWarnings("unchecked")
public class RedisServiceImpl implements RedisService {

	@Autowired
	RedisTemplate<String, Object> objectRedisTemplate;

	/* ****************************** 地理位置(geo) ****************************** */

	/* ****************************** 键(key) ****************************** */

	@Override
	public void del(String key) {
		objectRedisTemplate.delete(key);
	}

	@Override
	public byte[] dump(String key) {
		return objectRedisTemplate.dump(key);
	}

	@Override
	public Boolean exists(String key) {
		return objectRedisTemplate.hasKey(key);
	}

	@Override
	public Boolean expire(String key, long timeout) {
		return expire(key, timeout, TimeUnit.SECONDS);
	}

	@Override
	public Boolean expire(String key, long timeout, TimeUnit unit) {
		return objectRedisTemplate.expire(key, timeout, unit);
	}

	@Override
	public Boolean expireAt(String key, Date date) {
		return objectRedisTemplate.expireAt(key, date);
	}

	@Override
	public Set<String> keys(String pattern) {
		return objectRedisTemplate.keys(pattern);
	}

	@Override
	public Boolean move(String key, int dbIndex) {
		return objectRedisTemplate.move(key, dbIndex);
	}

	@Override
	public Boolean persist(String key) {
		return objectRedisTemplate.persist(key);
	}

	@Override
	public Long pttl(String key) {
		return ttl(key, TimeUnit.MILLISECONDS);
	}

	@Override
	public Long ttl(String key) {
		return objectRedisTemplate.getExpire(key);
	}

	@Override
	public Long ttl(String key, TimeUnit timeUnit) {
		return objectRedisTemplate.getExpire(key, timeUnit);
	}

	@Override
	public void rename(String oldKey, String newKey) {
		objectRedisTemplate.rename(oldKey, newKey);
	}

	@Override
	public Boolean renameNx(String oldKey, String newKey) {
		return objectRedisTemplate.renameIfAbsent(oldKey, newKey);
	}

	@Override
	public DataType type(String key) {
		return objectRedisTemplate.type(key);
	}

	/* ****************************** 字符串(String) ****************************** */

	@Override
	public <V> void set(String key, V value) {
		objectRedisTemplate.opsForValue().set(key, value);
	}

	@Override
	public <V> V get(String key) {
		return (V) objectRedisTemplate.opsForValue().get(key);
	}

	@Override
	public String getRange(String key, long start, long end) {
		return objectRedisTemplate.opsForValue().get(key, start, end);
	}

	@Override
	public <V> V getSet(String key, V value) {
		return (V) objectRedisTemplate.opsForValue().getAndSet(key, value);
	}

	@Override
	public Boolean getBit(String key, long offset) {
		return objectRedisTemplate.opsForValue().getBit(key, offset);
	}

	@Override
	public <V> List<V> mget(List<String> keys) {
		return (List<V>) objectRedisTemplate.opsForValue().multiGet(keys);
	}

	@Override
	public Boolean setBit(String key, long offset, boolean value) {
		return objectRedisTemplate.opsForValue().setBit(key, offset, value);
	}

	@Override
	public <V> void setEx(String key, V value, long timeout) {
		setEx(key, value, timeout, TimeUnit.SECONDS);
	}

	@Override
	public <V> void setEx(String key, V value, long timeout, TimeUnit unit) {
		objectRedisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	@Override
	public <V> Boolean setNx(String key, V value) {
		return objectRedisTemplate.opsForValue().setIfAbsent(key, value);
	}

	@Override
	public <V> void setRange(String key, V value, long offset) {
		objectRedisTemplate.opsForValue().set(key, value, offset);
	}

	@Override
	public Long strLen(String key) {
		return objectRedisTemplate.opsForValue().size(key);
	}

	@Override
	public <V> void mset(Map<String, V> map) {
		objectRedisTemplate.opsForValue().multiSet(map);
	}

	@Override
	public <V> Boolean msetNx(Map<String, V> map) {
		return objectRedisTemplate.opsForValue().multiSetIfAbsent(map);
	}

	@Override
	public <V> void psetEx(String key, V value, long timeout) {
		setEx(key, value, timeout, TimeUnit.MILLISECONDS);
	}

	@Override
	public Long incr(String key) {
		return incrBy(key, 1L);
	}

	@Override
	public Long incrBy(String key, long delta) {
		return objectRedisTemplate.opsForValue().increment(key, delta);
	}

	@Override
	public Double incrByFloat(String key, Double delta) {
		return objectRedisTemplate.opsForValue().increment(key, delta);
	}

	@Override
	public Long decr(String key) {
		return objectRedisTemplate.opsForValue().increment(key, -1);
	}

	@Override
	public Long decrBy(String key, Long delta) {
		return objectRedisTemplate.opsForValue().increment(key, (-1 * delta));
	}

	@Override
	public Double decrByFloat(String key, Double delta) {
		return objectRedisTemplate.opsForValue().increment(key, (-1.0 * delta));
	}

	@Override
	public Integer append(String key, String value) {
		return objectRedisTemplate.opsForValue().append(key, value);
	}

	/* ****************************** 哈希(Hash) ****************************** */

	@Override
	public Long hdel(String key, List<String> hashKeys) {
		return objectRedisTemplate.opsForHash().delete(key, hashKeys);
	}

	@Override
	public Boolean hexists(String key, String hashKey) {
		return objectRedisTemplate.opsForHash().hasKey(key, hashKey);
	}

	@Override
	public <HV> HV hget(String key, String hashKey) {
		return (HV) objectRedisTemplate.opsForHash().get(key, hashKey);
	}

	@Override
	public <HV> Map<String, HV> hgetAll(String key) {
		Map<Object, Object> objMap = objectRedisTemplate.opsForHash().entries(key);
		Map<String, HV> retMap = new HashMap<>(objMap.size());
		objMap.entrySet().forEach(entry -> {
			retMap.put((String) entry.getKey(), (HV) entry.getValue());
		});
		return retMap;
	}

	@Override
	public Long hincrBy(String key, String hashKey) {
		return hincrBy(key, hashKey, 1L);
	}

	@Override
	public Long hincrBy(String key, String hashKey, long delta) {
		return objectRedisTemplate.opsForHash().increment(key, hashKey, delta);
	}

	@Override
	public Double hincrByFloat(String key, String hashKey, Double delta) {
		return objectRedisTemplate.opsForHash().increment(key, hashKey, delta);
	}

	@Override
	public Set<String> hkeys(String key) {
		Set<Object> objs = objectRedisTemplate.opsForHash().keys(key);
		Set<String> vals = new HashSet<>(objs.size());// 指定初始容量大小，防止重新扩容
		objs.forEach(val -> {
			vals.add((String) val);
		});
		return vals;
	}

	@Override
	public Long hlen(String key) {
		return objectRedisTemplate.opsForHash().size(key);
	}

	@Override
	public <HV> List<HV> hmGet(String key, List<Object> hashKeys) {
		return (List<HV>) objectRedisTemplate.opsForHash().multiGet(key, hashKeys);
	}

	@Override
	public <HV> void hmSet(String key, Map<String, HV> map) {
		objectRedisTemplate.opsForHash().putAll(key, map);
	}

	@Override
	public <HV> void hset(String key, String hashKey, HV value) {
		objectRedisTemplate.opsForHash().put(key, hashKey, value);
	}

	@Override
	public <HV> Boolean hsetNx(String key, String hashKey, HV value) {
		return objectRedisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
	}

	@Override
	public <HV> List<HV> hvals(String key) {
		return (List<HV>) objectRedisTemplate.opsForHash().values(key);
	}

	/* ****************************** 列表(List) ****************************** */

	public <V> V blPop(String key, long timeout, TimeUnit unit) {
		return (V) objectRedisTemplate.opsForList().leftPop(key, timeout, unit);
	}

	@Override
	public <V> V brPop(String key, long timeout, TimeUnit unit) {
		return (V) objectRedisTemplate.opsForList().rightPop(key, timeout, unit);
	}

	@Override
	public <V> V brPoplPush(String sourceKey, String destinationKey, long timeout, TimeUnit unit) {
		return (V) objectRedisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey, timeout, unit);
	}

	@Override
	public <V> V lindex(String key, long index) {
		return (V) objectRedisTemplate.opsForList().index(key, index);
	}

	@Override
	public Long llen(String key) {
		return objectRedisTemplate.opsForList().size(key);
	}

	@Override
	public <V> V lpop(String key) {
		return (V) objectRedisTemplate.opsForList().leftPop(key);
	}

	@Override
	public <V> Long lpush(String key, V value) {
		return objectRedisTemplate.opsForList().leftPush(key, value);
	}

	@Override
	public <V> Long lpush(String key, List<V> values) {
		return objectRedisTemplate.opsForList().leftPushAll(key, values.toArray());
	}

	@Override
	public <V> Long lpushx(String key, V value) {
		return objectRedisTemplate.opsForList().leftPushIfPresent(key, value);
	}

	@Override
	public <V> List<V> lrange(String key, long start, long end) {
		return (List<V>) objectRedisTemplate.opsForList().range(key, start, end);
	}

	@Override
	public <V> Long lrem(String key, long count, V value) {
		return objectRedisTemplate.opsForList().remove(key, count, value);
	}

	@Override
	public <V> void lset(String key, long index, V value) {
		objectRedisTemplate.opsForList().set(key, index, value);
	}

	@Override
	public void ltrim(String key, long start, long end) {
		objectRedisTemplate.opsForList().trim(key, start, end);
	}

	@Override
	public <V> V rpop(String key) {
		return (V) objectRedisTemplate.opsForList().rightPop(key);
	}

	@Override
	public <V> V rpoplPush(String sourceKey, String destinationKey) {
		return (V) objectRedisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
	}

	@Override
	public <V> Long rpush(String key, V value) {
		return objectRedisTemplate.opsForList().rightPush(key, value);
	}

	@Override
	public <V> Long rpush(String key, List<V> values) {
		return objectRedisTemplate.opsForList().rightPushAll(key, values.toArray());
	}

	@Override
	public <V> Long rpushx(String key, V value) {
		return objectRedisTemplate.opsForList().rightPushIfPresent(key, value);
	}

	/* ****************************** 集合(Set) ****************************** */

	@Override
	public <V> Long sadd(String key, V value) {
		return objectRedisTemplate.opsForSet().add(key, value);
	}

	@Override
	public <V> Long sadd(String key, Collection<V> values) {
		return objectRedisTemplate.opsForSet().add(key, values.toArray());
	}

	@Override
	public Long scard(String key) {
		return objectRedisTemplate.opsForSet().size(key);
	}

	@Override
	public <V> Set<V> sdiff(String key, String otherKey) {
		return sdiff(key, Collections.singletonList(otherKey));
	}

	@Override
	public <V> Set<V> sdiff(String key, List<String> otherKeys) {
		return (Set<V>) objectRedisTemplate.opsForSet().difference(key, otherKeys);
	}

	@Override
	public Long sdiffStore(String key, String otherKey, String destKey) {
		return objectRedisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
	}

	@Override
	public Long sdiffStore(String key, List<String> otherKeys, String destKey) {
		return objectRedisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
	}

	@Override
	public <V> Set<V> sinter(String key, String otherKey) {
		return sinter(key, Collections.singletonList(otherKey));
	}

	@Override
	public <V> Set<V> sinter(String key, List<String> otherKeys) {
		return (Set<V>) objectRedisTemplate.opsForSet().intersect(key, otherKeys);
	}

	@Override
	public <V> Boolean sisMember(String key, V value) {
		return objectRedisTemplate.opsForSet().isMember(key, value);
	}

	@Override
	public <V> Set<V> smembers(String key) {
		return (Set<V>) objectRedisTemplate.opsForSet().members(key);
	}

	@Override
	public <V> Boolean smove(String key, V value, String destKey) {
		return objectRedisTemplate.opsForSet().move(key, value, destKey);
	}

	@Override
	public <V> V spop(String key) {
		return (V) objectRedisTemplate.opsForSet().pop(key);
	}

	@Override
	public <V> V srandMember(String key) {
		return (V) objectRedisTemplate.opsForSet().randomMember(key);
	}

	@Override
	public <V> List<V> srandMembers(String key, long count) {
		return (List<V>) objectRedisTemplate.opsForSet().randomMembers(key, count);
	}

	@Override
	public <V> Long srem(String key, V value) {
		return objectRedisTemplate.opsForSet().remove(key, value);
	}

	@Override
	public <V> Long srem(String key, List<V> values) {
		return objectRedisTemplate.opsForSet().remove(key, values.toArray());
	}

	@Override
	public <V> Set<V> sunion(String key, String otherKey) {
		return (Set<V>) objectRedisTemplate.opsForSet().union(key, otherKey);
	}

	@Override
	public <V> Set<V> sunion(String key, List<String> otherKeys) {
		return (Set<V>) objectRedisTemplate.opsForSet().union(key, otherKeys);
	}

	@Override
	public <V> Long sunionStore(String key, String otherKey, String destKey) {
		return objectRedisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
	}

	@Override
	public <V> Long sunionStore(String key, List<String> otherKeys, String destKey) {
		return objectRedisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
	}

	/*
	 * ****************************** 有序集合(sorted set)
	 * ******************************
	 */

	@Override
	public <V> Boolean zadd(String key, V value, double score) {
		return objectRedisTemplate.opsForZSet().add(key, value, score);
	}

	@Override
	public Long zadd(String key, Set<TypedTuple<Object>> tuples) {
		return objectRedisTemplate.opsForZSet().add(key, tuples);
	}

	@Override
	public Long zcard(String key) {
		return objectRedisTemplate.opsForZSet().zCard(key);
	}

	@Override
	public Long zcount(String key, double min, double max) {
		return objectRedisTemplate.opsForZSet().count(key, min, max);
	}
}
