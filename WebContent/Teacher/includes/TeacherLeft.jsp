<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <!-----------------------------------------------------------------+ 
       "#sidebar_left" Helper Classes: 
    -------------------------------------------------------------------+ 
       * Positioning Classes: 
        '.affix' - Sets Sidebar Left to the fixed position 

       * Available Skin Classes:
         .sidebar-dark (default no class needed)
         .sidebar-light  
         .sidebar-light.light   
    -------------------------------------------------------------------+
       Example: <aside id="sidebar_left" class="affix sidebar-light">
       Results: Fixed Left Sidebar with light/white background
    ------------------------------------------------------------------->

    <!-- Start: Sidebar Left -->
    <aside id="sidebar_left" class="nano nano-primary affix">

      <!-- Start: Sidebar Left Content -->
      <div class="sidebar-left-content nano-content">

        <!-- Start: Sidebar Header -->
        <header class="sidebar-header">

          <!-- Sidebar Widget - Menu (Slidedown) -->
          <div class="sidebar-widget menu-widget">
            <div class="row text-center mbn">
              <div class="col-xs-4">
                <a href="${pageContext.request.contextPath}/Teacher/Teacher_Main.jsp" class="text-primary" data-toggle="tooltip" data-placement="top" title="Dashboard" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-home"></span>
                </a>
              </div>
              <div class="col-xs-4">
                <a href="${pageContext.request.contextPath}/Teacher/Messages/Initiate_Teacher_Messages.jsp" class="text-info" data-toggle="tooltip" data-placement="top" title="Messages" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-inbox"></span>
                </a>
              </div>
              <div class="col-xs-4">
                <a href="${pageContext.request.contextPath}/Teacher/Schedule/Teacher_Schedule.jsp" class="text-alert" data-toggle="tooltip" data-placement="top" title="Schedule" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-bell"></span>
                </a>
              </div>
              <div class="col-xs-4">
                <a href="${pageContext.request.contextPath}/Help/EduHappy_Teacher_Guide.pdf" target=_blank class="text-system" data-toggle="tooltip" data-placement="top" title="Help">
                  <span class="glyphicon glyphicon-question-sign"></span>
                </a>
              </div>
              <div class="col-xs-4">
                <a href="${pageContext.request.contextPath}/Teacher/Profile/Change_Password.jsp" class="text-danger" data-toggle="tooltip" data-placement="top" title="Settings" onclick="overlayOn();">
                  <span class="fa fa-gears"></span>
                </a>
              </div>
              <div class="col-xs-4">
                <a href="${pageContext.request.contextPath}/Teacher/Attendance/Initiate_Attendance.jsp" class="text-warning" data-toggle="tooltip" data-placement="top" title="Attendance" onclick="overlayOn();">
                  <span class="fa fa-clock-o"></span>
                </a>
              </div>
            </div>
          </div>

          <!-- Sidebar Widget - Author (hidden)  -->
          <div class="sidebar-widget author-widget hidden">
            <div class="media">
              <a class="media-left" href="#">
                <img src="${pageContext.request.contextPath}/img/avatars/3.jpg" class="img-responsive">
              </a>
              <div class="media-body">
                <div class="media-links">
                   <a href="#" class="sidebar-menu-toggle">User Menu -</a> <a href="pages_login(alt).html">Logout</a>
                </div>
                <div class="media-author">Michael Richards</div>
              </div>
            </div>
          </div>

          <!-- Sidebar Widget - Search (hidden) -->
          <div class="sidebar-widget search-widget hidden">
            <div class="input-group">
              <span class="input-group-addon">
                <i class="fa fa-search"></i>
              </span>
              <input type="text" id="sidebar-search" class="form-control" placeholder="Search...">
            </div>
          </div>

        </header>
        <!-- End: Sidebar Header -->

        <!-- Start: Sidebar Left Menu -->
        <ul class="nav sidebar-menu">
          <li class="sidebar-label pt20">Menu</li>
          <li class="active">
            <a href="${pageContext.request.contextPath}/Teacher/Teacher_Main.jsp" onclick="overlayOn();">
              <span class="glyphicon glyphicon-home"></span>
              <span class="sidebar-title">Dashboard</span>
            </a>
          </li>          
          <li class="sidebar-label pt20">Course Content</li>
          <li>
            <a href="${pageContext.request.contextPath}/Teacher/Course_Content/Manage_Book.jsp" onclick="overlayOn();">
              <span class="fa fa-book"></span>
              <span class="sidebar-title">e-Books</span>
            </a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/Teacher/Course_Content/Manage_Unit.jsp" onclick="overlayOn();">
              <span class="glyphicon glyphicon-th"></span>
              <span class="sidebar-title">Course Units</span>
            </a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/Teacher/Course_Content/Manage_Lesson.jsp?Page=Lesson&&Action=New" onclick="overlayOn();">
              <span class="glyphicon glyphicon-pencil"></span>
              <span class="sidebar-title">New Lesson</span>
            </a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/Teacher/Course_Content/Manage_Lesson.jsp?Page=Lesson&&Action=Edit" onclick="overlayOn();">
              <span class="glyphicon glyphicon-edit"></span>
              <span class="sidebar-title">Manage Lesson</span>
            </a>
          </li>
           <li>
            <a href="${pageContext.request.contextPath}/Teacher/Course_Content/Manage_Assignment_Category.jsp?Page=Assignment_Category" onclick="overlayOn();">
              <span class="fa fa-tags"></span>
              <span class="sidebar-title">Assignment Category</span>
            </a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/Teacher/Course_Content/Manage_Standard_Assignment.jsp" onclick="overlayOn();">
              <span class="fa fa-columns"></span>
              <span class="sidebar-title">Standard Assignments</span>
            </a>
          </li>                   
                  
          <li class="sidebar-label pt15">Student Attendance</li>
          <li>
            <a href="${pageContext.request.contextPath}/Teacher/Attendance/Initiate_Attendance.jsp" onclick="overlayOn();">
              <span class="fa fa-clock-o"></span>
              <span class="sidebar-title">Attendance</span>
            </a>
          </li> 

          <li class="sidebar-label pt20">Assignments</li>
          <li>
            <a href="${pageContext.request.contextPath}/Teacher/Assignments/New_Assignment.jsp?Page=New" onclick="overlayOn();">
              <span class="glyphicon glyphicon-tasks"></span>
              <span class="sidebar-title">New Assignment</span>
            </a>
          </li>
           <li>
            <a href="${pageContext.request.contextPath}/Teacher/Assignments/New_Assignment.jsp?Page=Edit" onclick="overlayOn();">
              <span class="glyphicon glyphicon-th-list"></span>
              <span class="sidebar-title">Manage Assignment</span>
            </a>
          </li>


          <!-- sidebar resources -->
          <li class="sidebar-label pt20">Grading</li>
          <li>
            <a href="${pageContext.request.contextPath}/Teacher/Assignments/New_Assignment.jsp?Page=Grade" onclick="overlayOn();">
              <span class="fa fa-diamond"></span>
              <span class="sidebar-title">Grade Assignment</span>
            </a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/Teacher/Assignments/Semester_Exams.jsp" onclick="overlayOn();">
              <span class="fa fa-check-square"></span>
              <span class="sidebar-title">Grade Semester Exam</span>
            </a>
          </li>
           <li>
            <a href="${pageContext.request.contextPath}/Teacher/Assignments/Grading_Reports.jsp" onclick="overlayOn();">
              <span class="fa fa-bar-chart"></span>
              <span class="sidebar-title">Grading Reports</span>
            </a>
          </li>        
           <li class="sidebar-label pt20">Schedule</li>
           <li>
            <a href="${pageContext.request.contextPath}/Teacher/Schedule/Teacher_Schedule.jsp" onclick="overlayOn();">
              <span class="glyphicon glyphicon-bell"></span>
              <span class="sidebar-title">Schedule</span>
            </a>
          </li>                      
        </ul>
        <!-- End: Sidebar Menu -->

	      <!-- Start: Sidebar Collapse Button -->
	      <div class="sidebar-toggle-mini">
	        <a href="#">
	          <span class="fa fa-sign-out"></span>
	        </a>
	      </div>
	      <!-- End: Sidebar Collapse Button -->

      </div>
      <!-- End: Sidebar Left Content -->

    </aside>
    <!-- End: Sidebar Left -->


