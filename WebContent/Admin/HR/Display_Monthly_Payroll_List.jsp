<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select name="Monthly_Payroll_id" id="Monthly_Payroll_id" class="span11">
		<option value="">
			Select Payroll Month
		</option>	 	
	<c:forEach items="${Monthly_Payroll_List.monthly_Payroll_id}" var="Monthly_Payroll_id" varStatus="loop">
		<option value="${Monthly_Payroll_id}">
			<c:out value='${Monthly_Payroll_List.report_Month[loop.index]}'/>
		</option>	   						 			
	</c:forEach>
	</select>
