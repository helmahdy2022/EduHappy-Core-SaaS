<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Apply Leave</title>

<script>
function showAllEmployees()
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
		document.getElementById("Employee_id").innerHTML=xmlhttp.responseText;
	}
};
xmlhttp.open("GET","${pageContext.request.contextPath}/List_All_Employees?Dropdown=1",true);
xmlhttp.send();
}
 
 function showLeaveTypes(Employee_id)
 {
 if (Employee_id=="")
   {
	   document.getElementById("Leave_id").innerHTML="<select><option value=''>Select Leave Type</option></select>";
	   document.getElementById("EmployeeLeaveList").innerHTML="";
	 	$('#Leave_From').val('');
	 	$('#Leave_To').val('');
	 	$('#Leave_Days').val('');
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
     	document.getElementById("Leave_id").innerHTML=xmlhttp.responseText; 
     	 $('#Leave_From').val('');
     	 $('#Leave_To').val('');
     	 $('#Leave_Days').val('');   	
	     document.getElementById("EmployeeLeaveList").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Leaves?Employee_id="+Employee_id+"&Dropdown=1",true);
 xmlhttp.send();
 }
 
 function showEmployeeLeave(Employee_id, Leave_id)
 {
document.getElementById("EmployeeLeaveList").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>"; 
if (Employee_id=="" && document.getElementById('Employee_Leave_id').value!=0)
   {
 	$('#Leave_From').val('');
 	$('#Leave_To').val('');
 	$('#Leave_Days').val('');
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
 	    document.getElementById("EmployeeLeaveList").innerHTML=xmlhttp.responseText;
 	   $('#employee_Leaves').DataTable();
 		if(document.getElementById('Employee_Leave_id').value!=0)
 		{
 		 	$('#Leave_From').val('');
 		 	$('#Leave_To').val('');
 		 	$('#Leave_Days').val('');
 		}
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Employee_Leave?Employee_id="+Employee_id+"&Leave_id="+Leave_id,true);
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
<body onload="showAllEmployees();">
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">HR and Payroll</a> <a href="${pageContext.request.contextPath}/Admin/HR/Apply_Leave.jsp" class="current">Apply Leave</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Apply Leave</h1>
		</div>
		<!--End-breadcrumbs-->
		<div style="margin-left:10px;color:blue;">${param.message}</div>		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Apply Leave</h5>
		        </div>
		        <div class="widget-content nopadding">
					<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Apply_Leave">
		            <div class="control-group">
					  <label class="control-label">Employee Name *</label>
		              <div class="controls">
			            <select name="Employee_id" id="Employee_id" class="span11" onChange="showLeaveTypes(this.value)" required>
			 			 <option value=""> 
						  	Select Employee
						  </option>  
						</select>  								              
					  </div>
		            </div>
					<div class="control-group">
					  <label class="control-label">Leave Type *</label>
		              <div class="controls">
			         	<select name="Leave_id" id="Leave_id" class="span11" onChange="showEmployeeLeave(Employee_id.value, this.value)" required>
			 			 <option value="">Select Leave Type</option>
			 			</select>  						
 					  </div>
 					</div>	 						           
  					<div class="control-group">
					  <label class="control-label">Leave Date From *</label>
		              <div class="controls">
            			<input name="Leave_From" type="Date" class="span11" id="Leave_From" required>			
 					  </div>
 					</div> 
  					<div class="control-group">
					  <label class="control-label">Leave Date To *</label>
		              <div class="controls">
            			<input name="Leave_To" type="Date" class="span11" id="Leave_To" required>			
 					  </div>
 					</div> 
		            <div class="control-group">
		              <label class="control-label">Number of Leave Days *</label>
		              <div class="controls"> 
	 					<input type="number" id="Leave_Days" name="Leave_Days" class="span11" value="" step="0.01" min="0" max="1000000000" required>                
		              </div>
		            </div> 
		            <div class="form-actions">
		            <input type="hidden" name="Employee_Leave_id" id="Employee_Leave_id">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>	
		     <div id="EmployeeLeaveList" class="span6">		     
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