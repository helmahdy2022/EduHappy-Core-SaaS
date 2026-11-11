<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	 <table>
	 <tr><td class="selectAll"><input type="checkbox" onClick="selectAll(this,${param.Grade_id},${param.Class_id},${param.Course_id},${param.Group_id})" /> Select All</td></tr>
		<c:forEach items="${Teacher_Students.student_id}" var="Student_id" varStatus="position">
		<tr>
		<td>

			<input type="checkbox" name="Student${param.Grade_id}${param.Class_id}${param.Course_id}${param.Group_id}"  value="${Student_id}" onClick="document.getElementById('messageArea').innerHTML='';">
			<a href="javascript:showMessages(${Student_id},'${Teacher_Students.first_Name[position.index]}','${Teacher_Students.last_Name[position.index]}', 'Student', 'Teacher')">
			<c:out value="${Teacher_Students.first_Name[position.index]} ${Teacher_Students.last_Name[position.index]}"/>
			</a>		
  		</td>
  		</tr>
  		</c:forEach>
  		<tr><td>
  		
  		<div class="buttonHolder"><input type="button" class="btn-success" value="Send to All selected students" onClick="showMessages(getCheckedBoxes('Student${param.Grade_id}${param.Class_id}${param.Course_id}${param.Group_id}'),'Selected','Students', 'Student', 'Teacher', '1')"></div></td></tr>
	 </table>
