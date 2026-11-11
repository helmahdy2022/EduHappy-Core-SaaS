<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>

<select class="form-control" name="Lesson_id${param.Class_id}${param.Course_id}${param.Group_id}">
<option value="">
Select Lesson
</option>
	<c:forEach items="${Lessons_List.lesson_Name}" var="Lesson" varStatus="loop">
	<option value="${Lessons_List.lesson_id[loop.index]}">
	<c:out value="${Lesson}"/>
	</option>
	</c:forEach>
</select>

</body>
</html>