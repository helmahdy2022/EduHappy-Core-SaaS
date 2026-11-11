<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<select class="form-control"

	<c:choose>
	<c:when test="${param.Class_id!='null'}">
	name="Lesson_id${param.Class_id}${param.Course_id}${param.Group_id}" id="Lesson_id${param.Class_id}${param.Course_id}${param.Group_id}"	
	</c:when>
	<c:otherwise>
	name="Lesson_idGrade${param.Grade_id}Course${param.Course_id}" id="Lesson_idGrade${param.Grade_id}Course${param.Course_id}"	
	</c:otherwise>
	</c:choose>	 
	
	onChange="this.style.backgroundColor='#ffffff'; document.getElementById('AssignmentDetails').innerHTML=''; document.getElementById('Assignment').innerHTML=''">
		<option value="">
			Select Lesson
		</option>	 	
	<c:forEach items="${Lessons_List.lesson_Name}" var="Lesson" varStatus="loop">

		<option value="${Lessons_List.lesson_id[loop.index]}">
			<c:out value="${Lesson}"></c:out>
		</option>	   						 			
	</c:forEach>
	</select>
</body>
</html>

