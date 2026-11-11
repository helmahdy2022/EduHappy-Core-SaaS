<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select name="Financial_Account_id" id="Financial_Account_id" class="span11">
		<option value="">
			Select Financial Account
		</option>	 	
	<c:forEach items="${Financial_Accounts_List.financial_Account_id}" var="Financial_Account_id" varStatus="loop">

		<option value="${Financial_Account_id}">
			<c:out value="${Financial_Accounts_List.financial_Account_Name[loop.index]}"></c:out>
		</option>	   						 			
	</c:forEach>
	</select>
