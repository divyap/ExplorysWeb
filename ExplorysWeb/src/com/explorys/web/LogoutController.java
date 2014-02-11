package com.explorys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.explorys.business.Credentials;
import com.explorys.business.UserInfo;


public class LogoutController extends AbstractController{

   	protected final Log logger = LogFactory.getLog(getClass());

   	/*
   	protected ModelAndView onSubmit(HttpServletRequest request,
   										HttpServletResponse response,
   							            Object command,
   							            BindException errors)
            								throws Exception {
   		
   		logger.info("====== inside LogoutController-> onSubmit =======");
		//String userId = (String)request.getAttribute("userId");

		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		logger.info("loggedin user is ===>" + userId);
		userId = null;
    	session.setAttribute("userId", userId);
   		return new ModelAndView(new RedirectView(getSuccessView()));
   	}
	*/
   	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
   		logger.info("====== inside LogoutController-> onSubmit =======");
		//String userId = (String)request.getAttribute("userId");
   		Credentials credentials = null;
   		UserInfo userInfo = new UserInfo();
   		userInfo.setLogin(null);
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		logger.info("loggedin user is ===>" + userId);
		userId = null;
    	session.setAttribute("userId", userId);
    	session.setAttribute("login", false);
    	session.setAttribute("logout", true);
    	Boolean logout = new Boolean(true);
   		//return new ModelAndView(new RedirectView("index"));
    	ModelAndView model = new ModelAndView("logon");
    	model.addObject("userInfo", userInfo);
    	model.addObject("logout", logout);
    	return model;
	}

}
