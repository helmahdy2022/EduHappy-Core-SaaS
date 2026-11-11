<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select name="Route_id${param.School_id}" id="Route_id${param.School_id}" class="span11">
		<option value="">
			Select Route
		</option>	 	
	<c:forEach items="${Routes_List.route_id}" var="Route_id" varStatus="loop">

		<option value="${Route_id}">
			<c:out value='${Routes_List.vehicle_Name[loop.index]}'/> -- <c:out value="${Routes_List.route_Name[loop.index]}"></c:out>
		</option>	   						 			
	</c:forEach>
	</select>
