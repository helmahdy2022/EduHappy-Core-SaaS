<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<table class="childTable">
	<tr>
    <td><label for="Subject_Name">Subject Name *</label></td>
    <td>
    <input type="text" name="Subject_Name" id="Subject_Name" value="<c:out value='${Subject_Details.subject_Name}'/>" class="mediumText" required maxlength="50">
    <input type="hidden" name="Subject_id" id="Subject_id" value="${Subject_Details.subject_id}">
    </td>
  </tr>
  <tr>
     <td colspan="2">
		<b>Select Course</b><br>	 	
		<c:forEach items="${Courses.course_Name}" var="Course" varStatus="loop">
		&nbsp;&nbsp;&nbsp;<input name="Course_id${Courses.course_id[loop.index]}" type="checkbox" value="${Courses.course_id[loop.index]}"
		<c:if test="${Courses.course_Subject_id[loop.index]==Subject_Details.subject_id}">
		Checked
		</c:if>
		/><c:out value='${Course}'/><br>
		<input type="hidden" name="hiddenCourse_id${Courses.course_id[loop.index]}" value="${Courses.course_id[loop.index]}" id="hiddenCourse_id${Courses.course_id[loop.index]}">					 			
		</c:forEach>
     </td>
  </tr>
  <tr>

     <td  colspan="2">
     <div class="buttonHolder">
     <input type="submit" name="button" id="button" value="Modify" class="smallButton">
     <input type="button" name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage()" class="smallButton">
      <div class="requiredSignNote">Fields marked (*) are required</div>
      </div>   
     </td>
  </tr>
  
  </table>