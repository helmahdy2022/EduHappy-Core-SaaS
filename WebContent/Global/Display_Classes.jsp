<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<select name="Class_id${param.Grade_id}" id="Class_id${param.Grade_id}">
		<option value="">
			Select Class
		</option>	 	
	<c:forEach items="${Classes.class_Name}" var="Class" varStatus="loop">

		<option value="${Classes.class_id[loop.index]}">
			<c:out value="${Class}"></c:out>
		</option>	   						 			
	</c:forEach>
	</select>
