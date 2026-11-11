<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select name="Payroll_id" id="Payroll_id" class="span11">
		<option value="">
			Select Payroll
		</option>	 	
	<c:forEach items="${Payroll_List.payroll_id}" var="Payroll_id" varStatus="loop">
		<option value="${Payroll_id}">
			School Name: <c:out value='${Payroll_List.payroll_School_Name[loop.index]}'/> - Payroll: <c:out value='${Payroll_List.payroll_Name[loop.index]}'/>
		</option>	   						 			
	</c:forEach>
	</select>
