<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Grade Semester Details</h5>
</div>
<div class="widget-content nopadding">
<div class="control-group">
	<label class="control-label">Semester Name *</label>
	<div class="controls"> 		 
    	<input type="text" name="Semester_Name" id="Semester_Name" class="span11" value="<c:out value='${Grade_Semester_Details.semester_Name}'/>" required maxlength="50">
    	<input type="hidden" name="Semester_id" id="Semester_id" value="${Grade_Semester_Details.semester_id}">
    </div>
</div>	
<c:choose>
	<c:when test="${not empty Grade_Semester_Terms.grade_Term_Name}">
	<div class="control-group">
		<label class="control-label">Select Grade Term(s)</label>
		<div class="controls">
			<c:forEach items="${Grade_Semester_Terms.grade_Term_Name}" var="Grade_Term" varStatus="loop">
			&nbsp;&nbsp;&nbsp;<input name="Grade_Term_id${Grade_Semester_Terms.grade_Term_id[loop.index]}" type="checkbox" value="${Grade_Semester_Terms.grade_Term_id[loop.index]}"
			<c:if test="${Grade_Semester_Terms.term_Semester_id[loop.index]==Grade_Semester_Details.semester_id}">
			Checked
			</c:if>
			><c:out value="${Grade_Term}"/><br>
			<input type="hidden" name="hiddenGrade_Term_id${Grade_Semester_Terms.grade_Term_id[loop.index]}" value="${Grade_Semester_Terms.grade_Term_id[loop.index]}" id="hiddenGrade_Term_id${Grade_Semester_Terms.grade_Term_id[loop.index]}">					 			
			</c:forEach>		 
	    </div>
	</div>
	</c:when>
<c:otherwise>
		<c:out value="There are no terms"/>
</c:otherwise>
</c:choose>

<div class="form-actions">
    <input type="submit" name="Modify" id="Modify" value="Edit" class="btn btn-info">
 	<c:if test="${Grade_Semester_Details.hasGradedExams==false}">
    	<input type="button" name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage()" class="btn btn-danger"> 
    </c:if>                 
</div>
<div class="requiredSignNote"><label>Fields marked (*) are required</label></div>
</div>