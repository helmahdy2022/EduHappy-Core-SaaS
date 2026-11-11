<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Exam Schedule</title>

<script>
function getExamSchedule()
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
    document.getElementById("ExamSchedule").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Exam_Schedule",true);
xmlhttp.send();
}

</script>
</head>
<!-- <body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300" onLoad="getExamSchedule();">-->
<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300" <c:if test="${Source!='Parent'}">onLoad="getExamSchedule();"</c:if>>
  <!-- For Demo Purposes - Theme Settings Pane -->
	<%@include file="../includes/Theme_Settings.jsp" %>
  <!-- End: Theme Settings Pane -->
  <div id="main">
	<%@include file="../includes/StudentMenu.jsp" %> 
	<%@include file="../includes/StudentLeft.jsp" %>
     <section id="content_wrapper">

      <!-- Start: Topbar-Dropdown -->
	<%@include file="../includes/Topbar_Dropdown.jsp" %>
      <!-- End: Topbar-Dropdown -->  
      <!-- Start: Topbar -->
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
            <li class="crumb-trail">Exam Schedule</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/ExamSchedule/Exam_Schedule.jsp">Exam Schedule</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
	   <%@include file="../includes/Adsense_Top.jsp" %>
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
	          <h4>Exam Schedule</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="fa fa-book"></span>Exam Schedule</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Exam Schedule</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
				  <table class="table table-bordered mbn">	
					<tr>
					<td colspan="2">
						<div id="ExamSchedule" style="text-align:center;margin-top:10px;"><img src='${pageContext.request.contextPath}/images/Spinner.gif'></div>		
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