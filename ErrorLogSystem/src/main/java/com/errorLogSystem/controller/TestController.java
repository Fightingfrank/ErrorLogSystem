package com.errorLogSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.errorLogSystem.service.ErrorInforServiceImpl;
import com.errorLogSystem.service.getInfoService;
import com.errorLogSystem.util.RedisUtil;


@RestController
public class TestController {

	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private getInfoService service;
	
	@Autowired
	private ErrorInforServiceImpl serviceImpl;
	@RequestMapping("/hello")
	public String index(){
		
		return redisUtil.toString();
	}
	
	@RequestMapping("/getInfoTest")
	public String getInfo(){
		return service.getInfoService();
	}
	
	@RequestMapping("/getInfo")
	public String getTop10Error() {
		return serviceImpl.getTop10Error();
	}
}
