<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select name="Leave_id" id="Leave_id" class="span11">
		<option value="">
			Select Leave Type
		</option>	 	
	<c:forEach items="${Leave_List.leave_id}" var="Leave_id" varStatus="loop">
		<option value="${Leave_id}">
			<c:out value='${Leave_List.leave_Name[loop.index]}'/>
		</option>	   						 			
	</c:forEach>
	</select>
