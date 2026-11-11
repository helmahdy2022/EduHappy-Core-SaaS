<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Admin Main</title>
</head>
<body>
<div>
	<%@include file="includes/AdminMenu.jsp"%> 
</div>
	<div class="leftContent"><%@include file="includes/AdminLeft.jsp" %> </div>
	<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<!--End-breadcrumbs-->
  <%@include file="includes/Adsense_Top.jsp" %>
<!--Action boxes-->
  <div class="container-fluid">
    <div class="quick-actions_homepage">
      <ul class="quick-actions">

		<c:if test="${Account_Details.account_Owner==1}">
        	<li class="bg_lg span3"> <a href="${pageContext.request.contextPath}/Admin/Schools/New_School.jsp" onclick="overlayOn();"> <img src="${pageContext.request.contextPath}/img/dashboard/school.png"><br> Schools</a> </li>
        </c:if>
 	    <c:set var="Privileged" value="No" />
	    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
	    	<c:if test="${Featur_Name=='Academic_Year'}">
	    		<c:set var="Privileged" value="Yes" />
	        </c:if>
	    </c:forEach>
	    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">
       	 	<li class="bg_lr"> <a href="${pageContext.request.contextPath}/Admin/Academic_Year/New_School_Year.jsp" onclick="overlayOn();"><img src="${pageContext.request.contextPath}/img/dashboard/Academic_Year.png"><br> Academic Year</a> </li>
        </c:if>  
        
 	    <c:set var="Privileged" value="No" />
	    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
	    	<c:if test="${Featur_Name=='Grades'}">
	    		<c:set var="Privileged" value="Yes" />
	        </c:if>
	    </c:forEach>
	    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">
       	 	<li class="bg_ly"> <a href="${pageContext.request.contextPath}/Admin/Grades/New_Grade.jsp" onclick="overlayOn();"> <img src="${pageContext.request.contextPath}/img/dashboard/Grades.png"><br>Grades </a> </li>
        </c:if>          
        
 	    <c:set var="Privileged" value="No" />
	    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
	    	<c:if test="${Featur_Name=='Classes'}">
	    		<c:set var="Privileged" value="Yes" />
	        </c:if>
	    </c:forEach>
	    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">
       	 	<li class="bg_lo"> <a href="${pageContext.request.contextPath}/Admin/Classes/New_Class.jsp" onclick="overlayOn();"> <img src="${pageContext.request.contextPath}/img/dashboard/Classroom.png"><br> Classrooms</a> </li>
        </c:if>  
        
  	    <c:set var="Privileged" value="No" />
	    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
	    	<c:if test="${Featur_Name=='Courses'}">
	    		<c:set var="Privileged" value="Yes" />
	        </c:if>
	    </c:forEach>
	    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">
       	 	<li class="bg_lr span3"> <a href="${pageContext.request.contextPath}/Admin/Courses/New_Course.jsp" onclick="overlayOn();"> <img src="${pageContext.request.contextPath}/img/dashboard/Courses.png"><br> Courses</a> </li>
        </c:if> 
              
     	<c:set var="Privileged" value="No" />
	    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
	    	<c:if test="${Featur_Name=='Students'}">
	    		<c:set var="Privileged" value="Yes" />
	        </c:if>
	    </c:forEach>
	    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">
       	 	<li class="bg_lo"> <a href="${pageContext.request.contextPath}/Admin/Students/New_Student.jsp" onclick="overlayOn();"> <img src="${pageContext.request.contextPath}/img/dashboard/students.png"><br> Students</a> </li>
        </c:if>          
        
     	<c:set var="Privileged" value="No" />
	    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
	    	<c:if test="${Featur_Name=='Teachers'}">
	    		<c:set var="Privileged" value="Yes" />
	        </c:if>
	    </c:forEach>
	    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">
       	 	<li class="bg_ls span3"> <a href="${pageContext.request.contextPath}//Admin/HR/New_Employee.jsp?emp=teacher" onclick="overlayOn();"> <img src="${pageContext.request.contextPath}/img/dashboard/teachers.png"><br>Teachers</a> </li>
        </c:if>                
      </ul>
    </div>
