<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select name="Employee_id" id="Employee_id" class="span11">
		<option value="">
			Select Employee
		</option>	 	
	<c:forEach items="${Employees.employee_id}" var="Employee_id" varStatus="position">
		<option value="${Employee_id}">
			<c:out value="${Employees.first_Name[position.index]}"/> <c:out value='${Employees.last_Name[position.index]}'/>
		</option>	   						 			
	</c:forEach>
	</select>
