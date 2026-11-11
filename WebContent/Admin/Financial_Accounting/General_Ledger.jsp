<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>General Ledger</title>
<script>
function showFinancialAccounts(School_id)
{
if (School_id=="")
  {
	   document.getElementById("Financial_Account_id").innerHTML="<select><option value=''>Select Financial Account</option></select>";
	   document.getElementById("GeneralLedger").innerHTML="";

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
    	document.getElementById("Financial_Account_id").innerHTML=xmlhttp.responseText; 
    	document.getElementById("GeneralLedger").innerHTML="";
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Financial_Accounts?School_id="+School_id+"&Dropdown=1",true);
xmlhttp.send();
}
function showGeneralLedger(School_id, Financial_Account_id, General_Ledger_Date_From, General_Ledger_Date_To)
 {
	document.getElementById("GeneralLedger").innerHTML="<div><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
	if (School_id=="" && Financial_Account_id=="" && General_Ledger_Date_From=="" && General_Ledger_Date_To=="")
	   {
		 document.getElementById("GeneralLedger").innerHTML="";
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
	      document.getElementById("GeneralLedger").innerHTML=xmlhttp.responseText;
	      $('#general_Ledger').DataTable();
	     }
	   };
	 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_General_Ledger?School_id="+School_id+"&Financial_Account_id="+Financial_Account_id+"&General_Ledger_Date_From="+General_Ledger_Date_From+"&General_Ledger_Date_To="+General_Ledger_Date_To,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Financial Accounting</a> <a href="${pageContext.request.contextPath}/Admin/Financial_Accounting/General_Ledger.jsp" class="current">General Ledger</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>General Ledger</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>General Ledger</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" id="form1" method="post" id="form1" class="form-horizontal" action="">
			            <div class="control-group">
						  <label class="control-label">School Name *</label>
			              <div class="controls">	              										
				           	<select name="School" id="School" class="span11" onchange="showFinancialAccounts(this.value);">
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
						  <label class="control-label">Account Name *</label>
			              <div class="controls">	              										
				           	<select name="Financial_Account_id" id="Financial_Account_id" class="span11">
				 			 <option value=""> 
							  	Select Financial Account
							  </option> 
							</select> 
						 </div>
			            </div>	
			            <div class="control-group">
						  <label class="control-label">Date From *</label>
			              <div class="controls">	              										
				           	<input type="Date" name="General_Ledger_Date_From" id="General_Ledger_Date_From" class="span11" required>
						 </div>
			            </div> 
			            <div class="control-group">
						  <label class="control-label">Date To *</label>
			              <div class="controls">	              										
				           	<input type="Date" name="General_Ledger_Date_To" id="General_Ledger_Date_To" class="span11" required>
						 </div>
			            </div> 			            
     					<div class="buttonHolder">
     					<button type="button" class="btn btn-info" onclick="showGeneralLedger(School.value, Financial_Account_id.value, General_Ledger_Date_From.value, General_Ledger_Date_To.value);">Get the General Ledger Report</button>
     					</div>
				    </form>
		        </div>
		     </div>	
		   </div>		
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
		 </div>
		<div id="GeneralLedger" class="row-fluid"></div>
		</div> 
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>