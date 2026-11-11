<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Division Details</h5>
</div>
<div class="widget-content nopadding">
<div class="control-group">
	<label class="control-label">Division Name *</label>
	<div class="controls">
    	<input type="text" name="Division_Name" id="Division_Name" class="span11" value="<c:out value='${Division_Details.division_Name}'/>" required maxlength="50">
    	<input type="hidden" name="Division_id" id="Division_id" value="${Division_Details.division_id}">   		 
    </div>
</div>	
<div class="control-group">
	<label class="control-label">Select Course(s)</label>
	<div class="controls">
		<c:forEach items="${Courses.course_Name}" var="Course" varStatus="loop">
		&nbsp;&nbsp;&nbsp;<input name="Course_id${Courses.course_id[loop.index]}" type="checkbox" value="${Courses.course_id[loop.index]}"
		<c:if test="${Courses.course_Division_id[loop.index]==1}">
		Checked
		</c:if>
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
		<c:if test="${Classes.class_Division_id[loop.index]==Division_Details.division_id}">
		Checked
		</c:if>
		>${Class}<br>
		<input type="hidden" name="hiddenClass_id${Classes.class_id[loop.index]}" value="${Classes.class_id[loop.index]}" id="hiddenClass_id${Classes.class_id[loop.index]}">					 			
		</c:forEach>  		 
    </div>
</div>
<div class="form-actions">
    <input type="submit" name="Modify" id="Modify" value="Edit" class="btn btn-info">
    <input type="button" name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage()" class="btn btn-danger">             
</div>
<div class="requiredSignNote"><label>Fields marked (*) are required</label></div>
</div>
