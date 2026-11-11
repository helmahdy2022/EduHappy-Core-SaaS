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
                <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp" class="text-primary" data-toggle="tooltip" data-placement="top" title="Dashboard" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-home"></span>
                </a>
              </div>
              <div class="col-xs-4">
                <a href="${pageContext.request.contextPath}/Parent/Messages/Initiate_Parent_Messages.jsp" class="text-info" data-toggle="tooltip" data-placement="top" title="Messages" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-inbox"></span>
                </a>
              </div>
              <div class="col-xs-4">
                <a href="${pageContext.request.contextPath}/Help/EduHappy_Student_Guide.pdf" target=_blank class="text-system" data-toggle="tooltip" data-placement="top" title="Help">
                  <span class="glyphicon glyphicon-question-sign"></span>
                </a>
              </div>
              <div class="col-xs-4">
                <a href="${pageContext.request.contextPath}/Parent/Profile/Change_Password.jsp" class="text-danger" data-toggle="tooltip" data-placement="top" title="Settings" onclick="overlayOn();">
                  <span class="fa fa-gears"></span>
                </a>
              </div>
              <div class="col-xs-4">
                <a href="${pageContext.request.contextPath}/Get_Parent_Details?Parent_id=${Parent_Account_Details.parent_id}&&Source=view" class="text-warning" data-toggle="tooltip" data-placement="top" title="Profile" onclick="overlayOn();">
                  <span class="fa fa-user"></span>
                </a>
              </div>            
              <div class="col-xs-4">
                <a href="mailto:info@edu-happy.com" class="text-warning" data-toggle="tooltip" data-placement="top" title="Support" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-headphones"></span>
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
            <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp" onclick="overlayOn();">
              <span class="glyphicon glyphicon-home"></span>
              <span class="sidebar-title">Dashboard</span>
            </a>
          </li>          
 		
