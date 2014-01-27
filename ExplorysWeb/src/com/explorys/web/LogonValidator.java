package com.explorys.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.explorys.business.Credentials;
import com.explorys.business.PatientInfo;
import com.explorys.business.UserInfo;



public class LogonValidator implements Validator {

    private final Log logger = LogFactory.getLog(getClass());
 // Create a new application context. this processes the Spring config
	ApplicationContext ctx =
	    new ClassPathXmlApplicationContext("applicationContext.xml");
	
	// Retrieve the data source from the application context
	BasicDataSource ds = (BasicDataSource) ctx.getBean("dataSource");
	
	Connection c = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	private Properties appConfig;
	private RestTemplate restTemplate;

	public void setAppConfig(Properties config) {
	   this.appConfig = config;
	}
	 
	public void setRestTemplate(RestTemplate rest) {
		   this.restTemplate = rest;
	}
		
    public boolean supports(Class clazz) {
        return clazz.equals(Credentials.class);
    }
    
    public static HttpSession getHTTPSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }
    
    public void validate(Object obj, Errors errors) {
    	logger.info("==========Inside LogonValidator -> validate method===========");
        Credentials credentials = (Credentials) obj;
		String loginURL = (String) appConfig.get("sjhs.url.login");
        if (credentials == null) {
            errors.rejectValue("username", "error.login.not-specified", null,
                    "Value required.");
        } else {

            logger.info("Validating user credentials for: "
                    + credentials.getUsername());
            if ((credentials.getUsername()== null)|| (credentials.getUsername().equals(""))) {
	       		logger.error("username is empty ..");
	       		errors.rejectValue("username", "error.login.not-specified",
                            null, "username is empty. try admin/admin");
            }
            if ((credentials.getPassword()== null)|| (credentials.getPassword().equals(""))) {
	       		logger.error("password is empty ..");
	       		errors.rejectValue("password", "error.login.not-specified",
                            null, "password is empty. try admin/admin");
            }
            String userid = credentials.getUsername();
            String password = credentials.getPassword();
            logger.info("userId and Password from logi page==>" + userid + " " + password);
    		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
    	    // Add the Jackson Message converter
    	    messageConverters.add(new MappingJacksonHttpMessageConverter());
    	    // Add the message converters to the restTemplate
    	    restTemplate.setMessageConverters(messageConverters);
    	    // Prepare URL
    	    String URL = loginURL + "userid=" + userid + "&" + "pwd=" + password;
    	    logger.info("url for login ==>" + URL);
    	    UserInfo userInfo = (UserInfo) restTemplate.getForObject(URL, UserInfo.class);
            if(userInfo != null) {
            	Integer logVal = userInfo.getLogin();
            	if(logVal != null) {
            		if(logVal.intValue() == 0) {
            			logger.error("Incorrect Username ..");
            			errors.rejectValue("username", "error.login.invalid-user",
            								null, "Incorrect Username.");
            		}else if(logVal.intValue() == 1) {
            			HttpSession session = getHTTPSession();
            	    	session.setAttribute("userId", userInfo.getUserid());
            	    	session.setAttribute("userInfo", userInfo);
            			logger.info("successful login!! ==>" + userInfo.getUserid());
            		}
            	}else {
            		logger.info("Login Value is null from userInfo Object" + logVal);
            	}
            } else {
            	logger.info("userInfo Object is null " + userInfo);
            }
        } // end else block
    }// end method: validate

}// end class: LogonValidator