package com.explorys.business;

import java.sql.Connection;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Credentials {
    private String username;

    private String password;
    
    private ArrayList<String> ministryList;

    private ApplicationContext ctx;
    
    private Connection connection;
    
    private HttpServletRequest request;
    
    private HttpSession session;
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;

    }
    
    public ApplicationContext getAppContext() {
        return ctx;
    }

    public void setAppContext(ApplicationContext ctx) {
        this.ctx = ctx;

    }

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setMinistryList(ArrayList<String> ministryList) {
		this.ministryList = ministryList;
	}

	public ArrayList<String> getMinistryList() {
		return ministryList;
	}

}