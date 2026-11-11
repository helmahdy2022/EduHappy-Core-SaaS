<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.owasp.encoder.Encoder" %>
<html>
<head>
<meta charset="utf-8">
<title>Admin Messages</title>

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

	  //document.getElementById("messageArea").innerHTML=xmlhttp.responseText;
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
  //document.getElementById("messageArea").innerHTML="Please write a message";
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


function showGrades(School_id)
{
if (School_id=="")
  {
	   document.getElementById("Grade").innerHTML="<option value=''>Select Grade</option>";
	   document.getElementById("Class").innerHTML="<option value=''>Select Class</option>";
	   document.getElementById("Route_id").innerHTML="<option value=''>Select Route</option>";
	   document.getElementById("Student").innerHTML="";
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
       document.getElementById("Grade").innerHTML=xmlhttp.responseText;
	   document.getElementById("Class").innerHTML="<option value=''>Select Class</option>";
	   document.getElementById("Route_id").innerHTML="<option value=''>Select Route</option>";
	   document.getElementById("Student").innerHTML="";
	   document.getElementById("messageArea").innerHTML="";
	   showTransportRoutes(School_id);
    }
  };
  xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+School_id,true);
xmlhttp.send();
}
//==========End of showGrades function=================
function showClasses(str)
{
if (str=="")
  {
	   document.getElementById("Class").innerHTML="<option value=''>Select Class</option>";
	   document.getElementById("Student").innerHTML="";
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
	     document.getElementById("Class").innerHTML=xmlhttp.responseText;
	     document.getElementById("Student").innerHTML="";
	     document.getElementById("messageArea").innerHTML="";
    }
  };
  xmlhttp.open("GET","${pageContext.request.contextPath}/List_Classes?Grade_id="+str,true);
xmlhttp.send();
}

