<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Student Attendance</title>
</head>


<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300">

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
            <li class="crumb-trail">Attendance Report</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Attendance/Initiate_Attendance_Summary.jsp?Student_id=${param.Student_id}">Attendance Report</a>
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
            <li class="crumb-trail">Course Content</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Attendance/Initiate_Attendance_Summary.jsp">Course Content</a>
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
	          <h4>Student Attendance</h4>
	          <hr class="alt short">
  	          <h4>
	             <c:forEach items="${child.student_id}" var="Student_id"  varStatus="position">
					<c:if test="${Student_id == param.Student_id}">	             
	                  	 <font style="color:blue"><c:out value='${child.first_Name[position.index]}'/>&nbsp;<c:out value='${child.last_Name[position.index]}'/></font>		             			
					</c:if>
	             </c:forEach>
	          </h4>
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="fa fa-book"></span>My Attendance</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Student Attendance Summary Report</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
					<c:choose>
					 <c:when test="${not empty Attendance_Summary.attendanceSummary}">
					  <table class="table table-bordered mbn">
			      		<thead>	
						<tr>
						<th></th>
						<th>Course</th>
						<th>Sessions Completed</th>
						<th>Present</th>						
						<th>Absent</th>
						<th>Excused</th>
						<th>Late</th>			
						</tr>
						</thead>
						<c:forEach items="${Attendance_Summary.attendanceSummary}" var="Attendance_Summary" >
						<c:if test="${Attendance_Summary[0]!=null}">	
						<tr>
						<td style="width:20px;"><i class="fa fa-circle text-warning fs8 pr15"></i></td>						
						<td><c:out value="${Attendance_Summary[1]}"/></td>
						<td><c:out value="${Attendance_Summary[2]}"/></td>
						<td><c:out value="${Attendance_Summary[3]}"/></td>
						<td><c:out value="${Attendance_Summary[4]}"/></td>
						<td><c:out value="${Attendance_Summary[5]}"/></td>
						<td><c:out value="${Attendance_Summary[6]}"/></td>			
						</tr>
						</c:if>
						</c:forEach>
					</table>		
				  </c:when>
				  <c:otherwise>
			  		<div class="message">
						Attendance report is not available at the moment. Please try again later.		
			  		</div>		
				  </c:otherwise>
				 </c:choose>
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