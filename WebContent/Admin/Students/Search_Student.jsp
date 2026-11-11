<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Student Search</title>

 <script>

 function showGrades(School_id)
 {
 if (School_id=="")
   {
	   document.getElementById("Grade").innerHTML="<option value=''>Select Grade</option>";
	   document.getElementById("Class").innerHTML="<option value=''>Select Class</option>";
	   document.getElementById("Route_id").innerHTML="<option value=''>Select Route</option>";
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
       document.getElementById("Grade").innerHTML=xmlhttp.responseText;
	   document.getElementById("Class").innerHTML="<option value=''>Select Class</option>";
	   document.getElementById("Route_id").innerHTML="<option value=''>Select Route</option>";
	   document.getElementById("Student").innerHTML="";
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
     $('#students').DataTable();
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Students?First_Name="+First_Name+"&Last_Name="+Last_Name+"&Username="+Username+"&Student_id="+Student_id+"&Route_id="+Route_id+"&Class_id="+Class_id+"&School_id="+School_id+"&Grade_id="+Grade_id,true);
 xmlhttp.send();
 }

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
</head>

<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Students</a> <a href="${pageContext.request.contextPath}/Admin/Students/Search_Student.jsp" class="current">Edit Student</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Search Student</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Search Student</h5>
		        </div>
		        <div class="widget-content nopadding">
    			<form name="form1" id="form1" class="form-horizontal" action="">
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
			 	         <select name="Class" id="Class" class="span11" onChange="document.getElementById('Student').innerHTML=''">
				 			 <option value="">Select Classroom</option>
				 		</select> 
 					  </div>
 					</div>
					<div class="control-group">
					  <label class="control-label">First Name</label>
		              <div class="controls">
            			 <input type="text" name="First_Name" id="First_Name" class="span11" maxlength="50">					
 					  </div>
 					</div>
 					<div class="control-group">
					  <label class="control-label">Last Name</label>
		              <div class="controls">
            			 <input type="text" name="Last_Name" id="Last_Name" class="span11" maxlength="50">				
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
            			 <input name="Student_id" type="number" class="span11" id="Student_id" max="1000000000">			
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
		            <div class="form-actions">
		            <input type="button" name="Submit" id="Submit" value="Search" class="btn btn-success" onClick="showStudent(First_Name.value, Last_Name.value, Username.value, Student_id.value, Class.value, School.value, Grade.value, Route_id.value)">
		            </div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
 				</form>
 			</div>
 		</div>
     </div>
   		<div id="Student" class="span6">
   			<%@include file="../includes/Adsense_Inline.jsp" %>
   		</div>
    </div>

   </div>
  </div>
 	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"> <%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>