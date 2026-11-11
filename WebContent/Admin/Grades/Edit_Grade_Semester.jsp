<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Edit Grade Semester</title>
<script>
function showYears(str)
{
if (str=="")
  {
	document.getElementById("School_Year_id").innerHTML="<select><option>Select Year</option></select>";
  	document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
  	document.getElementById("Semester").innerHTML="<select><option >Select Semester</option></select>";
  	document.getElementById("SemesterDetails").innerHTML="";
	 document.getElementById("DeleteMessage").innerHTML="";
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
	  document.getElementById("SemesterDetails").innerHTML="";
	  document.getElementById("School_Year_id").innerHTML=xmlhttp.responseText;
	  document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
	  document.getElementById("Semester").innerHTML="<select><option >Select Semester</option></select>";
	  document.getElementById("DeleteMessage").innerHTML="";
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_School_Years?School_id="+str,true);
xmlhttp.send();
}

function showGrades(str, School_Year_id)
{
if (str=="" || School_Year_id=="")
  {
  document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
  document.getElementById("Semester").innerHTML="<select><option >Select Semester</option></select>";
  document.getElementById("SemesterDetails").innerHTML="";
	 document.getElementById("DeleteMessage").innerHTML="";
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
	  document.getElementById("Semester").innerHTML="<select><option >Select Semester</option></select>";
	  document.getElementById("SemesterDetails").innerHTML="";
	  document.getElementById("Grade").innerHTML=xmlhttp.responseText;
		 document.getElementById("DeleteMessage").innerHTML="";
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
xmlhttp.send();
}

  function showSemesters(School_Year_id, Grade_id)
 {
 if (Grade_id=="" || School_Year_id=="")
   {
   document.getElementById("Semester").innerHTML="<select><option >Select Semester</option></select>";
   document.getElementById("SemesterDetails").innerHTML="";
	 document.getElementById("DeleteMessage").innerHTML="";
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
		document.getElementById("SemesterDetails").innerHTML="";
	    document.getElementById("Semester").innerHTML=xmlhttp.responseText;
		 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grade_Semesters?School_Year_id="+School_Year_id+"&&Grade_id="+Grade_id,true);
 xmlhttp.send();
 }
 //==========End of showDivisions function=================
  function showSemesterDetails(Semester_id)
 {
document.getElementById("SemesterDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Semester_id=="")
   {
   document.getElementById("SemesterDetails").innerHTML="";
	 document.getElementById("DeleteMessage").innerHTML="";
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
     document.getElementById("SemesterDetails").innerHTML=xmlhttp.responseText;
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Grade_Semester_Details?Semester_id="+Semester_id,true);
 xmlhttp.send();
 }
  function showDeleteMessage()
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
		      document.getElementById("DeleteMessage").innerHTML=xmlhttp.responseText;
		      //document.getElementById("OneSession").innerHTML="";
		      }
		    };
		  xmlhttp.open("GET","${pageContext.request.contextPath}/Global/DeleteMessage.jsp",true);
		  xmlhttp.send();

  }
  function cancelDelete()
  {
      document.getElementById("DeleteMessage").innerHTML= "";

  }
 
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Grade Semester</a> <a href="${pageContext.request.contextPath}/Admin/Grades/Edit_Grade_Semester.jsp" class="current">Edit Grade Semester</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Edit Grade Semester</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Edit Grade Semester</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Grade_Semester">    				  				
			            <div class="control-group">
						  <label class="control-label">School Name</label>
			              <div class="controls">	              						 											 	
					        <select name="School" id="School" class="span11" onChange="showYears(this.value)">
				 			 <option value=""> 
							  	Select School
							  </option>
							  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
						       	<option value="${School_id}"
						       	   <c:if test="${Student_Details.school_id==School_id}">
					       			selected
					       		   </c:if>
						       	><c:out value='${Schools.school_Name[position.index]}'/></option>
						      </c:forEach>	  
							</select>
						 </div>
			            </div>
						<div class="control-group">
						  <label class="control-label">Academic Year Name</label>
			              <div class="controls"> 						 	
					        <select name="School_Year_id" id="School_Year_id" class="span11" onChange="showGrades(School.value, this.value)">
					 			 <option value="">Select Year</option>
					 		</select> 	
	 					  </div>
	 					</div>	
						<div class="control-group">
						  <label class="control-label">Grade Name</label>
			              <div class="controls"> 						 	
					        <select name="Grade" id="Grade" class="span11" onChange="showSemesters(School_Year_id.value, this.value)">
					 			 <option value="">Select Grade</option>
					 		</select> 
	 					  </div>
	 					</div>	 					
						<div class="control-group">
						  <label class="control-label">Semester Name</label>
			              <div class="controls">					 		  						 
					        <select name="Semester" id="Semester" class="span11" onChange="showSemesterDetails(this.value)">
					 			 <option value="">Select Semester</option>
					 		</select> 
	 					  </div>
	 					</div>	 						            					      
      				  <div id="SemesterDetails" ></div>       
				      <div id="DeleteMessage" ></div> 
					  <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>	
 		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
		 </div>
		</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>

<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>