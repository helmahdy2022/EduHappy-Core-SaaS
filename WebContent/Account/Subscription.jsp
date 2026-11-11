<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!doctype html>
<html lang="en">  
<head>
        <title>Sign up For Free Trial</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-login.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-responsive.min.css" />
		<link href="${pageContext.request.contextPath}/Global/font-awesome/css/font-awesome.css" rel="stylesheet" />
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
 		<script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/jquery-1.11.3.js"></script> 	

		<c:choose>
		<c:when test="${Account_Details.no_Of_Students > 200}">
		<c:set var="Min_Number_Of_Students" value="${Account_Details.no_Of_Students}"/>
		</c:when>
		<c:otherwise>
		<c:set var="Min_Number_Of_Students" value="200"/>
		</c:otherwise>
		</c:choose>
		<script>
		function calculateTotalFees(Subscription_Option, Number_Of_Students)
		{
			var Min_Number_Of_Students = ${Min_Number_Of_Students};
			if(Subscription_Option!=""&&Number_Of_Students!=""&&Number_Of_Students>=Min_Number_Of_Students)
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
  <body style="background-color:#eeeeee">
	<div class="wrapper">
		<%@include file="../Admin/includes/Signup_Header.jsp" %>
		<main id="content" class="neve-main" role="main">
		<div class="container single-page-container">
			<div class="row">
				<div class="nv-single-page-wrap col">
					<div class="nv-page-title-wrap nv-big-title">
						<div class="nv-page-title ">
							<small class="nv--yoast-breadcrumb neve-breadcrumbs-wrapper"><span><span><a
										href="https://edu-happy.com/">Home</a> » <span
										class="breadcrumb_last" aria-current="page">Sign up</span></span></span></small>
							<h2>Subscription</h2>
						</div>
					</div>
					<div class="nv-content-wrap entry-content">
				        <div id="loginbox">           
							 <div class="control-group normal_text" style="background-color:#fff"> 
								 <p style="margin-bottom: 15px;margin-top: 15px;padding-left: 30px;padding-right: 30px;text-align: center;font-size:1.3em; color:#000">
							  		<c:if test="${not empty param.message && param.message!='null'}">
							  			<br/><font size="3" color="red">${param.message} </font><br/>
							  		</c:if> 		
							  			${param.expiry_message}<br/><br/>
							 		<c:if test="${param.AgreementPending==false || empty param.AgreementPending}">
							  			<c:if test="${param.User=='Admin'}">
							  			  	<input type="button" id="myBtn" name="Subscribe" class="btn btn-success" style="background-color:orange; color:black; background-image:none" value="Subscribe here"/><br/><br/>
							  			</c:if>
							  		</c:if>
										If you would like to login into different account 
						  			<c:if test="${param.User!='Teacher' || param.User!='Student'}">
						 				<a href="${pageContext.request.contextPath}/Admin/Admin_Signout.jsp" style="color:red">click here</a><br/>
						 			</c:if> 
						 			<c:if test="${param.User=='Teacher'}">
						 				<a href="${pageContext.request.contextPath}/Teacher/Teacher_Signout.jsp" style="color:red">click here</a><br/>
						 			</c:if>
						 			<c:if test="${param.User=='Student'}">
						 				<a href="${pageContext.request.contextPath}/Student/Student_Signout.jsp" style="color:red">click here</a><br/>
						 			</c:if> 		
								 </p>
							 </div>
				        </div>
						<!-- The Modal -->
						<div id="myModal" class="modal" style="display:none;">
						  <!-- Modal content -->
						  <div class="modal-content" style="width:100%;">
						    <span class="close">×</span>		
						<div class="container-fluid">
						  <div class="row-fluid">
				
						      <div class="widget-box">
						        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
						          <h5>Subscription Details</h5>
						        </div>
						        <div class="widget-content nopadding">
									<form name="form1" method="post" id="form1" class="form-vertical" action="${pageContext.request.contextPath}/Subscription_Request">
						            <div class="control-group">
									  <label class="control-label">Number of students (minimum ${Min_Number_Of_Students} Students) *</label>
						              <div class="controls">
				  						<input type="number" name="Number_Of_Students" id="Number_Of_Students" class="span11" required maxlength="100" min="${Min_Number_Of_Students}" placeholder="Enter number of students" onkeyup="calculateTotalFees(Subscription_Option.value, this.value);" onchange="calculateTotalFees(Subscription_Option.value, this.value);"> 							              
									  </div>
						            </div>
						            <div class="control-group">
									  <label class="control-label">Subscription Option *</label>
						              <div class="controls">
							          	<select name="Subscription_Option" id="Subscription_Option" required onchange="calculateTotalFees(this.value, Number_Of_Students.value);" class="span11">
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
				  						<div class="input-append">
				  						<input type="number" name="Total_Fees" id="Total_Fees" class="span11" required maxlength="100" disabled><span class="add-on" style="height:100%">$</span>
						                </div>											              
									  </div>
						            </div>
									<div style="text-align:center;">
						            <input type="submit" name="Submit" id="Submit" value="Subscribe Now" class="btn btn-success" style="background-color:orange; color:black; background-image:none" >
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
					</div>
				</div>
			</div>
		</div>
		</main>
		<%@include file="../Admin/includes/Signup_Footer.jsp" %>
	</div> 

<!-- Start of HubSpot Embed Code -->
<script type="text/javascript" id="hs-script-loader" async defer src="//js-eu1.hs-scripts.com/24991813.js"></script>
<!-- End of HubSpot Embed Code -->         
		<script>
		// Get the modal
		var modal = document.getElementById('myModal');
		
		// Get the button that opens the modal
		var btn = document.getElementById("myBtn");
		
		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];
		
		// When the user clicks the button, open the modal
		btn.onclick = function() {
		    modal.style.display = "block";
		}
		
		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
		    modal.style.display = "none";
		}
		
		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
		    if (event.target == modal) {
		        modal.style.display = "none";
		    }
		}
		</script>
</body>
</html>
