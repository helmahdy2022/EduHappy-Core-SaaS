<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Exam Schedule</title>

<script>
 function showGrades(str)
 {
 if (str=="")
   {
   document.getElementById("Grade").innerHTML="<option>Select Grade</option>";
   document.getElementById("ExamScheduleDetails").innerHTML="";
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
	   document.getElementById("ExamScheduleDetails").innerHTML="";
	   document.getElementById("Grade").innerHTML=xmlhttp.responseText;
	   document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 // end of ShowGrade function
 
  function showGradeDetails(Grade_id)
 {
 document.getElementById("ExamScheduleDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
 if (Grade_id=="")
   {
   document.getElementById("ExamScheduleDetails").innerHTML="";
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
		     document.getElementById("ExamScheduleDetails").innerHTML=xmlhttp.responseText;
		     $('body').updatePolyfill();
	     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Exam_Schedule?Grade_id="+Grade_id,true);
 xmlhttp.send();
 }
  
  function updateRequired(Course_id)
  {
	  	var Exam_Date = document.getElementById("Original_Exam_Date"+Course_id).value;
	  	var Time_From = document.getElementById("Original_Time_From"+Course_id).value;
	  	var Time_To   = document.getElementById("Original_Time_To"+Course_id).value;
	  if(document.getElementById("Course_Required"+Course_id).checked==false)
		  {
		  	document.getElementById("Exam_Date"+Course_id).required = false;
		  	document.getElementById("Time_From"+Course_id).required = false;
		  	document.getElementById("Time_To"+Course_id).required = false;
		  	document.getElementById("Exam_Date"+Course_id).value=false;
		  	document.getElementById("Time_From"+Course_id).value="";
		  	document.getElementById("Time_To"+Course_id).value="";
		  }
	  else
		  {
		  	document.getElementById("Exam_Date"+Course_id).required = true;
		  	document.getElementById("Time_From"+Course_id).required = true;
		  	document.getElementById("Time_To"+Course_id).required = true;
		  	document.getElementById("Exam_Date"+Course_id).value=Exam_Date;
		  	document.getElementById("Time_From"+Course_id).value=Time_From;
		  	document.getElementById("Time_To"+Course_id).value=Time_To;
		  }
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Grades</a> <a href="${pageContext.request.contextPath}/Admin/Grades/Exam_Schedule.jsp" class="current">Exam Schedule</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Exam Schedule</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Exam Schedule</h5>
		        </div>
		        <div class="widget-content nopadding">
					<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Exam_Schedule">
		            <div class="control-group">
					  <label class="control-label">School Name</label>
		              <div class="controls">						              
			           	<select name="School" id="School"  class="span11" onChange="showGrades(this.value)" required>
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
					  <label class="control-label">Grade Name</label>
		              <div class="controls"> 						
				        <select name="Grade" id="Grade" class="span11" onChange="showGradeDetails(this.value)">
				 			 <option>Select Grade</option>
				 		</select> 
 					  </div>
 					</div>	
				      <div id="ExamScheduleDetails" ></div> 
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