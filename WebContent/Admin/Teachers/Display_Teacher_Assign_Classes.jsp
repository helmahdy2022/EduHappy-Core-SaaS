<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Assign Teacher Classes</title>
 </head>

<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Teachers</a> <a href="${pageContext.request.contextPath}/List_Teachers?Page=Assign_Classes" class="current">Assign Classrooms</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Assign classrooms to teacher</h1>
		</div>
		<!--End-breadcrumbs-->		
		<div class="container-fluid">
		  <hr>
		    <form name="form1" action="${pageContext.request.contextPath}/Manage_Teacher_Classes" method="post">	  
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
									  				<label class="control-label">Select from the following classrooms to assign to the teacher:</label>
						              				<div class="controls"> 								              
					   									<c:forEach items="${Teacher_School_Grade.classmap}" var="Class">
					   										<c:if test="${Class.key==Grade[1] && Grade!=null && Class.key!=null}">
																<c:forEach items="${Class.value}" var="Class">
																	<c:if test="${Class[0]!=null}">
																		<div>
																		<input type="checkbox" name="Class${Class[1]}" id="Class${Class[1]}" value="${Class[1]}"
																		<c:forEach items="${Teacher_School_Grade.teacher_Classes}" var="Teacher_Classes">
																		<c:if test="${Teacher_Classes==Class[1]}">
																		Checked
																		</c:if>
																		</c:forEach>															
																		/>
																	<c:out value='${Class[0]}'/>
																	<input type="hidden" name="hiddenClass${Class[1]}" id="hiddenClass${Class[1]}" value="${Class[1]}"/>
																		</div>
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
		           <input type="submit" name="Submit" id="Submit" value="Assign classroom(s) to teacher" class="btn btn-success">
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