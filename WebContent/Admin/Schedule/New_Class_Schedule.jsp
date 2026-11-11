<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Create New Class Schedule</title>
  <script src="${pageContext.request.contextPath}/Global/lib/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.form.js"></script> 
  <script>
  $(document).ready(function() 
{ 
    //$('.mainContent').$('#content').$('.container-fluid').$('#form1').ajaxForm({target:'#Schedule'}); 
	    $('#form1').ajaxForm({target:'#Schedule'}); 
}); 
  </script>
  
<script>
 function showGrades(School_id, Grade_id)
 {
if (School_id=="")
   {
	   document.getElementById("Grade").innerHTML="<option>Select Grade</option>";
	   document.getElementById("Class").innerHTML="<option>Select Class</option>";
	   document.getElementById("Schedule").innerHTML="";
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
     if(Grade_id==null)
    	 {
         document.getElementById("Class").innerHTML="<option>Select Class</option>";
         document.getElementById("Schedule").innerHTML="";
    	 }
      if(Grade_id!=null)
	 	{	 	
	 	document.getElementById("Grade").value=Grade_id;	 
	 	} 
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+School_id,true);
 xmlhttp.send();
 }
 // end of ShowGrade function
  function showClasses(Grade_id, Class_id, School_id)
 {
 if (Grade_id=="")
   {
		 document.getElementById("Class").innerHTML="<option>Select Class</option>";
	   	 document.getElementById("Schedule").innerHTML="";
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
      if(Class_id!=null)
    	 {	
    	 	document.getElementById("Class").value=Class_id;	
    	 	showGrades(School_id, Grade_id);
    	 } 
      else
    	  {
    		document.getElementById("Schedule").innerHTML="";
    	  }
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Classes?Grade_id="+Grade_id,true);
 xmlhttp.send();
 }
 //==========End of showClasses function=================
function getClientTimeZone() 
    {
        var clientDate = new Date();
        var offset = clientDate.getTimezoneOffset();
        var clientHours = clientDate.getHours();
        var clientMinutes = clientDate.getMinutes();
        var clientDay = clientDate.getDay();
   		
    }
	 
function showSchedule(Class_id, Grade_id, School_id)
 {

document.getElementById("Schedule").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
	 
if (Class_id=="")
   {
   document.getElementById("Schedule").innerHTML="";
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
	     document.getElementById("Schedule").innerHTML=xmlhttp.responseText;
	      if (School_id!=null && Grade_id!=null)
		 {
		 	document.getElementById("School").value=School_id;
		 	document.getElementById("Grade").value=Grade_id;
		 	showClasses(Grade_id, Class_id, School_id); 
		 	
		 }
     }
   };

 var clientDate = new Date();
 var offset = clientDate.getTimezoneOffset();
 var clientHours = clientDate.getHours();
 var clientMinutes = clientDate.getMinutes();
 var clientDay = clientDate.getDay();  
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Class_Schedule?Class_id="+Class_id+"&&clientHours="+clientHours+"&&clientMinutes="+clientMinutes+"&&clientDay="+clientDay,true);
 xmlhttp.send();
 }
 
 function showTeacher(str,n, Class_id)
 {
	cell_count= document.getElementById("cell_count"+n).value;
	 document.getElementById("Teacher_Name"+cell_count).innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
	if ((str=="")&&(cell_count==""))
   {
   
	document.getElementById("Teacher_Name"+cell_count).innerHTML="";
	
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
	  document.getElementById("Teacher_Name"+cell_count).innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Teacher_Class_Course?CG="+str+"&Class_id="+Class_id+"&n="+n,true);
 xmlhttp.send();
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Schedule Sessions</a> <a href="${pageContext.request.contextPath}/Admin/Schedule/New_Class_Schedule.jsp" class="current">Manage Schedule</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Manage Schedule</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		   <form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Manage_Class_Schedule">

		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Manage Schedule</h5>
		        </div>
		        <div class="widget-content nopadding">					
		            <div class="control-group">
					  <label class="control-label">School Name</label>
		              <div class="controls">						              
			           	<select name="School" id="School" class="span11" onChange="showGrades(this.value, null)">
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
					  <label class="control-label">Grade Name</label>
		              <div class="controls">					
				        <select name="Grade" id="Grade" class="span11" onChange="showClasses(this.value, null)">
				 			 <option value="">Select Grade</option>	
				 		</select>
 					  </div>
 					</div>	
					<div class="control-group">
					  <label class="control-label">Classroom Name</label>
		              <div class="controls">									 		
				        <select name="Class" id="Class" class="span11" onChange="showSchedule(this.value)">
				 			 <option value="">Select Class</option>
				 		</select>				 		
 					  </div>
 					</div>
<!--  					<div id="Schedule"></div> -->
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>

		        </div>
		     </div>			     
		   </div>	
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
			   
		 </div>
		    <div class="row-fluid">
		    <div>
		      <div class="widget-box">
		        <div class="widget-content nopadding">					
 					<div id="Schedule"></div>
		        </div>
		     </div>	
		   </div>
		   </div>
		   		 </form>		 
		</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>

<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>