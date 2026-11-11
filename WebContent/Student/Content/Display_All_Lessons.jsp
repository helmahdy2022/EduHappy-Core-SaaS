<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Course Lessons</title>
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
            <li class="crumb-trail">Course Content</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Content/Initiate_Course_Content.jsp?Student_id=${param.Student_id}">Course Content</a>
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
              <a href="${pageContext.request.contextPath}/Student/Content/Initiate_Course_Content.jsp">Course Content</a>
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
	          <h4>Course Content</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="fa fa-book"></span>All Lessons</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Course Lessons</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
					  <table class="table table-bordered mbn">
				      	<thead>
					      <tr><th colspan="5">Course Lessons</th></tr>
					    </thead>		
						<c:forEach items="${Course_Structure.course_Structure_List}" var="Course_Structure_List" begin="0" end="0">
						<c:set var="Course_Name" value="${Course_Structure_List[1]}"/>
						</c:forEach>
					  	<tr>		
					 	  <td class="contentCourse"><c:out value="${Course_Name}"/></td>
					      <td class="contentLessonInProgress">All Lessons</td>
					      <td class="contentAllLessons">
						 	<c:choose>
								<c:when test="${param.Source=='Parent'}">
					      			<a href="${pageContext.request.contextPath}/Student/Content/Initiate_Course_Content.jsp?Student_id=${param.Student_id}">Back to Course Content</a>
								</c:when>
								<c:otherwise>
					      			<a href="${pageContext.request.contextPath}/Student/Content/Initiate_Course_Content.jsp">Back to Course Content</a>
								</c:otherwise>
							 </c:choose>					      					      
					      </td>
					    </tr>
					  	<tr>
						  <td></td>
						  <td colspan="2">
							<c:choose>
							<c:when test="${not empty Course_Structure.course_Structure_List}">
								<table class="table table-striped table-hover" id="datatable2" cellspacing="0" width="100%">
								<tr>
								<td>
								<label>Unit Name</label>
								</td>
								<td>
								<label>Lesson Name</label>
								</td>
								<td >
								<label>Lesson Start</label>
								</td>
								<td>
								<label>Lesson End</label>
								</td>					
								</tr>
								<c:forEach items="${Course_Structure.course_Structure_List}" var="Course_Structure_List">
								
								<c:if test="${Course_Structure_List[2]!=Unit_id}">
								<tr>
								<td>
								<c:out value="${Course_Structure_List[3]}"/>
								</td>
								<td colspan="3"></td>
								</tr>
								</c:if>
								<tr>
								<td></td>
								<td>

							 	<c:choose>
									<c:when test="${param.Source=='Parent'}">
										<a href="${pageContext.request.contextPath}/Get_Lesson_Details?User=Student&&Course_id=${Course_Structure_List[0]}&&Course_Name=${Course_Name}&&Lesson_id=${Course_Structure_List[4]}&&Student_id=${param.Student_id}" onClick="checkParentSession();"><c:out value="${Course_Structure_List[5]}"/></a>									
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/Get_Lesson_Details?User=Student&&Course_id=${Course_Structure_List[0]}&&Course_Name=${Course_Name}&&Lesson_id=${Course_Structure_List[4]}" onClick="checkSession();"><c:out value="${Course_Structure_List[5]}"/></a>
									</c:otherwise>
								 </c:choose>
								</td>
								<td>
								<font color="#834099"><b><c:out value="${Course_Structure_List[6]}"/></b></font>
								</td>
								<td>
								<font color="#834099"><b><c:out value="${Course_Structure_List[7]}"/></b></font>
								</td>
								</tr>					
								<c:set var="Unit_id" value="${Course_Structure_List[2]}"/>
								</c:forEach>
								</table>				
							</c:when>
							<c:otherwise>
								<div style="margin-left:400px;"><c:out value="There are no lessons to display"/></div>
							</c:otherwise>
							</c:choose>
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