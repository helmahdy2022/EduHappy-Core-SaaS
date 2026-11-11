<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select name="Admin_id" id="Admin_id" class="span11">
		<option value="">
			Select Admin
		</option>	 	
	<c:forEach items="${Admins.admin_id}" var="Admin_id" varStatus="position">
		<option value="${Admin_id}">
			<c:out value="${Admins.first_Name[position.index]}"/> <c:out value='${Admins.last_Name[position.index]}'/>
		</option>	   						 			
	</c:forEach>
	</select>
