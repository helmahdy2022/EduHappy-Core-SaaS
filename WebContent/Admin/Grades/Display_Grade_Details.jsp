<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Grade Details</h5>
</div>
<div class="widget-content nopadding">
<div class="control-group">
	<label class="control-label">Grade Name *</label>
	<div class="controls">
		<input type="text" name="Grade_Name" id="Grade_Name" class="span11" value="<c:out value='${Grade_Details.grade_Name}'/>" required maxlength="50">
    </div>
</div>	
<div class="control-group">
	<label class="control-label">Passing Percentage *</label>
	<div class="controls">
   		<input name="Passing_Grade" type="number" class="span11" id="Passing_Grade" min="0" max="100" value="<c:out value='${Grade_Details.passing_Grade}'/>" required/>%
				                
	</div>
</div>
<div class="control-group">
	<label class="control-label">Max Possible Points *</label>
	<div class="controls">	                
    	<input name="Max_Grade" type="number" class="span11" id="Max_Grade" min="0" max="1000000000" required value="<c:out value='${Grade_Details.max_Grade}'/>">
	</div>
</div>
<div class="control-group">
	<label class="control-label">Grade Index *</label>
	<div class="controls">
	    <input type="number" name="Grade_Index" id="Grade_Index" class="span11" value="<c:out value='${Grade_Details.grade_Index}'/>" min="-1000000000" max="1000000000" required>
    	<input type="hidden" name="Grade_id" id="Grade_id" value="${param.Grade_id}">
	</div>
</div>
<div class="control-group">
  	<label class="control-label">Grading Report Options</label>
  	<div class="controls">
		<input type="radio" name="DisplayHideReport" id="DisplayReport" style="width:18px; height:18px; margin-top:0" value="1" <c:if test='${Grade_Details.displayHideReport == 1}'>checked</c:if>>	Display Grading Report to students by default	<br/>               
		<input type="radio" name="DisplayHideReport" id="HideReport" style="width:18px; height:18px; margin-top:0" value="0" <c:if test='${Grade_Details.displayHideReport == 0}'>checked</c:if>>	Hide Grading Report from students by default
  	</div>
</div>
<div class="form-actions">
    <input type="submit" name="Modify" id="Modify" value="Edit" class="btn btn-info">
    <input type="button" name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage()" class="btn btn-danger">              
</div>
<div class="requiredSignNote"><label>Fields marked (*) are required</label></div>
</div>
