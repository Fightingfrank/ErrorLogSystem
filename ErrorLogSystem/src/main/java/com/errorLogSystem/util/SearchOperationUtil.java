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
	
	public List<ErrorObject> getTopNErrorList(int n){
		List<ErrorObject> lists = new ArrayList<ErrorObject>();
		lists = getAllError();
		lists = sortList(lists,1);
		if(lists.size() != 0){
			if(lists.size() >= n){
				return lists.subList(0, n);
			}else{
				logger.info("error不足 " + n + "条 ： size = " + lists.size());
				return lists;
			}
		}else{
			logger.error("error记录为空");
		}
		return null;
		
	}
	
	public ErrorObject getErrorByURL(String url){
		
		for(ErrorObject object : getAllError()){
			if(object.getHashUrl().equalsIgnoreCase(url))
				return object;
		}
		
		logger.error("没有查询" + url);
		return null;
	}
	
	public List<ErrorObject> getAllError(){
		List<ErrorObject> errorList = new ArrayList<ErrorObject>();
		Jedis jedis = redisUtil.getJedisConnection();
		Set<String> sets = jedis.keys("URLERROR*");
		System.out.println(sets.getClass());
		for (String str : sets) {  
		    Map<String,String> keys = jedis.hgetAll(str);
		    for (Map.Entry<String, String> entry : keys.entrySet()) { 
		    	ErrorObject object = new ErrorObject();
				object.setHashUrl(str);
		    	object.setKey(entry.getKey());
		    	object.setNum(entry.getValue());
		    	errorList.add(object);	
			}
		}  
		return errorList;
	}
	
	public List<ErrorObject> getLastNError(int n){
		List<ErrorObject> lists = new ArrayList<ErrorObject>();
		lists = sortList(getAllError(),1);
		if(lists.size() != 0){
			if(lists.size() >= n){
				return lists.subList(lists.size() - n, lists.size());
			}else{
				logger.info("error不足 " + n + "条 ： size = " + lists.size());
				return lists;
			}
		}else{
			logger.error("error记录为空");
		}
		return null;
	}
	
	public List<ErrorObject> getNewError(){
		
		return null;
	}
	
	public List<ErrorObject> sortList(List<ErrorObject> list, int flag){
		return ListComparator.sortList(list, flag);
	}
	
}
