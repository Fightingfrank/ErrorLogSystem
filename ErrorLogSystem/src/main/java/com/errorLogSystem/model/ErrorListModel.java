package com.errorLogSystem.model;

import java.io.Serializable;
import java.util.List;


/**
 * ErrorList model 维护errorList，每次定时任务该处的数据,前端修改也修改该处的数据
 * @author lijunhui
 *
 */
public class ErrorListModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1629578718483691712L;

	private List<ErrorObject> errorList;
	
	private List<ErrorObject> newErrorList;
	
	private static ErrorListModel errorListModel = new ErrorListModel();
	
	private ErrorListModel(){
		
	}
	
	public static ErrorListModel getInstance(){
		return errorListModel;
	}

	
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
