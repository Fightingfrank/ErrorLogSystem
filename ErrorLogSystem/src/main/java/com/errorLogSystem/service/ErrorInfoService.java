package com.errorLogSystem.service;

public interface ErrorInfoService {
	public String getTopNError(int n);
	public String getLastNError(int n);
	public String getErrorByURL(String url);
}
