<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="Subject" value="${Assignment_Details.subject}">
</c:set>
<c:set var="Text" value="${Assignment_Details.text}">
</c:set>
<c:set var="Max_Grade" value="${Assignment_Details.max_Grade}">
</c:set>
<c:forEach items="${TeacherAuthority.authorityList}" var="AuthorityList">	
<c:if test="${AuthorityList[2] == param.Grade_id && AuthorityList[4]==param.Class_id && AuthorityList[6]==param.Course_id && (AuthorityList[8]==param.Group_id || AuthorityList[8]==null)}">
	<div class="alert alert-dark light alert-dismissable">
	  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
	  <i class="fa fa-arrow-circle-o-right"></i>
	  <strong><c:out value="${AuthorityList[1]} > ${AuthorityList[3]}  > ${AuthorityList[5]} > ${AuthorityList[7]} ${AuthorityList[9]}"/></strong>
	</div>
</c:if>
</c:forEach>

            <!-- Input Fields -->

		<form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Manage_AllGrading">
            <div class="panel">
              <div class="panel-heading">
                <span class="fa fa-diamond"></span> Grade Assignment
	              <div class="pull-right hidden-xs">
	  				<c:if test="${param.Group_id==null || param.Group_id=='0'}"><c:set var="Group_id" value=""></c:set></c:if>
	                <a href="#" 
			      		onClick="listAssignment(${param.Grade_id},${param.Class_id}, ${param.Course_id},'${Group_id}', 
			       		Lesson_id${param.Class_id}${param.Course_id}${Group_id}.value, 
			       		Category_id${param.Class_id}${param.Course_id}${Group_id}.value, '${param.Page}', '', '${param.School_id}${param.Grade_id}${param.Class_id}${param.Course_id}${Group_id}')">						
	                <span class="glyphicon glyphicon-left-arrow"></span> << Back to Assignments List</a>
	              </div>
              </div>
              <div class="panel-body"> 				
                  <div class="form-group" style="vertical-align:bottom;">
                    <label class="col-lg-3 control-label">Subject</label>
                    <div class="col-lg-8" style="min-height:38px;line-height:38px;">
                      <div class="bs-component">   
						<c:out value="${Subject}"/>				                    
                      </div>
                    </div>
                  </div>                                  
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Text</label>
                    <div class="col-lg-8" style="min-height:38px;line-height:38px;">
                      <div class="bs-component">   
						<div style="white-space:pre-wrap;"><c:out value="${Text}" escapeXml="false"/></div>										                    
                      </div>
                    </div>
                  </div>                                           
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Max Possible Points</label>
                    <div class="col-lg-8" style="min-height:38px;line-height:38px;">
                      <div class="bs-component">   
						<c:out value="${Max_Grade}"/>				                    
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						 <table class="uploadedFilesTable">	
							<tr>
							<td colspan="3"><label>Uploaded Files:</label></td>
							</tr>
							<c:forEach items="${Assignment_Details.file_id}" var="File_id" varStatus="loop">
								<c:set var="Account_id" value="${param.Account_id}" scope="application" />
								<c:set var="School_id" value="${param.School_id}" scope="application" />
								<c:set var="Entity_id" value="${Assignment_Details.assignment_id}" scope="application"  />	
								<tr id="Assignment_File${File_id}">
								<c:choose>
								<c:when test="${Assignment_Details.fileName[loop.index] == 'OnlineQuiz'}">
								<td width="10%"><img src="${pageContext.request.contextPath}/images/File_Extensions/OnlineQuiz.jpg" width="20" height="20"></td>
								<td>
									<a href="#" onClick="window.open('${pageContext.request.contextPath}/uploadFiles/Scorm/review.jsp?Assignment_id=${param.Assignment_id}&&Account_id=${param.Account_id}&&School_id=${param.School_id}', '_blank', 'height=1000, width=1200')" class="assignmentLink"><c:out value="${Assignment_Details.realFileName[loop.index]} Online Quiz"/></a>
								</td>
								</c:when>
								<c:otherwise>
								<td width="10%"><img src="${pageContext.request.contextPath}/images/File_Extensions/${Assignment_Details.fileCategory[loop.index]}.jpg" width="20" height="20"></td>
								<td>
									<a href="${pageContext.request.contextPath}/File_Download?File_id=${File_id}&&Entity=Assignments&&EntityIdName=Assignment_id" target="_blank" class="assignmentLink"><c:out value="${Assignment_Details.realFileName[loop.index]}"/></a>
								</td>
								</c:otherwise>
								</c:choose>
								
					
								<td>
								</td>
								</tr>
							</c:forEach>
						 </table>      
                      </div>
                    </div>
                  </div>  
				 <c:choose>
					<c:when test="${Assignment_Dates.due_Date==null}">				
				  		<div class="message">This Assignment is not given dates. Please give it at least Due Date</div>	
					</c:when>
					<c:otherwise>
						<c:choose>
					    <c:when test="${not empty Students.studentList}">	
			            <div class="panel-body pn">
			              <div class="table-responsive">
			                <div class="bs-component">
			                    <table class="table table-bordered mbn">		
								<thead>
								<tr>
									<th>ID</th>
									<th>Photo</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Student Submission</th>
									<th>Points</th>
									<th>Out of</th>
									<th>Remarks</th>
									<th colspan="2" style="text-align:center;">Action</th>								
								</tr>
								</thead>					   		
							  	<c:forEach items="${Students.studentList}" var="Student" varStatus="position">
							 		<c:if test="${Student[0]!=null}">
									<tr> 			
								   		<td>
								   		<c:out value="${Student[0]}"/>
								   		</td>
									  	<td style="width:50px;">
											<c:choose>
											<c:when test="${not empty Students.fileName[position.index] && Students.fileName[position.index]!=null}">
												<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Students.account_id[position.index]}/Schools/${Students.school_id[position.index]}/Students/Photos/${Student[0]}/${Students.fileName[position.index]}">
											</c:when>
											<c:otherwise>
												<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
											</c:otherwise>
											</c:choose>							  						 
									  	</td>			   		
								   		<td>
								   		<c:out value="${Student[1]}"/>
								   		</td>
								   		<td>
								   		<c:out value="${Student[2]}"/>
								   		</td>
								   		<td>
										<table>
									<c:forEach items="${Student_Submission}" var="Student_Submission_Map">
									<c:if test="${Student_Submission_Map.key==Student[0]}">
										<c:if test="${not empty Student_Submission_Map.value.submissionNotes}">
										<tr>
										<td>
										<img src="${pageContext.request.contextPath}/images/File_Extensions/txt.jpg" width="20" height="20">
										</td>
										<td>
										<a href="javascript:window.open('${pageContext.request.contextPath}/Get_Submission_Notes?Student_id=${Student[0]}&&Give_Assignment_id=${Give_Assignment_id}','Submission_Notes','toolbar=no, menubar=no, location=no, width=850,height=500, top=300, left=500')" class="assignmentLink">Submission Notes</a>
										</td></tr>
										
										</c:if>
										<c:forEach items="${Student_Submission_Map.value.file_id}" var="File_id" varStatus="loop">	
											<tr id="Submission_Files${File_id}">
											<td width="10%"><img src="${pageContext.request.contextPath}/images/File_Extensions/${Student_Submission_Map.value.fileCategory[loop.index]}.jpg" width="20" height="20"></td>
											<td>
												<a href="${pageContext.request.contextPath}/File_Download?File_id=${File_id}
												&&Entity=AssignmentSubmissions&&EntityIdName=Give_Assignment_id&&SubEntity=Students&&SubEntityIdName=Student_id
												&&Account_id=${Account_id}&&School_id=${School_id}&&Entity_id=${Give_Assignment_id}&&SubEntity_id=${Student_Submission_Map.key}" 
												target="_blank" class="assignmentLink"><c:out value="${Student_Submission_Map.value.realFileName[loop.index]}"/></a>
											</td>			
											</tr>
										</c:forEach>
									</c:if>
									</c:forEach>
										</table>
								   		</td>
								   		<td style="width:100px;text-align:center">
								   		<div id="Grade_Box${Student[0]}">			
											<c:choose>														 									
												<c:when test="${Student[3] == null}">
													<input name="Assignment_Grade${Student[0]}" id="Assignment_Grade${Student[0]}" required type="number" min="0" class="form-control">		
												</c:when>
												<c:otherwise>
										      	<c:out value="${Student[3]}"></c:out>
										      	<input name="Assignment_Grade${Student[0]}" type="hidden"  id="Assignment_Grade${Student[0]}">
												</c:otherwise>
										</c:choose>						
								   		</div>
								   		<div id="Grade_Required${Student[0]}" style="color:red"></div>
								   		</td>
								   		<td style="width:80px;text-align:center">
											<c:out value="${Max_Grade}"/>
							   			</td>
								   		<td>
								   		<div id="Remarks_Box${Student[0]}">			
											<c:choose>														 									
												<c:when test="${Student[5] == null}">
													<input name="Remarks${Student[0]}" id="Remarks${Student[0]}" type="text" max="255" class="form-control">		
												</c:when>
												<c:otherwise>
										      	<c:out value="${Student[5]}"></c:out>
										      	<input name="Remarks${Student[0]}" type="hidden"  id="Remarks${Student[0]}" value="${Student[5]}">
												</c:otherwise>
										</c:choose>						
								   		</div>
								   		</td>
								   		<td style="text-align:center;width:130px;">
									   		<div class="buttonHolder">
									   		<input name="Grade${Student[0]}" type="button" class="btn btn-sm btn-success btn-block" style="width:130px;" id="Grade${Student[0]}" 
											<c:choose>						 									
												<c:when test="${Student[3] == null}">
													value="Grade"		 			
												</c:when>
												<c:otherwise>
													value="Edit"					      
												</c:otherwise>
											</c:choose>
								   			onClick="manageGrading(Assignment_Grade${Student[0]}.value,${Student[0]},Assignment_id.value, Grade${Student[0]}.value,'${Max_Grade}', Remarks${Student[0]}.value);
								   				
								   			">	
											</div>
								   		</td>	
								   		<td style="text-align:center;width:130px;">
									   		<div id="DeleteArea${Student[0]}">
									   		<c:if test="${Student[3] != null}">
										   		<div class="buttonHolder">
										   		<input name="Delete${Student[0]}" type="button" class="btn btn-sm btn-danger btn-block" style="width:130px;" id="Delete${Student[0]}" value="Delete"
										   		onClick="manageGrading(Assignment_Grade${Student[0]}.value,${Student[0]},Assignment_id.value, Delete${Student[0]}.value, '${Max_Grade}', '');"
										   		>
										   		</div>
											</c:if>
											</div>  
										</td>		
									</tr>
								
									</c:if>
							</c:forEach>
							<tr>
								<td colspan="8">
									<div class="buttonHolder">
										   	<button name="submit" type="submit" class="btn btn-success mr10" style="width:133px;" id="submit" value="Grade All" onClick="checkSession();">Grade All</button>
										   	<button name="submit" type="submit" class="btn btn-danger mr10" id="submit" value="Delete All Grades" onClick="checkSession();">Delete All Grades</button>
									</div>										
								</td>
							</tr>
							</table>
						   </div>
					      </div>
				         </div>				
							<input name="Assignment_id" type="hidden"  id="Assignment_id" value="${param.Assignment_id}">
							<input name="Grade_id" type="hidden"  id="Grade_id" value="${param.Grade_id}">
							<input name="Class_id" type="hidden"  id="Class_id" value="${param.Class_id}">
							<input name="Course_id" type="hidden"  id="Course_id" value="${param.Course_id}">
							<input name="Group_id" type="hidden"  id="Group_id" value="${param.Group_id}">
							<input name="Lesson_id" type="hidden"  id="Lesson_id" value="${param.Lesson_id}">
							<input name="Category_id" type="hidden"  id="Category_id" value="${param.Category_id}">
							<input name="Max_Grade" type="hidden"  id="Max_Grade" value="<c:out value='${Max_Grade}'/>">
							<input name="Page" type="hidden"  id="Page" value="<c:out value='${param.Page}'/>">
					
					    </c:when>
					    <c:otherwise>
				  			 <div class="message">There are no students available to be graded</div>	
					    </c:otherwise>
					</c:choose>
					</c:otherwise>
				</c:choose>	 
              </div>
            </div>
              <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
              <input type="hidden" name="page" value="${param.Page}">
            </form>