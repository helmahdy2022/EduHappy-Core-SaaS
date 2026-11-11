<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${Student_Account_Details.subscription_Valid==false && Student_Account_Details.trial_Valid==false}">
	<jsp:forward page="/StudentLogin"/>
</c:if>
<c:if test="${Student_Account_Details.student_First_Login==1}">
	<jsp:forward page="${pageContext.request.contextPath}/Student/Profile/Change_Password.jsp"/>
</c:if>
<html>
<head>
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <title>Student Dashboard</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="${pageContext.request.contextPath}/_css/Student_Main.css" rel="stylesheet" type="text/css"> 
  <!-- Font CSS (Via CDN) -->
  <link rel='stylesheet' type='text/css' href='https://fonts.googleapis.com/css?family=Open+Sans:300,400,600'>

  <!-- Theme CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/theme.css">

  <!-- Admin Forms CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/admin-forms.css">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/summernote.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/icomoon.css">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
			<!-- Global site tag (gtag.js) - Google Analytics -->
			<script async src="https://www.googletagmanager.com/gtag/js?id=UA-75892673-4"></script>
			<script>
			  window.dataLayer = window.dataLayer || [];
			  function gtag(){dataLayer.push(arguments);}
			  gtag('js', new Date());
			
			  gtag('config', 'UA-75892673-4');
			</script>

<script>


  function sessionExpired()
  {
 	  if(xmlhttp.responseText=='SessionExpired')
 		  {
 	      	window.location.href = "${pageContext.request.contextPath}/Student/Student_Login.jsp";
 		  }
  }
  function checkSession()
  {
  	if (window.XMLHttpRequest)
  	  {// code for IE7+, Firefox, Chrome, Opera, Safari
  	  xmlhttp=new XMLHttpRequest();
  	  }
  	else
  	  {// code for IE6, IE5
  	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	  }
  	xmlhttp.onreadystatechange=function()
  	  {
  	     if (xmlhttp.readyState==4 && xmlhttp.status==200)
  	     {
  			if(xmlhttp.responseText=='SessionExpired')
  			{
  				window.location.href = "${pageContext.request.contextPath}/Student/Student_Login.jsp";
  			}
  	     }
  	  };
  	xmlhttp.open("GET","${pageContext.request.contextPath}/Check_Session?User=Student",true);
  	xmlhttp.send();
  }
  var timeoutID;

  function setup() {
      this.addEventListener("mousemove", resetTimer, false);
      this.addEventListener("mousedown", resetTimer, false);
      this.addEventListener("keypress", resetTimer, false);
      this.addEventListener("DOMMouseScroll", resetTimer, false);
      this.addEventListener("mousewheel", resetTimer, false);
      this.addEventListener("touchmove", resetTimer, false);
      this.addEventListener("MSPointerMove", resetTimer, false);
   
      startTimer();
  }
  setup();
   
  function startTimer() {
      // wait 2 seconds before calling goInactive
      timeoutID = window.setTimeout(goInactive, '${pageContext.session.maxInactiveInterval}000');
  }
   
  function resetTimer(e) {
      window.clearTimeout(timeoutID);
   
      goActive();
  }
   
  function goInactive() {
      // do something
  	window.location = "${pageContext.request.contextPath}/Student/Student_Login.jsp";
  }
   
  function goActive() {
      // do something
           
      startTimer();
  }
</script>
</head>

    <!-----------------------------------------------------------------+ 
       ".navbar" Helper Classes: 
    -------------------------------------------------------------------+ 
       * Positioning Classes: 
        '.navbar-static-top' - Static top positioned navbar
        '.navbar-static-top' - Fixed top positioned navbar

       * Available Skin Classes:
         .bg-dark    .bg-primary   .bg-success   
         .bg-info    .bg-warning   .bg-danger
         .bg-alert   .bg-system 
    -------------------------------------------------------------------+
      Example: <header class="navbar navbar-fixed-top bg-primary">
      Results: Fixed top navbar with blue background 
    ------------------------------------------------------------------->

<c:set var = "Source" value = "Student"/>
<div id="overlay">
  <div id="text"><img src="${pageContext.request.contextPath}/images/Loader.gif"></div>
