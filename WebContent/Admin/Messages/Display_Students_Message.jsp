<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:choose>
	<c:when test="${not empty Students.student_id}">
	 <table>	 
	 <tr><td class="selectAll"><input type="checkbox" onClick="selectAll(this)" /> Select All</td></tr>
		<c:forEach items="${Students.student_id}" var="Student_id" varStatus="position">
		<tr>
		<td>

			<input type="checkbox" name="Student"  value="${Student_id}" onClick="document.getElementById('messageArea').innerHTML='';">
			<a href="javascript:showMessages(${Student_id},'<c:out value='${Students.first_Name[position.index]}'/>','<c:out value='${Students.last_Name[position.index]}'/>', 'Student', 'Admin')">
			<c:out value='${Students.first_Name[position.index]}'/> <c:out value='${Students.last_Name[position.index]}'/>
			</a>		
  		</td>
  		</tr>
  		</c:forEach>
  		<tr><td>
  		
  		<div class="buttonHolder"><input type="button" value="Send to All selected students" onClick="showMessages(getCheckedBoxes('Student'),'Selected','Students', 'Student', 'Admin', '1')"></div></td></tr>
	 </table>	
	</c:when>
	<c:otherwise>
	<br>No Students Found
	</c:otherwise>
	</c:choose>
