<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

</head>

<body class="dashboard-page sb-l-o sb-r-c">

<!-------------------------------------------------------------+ 
  <body> Helper Classes: 
---------------------------------------------------------------+ 
  '.sb-l-o' - Sets Left Sidebar to "open"
  '.sb-l-m' - Sets Left Sidebar to "minified"
  '.sb-l-c' - Sets Left Sidebar to "closed"

  '.sb-r-o' - Sets Right Sidebar to "open"
  '.sb-r-c' - Sets Right Sidebar to "closed"
---------------------------------------------------------------+
 Example: <body class="example-page sb-l-o sb-r-c">
 Results: Sidebar left Open, Sidebar right Closed
--------------------------------------------------------------->

  <!-- For Demo Purposes - Theme Settings Pane -->
	<%@include file="includes/Theme_Settings.jsp" %>
  <!-- End: Theme Settings Pane -->

  <!-- Start: Main -->
  <div id="main">

<%@include file="includes/TeacherMenu.jsp" %>
<%@include file="includes/TeacherLeft.jsp" %>
    <!-- Start: Content-Wrapper -->
    <section id="content_wrapper">

      <!-- Start: Topbar-Dropdown -->
	<%@include file="includes/Topbar_Dropdown.jsp" %>
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
            <li class="crumb-trail">Dashboard</li>
          </ol>
        </div>
		<%@include file="includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
	  <%@include file="includes/Adsense_Top.jsp" %>
      <!-- Begin: Content -->
      <section id="content" class="animated fadeIn">

        <!-- Dashboard Tiles -->
        <div class="row mb10">
          <div class="col-sm-6 col-md-3">
            <div class="panel bg-alert light of-h mb10">
              <div class="pn pl20 p5">
                <div class="icon-bg">
                  <i class="fa fa-clock-o"></i>
                </div>
                <h2 class="mt15 lh15">
                  <b>A</b>
                </h2>
                <h5 class="text-muted">Attendance</h5>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-md-3">
            <div class="panel bg-info light of-h mb10">
              <div class="pn pl20 p5">
                <div class="icon-bg">
                  <i class="fa fa-tasks"></i>
                </div>
                <h2 class="mt15 lh15">
                  <b>AS</b>
                </h2>
                <h5 class="text-muted">Assignments</h5>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-md-3">
            <div class="panel bg-danger light of-h mb10">
              <div class="pn pl20 p5">
                <div class="icon-bg">
                  <i class="fa fa-bar-chart-o"></i>
                </div>
                <h2 class="mt15 lh15">
                  <b>G</b>
                </h2>
                <h5 class="text-muted">Grading</h5>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-md-3">
            <div class="panel bg-warning light of-h mb10">
              <div class="pn pl20 p5">
                <div class="icon-bg">
                  <i class="fa fa-bell"></i>
                </div>
                <h2 class="mt15 lh15">
                  <b>S</b>
                </h2>
                <h5 class="text-muted">Schedule</h5>
              </div>
            </div>
          </div>
        </div>

        <!-- Admin-panels -->
        <div class="admin-panels fade-onload">

          <div class="row">
            <div class="col-md-12 admin-grid">
              <div class="panel sort-disable" id="p0">
                <div class="panel-heading">
                  <span class="panel-title">Class Authority List</span>
                </div>
                <div class="panel-body mnw700 of-a">
                  <div class="row">
                    <div>
                      <h5 class="ml5 mt20 ph10 pb5 br-b fw600">The following represents the <span class="text-primary">class authority list</span> assigned to you by the admin:</h5>         
		              <div class="table-responsive">
		                <div class="bs-component">
		                      <table class="table table-bordered mbn">
		                        <thead>
		                          <tr >
		                            <th></th>
		                            <th>School</th>
		                            <th>Grade</th>
		                            <th>Class</th>
		                            <th>Course</th>
		                            <th>Group</th>                                                                                                               
		                          </tr>
		                        </thead>
		                        <tbody>
								     <c:forEach items="${TeacherAuthority.authorityList}" var="AuthorityList">  
								    <tr>
									    <td style="width:20px;"><i class="fa fa-circle text-warning fs8 pr15"></i></td>
									    <td><c:out value="${AuthorityList[1]}"/></td>
									    <td><c:out value="${AuthorityList[3]}"/></td>
									    <td><c:out value="${AuthorityList[5]}"/></td>
									    <td><c:out value="${AuthorityList[7]}"/></td>
									    <td><c:out value="${AuthorityList[9]}"/></td>               
								    </tr>
								    </c:forEach> 
		                        </tbody>
		                      </table>           
		              	</div>
		              </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- end: .col-md-12.admin-grid -->

          </div>
          <!-- end: .row -->

          <div class="row">
            <div class="col-md-12 admin-grid">
              <div class="panel sort-disable" id="p0">
                <div class="panel-heading">
                  <span class="panel-title">Content Authority List</span>
                </div>
                <div class="panel-body mnw700 of-a">
                  <div class="row">
                    <div>
                      <h5 class="ml5 mt20 ph10 pb5 br-b fw600">The following represents the <span class="text-primary">content authority list</span> assigned to you by the admin:</h5>         
		              <div class="table-responsive">
		                <div class="bs-component">
		                      <table class="table table-bordered mbn">
		                        <thead>
		                          <tr >
		                            <th></th>
		                            <th>School</th>
		                            <th>Grade</th>
		                            <th>Course</th>                                                                                                             
		                          </tr>
		                        </thead>
		                        <tbody>
									<c:forEach items="${TeacherContent.contentList}" var="ContentList"> 
								    <tr>
									    <td style="width:20px;"><i class="fa fa-circle text-warning fs8 pr15"></i></td>
										<td><c:out value="${ContentList[1]}"/></td>
										<td><c:out value="${ContentList[3]}"/></td>
										<td><c:out value="${ContentList[5]}"/></td>               
								    </tr>
								    </c:forEach>
		                        </tbody>
		                      </table>           
		              	</div>
		              </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- end: .col-md-12.admin-grid -->

          </div>
          <!-- end: .row -->

        </div>

      </section>
      <!-- End: Content -->

      <!-- Begin: Page Footer -->
      <footer id="content-footer">
		<%@include file="includes/TeacherFooter.jsp" %>
		<%@include file="includes/Page_Scripts.jsp" %>
      </footer>
      <!-- End: Page Footer -->

    </section>
    <!-- End: Content-Wrapper -->

    <!-- Start: Right Sidebar -->
			<%@include file="includes/Topbar_Right_Content.jsp" %>
    <!-- End: Right Sidebar -->

  </div>
  <!-- End: Main -->


</body>

</html>
