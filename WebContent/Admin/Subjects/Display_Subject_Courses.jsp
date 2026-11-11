<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<b>Select Course</b><br>	 	
	<c:forEach items="${Courses.course_Name}" var="Course" varStatus="loop">
		&nbsp;&nbsp;&nbsp;<input name="Course_id${Courses.course_id[loop.index]}" type="checkbox" value="${Courses.course_id[loop.index]}"><c:out value='${Course}'/><br>					 			
	</c:forEach>
</body>
</html>