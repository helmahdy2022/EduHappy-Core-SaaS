<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>New Course</title>
<script>
 function showGrades(str)
 {
 if (str=="")
   {
	 document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
	 //document.getElementById("Subject").innerHTML="<select><option >Select Subject</option></select>";
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
     //document.getElementById("Subject").innerHTML="<select><option >Select Subject</option></select>";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 
 function checkGradeIntegrity(Status_Grade,Attendance_Grade, order)
 {
	if(parseInt(Status_Grade)>parseInt(Attendance_Grade))
		{
			document.getElementById("errorMessage"+order).innerHTML="<font style='font:11px/1 Helvetica, Arial, sans-serif;color:red;'>Must be less than or equal Attendance Grade</font>";
		}
	else
		{
			document.getElementById("errorMessage"+order).innerHTML="";
			if(order==1)
				document.getElementById("errorMessagePresent").innerHTML="";
			if(order==2)
				document.getElementById("errorMessageAbsent").innerHTML="";
			if(order==3)
				document.getElementById("errorMessageLate").innerHTML="";
			if(order==4)
				document.getElementById("errorMessageExcused").innerHTML="";
		}
 }
function checkAttGradeIntegrity(Attendance_Grade, Present, Absent, Late, Excused)
{
	if(parseInt(Attendance_Grade)<parseInt(Present))
		{
			document.getElementById("errorMessagePresent").innerHTML="<font style='font:11px/1 Helvetica, Arial, sans-serif;color:red;'>Must be higher than or equal Presnt Grade</font>";
			submit1=0;
			//return false;
		}
	else
		{
			document.getElementById("errorMessagePresent").innerHTML="";
			document.getElementById("errorMessage1").innerHTML="";
			submit1=1;
		}
	if(parseInt(Attendance_Grade)<parseInt(Absent))
		{
			document.getElementById("errorMessageAbsent").innerHTML="<font style='font:11px/1 Helvetica, Arial, sans-serif;color:red;'>Must be higher than or equal Absent Grade</font>";
			submit2=0;
		}
	else
		{
			document.getElementById("errorMessageAbsent").innerHTML="";
			document.getElementById("errorMessage2").innerHTML="";
			submit2=1;
		}
	if(parseInt(Attendance_Grade)<parseInt(Late))
		{
			document.getElementById("errorMessageLate").innerHTML="<font style='font:11px/1 Helvetica, Arial, sans-serif;color:red;'>Must be higher than or equal Late Grade</font>";
			//return false;
			submit3=0;
		}
	else
		{
			document.getElementById("errorMessageLate").innerHTML="";
			document.getElementById("errorMessage3").innerHTML="";
			submit3=1;
		}
	if(parseInt(Attendance_Grade)<parseInt(Excused))
		{
			document.getElementById("errorMessageExcused").innerHTML="<font style='font:11px/1 Helvetica, Arial, sans-serif;color:red;'>Must be higher than or equal Excused Grade</font>";
			//return false;
			submit4=0;
		}
	else
		{
			document.getElementById("errorMessageExcused").innerHTML="";
			document.getElementById("errorMessage4").innerHTML="";
			submit4=1;
		}	
	if(submit1==0 || submit2==0 || submit3==0 || submit4==0)
		{
			return false;
		}
	else
		{
			return true;
		}
}
 // end of ShowGrade function
 
/*    function showSubjects(str)
 {
 if (str=="")
   {
	 document.getElementById("Subject").innerHTML="<select><option >Select Subject</option></select>";
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
     document.getElementById("Subject").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Subjects?Grade_id="+str,true);
 xmlhttp.send();
 } */
   function changeDisplayReport()
   {
 	  if(document.getElementById("Contribute").checked==false)
 		  {
 		  	document.getElementById("Report_Display").disabled = false;
 		  }
 	  else
 		  {
 		 	document.getElementById("Report_Display").checked=true;	
 		  	document.getElementById("Report_Display").disabled = true;
 	  		
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Grades</a> <a href="${pageContext.request.contextPath}/Admin/Courses/New_Course.jsp" class="current">New Course</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>New Course</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Course</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Course" onsubmit="return checkAttGradeIntegrity(Attendance_Grade.value, Present_Grade.value, Absent_Grade.value, Late_Grade.value, Excused_Grade.value);">

   		            <div class="control-group">
		              <label class="control-label">School Name*</label>
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
		              <label class="control-label">Grade Name *</label>
		              <div class="controls">
				         <select name="Grade" class="span11" id="Grade" required>
				 			 <option value="">Select Grade</option>
				 		 </select>		                
		              </div>		              
		            </div>	
		            <div class="control-group">
		              <label class="control-label">Course Name *</label>
		              <div class="controls">
          				<input type="text" name="Course_Name" id="Course_Name" class="span11" required maxlength="50">		                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Course Points *</label>
		              <div class="controls">
          				<input name="Course_Grade" type="number" id="Course_Grade" class="span11" min="0" max="1000000000" required>	                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Attendance Weight *</label>
		              <div class="controls">
						<input name="Attendance_Weight" type="number" id="Attendance_Weight"  class="span11" min="0" max="1000000000" required>           						                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Attendance Points *</label>
		              <div class="controls">
						<input name="Attendance_Grade" type="number" id="Attendance_Grade"  class="span11" min="0" required onfocusout="checkAttGradeIntegrity(this.value, Present_Grade.value, Absent_Grade.value, Late_Grade.value, Excused_Grade.value)">						           						                
				    	<div id="errorMessagePresent"></div>
				    	<div id="errorMessageAbsent"></div>
				    	<div id="errorMessageLate"></div>
				    	<div id="errorMessageExcused"></div>
		              </div>
		            </div>	
		            <div class="control-group">
		              <label class="control-label">Present Points *</label>
		              <div class="controls">
						<input name="Present_Grade" type="number" id="Present_Grade" class="span11" min="0" max="1000000000" required onfocusout="checkGradeIntegrity(this.value,Attendance_Grade.value,1)">
   						<div id="errorMessage1"></div>
		              </div>
		            </div>		            	            
		            <div class="control-group">
		              <label class="control-label">Absent Points *</label>
		              <div class="controls">
						<input name="Absent_Grade" type="number" id="Absent_Grade"  class="span11" min="0" max="1000000000" required onfocusout="checkGradeIntegrity(this.value,Attendance_Grade.value,2)">
    					<div id="errorMessage2"></div>
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Late Points *</label>
		              <div class="controls">
						<input name="Late_Grade" type="number" id="Late_Grade" class="span11" min="0" max="1000000000" required onfocusout="checkGradeIntegrity(this.value,Attendance_Grade.value,3)">
   						<div id="errorMessage3"></div>
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Excused Points *</label>
		              <div class="controls">
 						<input name="Excused_Grade" type="number" id="Excused_Grade" class="span11" min="0" max="1000000000" required onfocusout="checkGradeIntegrity(this.value,Attendance_Grade.value,4)">
   						<div id="errorMessage4"></div>
		              </div>
		            </div>	
					<c:if test="${Parent_Account_Details.account_id=='258'}">  
		            <div class="control-group">
		              <label class="control-label">Price</label>
		              <div class="controls">
 						<input name="Price" type="number" id="Price" class="span11" min="0" max="1000000000">
		              </div>
		            </div>	
		            </c:if>
		            <div class="control-group">
		              <label class="control-label">Course is Elective</label>
		              <div class="controls">
		                <label>
		                  <input type="radio" name="Elective" value="1" />
		                  Yes</label>
		                <label>
		                  <input type="radio" name="Elective" value="0" checked />
		                  No</label>
		              </div>
		            </div>	
		            <div class="control-group">
		              <label class="control-label">Contribute to final grade</label>
		              <div class="controls">
		 				  <input name="Contribute" type="checkbox" id="Contribute" checked onChange="changeDisplayReport();">
		              </div>
		            </div>	
		            <div class="control-group">
		              <label class="control-label">Display in the Grading Report</label>
		              <div class="controls">
						  <input name="Report_Display" type="checkbox" id="Report_Display"  Checked disabled >
		              </div>
		            </div>		            	            	            	            		            
		            <div class="form-actions">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
	            	<div class="requiredSignNote"><label>Fields marked (*) are required</label></div>
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
				 The course is the subject that is led by the teachers, e.g. English, Math, Science...etc.
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