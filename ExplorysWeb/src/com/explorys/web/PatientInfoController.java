package com.explorys.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.explorys.business.PatientInfo;
import com.sun.net.ssl.internal.ssl.X509ExtendedTrustManager;


@Controller

public class PatientInfoController extends AbstractController{

   	protected final Log logger = LogFactory.getLog(getClass());
   	
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
	
	/*
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
	*/

	 private static String getNonce(String host) throws NoSuchAlgorithmException, KeyManagementException, IOException {

		 //String url = "http://" + host + "/patient-record-external/nonce";
		 String url = host;
	     // Create a trust manager that does not validate certificate chains
		 
	     TrustManager[] trustAllCerts = new TrustManager[] { new X509ExtendedTrustManager() {
		    		@Override
	            	public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	            		return null;
	            	}

					@Override
					public void checkClientTrusted(X509Certificate[] arg0,
							String arg1) throws CertificateException {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void checkServerTrusted(X509Certificate[] arg0,
							String arg1) throws CertificateException {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void checkClientTrusted(X509Certificate[] arg0,
							String arg1, String arg2, String arg3)
							throws CertificateException {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void checkServerTrusted(X509Certificate[] arg0,
							String arg1, String arg2, String arg3)
							throws CertificateException {
						// TODO Auto-generated method stub
						
					}
	            }
	    };

	    // Install the all-trusting trust manager
	    SSLContext sc = SSLContext.getInstance("SSL");
	    sc.init(null, trustAllCerts, new java.security.SecureRandom());
	    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	    // Create all-trusting host name verifier
	    HostnameVerifier allHostsValid = new HostnameVerifier() {
	    	@Override
	    	public boolean verify(String hostname, SSLSession session) {
	    		return true;
	    	}
	    };

	    // Install the all-trusting host verifier
	    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

	    URL obj = new URL(url);
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	    // optional default is GET
	    con.setRequestMethod("POST");
	    StringBuffer response = null;

	    int responseCode = con.getResponseCode();
	    if (responseCode == 200) {
	    	BufferedReader in = null;
            try {
            	in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            String inputLine;
	            response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                	response.append(inputLine);
	            }
	            in.close();

	        } finally {
	        	if (in != null)
	        		in.close();
	        }
	    }
	    return response == null ? null : response.toString();
	 }

	
	 private static String getPatInfo(String host) throws NoSuchAlgorithmException, KeyManagementException, IOException {

		 //String url = "http://" + host + "/patient-record-external/nonce";
		 String url = host;
	     // Create a trust manager that does not validate certificate chains
		 
	     TrustManager[] trustAllCerts = new TrustManager[] { new X509ExtendedTrustManager() {
		    		@Override
	            	public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	            		return null;
	            	}

					@Override
					public void checkClientTrusted(X509Certificate[] arg0,
							String arg1) throws CertificateException {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void checkServerTrusted(X509Certificate[] arg0,
							String arg1) throws CertificateException {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void checkClientTrusted(X509Certificate[] arg0,
							String arg1, String arg2, String arg3)
							throws CertificateException {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void checkServerTrusted(X509Certificate[] arg0,
							String arg1, String arg2, String arg3)
							throws CertificateException {
						// TODO Auto-generated method stub
						
					}
	            }
	    };

	    // Install the all-trusting trust manager
	    SSLContext sc = SSLContext.getInstance("SSL");
	    sc.init(null, trustAllCerts, new java.security.SecureRandom());
	    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	    // Create all-trusting host name verifier
	    HostnameVerifier allHostsValid = new HostnameVerifier() {
	    	@Override
	    	public boolean verify(String hostname, SSLSession session) {
	    		return true;
	    	}
	    };

	    // Install the all-trusting host verifier
	    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

	    URL obj = new URL(url);
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	    // optional default is GET
	    con.setRequestMethod("POST");
	    StringBuffer response = null;

	    int responseCode = con.getResponseCode();
	    if (responseCode == 200) {
	    	BufferedReader in = null;
            try {
            	in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            String inputLine;
	            response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                	response.append(inputLine);
	            }
	            in.close();

	        } finally {
	        	if (in != null)
	        		in.close();
	        }
	    }
	    return response == null ? null : response.toString();
	 }
	 
	 
	 
	 
	 
	private char[] generateMAC(String url) {
		logger.info("<--------inside generateMAC method--------->" + url);
		String data = url;
		Security.addProvider(new BouncyCastleProvider());
		MessageDigest mda = null;
		MessageDigest mdb = null;
		try {
			mda = MessageDigest.getInstance("SHA-512", "BC");
			mdb = MessageDigest.getInstance("SHA-512", "BC");

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte [] digesta = mda.digest(data.getBytes());
		byte [] digestb = mdb.digest(data.getBytes());


		System.out.println(MessageDigest.isEqual(digesta, digestb));
		char[] result = Hex.encodeHex(digesta);
		//System.out.println(result);
		//logger.info("<-------HEX code in String ===>" + result.toString());
		return result;

	    //return output.toString();
	}
	 
	private String SHA512Converter(String url) {
		String shaStr = DigestUtils.shaHex(url);		
		return shaStr;
	}
	
	private PatientInfo getExplorysPatient(String patID) {
		String URL = null;
		PatientInfo patInfo = new PatientInfo();
		String expNonceURL = (String) appConfig.get("explorys.nonce.url");
		String expDataURL = (String) appConfig.get("explorys.data.url");
		String expUser = (String) appConfig.get("explorys.user");
		String expKey = (String) appConfig.get("explorys.key");
		logger.info("explorys nonce url ==>" + expNonceURL);
		logger.info("explorys data url ==>" + expDataURL);
		logger.info("explorys User ==>" + expUser);
		logger.info("explorys SJHS Key ==>" + expKey);
		
		//Step1 : Request for NONCE
		String nonce = null;
		try {
			nonce = getNonce(expNonceURL);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("NONCE without certificate validation ==>" + nonce);
		
		//Lexical Sort
		Collection<String> lexArr = 
		    new TreeSet<String>(Collator.getInstance());
		lexArr.add(nonce);
		lexArr.add(patID);
		lexArr.add(expUser);
		lexArr.add(expKey);
		//String lexStr = lexArr.toString();
		
		String lexStr = nonce + patID + expUser + expKey;
		logger.info("Lexical Sorted key for MAC genration ==>" + lexStr);
		
		//Create MAC
		// Testing with given example
		//String testStr = (String) appConfig.get("test.string");
		//char[] mac = generateMAC(testStr);
		char[] mac = generateMAC(lexStr);
		System.out.print(mac);
		String macFinal = String.valueOf(mac);
		logger.info("MAC String from method generateMAC==>" + macFinal);

		//Prepare Data URL
		URL = expDataURL + "patientId=" + patID + 
					"&user=" + expUser + "&nonce=" + nonce + "&mac=" + macFinal;
		
		logger.info("prepared URL for explorys server ==>" + URL);
		
		//STEP1 : getting NONCE from Explorys Web Services
		//String nonceREST = (String) restTemplate.getForObject(expNonceURL, String.class);
		//logger.info("NONCE from Explorys Test Server ==>" + nonceREST);
		
		// Step2: get Patient Info from Explorys Web Services
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	    // Add the Jackson Message converter
	    messageConverters.add(new MappingJacksonHttpMessageConverter());
	    // Add the message converters to the restTemplate
	    restTemplate.setMessageConverters(messageConverters);

		patInfo = (PatientInfo) restTemplate.getForObject(URL, PatientInfo.class);
		
		/*
		String result = null;
		try {
			result = getPatInfo(URL);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("patiet info in string ==>" + result);
		*/
		return patInfo;
	}
	
	
	@SuppressWarnings("rawtypes")
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
    		
     	    int size = 0;
    		if (rs != null){  
         	    size = rs.getFetchSize();  
    	    }
    	    
    	    if(size == 0) {
    	    	logger.error("No records found ..");
    	    }else {
    	    	logger.error("records size is =>" + size);
    	    	while(rs.next()) {
	    	        String key = rs.getString(1);
	    	        logger.info("Key==>" + key);
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
    	    try { c.close(); } catch (SQLException e) {e.printStackTrace(); }
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
  		logger.info("====== inside PatientInfoController-> handleRequestInternal =======");
		
		HttpSession session = request.getSession();
    	String patID = null;
    	
    	patID = ((String)request.getParameter("patient")).trim();
    	logger.info("Patient ID ===>" + patID);
    	//@SuppressWarnings("rawtypes")
		//ArrayList testData = getTestData();

		PatientInfo patInfo = getExplorysPatient(patID);
    	return new ModelAndView("patient", "patInfo", patInfo);

	}
  
}
