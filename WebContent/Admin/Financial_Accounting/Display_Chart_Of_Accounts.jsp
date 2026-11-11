<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="widget-box">
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Chart of Accounts</h5>
</div>
	<c:choose>
	<c:when test="${empty Financial_Accounts_List.financial_Account_id}">
	<div class="message">
		No Accounts found under this school.<br>
	</div>
	</c:when>
	<c:otherwise>
	<c:forEach items="${Financial_Categories_List.financial_Category_id}" var="Financial_Category_id" varStatus="position">
		<table class="table table-bordered data-table">
 			<tr>			
 			<thead>
 			<tr>
 			<th colspan="2">
 			<font style="font:20px/1.2 open-sans;color:blue">
 			<c:out value="${Financial_Categories_List.financial_Category_Name[position.index]}"/>
 			</font>
 			</th>			 			
 			</tr>
 			</thead>
 			<tr>			
 			<thead>
 			<tr>
 			<th>Account Code</th>
 			<th>Account Name</th>	 			 			
 			</tr>
 			</thead>
 			<tbody>
   			<c:forEach items="${Financial_Accounts_List.financial_Account_id}" var="Financial_Account_id" varStatus="position">
			<c:if test="${Financial_Accounts_List.financial_Category[position.index]==Financial_Category_id}">
 			<tr>
 			<td>
 			<c:out value="${Financial_Account_id}"/>
 			</td>
  			<td>
  			<c:out value="${Financial_Accounts_List.financial_Account_Name[position.index]}"/>
 			</td> 			 			 						
 			</tr>
 			</c:if>
  			</c:forEach>	
 			</tbody>

 			</table>
 		</c:forEach>
		</c:otherwise>
		</c:choose>
 </div>
