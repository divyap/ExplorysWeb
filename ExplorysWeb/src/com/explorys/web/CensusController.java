package com.explorys.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

import com.explorys.business.CensusInfo;
import com.explorys.business.Credentials;
import com.explorys.business.UserInfo;



public class CensusController extends AbstractController {
	
	private Properties appConfig;
	private RestTemplate restTemplate;
	
	private final Log logger = LogFactory.getLog(getClass());
	
	public void setAppConfig(Properties config) {
		this.appConfig = config;
		
	}
	 
	public void setRestTemplate(RestTemplate rest) {
		this.restTemplate = rest;
	}

    @SuppressWarnings("null")
	public Object getCensus(String ministry) {
    	logger.info("==========Inside CensusController -> validateUser method===========");
        CensusInfo censusInfo = null;
		String censusURL = null;
		String URL = null;	  
  		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	    // Add the Jackson Message converter
	    messageConverters.add(new MappingJacksonHttpMessageConverter());
	    // Add the message converters to the restTemplate
	    restTemplate.setMessageConverters(messageConverters);
	    
		if (ministry == null) {
        	logger.error("ministry not specified.");
        	censusInfo = new CensusInfo();
        	URL = (String) appConfig.get("sjhs.url.censusAll");
        }else {
            logger.info("Ministry selected ==>" + ministry);
    	    // Prepare URL
            censusURL = (String) appConfig.get("sjhs.url.census");
    	    URL = censusURL + "ministry=" + ministry;
    	    logger.info("url for login ==>" + URL);
        }
		
    	try {
    		censusInfo = (CensusInfo) restTemplate.getForObject(URL, CensusInfo.class);
    		if(censusInfo == null) {
    			censusInfo = new CensusInfo();
    			censusInfo.setErrorMsg("CensusInfo is null");
    		}
    	} catch (Exception e) {
    		censusInfo = new CensusInfo();
    			censusInfo.setErrorMsg("REST API call failed with exception.");
    	   	e.printStackTrace();
    	   	return censusInfo;
    	}
        
        return censusInfo;
    }// end method: validate


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		logger.info("====== Inside LogonFormController->handleRequestInternal method =======");
		ModelAndView model;
		ArrayList<String> ministryList = null;
		CensusInfo censusInfo = null;
		HttpSession session = request.getSession();

		String ministry = (String) request.getParameter("ministry");
		logger.info("<CensusController> ministry selected ==>:" + ministry);
		//censusInfo = (CensusInfo)getCensus(ministry);
		censusInfo = new CensusInfo();
		UserInfo user = (UserInfo)session.getAttribute("userInfo");
		if(user != null) {
			ministryList =  user.getMinistryList();
		}
	
		if(ministryList!=null) {
			logger.info("<CensusController> number of mninistries from session ==>:" + ministryList.size());
			session.setAttribute("ministryList", ministryList);
			
		}

		session.setAttribute("census", censusInfo);
		model = new ModelAndView("census");
		model.addObject("censusInfo", censusInfo);
		model.addObject("userInfo", user);
		model.addObject("ministryList", ministryList);
		return model;
	}

}
