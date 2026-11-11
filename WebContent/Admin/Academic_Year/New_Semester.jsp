<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>New Semester</title>
<script>
function showYears(str)
{
if (str=="")
  {
	document.getElementById("YearTerms").innerHTML="";
	document.getElementById("School_Year_id").innerHTML="<option>Select Year</option>";
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
	  document.getElementById("YearTerms").innerHTML="";
	  document.getElementById("School_Year_id").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_School_Years?School_id="+str,true);
xmlhttp.send();
}
 // end of ShowGrade function
 function showYearTerms(school_year_id)
 {
 if (school_year_id=="")
   {
   document.getElementById("YearTerms").innerHTML="";
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
     document.getElementById("YearTerms").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Year_Terms?School_year_id="+school_year_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Semesters</a> <a href="${pageContext.request.contextPath}/Admin/Academic_Year/New_Semester.jsp" class="current">New Year Semester</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>New Year Semester</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Year Semester</h5>
		        </div>
		        <div class="widget-content nopadding">
   				   <form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Year_Semester">
		            <div class="control-group">
					  <label class="control-label">School Name *</label>
		              <div class="controls">  													              
			            <select name="School_id" id="School_id"  class="span11" onChange="showYears(this.value)" required>
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
					  <label class="control-label">Academic Year Name *</label>
		              <div class="controls">  
						<select name="School_Year_id" id="School_Year_id" class="span11" onChange="showYearTerms(this.value)" required>
						  <option value=""> 
						  	Select Year
						  </option>
						</select>				 						 								
 					  </div> 					  
 					</div>	
					<div class="control-group">
					  <label class="control-label">Semester Name *</label>
		              <div class="controls">						
           				<input type="text" name="Semester_Name" id="Semester_Name" class="span11" required maxlength="50">
 					  </div>
 					</div>
					<div class="control-group">
						<div id="YearTerms"> </div>					 	
 					</div> 						
		            <div class="form-actions">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>
		   </div>
		   <div  class="span6">
		   	<div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Quick Hints</h5>
		        </div>
			        Year semester is part of the year that can cover one or more terms. 
			        Semester must cover one or more terms or otherwise it will have no effect and won't be displayed in the grading reports.
			        If you created a semester and covered at least one term, all other terms must be covered by semesters as well or the grading report will not be displayed properly.   
		   			<hr>
		   </div>
			<div class="widget-box">
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
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