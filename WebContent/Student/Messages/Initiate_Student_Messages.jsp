<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope['Student_Session']!=null}">
<jsp:forward page="/Get_Student_Messages"/>
</c:if>
</body>
</html>