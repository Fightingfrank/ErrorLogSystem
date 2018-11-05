package com.errorLogSystem.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
//@PropertySource("classpath:/Application.properties")  
public class RedisUtil implements InitializingBean{
	
	@Value("${redis.ip}")  
	private String redis_ip;
	
	@Value("${redis.port}")
	private String redis_port;
	
	@Value("${redis.timeout}")
	private String redis_timeout;
	
	@Value("${jedis.pool.maxAction}")
	private String jedisPool_maxAction;
	
	@Value("${jedis.pool.maxIdle}")
	private String jedisPool_maxIdle;
	
	@Value("${jedis.pool.maxWait}")
	private String jedisPool_maxWait;
	
	
	@Value("${jedis.pool.testOnBorrow}")
	private boolean jedisPool_testOnBorrow;
	
	@Value("${jedis.pool.testOnReturn}")
	private boolean jedisPool_testOnReturn;
	
	private JedisPool jedisPool = null;

	@Override
	public void afterPropertiesSet() throws Exception {
		JedisPoolConfig config = new JedisPoolConfig();
		
		config.setMaxTotal(Integer.valueOf(jedisPool_maxAction));
		config.setMaxIdle(Integer.valueOf(jedisPool_maxIdle));
		config.setMaxWaitMillis(Integer.valueOf(jedisPool_maxWait));
		config.setTestOnBorrow(jedisPool_testOnBorrow);
		config.setTestOnReturn(jedisPool_testOnReturn);
		jedisPool = new JedisPool(config, redis_ip, Integer.valueOf(redis_port));
	}
	
	public Jedis getJedisConnection(){
		return jedisPool.getResource();
	}
	
	public String toString(){
		return redis_ip + "  : " + redis_port  + jedisPool_testOnBorrow;
	}
	
	

}
