package com.errorLogSystem.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.errorLogSystem.model.ErrorObject;
import com.errorLogSystem.util.RedisUtil;


@Component
public class RefreshErrorList {
	
	private List<ErrorObject> errorObjectList;
	
	private Map<String, List<ErrorObject>> newErrorList = new HashMap<String, List<ErrorObject>>();
	
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 定时从redis读取更新error列表，调用SearchOperationUtil中的更新方法，更新errorList, 初次定位每5分钟轮询一次
	 * @return
	 */
	public List<ErrorObject> queryErrorListAll(){
		
		
		return null;
	}
	
	/**
	 * 定时从redis中读取出否出现最新的error，
	 */
	public List<ErrorObject> queryNewError(){
		
		
		return null;
	}
	
	
	
}
