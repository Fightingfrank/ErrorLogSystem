package com.errorLogSystem.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;


/**
 * ErrorList model 维护errorList，每次定时任务该处的数据,前端修改也修改该处的数据
 * @author lijunhui
 *
 */

@Component
public class ErrorListModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1629578718483691712L;

	private List<ErrorObject> errorList;
	
	private List<ErrorObject> newErrorList;
	
	public List<ErrorObject> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ErrorObject> errorList) {
		this.errorList = errorList;
	}

	public List<ErrorObject> getNewErrorList() {
		return newErrorList;
	}

	public void setNewErrorList(List<ErrorObject> newErrorList) {
		this.newErrorList = newErrorList;
	}
	
}
