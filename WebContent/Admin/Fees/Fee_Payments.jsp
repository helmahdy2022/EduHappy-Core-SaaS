<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Student Search</title>
 <script>

 function showGrades(str)
 {
 if (str=="")
   {
	   document.getElementById("Grade").innerHTML="<option>Select Grade</option>";
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
	   document.getElementById("Student").innerHTML="";
     }
   };
   xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 //==========End of showGrades function=================
 
 function showStudent(Grade_id)
 {
 document.getElementById("Student").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
 if ( Grade_id=="") 
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
     $('#grade_Students').DataTable();
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grade_Students_Payment?Grade_id="+Grade_id,true);
 xmlhttp.send();
 }
 // end of ShowStudent function
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Fees Management</a> <a href="${pageContext.request.contextPath}/Admin/Fees/Fee_Payments.jsp" class="current">Fee Payment</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Fee Payment</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
    		<form name="form1" id="form1" class="form-horizontal">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Students</h5>
		        </div>
		        <div class="widget-content nopadding">

		            <div class="control-group">
					  <label class="control-label">School Name</label>
		              <div class="controls">  								              
			          	<select name="School" id="School" class="span11" onChange="showGrades(this.value)" required>
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
						 <select name="Grade" id="Grade" class="span11" onChange="showStudent(this.value)" required>
				 			 <option value="">Select Grade</option>
				 		</select> 				 							  
 					  </div>
 					</div>	
				 	<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
 			</div>
 		</div>
     </div>
   	<div id="Student" class="span6">
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>   	
   	</div>
	</form>
    </div>

   </div>
  </div>
 	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>