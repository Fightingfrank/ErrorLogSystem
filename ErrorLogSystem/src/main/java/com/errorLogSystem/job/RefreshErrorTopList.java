package com.errorLogSystem.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.errorLogSystem.model.ErrorObject;
import com.errorLogSystem.util.RedisUtil;

@Component
public class RefreshErrorTopList {
	
	private List<ErrorObject> errorObjectList;
	
	
	@Autowired
	private RedisUtil redisUtil;
	
	public List<ErrorObject> getTop10ErrorList(){
		
		
		
		return null;
	}
	
}
