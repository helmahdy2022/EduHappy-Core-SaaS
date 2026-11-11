<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Edit Course</title>

<script>
 function showGrades(str)
 {
 if (str=="")
   {
	 document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("CourseDetails").innerHTML="";
	 document.getElementById("AuthorityDetails").innerHTML="Here it will list the teachers who are assigned to teach this course per class.<br> It will also list the teachers who are responsible to upload content and material related to this course.";
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
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("CourseDetails").innerHTML="";
	 document.getElementById("AuthorityDetails").innerHTML="Here it will list the teachers who are assigned to teach this course per class.<br> It will also list the teachers who are responsible to upload content and material related to this course.";
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 // end of ShowGrade function
  function showCourses(str)
 {
 if (str=="")
   {
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("CourseDetails").innerHTML="";
	 document.getElementById("AuthorityDetails").innerHTML="Here it will list the teachers who are assigned to teach this course per class.<br> It will also list the teachers who are responsible to upload content and material related to this course.";
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
     document.getElementById("CourseDetails").innerHTML="";
	 document.getElementById("AuthorityDetails").innerHTML="Here it will list the teachers who are assigned to teach this course per class.<br> It will also list the teachers who are responsible to upload content and material related to this course.";
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Courses?Grade_id="+str,true);
 xmlhttp.send();
 }
 //==========End of showCourses function=================
  function showCourseDetails(Course_id, Grade_id)
 {
document.getElementById("CourseDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>"; 
document.getElementById("AuthorityDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Course_id=="")
   {
   document.getElementById("CourseDetails").innerHTML="";
   document.getElementById("AuthorityDetails").innerHTML="Here it will list the teachers who are assigned to teach this course per class.<br> It will also list the teachers who are responsible to upload content and material related to this course.";
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
     document.getElementById("CourseDetails").innerHTML=xmlhttp.responseText;
	 document.getElementById("AuthorityDetails").innerHTML="Here it will list the teachers who are assigned to teach this course per class.<br> It will also list the teachers who are responsible to upload content and material related to this course.";
	 document.getElementById("DeleteMessage").innerHTML="";
     showAuthority(Course_id, Grade_id)
     //document.getElementById("OneSession").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Course_Details?Course_id="+Course_id+"&Grade_id="+Grade_id,true);
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
  function showAuthority(Course_id, Grade_id)
  {
  if (Grade_id==""||Course_id=="")
    {
		 document.getElementById("AuthorityDetails").innerHTML="Here it will list the teachers who are assigned to teach this course per class.<br> It will also list the teachers who are responsible to upload content and material related to this course.";
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
      document.getElementById("AuthorityDetails").innerHTML=xmlhttp.responseText;
      }
    };
  xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Authority_Details?Grade_id="+Grade_id+"&Course_id="+Course_id,true);
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
  
  function changeDisplayReport()
  {
	  if(document.getElementById("Contribute").checked==false)
		  {
		  	document.getElementById("Report_Display").disabled = false;
		  }
	  else
		  {
		  	document.getElementById("Report_Display").disabled = true;
	  		document.getElementById("Report_Display").checked=true;
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Courses</a> <a href="${pageContext.request.contextPath}/Admin/Courses/Edit_Course.jsp" class="current">Edit Course</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Edit Course</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Edit Course</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Course" onsubmit="return checkAttGradeIntegrity(Attendance_Grade.value, Present_Grade.value, Absent_Grade.value, Late_Grade.value, Excused_Grade.value);">    				
			            <div class="control-group">
						  <label class="control-label">School Name</label>
			              <div class="controls">	               	
				           	<select name="School" id="School" class="span11" onChange="showGrades(this.value)" required>
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
					         <select name="Grade" id="Grade" class="span11" onChange="showCourses(this.value)" required>
					 			 <option value="">Select Grade</option>
					 		</select>					 		  						 
	 					  </div>
	 					</div>	
						<div class="control-group">
						  <label class="control-label">Course Name</label>
			              <div class="controls">
					         <select name="Course" id="Course" class="span11" onChange="showCourseDetails(this.value, Grade.value)" required>
					 			 <option value="">Select Course</option>
					 		</select>					 		  						 
	 					  </div>
	 					</div>	 						            					      
					     <div id="CourseDetails" ></div>
					     <div id="DeleteMessage" ></div>  
					  <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>
		   <div  class="span6">
		   	<div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Authority Details</h5>
		        </div>
				<div id="AuthorityDetails" >
				Here it will list the teachers who are assigned to teach this course per class.<br> It will also list the teachers who are responsible to upload content and material related to this course.
				</div>
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