<!--End-Action boxes-->    

<!--Chart-box-->    
    <div class="row-fluid">
      <div class="widget-box">
        <div class="widget-title bg_lg"><span class="icon"><i class="icon-signal"></i></span>
          <h5>Video Tutorial</h5>
        </div>
        <div class="widget-content" >
          <div class="row-fluid">
           <div class="span9" style="text-align:center;">
   				<div style="overflow-x:auto;">				
					<iframe width="350" height="280" src="https://www.youtube.com/embed/Jlc7tun3Qw0" frameborder="0" allowfullscreen style="margin-right: 3em; margin-bottom: 3em"></iframe>	
					<iframe width="350" height="280" src="https://www.youtube.com/embed/jt4nBn-24Ls" frameborder="0" allowfullscreen style="margin-right: 3em; margin-bottom: 3em"></iframe>					
				</div> 
   				<div style="overflow-x:auto;">				
					<iframe width="350" height="280" src="https://www.youtube.com/embed/NcavW06STD0" frameborder="0" allowfullscreen style="margin-right: 3em; margin-bottom: 3em"></iframe>
					<iframe width="350" height="280" src="https://www.youtube.com/embed/jfOPQH7B2Pg" frameborder="0" allowfullscreen style="margin-right: 3em; margin-bottom: 3em"></iframe>
				</div>
				<div style="overflow-x:auto;">	
					<iframe width="350" height="280" src="https://www.youtube.com/embed/M4mpaudajpc" frameborder="0" allowfullscreen style="margin-right: 3em; margin-bottom: 3em"></iframe>	
					<iframe width="350" height="280" src="https://www.youtube.com/embed/NFhdbe6ZZWA" frameborder="0" allowfullscreen style="margin-right: 3em; margin-bottom: 3em"></iframe>						
				</div>    				             
            </div>
            <div class="span3">
              <ul class="site-stats">
				<c:choose>
				<c:when test="${Account_Details.subscription_Valid==true}">
				     <li class="bg_lh"><i class="icon-th"></i> <strong>Status</strong><small>Subscribed </small></li>
				      <li class="bg_lh"><i class="icon-time"></i> <strong>Valid till</strong><small><c:out value="${Account_Details.subscription_Expiry}"/></small></li>				
				</c:when>
				<c:otherwise>
      				<li class="bg_lh"><i class="icon-th"></i> <strong>Status</strong><small>7-Day Trial </small></li>
     				<li class="bg_lh"><i class="icon-time"></i> <strong>Valid till</strong><small><c:out value="${Account_Details.trial_Expiry}"/></small></li>	
<!-- 				     <li class="bg_lh"><i class="icon-th"></i> <strong>Status</strong><small>Subscribed </small></li>   -->   							
				</c:otherwise>
				</c:choose>
  				<c:if test="${Account_Details.account_Owner==1 && Account_Details.account_id!='71'}">
 				 <c:choose>
				  <c:when test="${Account_Details.subscription_Valid==true}">
				     <li class="bg_lh"><a href="${pageContext.request.contextPath}/Subscription_Details" style="color:#fff" onclick="overlayOn();"><i class="icon-cog"></i> <strong>Settings</strong> <small>Subscription Details</small></a></li>
				  </c:when>
				  <c:otherwise>
				      <li class="bg_lh"><a href="${pageContext.request.contextPath}/Subscription_Details" style="color:#fff" onclick="overlayOn();"><i class="icon-cog"></i> <strong>Settings</strong> <small>Subscribe Now</small></a></li>
				      
				  </c:otherwise>				
				 </c:choose>
				</c:if> 
                <li class="bg_lh"><a href="${pageContext.request.contextPath}/Help/EduHappy_The_Complete_Guide.pdf" target=_blank style="color:#fff"><i class="icon-book"></i> <strong>Help</strong> <small>Complete Guide</small></a></li>
                <li class="bg_lh"><a href="https://www.eduhappylms.com/teacher" style="color:#fff" target=_blank><i class="icon-user"></i><strong>Teacher</strong> <small>Teacher Login </small></a></li>
                <li class="bg_lh"><a href="https://www.eduhappylms.com/student" style="color:#fff" target=_blank><i class="icon-user"></i>  <strong>Student </strong> <small>Portal Login</small></a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
