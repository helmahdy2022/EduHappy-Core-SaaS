<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select name="Group_id${param.Course_id}" id="Group_id${param.Course_id}">
		<option value="">
			Select Group
		</option>	 	
	<c:forEach items="${Groups.group_Name}" var="Group" varStatus="loop">

		<option value="${Groups.group_id[loop.index]}">
			<c:out value="${Group}"></c:out>
		</option>	   						 			
	</c:forEach>
	</select>