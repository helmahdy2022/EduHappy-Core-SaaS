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
<title>Teacher Messages</title>
<!-- <style> 	
	a:hover, a:visited, a:link, a:active
	{
	    text-decoration: none;
	}
</style> -->
</head>
<script>
function showMessages(User_id, First_Name, Last_Name, User_Type, Account_Owner, Group_Message)
{

if(Group_Message==1 && User_id!=null)
	{
		var StudentArray = [];
		for (var i=0; i<User_id.length; i++) 
		{
	      StudentArray.push(User_id[i].value);
		}	
	}

if (User_id=="" || User_id==null)
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
  if(Group_Message==1)
	xmlhttp.open("GET","${pageContext.request.contextPath}/List_Messages?User_id="+StudentArray+"&&First_Name="+First_Name+"&&Last_Name="+Last_Name+"&&User_Type="+User_Type+"&&Account_Owner="+Account_Owner+"&&Group_Message="+Group_Message,true);
  else
		xmlhttp.open("GET","${pageContext.request.contextPath}/List_Messages?User_id="+User_id+"&&First_Name="+First_Name+"&&Last_Name="+Last_Name+"&&User_Type="+User_Type+"&&Account_Owner="+Account_Owner+"&&Group_Message="+Group_Message,true);	  
  xmlhttp.send();
}

function createMessage(message_Body, User_id, First_Name, Last_Name, User_Type, Account_Owner, Group_Message, Send_To_Parent)
{
if (message_Body=="")
  {
  alert('Please write a message');
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
	  if(Group_Message!=1 && Send_To_Parent!=1)
	  showMessages(User_id, First_Name, Last_Name, User_Type, Account_Owner);
    }
  };
message_Body = message_Body.replace(/\n|\r\n|\r/g, "%0D%0A");
xmlhttp.open("GET","${pageContext.request.contextPath}/Manage_Messages?message_Body="+message_Body+"&&User_id="+User_id+"&&User_Type="+User_Type+"&&Account_Owner="+Account_Owner+"&&Group_Message="+Group_Message+"&&Send_To_Parent="+Send_To_Parent,true);
xmlhttp.send();
}


function showStudents(Grade_id, Class_id, Course_id, Group_id)
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
	  sessionExpired();
	  document.getElementById("StudentList"+Grade_id+Class_id+Course_id+Group_id).innerHTML=xmlhttp.responseText;
	  if (document.getElementById(Grade_id+Class_id+Course_id+Group_id).name== "+")
		  {
		  	document.getElementById(Grade_id+Class_id+Course_id+Group_id).src="${pageContext.request.contextPath}/images/-.jpg";
		  	document.getElementById(Grade_id+Class_id+Course_id+Group_id).name="-";
		  }
	  else
		  {
		  	document.getElementById(Grade_id+Class_id+Course_id+Group_id).src="${pageContext.request.contextPath}/images/+.jpg";
		    document.getElementById("StudentList"+Grade_id+Class_id+Course_id+Group_id).innerHTML="";
		    document.getElementById(Grade_id+Class_id+Course_id+Group_id).name="+";
		  }
	  document.getElementById("messageArea").innerHTML="";
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Teacher_Students?Grade_id="+Grade_id+"&Class_id="+Class_id+"&Course_id="+Course_id+"&Group_id="+Group_id,true);
xmlhttp.send();
}

//Pass the checkbox name to the function
function getCheckedBoxes(Student) {
var checkboxes = document.getElementsByName(Student);
var checkboxesChecked = [];
// loop over them all
for (var i=0; i<checkboxes.length; i++) {
   // And stick the checked ones onto an array...
   if (checkboxes[i].checked) {
      checkboxesChecked.push(checkboxes[i]);
   }
}
// Return the array if it is non-empty, or null
return checkboxesChecked.length > 0 ? checkboxesChecked : null;
}

//Call as
var checkedBoxes = getCheckedBoxes("Student");
///////////Function for Select All ///////////////////////
function selectAll(source, Grade_id, Class_id, Course_id, Group_id) {
	checkboxes = document.getElementsByName('Student'+Grade_id+Class_id+Course_id+Group_id);
	  for(var i=0, n=checkboxes.length;i<n;i++) {
	    checkboxes[i].checked = source.checked;
	  }
	  document.getElementById("messageArea").innerHTML="";
	}
/////////////////////////////////////////////////////////
</script>
<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300"
<c:if test="${param.Session_id ne null && param.Session_id ne 'null' && not empty param.Session_id}">
 onload="showCurrentSession(${param.Session_id}, 'Current','${param.Time_From}','${param.Time_To}');"
