<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Teacher Search</title>
 <script>
 function showGrades(str)
 {
 if (str=="")
   {
	   document.getElementById("Grade").innerHTML="<option value=''>Select Grade</option>";
	   document.getElementById("Class").innerHTML="<option value=''>Select Class</option>";
		 document.getElementById("Course").innerHTML="<option value=''>Select Course</option>";
	   document.getElementById("Teacher").innerHTML="";
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
	   document.getElementById("Class").innerHTML="<option value=''>Select Class</option>";
		 document.getElementById("Course").innerHTML="<option value=''>Select Course</option>";
	   document.getElementById("Teacher").innerHTML="";
     }
   };
   xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 //==========End of showGrades function=================
 function showClasses(str)
 {
 if (str=="")
	 {
	   document.getElementById("Class").innerHTML="<option value=''>Select Class</option>";
		 document.getElementById("Course").innerHTML="<option value=''>Select Course</option>";
	   document.getElementById("Teacher").innerHTML="";
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

	   document.getElementById("Class").innerHTML=xmlhttp.responseText;
     document.getElementById("Teacher").innerHTML="";
	   showCourses(str);
     }
   };
   xmlhttp.open("GET","${pageContext.request.contextPath}/List_Classes?Grade_id="+str,true);
 xmlhttp.send();
 }
 function showCourses(str)
 {
 if (str=="")
   {
	 document.getElementById("Course").innerHTML="<option value=''>Select Course</option>";
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
     document.getElementById("Course").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Courses?Grade_id="+str,true);
 xmlhttp.send();
 }
 function showTeacher(First_Name, Last_Name, Username, Teacher_id, Class_id, School_id, Grade_id, Course_id, Page)
 {
 if (First_Name==""&&Last_Name==""&&Username==""&&Teacher_id==""&&Class_id==""&&School_id==""&&Grade_id==""&&Course_id=="") 
    {
	   document.getElementById("Teacher").innerHTML="";
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
     document.getElementById("Teacher").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Teachers?First_Name="+First_Name + "&Last_Name="+Last_Name+"&Username="+Username+"&Teacher_id="+Teacher_id+"&Class_id="+Class_id+"&School_id="+School_id+"&Grade_id="+Grade_id+"&Course_id="+Course_id+"&Page="+Page,true);
 xmlhttp.send();
 }
 // end of ShowTeacher function
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Teachers</a> <a href="${pageContext.request.contextPath}/Admin/Teachers/Search_Teacher.jsp" class="current">Manage Teacher</a> </div>
		  <h1>Search Teacher</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Search Teacher</h5>
		        </div>
		        <div class="widget-content nopadding">
    			<form name="form1" id="form1" class="form-horizontal" action="">
      <c:if test="${param.Page=='Assign_Grades'}">
		<c:set var="element" value="Grades"/>
      </c:if>
      <c:if test="${param.Page=='Assign_Classes'}">
		<c:set var="element" value="Classrooms"/>
      </c:if>
      <c:if test="${param.Page=='Assign_Courses'}">
		<c:set var="element" value="Courses"/>
      </c:if>            
      <c:if test="${param.Page=='Assign_Content'}">
		<c:set var="element" value="Content"/>
      </c:if>          
      <c:if test="${param.Page=='Assign_Grades' || param.Page=='Assign_Classes'|| param.Page=='Assign_Courses' || param.Page=='Assign_Content'}">
      In order to assign <b>${element}</b> to the a certain teacher, you need to select the teacher first. 
      The following form is used to search for the teacher and then list all teachers that meet the search criteria. 
      <b>For example</b>, select the school that the teacher belong to and then click Search Teachers button. 
      You will get the teachers list. Spot the teacher you want to assign <b>${element}</b> to and click on the Teacher ID. 
      The system will forward you to the Assign <b>${element}</b> form so that you can select the <b>${element}</b> you want to assign to that teacher.
      </c:if>
		            <div class="control-group">
					  <label class="control-label">School Name</label>
		              <div class="controls">  								              
			          	<select name="School" id="School" class="span11" onChange="showGrades(this.value)">
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
			 			 <select name="Grade" id="Grade" class="span11" onChange="showClasses(this.value)">
				 			 <option value="">Select Grade</option>
				 		</select> 
 					  </div>
 					</div>	
					<div class="control-group">
					  <label class="control-label">Classroom Name</label>
		              <div class="controls">						 
				         <select name="Class" id="Class" class="span11" onChange="document.getElementById('Teacher').innerHTML=''">
				 			 <option value="">Select Class</option>
				 		</select> 
 					  </div>
 					</div>
					<div class="control-group">
					  <label class="control-label">Course Name</label>
		              <div class="controls">						 
			 	         <select name="Course" id="Course" class="span11" onChange="document.getElementById('Teacher').innerHTML=''">
				 			 <option value="">Select Course</option>
				 		</select> 
 					  </div>
 					</div>
					<div class="control-group">
					  <label class="control-label">First Name</label>
		              <div class="controls">
            			 <input type="text" name="First_Name" id="First_Name" class="span11">					
 					  </div>
 					</div>
 					<div class="control-group">
					  <label class="control-label">Last Name</label>
		              <div class="controls">		
            			 <input type="text" name="Last_Name" id="Last_Name" class="span11">		
 					  </div>
 					</div>
 					<div class="control-group">
					  <label class="control-label">Username</label>
		              <div class="controls">	
            			 <input name="Username" type="text" class="span11" id="Username">		
 					  </div>
 					</div> 					
  					<div class="control-group">
					  <label class="control-label">Student ID</label>
		              <div class="controls">
            			 <input name="Teacher_id" type="number" id="Teacher_id" class="span11">			
 					  </div>
 					</div> 
		            <div class="form-actions">
            		<input type="button" name="Submit" id="Submit" value="Search Teachers" class="btn btn-success" onClick="showTeacher(First_Name.value, Last_Name.value, Username.value, Teacher_id.value, Class.value, School.value, Grade.value, Course.value, Page.value)">
		            </div>
				 		 <input name="Page" type="hidden" class="text" value="<c:out value='${param.Page}'/>">
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
 				</form>
 			</div>
 		</div>
     </div>
   		<div id="Teacher" class="span6"></div>
    </div>

   </div>
  </div>
 	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>