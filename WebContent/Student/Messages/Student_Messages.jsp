<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
<link href="${pageContext.request.contextPath}/_css/Admin_Main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/fullcalendar.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/datepicker.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/colorpicker.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-wysihtml5.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/uniform.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/select2.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-media.css" />
<link href="${pageContext.request.contextPath}/Global/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/jquery.gritter.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/jquery-ui.css" />
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<meta charset="utf-8">
<title>Student Messages</title>

</head>
<script>
function showMessages(User_id, First_Name, Last_Name, User_Type, Account_Owner)
{
if (User_id=="")
  {
  document.getElementById("messageArea").innerHTML="";
  return;
  } 
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
	  sessionExpired();
	  document.getElementById("messageArea").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Messages?User_id="+User_id+"&&First_Name="+First_Name+"&&Last_Name="+Last_Name+"&&User_Type="+User_Type+"&&Account_Owner="+Account_Owner,true);
xmlhttp.send();
}

function createMessage(message_Body, User_id, First_Name, Last_Name, User_Type, Account_Owner)
{
if (message_Body=="")
  {
  document.getElementById("messageArea").innerHTML="";
  return;
  } 
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
	  sessionExpired();
	  document.getElementById("messageArea").innerHTML=xmlhttp.responseText;
	  
	  showMessages(User_id, First_Name, Last_Name, User_Type, Account_Owner);
    }
  };
