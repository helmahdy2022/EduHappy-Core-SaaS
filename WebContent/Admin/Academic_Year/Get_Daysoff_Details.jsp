<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${Daysoff_Details.daysoff_id}" var="Daysoff" varStatus="position">
		<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		<h5>Days-off Details</h5>
		</div>
		<div class="widget-content nopadding">
		<div class="control-group">
			<label class="control-label">Days-off Name *</label>
			<div class="controls">
				 <input name="Daysoff_Name" type="text" class="span11" id="Daysoff_Name" value="<c:out value='${Daysoff_Details.daysoff_Name[position.index]}'/>" required>
		    </div>
		</div>
		<div class="control-group">
			<label class="control-label">Start Date *</label>
			<div class="controls">
				<input name="Daysoff_Start" type="Date" id="Daysoff_Start" class="span11" value="<c:out value='${Daysoff_Details.s_Date[position.index]}'/>" required>
		    </div>
		</div>			
		<div class="control-group">
			<label class="control-label">End Date *</label>
			<div class="controls">
				<input name="Daysoff_End" type="Date" id="Daysoff_End" class="span11" value="<c:out value='${Daysoff_Details.e_Date[position.index]}'/>" required>
		    </div>
		</div>	
		<div class="control-group">
			<label class="control-label">
				<input type="checkbox" name="All_Schools" id="All_Schools"             
	            <c:if test="${Daysoff_Details.all_Schools[position.index] == 1}">
	            Checked
	            </c:if>
	              onClick="showSchools(this.value)">			
			</label>
			<div class="controls">
				Apply on all schools and grades
		    </div>
		</div>

		</div>
          <div id="Schools" 
          <c:choose>
          <c:when test="${Daysoff_Details.all_Schools[position.index] == 0}">
          	style="display:block;"
          </c:when>
          <c:otherwise>
          	style="display:none;"
          </c:otherwise>
          </c:choose>
          >
					
			    <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i></span>
			        <h5>Select from the following grades to apply the days-off</h5>
			    </div>
				<c:forEach items="${Daysoff_Schools_Grades.grademap}" var="Grade">
			        <div class="widget-content nopadding">
			            <div class="control-group">
						  	<label class="control-label" style="width:200px;text-align:left;">
								<c:forEach items="${Daysoff_Schools_Grades.school_id}" var="School" varStatus="position">
									<c:if test="${Grade.key==School && School!=null && Grade.key!=null}">
						   				<h5>${Daysoff_Schools_Grades.school_Name[position.index]}</h5>
						   				<input type="hidden" name="school${School}" id="School" value="${School}"/>
									</c:if>
								</c:forEach>
							</label>
			              <div class="controls" style="margin-left:200px;margin-top:20px;"> 								              
							<c:forEach items="${Grade.value}" var="Grade">
								<c:if test="${Grade[0]!=null}">
	   						 	<div>											
									<input type="checkbox" name="grade${Grade[1]}" id="Grade" value="${Grade[1]}"
										<c:if test="${Grade[2]==1}">
										Checked
										</c:if>				
									/>
									<input type="hidden"  name="hiddengrade${Grade[1]}" id="hiddenGrade" value="${Grade[1]}"/>
		   						 	<c:out value='${Grade[0]}'/>
								</div>
								</c:if>
							</c:forEach>
						  </div>
			            </div>
			        </div>									
				</c:forEach>	
           
          </div>
 </c:forEach>
 <div class="form-actions">
 <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
 </div>
