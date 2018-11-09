package com.errorLogSystem.service;

import com.errorLogSystem.model.ErrorObject;
import com.errorLogSystem.model.ModifyErrorPriorityReqBean;

public interface ErrorInfoService {
	
	/**
	 * 获取出现次数排名前N的error信息
	 * @param n
	 * @return
	 */
	public String getTopNError(int n);
	
	/**
	 * 获取次数排名后N的error信息、
	 * @param n
	 * @return
	 */
	public String getLastNError(int n);
	
	/**
	 * 查看具体请求接口的Error信息
	 * @param url
	 * @return
	 */
	public String getErrorByURL(String url);
	
	/**
	 * 获取最近出现的新error (后续需要考虑参数问题，比如根据日期来查询，现在默认全部展示出来)
	 * @return
	 */
	public String getNewError();
	
	
	/**
	 * 修改对应error的优先级
	 * @param reqBean
	 * @return
	 */
	public String modifyErrorPriority(ModifyErrorPriorityReqBean reqBean);
}
