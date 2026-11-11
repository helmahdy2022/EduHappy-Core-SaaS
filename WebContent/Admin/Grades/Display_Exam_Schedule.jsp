<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:choose>
	<c:when test="${not empty Courses.course_id}">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Exam Schedule</h5>
		        </div>			
					<div class="control-group">
					  <label class="control-label">Exam Name *</label>
		              <div class="controls"> 						
						<c:set var="Display" value="1"/>
						<c:forEach items="${ExamScheduleDetails}" var="ExamScheduleDetails" begin="0" end="0">
								<c:set var="Exam_Name" value="${ExamScheduleDetails[1]}"/>	
								<c:set var="Display" value="${ExamScheduleDetails[6]}"/>						
						</c:forEach>
						 <input type="text" name="Exam_Name" id="Exam_Name" class="span11" required maxlength="50" value="<c:out value='${Exam_Name}'/>">
						<input type="hidden" name="Grade_id" value="${param.Grade_id}">
 					  </div>
 					</div>		        				
					<table class="table table-bordered data-table">
					<thead>
					<tr>
					<th>Course</th>
					<th>Date*</th>
					<th>Time From*</th>
					<th>Time To*</th>
					<th>Required</th>
					</tr>	
					</thead>
					<c:forEach items="${Courses.course_id}" var="Course_id" varStatus="position">
						<c:set var="Exam_Date" value=""/>
						<c:set var="Time_From" value=""/>
						<c:set var="Time_To" value=""/>	
						<c:forEach items="${ExamScheduleDetails}" var="ExamScheduleDetails">
							<c:if test="${ExamScheduleDetails[2]==Course_id}">
								<c:set var="Exam_Date" value="${ExamScheduleDetails[3]}"/>
								<c:set var="Time_From" value="${ExamScheduleDetails[4]}"/>
								<c:set var="Time_To" value="${ExamScheduleDetails[5]}"/>				
							</c:if>
						</c:forEach>
						<tr>
						<td><c:out value='${Courses.course_Name[position.index]}'/></td>
						<td><input name="Exam_Date${Course_id}" type="Date" class="span11" id="Exam_Date${Course_id}" required value="<c:out value='${Exam_Date}'/>"></td>
						<td><input type="time" name="Time_From${Course_id}" class="span11" id="Time_From${Course_id}" required value="<c:out value='${Time_From}'/>"></td>
						<td><input type="time" name="Time_To${Course_id}" class="span11" id="Time_To${Course_id}" required value="<c:out value='${Time_To}'/>"></td>
						<td><input type="checkbox" name="Course_Required${Course_id}" id="Course_Required${Course_id}" checked onChange="updateRequired(${Course_id});">required</td>		
						</tr>
						<input type="hidden" name="Course${Course_id}" value="${Course_id}">
						<input type="hidden" id="Original_Exam_Date${Course_id}" value="${Exam_Date}">
						<input type="hidden" id="Original_Time_From${Course_id}" value="${Time_From}">
						<input type="hidden" id="Original_Time_To${Course_id}" value="${Time_To}">
					</c:forEach>
					</table>
					<div class="control-group">
					  <label class="control-label">
						<input type="checkbox" name="DisplayHideExamSchedule" id="DisplayHideExamSchedule" 
						<c:if test="${Display==1}">
						 checked
						</c:if>
						>			  
					 </label>
		              <div class="controls"> 						
						Display Exam Schedule - ${ExamScheduleDetails[6]}		
 					  </div>
 					</div>
 					<div class="form-actions">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 	<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>	
	</c:when>
	<c:otherwise>
  		<div class="message">There are no courses found under this Grade</div>
	</c:otherwise>
	</c:choose>

