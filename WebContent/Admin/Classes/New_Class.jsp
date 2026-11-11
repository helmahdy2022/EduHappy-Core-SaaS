<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>New Class</title>

<script>
 function showGrades(str)
 {
 if (str=="")
   {
	   document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
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
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 // end of ShowGrade function
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Classrooms</a> <a href="${pageContext.request.contextPath}/Admin/Classes/New_Class.jsp" class="current">New Classroom</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>New Classroom</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Classroom</h5>
		        </div>
		        <div class="widget-content nopadding">
					<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Class">
		            <div class="control-group">
					  <label class="control-label">School Name</label>
		              <div class="controls">
			            <select name="School_id" id="School_id" class="span11" onChange="showGrades(this.value)" required>
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
					  <label class="control-label">Grade Name</label>
		              <div class="controls">
			         <select name="Grade" id="Grade" class="span11" required>
			 			 <option value="">Select Grade</option>
			 		</select>  						
 					  </div>
 					</div>	
					<div class="control-group">
					  <label class="control-label">Classroom Name *</label>
		              <div class="controls">
						<input type="text" name="Class_Name" id="Class_Name" class="span11" required maxlength="50">  						
 					  </div>
 					</div>
		            <div class="form-actions" style="text-align:center;">
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
					<ul>
					<li>
						The class is a physical grouping of students under the grade (might be a room number in which the students will take their courses).
					</li>
					<li>
						Students under this grade will be distributed into these classes. 
					</li>
					<li>
						You must create classes under the grade. 
					</li>
					</ul>     
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