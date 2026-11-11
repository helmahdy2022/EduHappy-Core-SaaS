<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<%@include file="includes/StudentMenu.jsp" %>
<%@include file="includes/StudentLeft.jsp" %>
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
            <div class="panel bg-system light of-h mb10">
              <div class="pn pl20 p5">
                <div class="icon-bg">
                  <i class="fa fa-book"></i>
                </div>
                <h2 class="mt15 lh15">
                  <b>C</b>
                </h2>
                <h5 class="text-muted">Course Content</h5>
              </div>
            </div>
          </div>
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
            <div class="panel bg-success light of-h mb10">
              <div class="pn pl20 p5">
                <div class="icon-bg">
                  <i class="fa fa-users"></i>
                </div>
                <h2 class="mt15 lh15">
                  <b>T</b>
                </h2>
                <h5 class="text-muted">Teachers</h5>
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
          <div class="col-sm-6 col-md-3">
            <div class="panel bg-dark light of-h mb10">
              <div class="pn pl20 p5">
                <div class="icon-bg">
                  <i class="fa fa-calendar"></i>
                </div>
                <h2 class="mt15 lh15">
                  <b>SC</b>
                </h2>
                <h5 class="text-muted">School Calendar</h5>
              </div>
            </div>
          </div>
           <div class="col-sm-6 col-md-3">
            <div class="panel bg-primary light of-h mb10">
              <div class="pn pl20 p5">
                <div class="icon-bg">
                  <i class="fa fa-list"></i>
                </div>
                <h2 class="mt15 lh15">
                  <b>E</b>
                </h2>
                <h5 class="text-muted">Exam Schedule</h5>
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
                  <span class="panel-title">Student Portal</span>
                </div>
                <div class="panel-body mnw700 of-a">
                  <div class="row">
                    <div>
                      <h5 class="ml5 mt20 ph10 pb5 br-b fw600">Welcome to the <span class="text-primary">Student Portal</span> </h5>  
                      <ul>
					    <li> You will be able to check the content per course and download e-Books, media files, word and PDF documents associated with each course and each lesson. Please check it on <b>Course Content</b> page</li><br/>
						<li>You can check your attendance report and review attendance progress per course. Please check it on the <b>Attendance</b> page</li><br/>
						<li>You can check your assignments per course with due dates, download assignment documents, attempt online quiz and more from <b>Assignments</b> page.</li><br/>
						<li>You can view all your teachers per each course with name and photo for each teacher in <b>MyTeachers</b> page.</li><br/>
						<li>You can review your Grading report, Grade Book, as well as report Card and check for the detailed grading progress for all the assignments and attendance per course and overall by checking the <b>Grading</b> page</li><br/>
						<li>You can check the detailed Schedule for your class, course group, or even elective course you have opted in from <b>Schedule</b> page</li><br/>
						<li>If you want to communicate with your colleagues in the class or you teachers you can always go to the <b>Messages</b> area and send and receive messages.</li>
                      
                      </ul>       
		              <div class="table-responsive">
		                <div class="bs-component">
        
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

          <!-- end: .row -->

        </div>

      </section>
      <!-- End: Content -->
	  <%@include file="includes/Adsense_Bottom.jsp" %>
      <!-- Begin: Page Footer -->
      <footer id="content-footer">
		<%@include file="includes/StudentFooter.jsp" %>
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



<%-- 


<html>
<head>
<meta charset="utf-8">
<title>Student Main</title>
</head>
<body>
<div class="pagewrap">
 <div class="header">
  <%@include file="includes/StudentMenu.jsp" %>
</div>
<div class="content">
	<div class="leftContent"></div>
	<div class="middleContent"  style="width:700px;margin-top:50px;"> 
    <h1>Welcome ${Student_Account_Details.first_Name}!</h1>
    <c:if test="${Student_Account_Details.student_Promoted==true}">
     	<div style="width:450px;display:table-cell"><img src="${pageContext.request.contextPath}/images/Final_Report_Card_Image.jpg"></div>
     	<div style="width:250px;display:table-cell;vertical-align:top;text-align:center;">
     	<font size="6">
     	Your Final Grading Report is NOW ready<br/>
     	<a href="${pageContext.request.contextPath}/Student/Grading/Initiate_Student_Grading.jsp"><font color="red">Check It HERE</font></a>
     	</font>
     	</div>
    </c:if> 
    <br/>
    Did you know that:
    <br>
    <ul>
    <li> You will be able to check the content per course and download e-Books, media files, word and PDF documents associated with each course and each lesson. Please check it on <b>Course Content</b> page</li><br/>
	<li>You can check your attendance report and review attendance progress per course. Please check it on the <b>Attendance</b> page</li><br/>
	<li>You can check your assignments per course with due dates, download assignment documents, attempt online quiz and more from <b>Assignments</b> page.</li><br/>
	<li>You can view all your teachers per each course with name and photo for each teacher in <b>MyTeachers</b> page.</li><br/>
	<li>You can review your Grading report, Grade Book, as well as report Card and check for the detailed grading progress for all the assignments and attendance per course and overall by checking the <b>Grading</b> page</li><br/>
	<li>You can check the detailed Schedule for your class, course group, or even elective course you have opted in from <b>Schedule</b> page</li><br/>
	<li>If you want to communicate with your colleagues in the class or you teachers you can always go to the <b>Messages</b> area and send and receive messages.</li>
	</ul>
	</div>
	<div class="rightContent" style="margin-right:100px;text-align:center;">
	<%@include file="includes/StudentRight.jsp" %>
    <c:if test="${Student_Account_Details.student_Promoted==false}">
    	<br/><br/><br/><br/>
		<a href="${pageContext.request.contextPath}/Student/Calendar/School_Calendar.jsp">
		<img src="${pageContext.request.contextPath}/images/School_Calendar.jpg"><br/>
		School Calendar
		</a>
		<br/><br/>	
		<a href="${pageContext.request.contextPath}/Student/ExamSchedule/Exam_Schedule.jsp">
		<img src="${pageContext.request.contextPath}/images/Exam_Schedule.jpg"><br/>
		Exam Schedule
		</a>
	</c:if>
	</div>
</div>
<div class="footer"><%@include file="includes/StudentFooter.jsp" %></div>

</div>


</body>
</html> --%>