<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${param.Class_id==null}">
<c:set var="w" value="300"/>
<c:set var="cw" value="100"/>
<c:set var="tw" value="200"/>
</c:when>
<c:otherwise>
<c:set var="w" value="500"/>
<c:set var="cw" value="200"/>
<c:set var="tw" value="300"/>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${not empty Authority_Report.course_id}">
        <div class="widget-box">
          <div class="widget-title">
            <ul class="nav nav-tabs">
              <li class="active"><a data-toggle="tab" href="#tab1">Classroom Teachers</a></li>
              <c:if test="${param.Class_id == null}">
              <li><a data-toggle="tab" href="#tab2">Content Teachers</a></li>
              </c:if>
            </ul>
          </div>
          <div class="widget-content tab-content">
            <div id="tab1" class="tab-pane active">
				<c:forEach items="${Authority_Report.course_id}" var="Course_id" varStatus="loop">
			      <div class="widget-box">
			        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			          <h5>${Authority_Report.course_Name[loop.index]} ${Group_Name}</h5>
			        </div>
			        <div class="widget-content nopadding">
			            <div class="control-group">
			              <div class="controls" style="margin-left:10px;">	              										

								<c:choose>
								<c:when test="${not empty Authority_Report.class_Authority_List}">
									<table class="table table-bordered data-table">
									<thead>
										<tr>
										<th>
										Classroom
										</th>
										<th>
										Teachers
										</th>
										</tr>	
									</thead>								
									<tbody>
									<c:forEach items="${Authority_Report.class_Authority_List}" var="Course_Class_Teacher">
										<c:if test="${Course_id==Course_Class_Teacher[0]}">
										<tr>	
										<td style="text-align:center;">	
											${Course_Class_Teacher[2]}
										</td>	
										<td>
											<c:choose>
											<c:when test="${Course_Class_Teacher[3]!=null}">
												<c:choose>
												<c:when test="${not empty Course_Class_Teacher[6] && Course_Class_Teacher[6]!=null}">
													<div style="display:inline-block">
													<a href="${pageContext.request.contextPath}/Get_Teacher_Details?Teacher_id=${Course_Class_Teacher[3]}" target="_blank">
														<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Course_Class_Teacher[5]}/Teachers/Photos/${Course_Class_Teacher[3]}/${Course_Class_Teacher[6]}">		
													</a>
													</div>					
												</c:when>
												<c:otherwise>
													<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
												</c:otherwise>
												</c:choose>								
												<div style="display:inline-block; width:${tw-50}px;">
												<a href="${pageContext.request.contextPath}/Get_Teacher_Authority?Teacher_id=${Course_Class_Teacher[3]}&&Page=Assign_Courses" target="_blank">
												${Course_Class_Teacher[4]}
												</a>
												</div>
											</c:when>
											<c:otherwise>
												${Course_Class_Teacher[4]}
											</c:otherwise>
											</c:choose>	
										 </td>
										</tr>
										</c:if>
									</c:forEach>
									</tbody>
									</table>			
								</c:when>
								<c:otherwise>
									There are no classes found under this grade
								</c:otherwise>
								</c:choose>
						 </div>
			            </div>	 						            					      
			        </div>
			     </div>
			     </c:forEach>
            </div>
            <c:if test="${param.Class_id == null}">
            <div id="tab2" class="tab-pane"> 
				<c:forEach items="${Authority_Report.course_id}" var="Course_id" varStatus="loop">
			      <div class="widget-box">
			        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			          <h5>${Authority_Report.course_Name[loop.index]}</h5>
			        </div>
			        <div class="widget-content nopadding">
			            <div class="control-group">
			              <div class="controls" style="margin-left:10px;">	              										
							<c:if test="${param.Class_id == null}">
								<c:choose>
								<c:when test="${not empty Authority_Report.content_Authority_List}">
									<table class="table table-bordered data-table">
										<tr>
										<th>
										Teachers
										</th>
										</tr>									
									<c:forEach items="${Authority_Report.content_Authority_List}" var="Content_Teacher">
										<c:if test="${Course_id==Content_Teacher[0]}">
										<tr>	
										<td>
											<c:choose>
											<c:when test="${Content_Teacher[1]!=null}">
												<c:choose>
												<c:when test="${not empty Content_Teacher[4] && Content_Teacher[4]!=null}">
													<div style="display:inline-block">
														<a href="${pageContext.request.contextPath}/Get_Teacher_Details?Teacher_id=${Content_Teacher[1]}" target="_blank">
															<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Content_Teacher[3]}/Teachers/Photos/${Content_Teacher[1]}/${Content_Teacher[4]}">	
														</a>						
													</div>
												</c:when>
												<c:otherwise>
													<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
												</c:otherwise>
												</c:choose>		
													<div style="display:inline-block;">
														<a href="${pageContext.request.contextPath}/Get_Teacher_Authority?Teacher_id=${Content_Teacher[1]}&&Page=Assign_Content" target="_blank">													
															${Content_Teacher[2]}
														</a>
													</div>
											</c:when>
											<c:otherwise>
												${Content_Teacher[2]}
											</c:otherwise>
											</c:choose>
										 </td>
										</tr>
										</c:if>
									</c:forEach>
									</table>			
								</c:when>
								<c:otherwise>
									There are no Teachers assigned to upload content to this course
								</c:otherwise>
								</c:choose>
							</c:if>
						 </div>
			            </div>	 						            					      
			        </div>
			     </div>
				</c:forEach>
            </div>
            </c:if>
          </div>
        </div>
</c:when>
<c:otherwise>
	<c:if test="${param.Class_id==null}">
		<div class="messageHeader">No Courses Found</div>
  		<div class="message">There are no courses found under this grade</div>
  	</c:if>
</c:otherwise>
</c:choose>	
