<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="Subject" value="${Assignment_Details.subject}"/>
<c:choose>
<c:when test="${Assignment_Details.text==null}">
<c:set var="Text" value=" "/>
</c:when>
<c:otherwise>
<c:set var="Text" value="${Assignment_Details.text}"/>
</c:otherwise>
</c:choose>

<c:set var="Max_Grade" value="${Assignment_Details.max_Grade}"/>
<c:if test="${Assignment_Details.attempts_Allowed!=0}">
<c:set var="Attempts_Allowed" value="${Assignment_Details.attempts_Allowed}"/>
</c:if>
<c:if test="${Assignment_Details.duration!=0}">
<c:set var="Duration" value="${Assignment_Details.duration}"/>
</c:if>
<c:choose>
<c:when test="${param.Category_id=='null'}">
<c:set var="Category_id" value="${Assignment_Details.category_id}"/>
</c:when>
<c:otherwise>
<c:set var="Category_id" value="${param.Category_id}"/>
</c:otherwise>
</c:choose>

 <c:choose>
<c:when test="${param.Class_id ne 0 && not empty param.Class_id}">
	<c:forEach items="${TeacherAuthority.authorityList}" var="AuthorityList">	
		<c:if test="${AuthorityList[2] == param.Grade_id && AuthorityList[4]==param.Class_id && AuthorityList[6]==param.Course_id}">
			<div class="alert alert-dark light alert-dismissable">
			  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
			  <i class="fa fa-arrow-circle-o-right"></i>
			  <strong><c:out value="${AuthorityList[1]} > ${AuthorityList[3]}  > ${AuthorityList[5]} > ${AuthorityList[7]} ${AuthorityList[9]}"/></strong>
			</div>
		</c:if>
	</c:forEach>
</c:when>

<c:otherwise>
	<c:forEach items="${TeacherContent.contentList}" var="ContentList">
		<c:if test="${ContentList[2] == param.Grade_id && ContentList[4]==param.Course_id}">
			<div class="alert alert-dark light alert-dismissable">
			  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
			  <i class="fa fa-arrow-circle-o-right"></i>
			  <strong><c:out value="${ContentList[1]} > ${ContentList[3]}  > ${ContentList[5]}"/></strong>
			</div>
		</c:if>
	</c:forEach>
