<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Year End Procedures</title>

</head>
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.form.js"></script> 
<script>
  $(document).ready(function() 
{ 
    $('#form1').ajaxForm({target:'#FinalGrading'}); 
}); 
  
function displaySchoolYear(School_id)
{
document.getElementById("Year_Name").innerHTML="<img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'>"; 
 if (School_id=="")
{
  document.getElementById("Year_Name").innerHTML="";
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
 	  document.getElementById("Year_Name").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Current_Year?School_id="+School_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Year End Procedures</a> <a href="${pageContext.request.contextPath}/Admin/YearEnd/Final_Grading.jsp" class="current">Final Grading</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Produce Final Grading</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Final Grading</h5>
		        </div>
		        <div class="widget-content nopadding" id="FinalGrading">
					<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Final_Grading">
		            <div class="control-group">
					This process will calculate the final Overall Grading for each student in the school.
					<br>The process may take several minutes...
					<br> Please select the school and click Proceed. 

		            </div>	
		            <div class="control-group">
					  <label class="control-label">School Name</label>
		              <div class="controls"> 								              
				        <select name="School_id" id="School_id" class="span11" required onChange="displaySchoolYear(this.value)">
							 <option value=""> 
						  	Select School
						  </option>
						  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
					       	<option value="${School_id}"><c:out value='${Schools.school_Name[position.index]}'/></option>
					      </c:forEach>	  
						</select>
					  </div>
		            </div>	
 					<div id="Year_Name"></div>
		            <div class="form-actions">
		            <input type="submit" name="Submit" id="Submit" value="Proceed" class="btn btn-success" onclick="checkSession();">
		            </div>
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
					This process will calculate the final Overall Grading for each student in the school.
					<br>The process may take several minutes...
					<br> Please select the school and click Proceed.    
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
<div id="footer" class="span12"> <%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>