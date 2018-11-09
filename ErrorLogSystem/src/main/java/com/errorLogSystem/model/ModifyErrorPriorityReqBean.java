package com.errorLogSystem.model;

import java.io.Serializable;

public class ModifyErrorPriorityReqBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8133455698915092268L;
	private String key;
	private String priority;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
}
