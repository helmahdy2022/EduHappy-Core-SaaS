<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Employee Promotion</title>

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
 
function showEmployeePromotion(Employee_id)
{
document.getElementById("EmployeePromotionList").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Employee_id=="" && document.getElementById('Employee_Promotion_id').value!=0)
  {
	$('#Promotion_Date').val('');
	$('#Basic_Salary').val('');
	$('#Promotion_Designation').val('');
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
	    document.getElementById("EmployeePromotionList").innerHTML=xmlhttp.responseText;
	    $('#promotions').DataTable();
		if(document.getElementById('Employee_Promotion_id').value!=0)
		{
			$('#Promotion_Date').val('');
			$('#Basic_Salary').val('');
			$('#Promotion_Designation').val('');
		}
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Employee_Promotion?Employee_id="+Employee_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">HR and Payroll</a> <a href="${pageContext.request.contextPath}/Admin/HR/Promote_Employee.jsp" class="current">Employee Promotion</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Employee Promotion</h1>
		</div>
		<!--End-breadcrumbs-->
		<div style="margin-left:10px;color:blue;">${param.message}</div>		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Employee Promotion</h5>
		        </div>
		        <div class="widget-content nopadding">
					<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Employee_Promotion">
		            <div class="control-group">
					  <label class="control-label">Employee Name *</label>
		              <div class="controls">
			            <select name="Employee_id" id="Employee_id" class="span11" onChange="showEmployeePromotion(this.value)" required>
			 			 <option value=""> 
						  	Select Employee
						  </option>  
						</select>  								              
					  </div>
		            </div>	 						           
  					<div class="control-group">
					  <label class="control-label">Promotion Effective Date *</label>
		              <div class="controls">
            			<input name="Promotion_Date" type="Date" class="span11" id="Promotion_Date" required>			
 					  </div>
 					</div> 
		            <div class="control-group">
		              <label class="control-label">New Basic Salary *</label>
		              <div class="controls"> 
	 					<input type="number" id="Basic_Salary" name="Basic_Salary" class="span11" value="" step="0.01" min="0" max="1000000000" required>                
		              </div>
		            </div> 
		            <div class="control-group">
		              <label class="control-label">New Designation</label>
		              <div class="controls">
		                <input type="text" name="Promotion_Designation" id="Promotion_Designation" class="span11"  placeholder="New Designation" maxlength="100">
		              </div>
		            </div>	
		            <div class="form-actions">
		            <input type="hidden" name="Employee_Promotion_id" id="Employee_Promotion_id">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>	
		     <div id="EmployeePromotionList" class="span6">
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