<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Courses Assignments</title>

<script>
window.name="parentwindow";

function startAttempt(Assignment_id, Attempt_id, Assignment_Duration, max_Grade, Course_id)
{
var start;
start = confirm('You are about to start an attempt, please click OK button to proceed or otherwise click Cancel');

if(start==true)
	window.open('${pageContext.request.contextPath}/Start_Attempt?Assignment_id='+Assignment_id+'&&Assignment_Duration='+Assignment_Duration+'&&max_Grade='+max_Grade+"&&Course_id="+Course_id, 'ScoWindow', 'height=1000, width=1200');
	///window.open('${pageContext.request.contextPath}/content/Assignments/Scorm/player.jsp', 'ScoWindow', 'height=1000, width=1200');
	if(Attempt_id!='undefined') 
  		{
			document.getElementById("Attempt_Window"+Attempt_id).innerHTML="Window Opened";
  		}
	else
		{
			window.location.reload(); 
		}
}
</script>
</head>

<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300">

  <!-- For Demo Purposes - Theme Settings Pane -->
	<%@include file="../includes/Theme_Settings.jsp" %>
  <!-- End: Theme Settings Pane -->
  <div id="main">
	 <c:choose>
		<c:when test="${param.Source=='Parent'}">
			<%@include file="../includes/ParentMenu.jsp" %> 
			<%@include file="../includes/ParentLeft.jsp" %>
		</c:when>
		<c:otherwise>
			<%@include file="../includes/StudentMenu.jsp" %> 
			<%@include file="../includes/StudentLeft.jsp" %>
		</c:otherwise>
	 </c:choose>
     <section id="content_wrapper">

      <!-- Start: Topbar-Dropdown -->
	<%@include file="../includes/Topbar_Dropdown.jsp" %>
      <!-- End: Topbar-Dropdown -->  
      <!-- Start: Topbar -->
	 <c:choose>
		<c:when test="${param.Source=='Parent'}">
      <header id="topbar">
        <div class="topbar-left">
          <ol class="breadcrumb">
            <li class="crumb-active">
              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">Dashboard</a>
            </li>
            <li class="crumb-icon">
              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">
                <span class="glyphicon glyphicon-home"></span>
              </a>
            </li>
            <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">Home</a>
            </li>
            <li class="crumb-trail">Student's Assignments</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Assignments/Initiate_Assignments.jsp?Student_id=${param.Student_id}">Assignments</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
		</c:when>
		<c:otherwise>
      <header id="topbar">
        <div class="topbar-left">
          <ol class="breadcrumb">
            <li class="crumb-active">
              <a href="${pageContext.request.contextPath}/Student/Student_Main.jsp">Dashboard</a>
            </li>
            <li class="crumb-icon">
              <a href="${pageContext.request.contextPath}/Student/Student_Main.jsp">
                <span class="glyphicon glyphicon-home"></span>
              </a>
            </li>
            <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Student_Main.jsp">Home</a>
            </li>
            <li class="crumb-trail">Student's Assignments</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Assignments/Initiate_Assignments.jsp">Assignments</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
		</c:otherwise>
	 </c:choose>
      <!-- End: Topbar -->
  	   <%@include file="../includes/Adsense_Top.jsp" %>
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
	          <h4>Student Assignments</h4>
	          <hr class="alt short">
  	          <h4>
	             <c:forEach items="${child.student_id}" var="Student_id"  varStatus="position">
					<c:if test="${Student_id == param.Student_id}">	             
	                  	 <font style="color:blue"><c:out value='${child.first_Name[position.index]}'/>&nbsp;<c:out value='${child.last_Name[position.index]}'/></font>		             			
					</c:if>
	             </c:forEach>
	          </h4>
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="glyphicon glyphicon-tasks"></span>Current Assignments</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Student Assignments</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
					<c:choose>
					 <c:when test="${not empty Assignments.courseAssignments}">
					  <table class="table table-bordered mbn">
						<c:forEach items="${Assignments.courseAssignments}" var="Assignment">
						<c:set var="CourseNameX" value="${Assignment[1]} ${Assignment[3]}"/>
						<c:if test="${CourseNameX!=CourseNameY}">
					   <tr>
					   <td width="10%">
						 <c:out value="${Assignment[1]} ${Assignment[3]}"/>
					  </td>		  
					  <td style="text-align:right;">
					 <c:choose>
						<c:when test="${param.Source=='Parent'}">
					  		<a href="${pageContext.request.contextPath}/Student/Assignments/Previous_Assignments.jsp?Course_id=${Assignment[0]}&&Course_Name=${Assignment[1]}&&Group_id=${Assignment[2]}&&Group_Name=${Assignment[3]}&&Student_id=${param.Student_id}&&Source=Parent">Previous Assignments</a>
						</c:when>
						<c:otherwise>
					  		<a href="${pageContext.request.contextPath}/Student/Assignments/Previous_Assignments.jsp?Course_id=${Assignment[0]}&&Course_Name=${Assignment[1]}&&Group_id=${Assignment[2]}&&Group_Name=${Assignment[3]}">Previous Assignments</a>
						</c:otherwise>
					 </c:choose>
					  </td>
					   </tr>
					   <c:set var="CourseNameY" value="${Assignment[1]} ${Assignment[3]}"/>
					   </c:if>
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
					    <td width="85%">						
						<c:choose>
						<c:when test="${not empty Assignment[5]}">
							<div class="assignmentGrade" style="margin-left:0px">Max possible points:</div><div class="contentLessonField" style="width:5px;"><c:out value="${Assignment[5]}"/></div> 
							<div class="contentLessonStart" style="margin-left:50px;">Start Date:</div> <div class="contentLessonField"><c:out value="${Assignment[6]}"/></div>
							<div class="contentLessonEnd" style="margin-left:50px;"> Due Date:</div> <div class="contentLessonField"><c:out value="${Assignment[7]}"/></div>
					  		<c:set var="isOnlineQuiz" value="No"/>
					  	<c:forEach items="${Assignments.assignmentFiles}" var="assignmentFiles">					  	
					  		<c:if test="${assignmentFiles[0]== Assignment[9]}">
					  			<c:if test="${assignmentFiles[1]=='OnlineQuiz'}">
					  				<c:set var="isOnlineQuiz" value="Yes"/>
					  			</c:if>
					  		</c:if>
					  	</c:forEach>
						<c:if test="${isOnlineQuiz=='No' && Assignment[12]=='1' && param.Source!='Parent'}">
							<c:choose>
							<c:when test="${Assignment[14]=='No'}">
							<div class="assignmentGrade" style="margin-left:50px"><a href="${pageContext.request.contextPath}/Get_One_Assignment?Assignment_id=${Assignment[9]}&&Give_Assignment_id=${Assignment[13]}">Submit Online</a></div>
							</c:when>
							<c:otherwise>
							<div class="assignmentGrade" style="margin-left:50px">Graded - Submission online closed</div>
							<div class="contentLessonStart" style="margin-left:50px">Your Grade: </div>
							<div class="contentLessonField" style="width:5px;"><c:out value="${Assignment[15]}"/></div>
							<br><br><div class="contentLessonStart" style="margin-left:0px;">Teacher Remarks: </div>
							<div class="contentLessonField" style="white-space:pre-wrap;"><c:out value="${Assignment[16]}"/></div>
							</c:otherwise>
							</c:choose>
						</c:if>
					  	</c:when>
					  	<c:otherwise>
					  	There are no Current Assignments under this course at the moment
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
									<c:if test="${AttemptInProgress!='Yes' && (AttemptCount<AttemptsAllowed || AttemptsAllowed==null) && param.Source!='Parent'}">
						    		 <input type="button" class="btn btn-sm btn-success" style="width:130px;" value="Start Quiz Attempt" onClick="startAttempt(${Assignment[9]}, 'undefined', '${Assignment[11]}', ${Assignment[5]}, ${Assignment[0]});"> 
									</c:if>
									Attempts Allowed ${AttemptsAllowed}
									<c:if test="${AttemptsAllowed==null}"> - Unlimited</c:if>
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
				                  <th style="text-align:center;">Action</th>
				                  <th style="text-align:center;">Attempt Window</th>                                                                       
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
									   <td style="text-align:center;">  
										 <c:if test="${Attempt[1]!='Completed' && param.Source!='Parent'}">
											<input type="button" value="Start Quiz Attempt" class="btn btn-sm btn-success mr10" onClick="startAttempt(${Assignment[9]} , '${Attempt[0]}', '${Assignment[11]}', ${Assignment[5]}, ${Assignment[0]});">
										 </c:if>
									   </td>
									   <td style="text-align:center;">  
											<div id="Attempt_Window${Attempt[0]}"><font size="2"><b></b></font></div>
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
				  </c:when>
				  <c:otherwise>
			  		<div class="message">
						There are no assignments available at the moment. Please try again later.		
			  		</div>		
				  </c:otherwise>
				 </c:choose>
                </div>
              </div>
            </div>
          </div>	
	        </div>
       </section>  
      <!-- End: Content -->
	  <%@include file="../includes/Adsense_Bottom.jsp" %>
      <!-- Begin: Page Scripts -->

		<%@include file="../includes/Page_Scripts.jsp" %>

      <!-- End: Page Scripts -->            
     </section>
    <!-- Start: Right Sidebar -->
			<%@include file="../includes/Topbar_Right_Content.jsp" %>
    <!-- End: Right Sidebar -->
  </div>
  </body>
</html>