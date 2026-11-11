<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Payments</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${pageContext.request.contextPath}/_css/Admin_Main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/fullcalendar.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/datepicker.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/colorpicker.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-wysihtml5.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/uniform.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/select2.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-media.css" />
<link href="${pageContext.request.contextPath}/Global/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/jquery.gritter.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/jquery-ui.css" />
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

</head>
<body style="background-color:whire;">
<div>
  <div class="container-fluid">
    <div class="row-fluid">
	  <div >
        <div class="widget-box">
		<div style="overflow-x:auto;">
           <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			<h5>Student Payment for Academic Year <font color="blue">${param.Current_Year}</font></h5>
		   </div>
   		   <div class="widget-content nopadding">
		    <form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Fee_Payments">
		  	  <div class="control-group" style="text-align:right"> 
		  	   <div class="controls">
		   		<select class="span11" onchange="window.open('${pageContext.request.contextPath}/Get_Fee_Payments?Student_id=${param.Student_id}&&School_Year_id='+this.value, 'Payment_Window', 'toolbar=no, menubar=no, location=no, width=900,height=500, top=300, left=500');">
		   		 <option value="">Select another Academic Year</option>
		 		 <c:forEach items="${School_Years.school_Year_id}" var="School_Year_id" varStatus="position">
			 	 <option value="${School_Year_id}"><c:out value='${School_Years.year_Name[position.index]}'/></option>
			  	 </c:forEach> 
		   		</select>
		  	   </div>
		  	  </div>		
			  <c:choose>
			   <c:when test="${empty Fee_Types_List.fee_id}">
			    <div class="message">No Fee Types have been created for this Fee Payments. Please create fee types and then try again.</div>
			   </c:when>
			   <c:otherwise>
		        <table class="table table-bordered data-table">
				 <thead>
				  <tr>
				   <th>Fee Name</th>
				   <th>Fee</th>
				   <th>Paid</th>
				   <th>Applied Discount</th>	
				   <th>Applied Fine</th>		  
				   <th>Due</th>
				   <th style="width:80px">Amount</th>
				   <th>Discount</th>
				   <th>Fine</th>
				  </tr>
				 </thead>
				 <tbody>
				<c:forEach items="${Fee_Types_List.fee_id}" var="Fee_id" varStatus="position">
				 <tr class="gradeA">
				  <td><label><c:out value='${Fee_Types_List.fee_Name[position.index]}'/></label></td>
				  <td>		
		       		<c:forEach items="${Fee_Structure.fee_id}" var="Existing_Fee_id" varStatus="loop">
		       		  <c:if test = "${Existing_Fee_id==Fee_id}">
						<c:out value="${Fee_Structure.fee_Amount[loop.index]}"/>							
		       		  </c:if>	
		       		</c:forEach>	
				  </td>	
				  <td>
		       		<c:forEach items="${Fee_Payment_Amount.fee_id}" var="Existing_Fee_id" varStatus="loop">
		       		  <c:if test = "${Existing_Fee_id==Fee_id}">
						<c:out value="${Fee_Payment_Amount.payment_Amount[loop.index]}"/>							
		       		  </c:if>	
		       		</c:forEach>
				  </td>		  
				  <td>
		       		<c:forEach items="${Fee_Payment_Amount.fee_id}" var="Existing_Fee_id" varStatus="loop">
		       		  <c:if test = "${Existing_Fee_id==Fee_id}">
						<c:out value="${Fee_Payment_Amount.payment_Discount[loop.index]}"/>							
		       		  </c:if>	
		       		</c:forEach>
				  </td>
				  <td>
		       		<c:forEach items="${Fee_Payment_Amount.fee_id}" var="Existing_Fee_id" varStatus="loop">
		       		  <c:if test = "${Existing_Fee_id==Fee_id}">
						<c:out value="${Fee_Payment_Amount.payment_Fine[loop.index]}"/>							
		       		  </c:if>	
		       		</c:forEach>
				  </td>			  	
				  <td>
		       		<c:forEach items="${Fee_Payment_Amount.fee_id}" var="Existing_Fee_id" varStatus="loop">
		       		  <c:if test = "${Existing_Fee_id==Fee_id}">
						<c:out value="${Fee_Payment_Amount.due_Amount[loop.index]}"/>							
		       		  </c:if>	
		       		</c:forEach>		  
				  </td>
				  <td>
				    <c:forEach items="${Fee_Payment_Amount.fee_id}" var="Existing_Fee_id" varStatus="loop">
				  	 <c:if test="${Existing_Fee_id==Fee_id && Fee_Payment_Amount.allow_Entry[loop.index]==true}">
				   		<input type="number" step="0.01" class="span11" style="width:80px" name="Payment_Amount${Fee_id}" id="Payment_Amount${Fee_id}">
				  	 </c:if>
				    </c:forEach>
				  </td>
				  <td>
				    <c:forEach items="${Fee_Payment_Amount.fee_id}" var="Existing_Fee_id" varStatus="loop">
				  	 <c:if test="${Existing_Fee_id==Fee_id && Fee_Payment_Amount.allow_Entry[loop.index]==true}">
				  		<input type="number" step="0.01" class="span11" style="width:80px" name="Payment_Discount${Fee_id}" id="Payment_Discount${Fee_id}">		  
				  	 </c:if>
				    </c:forEach>
				  </td>
				  <td>
				    <c:forEach items="${Fee_Payment_Amount.fee_id}" var="Existing_Fee_id" varStatus="loop">
				  	 <c:if test="${Existing_Fee_id==Fee_id && Fee_Payment_Amount.allow_Entry[loop.index]==true}">
				  		<input type="number" step="0.01" class="span11" style="width:80px" name="Payment_Fine${Fee_id}" id="Payment_Fine${Fee_id}">		  
				     </c:if>
				    </c:forEach>
				  </td>
				  </tr>
				</c:forEach>
				 <c:if test="${not empty Transport_Fees.route_id || (Fee_Payment_Amount.paidTransportAmount!=-1 && Fee_Payment_Amount.paymentTransportDiscount!=-1 && Fee_Payment_Amount.paymentTransportFine!=-1)}">
				 <tr class="gradeA">
				  <td><label>Transport Fees</label></td>
				  <td>		
		       		<c:choose>
		       		<c:when test="${not empty Transport_Fees.route_id}">
		       			<c:forEach items="${Transport_Fees.route_id}" var="Route_id" varStatus="loop">
						<c:out value="${Transport_Fees.transport_Fees[loop.index]}"/>
						<input type="hidden" name="Route_id" value="${Route_id}">							
		       		</c:forEach>
		       		</c:when>
		       		<c:otherwise>
		       			<c:out value="${Fee_Payment_Amount.transportFees}"/>
		       			<input type="hidden" name="Route_id" value="${Fee_Payment_Amount.mostRecentRoute_id}">
		       		</c:otherwise>
		       		</c:choose>		       			
				  </td>	
				  <td>
					<c:choose>
					  <c:when test="${Fee_Payment_Amount.paidTransportAmount==-1}">
						0.0
					  </c:when>
					  <c:otherwise>
						<c:out value="${Fee_Payment_Amount.paidTransportAmount}"/>
					  </c:otherwise>
					</c:choose>	
				  </td>		  
				  <td>
					<c:choose>
					  <c:when test="${Fee_Payment_Amount.paymentTransportDiscount==-1}">
						0.0
					  </c:when>
					  <c:otherwise>
						<c:out value="${Fee_Payment_Amount.paymentTransportDiscount}"/>
					  </c:otherwise>
					</c:choose>
				  </td>
				  <td>
					<c:choose>
					  <c:when test="${Fee_Payment_Amount.paymentTransportFine==-1}">
						0.0
					  </c:when>
					  <c:otherwise>
					  	<c:out value="${Fee_Payment_Amount.paymentTransportFine}"/>
					  </c:otherwise>
					  </c:choose>
				  </td>			  	
				  <td>
					<c:out value="${Fee_Payment_Amount.dueTransport}"/>		  
				  </td>
				  <td>
					<input type="number" step="0.01" class="span11" style="width:80px" name="Transport_Payment_Amount" id="Transport_Payment_Amount">
				  </td>
				  <td>
					<input type="number" step="0.01" class="span11" style="width:80px" name="Transport_Payment_Discount" id="Transport_Payment_Discount">	
				  </td>
				  <td>
				  	<input type="number" step="0.01" class="span11" style="width:80px" name="Transport_Payment_Fine" id="Transport_Payment_Fine">
				  </td>
				  </tr>
				  </c:if>
				<tr>
				<td colspan="9" style="text-align:center">
				<input type="submit" class="btn btn-success" value="Assign Payments and Generate Receipt">
				</td>
				</tr>
				</tbody>
		       </table>
				  <input type="hidden" name="Student_id" id="Student_id" value="${param.Student_id}">
				  <input type="hidden" name="School_Year_id" id="School_Year_id" value="${param.School_Year_id}">
				  <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
		  	   </c:otherwise>
		  	  </c:choose>
		  </form>
 		 </div>
		</div>
		</div>
	  </div>
	</div>
 </div>
</div>
</body>
</html>