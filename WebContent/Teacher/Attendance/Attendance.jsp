<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Attendance</title>

<script>

 function showSessions(str,Grade_id, Class_id, Course_id, Group_id, selectedRow)
 {
  document.getElementById("Session").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
  if (str=="")
   {
   document.getElementById("Session").innerHTML="";
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
	   	document.getElementById("Student").innerHTML="";
	   	document.getElementById("Attendance").innerHTML="";
		document.getElementById("Session").innerHTML=xmlhttp.responseText;
		var x = document.getElementsByClassName("selectedRow");
		if(x[0]!=undefined) x[0].className = "";
		  document.getElementById(selectedRow).className="selectedRow";
     }
   };

   xmlhttp.open("GET","${pageContext.request.contextPath}/Display_Sessions?Session_Date="+str+"&Grade_id="+Grade_id+"&Class_id="+Class_id+"&Course_id="+Course_id+"&Group_id="+Group_id,true);
 xmlhttp.send();
 }
 function showCurrentSession(Session_id, Current, Time_From, Time_To)
 {

 if (Session_id=="")
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
     }
   };
   xmlhttp.open("GET","${pageContext.request.contextPath}/Display_Sessions?Session_id="+Session_id+"&Current="+Current+"&Time_From="+Time_From+"&Time_To="+Time_To,true);
 xmlhttp.send();
 }
 function showStudents(Session_id, Current, Time_From, Time_To)
 {
 document.getElementById("Student").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
 if (Session_id=="")
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
     }
   };
   xmlhttp.open("GET","${pageContext.request.contextPath}/Display_Students?Session_id="+Session_id+"&Current="+Current+"&Time_From="+Time_From+"&Time_To="+Time_To,true);
 xmlhttp.send();
 }

 function manageAttendance(Student_id, Status, Function)
 {

 if (Student_id=="")
   {
   document.getElementById("Attendance").innerHTML="";
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

	   //document.getElementById("Attendance").innerHTML=xmlhttp.responseText;
     }
   };
   xmlhttp.open("GET","${pageContext.request.contextPath}/Manage_Attendance?Student_id="+Student_id+"&Status="+Status+"&Function="+Function,true);
 xmlhttp.send();
 }

 </script>
</head>

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
            <li class="crumb-trail">Students Attendance</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Teacher/Attendance/Initiate_Attendance.jsp">Students Attendance</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
	<form name="form1" id="form1" class="form-horizontal">
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
		  	<%@include file="../includes/Adsense_Top.jsp" %>
	          <h4>Students Attendance</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="fa fa-book"></span>Take Students Attendance</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Students Attendance Class Authority List</code>
              </div>
            </div>
            <div class="panel-body pn">
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
                          <th>Date</th>                              
                        </tr>
                      </thead>
                      <tbody>
					<c:forEach items="${TeacherAuthority.authorityList}" var="AuthorityList">
				    <tr id="${AuthorityList[0]}${AuthorityList[2]}${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}">
					    <td style="width:20px;"><i class="fa fa-circle text-warning fs8 pr15"></i></td>
						<td><c:out value="${AuthorityList[1]}"/></td>
						<td><c:out value="${AuthorityList[3]}"/></td>
						<td><c:out value="${AuthorityList[5]}"/></td>
						<td><c:out value="${AuthorityList[7]}  ${AuthorityList[9]}"/></td>
						<td>
								<input name="Session_Date" type="Date" class="form-control" id="Session_Date" 
								onchange="showSessions(this.value, '${AuthorityList[2]}' ,'${AuthorityList[4]}', '${AuthorityList[6]}', '${AuthorityList[8]}', '${AuthorityList[0]}${AuthorityList[2]}${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}');">
			 			</td>
				    </tr>
				    </c:forEach>
                      </tbody>
                    </table>  
                </div>
              </div>
            </div>
          </div>	
	        </div>
       </section>  
      <div class="panel" id="spy4">
        <div class="panel-body pn">
          <div class="table-responsive">
            <div class="bs-component">
                <table class="table table-bordered mbn">
                  <tbody>
				    <tr>
				    <td style="vertical-align:top;border-left:transparent">
						<div id="Session"></div>
						<div id="Student"></div>
						<div id="Attendance"></div>
				    </td>
				    </tr>
                  </tbody>
                </table>  
            </div>
          </div>
        </div>
      </div>
  		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
      </form>
	  <%@include file="../includes/Adsense_Bottom.jsp" %>
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