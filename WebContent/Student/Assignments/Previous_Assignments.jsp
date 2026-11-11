<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Previous Assignments</title>

<script>
function getAllLessons(Course_id)
{
if (Course_id=="")
  {
	   document.getElementById("LessonsList").innerHTML="<select><option >Select Lesson</option></select>";
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
    document.getElementById("LessonsList").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Lessons_List?Course_id="+Course_id,true);
xmlhttp.send();
}
function getPreviousAssignments(Course_id, Lesson_id, Date_From, Date_To, Student_id)
{
if (Lesson_id==""&&(Date_From==""||Date_To==""))
  {
	   document.getElementById("AssignmentsList").innerHTML="";
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
    document.getElementById("AssignmentsList").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Previous_Assignments?Course_id="+Course_id+"&Lesson_id="+Lesson_id+"&Date_From="+Date_From+"&Date_To="+Date_To+"&Student_id="+Student_id,true);
xmlhttp.send();
}
function showMore()
{
	document.getElementById('fullText').style.display='block';
	document.getElementById('breifText').style.display='none';
}
function showLess()
{
	document.getElementById('fullText').style.display='none';
	document.getElementById('breifText').style.display='block'
}
</script>
</head>


<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300" onLoad="getAllLessons(${param.Course_id});">

  <!-- For Demo Purposes - Theme Settings Pane -->
	<%@include file="../includes/Theme_Settings.jsp" %>
  <!-- End: Theme Settings Pane -->
  <div id="main">
	 <c:choose>
		<c:when test="${param.Source=='Parent'}">
			<%@include file="../includes/ParentMenu.jsp" %> 
			<%@include file="../includes/ParentLeft.jsp" %>
		</c:when>
		<c:otherwise>
			<%@include file="../includes/StudentMenu.jsp" %> 
			<%@include file="../includes/StudentLeft.jsp" %>
		</c:otherwise>
	 </c:choose>
     <section id="content_wrapper">

      <!-- Start: Topbar-Dropdown -->
	<%@include file="../includes/Topbar_Dropdown.jsp" %>
      <!-- End: Topbar-Dropdown -->  
      <!-- Start: Topbar -->
	 <c:choose>
		<c:when test="${param.Source=='Parent'}">
      <header id="topbar">
        <div class="topbar-left">
          <ol class="breadcrumb">
            <li class="crumb-active">
              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">Dashboard</a>
            </li>
            <li class="crumb-icon">
              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">
                <span class="glyphicon glyphicon-home"></span>
              </a>
            </li>
            <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">Home</a>
            </li>
            <li class="crumb-trail">Student's Assignments</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Assignments/Initiate_Assignments.jsp?Student_id=${param.Student_id}">Assignments</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
		</c:when>
		<c:otherwise>
      <header id="topbar">
        <div class="topbar-left">
          <ol class="breadcrumb">
            <li class="crumb-active">
              <a href="${pageContext.request.contextPath}/Student/Student_Main.jsp">Dashboard</a>
            </li>
            <li class="crumb-icon">
              <a href="${pageContext.request.contextPath}/Student/Student_Main.jsp">
                <span class="glyphicon glyphicon-home"></span>
              </a>
            </li>
            <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Student_Main.jsp">Home</a>
            </li>
            <li class="crumb-trail">Student's Assignments</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Assignments/Initiate_Assignments.jsp">Assignments</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
		</c:otherwise>
	 </c:choose>
      <!-- End: Topbar -->
 	   <%@include file="../includes/Adsense_Top.jsp" %>
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
	          <h4>Student Assignments</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="glyphicon glyphicon-tasks"></span>Previous Assignments</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Student Assignments</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
				  <table class="table table-bordered mbn">
			     	<thead>
			    		<tr><th colspan="7">Previous Assignments</th></tr>
			  		</thead>
					   <tr>
					  <td>
						 <div><c:out value="${param.Course_Name} ${param.Group_Name}"/></div>
					  </td>
					  <td style="text-align:right;">

					 <c:choose>
						<c:when test="${param.Source=='Parent'}">
					  <a href="${pageContext.request.contextPath}/Get_Assignments?Student_id=${param.Student_id}" onClick="checkParentSession();">Back to Current Assignments</a>
						</c:when>
						<c:otherwise>
					  <a href="${pageContext.request.contextPath}/Get_Assignments" onClick="checkSession();">Back to Current Assignments</a>
						</c:otherwise>
					 </c:choose>
					  </td>
					   </tr>
			
					  <tr>
					  <td rowspan="2">
					  </td>
					   </tr>
					  <tr>
					  <td>
						  <table class="table table-bordered mbn">
						  <thead>
							<tr><th colspan="4"><span class="fa fa-search"></span> Search Previous Assignments</th></tr>
						  </thead>
						  <tr><td colspan="4"><label>Search by Date</label></td></tr>
						  <tr>
						  <td style="width:40%">
						  <label>From</label> <input type="Date" class="form-control" name="Date_From" id="Date_From">
						  </td>
						  <td style="width:40%">
						  <label>To</label> <input type="Date" class="form-control" name="Date_To" id="Date_To">
						  </td>
						  </tr>
						  <tr><td colspan="4" style="text-align:center;">AND / OR</td></tr>
						  <tr>
						  <td colspan="4">
						 	<label>Search by Lesson</label>
							<div id="LessonsList" style="text-align:center;margin-top:10px;"><img src='${pageContext.request.contextPath}/images/Spinner.gif'></div>
						  </td>
						  </tr>
						  <tr>
						  <td colspan="4">
						  <div class="buttonHolder">
						  <c:choose>
							<c:when test="${param.Source=='Parent'}">
						  		<input type="button" value="Search" class="btn btn-info mr10" onClick="getPreviousAssignments(${param.Course_id}, Lesson_id.value, Date_From.value, Date_To.value, ${param.Student_id})">
							</c:when>
							<c:otherwise>
						  		<input type="button" value="Search" class="btn btn-info mr10" onClick="getPreviousAssignments(${param.Course_id}, Lesson_id.value, Date_From.value, Date_To.value, 0)">
							</c:otherwise>
						  </c:choose>					  						  
						  </div>		  
						  </td>
						  </tr>
						  </table>	
					  </td>
					  </tr>
					  <tr>
					  <td colspan="2">
						<div id="AssignmentsList"></div>							
					  </td>
					   </tr>	
			 	  </table>		
                </div>
              </div>
            </div>
          </div>	
	        </div>
       </section>  
      <!-- End: Content -->
	  <%@include file="../includes/Adsense_Bottom.jsp" %>
      <!-- Begin: Page Scripts -->

		<%@include file="../includes/Page_Scripts.jsp" %>

      <!-- End: Page Scripts -->            
     </section>
    <!-- Start: Right Sidebar -->
			<%@include file="../includes/Topbar_Right_Content.jsp" %>
    <!-- End: Right Sidebar -->
  </div>
  </body>
</html>