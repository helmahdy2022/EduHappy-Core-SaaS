<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="control-group">
	<label class="control-label">Select Course(s)</label>
	<div class="controls">
		<c:forEach items="${Courses.course_Name}" var="Course" varStatus="loop">
		&nbsp;&nbsp;&nbsp;<input name="Course_id${Courses.course_id[loop.index]}" type="checkbox" value="${Courses.course_id[loop.index]}"
		/><c:out value='${Course}'/><br>
		<input type="hidden" name="hiddenCourse_id${Courses.course_id[loop.index]}" value="${Courses.course_id[loop.index]}" id="hiddenCourse_id${Courses.course_id[loop.index]}">					 			
		</c:forEach>  		 
    </div>
</div>
<div class="control-group">
	<label class="control-label">Select Class(es)</label>
	<div class="controls">
		<c:forEach items="${Classes.class_Name}" var="Class" varStatus="loop">
		&nbsp;&nbsp;&nbsp;<input name="Class_id${Classes.class_id[loop.index]}" type="checkbox" value="${Classes.class_id[loop.index]}"
		>${Class}<br>
		<input type="hidden" name="hiddenClass_id${Classes.class_id[loop.index]}" value="${Classes.class_id[loop.index]}" id="hiddenClass_id${Classes.class_id[loop.index]}">					 			
		</c:forEach>  		 
    </div>
</div>

