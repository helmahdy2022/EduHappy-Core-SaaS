<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Fee Types</title>
<script>
function showFeeTypesList(School_id)
{
document.getElementById("FeeTypesList").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (School_id=="" && document.getElementById('Fee_id').value!=0)
  {
	$('#Fee_Name').val('');
	$('#Fee_Description').val('');
	$('#Fee_id').val('');
	document.getElementById('Yearly').checked=true;
	$('#Debit_Account_id').val('');
	$('#Credit_Account_id').val('');
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
	    document.getElementById("FeeTypesList").innerHTML=xmlhttp.responseText;
	    $('#fee_Types').DataTable();
		showFinancialAccounts(School_id);
		if(document.getElementById('Fee_id').value!=0)
		{
			$('#Fee_Name').val('');
			$('#Fee_Description').val('');
			$('#Fee_id').val('');	
			document.getElementById('Yearly').checked=true;
			$('#Debit_Account_id').val('');
			$('#Credit_Account_id').val('');
		}
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Fee_Types?School_id="+School_id,true);
xmlhttp.send();
}

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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Fees Management</a> <a href="${pageContext.request.contextPath}/Admin/Fees/Fee_Types.jsp" class="current">Fee Types</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Create New Fee Type</h1>
		</div>
		<!--End-breadcrumbs-->
		<div class="container-fluid">
		  <hr>
		<div style="margin-left:10px;color:blue;">${param.message}</div>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Fee Type</h5>
		        </div>
		        <div class="widget-content nopadding">
				  <form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Manage_Fee_Types">		          
		            <div class="control-group">
					  <label class="control-label">School Name *</label>
		              <div class="controls">	              										
			           	<select name="School" id="School" class="span11" onChange="showFeeTypesList(this.value)" required>
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
		              <label class="control-label">Fee Name *</label>
		              <div class="controls">
		                <input type="text" name="Fee_Name" id="Fee_Name" class="span11"  placeholder="Fee name" required maxlength="50">
		              </div>
		            </div>	           
		            <div class="control-group">
		              <label class="control-label">Description</label>
		              <div class="controls"> 
	 					<textarea name="Fee_Description" id="Fee_Description" class="span11" maxlength="1000"></textarea>		                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Payment Mode *</label>
		              <div class="controls">
					  <input type="radio" name="Payment_Mode" id="Yearly" value="Yearly" checked> Yearly<br>		              
					  <input type="radio" name="Payment_Mode" id="Once" value="Once"> Once<br>	                
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
		            <div class="form-actions">
		            <input type="hidden" name="Fee_id" id="Fee_id">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
	            	<div class="requiredSignNote"><label>Fields marked (*) are required</label></div> 
 		 			<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
		          </form>
		        </div>
		      </div>
		     </div>
		    <div id="FeeTypesList" class="span6">
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>		    
		    </div>
		    <div class="widget-box">
				  <div id="DeleteMessage"></div>
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