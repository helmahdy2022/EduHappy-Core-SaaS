<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${sessionScope['Student_Session']!=null && param.Student_id==0}">
		<jsp:forward page="/Get_Exam_Schedule"/>
	</c:when>
	<c:otherwise>
		<jsp:forward page="/Get_Exam_Schedule?Student_id=${param.Student_id}"/>
	</c:otherwise>
</c:choose>
</body>
</html>