</c:if>
>

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
            <li class="crumb-trail">Messages</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Teacher/Messages/Initiate_Teacher_Messages.jsp">Teacher Messages</a>
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
						<c:if test="${not empty Senders.student_id}"><div class="StudentsInConversation">Students in Conversation</div></c:if>
				  		 <ul class="contact-list">
				  		 <c:forEach items="${Senders.student_id}" var="Sender_id" varStatus="position">
							<c:if test="${Sender_id!=sessionScope['Teacher_Session']}">
								<li id="user-Alex" class="online">
								<a href="javascript:showMessages(${Sender_id},'<c:out value='${Senders.student_First_Name[position.index]}'/>','<c:out value='${Senders.student_Last_Name[position.index]}'/>', 'Student', 'Teacher')">
								<span>
								<c:out value='${Senders.student_First_Name[position.index]}'/> <c:out value='${Senders.student_Last_Name[position.index]}'/>
								<c:if test="${Senders.student_NewMessages[position.index]!=0}">
								<div class="newMessagesSign"><c:out value='${Senders.student_NewMessages[position.index]}'/></div>
								</c:if>
								</span>
								</a>
								</li>
							</c:if>
						</c:forEach>
						</ul>
						<div class="MyStudents">My Students</div>
						<div>
							<c:forEach items="${TeacherAuthority.authorityList}" var="AuthorityList">
			            <div class="panel-body pn">
			              <div class="table-responsive">
			                <div class="bs-component">							
							<table class="table table-bordered mbn">
							<tr>
							<td style="width:33px;vertical-align:top;">
							  <img id="${AuthorityList[2]}${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" name="+" src="${pageContext.request.contextPath}/images/+.jpg" width="20" height="20"  onClick="showStudents('${AuthorityList[2]}' ,'${AuthorityList[4]}', '${AuthorityList[6]}', '${AuthorityList[8]}');">							
							</td>
							<td>
							 <h6><c:out value="${AuthorityList[1]} - ${AuthorityList[3]} - ${AuthorityList[5]} - ${AuthorityList[7]}  ${AuthorityList[9]}"/></h6>
							<div id="StudentList${AuthorityList[2]}${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}"></div>
							</td>
							</tr>
							</table>
							</div>
						  </div>
						</div>						
							</c:forEach>						
						</div>
				        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
				          <h5>Teachers</h5>
				        </div>
						<c:if test="${fn:length(Senders.teacher_id) gt 1}"><div class="StudentsInConversation">Teachers in Conversation</div></c:if><br/>
				  		 <ul class="contact-list">
				  		 <c:forEach items="${Senders.teacher_id}" var="Sender_id" varStatus="position">
							<c:if test="${Sender_id!=sessionScope['Teacher_Session']}">
								<li id="user-Alex" class="online">
								<a href="javascript:showMessages(${Sender_id},'${Senders.teacher_First_Name[position.index]}','${Senders.teacher_Last_Name[position.index]}', 'Teacher', 'Teacher')">
								<span>
								<c:out value="${Senders.teacher_First_Name[position.index]} ${Senders.teacher_Last_Name[position.index]}"/>
								<c:if test="${Senders.teacher_NewMessages[position.index]!=0}">
								<div class="newMessagesSign"><c:out value="${Senders.teacher_NewMessages[position.index]}"/></div>
								</c:if>
								</span>
								</a>
								</li>
							</c:if>
						</c:forEach>
						</ul>
						<div class="MyStudents">All Teachers</div>
				  		 <ul class="contact-list">
				  		<c:forEach items="${Teachers.teacher_id}" var="Teacher_id" varStatus="position">
							<c:if test="${Teacher_id!=sessionScope['Teacher_Session']}">
								<li id="user-Alex" class="online">
								<a href="javascript:showMessages(${Teacher_id},'${Teachers.first_Name[position.index]}','${Teachers.last_Name[position.index]}', 'Teacher', 'Teacher')">
								<span>
								<c:out value="${Teachers.first_Name[position.index]} ${Teachers.last_Name[position.index]}"/>
								</span>
								</a>
								</li>
							</c:if>
						</c:forEach>
						</ul>						
				        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
				        <h5>Admins</h5>
				        </div>						
						<c:if test="${not empty Senders.admin_id}"><div class="StudentsInConversation">Admins in Conversation</div></c:if>
				  		 <ul class="contact-list">
				  		 <c:forEach items="${Senders.admin_id}" var="Sender_id" varStatus="position">
								<li id="user-Alex" class="online">
								<a href="javascript:showMessages(${Sender_id},'${Senders.admin_First_Name[position.index]}','${Senders.admin_Last_Name[position.index]}', 'Admin', 'Teacher')">
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
						<div class="MyStudents">All Administrators</div>
				  		 <ul class="contact-list">
				  		 <c:forEach items="${Admins.admin_id}" var="Admin_id" varStatus="position">
								<li id="user-Alex" class="online">
								<a href="javascript:showMessages(${Admin_id},'${Admins.first_Name[position.index]}','${Admins.last_Name[position.index]}', 'Admin', 'Teacher')">
								<span>
								<c:out value="${Admins.first_Name[position.index]} ${Admins.last_Name[position.index]}"/>
								</span>
								</a>
								</li>
						</c:forEach>
						</ul>										

				        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
				        <h5>Parents</h5>
				        </div>						
						<c:if test="${not empty Senders.parent_id}"><div class="StudentsInConversation">Parents in Conversation</div></c:if>
				  		 <ul class="contact-list">
				  		 <c:forEach items="${Senders.parent_id}" var="Sender_id" varStatus="position">
								<li id="user-Alex" class="online">
								<a href="javascript:showMessages(${Sender_id},'${Senders.parent_First_Name[position.index]}','${Senders.parent_Last_Name[position.index]}', 'Parent', 'Teacher')">
								<span>
								<c:out value="${Senders.parent_First_Name[position.index]} ${Senders.parent_Last_Name[position.index]}"/>
									<c:if test="${Senders.parent_NewMessages[position.index]!=0}">
									<div class="newMessagesSign"><c:out value="${Senders.parent_NewMessages[position.index]}"/></div>
									</c:if>
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