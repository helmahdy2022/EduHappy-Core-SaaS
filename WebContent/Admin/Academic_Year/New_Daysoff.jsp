<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8">
<title>New Daysoff</title>

<script>
 function showSchools(str)
 {

if (str=="")
   {

   document.getElementById("Schools").innerHTML="";
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
     
	    var checkbox = document.getElementById ("All_Schools");;
	     if (checkbox.checked) {
	    	 document.getElementById("Schools").innerHTML="";
	     }
	     else 
	     {
	    	 document.getElementById("Schools").innerHTML=xmlhttp.responseText;
	     }  
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Daysoff_Schools_Grades",true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Academic Year</a> <a href="${pageContext.request.contextPath}/Admin/Academic_Year/New_Daysoff.jsp" class="current">New Days-off</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>New Days-off</h1>
		</div>
		<!--End-breadcrumbs-->

		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Days-off</h5>
		        </div>
		        <div class="widget-content nopadding">
				  <form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Manage_Daysoff">		          
		            <div class="control-group">
		              <label class="control-label">Days-off Name *</label>
		              <div class="controls">
		             	<input name="Daysoff_Name" type="text" class="span11" id="Daysoff_Name" required maxlength="100">
		              </div>
		            </div>		           
		            <div class="control-group">
		              <label class="control-label">Start Date *</label>
		              <div class="controls">
	 					<input name="Daysoff_Start" type="Date" id="Daysoff_Start" class="span11" required>	                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">End Date *</label>
		              <div class="controls">	
						<input name="Daysoff_End" type="Date" id="Daysoff_End" class="span11" required>	                
		              </div>
		            </div>	            
		            <div class="control-group">
		              <label class="control-label">
						<input type="checkbox" name="All_Schools" id="All_Schools" checked onClick="showSchools(this.value)">
					  </label>
		              <div class="controls">	
  						Apply on all schools and grades              
		              </div>
		            </div>
          			<div id="Schools"></div>		            
		            <div class="form-actions">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
	            	<div class="requiredSignNote"><label>Fields marked (*) are required</label></div>
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