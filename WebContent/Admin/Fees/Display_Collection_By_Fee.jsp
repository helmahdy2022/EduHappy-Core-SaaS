<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="span6" style="width:80%">
<div class="widget-box">
<div style="overflow-x:auto;">
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Collection by Fee</h5>
</div>
	<c:choose>
	<c:when test="${empty Fee_Payment_Amount.fee_id && empty Transport_Payment_Amount.route_id}">
	<div class="message">
		No Fee Collections found for these criteria.<br>
		<c:if test="${param.Fee_id!=0}">
		<button class="btn btn-info" onclick="showCollectionByFee(School.value, Year_id.value, Grade.value, 0);"> Go Back</button>
		</c:if>
	</div>
	</c:when>
	<c:otherwise>
		<div class="control-group"> 
		  <div class="controls" style="width:30%; height:30px; margin-top:15px; margin-left:35px;">
		    <select class="span11" onchange="showCollectionByFee(School.value, Year_id.value, Grade.value, this.value);">
		   	  <option value="">----All Fee Types----</option>
		 	    <c:forEach items="${Fee_Types_List.fee_id}" var="Fee_id" varStatus="position">
			 	<option value="${Fee_id}" <c:if test="${param.Fee_id==Fee_id}">selected</c:if>><c:out value='${Fee_Types_List.fee_Name[position.index]}'/></option>
			  </c:forEach> 
			   <option value="-1" <c:if test="${param.Fee_id==-1}">selected</c:if>>Transport Fees</option>
		    </select>
		  </div>
		</div>
		<table class="table table-bordered data-table">
 			<tr>			
 			<thead>
 			<tr>
 			<th>Date</th>
 			<th>Receipt Number</th>
 			<th>Student ID</th>
 			<th>Student Name</th> 			 			
 			<th>Fee Type</th> 
 			<th>Fee Amount</th>  						
 			<th>Payment</th>
 			<th>Discount</th>
 			<th>Fine</th> 			 			 			
 			</tr>
 			</thead>
 			<tbody>
   			<c:forEach items="${Fee_Payment_Amount.fee_id}" var="Fee_id" varStatus="position">
 			<tr>
 			<td>
 			${Fee_Payment_Amount.receipt_Date[position.index]}
 			</td>
  			<td>
  			${Fee_Payment_Amount.receipt_id[position.index]}
 			</td>
   			<td>
  			${Fee_Payment_Amount.student_id[position.index]}
 			</td>
   			<td>
  			${Fee_Payment_Amount.student_Name[position.index]}
 			</td>
  			<td>
  			${Fee_Payment_Amount.fee_Name[position.index]}
 			</td>
  			<td>
  			${Fee_Payment_Amount.fee_Amount[position.index]}
 			</td>
  			<td>
  			${Fee_Payment_Amount.payment_Amount[position.index]}
 			</td>
  			<td>
  			${Fee_Payment_Amount.payment_Discount[position.index]}
 			</td>
  			<td>
  			${Fee_Payment_Amount.payment_Fine[position.index]}
 			</td>			 			 			 						
 			</tr>
  			</c:forEach>
  			
  			<c:forEach items="${Transport_Payment_Amount.route_id}" var="Route_id" varStatus="position">
 			<tr>
 			<td>
 			${Transport_Payment_Amount.receipt_Date[position.index]}
 			</td>
  			<td>
  			${Transport_Payment_Amount.receipt_id[position.index]}
 			</td>
   			<td>
  			${Transport_Payment_Amount.student_id[position.index]}
 			</td>
   			<td>
  			${Transport_Payment_Amount.student_Name[position.index]}
 			</td>
  			<td>
  			Transport Fees
 			</td>
  			<td>
  			${Transport_Payment_Amount.transport_Fees[position.index]}
 			</td>
  			<td>
  			${Transport_Payment_Amount.payment_Transport_Amount[position.index]}
 			</td>
  			<td>
  			${Transport_Payment_Amount.payment_Transport_Discount[position.index]}
 			</td>
  			<td>
  			${Transport_Payment_Amount.payment_Transport_Fine[position.index]}
 			</td>			 			 			 						
 			</tr>
  			</c:forEach>
  			<tr>
  			<td colspan="6" style="text-align:right; font-weight:bold;">Total</td>
  			<td style="font-weight:bold;">${Fee_Payment_Amount.totalPayment+Transport_Payment_Amount.totalPayment}</td>
  			<td colspan="2"></td> 			
 			</tbody>

 			</table>
			</c:otherwise>
		</c:choose>
 </div>
 </div>
 </div>