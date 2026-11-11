<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>New Subject</title>

</head>

<body>
<div class="pagewrap"> 
<div class="header">
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
<div class="content">
	<div class="leftContent"></div>
	<div class="middleContent">
		<div class="messageHeader">Manage Subject</div>
  		<div class="message">${param.message}</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
</div>
<div class="footer">  <%@include file="../includes/AdminFooter.jsp" %></div>
</div>
</body>
</html>