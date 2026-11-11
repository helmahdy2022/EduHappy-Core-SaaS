<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>My Teachers</title>
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
            <li class="crumb-trail">Student's Teachers</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/MyTeachers/Initiate_MyTeachers.jsp?Student_id=${param.Student_id}">Student's Teachers</a>
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
            <li class="crumb-trail">My Teachers</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/MyTeachers/Initiate_MyTeachers.jsp">My Teachers</a>
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
	          <h4>Teachers</h4>
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
                <span class="fa fa-book"></span>Teachers</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Student Teachers per Course</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
				 <table class="table table-bordered mbn">
		      		<thead>	
					<tr >
					<th>Course</th>
					<th>Group</th>
					<th>Teacher Name</th>	
					<th>Teacher Photo</th>		
					</tr>				
					<c:forEach items="${MyTeachers.course_id}" var="Course_id" varStatus="loop">					
					<c:set var="Teacher_id" value=""/>
					<c:set var="Account_id" value=""/>
					<c:set var="FileName" value=""/>						
					<tr>
					<td>
						${MyTeachers.course_Name[loop.index]}					
					</td>
					<td>				
					<c:if test="${not empty MyTeachers.class_Authority_List}">
						<c:forEach items="${MyTeachers.class_Authority_List}" var="Course_Class_Teacher">
						  <c:if test="${Course_id==Course_Class_Teacher[0]}">
							${Course_Class_Teacher[8]}					
						  </c:if>
						</c:forEach>		
					</c:if>
					</td>
					<td>				
					<c:if test="${not empty MyTeachers.class_Authority_List}">
						<c:forEach items="${MyTeachers.class_Authority_List}" var="Course_Class_Teacher">
						  <c:if test="${Course_id==Course_Class_Teacher[0]}">
							${Course_Class_Teacher[4]}
							<c:set var="Teacher_id" value="${Course_Class_Teacher[3]}"/>
							<c:set var="Account_id" value="${Course_Class_Teacher[5]}"/>
							<c:set var="FileName" value="${Course_Class_Teacher[6]}"/>							
						  </c:if>
						</c:forEach>		
					</c:if>
					</td>
					<td>
					<c:choose>
						<c:when test="${not empty FileName && FileName!=null}">
							<img  width="100" height="100" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Account_id}/Teachers/Photos/${Teacher_id}/${FileName}">							
						</c:when>
						<c:otherwise>
							<img  width="100" height="100" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
						</c:otherwise>
					</c:choose>	
					</td>		
					</tr>
					</c:forEach>
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