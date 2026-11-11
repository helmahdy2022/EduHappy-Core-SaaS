<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="span6">
  <div class="widget-box">
   <div style="overflow-x:auto;">
    <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		<h5>Income Statement</h5>
	</div>
	<table class="table table-bordered data-table">
		<tr>			
		<tbody>
		<tr>
		<td>
		  <table class="table table-bordered data-table">
			<thead>
			<tr>
			<th colspan="2"><font style="font:20px/1.2 open-sans;">Revenue</font></th>	 			 			
			</tr>
			</thead> 
			<tbody>
			<c:forEach items="${Income_Statement.financial_Revenue_Account_id}" var="Revenue_Financial_Account_id" varStatus="position"> 			
			<tr>
			<td style="width:70%"> 
			<c:out value="${Income_Statement.financial_Revenue_Account_Name[position.index]}" />
			</td>
			<td>
			<c:out value="${Income_Statement.financial_Revenue_Amount[position.index]}" />
			</td>
			</tr>
			</c:forEach>
			<tr>
			<td style="text-align:right; font-weight:bold">
			Total Revenue
			</td>
			<td  style="text-align:left; font-weight:bold">
			<c:out value="${Income_Statement.totalRevenueAmount}" />
			</td>
			</tr> 			
			</tbody>			
		  </table>
		</td>
		</tr>
		<tr>
		<td>
		  <table class="table table-bordered data-table">
			<thead>
			<tr>
			<th colspan="2"><font style="font:20px/1.2 open-sans;">Expenses</font></th>	 			 			
			</tr>
			</thead> 
			<tbody>
			<c:forEach items="${Income_Statement.financial_Expenses_Account_id}" var="Expenses_Financial_Account_id" varStatus="position">
			<tr>
			<td style="width:70%">
			<c:out value="${Income_Statement.financial_Expenses_Account_Name[position.index]}" />
			</td>
			<td>
			<c:out value="${Income_Statement.financial_Expenses_Amount[position.index]}" />
			</td>
			</tr>
			</c:forEach>
			<tr>
			<td style="text-align:right; font-weight:bold">
			Total Expenses
			</td>
			<td  style="text-align:left; font-weight:bold">
			<c:out value="${Income_Statement.totalExpensesAmount}" />
			</td>
			</tr>
			</tbody>			
		  </table>
		</td>	 			 			 						
		</tr>
		<tr>
		<td> 			 			
		</td>
		</tr>		
		<tr>
		<td style="text-align:center; font-weight:bold">
		Net Income (Revenue - Expenses) = <c:out value="${Income_Statement.netIncome}" />
		</td>
		</tr>
		</tbody>
	</table>
    </div>
   </div>
 </div>