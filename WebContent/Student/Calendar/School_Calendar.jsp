<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>School Calendar</title>

<script>
function getSchoolCalendar()
{

if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
     if (xmlhttp.readyState==4 && xmlhttp.status==200)
     {
	  sessionExpired();
    document.getElementById("SchoolCalendar").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_School_Calendar",true);
xmlhttp.send();
}

</script>
</head>

<body onLoad="getSchoolCalendar();">
<div class="pagewrap">
 <div class="header">
  <%@include file="../includes/StudentMenu.jsp" %>
</div>
<div class="content">
	<div class="leftContent"></div>
	<div class="middleContent">

<table class="calendarTable">
     	<thead>
    		<tr><th colspan="7">School Calendar</th></tr>
  		</thead>
		  <tr>
		  <td class="calendar" colspan="2">
			<div id="SchoolCalendar" style="text-align:center;margin-top:10px;"><img src='${pageContext.request.contextPath}/images/Spinner.gif'></div>						
		  </td>
		   </tr>		   		   
 </table>

	</div>
	<div class="rightContent"><%@include file="../includes/StudentRight.jsp" %></div>
</div>
<div class="footer"><%@include file="../includes/StudentFooter.jsp" %></div>
</div>
</body>
</html>