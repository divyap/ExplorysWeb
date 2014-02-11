package com.explorys.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.asn1.ocsp.Request;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.explorys.business.Credentials;
import com.explorys.business.UserInfo;



public class LogonFormController extends AbstractController {
	
	private Properties appConfig;
	private RestTemplate restTemplate;
	
	private final Log logger = LogFactory.getLog(getClass());
	
	public void setAppConfig(Properties config) {
		this.appConfig = config;
		
	}
	
	public void setRestTemplate(RestTemplate rest) {
		this.restTemplate = rest;
	}

    public Object validateUser(Object obj) {
    	logger.info("==========Inside LogonFormController -> validateUser method===========");
        Credentials credentials = (Credentials) obj;
        UserInfo userInfo = null;
		String loginURL = (String) appConfig.get("sjhs.url.login");
        if (credentials == null) {
        	logger.error("User credentials not specified.");
        	credentials = new Credentials();
        	credentials.setErrorMsg("User credentials not specified.");
        	return credentials;
        } else {

            logger.info("Validating user credentials for: "
                    + credentials.getUsername());
            if ((credentials.getUsername()== null)|| (credentials.getUsername().equals(""))) {
	       		logger.error("username is empty ..");
	       		credentials.setErrorMsg("username is empty. Please provide Username.");
	       		return credentials;
            } 
            if ((credentials.getPassword()== null)|| (credentials.getPassword().equals(""))) {
	       		logger.error("password is empty ..");
	       		credentials.setErrorMsg("Password is empty. Please provide Password.");
	       		return credentials;
            }
            String userid = credentials.getUsername().trim();
            String password = credentials.getPassword().trim();
            logger.info("userId and Password from login page==>" + userid + " " + password);
    		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
    	    // Add the Jackson Message converter
    	    messageConverters.add(new MappingJacksonHttpMessageConverter());
    	    // Add the message converters to the restTemplate
    	    restTemplate.setMessageConverters(messageConverters);
    	    // Prepare URL
    	    String URL = loginURL + "userid=" + userid + "&" + "pwd=" + password;
    	    logger.info("url for login ==>" + URL);
    	    try {
    	   	    userInfo = (UserInfo) restTemplate.getForObject(URL, UserInfo.class);   	    	
    	    } catch (Exception e) {
    	    	if(userInfo!=null) {
    	    		credentials.setErrorMsg(userInfo.getMsg());
    	    	}else {
    	    		credentials.setErrorMsg("REST API call failed with exception");
    	    	}
    	    	e.printStackTrace();
    	    	return credentials;
    	    }
 
            if(userInfo != null) {
            	Integer logVal = userInfo.getLogin();
            	if(logVal != null) {
            		if(logVal.intValue() == 0) {
            			logger.error("Incorrect Username ..");
            			credentials.setErrorMsg("Incorrect Username.");
            		}else if(logVal.intValue() == 1) {
            			credentials.setErrorMsg(null);
            			logger.info("successful login!! ==>" + userInfo.getUserid());
            			credentials.setSuccessfulLogin(true);
            			credentials.setUserInfo(userInfo);
            		}
            	}else {
            		logger.info("Login Value is null from userInfo Object" + logVal);
            		credentials.setErrorMsg("Login failed. Invalid Username or Password. Please try again.");
            	}
            } else {
            	logger.info("userInfo Object is null " + userInfo);
            	credentials.setErrorMsg("Login failed. User does not exist in system.");
            }
        } // end else block
        
        return credentials;
    }// end method: validate


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		logger.info("====== Inside LogonFormController->handleRequestInternal method =======");
		ModelAndView model = null;
		HttpSession session = request.getSession();
		Credentials credentials = new Credentials();
		String user = (String) request.getParameter("username");
		String pwd = (String) request.getParameter("password");
		Boolean logout = (Boolean)session.getAttribute("logout");
		logger.info("<LogonFormController> username==>:" + user + " " + pwd + " " + logout);

		
		credentials.setUsername(user);
		credentials.setPassword(pwd);
		Credentials validate = (Credentials) validateUser(credentials);
		if(validate.isSuccessfulLogin()) {
	    	session.setAttribute("userId", user);
	    	session.setAttribute("login", true);
	    	session.setAttribute("logout", false);
	    	UserInfo userInfo = validate.getUserInfo();
	    	session.setAttribute("credentials", validate);
	    	session.setAttribute("userInfo", userInfo);
	    	model = new ModelAndView("index");
	    	model.addObject("userInfo", userInfo);
	    	
		}else if(!validate.isSuccessfulLogin()) {
			session.setAttribute("userId", null);
			session.setAttribute("login", false);
			session.setAttribute("logout", false);
			session.setAttribute("credentials", validate);
			model = new ModelAndView("logon");
			model.addObject("credentials", validate);
		}else if(logout!= null && (logout.booleanValue())) {
			session.setAttribute("userId", null);
			session.setAttribute("login", false);
			session.setAttribute("logout", false);
			model = new ModelAndView("logon");
			model.addObject("session", session);
		}else {
			model = new ModelAndView("logon");
		}
		return model;
	}

}
