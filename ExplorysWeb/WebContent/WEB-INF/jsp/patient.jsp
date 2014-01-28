<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->
<html>
<head>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teen Survey Administration Site</title>

<script type='text/javascript'>
function checkform()
{
	var x=document.forms["myForm"]["selectGrp"].value;
	if (x== null || x=="0")
	  {
	  alert("Select a group");
	  return false;
	  }

	return true;
}
</script>

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
	font-weight:bold;
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
.style10 {
	font-size: medium;
	color: #000000;
}
.style11 {
	margin-bottom: 9px;
}
.style12 {
	border: 1px solid #800080;
	background-color: grey;
	background-position: center center;
}

.style13 {
	color: #0000FF;
}
.style14 {
	text-align: center;
	font-weight:bold;
	font-size: medium;
}

.style15{
	text-align: center;
	font-size: large;
	font-weight:normal;
}

.style16{
	text-align: center;
	font-size: medium;
	font-weight:bold;
}
.smallFont {
	font-size: small;
	text-align: left;
}
.styleSubmit {
	text-align: center;
	font-size: x-large;
	font-weight:bold;
}

.style17 {
	text-align: center;
	font-weight: normal;
	font-size: medium;
}
</style>

</head>
<%
com.explorys.business.PatientInfo patInfo 
		= (com.explorys.business.PatientInfo)pageContext.findAttribute("patInfo");

System.out.println("JSP -- patient's First Name : ==>" + patInfo);
%>

<center>

<br>
<br> 
<table align="center" class="style12" style="width: 500px; height: 88px" border="2">
    <tr>
    <td colspan="100" class="style16">Patient Information</td>        
    </tr>
	<tr>
      	<td colspan="15" class="style14" valign="top" bgcolor="lightblue">User Code 
      	</td>
  	<td colspan="10" class="style14" valign="top" bgcolor="lightblue">Group Number
  	</td>
  	<td colspan="55" class="style14" valign="top" bgcolor="lightblue">Device ID
  	</td>
  	<td colspan="20" class="style14" valign="top" bgcolor="lightblue">User Survey
  	</td>
    </tr>

  </table>
</center>

</body>
</html>