</div>
    <!-- Start: Header -->
    <header class="navbar navbar-fixed-top">
      <div class="navbar-branding">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/Student/Student_Main.jsp">
          <img src="${pageContext.request.contextPath}/img/logo_colored_text.png">
        </a>
        <span id="toggle_sidemenu_l" class="ad ad-lines"></span>
      </div>
      <ul class="nav navbar-nav navbar-left">
        <li>
          <a class="sidebar-menu-toggle" href="#">
            <span class="ad ad-ruby fs18"></span>
          </a>
        </li>
        <li>
          <a class="topbar-menu-toggle" href="#">
            <span class="ad ad-wand fs16"></span>
          </a>
        </li>
        <li class="hidden-xs">
          <a class="request-fullscreen toggle-active" href="#">
            <span class="ad ad-screen-full fs18"></span>
          </a>
        </li>
      </ul>
      <form class="navbar-form navbar-left navbar-search" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search...">
        </div>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <span class="fa fa-envelope"></span><span class="newMessagesSign">${sessionScope['newStudentMessagesCount']}</span>
          </a>
          <ul class="dropdown-menu media-list w350 animated animated-shorter fadeIn" role="menu">
            <li class="dropdown-header">
              <span class="dropdown-title"> Notifications</span>
              <span class="label label-danger">${sessionScope['newStudentMessagesCount']}</span>
            </li>
            <li class="media">
              <a class="media-left" href="${pageContext.request.contextPath}/Student/Messages/Initiate_Student_Messages.jsp"> <img src="${pageContext.request.contextPath}/img/avatars/messages.jpg" class="mw40" alt="avatar"> </a>
              <div class="media-body">
                <h5 class="media-heading">Messages
                  <small class="text-muted"></small>
                </h5> You have ${sessionScope['newStudentMessagesCount']} new messages
                <a class="text-system" href="${pageContext.request.contextPath}/Student/Messages/Initiate_Student_Messages.jsp" onClick="overlayOn();"> Inbox </a>
              </div>
            </li>
          </ul>
        </li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <span class="flag-xs flag-us"></span> US
          </a>
          <ul class="dropdown-menu pv5 animated animated-short flipInX" role="menu">
            <li>
              <a href="${pageContext.request.contextPath}/Student/Student_Main.jsp">
                <span class="flag-xs flag-us mr10"></span> English </a>
            </li>
          </ul>
        </li>
        <li class="menu-divider hidden-xs">
          <i class="fa fa-circle"></i>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle fw600 p15" data-toggle="dropdown"> 
			<c:choose>
			<c:when test="${not empty Student_Account_Details.fileName && Student_Account_Details.fileName!=null}">
				<img width="30" class="mw30 br64 mr15" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Student_Account_Details.account_id}/Schools/${Student_Account_Details.school_id}/Students/Photos/${Student_Account_Details.student_id}/${Student_Account_Details.fileName}">
			</c:when>
			<c:otherwise>
				<img width="30" class="mw30 br64 mr15" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
			</c:otherwise>
			</c:choose>          
          ${Student_Account_Details.first_Name} ${Student_Account_Details.last_Name}
            <span class="caret caret-tp hidden-xs"></span>
          </a>
          <ul class="dropdown-menu list-group dropdown-persist w250" role="menu">
            <li class="list-group-item">
  			  <a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_Account_Details.student_id}&&Source=view" onClick="checkSession(); overlayOn();" class="animated animated-short fadeInUp">
                <span class="fa fa-user"></span> My Profile
              </a>
            </li>
             <li class="list-group-item">
              <a href="${pageContext.request.contextPath}/Student/Profile/Change_Password.jsp" class="animated animated-short fadeInUp" onClick="overlayOn();">
                <span class="imoon imoon-key"></span> Change Password  </a>
            </li>
             <li class="list-group-item">
              <a href="${pageContext.request.contextPath}/Student/ExamSchedule/Exam_Schedule.jsp" class="animated animated-short fadeInUp" onClick="overlayOn();">
                <span class="fa fa-list"></span> Exam Schedule </a>
            </li>
            <li class="dropdown-footer">
              <a href="${pageContext.request.contextPath}/Student/Student_Signout.jsp" class="" onClick="overlayOn();">
              <span class="fa fa-power-off pr5"></span> Logout </a>
            </li>
          </ul>
        </li>
      </ul>

    </header>
    <!-- End: Header -->


