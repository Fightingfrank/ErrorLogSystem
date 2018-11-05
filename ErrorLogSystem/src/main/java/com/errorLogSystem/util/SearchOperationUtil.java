package com.errorLogSystem.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

import com.errorLogSystem.model.ErrorObject;

@Component
public class SearchOperationUtil {
	
	private static Logger logger = LoggerFactory.getLogger(SearchOperationUtil.class);
	
	@Autowired
	private RedisUtil redisUtil;
	
	public List<ErrorObject> getTop10ErrorList(){
		List<ErrorObject> lists = new ArrayList<ErrorObject>();
		lists = getAllError();
		lists = sortList(lists,0);
		if(lists.size() != 0){
			if(lists.size() >= 10){
				return lists.subList(0, 10);
			}else{
				logger.info("error不足10条 ： size = " + lists.size());
				return lists;
			}
		}else{
			logger.error("error记录为空");
		}
		return null;
		
	}
	
	public ErrorObject getErrorByName(String name){
		
		return null;
	}
	
	public List<ErrorObject> getAllError(){
		List<ErrorObject> errorList = new ArrayList<ErrorObject>();
		Jedis jedis = redisUtil.getJedisConnection();
		Set<String> sets = jedis.keys("URLERROR*");
		for (String str : sets) {  
			
			System.out.println(str);
		    Map<String,String> keys = jedis.hgetAll(str);
		    for (Map.Entry<String, String> entry : keys.entrySet()) { 
		    	ErrorObject object = new ErrorObject();
				object.setHashUrl(str);
		    	object.setKey(entry.getKey());
		    	object.setNum(entry.getValue());
		    	System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
		    	errorList.add(object);	
			}
		}  
		
		return errorList;
	}
	
	public List<ErrorObject> sortList(List<ErrorObject> list, int flag){
		
		return ListComparator.sortList(list, flag);
	}
	
	
}
