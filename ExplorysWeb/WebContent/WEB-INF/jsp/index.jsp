<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<meta http-equiv="content-type" content="text/html; charset=utf-8"/>

<title>Big Data Search Administration Site</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
<script type="text/javascript">
      google.load('visualization', '1', {packages: ['corechart']});
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
  <script type="text/javascript">
    var transData;
   	$(document).ready(function(){
   	   	var url = "http://localhost:8080/SJHSApp/rest/App/getCensusAll";
   		$("button").click(function(){
 	    	$.getJSON(url,function(result){
 	    		transData = result;
  	    		var errorMsg = transData.errorMsg;
 	    		alert(errorMsg);
  	    		$.each(transData, function(i, field){
   	      			$("div").append(field + " ");
   	     		});
   		    });
   		});
  	});

    	function drawVisualization(result) {
    		//alert("inside drawVisualization()");
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
          ['Month', 'Bolivia', 'Ecuador', 'Madagascar', 'Papua New Guinea', 'Rwanda', 'Average'],
          ['2004/05',  165,      938,         522,             998,           450,      614.6],
          ['2005/06',  135,      1120,        599,             1268,          288,      682],
          ['2006/07',  157,      1167,        587,             807,           397,      623],
          ['2007/08',  139,      1110,        615,             968,           215,      609.4],
          ['2008/09',  136,      691,         629,             1026,          366,      569.6]
        ]);

        var options = {
          title : 'Patient Census by Hour',
          vAxis: {title: "# of Patients"},
          hAxis: {title: "Hour"},
          seriesType: "bars",
          series: {5: {type: "line"}},
          backgroundColor: "#D3EDEB",
          chartArea: {left:20,top:0,width:"50%",height:"75%"},
          width: 2000,
          height: 700,
          fontSize: 30,
          italic: false
        };

        var chart = new google.visualization.ComboChart(document.getElementById('census_chart'));
        chart.draw(data, options);
      }
      //google.setOnLoadCallback(drawVisualization);
      
      function loadChart(result) {
    	  //result is in JSON object format.
    	  var census = result.census;
    	  var minList = new Array();

    	  var rowMap = new Array();
    	  for(i=1; i<=24; i++) {
    		  var temp = new Array();
    		  temp[0] = i;
    		  rowMap[i] = temp;
    	  }
    	  //alert("rowMap before==>" + rowMap);
    	  minList[0] = "Hour";
    	  //alert("ministryList  before==>" + minList.toLocaleString());
    	  //var data = new google.visualization.DataTable();
    	  //data.addColumn('number', 'Hour');
			var len = 1;
          for (var i in census) {
        	  var ministry = result.census[i].ministry;
        	  //alert("ministry =>" + ministry);
        	  minList[len]= ministry;
        	  //data.addColumn('string', ministry);
        	  var day1temp = result.census[i].day1;
        	  var day2temp = result.census[i].day2;
        	  //alert("day1 =>" + day1temp + " day2 =>" + day2temp);
        	  if(day1temp != null){
        		  //alert("minstry=>" + ministry + " day1==" + day1temp);
        		  var countMap1 = result.census[i].day1census;
              	  for(j=1; j<=24; j++) {
            		  if(countMap1[j] != null) {
            			  //alert("Hour=>" + j + " patientcount=>" + countMap1[j]);
            			  var tempArr = rowMap[j];
            			  tempArr[len] = countMap1[j];
            			  rowMap[j] = tempArr;
            		  }else {
            			  var tempArr = rowMap[j];
            			  tempArr[len] = 0;
            			  rowMap[j] = tempArr;
            		  }
            		 //alert("rowMap["+j+"]"+"["+len+"]==>" + rowMap[j][len]);
              	  }
        	  }
        	  if(day2temp != null){
        		  var countMap2 = result.census[i].day2census;
        		  //alert("minstry=>" + ministry + " day2==" + day2temp);
            	  for(k=1; k<=24; k++) {
            		  if(countMap2[k] != null) {
            			  //alert("Hour=>" + k + " patientcount=>" + countMap2[k]);
            			  var tempArr = rowMap[k];
            			  tempArr[len] = countMap2[k];
            			  rowMap[k] = tempArr;

            		  }else {
            			  var tempArr = rowMap[k];
            			  tempArr[len] = 0;
            			  rowMap[k] = tempArr;
            		  }
        			  //alert("rowMap["+k+"]"+"["+len+"]==>" + rowMap[k][len]);
            	  }
        	  }
        	  len = len +1;
          }// end of for loop
          rowMap[0] = minList;
          //var data = google.visualization.arrayToDataTable(rowMap);
          var data = google.visualization.DataTable();

       		// determine the number of rows and columns.
          var numRows = rowMap.length;
          var numCols = rowMap[0].length;
          alert("number of rows==>" + numRows);
          alert("number of columns==>" + numCols);
      	  
          // all other columns are of type 'number'.
          for (var i = 0; i < numCols; i++) {
        	alert("column added=>" + rowMap[0][i]);
            data.addColumn('number', rowMap[0][i]);           
          }
          // now add the rows.
          for (var i = 1; i < numRows; i++) {
        	alert("row added=>" + rowMap[i]);
            data.addRow(rowMap[i]);            
          }
          
           var options = {
                  title : 'Patient Census by Hour',
                  vAxis: {title: "# of Patients"},
                  hAxis: {title: "Hour"},
                  seriesType: "bars",
                  series: {5: {type: "line"}},
                  backgroundColor: "#D3EDEB",
                  chartArea: {left:20,top:0,width:"50%",height:"75%"},
                  width: 2000,
                  height: 700,
                  fontSize: 30,
                  italic: false
                };
           var chart = new google.visualization.ComboChart(document.getElementById('census_chart'));
           // redraw the chart.
           chart.draw(data, options);
      }
      
      function getCensus() {
    	  var url = null;
    	  var ministry = document.getElementById("ministry").value;
    	  alert(ministry);
    	  if(ministry == 'ALL') {
    		  url = "http://localhost:8080/SJHSApp/rest/App/getCensusAll";
     	  }else {
     		 url = "http://localhost:8080/SJHSApp/rest/App/getCensus?ministry=" + ministry;
     	  }
    	  alert(url);
    	  $.getJSON(url,function(result){
    		  google.setOnLoadCallback(loadChart(result));
    		  //loadChart(result);
    		  //google.setOnLoadCallback(drawVisualization(result));
 		    });
      }
      
      
  </script>
