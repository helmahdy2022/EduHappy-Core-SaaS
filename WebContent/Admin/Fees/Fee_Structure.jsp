<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Fee Structure</title>
<script>
function showYears(str)
{
if (str=="")
  {
	document.getElementById("School_Year_id").innerHTML="<option>Select Year</option>";
	document.getElementById("Fee_Structure").innerHTML="";
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
	  document.getElementById("School_Year_id").innerHTML=xmlhttp.responseText;
	  document.getElementById("Fee_Structure").innerHTML="";
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_School_Years?School_id="+str,true);
xmlhttp.send();
}
 // end of ShowGrade function
 function showFeeStructure(School_id, School_Year_id)
{
document.getElementById("Fee_Structure").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (School_id==""&&School_Year_id=="")
  {
	document.getElementById("Fee_Structure").innerHTML="";
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
	  document.getElementById("Fee_Structure").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Fee_Structure?School_id="+School_id+"&School_Year_id="+School_Year_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Fees Management</a> <a href="${pageContext.request.contextPath}/Admin/Fees/Fee_Structure.jsp" class="current">Fee Structure</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Fee Structure</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
   		   <form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Fee_Structure">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Fee Structure</h5>
		        </div>
		        <div class="widget-content nopadding">
		            <div class="control-group">
					  <label class="control-label">School Name</label>
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
					  <label class="control-label">Academic Year Name</label>
		              <div class="controls">  
						<select name="School_Year_id" id="School_Year_id" class="span11" onChange="showFeeStructure(document.getElementById('School_id').value, this.value)" required>
						  <option value=""> 
						  	Select Year
						  </option>
						</select>				 						 								
 					  </div> 					  
 					</div>	
					<div class="control-group">
						<div id="YearTerms"> </div>					 	
 					</div> 						
		        </div>
		     </div>
		     <div class="widget-box">
		     <div id="Fee_Structure">		     
		     </div>
		     </div>
		   </div>
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
			<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
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