function showStudent(First_Name, Last_Name, Username, Student_id, Class_id, School_id, Grade_id, Route_id)
{
document.getElementById("Student").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (First_Name==""&&Last_Name==""&&Username==""&&Student_id==""&&Class_id==""&&School_id==""&&Grade_id==""&&Route_id=="") 
  {
  document.getElementById("Student").innerHTML="";
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
    document.getElementById("Student").innerHTML=xmlhttp.responseText;
    document.getElementById("messageArea").innerHTML="";
    //$('#students').DataTable();
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Students?First_Name="+First_Name+"&Last_Name="+Last_Name+"&Username="+Username+"&Student_id="+Student_id+"&Route_id="+Route_id+"&Class_id="+Class_id+"&School_id="+School_id+"&Grade_id="+Grade_id+"&Page=Message",true);
xmlhttp.send();
}
///////////Function for Select All ///////////////////////
function selectAll(source) {
	  checkboxes = document.getElementsByName('Student');
	  for(var i=0, n=checkboxes.length;i<n;i++) {
	    checkboxes[i].checked = source.checked;
	  }
	  document.getElementById("messageArea").innerHTML="";
	}
/////////////////////////////////////////////////////////
function displaySearchForm()
{
	if(document.getElementById('studentSearch').style.display=='none')
		{
			document.getElementById('studentSearch').style.display='block';
		}
	else
		{
			document.getElementById('studentSearch').style.display='none';
		}
	
}
////////////////////////////////////////////////////////////////////
 function showTransportRoutes(School_id)
 {
 if (School_id=="")
   {
 	document.getElementById("Route_id").innerHTML="<option value=''>Select Route</option>";
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
 	  document.getElementById("Route_id").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_All_Routes?School_id="+School_id,true);
 xmlhttp.send();
 }
</script>
<body>
<div>
  <%@include file="../includes/AdminMenu.jsp" %>
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Messages</a> <a href="${pageContext.request.contextPath}/Admin/Messages/Initiate_Admin_Messages.jsp" class="current">Messages</a> </div>
		  <h1>Messages</h1>
		</div>
		<!--End-breadcrumbs-->	
		  <div class="container-fluid">
		  	<%@include file="../includes/Adsense_Top.jsp" %>		  
		  <hr>
		    <div class="row-fluid">
		      <div class="span12">
		        <div class="widget-box widget-chat">
		          <div class="widget-title"> <span class="icon"> <i class="icon-comment"></i> </span>
		            <h5>All Users</h5>
		          </div>
		          <div class="widget-content nopadding">
		            <div class="chat-users panel-right2">
		              <div class="panel-content nopadding">
				        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
				          <h5>Teachers</h5>
				        </div>
						<c:if test="${not empty Senders.teacher_id}">
						<div class="StudentsInConversation">Teachers in Conversation</div>
						</c:if>
				  		<c:forEach items="${Senders.teacher_id}" var="Sender_id" varStatus="position">
							<c:if test="${Sender_id!=sessionScope['Admin_Session']}">
			                  <ul class="contact-list">
			                  <li id="user-Alex" class="online">
			  					<a href="javascript:showMessages(${Sender_id},'<c:out value='${Senders.teacher_First_Name[position.index]}'/>','<c:out value='${Senders.teacher_Last_Name[position.index]}'/>', 'Teacher', 'Admin')">
								<span>
								<c:out value='${Senders.teacher_First_Name[position.index]}'/> <c:out value=' ${Senders.teacher_Last_Name[position.index]}'/>
								<c:if test="${Senders.teacher_NewMessages[position.index]!=0}">
								<div class="newMessagesSign"><c:out value='${Senders.teacher_NewMessages[position.index]}'/></div>
								</c:if>
								</span>
								</a>
			                  </li>
			                </ul>
							</c:if>
						</c:forEach>
			
				  		<div class="MyStudents">All Teachers</div>
						 <ul class="contact-list">
				  			 <c:forEach items="${Teachers.teacher_id}" var="Teacher_id" varStatus="position">              
				                 <li id="user-Alex" class="online">
									<a href="javascript:showMessages(${Teacher_id},'<c:out value='${Teachers.first_Name[position.index]}'/>','<c:out value='${Teachers.last_Name[position.index]}'/>', 'Teacher', 'Admin')">
									<span>
									<c:out value='${Teachers.first_Name[position.index]}'/> <c:out value='${Teachers.last_Name[position.index]}'/> 
									</span>
									</a>
								</li>				
							</c:forEach>
						</ul>
 		        						
				        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
				          <h5>Students</h5>
				        </div>
						<c:if test="${not empty Senders.student_id}"><div class="StudentsInConversation">Students in Conversation</div></c:if>
				  		 <ul class="contact-list">
				  		 <c:forEach items="${Senders.student_id}" var="Sender_id" varStatus="position">
							<c:if test="${Sender_id!=sessionScope['Teacher_Session']}">
								<li id="user-Alex" class="online">
								<a href="javascript:showMessages(${Sender_id},'<c:out value='${Senders.student_First_Name[position.index]}'/>','<c:out value='${Senders.student_Last_Name[position.index]}'/>', 'Student', 'Admin')">
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

				        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
				          <h5>Parents</h5>
				        </div>
						<c:if test="${not empty Senders.parent_id}">
						<div class="StudentsInConversation">Parents in Conversation</div>
						</c:if>
				  		<c:forEach items="${Senders.parent_id}" var="Sender_id" varStatus="position">
							<c:if test="${Sender_id!=sessionScope['Admin_Session']}">
			                  <ul class="contact-list">
			                  <li id="user-Alex" class="online">
			  					<a href="javascript:showMessages(${Sender_id},'<c:out value='${Senders.parent_First_Name[position.index]}'/>','<c:out value='${Senders.parent_Last_Name[position.index]}'/>', 'Parent', 'Admin')">
								<span>
								<c:out value='${Senders.parent_First_Name[position.index]}'/> <c:out value=' ${Senders.parent_Last_Name[position.index]}'/>
								<c:if test="${Senders.parent_NewMessages[position.index]!=0}">
								<div class="newMessagesSign"><c:out value='${Senders.parent_NewMessages[position.index]}'/></div>
								</c:if>
								</span>
								</a>
			                  </li>
			                </ul>
							</c:if>
						</c:forEach>
						
						<div class="MyStudents"><a href="#" onclick="displaySearchForm();" style="color:#fff">Search Students</a></div>
						<!-- ----------------------------------------------------------------------------------------------------->
						<div id="studentSearch" style="display:none">
						   <form name="form1" action="">          
					            <div class="control-group">
								  <label class="control-label">School Name</label>
					              <div class="controls">  								              
						          	<select name="School" id="School" class="span11" onChange="showGrades(this.value)">
						 			 <option value=""> 
									  	Select School
									  </option> 
									  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
								       	<option value="${School_id}"><c:out value='${Schools.school_Name[position.index]}'/></option>
								      </c:forEach>	
									</select> 
								  </div>
					            </div>
					
								<div class="control-group">
								  <label class="control-label">Grade Name</label>
					              <div class="controls"> 			 		 						
									 <select name="Grade" id="Grade" class="span11" onChange="showClasses(this.value)">
							 			 <option value="">Select Grade</option>
							 		</select>  					  
									  </div>
									</div>
					
								<div class="control-group">
								  <label class="control-label">Classroom Name</label>
					              <div class="controls">						
						 	         <select name="Class" id="Class" class="span11" onChange="document.getElementById('Student').innerHTML=''; document.getElementById('messageArea').innerHTML='';">
							 			 <option value="">Select Classroom</option>
							 		</select> 
									  </div>
									</div>
								<div class="control-group">
								  <label class="control-label">First Name</label>
					              <div class="controls">
					          			 <input type="text" name="First_Name" id="First_Name" class="span11">					
									  </div>
									</div> 					 
									<div class="control-group">
								  <label class="control-label">Last Name</label>
					              <div class="controls">
					          			  <input type="text" name="Last_Name" id="Last_Name" class="span11">			
									  </div>
									</div>
									<div class="control-group">
								  <label class="control-label">Username</label>
					              <div class="controls">
					          			 <input name="Username" type="text" id="Username" class="span11">		
									  </div>
									</div>  					
								  <div class="control-group">
								  	<label class="control-label">Student ID</label>
					              	  <div class="controls">
					          			 <input name="Student_id" type="number" class="span11" id="Student_id">		
									  </div>
								  </div> 
				  				  <div class="control-group">
									  <label class="control-label">Transport Routes</label>
						              <div class="controls">						
							 	         <select name="Route_id" id="Route_id" class="span11" onchange="document.getElementById('Student').innerHTML=''">
								 			 <option value="">Select Route</option>
								 		</select> 
				 					  </div>
				 				  </div>
					          <div class="submitHolder">
					            <input type="button" name="Submit" id="Submit" value="Get Student" onClick="showStudent(First_Name.value, Last_Name.value, Username.value, Student_id.value, Class.value, School.value, Grade.value, Route_id.value)">
					          </div>
					
					      		<div id="Student"></div>
					 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
					    </form>
					    <!-- ----------------------------------------------------------------------------------------------------->
					    </div>
		              </div>
		            </div>
		            <div class="chat-content panel-left2">		                
							<div id="messageArea"></div>		                		                		              		
		            </div>
		          </div>
		        </div>
		      </div>
		    </div>
		  	<%@include file="../includes/Adsense_Top.jsp" %>
		  </div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>