</c:otherwise>
</c:choose>

		<form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Manage_Assignment" enctype="multipart/form-data" onsubmit="return validateForm()">
            <!-- Input Fields -->
            <div class="panel">
              <div class="panel-heading">
                <span class="glyphicon glyphicon-tasks"></span> Assignment Details
              <div class="pull-right hidden-xs">
				<c:if test="${param.Page=='Edit_Standard'}">
                <a href="#" 
		      		onClick="listAssignment(${param.Grade_id},'', ${param.Course_id},'', 
		       		Lesson_idGrade${param.Grade_id}Course${param.Course_id}.value, 
		       		Category_idGrade${param.Grade_id}Course${param.Course_id}.value, '${param.Page}', '', 'Grade${param.Grade_id}Course${param.Course_id}')">						
                <span class="glyphicon glyphicon-left-arrow"></span> << Back to Assignments List</a>
				</c:if>
				<c:if test="${param.Page=='Edit'}">
  				<c:if test="${param.Group_id==null || param.Group_id=='0'}"><c:set var="Group_id" value=""></c:set></c:if>
                <a href="#" 
		      		onClick="listAssignment(${param.Grade_id},${param.Class_id}, ${param.Course_id},'${Group_id}', 
		       		Lesson_id${param.Class_id}${param.Course_id}${Group_id}.value, 
		       		Category_id${param.Class_id}${param.Course_id}${Group_id}.value, '${param.Page}', '', '${param.School_id}${param.Grade_id}${param.Class_id}${param.Course_id}${Group_id}')">						
                <span class="glyphicon glyphicon-left-arrow"></span> << Back to Assignments List</a>
				</c:if>
              </div>
              </div>
              <div class="panel-body"> 				
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Subject *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">   
						<input type="text" name="Subject" id="Subject" class="form-control" value="<c:out value='${Subject}'/>" required maxlength="255">											                    
                      </div>
                    </div>
                  </div>                                  
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Max Possible Points *</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						<input type="number" name="Max_Grade" id="Max_Grade" class="form-control" required value="<c:out value='${Max_Grade}'/>"  min="1" max="1000000000">												                  
                      </div>
                    </div>
                  </div>   
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Text *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">   												                    
						<textarea id="Text" name="Text" class="Text" style="height: 400px;"><c:out value="${Text}"/></textarea>
                      </div>
                    </div>
                  </div>                                  
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Attempts</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 										                  
 						<input type="number" name="Attempts_Allowed" id="Attempts_Allowed" value="<c:out value='${Attempts_Allowed}'/>" class="form-control" min="0" max="1000000000" placeholder="(Optional) If the Assignment is a Scorm Online Quiz, please specify the number of allowed attempts">
 						 (Leave it empty for unlimted attempts or if the assignment is not online quiz)
                      </div>
                    </div>
                  </div> 
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Duration (minutes)</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 												                  
						<input type="number" name="Duration" id="Duration" value="<c:out value='${Duration}'/>" class="form-control" min="0" max="1000000000" placeholder="(Optional) If the Assignment is a Scorm Online Quiz, please specify the allowed time duration"> 
						(Leave it empty for unlimted duration or if the assignment is not online quiz)
                      </div>
                    </div>
                  </div>                                                
 				  <div class="form-group"> 
   				   <label class="col-lg-3 control-label">Upload Assignment Files</label>
                   <div class="admin-form">
                      <div class="col-lg-8">
						<div class="bs-component"> 
		                   <div class="section">
		                    <label class="field prepend-icon append-button file" onChange="filesSelected();">
		                      <span class="button btn-primary">Choose File</span>
		                      <input type="file" name="Assignments_Files" class="gui-file" id="Assignments_Files" multiple  onChange="document.getElementById('uploader1').value = this.value;"/>
		                      <input type="text" class="gui-input" id="uploader1" placeholder="Please Select A File"><span id="file-selected"></span>
		                      <label class="field-icon">
		                        <i class="fa fa-upload"></i>
		                      </label>
		                    </label>                    
		                  </div>
	                  	</div> 
                      </div>               
                    </div> 
                   </div>
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						 <div class="progress">
		        				<div class="bar" id="bar"></div >
		        				<div class="percent" id="percent">0%</div >
		    			 </div>              
                      </div>
                    </div>
                  </div>
				 <c:if test="${param.Page=='Edit' || param.Page=='Edit_Standard'}">
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
								<c:set var="Entity_id" value="${Assignment_Details.assignment_id}" scope="application"/>	
								<tr id="Assignment_File${File_id}">
								<c:choose>
								<c:when test="${Assignment_Details.fileName[loop.index] == 'OnlineQuiz'}">
								<td width="10%"><img src="${pageContext.request.contextPath}/images/File_Extensions/OnlineQuiz.jpg" width="20" height="20"></td>
								<td>
									<a href="#" onClick="window.open('${pageContext.request.contextPath}/uploadFiles/review.jsp?Assignment_id=${param.Assignment_id}&&Account_id=${param.Account_id}&&School_id=${param.School_id}', '_blank', 'height=1000, width=1200')" class="assignmentLink"><c:out value="${Assignment_Details.realFileName[loop.index]} Online Quiz"/></a>							
								</td>
								</c:when>
								<c:otherwise>
								<td width="10%"><img src="${pageContext.request.contextPath}/images/File_Extensions/${Assignment_Details.fileCategory[loop.index]}.jpg" width="20" height="20"></td>
								<td>
									<a href="${pageContext.request.contextPath}/File_Download?File_id=${File_id}&&Entity=Assignments&&EntityIdName=Assignment_id" target="_blank" class="assignmentLink"><c:out value="${Assignment_Details.realFileName[loop.index]}"/></a>
								</td>			
								</c:otherwise>
								</c:choose>
								<td><img src="${pageContext.request.contextPath}/images/delete.jpg" width="20" height="20" border="1" onClick="deleteFile(${File_id}, ${param.Assignment_id},'${Assignment_Details.fileName[loop.index]}', ${param.Account_id}, ${param.School_id});"></td>
								</tr>
							</c:forEach>
						 </table>        
                      </div>
                    </div>
                  </div> 
                  </c:if>                 
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
					<div style="text-align:center" >
						<input name="Grade_id" type="hidden"  id="Grade_id" value="${param.Grade_id}">
						<input name="Class_id" type="hidden"  id="Class_id" value="${param.Class_id}">
						<input name="Course_id" type="hidden"  id="Course_id" value="${param.Course_id}">
						<input name="Group_id" type="hidden"  id="Group_id" value="${param.Group_id}">
						<input name="Category_id" type="hidden"  id="Category_id" value="${Category_id}">
						<input name="Lesson_id" type="hidden"  id="Lesson_id" value="${param.Lesson_id}">
						<input name="Assignment_id" type="hidden"  id="Assignment_id" value="${param.Assignment_id}">
						<input name="type" type="hidden"  id="type" value="<c:out value='${param.type}'/>">
						<input name="Page" type="hidden"  id="Page" value="<c:out value='${param.Page}'/>">
						<c:if test="${param.Page=='New' || param.Page=='New_Standard'}">
						<button name="submit_button" type="submit" class="btn btn-success mr10" id="submit_button" onClick="uploadFile();checkSession();"><i class="fa fa-save"></i> Save</button>
						</c:if>
						<c:if test="${param.Page=='Edit'||param.Page=='Edit_Standard'}">
						<button name="submit_button" type="submit" class="btn btn-info mr10" id="submit_button" onClick="uploadFile();checkSession();"><i class="fa fa-edit"></i> Edit Assignment</button>
						<button name="Delete" type="button" class="btn btn-danger mr10" id="Delete" value="Delete Assignment" onClick="checkSession(); showDeleteMessage();"><i class="fa fa-remove"></i> Delete Assignment</button>
						</c:if>						
						<div class="requiredSignNote">Fields marked (*) are required</div>
					</div>
                    </div>
                  </div>  
				<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
              </div>
            </div>
            </form>
            
