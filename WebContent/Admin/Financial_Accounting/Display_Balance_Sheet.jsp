<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="span6">
  <div class="widget-box">
   <div style="overflow-x:auto;">
	<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		<h5>Balance Sheet</h5>
	</div>
	<table class="table table-bordered data-table">
 	  <tr>			
 	  <tbody>
		<tr>
		<td rowspan="2">
		  <table class="table table-bordered data-table">
			<thead>
			<tr>
			<th colspan="2"><font style="font:20px/1.2 open-sans;">Assets</font></th>	 			 			
			</tr>
			</thead> 
			<tbody>
			<c:forEach items="${Balance_Sheet.financial_Asset_Account_id}" var="Asset_Financial_Account_id" varStatus="position">
			<tr>
			<td style="width:70%">
			<c:out value="${Balance_Sheet.financial_Asset_Account_Name[position.index]}" />
			</td>
			<td>
			<c:out value="${Balance_Sheet.financial_Asset_Amount[position.index]}" />
			</td>
			</tr>
			</c:forEach>
			<tr>
			<td style="text-align:right; font-weight:bold">
			Total Assets
			</td>
			<td  style="text-align:left; font-weight:bold">
			<c:out value="${Balance_Sheet.totalAssetAmount}" />
			</td>
			</tr>
			</tbody>			
		  </table>
		</td>
		<td>
		<table class="table table-bordered data-table">
			<thead>
			<tr>
			<th colspan="2"><font style="font:20px/1.2 open-sans;">Liabilities</font></th>	 			 			
			</tr>
			</thead> 
			<tbody>
			<c:forEach items="${Balance_Sheet.financial_Liabilities_Account_id}" var="Liabilities_Financial_Account_id" varStatus="position"> 			
			<tr>
			<td style="width:70%"> 
			<c:out value="${Balance_Sheet.financial_Liabilities_Account_Name[position.index]}" />
			</td>
			<td>
			<c:out value="${Balance_Sheet.financial_Liabilities_Amount[position.index]}" />
			</td>
			</tr>
			</c:forEach>
			<tr>
			<td style="text-align:right; font-weight:bold">
			Total Liabilities
			</td>
			<td  style="text-align:left; font-weight:bold">
			<c:out value="${Balance_Sheet.totalLiabilitiesAmount}" />
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
			<th colspan="2"><font style="font:20px/1.2 open-sans;">Owners' Equity</font></th>	 			 			
			</tr>
			</thead> 
			<tbody>
			<c:forEach items="${Balance_Sheet.financial_Equity_Account_id}" var="Equity_Financial_Account_id" varStatus="position">
			<tr>
			<td style="width:70%">
			<c:out value="${Balance_Sheet.financial_Equity_Account_Name[position.index]}" />
			</td>
			<td>
			<c:out value="${Balance_Sheet.financial_Equity_Amount[position.index]}" />
			</td>
			</tr>
			</c:forEach>
			<tr>
			<td style="text-align:right; font-weight:bold">
			SubTotal Owners' Equity
			</td>
			<td  style="text-align:left; font-weight:bold">
			<c:out value="${Balance_Sheet.subTotalEquityAmount}" />
			</td>
			</tr>
			<tr>
			<td style="text-align:right;">
			Net Income (Revenue - Expenses)
			</td>
			<td  style="text-align:left;">
			<c:out value="${Balance_Sheet.netIncome}" />
			</td>
			</tr> 
			<tr>
			<td style="text-align:right; font-weight:bold">
			Total Owners' Equity
			</td>
			<td  style="text-align:left; font-weight:bold">
			<c:out value="${Balance_Sheet.totalEquityAmount}" />
			</td>
			</tr>  			 			 			
			</tbody>			
		</table>  			 			
		</td>
		</tr>		
		<tr>
		<td style="text-align:center; font-weight:bold">
		Total Assets = <c:out value="${Balance_Sheet.totalAssetAmount}" />
		</td>
		<td style="text-align:center; font-weight:bold">
		Total Liabilities + Owners' Equity = <c:out value="${Balance_Sheet.liabilityPlusEquity}" />
		</td>
		</tr>
		</tbody>
 	</table>
    </div>
   </div>
 </div>