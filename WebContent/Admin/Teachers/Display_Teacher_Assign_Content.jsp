<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Assign Teacher Content</title>
 </head>

<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Teachers</a> <a href="${pageContext.request.contextPath}/List_Teachers?Page=Assign_Content" class="current">Assign Content</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Assign content to teacher</h1>
		</div>
		<!--End-breadcrumbs-->		
		<div class="container-fluid">
		  <hr>
    		<form name="form1" action="${pageContext.request.contextPath}/Manage_Teacher_Content" method="post">  
				<c:forEach items="${Teacher_School_Grade.grademap}" var="Grade">		
					<c:forEach items="${Teacher_School_Grade.school_id}" var="School" varStatus="position">
									<c:if test="${Grade.key==School && School!=null && Grade.key!=null}">
										<c:forEach items="${Grade.value}" var="Grade">
					   						<c:forEach items="${Teacher_School_Grade.teacher_Grades}" var="Teacher_Grades">
					   							<c:if test="${Grade[0]!=null && Grade[1]==Teacher_Grades}">
							  						<div class="row-fluid">
							   						 <div class="span6">
							      						<div class="widget-box">
							        						<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>	
														   		<h5><c:out value='${Teacher_School_Grade.school_Name[position.index]}'/> >> <c:out value='${Grade[0]}'/></h5> 
						   										<input type="hidden" name="school${School}" id="School" value="${School}"/>
						   										<input type="hidden" name="grade${Grade[1]}" id="Grade" value="${Grade[1]}"/>
							        						</div>
							        						<div class="widget-content nopadding">
									           				 <div class="control-group">
												  				<label class="control-label">Select from the following courses to assign to the teacher:</label>
									              				<div class="controls"> 								              
												   					<c:forEach items="${Teacher_School_Grade.gradeCoursesmap}" var="Course">
													   					<c:if test="${Course.key==Grade[1]}">
																			<c:forEach items="${Course.value}" var="Course">
																				<c:if test="${Course[0]!=null}">
																					<input type="checkbox" name="course${Course[1]}"  value="${Course[1]}"
					
																						<c:forEach items="${Teacher_School_Grade.teacher_Content}" var="Teacher_Content">
																						<c:if test="${Teacher_Content==Course[1]}">
																							Checked
																						</c:if>
																						</c:forEach>															
																					/>	
																					<c:out value='${Course[0]}'/> 
																					<input type="hidden" name="hiddencourse${Course[1]}" id="hiddencourse${Course[1]}" value="${Course[1]}"/>																
																				<br>
																				</c:if>																
																			</c:forEach>
																		</c:if>															   					
												   					</c:forEach>
												  				</div>
									            			</div>
															<input type="hidden" name="Teacher_id" id="Teacher_id" value="${param.Teacher_id}"/>
									 		 				<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
							        						</div>
							    					 	</div>	
							   						</div>	   	
							 						</div>					   												   						
					   						 	</c:if>
											</c:forEach>
										</c:forEach>
									</c:if>
					</c:forEach>
				</c:forEach>
		           <div class="form-actions">
		           <input type="submit" name="Submit" id="Submit" value="Assign course(s) to teacher" class="btn btn-success">
		           </div>
			</form>			 
		</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div>
</div>
</body>
</html>