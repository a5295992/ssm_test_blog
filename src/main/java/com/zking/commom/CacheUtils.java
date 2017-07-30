package com.zking.commom;

import java.util.Map;
import java.util.Map.Entry;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

/**
 * 缓存 工具类
 * @author Along
 * @Date 2017-7-30
 */
public class CacheUtils {
	private static Logger log = Logger.getLogger(CacheUtils.class);
	//
	public static CacheManager cacheManager;
	//从静态变量中 获取  springContext 实例 来获取 指定的bean
	
	public CacheUtils() {
	}
	public CacheUtils(CacheManager cacheManager) {
		CacheUtils.cacheManager = cacheManager;
	}
	
	private static void InstanceCacheManager(){
		if(cacheManager ==null){
			cacheManager = ContextHodler.getBean(CacheManager.class);
		}
	}
	
	public static void  getCache(){
	}
	/**
	 * 加入缓存
	 * @param cacheName
	 * @param typeAlis
	 * @param cache
	 */
	public static void putOnCache(String cacheName,String typeAlis, Object cache){
		InstanceCacheManager();
		if(!cacheManager.cacheExists(cacheName)){
			cacheManager.addCache(cacheName);
		}
		if(!com.mysql.jdbc.StringUtils.isNullOrEmpty(typeAlis)){
			Element element =new Element(typeAlis,cache) ;
			Cache cache1 = cacheManager.getCache(cacheName);
			if(cache1!=null){
				cache1.put(element);
			}
		}else {
			log.error(typeAlis +" 别名不能为空");
		}
	}
	/**
	 * 一次添加多个 缓存实例
	 * @param cacheName
	 * @param list
	 */
	public static void putAllOnCache(String cacheName,Map<String,Object> list){
		for (Entry<String,Object> ma : list.entrySet()) {
			putOnCache(cacheName,ma.getKey(),ma.getValue());
		}
	}
	
	/**
	 * 根据 缓存名 获取 缓存信息
	 * @param cacheName
	 * @param typelis
	 * @return
	 */
	public static Object getCacheByName(String cacheName,String typelis){
		InstanceCacheManager();
		
		Cache cache = cacheManager.getCache(cacheName);
		
		if(cache==null){
			return null;
		}else {
			return cache.get(typelis);
		}
	}
	/**
	 * 移除制定key 的缓存
	 * @param cacheName
	 * @param typelis
	 */
	public static void removeCache(String cacheName,String typelis){
		InstanceCacheManager();
		
		Cache cache = cacheManager.getCache(cacheName);
		
		if(cache==null){
			
		}else {
			cache.remove(typelis);
		}
	}
	/**
	 * 移除该 缓存 对象
	 * @param cacheName
	 */
	public static void removeCache(String cacheName){
		InstanceCacheManager();
		if(!cacheManager.cacheExists(cacheName)){
			cacheManager.removeCache(cacheName);
		}
	}
	
	/**
	 * 移除该 所有 对象
	 * @param cacheName
	 */
	public static void removeCache(){
		InstanceCacheManager();
		
		cacheManager.removalAll();
		
	}
}
