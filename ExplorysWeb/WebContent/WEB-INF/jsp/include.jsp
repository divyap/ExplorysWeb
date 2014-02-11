<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ page import="java.util.*"%>
<%@ page import="com.explorys.business.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="org.apache.taglibs.standard.tag.el.core.*"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page import="org.apache.commons.dbcp.BasicDataSource"%>
<%@ page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@ page import="org.codehaus.jackson.JsonGenerationException"%>
<%@ page import="org.codehaus.jackson.map.JsonMappingException"%>
<%@ page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@ page import="java.io.IOException"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="str"	uri="http://jakarta.apache.org/taglibs/string-1.1"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>This is include jsp</title>
<style type="text/css">

.style11 {
	margin-top: 0px;
}

.style18 {
	text-align: left;
	font-size: small;
	font-family: Arial;
}
</style>
</head>
<%
System.out.println("======== Inside include.jsp page=====>");
String user = (String)request.getSession().getAttribute("userId");
Boolean login = (Boolean)request.getSession().getAttribute("login");
boolean check = false;
if(login != null) {
	check = login.booleanValue();
}

%>
<body style="background-color: #D3EDEB">

<img alt="" class="style11" height="125" src="/ExplorysWeb/images/SJHS-logo.jpg" width="175" style="float: left">&nbsp;&nbsp;&nbsp;
<br>
<p style="style8" style="height: 22px"><em style="margin-left: 640px"></em>
</p>
<br>
<h1 style="text-align:center;">Explorys Web-Search </h1>
<br>
<%
if (user == null && (!check)) {
	
%>
<hr style="color: #000000; text-align: center" style="height: -11px" />

<%
} else {
%>
<p style="font-size: small; text-align: right">Logged as: <%= user %></p>

<hr style="color: #000000; text-align: center" style="height: -11px" />

<form name="logout" method="post" action="logout.htm">

<p style="text-align: right; font-size: x-large; font-weight:bold"><input type="submit" value="Logout"></p>

</form>

<p style="margin-left:40px" style="height: 22px"><em style="margin-left: 640px">

</p>
<%
}
%>