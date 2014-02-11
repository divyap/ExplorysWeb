
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Big Data Search Administration Site</title>
<style type="text/css">
.newStyle1 {
	background-color: #99CCFF;
	background-image: none;
	background-repeat: repeat;
	background-attachment: scroll;
	background-position: center center;
	border: medium ridge #808000;
}
.style1 {
	color: #800080;
}
.style2 {
	color: #000000;
}
.style4 {
	color: #000000;
	text-align: center;
}
.style5 {
	text-align: center;
	font-size: x-large;
}
.style6 {
	margin-top: 175px;
}
.style7 {
	font-size: small;
	text-align: right;
}
.style8 {
	margin-left: 40px;
}
.style9 {
	margin-left: 640px;
}
</style>
</head>
<body style="background-color: #D3EDEB">

<% 
  	com.explorys.business.UserInfo userInfo 
    	= (com.explorys.business.UserInfo)pageContext.findAttribute("userInfo");
	com.explorys.business.Credentials credentials 
		= (com.explorys.business.Credentials)pageContext.findAttribute("credentials");	
String userid = null;

String errorMsg = null;

if(credentials != null) {
	errorMsg = credentials.getErrorMsg();
}
%>
<center>
<!--  
<p class="style5">Teen Survey Administration Site</p>
<br />
 -->
<form method="post" action="logon.htm">
<table width="25%" border="1">
	<tr>
		<td align="center" bgcolor="lightblue">Log on</td>
	</tr>
	<tr>
		<td>
		<table border="1" width="100%">
			<tr>
				<td width="33%" align="right">Username:</td>
				<td width="67%" align="left">
					<input type="text" name="username"	value="" />
				</td>
			</tr>
			<tr>
				<td width="33%" align="right">Password:</td>
				<td width="67%" align="left">
					<input type="password" name="password" />
				</td>
			</tr>
			<% if(errorMsg != null) {
					
				%>	
			<tr>
				<td colspan="100%" align="center">
					<font color="red"><%=errorMsg %> </font>
				</td>
			</tr>
			<%} %>
			<tr>
				<td align="center" colspan="100%"><input type="submit"
					alignment="center" value="Logon"></td>
			</tr>
		</table>

		</td>
	</tr>
</table>

</form>

</center>

</body>
</html>