package com.errorLogSystem.service;

import java.util.List;





import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.errorLogSystem.model.ErrorObject;
import com.errorLogSystem.util.SearchOperationUtil;

@Component
public class ErrorInforServiceImpl implements ErrorInfoService{

	private static Logger logger = LoggerFactory.getLogger(ErrorInforServiceImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private SearchOperationUtil searchOperationUtil;
	
	@Override
	public String getTopNError(int n) {
		
		List<ErrorObject> list = searchOperationUtil.getTopNErrorList(5);
		if(null == list){
			logger.error("list 返回为null");
		}else if(list.size() <10){
			logger.info("list 记录不足10个");
		}
		return JSON.toJSONString(list);
	}

	@Override
	public String getLastNError(int n) {
		
		List<ErrorObject> list = searchOperationUtil.getLastNError(n);
		if(null == list){
			logger.error("list 返回为null");
		}else if(list.size() <10){
			logger.info("list 记录不足10个");
		}
		return JSON.toJSONString(list);
	}

	@Override
	public String getErrorByURL(String url) {
		ErrorObject object = searchOperationUtil.getErrorByURL(url);
		if(null != object)
			return JSONObject.toJSONString(object);
		else {
			logger.error("未查找到对应URL的error");
		}
		return null;
	}
	
	
	@Override
	public String getNewError() {
		
		List<ErrorObject> object = searchOperationUtil.getNewError();
		
		return JSON.toJSONString(object);
	}
	
	
	
	

}	
