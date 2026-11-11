<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.io.*" %> 
<%@page import="java.util.*" %> 
<%
String Course_id=request.getParameter("Course_id");
String Group_id=request.getParameter("Group_id");
String Grade_id=request.getParameter("Grade_id");
String Class_id=request.getParameter("Class_id");
String Lesson_id=request.getParameter("Lesson_id");
String Assignment_id=request.getParameter("Assignment_id");

request.setAttribute("Grade_id", Grade_id);
request.setAttribute("Class_id", Class_id);
request.setAttribute("Course_id", Course_id);
request.setAttribute("Group_id", Group_id);
request.setAttribute("Lesson_id", Lesson_id);
request.setAttribute("Assignment_id", Assignment_id);
String Page=request.getParameter("Page");
request.setAttribute("Page", Page);
%>
<c:set var="Subject" value="${Assignment_Details.subject}">
</c:set>
<c:set var="Text" value="${Assignment_Details.text}">
</c:set>
<c:set var="Max_Grade" value="${Assignment_Details.max_Grade}">
</c:set>
<c:set var="SchoolName" value="${Assignment_Details.schoolName}">
</c:set>
<c:set var="GradeName" value="${Assignment_Details.gradeName}">
</c:set>
<c:set var="ClassName" value="${Assignment_Details.className}">
</c:set>
<c:set var="CourseName" value="${Assignment_Details.courseName}">
</c:set>
<c:set var="GroupName" value="${Assignment_Details.groupName}">
</c:set>
<c:set var="Display_Date" value="${Assignment_Dates.display_Date}">
</c:set>
<c:set var="Start_Date" value="${Assignment_Dates.start_Date}">
</c:set>
<c:set var="Due_Date" value="${Assignment_Dates.due_Date}">
</c:set>
<c:set var="Disappear_Date" value="${Assignment_Dates.disappear_Date}">
</c:set>
<c:set var="Online_Submsission_Option" value="${Assignment_Dates.online_Submission_Option}">
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
		<form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Give_Assignment">
            <!-- Input Fields -->
            <div class="panel">
              <div class="panel-heading">
                <span class="fa fa-book"></span>Assignment Dates
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
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Display Date</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						<input name="Display_Date" type="Date" class="form-control" id="Display_Date" value="<c:out value='${Display_Date}'/>">						                  
                      </div>
                    </div>
                  </div>   
                   <div class="form-group">
                    <label class="col-lg-3 control-label">Start Date</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						<input name="Start_Date" type="Date" class="form-control" id="Start_Date" value="<c:out value='${Start_Date}'/>">					                  
                      </div>
                    </div>
                  </div> 
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Due Date *</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						<input name="Due_Date" type="Date" class="form-control" id="Due_Date" value="<c:out value='${Due_Date}'/>" required>						                  
                      </div>
                    </div>
                  </div>   
                   <div class="form-group">
                    <label class="col-lg-3 control-label">Disappear Date</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						<input name="Disappear_Date" type="Date" class="form-control" id="Disappear_Date" value="<c:out value='${Disappear_Date}'/>">					                  
                      </div>
                    </div>
                  </div>
                   <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 					                    		     
					    <div class="checkbox-custom mb5">
						     <input name="Online_Submission_Option" type="checkbox" id="Online_Submission_Option" <c:if test='${Online_Submsission_Option==1}'>checked</c:if>>
							<label for="Online_Submission_Option">Allow submission online (Allow students to respond to this assignment online)</label>					    
						 </div>					   		
                      </div>
                    </div>
                  </div>    
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
					  <div style="text-align:center" >
						<input name="Grade_id" type="hidden"  id="Grade_id" value="${Grade_id}">
						<input name="Class_id" type="hidden"  id="Class_id" value="${Class_id}">
						<input name="Course_id" type="hidden"  id="Course_id" value="${Course_id}">
						<input name="Group_id" type="hidden"  id="Group_id" value="${Group_id}">
						<input name="Lesson_id" type="hidden"  id="Lesson_id" value="${Lesson_id}">
						<input name="Assignment_id" type="hidden"  id="Lesson_id" value="${Assignment_id}">
						<input name="Page" type="hidden"  id="Page" value="<c:out value='${Page}'/>">
						<div class="submitHolder">
						<button name="Submit" type="submit" class="btn btn-success mr10" id="submit" value="Give Assignment">Give Assignment</button>
						<c:if test="${Display_Date!=null || Start_Date!=null || Due_Date!=null || Disappear_Date!=null}">
						<button name="Submit" type="submit" class="btn btn-danger mr10" id="submit" value="Delete Dates">Delete Dates</button>
						</c:if>
						</div>
					  </div>
                    </div>
                  </div>  
              </div>
            </div>
              <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
              <input type="hidden" name="page" value="${param.Page}">
		</form>
