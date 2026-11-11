<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>New Holiday</title>
 <script>
 
 function showHolidays(str, Country_id)
 {
 document.getElementById("Holidays").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
 if (str=="" && Country_id=="")
   {
   document.getElementById("Holidays").innerHTML="";
   return;
   } 
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
     document.getElementById("Holidays").innerHTML=xmlhttp.responseText;
     $('body').updatePolyfill();
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Holidays_Details?Holidays_no="+str+ "&Country_id="+Country_id,true);
 xmlhttp.send();
 }
 

 
 function showOneHoliday(str,last_i)
 {
	
 if (str=="")
   {
   document.getElementById("OneHoliday").innerHTML="";
   return;
   } 
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
	 var newElement = document.createElement('div');
	 newElement.innerHTML = xmlhttp.responseText;
	 document.getElementById("OneHoliday").appendChild(newElement);
     document.getElementById("i").value=parseInt(last_i)+1;
     document.getElementById("Holidays_qty").value=parseInt(last_i)+1;
     $('body').updatePolyfill();
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Holidays_Details?Add_New="+str+"&last_i="+last_i,true);
 xmlhttp.send();
 }
 
 function deleteHoliday(str,last_i,i, Country_id, Holiday_id)
 {

 if (str=="")
   {
   document.getElementById("cell"+i).innerHTML="";
   return;
   } 
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
	 //document.getElementById("cell"+i).innerHTML=xmlhttp.responseText;
	 var tbl = document.getElementById("cell"+i);
	 tbl.parentNode.removeChild(tbl);
	 var tb2 = document.getElementById("wcell"+i);
	 tb2.parentNode.removeChild(tb2);
/* 	 if(i==1)
		 {
		 	document.getElementsByClassName("buttonHolder")[0].innerHTML="";
		 } */
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Holidays_Details?Delete="+str+"&last_i="+last_i+"&i="+i+"&Country_id="+Country_id+"&Holiday_id="+Holiday_id,true);
 xmlhttp.send();
 }

 </script>
</head>

<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Academic Year</a> <a href="${pageContext.request.contextPath}/Admin/Academic_Year/New_Holiday.jsp" class="current">National Holidays</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>National Holidays</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>National Holidays</h5>
		        </div>
		        <div class="widget-content nopadding">
					<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Holiday">
		            <div class="control-group">
					  <label class="control-label">Country Name</label>
		              <div class="controls">								              
						<select name="Country_id" id="Country_id" class="span11" onChange="showHolidays(0, this.value)">
						  <option value=""> 
						  	Select Country
						  </option>
					         <c:forEach items="${Countries.country_id}" var="Country_id" varStatus="position">
					          	<option value="${Country_id}"><c:out value='${Countries.country_Name[position.index]}'/></option>
					          </c:forEach>	
						</select>
					  </div>
		            </div>	
      					<div id="Holidays"></div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
		 </div>
		</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>

<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>