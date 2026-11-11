<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Class Details</h5>
</div>
<div class="widget-content nopadding">
<div class="control-group">
	<label class="control-label">Classroom Name *</label>
	<div class="controls">
    	 <input type="text" name="Class_Name" id="Class_Name" class="span11" value="<c:out value='${Class_Details.class_Name}'/>" required>
   		 <input type="hidden" name="Class_id" id="Class_id" value="${Class_Details.class_id}">
    </div>
</div>	
<div class="form-actions">
    <input type="submit" name="Modify" id="Modify" value="Edit" class="btn btn-info">
     <c:if test="${Class_Details.deleteClass == 'YES'}">
    	<input type="button" name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage()" class="btn btn-danger"> 
    </c:if>             
</div>
<div class="requiredSignNote"><label>Fields marked (*) are required</label></div>
</div>
