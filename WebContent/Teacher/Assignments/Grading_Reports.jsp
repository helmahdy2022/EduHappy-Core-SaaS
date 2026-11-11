<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<title>Grading Reports</title>
 </head>
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/jquery-1.11.3.js"></script>  
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.js"></script> 
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.form.js"></script> 
<script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/jquery.validate.js"></script> 

 <script type="text/javascript">
 function getCourseGradingReport(Grade_id, Class_id, Course_id, Group_id, selectedRow)
 {
 document.getElementById("GradingReportDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
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

 	  document.getElementById("GradingReportDetails").innerHTML=xmlhttp.responseText;
	  var x = document.getElementsByClassName("selectedRow");
	  if(x[0]!=undefined) x[0].className = "";
	  document.getElementById(selectedRow).className="selectedRow";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Grading_Report?Grade_id="+Grade_id+"&&Class_id="+Class_id+"&&Course_id="+Course_id+"&&Group_id="+Group_id,true);
 xmlhttp.send();
 }
 
 function showCategories(Student_id, Course_id, Grade_id, Class_id, Group_id)
 {
 if (Student_id=="")
   {
   document.getElementById("Categories"+Student_id).innerHTML="";
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
	  
	  document.getElementById("Categories"+Student_id).innerHTML=xmlhttp.responseText;
 	  if (document.getElementById(Student_id).name== "+")
 		  {
 		 	document.getElementById(Student_id).src="${pageContext.request.contextPath}/images/-.jpg";
 		  	document.getElementById(Student_id).name="-";
 		  }
 	  else
 		  {
 		 	document.getElementById(Student_id).src="${pageContext.request.contextPath}/images/+.jpg";
 		    document.getElementById("Categories"+Student_id).innerHTML="";
 		    document.getElementById(Student_id).name="+";
 		  }

     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Teacher/Assignments/Display_Student_Categories.jsp?Student_id="+Student_id+"&Course_id="+Course_id+"&Grade_id="+Grade_id+"&Class_id="+Class_id+"&Group_id="+Group_id,true);
 xmlhttp.send();
 }
 function checkSessionForCat(Student_id, Course_id, Grade_id, Class_id, Group_id)
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
	 				window.location.href = "${pageContext.request.contextPath}/Teacher/Teacher_Login.jsp";
	 			}
	 			else
	 			{
	 				showCategories(Student_id, Course_id, Grade_id, Class_id, Group_id);
	 			}
	 	     }
	 	  };
	 	xmlhttp.open("GET","${pageContext.request.contextPath}/Check_Session?User=Teacher",true);
	 	xmlhttp.send();
 }
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
            <li class="crumb-trail">Grading Report</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Teacher/Assignments/Grading_Reports.jsp">Grading Report</a>
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
	          <h4>Grading Report</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="fa fa-bar-chart"></span> Grading Report</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Grading Report Class Authority List</code>
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
				    <tr id="${AuthorityList[2]}${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}">
					    <td style="width:20px;"><i class="fa fa-circle text-warning fs8 pr15"></i></td>
						<td><c:out value="${AuthorityList[1]}"/></td>
						<td><c:out value="${AuthorityList[3]}"/></td>
						<td><c:out value="${AuthorityList[5]}"/></td>
						<td><c:out value="${AuthorityList[7]}  ${AuthorityList[9]}"/></td>
						<td>
			 	 			<button type="button" class="btn btn-xs btn-primary btn-block" value="Get Grading Report" name="Get_Grading_Report${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" id="Get_Grading_Report${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" 
			 	 			onclick="getCourseGradingReport(${AuthorityList[2]},${AuthorityList[4]}, ${AuthorityList[6]},'${AuthorityList[8]}', '${AuthorityList[2]}${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}')">
			 	 			<span class="fa fa-bar-chart-o"></span> Get Grading Report
			 	 			</button>
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
					 <div id="GradingReport"></div>
					 <div id="GradingReportDetails"> ${param.message}</div>
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