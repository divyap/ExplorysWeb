package com.explorys.business;

import java.util.ArrayList;

public class UserInfo {
	private String userid;
	private String msg;
	private Integer login;
	private ArrayList<String> ministryList;

	
	public void setMinistryList(ArrayList<String> ministryList) {
		this.ministryList = ministryList;
	}
	public ArrayList<String> getMinistryList() {
		return ministryList;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserid() {
		return userid;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setLogin(Integer login) {
		this.login = login;
	}
	public Integer getLogin() {
		return login;
	}
	
	
}