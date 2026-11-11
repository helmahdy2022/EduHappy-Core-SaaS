<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Manage Expenses</title> 
<script>

function showExpenseTypes(School_id)
{
	if (School_id=="" && document.getElementById('Expense_Type_id').value!=0)
	  {
		document.getElementById("ExpensesList").innerHTML="";
		document.getElementById("DeleteMessage").innerHTML= "";
		$('#Expense_Date').val('');
		$('#Reference_Number').val('');
		$('#Expense_Type_id').val('');
		$('#Paid_To').val('');
		$('#Expense_Amount').val('');
		$('#Payment_Method').val('');										
		$('#Phone_Number').val('');																
		$('#Expense_Details').val('');
		$('#Expense_id').val('');
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
	    document.getElementById("Expense_Type_id").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Expense_Types?School_id="+School_id+"&Dropdown=1",true);
xmlhttp.send();
}

function showExpensesList(Expense_Type_id)
{
document.getElementById("ExpensesList").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Expense_Type_id=="" && document.getElementById('Expense_id').value!=0)
  {
	document.getElementById("ExpensesList").innerHTML="";
	document.getElementById("DeleteMessage").innerHTML= "";
	$('#Expense_Date').val('');
	$('#Reference_Number').val('');
	$('#Expense_Type_id').val('');
	$('#Paid_To').val('');
	$('#Expense_Amount').val('');
	$('#Payment_Method').val('');										
	$('#Phone_Number').val('');																
	$('#Expense_Details').val('');
	$('#Expense_id').val('');
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
	    document.getElementById("ExpensesList").innerHTML=xmlhttp.responseText;
	    $('#expenses').DataTable();
	    document.getElementById("DeleteMessage").innerHTML= "";
		if(document.getElementById('Expense_id').value!=0)
		{
			$('#Expense_Date').val('');
			$('#Reference_Number').val('');
			$('#Expense_Type_id').val('');
			$('#Paid_To').val('');
			$('#Expense_Amount').val('');
			$('#Payment_Method').val('');										
			$('#Phone_Number').val('');																	
			$('#Expense_Details').val('');
			$('#Expense_id').val('');
		}
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Expenses?Expense_Type_id="+Expense_Type_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Expenses Management</a> <a href="${pageContext.request.contextPath}/Admin/Expenses/Manage_Expenses.jsp" class="current">Manage Expenses</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Manage Expenses</h1>
		</div>
		<!--End-breadcrumbs-->
		<div style="margin-left:10px;color:blue;">${param.message}</div>		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Expense</h5>
		        </div>
		        <div class="widget-content nopadding">
    			<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Expense">	  
		            <div class="control-group">
					  <label class="control-label">School Name *</label>
		              <div class="controls">
			            <select name="School" id="School" class="span11" onChange="showExpenseTypes(this.value)" required>
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
					  <label class="control-label">Expense Type *</label>
		              <div class="controls">
	 					<Select name="Expense_Type_id" class="span11" id="Expense_Type_id" onchange="showExpensesList(this.value)" required>
	 						<option value="">Select Expense Type</option>
	 					</Select>	          							
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Expense Date *</label>
		              <div class="controls">
            			<input name="Expense_Date" type="Date" class="span11" id="Expense_Date" required>			
 					  </div>
 					</div> 
					<div class="control-group">
					  <label class="control-label">Reference Number</label>
		              <div class="controls">
            			<input type="text" name="Reference_Number" id="Reference_Number" class="span11" maxlength="100">					
 					  </div>
 					</div> 	
   					<div class="control-group">
					  <label class="control-label">Paid To</label>
		              <div class="controls">
	 					<input name="Paid_To" type="text" class="span11" id="Paid_To" maxlength="100">			
 					  </div>
 					</div>   					
   					<div class="control-group">
					  <label class="control-label">Expense Amount *</label>
		              <div class="controls">
	 					<input type="number" step="0.01" class="span11" name="Expense_Amount" id="Expense_Amount" required>		
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Payment Method *</label>
		              <div class="controls">
	 					<Select name="Payment_Method" class="span11" id="Payment_Method" required>
	 						<option value="">Select Payment Mode</option>
	 						<option value="Cash">Cash</option>
	 						<option value="Cheque">Cheque</option>
	 						<option value="Online Banking">Online Banking</option>
	 						<option value="Credit Card">Credit Card</option>
	 						<option value="Money Transfer">Money Transfer</option>
	 						<option value="Money Order">Money Order</option>
	 						<option value="Other">Other</option>
	 					</Select>			
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Phone Number</label>
		              <div class="controls">
	 					<input name="Phone_Number" type="text" class="span11" id="Phone_Number" maxlength="50">			
 					  </div>
 					</div>		
  					<div class="control-group">
					  <label class="control-label">Details</label>
		              <div class="controls">
	 					 <input name="Expense_Details" type="text" class="span11" id="Expense_Details"  maxlength="255">			
 					  </div>
 					</div>  					 	
		            <div class="form-actions">
		            <input type="hidden" name="Expense_id" id="Expense_id">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>	
		     <div id="ExpensesList" class="span6">
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>		     
		     </div>
		     <div class="span6">
		     <div class="widget-box">
				  <div id="DeleteMessage"></div>
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