<!--End-Chart-box--> 

    <hr/>
    <div class="row-fluid">
      <div class="span6">
        <div class="widget-box">
          <div class="widget-title bg_ly" data-toggle="collapse" href="#collapseG2"><span class="icon"><i class="icon-chevron-down"></i></span>
            <h5>Quick Start</h5>
          </div>
          <div class="widget-content nopadding collapse in" id="collapseG2">
            <ul class="recent-posts">
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="${pageContext.request.contextPath}/img/demo/av1.jpg"> </div>
                <div class="article-post"> <span class="user-info">Create the school profile</span>
                  <p><a href="#">Go to the Schools menu item and then choose <b>New School</b></a> </p>
                </div>
              </li>
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="${pageContext.request.contextPath}/img/demo/av2.jpg"> </div>
                <div class="article-post"> <span class="user-info"> Academic Year </span>
                  <p><a href="#">After you have successfully created the school, you must create the academic year with year name, start and end dates. Please highlight Academic Year menu item and then choose <b>New Academic Year</b>.</a> </p>
                </div>
              </li>
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="${pageContext.request.contextPath}/img/demo/av2.jpg"> </div>
                <div class="article-post"> <span class="user-info"> Year Terms</span>
                  <p><a href="#">While creating the academic year, you can optionally create year terms. Just define how many terms in the year and then specify the name terms, start date, end date, and the vacation start and end dates as well.</a> </p>
                </div>
              </li>
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="${pageContext.request.contextPath}/img/demo/av2.jpg"> </div>
                <div class="article-post"> <span class="user-info"> Semesters</span>
                  <p><a href="#">If you need to create year semesters, you can highlight the Academic Year menu item then choose <b>New Year Semester</b>. You will then have the option to create the semester name and select the terms that belong to this semester.</a> </p>
                </div>
              </li>
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="${pageContext.request.contextPath}/img/demo/av4.jpg"> </div>
                <div class="article-post"> <span class="user-info"> Grades</span>
                  <p><a href="#">Start creating the Grades under each school. Please highlight the Grades menu item and choose <b>New Grade</b></a> </p>
                </div>
              </li>
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="${pageContext.request.contextPath}/img/demo/av4.jpg"> </div>
                <div class="article-post"> <span class="user-info"> Classes</span>
                  <p><a href="#">Similarly, you can move on to create the Classes under each grade by highlighting the Classes menu item and choose <b>New Class.</b></a> </p>
                </div>
              </li>
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="${pageContext.request.contextPath}/img/demo/av4.jpg"> </div>
                <div class="article-post"> <span class="user-info"> Courses</span>
                  <p><a href="#">To create courses under a certain grade, please highlight Courses menu item and select <b>New Course.</b></a> </p>
                </div>
              </li>
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="${pageContext.request.contextPath}/img/demo/av4.jpg"> </div>
                <div class="article-post"> <span class="user-info"> Students and Teachers</span>
                  <p><a href="#">Now since you have created the school, the grades under the school, the courses and classes under each grade, you can start creating the students records as well as the teacher records.</a> </p>
                </div>
              </li>
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="${pageContext.request.contextPath}/img/demo/av4.jpg"> </div>
                <div class="article-post"> <span class="user-info"> Teacher's Authority</span>
                  <p><a href="#">Assign grades, classes, and courses to the teachers in order for them to be able to start taking attendance, give assignments, grade assignments, ...etc.</a> </p>
                </div>
              </li>
              <li>
                <div class="user-thumb"> <img width="40" height="40" alt="User" src="${pageContext.request.contextPath}/img/demo/av4.jpg"> </div>
                <div class="article-post"> <span class="user-info"> Sessions and Schedule</span>
                  <p><a href="#">Build the schedule which is very important for the attendance process. To do this, first create the sessions for each grade from <b>Schedule > Manage Sessions</b> and then build the schedule for each class under that grade from <b>Schedule > Build Schedule</b>.</a> </p>
                </div>
              </li>                            
            </ul>
          </div>
        </div>
       </div>
      <div class="span6">
        <div class="widget-box">
          <div class="widget-title">
            <ul class="nav nav-tabs">
              <li class="active"><a data-toggle="tab" href="#tab1">Schedule</a></li>
              <li><a data-toggle="tab" href="#tab2">Grading Report</a></li>
              <li><a data-toggle="tab" href="#tab3">Attendance</a></li>
            </ul>
          </div>
          <div class="widget-content tab-content">
            <div id="tab1" class="tab-pane active">
              <p>
				Schedule Management System is an integrated and automated system that builds and manages
				the schedule for each classroom in the school. 
			  </p>
              <img src="${pageContext.request.contextPath}/img/demo/Dashboard_Photo1.png" alt="demo-image"/></div>
            <div id="tab2" class="tab-pane"> <img src="${pageContext.request.contextPath}/img/demo/Dashboard_Photo2.png" alt="demo-image"/>
              <p>
                Students will be able to get the full report card with detailed course grading and breakdown for
				categories grading as well as the attendance grading. They will also be able to get a full report
				about the grading per terms, per semester, semester exam grade, and final grade. At the end of
				the report, they will get the overall grading for the whole Grade. They can check any
				assignment grading or any session attendance status and grading.
              </p>
            </div>
            <div id="tab3" class="tab-pane">
              <p>
				Attendance of students is measured by the class teacher on the course level not on the school
				day level. This mean, each course has its own attendance for every student. This is to achieve
				maximum accuracy for attendance and attendance grading.
				The Attendance system is integrated with the Schedule system. For each course, there are
				scheduled sessions. The course may have a session or more per day or it may have no sessions
				at all in some days. Based on these scheduled sessions, the teacher will take the attendance for
				the students in a course. The class teacher will retrieve the session as per the schedule and
				mark the students as one of four attendance status: Present, Absent, Excused, or Late.               
              </p>
              <img src="${pageContext.request.contextPath}/img/demo/Dashboard_Photo3.png" alt="demo-image"/></div>
          </div>
        </div>
       <div class="widget-box">
	        <div class="widget-box collapsible">
	          <div class="widget-title"> <a data-toggle="collapse" href="#collapseOne"> <span class="icon"><i class="icon-arrow-right"></i></span>
	            <h5>Academic Year support </h5>
	            </a> </div>
	          <div id="collapseOne" class="collapse in">
	            <div class="widget-content"> Create school year and add terms and semesters with specific start and end dates. </div>
	          </div>
	          <div class="widget-title"> <a data-toggle="collapse" href="#collapseTwo"> <span class="icon"><i class="icon-arrow-right"></i></span>
	            <h5>Divisions support</h5>
	            </a> </div>
	          <div id="collapseTwo" class="collapse">
	            <div class="widget-content"> If you need to divide the grade into divisions you can create divisions under that specific grade. Create a division for Science and another one for Arts…etc. You can create division and assign courses and classes to that division. </div>
	          </div>
	          <div class="widget-title"> <a data-toggle="collapse" href="#collapseThree"> <span class="icon"><i class="icon-arrow-right"></i></span>
	            <h5>Student Grouping support</h5>
	            </a> </div>
	          <div id="collapseThree" class="collapse">
	            <div class="widget-content"> Create groups under the course and add students to that group. Assign teachers to each group. </div>
	          </div>
	        </div>        
        </div>
       </div>    
      </div>
	</div>
	<div class="rightContent">
	</div>
</div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="includes/AdminFooter.jsp" %></div></div>
</body>
</html>