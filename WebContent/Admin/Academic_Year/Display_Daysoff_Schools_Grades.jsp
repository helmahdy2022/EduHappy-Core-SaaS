<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
									<input type="checkbox" name="grade${Grade[1]}" id="Grade" value="${Grade[1]}"/>
									<input type="hidden"  name="hiddengrade${Grade[1]}" id="hiddenGrade" value="${Grade[1]}"/>
		   						 	<c:out value='${Grade[0]}'/>
								</div>
								</c:if>
							</c:forEach>
						  </div>
			            </div>
			        </div>									
				</c:forEach>		
