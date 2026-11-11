<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Grading Reports</title>

<script>
 function showGrades(str)
 {
 if (str=="")
   {
	 document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("Class").innerHTML="<select><option >Select Class</option></select>";	 
	 document.getElementById("GradingReportDetails").innerHTML="";
	 
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
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("Class").innerHTML="<select><option >Select Class</option></select>";
	 document.getElementById("GradingReportDetails").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 // end of ShowGrade function
  function showClasses(str)
 {
 if (str=="")
   {
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("Class").innerHTML="<select><option >Select Class</option></select>";
	 document.getElementById("GradingReportDetails").innerHTML="";
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
	 document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("Class").innerHTML=xmlhttp.responseText;
	 document.getElementById("GradingReportDetails").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Classes?Grade_id="+str,true);
 xmlhttp.send();
 }
 //==========End of showClasses function=================
	  function showCourses(Grade_id, Class_id)
 {
 if (Grade_id==""||Class_id=="")
   {
     document.getElementById("Course").innerHTML="<select><option >Select Course</option></select>";
	 document.getElementById("GradingReportDetails").innerHTML="";
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
     document.getElementById("Course").innerHTML=xmlhttp.responseText;
	 document.getElementById("GradingReportDetails").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Courses?Grade_id="+Grade_id,true);
 xmlhttp.send();
 }

function getCourseGradingReport(Grade_id, Class_id, Course_id, Group_id, selectedRow)
 {
	var e = document.getElementById("Course");
	var Course_Name = e.options[e.selectedIndex].text;
	document.getElementById("GradingReportDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
  if (Course_id=="")
	{
	  document.getElementById("GradingReportDetails").innerHTML="";
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

  	  document.getElementById("GradingReportDetails").innerHTML=xmlhttp.responseText;
      }
    };
  xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Grading_Report?User=Admin&Course_Name="+Course_Name+"&Grade_id="+Grade_id+"&&Class_id="+Class_id+"&&Course_id="+Course_id+"&&Group_id="+Group_id,true);
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
	  sessionExpired();
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
//xmlhttp.open("GET","${pageContext.request.contextPath}/Teacher/Assignments/Display_Student_Categories.jsp?User=Admin&Student_id="+Student_id+"&Course_id="+Course_id+"&Grade_id="+Grade_id+"&Class_id="+Class_id+"&Group_id="+Group_id,true);
xmlhttp.open("GET","${pageContext.request.contextPath}/Admin/Students/Display_Student_Categories.jsp?User=Admin&Student_id="+Student_id+"&Course_id="+Course_id+"&Grade_id="+Grade_id+"&Class_id="+Class_id+"&Group_id="+Group_id,true);
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
	 				window.location.href = "${pageContext.request.contextPath}/Admin/Admin_Login.jsp";
	 			}
	 			else
	 			{
	 				showCategories(Student_id, Course_id, Grade_id, Class_id, Group_id);
	 			}
	 	     }
	 	  };
	 	xmlhttp.open("GET","${pageContext.request.contextPath}/Check_Session?User=Admin",true);
	 	xmlhttp.send();
}

/* function opennewwindow(User, Category_id, Category_Name,Student_id,First_Name,Last_Name,Grade_id,Class_id,Course_id,Group_id)
{
	//window.open('${pageContext.request.contextPath}/Get_Assignments_Grading?User=${param.User}&Category_id=${Category[0]}&Category_Name=${Category[1]}&Student_id=${param.Student_id}&First_Name=${First_Name}&Last_Name=${Last_Name}&Grade_id=${param.Grade_id}&Class_id=${param.Class_id}&Course_id=${param.Course_id}&Group_id=${param.Group_id}','Assignments_Grading','toolbar=no, menubar=no, location=no, width=850,height=500, top=300, left=500')
	window.open('${pageContext.request.contextPath}/Get_Assignments_Grading?User='+User+'&Category_id='+Category_id+'&Category_Name='+Category_Name+'&Student_id='+Student_id+'&First_Name='+First_Name+'&Last_Name='+Last_Name+'&Grade_id='+Grade_id+'&Class_id='+Class_id+'&Course_id='+Course_id+'&Group_id='+Group_id,'Assignments_Grading','toolbar=no, menubar=no, location=no, width=850,height=500, top=300, left=500')
	//window.open("${pageContext.request.contextPath}/Get_Assignments_Grading?User="+User+"&Category_id="+Category_id+"&Category_Name='"+Category_Name+"'&Student_id="+Student_id+"&First_Name="+First_Name+"&Last_Name="+Last_Name+"&Grade_id="+Grade_id+"&Class_id="+Class_id+"&Course_id="+Course_id+"&Group_id="+Group_id,'Assignments_Grading','toolbar=no, menubar=no, location=no, width=850,height=500, top=300, left=500')
} */
</script>
</head>

<body>

<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Students</a> <a href="${pageContext.request.contextPath}/Admin/Students/Grading_Reports.jsp" class="current">Grading Reports</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Grading Report</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6" style="width:90%;">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Grading Report</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" method="post"  id="form1" class="form-horizontal" action="">
			            <div class="control-group">
						  <label class="control-label">School Name</label>
			              <div class="controls">	              										
				            <select name="School_id" id="School_id" class="span11" onChange="showGrades(this.value)">
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
					        <select name="Class" size="1" id="Class" class="span11" onChange="showCourses(Grade.value, this.value)">
					 			 <option>Select Class</option>
					 		</select>   						 
	 					  </div>
	 					</div>	
						<div class="control-group">
						  <label class="control-label">Course Name</label>
			              <div class="controls">
					        <select name="Course" id="Course" class="span11" onChange="getCourseGradingReport(Grade.value,Class.value, this.value,'', '')">
					 			 <option value="">Select Course</option>
					 		</select> 	 						 
	 					  </div>
	 					</div>		 					
 		 				<div id="GradingReportDetails"> ${param.message}</div> 
				    </form>
		        </div>
		     </div>	
		   </div>	
		 </div>
		</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>