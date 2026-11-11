<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<select name="Grade_Scale_id" id="Grade_Scale_id">
		<option value="">
			Select Grading Scale
		</option>	 	
	<c:forEach items="${Grading_Scales.grading_Scale_id}" var="Grading_Scale_id" varStatus="loop">

		<option value="${Grading_Scale_id}">
			<c:out value="${Grading_Scales.letter[loop.index]}"></c:out>
		</option>	   						 			
	</c:forEach>
	</select>
</body>
</html>