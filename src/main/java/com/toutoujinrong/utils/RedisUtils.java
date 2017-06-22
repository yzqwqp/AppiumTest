package com.toutoujinrong.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;




/**
 * redis对象类
 * @author qiupeng
 *
 */
public class RedisUtils {
	
	public static void main(String[] args) {
		/**
	     * 发送验证码
	     * @param instId 商户号
	     * @param mobile 手机号码
	     * @param type 验证码类型  1:注册(加薪宝激活目前使用) 2:设置密码  3-找回密码     11:鉴权
	     */
//		String sql = "select max(a.sMobile)+1 mobile from ltfund.lt_user a where a.sMobile like '18115566%' ";
//		String mobile =  OracleUtils.select(sql, "mobile");
//		System.out.println(mobile);
		String mobile = "18115566863";
//		String mobile = "15800817653";
		System.out.println(RedisUtils.getValue(mobile, ConstantUtils.REDIS_TYPE_FINDPWD));
		System.out.println(RedisUtils.getValue(mobile, ConstantUtils.REDIS_TYPE_SETPWD));
		System.out.println(RedisUtils.getValue(mobile, ConstantUtils.REDIS_TYPE_REGISTER));
//		getValue(mobile,"NULL");
	}
	
	
	
	public static String getValue(String mobileNo, String type) {
		String key = mobileNo + ConstantUtils.REDIS_KEYSPILT + ConstantUtils.REDIS_INSTID  + ConstantUtils.REDIS_KEYSPILT + type + ConstantUtils.REDIS_KEYSPILT + "vCode";
		return RedisUtils.getJedis().get(key);
	}
	
	//RedisUtils.getJedis().del(key);
	
	//Redis服务器IP
//	private static String ADDR = "192.168.2.56";
	private static String ADDR = "192.168.10.76";
	//Redis的端口号
	private static int PORT = 6379;
	//访问密码
	private static String AUTH = "66money@2014";
	//可用连接实例的最大数目，默认值为8；
	//如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 600;
	//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 300;
	//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 1000;
	private static int TIMEOUT = 1000;
	//在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;
	private static JedisPool jedisPool = null;
	
	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxActive(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWait(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取Jedis实例
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 释放jedis资源
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}

}
