<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>New School Year</title>
 
</head>

<script>
 function showTerms(str)
 {
 if (str=="")
   {
   document.getElementById("Year_Terms").innerHTML="";
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
     document.getElementById("Year_Terms").innerHTML=xmlhttp.responseText;
     $('body').updatePolyfill();
     }
   };
 xmlhttp.open("GET","Display_Terms.jsp?terms_no="+str,true);
 xmlhttp.send();
 }

/*  ////var datefield=document.createElement("input")
 //datefield.setAttribute("type", "date")
$(document).ready(function() 
{
	 //if (datefield.type!="date")
	 //{ //if browser doesn't support input type="date", initialize date picker widget:
		 $(function() 
			{
		     $( "#Year_End" ).datepicker({ dateFormat: 'yy-mm-dd'}); 
			});
	//}
}); */



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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Academic Year</a> <a href="${pageContext.request.contextPath}/Admin/Academic_Year/New_School_Year.jsp" class="current">New Academic Year</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>New Academic Year</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Acaemic Year</h5>
		        </div>
		        <div class="widget-content nopadding">
					<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_School_Year">
		            <div class="control-group">
					  <label class="control-label">School Name *</label>
		              <div class="controls"> 								              
						<select name="School" id="School" class="span11" required>
					 			 	<option value="" > 
								  	Select School
								  	</option>		  
					         <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
					          		<option value="${School_id}"><c:out value='${Schools.school_Name[position.index]}'/></option>
					          </c:forEach>	
						</select>
					  </div>
		            </div>
					<div class="control-group">
					  <label class="control-label">Academic Year Name *</label>
		              <div class="controls">
						<input name="Year_Name" type="text" class="span11" id="Year_Name" required maxlength="50">					
 					  </div>
 					</div>	
					<div class="control-group">
					  <label class="control-label">Start Date *</label>
 		              <div class="controls">
						<input name="Year_Start" type="Date" id="Year_Start" class="span11" required>						
 					  </div>	                  
		            </div> 					  
					<div class="control-group">
					  <label class="control-label">End Date *</label>
		              <div class="controls">	
						<input name="Year_End" type="date" id="Year_End" class="span11" required>					
 					  </div>
 					</div> 					
					<div class="control-group">
					  <label class="control-label">Number of School year terms *</label>
		              <div class="controls">	
						<input name="terms" type="number" min="0" required id="terms" class="span11" onkeyup="showTerms(this.value)" onChange="showTerms(this.value)">										
 					  </div>
 					</div> 
 					<div  id="Year_Terms" ></div>
					<div class="form-actions">
					<input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
					</div> 					
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>
		   <div  class="span6">
		   	<div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Quick Hints</h5>
		        </div>
		       		Every year, the School Administration will have to create a new school year which has a start and end dates. 
		       		The school year, in turn, might have a numbers of terms and each term will have to have start and end dates as well as vacation start and end dates.    	<hr>
		    </div>
			<div class="widget-box">
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
			</div>		    
		   </div>		
		 </div>
		</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>