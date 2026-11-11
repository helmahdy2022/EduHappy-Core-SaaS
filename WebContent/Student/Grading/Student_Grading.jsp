<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Student Grading</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<script>
function showCategories(Course_id, Student_id, Source)
{
if (Course_id=="")
  {
  document.getElementById("Categories"+Course_id).innerHTML="";
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
	  if(Source=='Admin')
		  {
		  	sessionExpiredAdmin();
		  }
/* 	  else
		  {
		  	sessionExpired();
		  } */
    	 
	  document.getElementById("Categories"+Course_id).innerHTML=xmlhttp.responseText;
	  if (document.getElementById(Course_id).name== "+")
		  {
		  	document.getElementById(Course_id).src="${pageContext.request.contextPath}/images/-.jpg";
		  	document.getElementById(Course_id).name="-";
		  }
	  else
		  {
		  	document.getElementById(Course_id).src="${pageContext.request.contextPath}/images/+.jpg";
		    document.getElementById("Categories"+Course_id).innerHTML="";
		    document.getElementById("CategoryDetaildGrades").innerHTML="";
		    document.getElementById(Course_id).name="+";
		  }

    }
  };
if(Source=='Admin')
	{
		xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Course_Categories?Course_id="+Course_id+"&Student_id="+Student_id+"&Source=Admin",true);
	}
else
	{
		xmlhttp.open("GET","${pageContext.request.contextPath}/Student/Grading/Display_Course_Categories.jsp?Course_id="+Course_id+"&Student_id="+Student_id,true);
	}
xmlhttp.send();
}

function checkSessionForCat(Course_id, Student_id, Source)
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
	 			if(xmlhttp.responseText=='SessionExpired'&&Source!='Admin'&&Source!='Parent')
	 			{
	 				window.location.href = "${pageContext.request.contextPath}/Student/Student_Login.jsp";
	 			}
	 			if(xmlhttp.responseText=='SessionExpired'&&Source=='Admin')
	 			{
	 				window.close();
	 			}
	 			else
	 			{
	 				showCategories(Course_id, Student_id, Source);
	 			}
	 	     }
	 	  };
	 	  //if(Source=='Admin')
	 		  //{
	 		 	xmlhttp.open("GET","${pageContext.request.contextPath}/Check_Session?User="+Source,true);
	 		 // }


	 	xmlhttp.send();
}

