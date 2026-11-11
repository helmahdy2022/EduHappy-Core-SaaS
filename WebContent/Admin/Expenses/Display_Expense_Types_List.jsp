<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select name="Expense_Type_id" id="Expense_Type_id" class="span11">
		<option value="">
			Select Expense Type
		</option>	 	
	<c:forEach items="${Expense_Types_List.expense_Type_id}" var="Expense_Type_id" varStatus="loop">
		<option value="${Expense_Type_id}">
			<c:out value='${Expense_Types_List.expense_Type_Name[loop.index]}'/>
		</option>	   						 			
	</c:forEach>
	</select>
