<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${Semester_Exam_Details.exam_id!=0}">
<c:set var="Exam_id" value="${Semester_Exam_Details.exam_id}"/>
<c:set var="Exam_Grade" value="${Semester_Exam_Details.exam_Grade}"/>
<c:set var="Exam_Contribution" value="${Semester_Exam_Details.exam_Contribution}"/>
</c:if>
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Semester Exam Details</h5>
</div>
<div class="widget-content nopadding">
<c:choose>
	<c:when test="${Semester_Exam_Details.hasTerms==false}">
		<font color="red">
		The semester that you have selected has no terms. Please create terms and link them to that semester in order to create exam semester.
		</font>
	</c:when>
	<c:otherwise>
		<div class="control-group">
			<label class="control-label">Exam Grade *</label>
			<div class="controls">
		 		 <input type="number" name="Exam_Grade" value="<c:out value='${Exam_Grade}'/>" class="span11" min="1" max="1000000000" required />
		    </div>
		</div>	
		<div class="control-group">
			<label class="control-label">Exam Weight *</label>
			<div class="controls">
		 		 <input type="number" name="Exam_Contribution" value="<c:out value='${Exam_Contribution}'/>"  class="span11" min="0" max="1000000000" required />
		    </div>
		</div>	
		<div class="form-actions">
			<c:choose>
			<c:when test="${Exam_id!=0 && Exam_id!=null}">
			    <input type="submit" name="Modify" id="Modify" value="Edit" class="btn btn-info" onClick="checkSession();">        
				<c:if test="${Semester_Exam_Details.hasGrades==false}">
						<input type="button" value="Delete" class="btn btn-danger" onClick="showDeleteMessage();">
				</c:if>
		   		 <input type="hidden" name="Exam_id" value="${Exam_id}">
			</c:when>
			<c:otherwise>
				<input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
			</c:otherwise>
			</c:choose>	
		</div>
		<div class="requiredSignNote"><label>Fields marked (*) are required</label></div>
	</c:otherwise>	
</c:choose>
</div>
</body>
</html>