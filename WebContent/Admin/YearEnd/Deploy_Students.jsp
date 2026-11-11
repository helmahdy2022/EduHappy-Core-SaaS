<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Year End Procedures</title>

</head>
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.form.js"></script> 
<script>
   $(document).ready(function() 
{ 
    $('#form1').ajaxForm({target:'#DeployStudents'}); 
});  
</script>
<script type="text/javascript">
function showGrades(School_id)
{
if (School_id=="")
  {
	   document.getElementById("Grade_id").innerHTML="<option>Select Grade</option>";
	   document.getElementById("Class_id").innerHTML="<option>Select Class</option>";
	   document.getElementById("DeployStudents").innerHTML="";
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
       document.getElementById("Grade_id").innerHTML=xmlhttp.responseText;
	   document.getElementById("Class_id").innerHTML="<option>Select Class</option>";
	   document.getElementById("DeployStudents").innerHTML="";
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+School_id,true);
xmlhttp.send();
}

function showClasses(Grade_id)
{
if (Grade_id=="")
  {
	   document.getElementById("Class_id").innerHTML="<option>Select Class</option>";
	   document.getElementById("DeployStudents").innerHTML="";
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
       document.getElementById("Class_id").innerHTML=xmlhttp.responseText;
	   document.getElementById("DeployStudents").innerHTML="";
       showGradeStudents(Grade_id);
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Classes?Grade_id="+Grade_id,true);
xmlhttp.send();
}

function showGradeStudents(Grade_id)
{
if (Grade_id=="")
  {
  document.getElementById("DeployStudents").innerHTML="";
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
    document.getElementById("DeployStudents").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grade_Students?Grade_id="+Grade_id,true);
xmlhttp.send();
}
</script>

<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Year End Procedures</a> <a href="${pageContext.request.contextPath}/Admin/YearEnd/Deploy_Students.jsp" class="current">Assign Students to Classrooms</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Assign Students to Classrooms</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Assign Students to Classrooms</h5>
		        </div>
		        	<div class="widget-content nopadding">
    			<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Deploy_Students">
			            <div class="control-group">
							In this process, you will need to select students under one Grade and assign them to class. 
							<br>You will need to repeat this process until you finish all students under the same Grade and then start over to the next Grade
							<br> Please select the School and Grade to Proceed.
			            </div>
			            <div class="control-group">
						  <label class="control-label">School Name</label>
			              <div class="controls">	               																	
				            <select name="School_id" id="School_id" class="span11" onChange="showGrades(this.value)">
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
					      	<select name="Grade_id" class="span11" id="Grade_id" onchange="showClasses(this.value);">
						 		<option value="">Select Grade</option>		
							</select> 
	 					  </div>
	 					</div>	 						            					      
						<div class="control-group">
						  <label class="control-label">Classroom Name</label>
			              <div class="controls"> 						 	 					   
						     <select name="Class_id" class="span11" id="Class_id" required>
								 <option value="">Select Class</option>
							</select> 
	 					  </div>
	 					</div>	 
						<div id="DeployStudents"></div>
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
					In this process, you will need to select students under one Grade and assign them to class. 
					<br>You will need to repeat this process until you finish all students under the same Grade and then start over to the next Grade
					<br> Please select the School and Grade to Proceed.  
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