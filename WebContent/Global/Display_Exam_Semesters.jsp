<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>





	<select>
		<option value="">
			Select Semester
		</option>	 	
	<c:forEach items="${Exam_Semesters.semester_Name}" var="Semester" varStatus="loop">

		<option value="${Exam_Semesters.semester_id[loop.index]}">
			<c:out value="${Semester}"></c:out>
		</option>	   						 			
	</c:forEach>
	</select>
     
	
