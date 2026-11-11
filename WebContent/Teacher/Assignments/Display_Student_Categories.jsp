<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="catTable">

<c:forEach items="${Students.student_id}" var="Student_id"  varStatus="position">
<c:if test="${Student_id == param.Student_id}">
	<c:set var="First_Name" value="${Students.first_Name[position.index]}"/>
	<c:set var="Last_Name" value="${Students.last_Name[position.index]}"/>
</c:if>
</c:forEach>	
	<c:forEach items="${Course_Grading}" var="Student_Grading">
		<c:forEach items="${Student_Grading.courseCatmap}" var="CategoryList">
			<c:if test="${CategoryList.key == param.Student_id}">
				<c:forEach items="${CategoryList.value}" var="Category" varStatus="loop">
					 <c:if test="${Category[0]!=null}">				
						<tr>
						<td>  				
						</td>
						<td class="catName">  
						<a href="javascript:window.open('${pageContext.request.contextPath}/Get_Assignments_Grading?User=${param.User}&
						Category_id=${Category[0]}&Category_Name=${Category[1]}&Student_id=${param.Student_id}&First_Name=${First_Name}&Last_Name=${Last_Name}&
						Grade_id=${param.Grade_id}&Class_id=${param.Class_id}&Course_id=${param.Course_id}&Group_id=${param.Group_id}','Assignments_Grading','toolbar=no, menubar=no, location=no, width=850,height=500, top=300, left=500')" class="catLink">
						<c:out value="${Category[1]}"/>
						</a>
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
			</c:if>
		</c:forEach>
	</c:forEach>

			<tr>
			<td>  
			
			</td>
			<td class="catName">  
<%-- 			<a href="javascript:showAttendanceGrades(${param.Student_id}, ${param.Course_id});" class="catLink">Attendance</a> --%>
				<a href="javascript:window.open('${pageContext.request.contextPath}/Get_Attendance_Grading?User=${param.User}&Category_Name=Attendance&Student_id=${param.Student_id}&First_Name=${First_Name}&Last_Name=${Last_Name}&
						Grade_id=${param.Grade_id}&Class_id=${param.Class_id}&Course_id=${param.Course_id}&Group_id=${param.Group_id}','Attendance_Grading','toolbar=no, menubar=no, location=no, width=850,height=500, top=300, left=500')" class="catLink">
					Attendance
				</a>
			</td>
			<td class="catPercent">  
			<c:forEach items="${Students.student_id}" var="Student_id"  varStatus="position" begin="0" end="0">
				<c:forEach items="${Course_Grading}" var="Student_Grading">
					<c:forEach items="${Student_Grading.term_Grading_Map}" var="Term_Grading_Map" >
						<c:if test="${Term_Grading_Map.key == param.Student_id}">
							<c:forEach items="${Term_Grading_Map.value}" var="Term_Grading">	
								<c:if test="${Term_Grading[15]!=null && Term_Grading_Map.key == param.Student_id}">			
								<c:out value="${Term_Grading[15]}"/>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</c:forEach>
			</c:forEach>
			</td>
			</tr>
</table>