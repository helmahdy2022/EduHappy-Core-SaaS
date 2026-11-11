<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Income Statement</title>
<script>
function showIncomeStatement(School_id, Income_Statement_Date_From, Income_Statement_Date_To)
 {
	document.getElementById("IncomeStatement").innerHTML="<div><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
	if (School_id==""&&Income_Statement_Date_From==""&&Income_Statement_Date_To=="")
	   {
		 document.getElementById("IncomeStatement").innerHTML="";
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
	     document.getElementById("IncomeStatement").innerHTML=xmlhttp.responseText;
	     }
	   };
	 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Income_Statement?School_id="+School_id+"&Income_Statement_Date_From="+Income_Statement_Date_From+"&Income_Statement_Date_To="+Income_Statement_Date_To,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Financial Accounting</a> <a href="${pageContext.request.contextPath}/Admin/Financial_Accounting/Income_Statement.jsp" class="current">Income Statement</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Income Statement</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Income Statement</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" id="form1" method="post" id="form1" class="form-horizontal" action="">
			            <div class="control-group">
						  <label class="control-label">School Name *</label>
			              <div class="controls">	              										
				           	<select name="School" id="School" class="span11">
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
						  <label class="control-label">Date From *</label>
			              <div class="controls">	              										
				           	<input type="Date" name="Income_Statement_Date_From" id="Income_Statement_Date_From" class="span11" required>
						 </div>
			            </div> 
			            <div class="control-group">
						  <label class="control-label">Date To *</label>
			              <div class="controls">	              										
				           	<input type="Date" name="Income_Statement_Date_To" id="Income_Statement_Date_To" class="span11" required>
						 </div>
			            </div>
     					<div class="buttonHolder">
     					<button type="button" class="btn btn-info" onclick="showIncomeStatement(School.value, Income_Statement_Date_From.value, Income_Statement_Date_To.value);">Get the Income Statement</button>
     					</div>
				    </form>
		        </div>
		     </div>	
		   </div>		
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
		 </div>
		<div id="IncomeStatement" class="row-fluid"></div>
		</div> 
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>