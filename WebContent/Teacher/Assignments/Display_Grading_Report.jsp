<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${TeacherAuthority.authorityList}" var="AuthorityList">	
	<c:if test="${param.User!='Admin' && AuthorityList[2] == param.Grade_id && AuthorityList[4] == param.Class_id && AuthorityList[6]== param.Course_id && (AuthorityList[8]== param.Group_id ||AuthorityList[8]==null)}">
		<div class="alert alert-dark light alert-dismissable">
		  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
		  <i class="fa fa-arrow-circle-o-right"></i>
		  <strong><c:out value="${AuthorityList[1]} > ${AuthorityList[3]}  > ${AuthorityList[5]} > ${AuthorityList[7]} ${AuthorityList[9]}"/></strong>
		</div>
		<c:set var="Course_Name" value="${AuthorityList[7]}"/>
	</c:if>
</c:forEach>
<c:if test="${Course_Name==null}">
	<c:set var="Course_Name" value="${param.Course_Name}"/>
</c:if>
<div class="panel">
  <div class="panel-heading">
     <span class="fa fa-bar-chart-o"></span>
   	 <c:forEach items="${Course_Grading}" var="Student_Grading" begin="0" end="0">
   		${Course_Name} Grading Report - Year <font color="Red">${Student_Grading.year_Name}</font>
   	 </c:forEach>		          
  </div>
  <div class="panel-body"> 	
	<div class="panel-body pn">
	  <div class="table-responsive">
	    <div class="bs-component" style="overflow-x:auto;">
		<c:choose>
		<c:when test="${not empty Students.student_id}">
		  <table class="table table-bordered mbn">	
		    	<thead>
				 <tr>
				 <th >Student</th>
				 <c:forEach items="${Course_Grading}" var="Student_Grading" begin="0" end="0">
					<c:forEach items="${Student_Grading.term_Grading_Map}" var="Term_Grading_Map" begin="0" end="0">
						<c:forEach items="${Term_Grading_Map.value}" var="Term">
							 <th colspan="2" ><c:out value="${Term[1]}"/></th>
			
							 <c:if test="${Term[6]!=null}">
							 <th colspan="2"><c:out value="${Term[7]}"/></th>
							 <th colspan="2"><c:out value="${Term[10]}"/></th>
							</c:if>
							 <c:if test="${Term[13]!=null}">
							 <th colspan="2">Final Year</th>
							</c:if>
						</c:forEach>
					</c:forEach>
				</c:forEach>
				 </tr>			
		  		</thead>
			<c:forEach items="${Students.student_id}" var="Student_id"  varStatus="position">
				<c:forEach items="${Course_Grading}" var="Student_Grading">
					<c:forEach items="${Student_Grading.term_Grading_Map}" var="Term_Grading_Map">
						<c:forEach items="${Term_Grading_Map.value}" var="Course"  begin="0" end="0">
							<c:if test="${Student_id==Course[16]}">
								<tr>
								 <td>
								  	<div>						  	
								  	<table class="table table-bordered mbn">						  	
								  	<tr>
								  	<td style="width:30px;">										
										<div class="plusImage"><img id="${Student_id}" name="+" src="${pageContext.request.contextPath}/images/+.jpg" width="30" height="30"  
										onClick="checkSessionForCat(${Student_id}, ${param.Course_id}, ${param.Grade_id}, ${param.Class_id}, ${param.Group_id});">
										</div>							  	
								  	</td>
		 						  	<td style="width:10px;">
								  		<c:out value="${Student_id}"/>
								  	</td>
								  	<td style="width:50px;">
										<c:choose>
										<c:when test="${not empty Students.fileName[position.index] && Students.fileName[position.index]!=null}">
											<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Students.account_id[position.index]}/Schools/${Students.school_id[position.index]}/Students/Photos/${Student_id}/${Students.fileName[position.index]}">
										</c:when>
										<c:otherwise>
											<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
										</c:otherwise>
										</c:choose>							  						 
								  	</td>
								  	<td>
									  	<c:choose>							  	
									  	<c:when test="${param.User=='Admin'}">
										<a href="javascript:window.open('${pageContext.request.contextPath}/Get_Report_Card?Student_id=${Student_id}
										&&User=Admin&&First_Name=${Students.first_Name[position.index]}&&Last_Name=${Students.last_Name[position.index]}
										&&Account_id=${Students.account_id[position.index]}&&School_id=${Students.school_id[position.index]}&&FileName=${Students.fileName[position.index]}',
										'Full_Grading_Report','toolbar=no, menubar=no, location=no, width=1200,height=500, top=300, left=500')" class="catLink">								 							
											<c:out value="${Students.first_Name[position.index]} ${Students.last_Name[position.index]}"/>
										</a>
									  	</c:when>
									  	<c:otherwise>
									  		<c:out value="${Students.first_Name[position.index]} ${Students.last_Name[position.index]}"/>
									  	</c:otherwise>					  	
									  	</c:choose>							  	
								  	
								  	</td>
								  	</tr>
								  	</table>
								  	</div>
		 						  <table>
								  <tr>
								  <td colspan="3" style="text-align:right;">
								 <div id="Categories${Student_id}" style="text-align:right;">				 
								 </div>
								 </td>
								 </tr>
								  </table>
								 </td>
								 <c:forEach items="${Student_Grading.term_Grading_Map}" var="Term_Grading_Map">
									<c:forEach items="${Term_Grading_Map.value}" var="Term_Grading" >
										<c:if test="${Term_Grading[2] == Course[2]}">
										<td>						
										<c:choose>
										<c:when test="${Term_Grading[4]!=null}">
										<c:out value="${Term_Grading[4]}"/>% 
										</c:when>
										<c:otherwise>
										-
										</c:otherwise>
										</c:choose>
										</td>
										<td>
										<c:choose>
										<c:when test="${Term_Grading[5]!=null}">
										<c:out value="${Term_Grading[5]}"/>
										</c:when>
										<c:otherwise>
										-
										</c:otherwise>
										</c:choose>
										</td>
										<c:if test="${Term_Grading[6]!=null}">
											<td>
											<c:out value="${Term_Grading[8]}"/>
											</td>
											<td>
											<c:choose>
												<c:when test="${Term_Grading[9]!=null}">
													<c:out value="${Term_Grading[9]}"/>
												</c:when>
												<c:otherwise>
												-
												</c:otherwise>
											</c:choose>
											</td>								
											<td>
											<c:out value="${Term_Grading[11]}"/>
											</td>
											<td>
											<c:choose>
												<c:when test="${Term_Grading[12]!=null}">
													<c:out value="${Term_Grading[12]}"/>
												</c:when>
												<c:otherwise>
													-
												</c:otherwise>
											</c:choose>
											</td>							
										</c:if>
										<c:if test="${Term_Grading[13]!=null}">								
											<td>
											<c:out value="${Term_Grading[13]}"/>
											</td>
											<td>
											<c:choose>
												<c:when test="${Term_Grading[14]!=null}">
													<c:out value="${Term_Grading[14]}"/>
												</c:when>
												<c:otherwise>
													-
												</c:otherwise>
											</c:choose>
											</td>
										</c:if>
										</c:if>						
									</c:forEach>
								</c:forEach>
								 </tr>
							 </c:if>
						</c:forEach>
					</c:forEach>
				</c:forEach>
			</c:forEach>
		  </table>
		</c:when>
		<c:otherwise>
		<div class="message">

		  <strong><c:out value="There are no students available to display Grading Report"/></strong>

		</div>
		</c:otherwise>
		</c:choose>
	    </div>
	  </div>
	</div>
  </div>
</div>
<div id="CategoryDetaildGrades"></div>