function showCatGrades(Category_id, Student_id, Source, School_year_id)
{
	document.getElementById("CategoryDetaildGrades").innerHTML="<img src='${pageContext.request.contextPath}/images/Spinner.gif'>"
if (Category_id=="")
  {
  document.getElementById("CategoryDetaildGrades").innerHTML="";
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
	  if(Source=='Admin')
		  {
		  	sessionExpiredAdmin()
		  }
	  else
		  {
		  	sessionExpired();
		  }   	 
	  document.getElementById("CategoryDetaildGrades").innerHTML=""
	  document.getElementById("CategoryDetaildGrades").innerHTML=xmlhttp.responseText;

    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Category_Assignments?Category_id="+Category_id+"&Student_id="+Student_id+"&School_year_id="+School_year_id+"&Promoted=${param.Promoted}",true);
xmlhttp.send();
}

function showAttendanceGrades(Course_id, Student_id, Source, School_year_id)
{
if (Course_id=="")
  {
  document.getElementById("CategoryDetaildGrades").innerHTML="";
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
	   	  if(Source=='Admin')
		  {
		  	sessionExpiredAdmin()
		  }
	  	  else
		  {
		  	sessionExpired();
		  } 
	  	  document.getElementById("CategoryDetaildGrades").innerHTML=""
	      document.getElementById("CategoryDetaildGrades").innerHTML=xmlhttp.responseText;

    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Attendance?Course_id="+Course_id+"&Student_id="+Student_id+"&School_year_id="+School_year_id+"&Promoted=${param.Promoted}",true);
xmlhttp.send();
}
function sessionExpiredAdmin()
{
	  if(xmlhttp.responseText=='SessionExpired')
		  {
	      	window.close();
		  }
}


function getPreviousReport(School_year_id, Source, Current_school_year_id, Student_id)
{
	document.getElementById("GradingReport").innerHTML="<div style='margin-left:500px;margin-top:200px;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>"

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
	  if(Source=='Admin')
		  {
		  	sessionExpiredAdmin()
		  }
	  else
		  {
		  	sessionExpired();
		  }   
	  document.getElementById("CategoryDetaildGrades").innerHTML=""
	  document.getElementById("GradingReport").innerHTML=xmlhttp.responseText;
	  
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Report_Card?Previous_school_year_id="+School_year_id+"&Current_school_year_id="+Current_school_year_id+"&Student_id="+Student_id+"&User="+Source,true);
xmlhttp.send();
}

</script>


<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300">
<c:choose>
<c:when test="${param.User=='Admin'}">
  <link href="${pageContext.request.contextPath}/_css/Student_Main.css" rel="stylesheet" type="text/css">
  <!-- Font CSS (Via CDN) -->
  <link rel='stylesheet' type='text/css' href='https://fonts.googleapis.com/css?family=Open+Sans:300,400,600'>
  <!-- Theme CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/theme.css">		
  <!-- Admin Forms CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/admin-forms.css">  
</c:when>
<c:otherwise>
  <!-- For Demo Purposes - Theme Settings Pane -->
 	<%@include file="../includes/Theme_Settings.jsp" %>
</c:otherwise>
</c:choose>
  <!-- End: Theme Settings Pane -->
  <div id="main">
	<c:if test="${param.User!='Admin'}">
	 <c:choose>
		<c:when test="${param.Source=='Parent'}">
			<%@include file="../includes/ParentMenu.jsp" %> 
			<%@include file="../includes/ParentLeft.jsp" %>
		</c:when>
		<c:otherwise>
			<%@include file="../includes/StudentMenu.jsp" %> 
			<%@include file="../includes/StudentLeft.jsp" %>
		</c:otherwise>
	 </c:choose>
	</c:if>
     <section id="content_wrapper" <c:if test="${param.User=='Admin'}">style="margin-left:0px;"</c:if>>

      <!-- Start: Topbar-Dropdown -->
	<c:if test="${param.User!='Admin'}">
	<%@include file="../includes/Topbar_Dropdown.jsp" %>
      <!-- End: Topbar-Dropdown -->  
      <!-- Start: Topbar -->
	 <c:choose>
		<c:when test="${param.Source=='Parent'}">
      <header id="topbar">
        <div class="topbar-left">
          <ol class="breadcrumb">
            <li class="crumb-active">
              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">Dashboard</a>
            </li>
            <li class="crumb-icon">
              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">
                <span class="glyphicon glyphicon-home"></span>
              </a>
            </li>
            <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">Home</a>
            </li>
            <li class="crumb-trail">Grading</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Grading/Initiate_Student_Grading.jsp?Student_id=${param.Student_id}&&Source=Parent">Grading Report</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
		</c:when>
		<c:otherwise>
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
            <li class="crumb-trail">Grading</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Grading/Initiate_Student_Grading.jsp">Grading Report</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
		</c:otherwise>
	 </c:choose>
      </c:if>
      <!-- End: Topbar -->
	   <%@include file="../includes/Adsense_Top.jsp" %>
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
	          <c:if test="${param.User!='Admin'}">
	           <h4>Student Grading</h4>
	 	       <h4>
	             <c:forEach items="${child.student_id}" var="Student_id"  varStatus="position">
					<c:if test="${Student_id == param.Student_id}">	             
	                  	 <font style="color:blue"><c:out value='${child.first_Name[position.index]}'/>&nbsp;<c:out value='${child.last_Name[position.index]}'/></font>		             			
					</c:if>
	             </c:forEach>
	           </h4>
	           <hr class="alt short">
	          </c:if>
          <div class="panel" id="spy4">
 			<c:if test="${param.User!='Admin'}">
             <div class="panel-heading">
              <span class="panel-title">
                <span class="fa fa-book"></span>Grading Report</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Student Grading Report</code>
              </div>
             </div>
            </c:if>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
				 <c:if test="${not empty Student_Grading.previous_Years_id}">
				  <table class="table table-bordered mbn">
				   <tr>
				    <td>
			    
					 <select name="Previous_school_year_id" id="Previous_school_year_id" style="width:200px;" onchange="getPreviousReport(this.value, '${param.User}', ${Student_Grading.school_year_id}, ${param.Student_id});">
					  <option value="${Student_Grading.school_year_id}">
					   Select School Year 
					  </option>
					  <c:forEach items="${Student_Grading.previous_Years_id}" var="School_Year_id" varStatus="position">
						<option value="${School_Year_id}">
						${Student_Grading.previous_Years_Name[position.index]}
						</option>
					  </c:forEach>
					 </select>
				    </td>
				   </tr>
				  </table>
				 </c:if>		
		 		<c:if test="${param.User=='Admin'}">
				<table class="table table-bordered mbn">
				 <tr>
				  <td style="vertical-align:top;">
					<div>
					<b>Full Student Report Card - School Year <c:out value="${Student_Grading.year_Name}"/></b>
					</div> <br/> 	
					<div><b>Student Name:</b> &nbsp;<c:out value=" ${param.First_Name} ${param.Last_Name}"></c:out></div>			
				  </td>
				  <td>
					<c:if test="${param.FileName!=null && param.FileName!=''}">
						<img src="${pageContext.request.contextPath}/uploadFiles/Accounts/${param.Account_id}/Schools/${param.School_id}/Students/Photos/${param.Student_id}/${param.FileName}">
					</c:if>		
				  </td>
				 </tr>
				</table>
				</c:if>
 				<div id="GradingReport">
					<%@include file="Grading_Report.jsp" %>
	 			</div>
			  	<table class="table table-bordered mbn">
				 <tr>
				  <td>
				   <div id="CategoryDetaildGrades"></div>
				  </td>
				 </tr>
				</table>
                </div>
              </div>
            </div>           
          </div>	
	        </div>
       </section>  
      <!-- End: Content -->
	  <%@include file="../includes/Adsense_Bottom.jsp" %>
      <!-- Begin: Page Scripts -->

		<%@include file="../includes/Page_Scripts.jsp" %>

      <!-- End: Page Scripts -->            
     </section>
    <!-- Start: Right Sidebar -->
	<c:if test="${param.User!='Admin'}">
		<%@include file="../includes/Topbar_Right_Content.jsp" %>
	</c:if>
    <!-- End: Right Sidebar -->
  </div>
  </body>
</html>