package com.minlia.cloud.utils;

import com.google.common.collect.Maps;
import com.minlia.cloud.holder.ContextHolder;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.Map;

public class JedisUtil {

	static RedisCacheManager cacheManager = ContextHolder.getContext().getBean("redisCacheManager",RedisCacheManager.class);

	public static final String KEY_PREFIX ="system" ;//ContextHolder.getBean(AlbedoProperties.class).getJedisKeyPrefix();
	/*** 系统 缓存 */
	private static final String CACHE_SYS = "systemJedisCache";
	/*** 用户 缓存 */
	private static final String CACHE_USER = "userJedisCache";

	public static String getKey(String key) {
		return PreconditionsHelper.toAppendStr(KEY_PREFIX, key);
	}

	public static String getNormalKey(String key) {
		return PreconditionsHelper.isNotEmpty(key) ? key.replace(KEY_PREFIX, "") : key;
	}
	public static String getUserStr(String key) {
		// return MemcacheUtil.get(key);
		return PreconditionsHelper.toStrString(get(CACHE_USER, key));
	}
	public static Object getUser(String key) {
		// return MemcacheUtil.get(key);
		return get(CACHE_USER, key);
	}

	public static void putUser(String key, Object value) {
		put(CACHE_USER, key, value);
		// MemcacheUtil.set(key, value);
	}

	public static void putUser(String key, Object value, int timeToLiveSeconds) {
		put(CACHE_USER, key, value, timeToLiveSeconds);
		// MemcacheUtil.set(key, value);
	}

	/**
	 * 清空指定Key的系统缓存对象
	 *
	 * @param key
	 */
	public static void removeUser(String key) {
		remove(CACHE_USER, key);
		// MemcacheUtil.deleteConfirmExists(key);
	}

	/** 清空所有系统缓存 */
	public static void clearAllCacheUser() {
		getCache(CACHE_USER).clear();
		// MemcacheUtil.deleteConfirmExists(key);
	}


	public static Object getSys(String key) {
		// return MemcacheUtil.get(key);
		return get(CACHE_SYS, key);
	}

	public static void putSys(String key, Object value) {
		put(CACHE_SYS, key, value);
		// MemcacheUtil.set(key, value);
	}

	public static void putSys(String key, Object value, int timeToLiveSeconds) {
		put(CACHE_SYS, key, value, timeToLiveSeconds);
		// MemcacheUtil.set(key, value);
	}

	/**
	 * 清空指定Key的系统缓存对象
	 *
	 * @param key
	 */
	public static void removeSys(String key) {
		remove(CACHE_SYS, key);
		// MemcacheUtil.deleteConfirmExists(key);
	}

	/** 清空所有系统缓存 */
	public static void clearAllCacheSys() {
		getCache(CACHE_SYS).clear();
		// MemcacheUtil.deleteConfirmExists(key);
	}

	/**
	 * 获得一个Cache，没有则创建一个。
	 *
	 * @param cacheName
	 * @return
	 */
	public static Cache getCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		return cache;
	}

	public static CacheManager getCacheManager() {
		return cacheManager;
	}

	public static <T> T getJson(String cacheName, String key, Class<T> clazz) {
		ValueWrapper element = getCache(cacheName).get(getKey(key));
		String value = element == null ? null : PreconditionsHelper.toStrString(element.get());
		return Json.parseObject(value, clazz);
	}

	public static Object get(String cacheName, String key) {
		return getNormal(cacheName, getKey(key));
	}

	public static void put(String cacheName, String key, Object value) {
		putNormal(cacheName, getKey(key), value, 0);
	}

	public static void put(String cacheName, String key, Object value, long timeToLiveSeconds) {
		putNormal(cacheName, getKey(key), value, timeToLiveSeconds);
	}

	public static void remove(String cacheName, String key) {
		removeNormal(cacheName, getKey(key));
	}

	public static Object getNormal(String cacheName, String key) {
		ValueWrapper element = getCache(cacheName).get(key);
		return element == null ? null : element.get();
	}

	public static void putNormal(String cacheName, String key, Object value) {
		put(cacheName, getKey(key), value, 0);
	}
	public static void putNormal(String cacheName, String key, Object value, long timeToLiveSeconds) {
		if(timeToLiveSeconds>0){
			Map<String, Long> map = Maps.newHashMap();
			map.put(cacheName, timeToLiveSeconds);
			cacheManager.setExpires(map);
		}

		getCache(cacheName).put(key, value);
	}
	public static void removeNormal(String cacheName, String key) {
		getCache(cacheName).evict(key);
	}

	public static void removeCache(String cacheName) {
		getCache(cacheName).clear();
	}

}
