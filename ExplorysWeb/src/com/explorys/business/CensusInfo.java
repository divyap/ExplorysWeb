package com.explorys.business;

import java.util.Map;

public class CensusInfo {
	
	private Map<String, Object> census;
	
	private String errorMsg;

	public void setCensus(Map<String, Object> census) {
		this.census = census;
	}

	public Map<String, Object> getCensus() {
		return census;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
