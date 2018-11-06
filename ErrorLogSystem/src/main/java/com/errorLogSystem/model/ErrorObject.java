package com.errorLogSystem.model;

import java.io.Serializable;


/**
 * Error信息model
 * @author lijunhui
 *
 */
public class ErrorObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5170610852083348315L;
	private String key;
	private String num;
	private String hashUrl;

	public String getHashUrl() {
		return hashUrl;
	}
	public void setHashUrl(String hashUrl) {
		this.hashUrl = hashUrl;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
}
