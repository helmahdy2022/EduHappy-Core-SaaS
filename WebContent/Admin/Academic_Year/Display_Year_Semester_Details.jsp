<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Year Semester Details</h5>
</div>
<div class="widget-content nopadding">
<div class="control-group">
	<label class="control-label">Semester Name *</label>
	<div class="controls"> 		 
	    <input type="text" name="Semester_Name" class="span11" required id="Semester_Name" value="<c:out value='${Year_Semester_Details.semester_Name}'/>" maxlength="50">
    	<input type="hidden" name="Semester_id" id="Semester_id" value="${Year_Semester_Details.semester_id}">
    </div>
</div>	
<c:choose>
<c:when test="${not empty Year_Terms.year_Term_Name}">
	<div class="control-group">
		<label class="control-label">Select Year Term(s)</label>
		<div class="controls">
			<c:forEach items="${Year_Terms.year_Term_Name}" var="Year_Term" varStatus="loop">
			&nbsp;&nbsp;&nbsp;<input name="Year_Term_id${Year_Terms.year_Term_id[loop.index]}" type="checkbox" value="${Year_Terms.year_Term_id[loop.index]}"
			<c:if test="${Year_Terms.term_Semester_id[loop.index]==Year_Semester_Details.semester_id}">
			Checked
			</c:if>
			><c:out value="${Year_Term}"/><br>
			<input type="hidden" name="hiddenYear_Term_id${Year_Terms.year_Term_id[loop.index]}" value="${Year_Terms.year_Term_id[loop.index]}" id="hiddenYear_Term_id${Year_Terms.year_Term_id[loop.index]}">					 			
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
    <input type="button" name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage()" class="btn btn-danger">                  
</div>
<div class="requiredSignNote"><label>Fields marked (*) are required</label></div>
</div>
