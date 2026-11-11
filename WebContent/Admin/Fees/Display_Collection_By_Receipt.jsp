<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="span6" style="width:80%">
<div class="widget-box">
<div style="overflow-x:auto;">
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Collection by Receipt</h5>
</div>
	<c:choose>
	<c:when test="${empty Fee_Payment_Amount.receipt_id}">
	<div class="message">
		No Fee Collections found for these criteria.<br>
		<c:if test="${param.Receipt_id!=0}">
		<button class="btn btn-info" onclick="showCollectionByReceipt(School.value, Year_id.value, Grade.value, 0);"> Go Back</button>
		</c:if>
	</div>
	</c:when>
	<c:otherwise>
		<div class="control-group"> 
		  <div class="controls" style="width:30%; margin-top:15px; margin-left:35px;">
		    <div style="display:inline-block; height:30px"><input type="text" name="Receipt_id" id="Receipt_id" class="span11" placeholder="Enter Receipt ID"></div>
		    <div style="display:inline-block; vertical-align:bottom"><button type="button" class="btn btn-success" onclick="showCollectionByReceipt(School.value, Year_id.value, Grade.value, Receipt_id.value);">Get Receipt</button></div>
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
   			<c:forEach items="${Fee_Payment_Amount.receipt_id}" var="Receipt_id" varStatus="position">
 			<tr>
 			<td <c:if test="${R_ID == Receipt_id}">style="border-bottom: 0;border-top:0;"</c:if>>
 			<c:if test="${R_ID != Receipt_id}">			
 			${Fee_Payment_Amount.receipt_Date[position.index]}
 			</c:if>
 			</td>
  			<td <c:if test="${R_ID == Receipt_id}">style="border-bottom: 0;border-top:0;"</c:if>>
 			<c:if test="${R_ID != Receipt_id}">	  			
  			${Fee_Payment_Amount.receipt_id[position.index]}
 			</c:if>
 			</td>
   			<td <c:if test="${R_ID == Receipt_id}">style="border-bottom: 0;border-top:0;"</c:if>>
 			<c:if test="${R_ID != Receipt_id}">	
  			${Fee_Payment_Amount.student_id[position.index]}
 			</c:if>
 			</td>
   			<td <c:if test="${R_ID == Receipt_id}">style="border-bottom: 0;border-top:0;"</c:if>>
 			<c:if test="${R_ID != Receipt_id}">	  			
  			${Fee_Payment_Amount.student_Name[position.index]}
 			</c:if>
 			</td>
  			<td <c:if test="${R_ID == Receipt_id}">style="border-bottom: 0;border-top:0;"</c:if>> 
  			${Fee_Payment_Amount.fee_Name[position.index]}
 			</td>
  			<td <c:if test="${R_ID == Receipt_id}">style="border-bottom: 0;border-top:0;"</c:if>> 
  			${Fee_Payment_Amount.fee_Amount[position.index]}
 			</td>
  			<td <c:if test="${R_ID == Receipt_id}">style="border-bottom: 0;border-top:0;"</c:if>> 
  			${Fee_Payment_Amount.payment_Amount[position.index]}
 			</td>
  			<td <c:if test="${R_ID == Receipt_id}">style="border-bottom: 0;border-top:0;"</c:if>> 
  			${Fee_Payment_Amount.payment_Discount[position.index]}
 			</td>
  			<td <c:if test="${R_ID == Receipt_id}">style="border-bottom: 0;border-top:0;"</c:if>>	 
  			${Fee_Payment_Amount.payment_Fine[position.index]}
 			</td>			 			 			 						
 			</tr>
  			<c:set var="R_ID" value="${Receipt_id}"/>
  			</c:forEach>
  			<tr>
  			<td colspan="6" style="text-align:right; font-weight:bold;">Total</td>
  			<td style="font-weight:bold;">${Fee_Payment_Amount.totalPayment}</td>
  			<td colspan="2"></td> 			
 			</tbody>

 			</table>
			</c:otherwise>
		</c:choose>
 </div>
 </div>
 </div>