<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Course Content</title>
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
                <span class="fa fa-book"></span>Course Content</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Course Content</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
			<c:choose>
			<c:when test="${not empty Course_Content.courseList}">
			 <table class="table table-bordered mbn">
		      	<thead>
			      <tr><th colspan="5">Course Content</th></tr>
			    </thead>		
			 <c:forEach items="${Course_Content.courseList}" var="Course_List">
			  <c:if test="${Course_List[0]!=null}">	
			   <tr>		
			 	<td class="contentCourse"><c:out value="${Course_List[1]}"/></td>
			    <td class="contentLessonInProgress">Lessons In Progress</td>
			    <td class="contentAllLessons">
			 	<c:choose>
					<c:when test="${param.Source=='Parent'}">
			    		<a href="${pageContext.request.contextPath}/Get_All_Lessons?Course_id=${Course_List[0]}&&Student_id=${param.Student_id}" onClick="checkParentSession();">See All Lessons</a>
					</c:when>
					<c:otherwise>
			    		<a href="${pageContext.request.contextPath}/Get_All_Lessons?Course_id=${Course_List[0]}" onClick="checkSession();">See All Lessons</a>
					</c:otherwise>
				 </c:choose>			    	    
			    </td>
			   </tr>
			  <tr>
				<td style="vertical-align:top;">
				<div class="contentBook">eBooks</div>
			 	<table>
				 <c:forEach items="${Course_Content.courseContent}" var="Course_Books">
				 <c:if test="${Course_List[0]==Course_Books[0]}">	
				 <tr>
				 <td align="left">				 
				  <table class="table table-bordered mbn">
				  <tr>
					 <td colspan="2"><label>Book Name: </label> <c:out value="${Course_Books[2]}"/></td>
				  </tr>
				  <c:forEach items="${Course_Content.bookFiles}" var="bookFiles">
				   <c:if test="${bookFiles[0]==Course_Books[1]}">	
				    <tr id="Book_File${bookFiles[1]}">
				     <td>
				    	 <img src="${pageContext.request.contextPath}/images/File_Extensions/${bookFiles[5]}.jpg" width="20" height="20">
				    	 
				     </td>
				     <td>				     
				     	<a href="${pageContext.request.contextPath}/File_Download?File_id=${bookFiles[4]}&&Entity=Books&&EntityIdName=Book_id&&Entity_id=${bookFiles[0]}&&Course_id=${Course_Books[0]}" target="_blank"  class="fileLink"><c:out value="${bookFiles[3]}"/></a>
				     </td>
				   </tr>
				  </c:if>
				  </c:forEach>
				  </table>
			     </td>
			    </tr>
			 </c:if>
			 </c:forEach>
			 </table>	
			 </td>	
			 <td colspan="2">
			 <div class="contentLesson">Lessons</div>
			 <table class="table table-bordered mbn">
			 <c:forEach items="${Course_Content.courseLessons}" var="Course_Lessons">
			 <c:if test="${Course_List[0]==Course_Lessons[0]}">
			  <tr> 
			  <td><div><label>Lesson Name: </label></div><div> <c:out value="${Course_Lessons[2]}"/></div></td> 
			  </tr>
			  <tr>
			   <td>
				<div class="contentLessonStart">Start Date:</div> 
				<div class="contentLessonField"><c:out value="${Course_Lessons[3]}"/></div>
				<div class="contentLessonEnd"> End Date:</div> 
				<div class="contentLessonField"><c:out value="${Course_Lessons[4]}"/></div> 
			 </td> 		  
			  </tr>
			<c:if test="${fn:length(Course_Lessons[5])<255}">
			  <tr> 
			   <td colspan="2">
				<div><label>Description:</label></div>
				<div>
					<div style="white-space:pre-wrap;"><c:out value="${Course_Lessons[5]}" escapeXml="false"/></div>		
				</div>
			  </td> 
			 </tr>
			</c:if>	
			<c:set var="fileExists" value="No"/>
			<c:forEach items="${Course_Content.lessonFiles}" var="files">
			<c:if test="${files[0]==Course_Lessons[1]}">
			<c:set var="fileExists" value="Yes"/>
			</c:if>
			</c:forEach>
			<c:if test="${fileExists=='Yes'}">
				<tr>  
				    <td colspan="2">
					<div><label>Attached Files:</label></div></td>
				</tr>
				<c:set var="Lesson_id" value="${Course_Lessons[1]}"/>
				<c:forEach items="${Course_Content.lessonFiles}" var="lessonFiles">
				  <c:if test="${lessonFiles[0]==Course_Lessons[1]}">	
					 <tr id="Lesson_File${lessonFiles[1]}">
					     <td colspan="2">
					     <div>
					     <img src="${pageContext.request.contextPath}/images/File_Extensions/${lessonFiles[5]}.jpg" width="20" height="20">
					     <a href="${pageContext.request.contextPath}/File_Download?File_id=${lessonFiles[4]}&&Entity=Lessons&&EntityIdName=Lesson_id&&Entity_id=${lessonFiles[0]}&&Course_id=${Course_Lessons[0]}" target="_blank"  class="fileLink"><c:out value="${lessonFiles[3]}"/></a>
					     </div>
					     </td>
					</tr>
					</c:if>
				 </c:forEach>		
			</c:if>
				<tr>
				  <td>
				 	<c:choose>
						<c:when test="${param.Source=='Parent'}">
							<button class="btn btn-sm btn-success btn-block" style="width:200px;" onclick="window.location.href ='${pageContext.request.contextPath}/Get_Lesson_Details?User=Student&&Course_id=${Course_List[0]}&&Course_Name=${Course_List[1]}&&Lesson_id=${Course_Lessons[1]}&&Student_id=${param.Student_id}'; checkSession();">Go to Full Lesson page</button>
						</c:when>
						<c:otherwise>
							<button class="btn btn-sm btn-success btn-block" style="width:200px;" onclick="window.location.href ='${pageContext.request.contextPath}/Get_Lesson_Details?User=Student&&Course_id=${Course_List[0]}&&Course_Name=${Course_List[1]}&&Lesson_id=${Course_Lessons[1]}'; checkSession();">Go to Full Lesson page</button>
						</c:otherwise>
					 </c:choose>	
				  </td>
				</tr>
			</c:if>
			</c:forEach>
			    </table>
			   </td>
			 </tr>
			</c:if>
			</c:forEach>
			</table>		
				  </c:when>
				  <c:otherwise>
			  		<div class="message">
						There is no content available at the moment. Please try again later.		
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