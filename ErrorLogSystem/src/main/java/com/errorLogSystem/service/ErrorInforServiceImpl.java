package com.errorLogSystem.service;

import java.io.File;
import java.io.IOException;
import java.util.List;









import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.errorLogSystem.model.ErrorObject;
import com.errorLogSystem.model.ModifyErrorPriorityReqBean;
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
		
		List<ErrorObject> list = searchOperationUtil.getTopNErrorList(n);
				
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
		ErrorObject object = searchOperationUtil.getErrorByKey(url);
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

	@Override
	public String modifyErrorPriority(ModifyErrorPriorityReqBean reqBean) {
		
		String key = reqBean.getKey();
		String priority = reqBean.getPriority();
		ErrorObject object = searchOperationUtil.getErrorByKey(key);
		if(null != object){
			logger.info(key + "更新优先级成功 :" + priority);
			object.setPriority(priority);
			// 需要更新排序顺序
		}else{
			logger.info("没有查找到该key对应的error信息");
			return "";
		}
		return JSONObject.toJSONString(object);
	}
	
	
	
	
	

}	
