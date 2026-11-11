<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select name="Vehicle_id${param.School_id}" id="Vehicle_id${param.School_id}" class="span11">
		<option value="">
			Select Vehicle
		</option>	 	
	<c:forEach items="${Vehicles_List.vehicle_Name}" var="Vehicle" varStatus="loop">

		<option value="${Vehicles_List.vehicle_id[loop.index]}">
			<c:out value="${Vehicle}"></c:out>
		</option>	   						 			
	</c:forEach>
	</select>
