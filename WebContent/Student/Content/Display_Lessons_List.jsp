<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<select name="Lesson_id" id="Lesson_id" class="form-control" onchange="document.getElementById('AssignmentsList').innerHTML='';">
		<option value="">
			Select Lesson
		</option>	 	
	<c:forEach items="${Course_Structure.course_Structure_List}" var="Lesson" varStatus="loop">

		<option value="${Lesson[4]}">
			<c:out value="${Lesson[3]} - ${Lesson[5]}"></c:out>
		</option>	   						 			
	</c:forEach>
	</select>