</head>
<% 
  	com.explorys.business.UserInfo userInfo 
    	= (com.explorys.business.UserInfo)pageContext.findAttribute("userInfo");
	com.explorys.business.Credentials credentials 
		= (com.explorys.business.Credentials)pageContext.findAttribute("credentials");	
String userid = null;
ArrayList<String> ministryList = null; 
String errorMsg = null;
if(userInfo != null) {
	userid = userInfo.getUserid();
	ministryList = (ArrayList<String>)userInfo.getMinistryList();
}

if(credentials != null) {
	errorMsg = credentials.getErrorMsg();
}
%>
<br>
<center>
<br>
<b>Patient Census by Ministry</b>
<br>

<form>
Ministry List: &nbsp;&nbsp;&nbsp;
<select name="ministry">
    <option value="" disabled="disabled" selected="selected">Please select a ministry</option>
    <option value="ALL">All Ministries</option>
<%
if(ministryList != null) {
	int size = ministryList.size();
	for(int i =0; i <size; i++) {
		String ministry = ministryList.get(i);
%>
<option value="<%=ministry %>"><%= ministry %></option>

<%
	}
}
%>
    
</select>
&nbsp;&nbsp;&nbsp;
<input type="button" value="Patient Census" align="center" style=" text-align: center; font-weight:bold; width: 125px; height: 40px" onclick="getCensus()">     
</form>
<br>
<br>
<br></br>
<div id="census_chart" style="style5"></div>
<br></br>


<form method="post" action="patient.htm">
Patient Search: &nbsp;&nbsp;&nbsp;
<input type="text" value="" name="patient" />
&nbsp;&nbsp;&nbsp;
<input type="submit" value="Search Patient" align="center" style=" text-align: center; font-weight:bold; width: 125px; height: 40px">     
</form>

</center>

<br>
<br> 

</body>
</html>


