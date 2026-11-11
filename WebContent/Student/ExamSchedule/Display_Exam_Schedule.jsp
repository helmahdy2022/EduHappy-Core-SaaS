<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<c:forEach items="${ExamScheduleDetails}" var="ExamScheduleDetails" begin="0" end="0">
		<c:set var="Exam_Name" value="${ExamScheduleDetails[1]}"/>	
		<c:set var="DisplayHideExamSchedule" value="${ExamScheduleDetails[6]}"/>			
</c:forEach>
<c:choose>
<c:when test="${param.Source=='Parent'}"> 
<html>
<head>
<meta charset="utf-8">
<title>Exam Schedule</title>
</head>


<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300">

  <!-- For Demo Purposes - Theme Settings Pane -->
	<%@include file="../includes/Theme_Settings.jsp" %>
  <!-- End: Theme Settings Pane -->
  <div id="main">

	<%@include file="../includes/ParentMenu.jsp" %> 
	<%@include file="../includes/ParentLeft.jsp" %>


     <section id="content_wrapper">

      <!-- Start: Topbar-Dropdown -->
	<%@include file="../includes/Topbar_Dropdown.jsp" %>
      <!-- End: Topbar-Dropdown -->  
      <!-- Start: Topbar -->

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
            <li class="crumb-trail">Exam Schedule</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/ExamSchedule/Initiate_Exam_Schedule.jsp?Student_id=${param.Student_id}">Exam Schedule</a>
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
                <span class="fa fa-book"></span>Exam Schedule</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Exam Schedule</code>
              </div>
            </div>
			<c:choose>
			<c:when test="${DisplayHideExamSchedule==1}">
				<table class="table table-striped table-hover" id="datatable2" cellspacing="0" width="100%">
				<tr>
				<td colspan="3">
				<div style="font: 25px/1.4 Times New Roman, 'Helvetica Neue', Helvetica, Arial, sans-serif;"><c:out value='${Exam_Name}'/></div>
				</td>
				</tr>
				<tr>
				<td colspan="3">
					<c:forEach items="${Courses.course_id}" var="Course_id" varStatus="position">
						<c:forEach items="${ExamScheduleDetails}" var="ExamScheduleDetails">
							<c:if test="${ExamScheduleDetails[2]==Course_id}">
								<c:set var="Course_Name" value="${Courses.course_Name[position.index]}"/>				
							</c:if>
						</c:forEach>
					</c:forEach>		
					
					<table class="table table-striped table-hover" id="datatable2" cellspacing="0" width="100%">
					<tr>
					<td><label>Course</label></td>
					<td><label>Date</label></td>
					<td><label>Time From</label></td>
					<td><label>Time To</label></td>	
					</tr>
					<c:forEach items="${ExamScheduleDetails}" var="ExamScheduleDetails">
						<c:forEach items="${Courses.course_id}" var="Course_id" varStatus="position">
								<c:if test="${ExamScheduleDetails[2]==Course_id}">
									<c:set var="Course_Name" value="${Courses.course_Name[position.index]}"/>				
								</c:if>
						</c:forEach>
						<tr>
						<td><c:out value='${Course_Name}'/></td>
						<td><c:out value='${ExamScheduleDetails[3]}'/></td>
						<td><c:out value='${ExamScheduleDetails[4]}'/></td>
						<td><c:out value='${ExamScheduleDetails[5]}'/></td>		
						</tr>
					</c:forEach>
					</table>	
				</td>
				</tr>
				<tr>
				</tr>
				</table>
			</c:when>
			<c:otherwise>
			There is no Exam Schedule to be displayed at the moment. Please check again later.
			</c:otherwise>
			</c:choose>
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
 </c:when>
 <c:otherwise>
 			<c:choose>
			<c:when test="${DisplayHideExamSchedule==1}">
				<table class="table table-striped table-hover" id="datatable2" cellspacing="0" width="100%">
				<tr>
				<td colspan="3">
				<div style="font: 25px/1.4 Times New Roman, 'Helvetica Neue', Helvetica, Arial, sans-serif;"><c:out value='${Exam_Name}'/></div>
				</td>
				</tr>
				<tr>
				<td colspan="3">
					<c:forEach items="${Courses.course_id}" var="Course_id" varStatus="position">
						<c:forEach items="${ExamScheduleDetails}" var="ExamScheduleDetails">
							<c:if test="${ExamScheduleDetails[2]==Course_id}">
								<c:set var="Course_Name" value="${Courses.course_Name[position.index]}"/>				
							</c:if>
						</c:forEach>
					</c:forEach>		
					
					<table class="table table-striped table-hover" id="datatable2" cellspacing="0" width="100%">
					<tr>
					<td><label>Course</label></td>
					<td><label>Date</label></td>
					<td><label>Time From</label></td>
					<td><label>Time To</label></td>	
					</tr>
					<c:forEach items="${ExamScheduleDetails}" var="ExamScheduleDetails">
						<c:forEach items="${Courses.course_id}" var="Course_id" varStatus="position">
								<c:if test="${ExamScheduleDetails[2]==Course_id}">
									<c:set var="Course_Name" value="${Courses.course_Name[position.index]}"/>				
								</c:if>
						</c:forEach>
						<tr>
						<td><c:out value='${Course_Name}'/></td>
						<td><c:out value='${ExamScheduleDetails[3]}'/></td>
						<td><c:out value='${ExamScheduleDetails[4]}'/></td>
						<td><c:out value='${ExamScheduleDetails[5]}'/></td>		
						</tr>
					</c:forEach>
					</table>	
				</td>
				</tr>
				<tr>
				</tr>
				</table>
			</c:when>
			<c:otherwise>
			There is no Exam Schedule to be displayed at the moment. Please check again later.
			</c:otherwise>
			</c:choose>
 </c:otherwise>
 </c:choose>