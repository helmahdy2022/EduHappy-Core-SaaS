<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>New Semester</title>
<script>
function showYears(str)
{
if (str=="")
  {
  	document.getElementById("School_Year_id").innerHTML="<select><option>Select Year</option></select>";
  	document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
  	document.getElementById("GradeTerms").innerHTML="";
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
	  document.getElementById("GradeTerms").innerHTML="";
	  document.getElementById("School_Year_id").innerHTML=xmlhttp.responseText;
	  document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_School_Years?School_id="+str,true);
xmlhttp.send();
}

function showGrades(str, School_Year_id)
 {
 if (str=="" || School_Year_id=="")
   {
  	 document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
   	 document.getElementById("GradeTerms").innerHTML="";
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
     
	   document.getElementById("GradeTerms").innerHTML="";
	   document.getElementById("Grade").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 // end of ShowGrade function
 function showGradeTerms(School_year_id, Grade_id)
 {
 if (School_year_id=="")
   {
   document.getElementById("GradeTerms").innerHTML="";
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
     document.getElementById("GradeTerms").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grade_Terms?School_year_id="+School_year_id+"&&Grade_id="+Grade_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Grade Semesters</a> <a href="${pageContext.request.contextPath}/Admin/Grades/New_Grade_Semester.jsp" class="current">New Grade Semester</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>New Grade Semester</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Grade Semester</h5>
		        </div>
		        <div class="widget-content nopadding">
   				   <form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Grade_Semester">
		            <div class="control-group">
					  <label class="control-label">School Name *</label>
		              <div class="controls">  													              
			            <select name="School_id" id="School_id" class="span11" onChange="showYears(this.value)" required>
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
					  <label class="control-label">Academic Year Name *</label>
		              <div class="controls">  			 						 								
			 			<select name="School_Year_id" id="School_Year_id" class="span11" onChange="showGrades(School_id.value, this.value)" required >
						  <option value=""> 
						  	Select Year
						  </option>
						</select>
 					  </div> 					  
 					</div>
					<div class="control-group">
					  <label class="control-label">Grade Name *</label>
		              <div class="controls">  			 						 								
				         <select name="Grade" id="Grade" class="span11" onChange="showGradeTerms(School_Year_id.value, this.value)" required>
				 			 <option value="">Select Grade</option>
				 		 </select> 
 					  </div> 					  
 					</div> 						
					<div class="control-group">
					  <label class="control-label">Semester Name *</label>
		              <div class="controls">						
           				<input type="text" name="Semester_Name" id="Semester_Name" class="span11" required maxlength="50">
 					  </div>
 					</div>
					<div class="control-group">
						<div id="GradeTerms"> </div>					 	
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
        			Grade semester is part of the year that can cover one or more Grade terms. 
        			This is in case you have a grade with semesters different than the general school year semesters.
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