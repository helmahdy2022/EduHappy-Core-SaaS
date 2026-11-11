<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Student Schedule</title>
<style>
a#OnlineMeeting:link, a#OnlineMeeting:visited {
  background-color: #f44336;
  color: white;
  padding: 5px 5px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
}

a#OnlineMeeting:hover, a#OnlineMeeting:active {
  background-color: red;
}
</style>
<script type="text/javascript">
 

function getClientTimeZone() 
{
	if (performance.navigation.type == 1) 
	  {
	    //This page is reloaded;
	  	var clientDate = new Date();
        var offset = clientDate.getTimezoneOffset();
        var clientHours = clientDate.getHours();
        var clientMinutes = clientDate.getMinutes();
        var clientDay = clientDate.getDay();
        var url_string = window.location.href;
        var url = new URL(url_string);
        var Student_id = url.searchParams.get("Student_id");
		if(Student_id==null)
    		{
			window.location = "${pageContext.request.contextPath}/Get_Student_Schedule?clientHours="+clientHours+"&&clientMinutes="+clientMinutes+"&&clientDay="+clientDay;
			}
    	else
    		{
    		window.location = "${pageContext.request.contextPath}/Get_Student_Schedule?Student_id=${param.Student_id}&&clientHours="+clientHours+"&&clientMinutes="+clientMinutes+"&&clientDay="+clientDay;
    		}
	  } 
}

</script>
</head>
<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300" onload="getClientTimeZone();">

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
            <li class="crumb-trail">Schedule</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Schedule/Initiate_Student_Schedule.jsp?Student_id=${param.Student_id}">Schedule</a>
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
            <li class="crumb-trail">Schedule</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Schedule/Initiate_Student_Schedule.jsp">Schedule</a>
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
	        <h4>My Schedule</h4>
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
                <span class="glyphicon glyphicon-bell"></span> Schedule Details</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Student Schedule</code>
              </div>
            </div>
             <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component" style="overflow-x:auto;white-space: nowrap;">
		 		<c:choose>
					<c:when test="${not empty Student_Schedule.sessions}">
						<%int n=0;%>
				 		<table class="table table-bordered mbn">
				 			<tr>
				 				<td class="scheduleSession"></td>
				 			  	<c:forEach items="${Student_Schedule.sessions}" var="Session" varStatus="position">
				 			  		<c:if test = "${Session[0]!=null}">		
						 				<td class="scheduleSession"><c:out value="${Session[1]} - ${Session[2]}"/> </td>
						 			</c:if>
						      </c:forEach>	
					 		</tr>
							
								<c:forEach items="${Student_Schedule.day_id}" var="Day_id" varStatus="position">
						 		<tr>
							 		<td class="scheduleDay"><c:out value="${Student_Schedule.day_Name[position.index]}"/></td>
				 					<c:forEach items="${Student_Schedule.sessions}" var="Session" varStatus="position">
				 						<c:if test = "${Session[0]!=null}">		
										 <%			 						
										n=n+1;
										request.setAttribute("n", n);
										%>			
											<td>
											  	<c:forEach items="${Student_Schedule.courseExist}" var="Course" varStatus="position">
													<c:if test = "${Course[0]==Day_id && Course[1]==Session[0]}" >
											       		<c:choose>
											       		<c:when test="${fn:contains(Course[4], 'Break')}">
												       		<div class="scheduleBreak">
												       			${Course[4]}
												       		</div>											       		
											       		</c:when>
											       		<c:otherwise>
												       		<div class="scheduleCourse">
												       			${Course[4]}
												       		</div>
											       		</c:otherwise>
											       		</c:choose>
											       	</c:if>		
										     	</c:forEach>
														
											</td>
										</c:if>
									</c:forEach>
								</tr>
						      </c:forEach>	
				 			</table>	
						</c:when>
					<c:otherwise>
			  		<div class="message">
						The schedule is not available at the moment. Please try again later.		
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