<%--   		<c:if test="${Parent_Account_Details.account_id=='168'}">     --%> 
 		<c:if test="${Parent_Account_Details.account_id=='258'}">            
  		   <li class="sidebar-label pt20">Subscribe for Subjects</li>
           <li>
            <a class="accordion-toggle" href="#">
              <span class="fa fa-book"></span>
              <span class="sidebar-title">Add and Enroll Child</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
              <li>
                <a href="${pageContext.request.contextPath}/Parent/Child/Add_Child.jsp" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-book"></span>
					Add Child
                </a>
              </li>
            </ul>         
          </li>

 		   <li class="sidebar-label pt20">Manage Subscriptions</li>
           <li>
            <a class="accordion-toggle" href="#">
              <span class="fa fa-book"></span>
              <span class="sidebar-title">Manage Subscriptions</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
             <c:forEach items="${child.student_id}" var="Student_id"  varStatus="position">
              <li>
                <a href="${pageContext.request.contextPath}/Get_Subscription_Courses?Student_id=${Student_id}" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-book"></span>
                  	<c:out value='${child.first_Name[position.index]}'/>&nbsp;<c:out value='${child.last_Name[position.index]}'/>
                </a>
              </li>
             </c:forEach>
            </ul>         
          </li>
  		   <li class="sidebar-label pt20">Manual Renewal</li>
           <li>
            <a class="accordion-toggle" href="#">
              <span class="fa fa-book"></span>
              <span class="sidebar-title">Manual Renewal</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
              <li>
                <a href="${pageContext.request.contextPath}/Get_Due_Renewal" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-book"></span>
                  	Pay overdue subscription
                </a>
              </li>
            </ul>         
          </li>
         </c:if>
   		   <li class="sidebar-label pt20">Course Content</li>
           <li>
            <a class="accordion-toggle" href="#">
              <span class="fa fa-book"></span>
              <span class="sidebar-title">Course Content</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
             <c:forEach items="${child.student_id}" var="Student_id"  varStatus="position">
              <li>
                <a href="${pageContext.request.contextPath}/Student/Content/Initiate_Course_Content.jsp?Student_id=${Student_id}" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-book"></span>
                  	<c:out value='${child.first_Name[position.index]}'/>&nbsp;<c:out value='${child.last_Name[position.index]}'/>
                </a>
              </li>
             </c:forEach>
            </ul>         
          </li>                
          <li class="sidebar-label pt15">Student Attendance</li>
           <li>
            <a class="accordion-toggle" href="#">
              <span class="fa fa-clock-o"></span>
              <span class="sidebar-title">Attendance Report</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
             <c:forEach items="${child.student_id}" var="Student_id"  varStatus="position">
              <li>
                <a href="${pageContext.request.contextPath}/Student/Attendance/Initiate_Attendance_Summary.jsp?Student_id=${Student_id}" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-book"></span>
                  	<c:out value='${child.first_Name[position.index]}'/>&nbsp;<c:out value='${child.last_Name[position.index]}'/>
                </a>
              </li>
             </c:forEach>
            </ul>         
          </li>

          <li class="sidebar-label pt20">Assignments</li>
          <li>
            <a class="accordion-toggle" href="#">
              <span class="glyphicon glyphicon-tasks"></span>
              <span class="sidebar-title">Assignments</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
             <c:forEach items="${child.student_id}" var="Student_id"  varStatus="position">
              <li>
                <a href="${pageContext.request.contextPath}/Student/Assignments/Initiate_Assignments.jsp?Student_id=${Student_id}" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-book"></span>
                  	<c:out value='${child.first_Name[position.index]}'/>&nbsp;<c:out value='${child.last_Name[position.index]}'/>
                </a>
              </li>
             </c:forEach>
            </ul>         
          </li>


          <li class="sidebar-label pt15">Student's Teachers</li>
           <li>
            <a class="accordion-toggle" href="#">
              <span class="fa fa-users"></span>
              <span class="sidebar-title">Teachers</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
             <c:forEach items="${child.student_id}" var="Student_id"  varStatus="position">
              <li>
                <a href="${pageContext.request.contextPath}/Student/MyTeachers/Initiate_MyTeachers.jsp?Student_id=${Student_id}" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-book"></span>
                  	<c:out value='${child.first_Name[position.index]}'/>&nbsp;<c:out value='${child.last_Name[position.index]}'/>
                </a>
              </li>
             </c:forEach>
            </ul>         
          </li>


          <!-- sidebar resources -->
          <li class="sidebar-label pt20">Grading</li>             
           <li>
            <a class="accordion-toggle" href="#">
              <span class="fa fa-bar-chart"></span>
              <span class="sidebar-title">Grading Report</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
             <c:forEach items="${child.student_id}" var="Student_id"  varStatus="position">
              <li>
                <a href="${pageContext.request.contextPath}/Student/Grading/Initiate_Student_Grading.jsp?Student_id=${Student_id}&&Source=Parent" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-book"></span>
                  	<c:out value='${child.first_Name[position.index]}'/>&nbsp;<c:out value='${child.last_Name[position.index]}'/>
                </a>
              </li>
             </c:forEach>
            </ul>         
          </li>

          <li class="sidebar-label pt15">Schedule</li>
           <li>
            <a class="accordion-toggle" href="#">
              <span class="glyphicon glyphicon-bell"></span>
              <span class="sidebar-title">Schedule</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
             <c:forEach items="${child.student_id}" var="Student_id"  varStatus="position">
              <li>
                <a href="${pageContext.request.contextPath}/Student/Schedule/Initiate_Student_Schedule.jsp?Student_id=${Student_id}" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-book"></span>
                  	<c:out value='${child.first_Name[position.index]}'/>&nbsp;<c:out value='${child.last_Name[position.index]}'/>
                </a>
              </li>
             </c:forEach>
            </ul>         
          </li>

          <li class="sidebar-label pt15">Exam Schedule</li>
           <li>
            <a class="accordion-toggle" href="#">
              <span class="glyphicon glyphicon-bell"></span>
              <span class="sidebar-title">Exam Schedule</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
             <c:forEach items="${child.student_id}" var="Student_id"  varStatus="position">
              <li>
                <a href="${pageContext.request.contextPath}/Student/ExamSchedule/Initiate_Exam_Schedule.jsp?Student_id=${Student_id}" onclick="overlayOn();">
                  <span class="glyphicon glyphicon-book"></span>
                  	<c:out value='${child.first_Name[position.index]}'/>&nbsp;<c:out value='${child.last_Name[position.index]}'/>
                </a>
              </li>
             </c:forEach>
            </ul>         
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


