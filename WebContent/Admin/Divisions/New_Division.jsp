<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>New Division</title>
<script>
 function showGrades(str)
 {
 if (str=="")
   {
	   document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
	   document.getElementById("CourseClass").innerHTML="";
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
     document.getElementById("Grade").innerHTML=xmlhttp.responseText;
     document.getElementById("CourseClass").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 
 // end of ShowGrade function
 function showCoursesClasses(str)
 {
 if (str=="")
   {
   document.getElementById("CourseClass").innerHTML="";
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
     document.getElementById("CourseClass").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Div_Courses_Classes?Grade_id="+str,true);
 xmlhttp.send();
 }
 </script>
<title>New Division</title>


</head>

<body>

<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Divisions</a> <a href="${pageContext.request.contextPath}/Admin/Divisions/New_Division.jsp" class="current">New Division</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>New Division</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Division</h5>
		        </div>
		        <div class="widget-content nopadding">
   					<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Division">
		            <div class="control-group">
					  <label class="control-label">School Name *</label>
		              <div class="controls">  	
			            <select name="School_id" id="School_id"  class="span11" onChange="showGrades(this.value)" required>
			 			 <option value=""> 
						  	Select School
						  </option>
						  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
					       	<option value="${School_id}"><c:out value='${Schools.school_Name[position.index]}'/></option>
					      </c:forEach>	  
						</select>													              
					  </div>
		            </div>
					<div class="control-group">
					  <label class="control-label">Grade Name *</label>
		              <div class="controls">  
				        <select name="Grade" id="Grade" class="span11" onChange="showCoursesClasses(this.value)" required>
				 			 <option value="">Select Grade</option>
				 		</select> 				 								
 					  </div>
 					</div>	
					<div class="control-group">
					  <label class="control-label">Division Name *</label>
		              <div class="controls">						
           				<input type="text" name="Division_Name" id="Division_Name" class="span11" required maxlength="50">
 					  </div>
 					</div>
					<div class="control-group">
						
 						<div id="CourseClass"> </div>
 	
 					</div> 						
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
	    		In some cases, you might want to divide the grade into divisions. 
	    		In some education systems, you might have, for example, a division for Science and another one for Arts. 
	    		You can create division and assign courses and classes to that division. 
	    		Students in that grade and in that class will be automatically added to the division once you assign their classes to that division.    
		   			<hr>
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