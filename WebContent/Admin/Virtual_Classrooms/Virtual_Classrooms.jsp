<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Virtual Classrooms</title>
</head>
<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Virtual Classrooms</a> <a href="${pageContext.request.contextPath}/Admin/Classes/Virtual_Classrooms.jsp" class="current">Virtual Classroom</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Virtual Classrooms</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Virtual Classrooms</h5>
		        </div>
		        <div class="widget-content nopadding">
					Virtual classroom is very important feature especially when it comes to working from home and studying from home for both teachers and students. 
					Virtual Classrooms is supported in EduHappy for online learning and online meetings with video conferencing that allows for live interaction between the teachers and the students as they are participating in learning activities. 
					hence, the virtual classroom allows the teachers and the students work together simultaneously even if they are home.
				<br/><br/>
					Virtual Classrooms is well integrated with the Schedule of both students and teachers. 
				<br/><br/>	
					From the student schedule, the system will detect the current schedule session and will automatically provide the students a link. 
					With just a click of a mouse, the students will go directly to the online meeting room where they will be able to meet their teachers online. 
				<br/><br/>
					Same for the teachers, from their schedule, the system will detect the the current session and will provide the teachers with the link for the online meeting room 
					and, again, with just a click of a mouse, the teachers will go directly to the online meeting room where they can meet their students online 
					and start interacting with their students with video conference and all virtual classroom features such as joining with microphone and camera, sharing their screen, 
					chat, upload presentation, share external video, whiteboard, breakout rooms and polling. 				

		        </div>
		     </div>	
		   </div>
			
		   <div  class="span6">
		   	<div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Quick Hints</h5>
		        </div>
					<ul>
					<li>
						To activate the virtual classrooms, you need to build the schedule first.
					</li>
					<li>
						Go to Schedule -> Manage Sessions in create the sessions per each grade. 
					</li>
					<li>
						Then go to Schedule -> Build Schedule to create the schedule for each class.
					</li>
					<li>
						Once done, the teachers and the students will get the online meeting links in the sessions that matches their current time.
					</li>
					</ul>     
		   			<hr>
		   </div>
			<div class="widget-box">
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
			</div>	
		   </div>	
			<img src="../../images/Virtual_Classrooms_Description.jpg" alt="Virtual Classrooms" width="1644" height="564">
		 </div>
		</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>