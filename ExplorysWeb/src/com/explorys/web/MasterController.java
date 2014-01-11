package com.explorys.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/movie")


public class MasterController {

   	protected final Log logger = LogFactory.getLog(getClass());
   	
    // Create a new application context. this processes the Spring config
	ApplicationContext ctx =
	    new ClassPathXmlApplicationContext("applicationContext.xml");
	
	// Retrieve the data source from the application context
	BasicDataSource ds = (BasicDataSource) ctx.getBean("dataSource");
	
	Connection c = null;
	// Open a database connection using Spring's DataSourceUtils
	//Connection c = DataSourceUtils.getConnection(ds);
		
	//JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	private ArrayList getTestData() {
		ArrayList testData = new ArrayList();
		
		try{
    	    // retrieve a list of users/password
    		String grpQuery = "SELECT top 10 * FROM sjhsSSRS.dbo.SSRS_CPT_Dictionary";
    		logger.info("Prepared Sql Query ==>" + grpQuery);
    		
    		c = ds.getConnection();
    		if(c!= null && c.isClosed()) {
				logger.error("Connection is CLOSED!");
				c = ds.getConnection();
			} else {
				if(c == null) {
					logger.error("Connection is NULL. Creating new connection object...");
					c = ds.getConnection();
				}else {
					logger.info("Connection is active.. creating prepared statement and executing query");
					ps = c.prepareStatement(grpQuery);
					rs = ps.executeQuery();
				}
			}
    	    
     	    int size =0;  
    	    if (rs != null){  
    	      rs.beforeFirst();  
    	      rs.last();  
    	      size = rs.getRow();  
    	      logger.info("Total number of records==>" + size);
    	      rs.beforeFirst(); 
    	    }
    	    
    	    if(size == 0) {
    	    	logger.error("No records found ..");
    	    }else {
    	    	
    	    	while(rs.next()) {
	    	        String key = rs.getString(1);
	    	        testData.add(key);
    	    	}
    	    }
    	    rs.close();
    	    rs = null;
    	    ps.close();
    	    ps = null;
    	    c.close();
    	    c = null;
    	} catch (SQLException ex) {
    	    // something has failed and we print a stack trace to analyze the error
    	    ex.printStackTrace();
    	    // ignore failure closing connection
    	    //try { c.close(); } catch (SQLException e) {e.printStackTrace(); }
        }finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (rs != null) {
              try { rs.close(); } catch (SQLException e) { ; }
              rs = null;
            }
            
            if (ps != null) {
              try { ps.close(); } catch (SQLException e) { ; }
              ps = null;
            }
            
            if (c != null) {
              try { c.close(); } catch (SQLException e) { ; }
              c = null;
            }
        } //end finally
		
		return testData;
	}
	
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
  		logger.info("====== inside masterController-> handleRequestInternal =======");
		
		HttpSession session = request.getSession();
    	
    	@SuppressWarnings("rawtypes")
		ArrayList testData = getTestData();

    	return new ModelAndView("index", "testData", testData);

	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getMovie(@PathVariable String name, ModelMap model) {
 
		model.addAttribute("movie", name);
		return "list";
 
	}
 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getDefaultMovie(ModelMap model) {
 
		model.addAttribute("movie", "this is default movie");
		return "list";
 
	}
	
  
}
