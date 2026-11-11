<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Subscription Details</title>


<script>
function calculateTotalFees(Subscription_Option, Number_Of_Students)
{
	if(Subscription_Option!=""&&Number_Of_Students!=""&&Number_Of_Students>=200)
		{
			total_Fees = Subscription_Option * Number_Of_Students*2;
			document.getElementById("Total_Fees").value=total_Fees;
		}
	else
		{
			document.getElementById("Total_Fees").value="";
		}
}
</script>
</head>
<body>

<div>
	<%@include file="../Admin/includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../Admin/includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Settings</a> <a href="${pageContext.request.contextPath}/Subscription_Details" class="current">Subscription Details</a> </div>
		  <h1>Subscription Details</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Subscription Details</h5>
		        </div>
		        <div class="widget-content nopadding">
			 		<div>
			 		<font size="3" color="red">${param.message}</font>
			 		</div>
					<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Subscription_Update">
		            <div class="control-group">
					  <label class="control-label">Number of students (minimum 200 Students) *</label>
		              <div class="controls">
	            		<input type="number" name="Number_Of_Students" id="Number_Of_Students" class="span11" required maxlength="100" min="Enter number of students" placeholder="50" onkeyup="calculateTotalFees(Subscription_Option.value, this.value);" onchange="calculateTotalFees(Subscription_Option.value, this.value);">  								              
					  </div>
		            </div>
					<div class="control-group">
					  <label class="control-label">Subscription Option *</label>
		              <div class="controls"> 						
		 	          	<select name="Subscription_Option" id="Subscription_Option" class="span11" required onchange="calculateTotalFees(this.value, Number_Of_Students.value);" style="width:300px;">
			 			  <option value=""> 
						  	Select Subscription Option
						  </option>
						  <option value="1">One Month - billing monthly</option>
						  <option value="3">Three Months - billing every three months</option>
						  <option value="6">Six Months - billing every 6 months</option>
						  <option value="12">One Year - billing annually</option>			  	
			            </select>
 					  </div>
 					</div>	
					<div class="control-group">
					  <label class="control-label">Subscription Fees / Student / Month</label>
		              <div class="controls">
						$2 per student per month 						
 					  </div>
 					</div>
					<div class="control-group">
					  <label class="control-label">Total Fees</label>
		              <div class="controls">
						<input type="number" name="Total_Fees" id="Total_Fees" class="mediumText" required maxlength="100" disabled>USD					
 					  </div>
 					</div>
		            <div class="form-actions">
		            <input type="submit" name="Submit" id="Submit" value="Subscribe Now" class="btn btn-success">
		            </div>
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>	
		 </div>
		</div>
	</div>
	<div class="rightContent"><%@include file="../Admin/includes/AdminRight.jsp" %></div>

<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../Admin/includes/AdminFooter.jsp" %></div></div>
</body>
</html>