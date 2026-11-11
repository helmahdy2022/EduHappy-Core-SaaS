<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Assignment Submission</title>
</head>
<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300">

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
            <li class="crumb-trail">Student's Assignments</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Assignments/Initiate_Assignments.jsp">Assignments</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
  	   <%@include file="../includes/Adsense_Top.jsp" %>
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
	          <h4>Student Assignments</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="glyphicon glyphicon-tasks"></span>Assignment Submission</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Student Assignments</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
					  <table class="table table-bordered mbn">
					   <tr>
					   <td></td>		  
					  <td style="text-align:right;">
					  	<a href="${pageContext.request.contextPath}/Student/Assignments/Initiate_Assignments.jsp">Current Assignments</a>
					  </td>
					   </tr>
					<tr>
					<td></td>
					<td>
				 	 <c:if test="${not empty message}">
				  		<div class="message">${message}
				  		<br>
				  		<c:forEach items="${uploadMessage}" var="uploadMessage">
				  		<c:out value="${uploadMessage}" />
				  		<br>
				  		</c:forEach>
				  		</div> 
				 	 </c:if>
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