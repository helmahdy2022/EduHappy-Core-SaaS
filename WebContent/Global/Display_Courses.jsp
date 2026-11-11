<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<select name="Course_id${param.Grade_id}" id="Course_id${param.Grade_id}">
		<option value="">
			Select Course
		</option>	 	
	<c:forEach items="${Courses.course_Name}" var="Course" varStatus="loop">

		<option value="${Courses.course_id[loop.index]}">
			<c:out value="${Course}"></c:out>
		</option>	   						 			
	</c:forEach>
	</select>
</body>
</html>