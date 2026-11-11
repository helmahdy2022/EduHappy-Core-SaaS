<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="catTable">
<c:forEach items="${Student_Grading.courseCatmap}" var="CategoryList" >
<c:set var="Course_id" value="${CategoryList.key}"/>
	<c:forEach items="${CategoryList.value}" var="Category" varStatus="loop">
		<c:if test="${Category[0]!=null && Course_id==param.Course_id }">
			
			<tr>
			<td class="catName">  
			<a href="javascript:showCatGrades(${Category[0]}, ${param.Student_id}, '${param.Source}', '${Student_Grading.school_year_id}');" class="catLink" onClick="location.href='#CategoryDetaildGrades'"><c:out value="${Category[1]}"/></a>
			</td>
			<td class="catPercent"> 
			<c:choose>
			<c:when test="${Category[2]!=null}">
			<c:out value="${Category[2]}"/>%
			</c:when>
			<c:otherwise>
			-
			</c:otherwise>
			</c:choose>
			</td>
			</tr>
		</c:if>
	</c:forEach>
</c:forEach>
			<tr>
			<td class="catName">  
			<a href="javascript:showAttendanceGrades(${param.Course_id}, ${param.Student_id}, '${param.Source}', '${Student_Grading.school_year_id}');" class="catLink" onClick="location.href='#CategoryDetaildGrades'">Attendance</a>
			</td>
			<td class="catPercent">  
			<c:forEach items="${Student_Grading.term_Grading_Map}" var="Term_Grading_Map" >
			<c:forEach items="${Term_Grading_Map.value}" var="Term_Grading" >
			<c:if test="${Term_Grading_Map.key == param.Course_id && Term_Grading[15]!=null }">			
			<c:out value="${Term_Grading[15]}"/>
			</c:if>
			</c:forEach>
			</c:forEach>
			</td>
			</tr>
</table>