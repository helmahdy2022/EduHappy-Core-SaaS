<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>

          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="glyphicon glyphicon-tasks"></span>Previous Assignments</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Student Assignments</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
					  <table class="table table-bordered mbn">
						<c:forEach items="${Assignments.courseAssignments}" var="Assignment"> 
					  <tr>	  
					  <td rowspan="5">
					  </td>  
					  <td style="background-color:grey;">
						<c:if test="${not empty Assignment[5]}">
						 <div style="color:#fff;"><label>Assignment Name: </label> <c:out value="${Assignment[4]}"/></div>
					  	</c:if>
					  </td>
					   </tr>
					  <tr>	   
					    <td>						
						<c:choose>
						<c:when test="${not empty Assignment[5]}">
							<div class="assignmentGrade">Max possible points:</div><div class="contentLessonField" style="width:5px;"><c:out value="${Assignment[5]}"/></div> 
							<div class="contentLessonStart" style="margin-left:150px;">Start Date:</div> <div class="contentLessonField"><c:out value="${Assignment[6]}"/></div>
							<div class="contentLessonEnd"> Due Date:</div> <div class="contentLessonField"><c:out value="${Assignment[7]}"/></div>
					  	</c:when>
					  	<c:otherwise>
					  		There are no Previous Assignments under this search criteria
					  	</c:otherwise>
					  	</c:choose>
					  </td>
					   </tr>
					  <tr>
					  <td>
						<div>			
						<c:if test="${not empty Assignment[5]}">
						<label>Description:	</label>
						</c:if>							
							<div style="white-space:pre-wrap;"><c:out value="${Assignment[8]}" escapeXml="false"/></div>
						</div>	
									
					  </td>
					   </tr>		   		   
					  <tr>
						<c:set var="fileExists" value="No"/>
						<c:forEach items="${Assignments.assignmentFiles}" var="files">
						<c:if test="${files[0]==Assignment[9]}">
						<c:set var="fileExists" value="Yes"/>
						</c:if>
						</c:forEach>
						<c:if test="${fileExists=='Yes'}">
						  <td>
							<c:set var="Assignment_id1" value="${Assignment[9]}"/>
							<label>Attached Files:</label><br/>			
							<c:forEach items="${Assignments.assignmentFiles}" var="assignmentFiles">
							<c:if test="${assignmentFiles[0]== Assignment[9]}">

							<c:set var="AttemptsAllowed" value="${Assignment[10]}"/>
							<c:set var="AttemptCount" value="0"/>
								<c:choose>
								<c:when test="${assignmentFiles[1]=='OnlineQuiz'}">
						    		<font size="3" color='red'><c:out value="${assignmentFiles[1]}"/></font> 
						    		<c:forEach items="${Assignments.assignmentAttemptsmap}" var="AttemptMap">
									  <c:if test="${AttemptMap.key==Assignment[9]}">
										<c:set var="AttemptCount" value="0"/>												
										<c:forEach items="${AttemptMap.value}" var="Attempt">
										  <c:if test="${Attempt[1]=='In Progress'}">
										    <c:set var="AttemptInProgress" value="Yes"/>										
									 	  </c:if>
										  <c:set var="AttemptCount" value="${ AttemptCount+1}"/>
										</c:forEach>
									  </c:if>
									</c:forEach>
									<c:set var="AttemptInProgress" value="No"/>	
								</c:when>
								<c:otherwise>
										<img src="${pageContext.request.contextPath}/images/File_Extensions/${assignmentFiles[5]}.jpg" width="20" height="20">
										<a href="${pageContext.request.contextPath}/File_Download?File_id=${assignmentFiles[4]}&&Entity=Assignments&&EntityIdName=Assignment_id&&Entity_id=${Assignment[9]}&&Course_id=${Assignment[0]}" target="_blank"><c:out value="${assignmentFiles[3]}"/></a>
													
								</c:otherwise>
								</c:choose>
								<br/>
							</c:if>
							</c:forEach>				
						  </td>			
						</c:if>
					   </tr>
						<tr>		
						<td>
						<div id="Attempt">
					<c:forEach items="${Assignments.assignmentAttemptsmap}" var="AttemptMap">
					<c:if test="${AttemptMap.key==Assignment[9]}">
					
					<c:if test="${AttemptMap.key==sessionScope['Assignment_id']}">
					<font size="2" color="red"><b><i>${sessionScope['message']}</i></b></font>
					<c:remove var="message" scope="session"/>
					</c:if>
					
				    <div class="col-md-12">
				     <div class="panel panel-visible" id="spy2">
				       <div class="panel-heading">
				         <div class="panel-title hidden-xs">
				           <span class="glyphicon glyphicon-tasks"></span>Online Quiz Attempts</div>
				         </div>
				         <div class="panel-body pn">
				            <table class="table table-striped table-hover" id="datatable2">
				              <thead>
				                <tr>
				                  <th style="text-align:center;">Attempt</th>
				                  <th style="text-align:center;">Status</th>
				                  <th style="text-align:center;">Score</th>                                                                     
				                </tr>
				              </thead>
				              <tbody>
								<c:forEach items="${AttemptMap.value}" var="Attempt" varStatus="loopStatus">
									<tr>			
									   <td style="text-align:center;">  
										${loopStatus.index+1}
									   </td>
									   <td style="text-align:center;">  
										<c:out value="${Attempt[1]}"/>
									   </td>
									   <td style="text-align:center;">  
										 <c:if test="${not empty Attempt[2]}">
											<c:out value="${Attempt[2]}"/> %
										 </c:if>
									   </td>					   					   					   
								    </tr>
								</c:forEach>
				              </tbody>
				            </table>
				          </div>
				     </div>
				    </div>		
					</c:if>
					</c:forEach>
						</div>
						</td>
						</tr>
			
					</c:forEach>
			 		</table>		
                </div>
              </div>
            </div>
          </div>
