<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="span6">
  <div class="widget-box">
   <div style="overflow-x:auto;">
    <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
     <h5>General Ledger Report - <font color="blue"><c:out value="${General_Ledger.financial_Account_Name}"/></font> Account</h5>
    </div>
	<c:choose>
	<c:when test="${empty General_Ledger.entry_id}">
	<div class="message">
		No ledger entries found for these criteria.<br>
	</div>
	</c:when>
	<c:otherwise>
		<table id="general_Ledger" class="table table-striped table-bordered" border="1" style="width:90%">		
 			<thead>
 			<tr>
 			<th>Date</th>
 			<th>Transaction Details</th>
 			<th>Debit</th>	
 			<th>Credit</th>	
 			<th>Balance</th>		 			 			
 			</tr>
 			</thead>
 			<tbody>
   			<c:forEach items="${General_Ledger.entry_id}" var="Entry_id" varStatus="position">
 			<tr>
 			<td>
 			<c:out value="${General_Ledger.journal_Date[position.index]}"/>
 			</td>
  			<td style="width:45%">
  			<c:out value="${General_Ledger.journal_Details[position.index]}"/>
 			</td>
   			<td>
  			<c:out value="${General_Ledger.debit_Amount[position.index]}"/>
 			</td>
   			<td>
  			<c:out value="${General_Ledger.credit_Amount[position.index]}"/>
 			</td> 
 			<td>
 			<c:choose>
 			<c:when test="${General_Ledger.balance_Amount[position.index]<0}">
 			<c:out value="(${General_Ledger.balance_Amount[position.index]*-1})"/>
 			</c:when>
 			<c:otherwise>
 			<c:out value=""/>${General_Ledger.balance_Amount[position.index]}
 			</c:otherwise>
 			</c:choose> 			
 			</td>				 			 			 						
 			</tr>
  			</c:forEach>		
 			</tbody>

 			</table>
			</c:otherwise>
		</c:choose>
   </div>
  </div>
</div>