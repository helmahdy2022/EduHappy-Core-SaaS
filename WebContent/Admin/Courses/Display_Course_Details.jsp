<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Course Details</h5>
		        </div>
		        <div class="widget-content nopadding">
		            <div class="control-group">
		              <label class="control-label">Course Name *</label>
		              <div class="controls">
    					<input type="text" name="Course_Name" id="Course_Name" value="<c:out value='${Course_Details.course_Name}'/>" class="span11" required maxlength="50">		                
    					<input type="hidden" name="Course_id" id="Course_id" value="${param.Course_id}">
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Course Points *</label>
		              <div class="controls">
    					<input type="number" name="Course_Grade" id="Course_Grade" value="<c:out value='${Course_Details.course_Grade}'/>" class="span11" min="0"  max="1000000000" required>	                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Attendance Weight *</label>
		              <div class="controls">           						                
    					<input type="number" name="Attendance_Weight" id="Attendance_Weight" value="<c:out value='${Course_Details.attendance_Weight}'/>" class="span11" min="0"  max="1000000000" required>
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Attendance Points *</label>
		              <div class="controls">
		                <input type="number" name="Attendance_Grade" id="Attendance_Grade" value="<c:out value='${Course_Details.attendance_Grade}'/>" class="span11" min="0" max="1000000000"  required onblur="checkAttGradeIntegrity(this.value, Present_Grade.value, Absent_Grade.value, Late_Grade.value, Excused_Grade.value)">
				    	<div id="errorMessagePresent"></div>
				    	<div id="errorMessageAbsent"></div>
				    	<div id="errorMessageLate"></div>
				    	<div id="errorMessageExcused"></div>
		              </div>
		            </div>	
		            <div class="control-group">
		              <label class="control-label">Present Points *</label>
		              <div class="controls">
    					<input type="number" name="Present_Grade" id="Present_Grade" value="<c:out value='${Course_Details.present_Grade}'/>" class="span11" min="0" max="1000000000"  required onblur="checkGradeIntegrity(this.value,Attendance_Grade.value,1)">
  						<div id="errorMessage1"></div>
		              </div>
		            </div>		            	            
		            <div class="control-group">
		              <label class="control-label">Absent Points *</label>
		              <div class="controls">
    					<input type="number" name="Absent_Grade" id="Absent_Grade" value="<c:out value='${Course_Details.absent_Grade}'/>" class="span11" min="0" max="1000000000"  required onblur="checkGradeIntegrity(this.value,Attendance_Grade.value,2)">
    					<div id="errorMessage2"></div>
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Late Points *</label>
		              <div class="controls">
				   	    <input type="number" name="Late_Grade" id="Late_Grade" value="<c:out value='${Course_Details.late_Grade}'/>" class="span11" min="0" max="1000000000"  required onblur="checkGradeIntegrity(this.value,Attendance_Grade.value,3)">
   						<div id="errorMessage3"></div>
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Excused Points *</label>
		              <div class="controls">					
     					<input type="number" name="Excused_Grade" id="Excused_Grade" value="<c:out value='${Course_Details.excused_Grade}'/>" class="span11" min="0" max="1000000000"  required onblur="checkGradeIntegrity(this.value,Attendance_Grade.value,4)">
   						<div id="errorMessage4"></div>
		              </div>
		            </div>
					<c:if test="${Parent_Account_Details.account_id=='258'}">  
		            <div class="control-group">
		              <label class="control-label">Price</label>
		              <div class="controls">
 						<input name="Price" type="number" id="Price" value="<c:out value='${Course_Details.price}'/>" class="span11" min="0" max="1000000000">
		              </div>
		            </div>	
		            </c:if>	            	
		            <div class="control-group">
		              <label class="control-label">Course is Elective</label>
		              <div class="controls">
		                <label>
		                  <input type="radio" name="Elective" value="1" <c:if test="${Course_Details.elective==1}"> checked </c:if> >
		                  Yes</label>
		                <label>
		                  <input type="radio" name="Elective" value="0" <c:if test="${Course_Details.elective==0}"> checked </c:if> >
		                  No</label>
		              </div>
		            </div>	
		            <div class="control-group">
		              <label class="control-label">Contribute to final grade</label>
		              <div class="controls">
					   <input name="Contribute" type="checkbox" id="Contribute" onChange="changeDisplayReport();"
					   		<c:if test="${Course_Details.contribute==1}">
							 	Checked
						    </c:if>
						    >
		              </div>
		            </div>	
		            <div class="control-group">
		              <label class="control-label">Display in the Grading Report</label>
		              <div class="controls">
						  <input name="Report_Display" type="checkbox" id="Report_Display" 
						   		<c:if test="${Course_Details.report_Display==1}">
								 	Checked 
							    </c:if>
							    <c:if test="${Course_Details.contribute==1}">
								 	disabled 
							    </c:if>
						  >						  
		              </div>
		            </div>
					<div class="form-actions">
					    <input type="submit" name="Modify" id="Modify" value="Edit" class="btn btn-info">
					    <input type="button" name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage()" class="btn btn-danger">              
					</div>
     				<div class="requiredSignNote">Fields marked (*) are required</div>
     			</div>
