<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Journal Entry</title>
<script>
function showFinancialAccounts(School_id)
{
if (School_id=="")
  {
	   document.getElementById("Debit_Account_id").innerHTML="<select><option value=''>Select Financial Account</option></select>";
	   document.getElementById("Credit_Account_id").innerHTML="<select><option value=''>Select Financial Account</option></select>";

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
    	document.getElementById("Debit_Account_id").innerHTML=xmlhttp.responseText; 
    	document.getElementById("Credit_Account_id").innerHTML=xmlhttp.responseText; 
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Financial_Accounts?School_id="+School_id+"&Dropdown=1",true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Financial Accounting</a> <a href="${pageContext.request.contextPath}/Admin/Financial_Accounting/Journal_Entry.jsp" class="current">Journal Entry</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Journal Entry</h1>
		</div>
		<!--End-breadcrumbs-->
		<div style="margin-left:10px;color:blue;">${param.message}</div>		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Journal Entry</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" id="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Journal_Entry">
			            <div class="control-group">
						  <label class="control-label">School Name *</label>
			              <div class="controls">	              										
				           	<select name="School" id="School" class="span11" onchange="showFinancialAccounts(this.value);" required>
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
						  <label class="control-label">Debit Account *</label>
			              <div class="controls">	              										
				           	<select name="Debit_Account_id" id="Debit_Account_id" class="span11" required>
				 			 <option value=""> 
							  	Select Financial Account
							  </option> 
							</select> 
						 </div>
			            </div>
			            <div class="control-group">
						  <label class="control-label">Credit Account *</label>
			              <div class="controls">	              										
				           	<select name="Credit_Account_id" id="Credit_Account_id" class="span11" required>
				 			 <option value=""> 
							  	Select Financial Account
							  </option> 
							</select> 
						 </div>
			            </div>			            	
			            <div class="control-group">
						  <label class="control-label">Date *</label>
			              <div class="controls">	              										
				           	<input type="Date" name="Entry_Date" id="Entry_Date" class="span11" required>
						 </div>
			            </div> 
			            <div class="control-group">
						  <label class="control-label">Amount *</label>
			              <div class="controls">	              										
				           	<input type="number" name="Journal_Amount" id="Journal_Amount" class="span11" required>
						 </div>
			            </div>
			            <div class="control-group">
						  <label class="control-label">Details *</label>
			              <div class="controls">	              										
				           	<input type="text" name="Journal_Details" id="Journal_Details" class="span11" required>
						 </div>
			            </div> 			             			            
     					<div class="form-actions">
     					<input type="submit" class="btn btn-success" value="Save">
     					</div>
		            	<div class="requiredSignNote">Fields marked (*) are required</div>
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