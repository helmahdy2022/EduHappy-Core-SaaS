<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="span6">
  <div class="widget-box">
   <div style="overflow-x:auto;">
    <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
	  <h5>Trial Balance Report</h5>
    </div>
	<c:choose>
	<c:when test="${empty Trial_Balance.financial_Account_id}">
	<div class="message">
		No Journal entries found for these criteria.<br>
	</div>
	</c:when>
	<c:otherwise>
		<table class="table table-bordered data-table">
 			<tr>			
 			<thead>
 			<tr>
 			<th>Account Title</th>
 			<th>Debit</th>
 			<th>Credit</th>			 			 			
 			</tr>
 			</thead>
 			<tbody>
   			<c:forEach items="${Trial_Balance.financial_Account_id}" var="Financial_Account_id" varStatus="position">
 			<tr>
 			<td>
 			<c:out value="${Trial_Balance.financial_Account_Name[position.index]}"/>
 			</td>
  			<td>
  			<c:out value="${Trial_Balance.financial_Account_Debit[position.index]}"/>
 			</td>
   			<td>
  			<c:out value="${Trial_Balance.financial_Account_Credit[position.index]}"/>
 			</td>	 			 			 						
 			</tr>
  			</c:forEach>
  			<tr>
  			<td></td>
  			<td style="font-weight:bold;"><c:out value="${Trial_Balance.totalDebitAmount}"/></td>
  			<td style="font-weight:bold;"><c:out value="${Trial_Balance.totalCreditAmount}"/></td> 	
  			</tr>		
 			</tbody>

 			</table>
			</c:otherwise>
		</c:choose>
   </div>
  </div>
</div>