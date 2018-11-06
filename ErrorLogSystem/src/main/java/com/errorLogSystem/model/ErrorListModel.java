package com.errorLogSystem.model;

import java.util.List;

public class ErrorListModel {
	
	private List<ErrorObject> errorList;
	
	
	private ErrorListModel errorListModel = new ErrorListModel();
	
	private ErrorListModel(){
		
	}
	
	public ErrorListModel getInstance(){
		return errorListModel;
	}

	
	public List<ErrorObject> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ErrorObject> errorList) {
		this.errorList = errorList;
	}
	
}
