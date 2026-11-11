<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>School Details</h5>
</div>
<div class="widget-content nopadding">
		<div class="control-group">
        <label class="control-label">School Name *</label>
        <div class="controls">
          <input type="text" name="name" id="name" value="<c:out value='${School_Details.name}'/>" class="span11" required maxlength="100">
        </div>
      </div>
      <div class="control-group">
         <label class="control-label">Country *</label>
        <div class="controls">            				            
			<select name="Country_id" id="Country_id" class="span11" required>
			  <option value=""> 
			  	Select Country
			  </option>
	  		<c:forEach items="${Countries.country_id}" var="Country_id" varStatus="position">
          	<option value="${Country_id}"
          	<c:if test="${School_Details.country_id==Country_id}">
       			selected
       		</c:if>	          	
          	>	          	
          	<c:out value='${Countries.country_Name[position.index]}'/></option>	          	
         		</c:forEach>
			</select>           
          </div>
        </div>		           
        <div class="control-group">
          <label class="control-label">City *</label>
          <div class="controls">
          	<input type="text" name="City" id="City" class="span11"  maxlength="100" required value="<c:out value='${School_Details.city}'/>"> 		                
          </div>
        </div>
        <div class="control-group">
          <label class="control-label">ZIP Code / PO Box</label>
          <div class="controls">
			<input name="PO_Box" type="number" class="span11" id="PO_Box"  value="<c:if test="${School_Details.PO_Box!=-1}"><c:out value='${School_Details.PO_Box}'/></c:if>" min="0" max="1000000000">	                
          </div>
        </div>
        <div class="control-group">
          <label class="control-label">Full Address</label>
          <div class="controls">
            <input name="Full_Address" type="text" class="span11" id="Full_Address" value="<c:out value='${School_Details.full_Address}'/>" maxlength="255">
          </div>
        </div>
        <div class="control-group">
          <label class="control-label">Website</label>
          <div class="controls">
            <input name="Website" type="text" class="span11" id="Website"  value="<c:out value='${School_Details.website}'/>" maxlength="255">
          </div>
        </div>
        <div class="control-group">
          <label class="control-label">Email</label>
          <div class="controls">
            <input name="Email" type="email" class="span11" id="Email" value="<c:out value='${School_Details.email}'/>" maxlength="255">
          </div>
        </div>
        <div class="control-group">
          <label class="control-label">Weekend (school closed)</label>
          <div class="controls">
		    <label><b><i>Select only the days when the school is CLOSED:</i></b></label>                  
	      	<c:forEach items="${Days.day_id}" var="Day_id" varStatus="position">
	      		<label>
			         <c:forEach items="${School_Weekend.school_Weekend_Days}" var="School_Weekend">
			         	<c:if test="${School_Weekend==Day_id}">
							<c:set var="DayExist" value="1">
							</c:set>
						</c:if>
	 				</c:forEach>
			        <input type="checkbox"
			          	<c:if test="${DayExist==1}">checked
			          	<c:set var="DayExist" value="0">
						</c:set>
						</c:if>
			          	 name="Weekend${Day_id}" value="${Day_id}" id="Weekend${Day_id}"><c:out value='${Days.day_Name[position.index]}'/>		          	 
			        <input type="hidden" name="hiddenWeekend${Day_id}" value="${Day_id}" id="hiddenWeekend${Day_id}">
			    </label>
			 </c:forEach>
       </div>
     </div>		            
     <div class="form-actions">
              <input type="submit" name="Modify" id="Modify" value="Edit" class="btn btn-info">
              <input type="button" name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage()" class="btn btn-danger">              
     </div>
<div class="requiredSignNote"><label>Fields marked (*) are required</label></div>
</div>