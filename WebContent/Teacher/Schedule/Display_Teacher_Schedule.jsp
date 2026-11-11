<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
<title>Teacher Schedule</title>
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
	    //console.info( "This page is reloaded" );
	  	var clientDate = new Date();
        var offset = clientDate.getTimezoneOffset();
        var clientHours = clientDate.getHours();
        var clientMinutes = clientDate.getMinutes();
        var clientDay = clientDate.getDay();
		window.location = "${pageContext.request.contextPath}/Get_Teacher_Schedule?clientHours="+clientHours+"&&clientMinutes="+clientMinutes+"&&clientDay="+clientDay;
	  } 
}

</script>
</head>
<body class="tables-basic-page"  data-spy="scroll" data-target="#nav-spy" data-offset="300" onload="getClientTimeZone();">

  <!-- For Demo Purposes - Theme Settings Pane -->
	<%@include file="../includes/Theme_Settings.jsp" %>
  <!-- End: Theme Settings Pane -->
  <div id="main">
	<%@include file="../includes/TeacherMenu.jsp" %> 
	<%@include file="../includes/TeacherLeft.jsp" %>
     <section id="content_wrapper">

      <!-- Start: Topbar-Dropdown -->
	<%@include file="../includes/Topbar_Dropdown.jsp" %>
      <!-- End: Topbar-Dropdown -->  
      <!-- Start: Topbar -->
      <header id="topbar">
        <div class="topbar-left">
          <ol class="breadcrumb">
            <li class="crumb-active">
              <a href="${pageContext.request.contextPath}/Teacher/Teacher_Main.jsp">Dashboard</a>
            </li>
            <li class="crumb-icon">
              <a href="${pageContext.request.contextPath}/Teacher/Teacher_Main.jsp">
                <span class="glyphicon glyphicon-home"></span>
              </a>
            </li>
            <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Teacher/Teacher_Main.jsp">Home</a>
            </li>
            <li class="crumb-trail">My Schedule</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Teacher/Assignments/Teacher_Schedule.jsp">My Schedule</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
		  	<%@include file="../includes/Adsense_Top.jsp" %>
	          <h4>My Schedule</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="glyphicon glyphicon-bell"></span> Schedule Details</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Teacher Schedule Details</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component" style="overflow-x:auto;white-space: nowrap;">
                    <table class="table table-bordered mbn">
                       <tbody>
					    <tr>
				 			<td class="scheduleSession"></td>
				 			  	<c:forEach items="${Teacher_Schedule.sessions}" var="Session" varStatus="position">
				 			  		<c:if test = "${Session[0]!=null}">		
						 				<td class="scheduleSession"><c:out value="${Session[1]}--${Session[2]}"/></td>
						 			</c:if>
						      </c:forEach>	
					 	</tr>
							
						<c:forEach items="${Teacher_Schedule.day_id}" var="Day_id" varStatus="position">
						 <tr>
					 		<td class="scheduleDay"><c:out value="${Teacher_Schedule.day_Name[position.index]}"/></td>
								<c:forEach items="${Teacher_Schedule.sessions}" var="Session" varStatus="position">
									<c:if test = "${Session[0]!=null}">					
									<td class="scheduleCell">
									  	<c:forEach items="${Teacher_Schedule.courseExist}" var="Course" varStatus="position">
											<c:if test = "${Course[0]==Day_id && Course[1]==Session[0]&& Course[2]!=null && Course[2]!=''}" >
									       		<div class="scheduleCourse">
									       			<c:out value="${Course[9]}"/>
									       			<br/> 
									       			<c:out value="${Course[8]}"/> 
									       			<br/> 
									       			<c:out value="${Course[7]}"/> 
									       			<br/>
									       		<label>
									       			<c:out value="${Course[4]} ${Course[10]}"/>
									       		</label>
									       			<br/>
								       			<c:if test="${Course[11]=='true'}">
									       			<label>
									       				<c:out value="Online Meeting"/>
									       			</label>									       											       			
									       			<a href="<c:out value="${Course[12]}" />" target="_blank" id="OnlineMeeting">Click Here</a>
									       		</c:if>
									       		</div>
									       	</c:if>		
								     	</c:forEach>								
								</td>
								</c:if>
							</c:forEach>
						</tr>
						</c:forEach>
                      </tbody>
                    </table>  
                </div>
              </div>
            </div>
          </div>	
	        </div>
       </section>  
	  <%@include file="../includes/Adsense_Bottom.jsp" %>
      <!-- End: Content -->

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