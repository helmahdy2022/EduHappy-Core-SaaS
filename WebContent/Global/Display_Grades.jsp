<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<select name="Grades_id${param.School_id}" id="Grade_id${param.School_id}" class="span11">
		<option value="">
			Select Grade
		</option>	 	
	<c:forEach items="${Grades.grade_Name}" var="Grade" varStatus="loop">

		<option value="${Grades.grade_id[loop.index]}">
			<c:out value="${Grade}"></c:out>
		</option>	   						 			
	</c:forEach>
	</select>
</body>
</html>