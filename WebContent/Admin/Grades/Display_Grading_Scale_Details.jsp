<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Grading Scale Details</h5>
		        </div>
		        <div class="widget-content nopadding">
		            <div class="control-group">
		              <label class="control-label">Lowest Grading Percentage *</label>
		              <div class="controls">		                
 						<input type="number" name="low" min="0" max="100" class="span11" required value="<c:out value='${Grading_Scale_Details.low}'/>"/>%
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Highest Grading Percentage *</label>
		              <div class="controls">	                
						<input type="number" name="high" min="0" max="100" class="span11" required value="<c:out value='${Grading_Scale_Details.high}'/>"/>%
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Letter / Level *</label>
		              <div class="controls">           						                
						<input type="text" name="letter" class="span11" required value="<c:out value='${Grading_Scale_Details.letter}'/>" maxlength="50"/>
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Description</label>
		              <div class="controls">
						<input type="text" name="description" class="span11" value="<c:out value='${Grading_Scale_Details.description}'/>" maxlength="1000"/>
						<input type="hidden" name="Grading_Scale_id" value="<c:out value='${Grading_Scale_Details.grading_Scale_id}'/>"/>
		              </div>
		            </div>	
					<div class="form-actions">
					    <input type="submit" name="Modify" id="Modify" value="Edit" class="btn btn-info">
					    <input type="button" name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage()" class="btn btn-danger">              
					</div>
     				<div class="requiredSignNote">Fields marked (*) are required</div>
     			</div>
