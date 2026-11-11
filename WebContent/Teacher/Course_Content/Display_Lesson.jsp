<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<c:set var="Lesson_Name" value="${Lesson_Details.lesson_Name}">
</c:set>
<c:set var="Lesson_Start" value="${Lesson_Details.lesson_Start}">
</c:set>
<c:set var="Lesson_End" value="${Lesson_Details.lesson_End}">
</c:set>
<c:choose>
<c:when test="${Lesson_Details.description==null}">
<c:set var="Description" value=" "/>
</c:when>
<c:otherwise>
<c:set var="Description" value="${Lesson_Details.description}"/>
</c:otherwise>
</c:choose>
<c:set var="Video1" value="${Lesson_Details.video1}"/>
<c:set var="Video2" value="${Lesson_Details.video2}"/>
<c:set var="Video3" value="${Lesson_Details.video3}"/>
            <!-- Input Fields -->
            <div class="panel">
             <div class="panel-heading">
              <span class="panel-title">
                <span class="glyphicon glyphicon-pencil"></span>Lessons Details</span>
              <div class="pull-right hidden-xs">
                <a href="#" onclick="listLessons(${param.Unit_id}, ${param.Course_id}, 'Lesson', 'Edit','', '${param.School_id}${param.Grade_id}${param.Course_id}')"><span class="glyphicon glyphicon-left-arrow"></span> << Back to Lessons List</a>
              </div>
            </div>
              <div class="panel-body">
				<form class="form-horizontal" role="form" name="form1" id="form1" method="post" action="${pageContext.request.contextPath}/Manage_Lesson" enctype="multipart/form-data"> 				
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Lesson Name *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">   
						<input name="Lesson_Name" type="text" class="form-control" id="Lesson_Name" value="<c:out value='${Lesson_Name}'/>" required>						                    
                      </div>
                    </div>
                  </div>                                  
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Start Date *</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						<input name="Lesson_Start" type="Date" class="form-control" id="Lesson_Start" value="<c:out value='${Lesson_Start}'/>" required>						                  
                      </div>
                    </div>
                  </div>   
                   <div class="form-group">
                    <label class="col-lg-3 control-label">End Date *</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						<input name="Lesson_End" type="Date" class="form-control" id="Lesson_End" value="<c:out value='${Lesson_End}'/>" required>						                  
                      </div>
                    </div>
                  </div> 
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Detailed Description *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">   
              			<textarea class="Description" id="Description" name="Description" required style="height: 400px;"><c:out value="${Description}"/></textarea>			                    
                      </div>
                    </div>
                  </div>                    
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Lesson Video1</label>                
                    <div class="col-lg-8">
                      <div class="bs-component">   
						<input name="Video1" type="text" class="form-control" id="Video1" 
						value="<c:out value='${Video1}'/>" 
						placeholder="To get the embed code, click Share button on Youtube, Vimeo or whatever video platform then click Embed and copy the code and paste it here."
						onchange="document.getElementById('VideoPreview1').innerHTML=this.value">
						<div id="VideoPreview1"><c:out value='${Video1}' escapeXml="false"/></div>						                    
                      </div>
                    </div>
                  </div>                                  
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Lesson Video2</label>
                    <div class="col-lg-8">
                      <div class="bs-component">   
						<input name="Video2" type="text" class="form-control" id="Video2" 
						value="<c:out value='${Video2}'/>" 
						placeholder="To get the embed code, click Share button on Youtube, Vimeo or whatever video platform then click Embed and copy the code and paste it here."
						onchange="document.getElementById('VideoPreview2').innerHTML=this.value">
						<div id="VideoPreview2"><c:out value='${Video2}' escapeXml="false"/></div>						                    
                      </div>
                    </div>
                  </div>                                  
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Lesson Video3</label>
                    <div class="col-lg-8">
                      <div class="bs-component">   
						<input name="Video3" type="text" class="form-control" id="Video3" 
						value="<c:out value='${Video3}'/>" 
						placeholder="To get the embed code, click Share button on Youtube, Vimeo or whatever video platform then click Embed and copy the code and paste it here."
						onchange="document.getElementById('VideoPreview3').innerHTML=this.value">
						<div id="VideoPreview3"><c:out value='${Video3}' escapeXml="false"/></div>						                    
                      </div>
                    </div>
                  </div>                                  

 				<div class="form-group"> 
   				  <label class="col-lg-3 control-label">Upload Lesson Files</label>
                  <div class="admin-form">
                      <div class="col-lg-8">
						<div class="bs-component"> 
		                   <div class="section">
		                    <label class="field prepend-icon append-button file" onChange="filesSelected();">
		                      <span class="button btn-primary">Choose File</span>
		                      <input type="file" name="Lessons_Files" class="gui-file" id="Lessons_Files" multiple  onChange="document.getElementById('uploader1').value = this.value;"/>
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
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						  <table class="uploadedFilesTable">
						  <tr>
							<td colspan="3"><label>Uploaded Files:</label></td>
						  </tr>
							<c:forEach items="${Lesson_Details.file_id}" var="File_id" varStatus="loop">
							<c:set var="Account_id" value="${param.Account_id}" scope="application"  />
							<c:set var="School_id" value="${param.School_id}" scope="application"  />
							<c:set var="Entity_id" value="${Lesson_Details.lesson_id}" scope="application"  />
							<tr id="Lesson_File${File_id}">
							<td width="10%"><img src="${pageContext.request.contextPath}/images/File_Extensions/${Lesson_Details.fileCategory[loop.index]}.jpg" width="20" height="20"></td>
							<td width="80%"><a href="${pageContext.request.contextPath}/File_Download?File_id=${File_id}&&Entity=Lessons&&EntityIdName=Lesson_id" target="_blank" class="assignmentLink"><c:out value="${Lesson_Details.realFileName[loop.index]}"/></a></td>	
							<td width="10%"><img src="${pageContext.request.contextPath}/images/delete.jpg" width="20" height="20" border="1" onClick="deleteFile(${File_id}, ${param.Lesson_id},'${Lesson_Details.realFileName[loop.index]}', ${param.Account_id}, ${param.School_id})"></td>
							</tr>
							</c:forEach>
						  </table>           
                      </div>
                    </div>
                  </div>                  
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
					<div style="text-align:center" >
						<input name="h_Unit_id" type="hidden"  id="Unit_id" value="${param.Unit_id}">
						<input name="Lesson_id" type="hidden"  id="Lesson_id" value="${param.Lesson_id}">
						<input name="Course_id" type="hidden"  id="Course_id" value="${param.Course_id}">
						<c:choose>
							<c:when test="${param.Lesson_id==null}">
							 	<button type="submit" id="submit_button" name="submit_button" class="btn btn-success mr10" onClick="uploadFile();"><i class="fa fa-save"></i> Save</button>								
							</c:when>
							<c:otherwise>
						 		<button type="submit" id="submit_button" name="submit_button" class="btn btn-info mr10" onClick="uploadFile();"><i class="fa fa-edit"></i> Edit Lesson</button>								
								<button name="Delete" type="button" class="btn btn-danger mr10" id="Delete" value="Delete Lesson" onClick="showDeleteMessage();"><i class="fa fa-remove"></i> Delete Lesson</button>
							</c:otherwise>
						</c:choose>
						<div class="requiredSignNote">Fields marked (*) are required</div>
					</div>
                    </div>
                  </div>  
				<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
               </form>
              </div>
            </div>
