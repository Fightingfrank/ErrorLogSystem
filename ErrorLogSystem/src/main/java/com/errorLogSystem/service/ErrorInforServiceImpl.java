package com.errorLogSystem.service;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.errorLogSystem.model.ErrorObject;
import com.errorLogSystem.util.SearchOperationUtil;


@Component
public class ErrorInforServiceImpl implements ErrorInfoService{

	private static Logger logger = LoggerFactory.getLogger(ErrorInforServiceImpl.class);
	
	@Autowired
	private SearchOperationUtil searchOperationUtil;
	
	@Override
	public String getTop10Error() {
		
		List<ErrorObject> list = searchOperationUtil.getTop10ErrorList();
		if(null == list){
			logger.error("list 返回为null");
		}else if(list.size() <10){
			logger.info("list 记录不足10个");
		}
		String result = JSON.toJSONString(list);
		System.out.println(result);
		return result;
	}

}	