message_Body = message_Body.replace(/\n|\r\n|\r/g, "%0D%0A");
xmlhttp.open("GET","${pageContext.request.contextPath}/Manage_Messages?User_id="+User_id+"&&User_Type="+User_Type+"&&Account_Owner=Student&&message_Body="+message_Body,true);
xmlhttp.setRequestHeader('Content-Type', 'text/plain; charset="utf-8"');
xmlhttp.send();
}
</script>
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
            <li class="crumb-trail">Messages</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Messages/Initiate_Student_Messages.jsp">Student Messages</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
		  <div class="container-fluid">
		  	<%@include file="../includes/Adsense_Top.jsp" %>		  
		  <hr>
		    <div class="row-fluid">
		      <div class="span12">
		        <div class="widget-box widget-chat">
		          <div class="widget-title"> <span class="icon"> <i class="icon-comment"></i> </span>
		            <h5>All Users</h5>
		          </div>
		          <div class="widget-content nopadding" style="min-height:550px">
		            <div class="chat-users panel-right2">
		              <div class="panel-content nopadding" >								  							        						
				        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
				          <h5>Students</h5>
				        </div>
						<c:if test="${fn:length(Senders.student_id) gt 1}"><div class="StudentsInConversation">Students in Conversation</div></c:if>
				  		 <ul class="contact-list">
				  		 <c:forEach items="${Senders.student_id}" var="Sender_id" varStatus="position">
							<c:if test="${Sender_id!=sessionScope['Student_Session']}">
								<li id="user-Alex" class="online">
								<a href="javascript:showMessages(${Sender_id},'${Senders.student_First_Name[position.index]}','${Senders.student_Last_Name[position.index]}', 'Student', 'Student')">
								<span>
								<c:out value="${Senders.student_First_Name[position.index]}"/> <c:out value="${Senders.student_Last_Name[position.index]}"/>
								<c:if test="${Senders.student_NewMessages[position.index]!=0}">
								<div class="newMessagesSign"><c:out value="${Senders.student_NewMessages[position.index]}"/></div>
								</c:if>
								</span>
								</a>
								</li>
							</c:if>
						</c:forEach>
						</ul>
						<c:if test="${not empty Students.student_id}"><div class="AllClassStudents">All Class Students</div></c:if>	
						<div>
						<ul class="contact-list">
						<c:forEach items="${Students.student_id}" var="Student_id" varStatus="position">
						<c:set var="StudentInConversation" value="No"></c:set>
						<c:forEach items="${Senders.student_id}" var="Sender_id">
						<c:if test="${Student_id == Sender_id }"><c:set var="StudentInConversation" value="Yes"></c:set></c:if>
						</c:forEach>			            
						<c:if test="${Student_id!=sessionScope['Student_Session'] && StudentInConversation=='No'}">
							<li id="user-Alex" class="online">
							<a href="javascript:showMessages(${Student_id},'${Students.first_Name[position.index]}','${Students.last_Name[position.index]}', 'Student', 'Student')">
							<span>
							<c:out value="${Students.first_Name[position.index]} ${Students.last_Name[position.index]}"/>
							</span>
							</a>
							</li>
						</c:if>			            						
						</c:forEach>
						</ul>						
						</div>
				        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
				          <h5>Teachers</h5>
				        </div>
						<c:if test="${not empty Senders.teacher_id}"><div class="TeachersInConversation">Teachers in Conversation</div></c:if>
				  		 <ul class="contact-list">
				  		 <c:forEach items="${Senders.teacher_id}" var="Sender_id" varStatus="loop">
							<li id="user-Alex" class="online">
							<a href="javascript:showMessages(${Sender_id},'${Senders.teacher_First_Name[loop.index]}','${Senders.teacher_Last_Name[loop.index]}', 'Teacher', 'Student')">
							<span>
							<c:out value="${Senders.teacher_First_Name[loop.index]} ${Senders.teacher_Last_Name[loop.index]}"/>
							<c:if test="${Senders.teacher_NewMessages[loop.index]!=0}">
							<div class="newMessagesSign"><c:out value="${Senders.teacher_NewMessages[loop.index]}"/></div>
							</c:if>
							</span>
							</a>
							</li>
						</c:forEach>
						</ul>															
						<div class="AllTeachers">All Teachers</div>
				  		 <ul class="contact-list">
				  		<c:forEach items="${Teachers.teacher_id}" var="Teacher_id" varStatus="position">
							<li id="user-Alex" class="online">
							<a href="javascript:showMessages(${Teacher_id},'${Teachers.first_Name[position.index]}','${Teachers.last_Name[position.index]}', 'Teacher', 'Student')">
							<span>
							<c:out value="${Teachers.first_Name[position.index]} ${Teachers.last_Name[position.index]}"/>
							</span>
							</a>
							</li>
						</c:forEach>
						</ul>						
				        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
				        <h5>Admins</h5>
				        </div>
						<c:if test="${not empty Senders.admin_id}"><div class="StudentsInConversation">Admins in Conversation</div></c:if>
				  		 <ul class="contact-list">
				  		 <c:forEach items="${Senders.admin_id}" var="Sender_id" varStatus="position">
							<li id="user-Alex" class="online">
							<a href="javascript:showMessages(${Sender_id},'${Senders.admin_First_Name[position.index]}','${Senders.admin_Last_Name[position.index]}', 'Admin', 'Student')">
							<span>
							<c:out value="${Senders.admin_First_Name[position.index]} ${Senders.admin_Last_Name[position.index]}"/>
							<c:if test="${Senders.admin_NewMessages[position.index]!=0}">
							<div class="newMessagesSign"><c:out value="${Senders.admin_NewMessages[position.index]}"/></div>
							</c:if>
							</span>
							</a>
							</li>
						</c:forEach>
						</ul>	
						<div class="AllTeachers">All Administrators</div>
				  		 <ul class="contact-list">
				  		 <c:forEach items="${Admins.admin_id}" var="Admin_id" varStatus="position">
							<li id="user-Alex" class="online">
							<a href="javascript:showMessages(${Admin_id},'${Admins.first_Name[position.index]}','${Admins.last_Name[position.index]}', 'Admin', 'Student')">
							<span>
							<c:out value="${Admins.first_Name[position.index]} ${Admins.last_Name[position.index]}"/>
							</span>
							</a>
							</li>
						</c:forEach>
						</ul>										
					   </div>
		              </div>
		              <div class="chat-content panel-left2">		                
						<div id="messageArea"></div>	
						
		             </div>	                		                		              		
		            </div>
		          </div>
		        </div>
		      </div>
	  		 <%@include file="../includes/Adsense_Bottom.jsp" %>
		    </div>  
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