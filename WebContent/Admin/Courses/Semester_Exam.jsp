<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Exam Semester</title>
<script>
function showYears(str)
{
if (str=="")
  {
  	 document.getElementById("Year_id").innerHTML="<select><option >Select Year</option></select>";
	 document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
	 document.getElementById("Semester_id").innerHTML="<select><option >Select Semester</option></select>";
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("SemesterExamDetails").innerHTML = "";
	 document.getElementById("DeleteMessage").innerHTML="";
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
    document.getElementById("Year_id").innerHTML=xmlhttp.responseText;
    document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
	 document.getElementById("Semester_id").innerHTML="<select><option >Select Semester</option></select>";
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("SemesterExamDetails").innerHTML = "";
	 document.getElementById("DeleteMessage").innerHTML="";

    
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_School_Years_Grades?School_id="+str,true);
xmlhttp.send();
}

function showGrades(str, School_Year_id)
 {
 if (str=="" || School_Year_id=="")
   {
	 document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
	 document.getElementById("Semester_id").innerHTML="<select><option >Select Semester</option></select>";
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("SemesterExamDetails").innerHTML = "";
	 document.getElementById("DeleteMessage").innerHTML="";
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
	 document.getElementById("Semester_id").innerHTML="<select><option >Select Semester</option></select>";
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("SemesterExamDetails").innerHTML = "";
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 // end of ShowGrade function
 
 function showSemesters(School_Year_id, Grade_id)
 {
 if (Grade_id=="")
   {
	 document.getElementById("Semester_id").innerHTML="<select><option >Select Semester</option></select>";
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("SemesterExamDetails").innerHTML = "";
	 document.getElementById("DeleteMessage").innerHTML="";
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
     document.getElementById("Semester_id").innerHTML=xmlhttp.responseText;
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("SemesterExamDetails").innerHTML = "";
	 document.getElementById("DeleteMessage").innerHTML="";

     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Exam_Semesters?School_Year_id="+School_Year_id+"&&Grade_id="+Grade_id,true);
 xmlhttp.send();
 }
  function showCourses(Semester_id, Grade_id)
 {
 if (Semester_id=="")
   {
   	 document.getElementById("Course").innerHTML="<option>Select Course</option>";
	 document.getElementById("SemesterExamDetails").innerHTML = "";
	 document.getElementById("DeleteMessage").innerHTML="";
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
	 document.getElementById("SemesterExamDetails").innerHTML = "";
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Courses?Grade_id="+Grade_id,true);
 xmlhttp.send();
 }
 //==========End of showCourses function=================
  function showSemesterExamDetails(Course_id, Semester_id)
 {
document.getElementById("SemesterExamDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Course_id==""||Semester_id=="")
   {
   document.getElementById("SemesterExamDetails").innerHTML="";
	 document.getElementById("DeleteMessage").innerHTML="";
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
     document.getElementById("SemesterExamDetails").innerHTML=xmlhttp.responseText;
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Semester_Exam_Details?Course_id="+Course_id+"&&Semester_id="+Semester_id,true);
 xmlhttp.send();
 }
 
  function showDeleteMessage()
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
		      document.getElementById("DeleteMessage").innerHTML=xmlhttp.responseText;
		      //document.getElementById("OneSession").innerHTML="";
		      }
		    };
		  xmlhttp.open("GET","${pageContext.request.contextPath}/Global/DeleteMessage.jsp",true);
		  xmlhttp.send();

  }
  function cancelDelete()
  {
      document.getElementById("DeleteMessage").innerHTML= "";


  }
 </script>
</head>
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.form.js"></script> 
<script>
  $(document).ready(function() 
{ 
    $('#form1').ajaxForm({target:'#SemesterExamDetails'}); 
}); 
</script>
<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Courses</a> <a href="${pageContext.request.contextPath}/Admin/Courses/Semester_Exam.jsp" class="current">Semester Exam</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Semester Exam</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Semester Exam</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" id="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Semester_Exam">
			            <div class="control-group">
						  <label class="control-label">School Name</label>
			              <div class="controls">	              										
				           	<select name="School" id="School" class="span11" onChange="showYears(this.value)" required>
				 			 <option value=""> 
							  	Select School
							  </option>
							  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
						       	<option value="${School_id}"
						       	   <c:if test="${Student_Details.school_id==School_id}">
					       			selected
					       		   </c:if>
						       	><c:out value='${Schools.school_Name[position.index]}'/></option>
						      </c:forEach>	  
							</select> 
						 </div>
			            </div>
						<div class="control-group">
						  <label class="control-label">Academic Year Name</label>
			              <div class="controls">					 
							<select id="Year_id" name="Year_id" class="span11" onChange="showGrades(School.value, this.value)" required >
								  <option value="" > 
								  	Select Year
								  </option>
							</select>
	 					  </div>
	 					</div>
						<div class="control-group">
						  <label class="control-label">Grade Name</label>
			              <div class="controls">						 
					        <select name="Grade" id="Grade" class="span11" onChange="showSemesters(Year_id.value, this.value)" required>
					 			 <option value="">Select Grade</option>
					 		</select> 
	 					  </div>
	 					</div>		 						            					      
						<div class="control-group">
						  <label class="control-label">Semester Name</label>
			              <div class="controls">						 
					        <select name="Semester_id" id="Semester_id" class="span11" onChange="showCourses(this.value, Grade.value)" required>
					 			 <option value="">Select Semester</option>
					 		</select>
	 					  </div>
	 					</div>
						<div class="control-group">
						  <label class="control-label">Course Name</label>
			              <div class="controls">						 
					        <select name="Course" id="Course" class="span11" onChange="showSemesterExamDetails(this.value, Semester_id.value)" required>
					 			 <option value="">Select Course</option>
					 		</select> 
	 					  </div>
	 					</div>	 					
				      <div id="SemesterExamDetails" ></div>      
				      <div id="DeleteMessage" ></div> 
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
				The semester exam is an exam given in a specific course linked to one of the semesters that you have already created.    
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