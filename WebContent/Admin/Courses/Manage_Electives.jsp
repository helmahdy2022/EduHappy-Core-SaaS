<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Manage Elective Course</title>

<script>
 function showGrades(str)
 {
 if (str=="")
   {
	 document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("ElectiveDetails").innerHTML= "";
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
	 document.getElementById("ElectiveDetails").innerHTML= "";
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 // end of ShowGrade function
  function showElectiveCourses(str)
 {
 if (str=="")
   {
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("ElectiveDetails").innerHTML= "";
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
	 document.getElementById("ElectiveDetails").innerHTML= "";
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Elective_Courses?Grade_id="+str,true);
 xmlhttp.send();
 }
 //==========End of showCourses function=================

  function showElectiveDetails(Course_id)
 {
document.getElementById("ElectiveDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Course_id=="")
   {
   document.getElementById("ElectiveDetails").innerHTML="";
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
     document.getElementById("ElectiveDetails").innerHTML=xmlhttp.responseText;
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Elective_Details?Course_id="+Course_id,true);
 xmlhttp.send();
 }
  function removeFromElective(Student_id, Course_id)
  {
  if (Student_id=="" && Course_id=="")
    {
    document.getElementById("Student_Elective"+Student_id+Course_id).innerHTML="";
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
      //document.getElementById("Student_Group").innerHTML=xmlhttp.responseText;
      document.getElementById("Student_Elective"+Student_id+Course_id).innerHTML="";
 	 document.getElementById("DeleteMessage").innerHTML="";
      }
    };
 var confirmed;
 confirmed = confirm('You are about to remove the student from the Elective Course, please click OK button to proceed or otherwise click Cancel');

 if(confirmed==true)
	 {
	 	 xmlhttp.open("GET","${pageContext.request.contextPath}/Manage_Student_Electives?RemoveFromElective=on&Student_id="+Student_id+"&Course="+Course_id,true);
	 	 xmlhttp.send(); 
	 }
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

<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Courses</a> <a href="${pageContext.request.contextPath}/Admin/Courses/Manage_Electives.jsp" class="current">Manage Elective Course</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Manage Elective Course</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Manage Elective Course</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Elective">
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
					        <select name="Grade" id="Grade" class="span11" onChange="showElectiveCourses(this.value)" required>
					 			 <option value="">Select Grade</option>
					 		</select> 
	 					  </div>
	 					</div>	
						<div class="control-group">
						  <label class="control-label">Elective Course Name</label>
			              <div class="controls">					 
					        <select name="Course" id="Course" class="span11" onChange="showElectiveDetails(this.value)" required>
					 			 <option value="">Select Course</option>
					 		</select> 
	 					  </div>
	 					</div>	 						            					      
       				  <div id="ElectiveDetails"></div>        
				      <div id="DeleteMessage" ></div> 
					  <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
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