package com.errorLogSystem.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.errorLogSystem.util.RedisUtil;
import redis.clients.jedis.Jedis;

@Component
public class getInfoService {
	
	
	private Jedis jedis;

	@Autowired
	private RedisUtil redisUtil;
	
	public String getInfoService(){
		
		jedis = redisUtil.getJedisConnection();
		Map<String,String> keys = jedis.hgetAll("URLERROR20181101_/rs/trade/moneytostock");
		String result = "";
		for (Map.Entry<String, String> entry : keys.entrySet()) { 
			result += entry.getValue();
		}
		return result;
	}
}
