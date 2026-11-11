<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			total_Fees = parseInt(Subscription_Option) * parseInt(Number_Of_Students)*2;
			document.getElementById("Total_Fees").value=total_Fees;
		}
	else
		{
			document.getElementById("Total_Fees").value="";
		}
}

function calculateTotalNewAgreementFees(Subscription_Option, Number_Of_Extra_Students, Number_Of_Students, Remaining_Amount, Agreement_State)
{
	if(Agreement_State=='Cancelled')
		{
			min_no_of_student = 0;
		}
	else
		{
			min_no_of_student = 1;
		}
	if(Subscription_Option!=""&&Number_Of_Extra_Students!=""&&Number_Of_Extra_Students>=min_no_of_student)
		{
			
			total_Fees = parseInt(Subscription_Option) * (parseInt(Number_Of_Students)+parseInt(Number_Of_Extra_Students))*2;
			document.getElementById("Total_Fees").value=total_Fees;
			
			settlement_Fees = parseInt(total_Fees) - parseInt(Remaining_Amount);
			document.getElementById("Settlement_Fees").value=settlement_Fees;
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
			<c:if test="${Account_Details.agreement_id ne null}">
			<c:set var="Agreement_id" value="${Account_Details.agreement_id}"/>
			<c:set var="No_Of_Students" value="${Account_Details.no_Of_Students}"/>
			<c:set var="FrequencyInterval" value="${Account_Details.frequencyInterval}"/>
			<c:set var="Last_Payment" value="${Account_Details.last_Payment}"/>
			<c:set var="Current_Subscription_Billing" value="${Account_Details.amount_Per_Cycle}"/>
			<c:set var="Last_Payment_Date" value="${Account_Details.last_Payment_Date}"/>
			<c:set var="Days_Consumed" value="${Account_Details.days_Consumed}"/>
			<c:set var="Cost_Till_Today" value="${Account_Details.cost_Till_Today}"/>
			<c:set var="Remaining_Amount" value="${Account_Details.remaining_Amount}"/>		
		</c:if>	
		<div style="margin-left:20px;">
	 	<c:if test="${param.message_Cancel ne 'null' && not empty param.message_Cancel}">
				<hr>
	 			<font size="3" color="red">${param.message_Cancel}</font>
	 		</c:if>
	 	</div>
		<c:choose>		
		<c:when test="${Account_Details.agreementPending==true}">
<!-- 			<div class="messageHeader">Subscription Update In Progress</div> -->
			<hr>
	  		<div class="message">
		  		<c:choose>
		  		<c:when test="${param.message_SubscriptionUpdate ne 'null' && not empty param.message_SubscriptionUpdate}">
		  			${param.message_SubscriptionUpdate}
		  		</c:when>
		  		<c:otherwise>	  			  		
			  		We are still processing your subscription update request.<br/>
			  		Kindly check again within couple of minutes.<br/>
			  		Thank you for your patience.
		  		</c:otherwise>
		  		</c:choose>
	  		</div>		
		</c:when>
		<c:when test="${Account_Details.agreementCancelling==true}">
<!-- 			<div class="messageHeader">Subscription Cancel In Progress</div> -->
			<hr>
	  		<div class="message">
		  		We are still processing your subscription cancel request.<br/>
		  		Kindly check again after a moment.<br/>
		  		Thank you for your patience.
	  		</div>		
		</c:when>
		<c:otherwise>
			<div class="container-fluid">
			  <hr>
			  <div class="row-fluid">
			    <div class="span6">
			      <div class="widget-box">
			        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			          <h5>Subscription Details</h5>
			        </div>
			     	<c:if test="${Account_Details.agreement_State=='Cancelled'}">	     	
			     	<h4>This subscription agreement is already cancelled. However, you still can re-subscribe by filling in the details below:</h4>
			 	    </c:if>
			        <div class="widget-content nopadding">
						<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Subscription_Request">
						<c:if test="${Agreement_id ne null}">
			            <div class="control-group">
						  <label class="control-label">Total current students Licenses</label>
			              
						  <label class="control-label" style="text-align:left;margin-left:20px;"><c:out value="${No_Of_Students}"/> Students </label>							              
					
			            </div>
			            </c:if>
						<div class="control-group">
						  <label class="control-label">
					          <c:choose>
						          <c:when test="${Agreement_id ne null}">
						          	Number of extra students Licenses *
						          </c:when>
						          <c:otherwise>
						          	Number of students (minimum 200 Students) *
						          </c:otherwise>
					          </c:choose> 						  
						  </label>
			              <div class="controls">
					          <c:choose>
						          <c:when test="${Agreement_id ne null}">
						          	<input type="number" name="Number_Of_Extra_Students" id="Number_Of_Extra_Students" class="span11" required maxlength="100" 
						          	 <c:choose>
							          	 <c:when test="${Account_Details.agreement_State=='Cancelled'}">
							          	 	min="0" placeholder="0" 
							          	 </c:when>
							          	 <c:otherwise>
							          	 	min="1" placeholder="1" 
							          	 </c:otherwise>
						          	 </c:choose>						          	 
						          	 onkeyup="calculateTotalNewAgreementFees(Subscription_Option.value, this.value, ${No_Of_Students}, ${Remaining_Amount}, '${Account_Details.agreement_State}');" 
						          	 onchange="calculateTotalNewAgreementFees(Subscription_Option.value, this.value, ${No_Of_Students}, ${Remaining_Amount}, '${Account_Details.agreement_State}');">
						          </c:when>
						          <c:otherwise>
						          	<input type="number" name="Number_Of_Students" id="Number_Of_Students" class="span11" required maxlength="100"  min="200" placeholder="Enter number of students" onkeyup="calculateTotalFees(Subscription_Option.value, this.value);" onchange="calculateTotalFees(Subscription_Option.value, this.value);">
						          </c:otherwise>
					          </c:choose> 						
	 					  </div>
	 					</div>	
						<div class="control-group">
						  <label class="control-label">Subscription Option *</label>
			              <div class="controls">
					          <select name="Subscription_Option" id="Subscription_Option" class="span11" required 	          
					          <c:choose>
						          <c:when test="${Agreement_id ne null}">
						          	onchange="calculateTotalNewAgreementFees(this.value, Number_Of_Extra_Students.value, ${No_Of_Students}, ${Remaining_Amount}, '${Account_Details.agreement_State}');" 
						          </c:when>
						          <c:otherwise>
						          	onchange="calculateTotalFees(this.value, Number_Of_Students.value);" 
						          </c:otherwise>
					          </c:choose>  	          	          
					          >
					 			  <option value=""> 
								  	Select Subscription Option
								  </option>
								  <c:if test="${FrequencyInterval<=1 || FrequencyInterval==null}">
								  		<option value="1" <c:if test="${FrequencyInterval==1}">selected</c:if>>One Month - billing monthly</option>
								  </c:if>
								  
								  <c:if test="${FrequencyInterval<=3 || FrequencyInterval==null}">
								 		<option value="3" <c:if test="${FrequencyInterval==3}">selected</c:if>>Three Months - billing every three months</option>				  
								  </c:if>
								  
								  <c:if test="${FrequencyInterval<=6 || FrequencyInterval==null}">
								  		<option value="6" <c:if test="${FrequencyInterval==6}">selected</c:if>>Six Months - billing every 6 months</option>						  
								  </c:if>
								  
								  <c:if test="${FrequencyInterval<=12 || FrequencyInterval==null}">
								  		<option value="12" <c:if test="${FrequencyInterval==12}">selected</c:if>>One Year - billing annually</option>	
								  </c:if>				  				  
					            </select>													
	 					  </div>
	 					</div>
			            <div class="control-group">
						  <label class="control-label">Subscription Fees / Student / Month</label>
			              <label class="control-label" style="text-align:left;margin-left:20px;">
							$2 per student per month								              
						  </label>
			            </div>
			            <div class="control-group">
						  <label class="control-label">Total <c:if test="${Agreement_id ne null}">New Subscription</c:if> Fees</label>
			              <div class="controls">
							<input type="number" name="Total_Fees" id="Total_Fees" class="span11" required maxlength="100" disabled>USD														              
						  </div>
			            </div>
			           <c:if test="${Agreement_id ne null}">
							<div class="control-group">
							  <label class="control-label">Current Subscription Billing</label>
							  <label class="control-label" style="text-align:left;margin-left:20px;">
								<c:out value="${Current_Subscription_Billing}"/> USD													              
							  </label>
				            </div>
							<div class="control-group">
							  <label class="control-label">Last Payment Date</label>
				              <label class="control-label" style="text-align:left;margin-left:20px;">
								<c:out value="${Last_Payment_Date}"/>													              
							  </label>
				            </div>
							<div class="control-group">
							  <label class="control-label">Days Consumed till today</label>
				              <label class="control-label" style="text-align:left;margin-left:20px;">
								<c:out value="${Days_Consumed}"/> Days												              
							  </label>
				            </div>
							<div class="control-group">
							  <label class="control-label">Cost till today</label>
				              <label class="control-label" style="text-align:left;margin-left:20px;">
								<c:out value="${Cost_Till_Today}"/> USD											              
							  </label>
				            </div>
							<div class="control-group">
							  <label class="control-label">Remaining Amount</label>
				              <label class="control-label" style="text-align:left;margin-left:20px;">
								<c:out value="${Remaining_Amount}"/> USD										              
							  </label>
				            </div>				            
							<div class="control-group">
							  <label class="control-label">Settlement Fees to be paid immediately</label>
				              <div class="controls">
		          				<input type="number" name="Settlement_Fees" id="Settlement_Fees" class="span11" required maxlength="100" disabled>USD										              
							  </div>
				            </div>
			            </c:if>			            
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
		</c:otherwise>
		</c:choose>	 	
	</div>
	<div class="rightContent"><%@include file="../Admin/includes/AdminRight.jsp" %></div>

<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../Admin/includes/AdminFooter.jsp" %></div></div>

</body>
</html>