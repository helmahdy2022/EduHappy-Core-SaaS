<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
 <c:choose>
 <c:when test="${not empty Grade_Semester_Terms}">
 	<b>Select Terms</b><br>	
	<c:forEach items="${Grade_Semester_Terms.grade_Term_Name}" var="Grade_Term" varStatus="loop">
		&nbsp;&nbsp;&nbsp;<input name="Grade_Term_id${Grade_Semester_Terms.grade_Term_id[loop.index]}" type="checkbox" value="${Grade_Semester_Terms.grade_Term_id[loop.index]}"><c:out value='${Grade_Term}'/><br>					 			
	</c:forEach>
 </c:when>
 <c:otherwise>
 <c:out value="There are no terms to select"/>
 </c:otherwise>
 </c:choose>	
</body>
</html>