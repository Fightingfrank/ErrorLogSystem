package com.errorLogSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.errorLogSystem.service.ErrorInforServiceImpl;
import com.errorLogSystem.util.RedisUtil;


@RestController
public class ErrorSystemController {

	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private ErrorInforServiceImpl infoService;
	@RequestMapping("/hello")
	public String index(){
		
		return redisUtil.toString();
	}
	
	@RequestMapping("/getTopNError")
	public String getTopNError(@RequestParam(name = "num") String num){
		return infoService.getTopNError(Integer.parseInt(num));
	}

	@RequestMapping("/getLastNError")
	public String getLastNError(@RequestParam(name = "num") String num){
		return infoService.getLastNError(Integer.parseInt(num));
	}
	
	@RequestMapping("/getErroByURL")
	public String getErrorByName(@RequestParam(name = "url") String url){
		return infoService.getErrorByURL(url);
	}
	
	@RequestMapping("/getNewError")
	public String getNewError(){
		return infoService.getNewError();
	}
}
