<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select name="Department_id" id="Department_id" class="span11">
		<option value="">
			Select Department
		</option>	 	
	<c:forEach items="${Department_List.department_id}" var="Department_id" varStatus="loop">
		<option value="${Department_id}">
			<c:out value='${Department_List.department_Name[loop.index]}'/>
		</option>	   						 			
	</c:forEach>
	</select>
