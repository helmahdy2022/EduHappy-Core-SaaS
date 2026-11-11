<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Course Lessons</title>
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
	            <li class="crumb-trail">Course Content</li>
	             <li class="crumb-link">
	              <a href="${pageContext.request.contextPath}/Student/Content/Initiate_Course_Content.jsp?Student_id=${param.Student_id}">Course Content</a>
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
	            <li class="crumb-trail">Course Content</li>
	             <li class="crumb-link">
	              <a href="${pageContext.request.contextPath}/Student/Content/Initiate_Course_Content.jsp">Course Content</a>
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
	          <h4>course Content</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="fa fa-book"></span>Lesson Details</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Course Content - Course Lesson Details</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
					  <table class="table table-bordered mbn">
	      				<thead>
		      			 <tr><th colspan="5">Course Lesson Details</th></tr>
		    			</thead>		
						<c:forEach items="${Course_Structure.course_Structure_List}" var="Course_Structure_List" begin="0" end="0">
						<c:set var="Course_Name" value="${Course_Structure_List[1]}"/>
						</c:forEach>
		  				<tr>		
					 	<td class="contentCourse"><c:out value="${param.Course_Name}"/></td>
					    <td class="contentLessonInProgress">Lesson Details</td>
						<td class="contentLessonInProgress" style="width:15%;background-color:#b3b3b3">		
						 <c:choose>
							<c:when test="${param.Source=='Parent'}">
					      		<a href="${pageContext.request.contextPath}/Get_All_Lessons?Course_id=${param.Course_id}&&Student_id=${param.Student_id}">Back to All Lessons</a>
							</c:when>
							<c:otherwise>
					      		<a href="${pageContext.request.contextPath}/Get_All_Lessons?Course_id=${param.Course_id}">Back to All Lessons</a>
							</c:otherwise>
						 </c:choose>	
						</td>	    
		    			<td class="contentAllLessons" width="100">
						 <c:choose>
							<c:when test="${param.Source=='Parent'}">
					      		<a href="${pageContext.request.contextPath}/Student/Content/Initiate_Course_Content.jsp?Student_id=${param.Student_id}">Back to Course Content</a>
							</c:when>
							<c:otherwise>
					      		<a href="${pageContext.request.contextPath}/Student/Content/Initiate_Course_Content.jsp">Back to Course Content</a>
							</c:otherwise>
						 </c:choose>	
		    			</td>
		   				</tr>
		  				<tr>
			  			<td></td>
			  			<td colspan="3">
						<c:set var="Lesson_Name" value="${Lesson_Details.lesson_Name}">
						</c:set>
						<c:set var="Lesson_Start" value="${Lesson_Details.lesson_Start}">
						</c:set>
						<c:set var="Lesson_End" value="${Lesson_Details.lesson_End}">
						</c:set>
						<c:set var="Description" value="${Lesson_Details.description}">
						</c:set>
						<c:set var="Video1" value="${Lesson_Details.video1}"/>
						<c:set var="Video2" value="${Lesson_Details.video2}"/>
						<c:set var="Video3" value="${Lesson_Details.video3}"/>
						<table class="table table-bordered mbn">
						<tr>
						<td width="150">
						<label>Lesson Name</label>
						</td>
						<td>
						<c:out value='${Lesson_Name}'/>
						</td>						
						</tr>
						<tr>
						<td width="150">
						<label>Start Date</label>
						</td>
						<td>
						<c:out value='${Lesson_Start}'/>
						</td>
						</tr>
						<tr>
						<td width="150">
						<label>End Date</label>
						</td>
						<td>
						<c:out value='${Lesson_End}'/>
						</td>
						</tr>
						<tr>
						<td width="150">
						<label>Detailed Description</label>
						</td>
						<td style="vertical-align:top">
						<c:out value="${Description}" escapeXml="false"/>
						</td>
						</tr>
						<tr>
						<td width="150">
						<label>Lesson Video1</label>
						</td>
						<td>
						<c:out value="${Video1}" escapeXml="false"/>
						</td>
						</tr>
						<tr>
						<td width="150">
						<label>Lesson Video2</label>
						</td>
						<td>
						<c:out value="${Video2}" escapeXml="false"/>
						</td>
						</tr>
						<tr>
						<td width="150">
						<label>Lesson Video3</label>
						</td>
						<td>
						<c:out value="${Video3}" escapeXml="false"/>
						</td>
						</tr>						
						<tr> 	
						  <td colspan="2">
						  <table class="table table-striped table-hover" id="datatable2">
						  <tr>
							<td colspan="2"><label>Attached Files:</label></td>
						  </tr>
						  <tr>
							<td colspan="2" style="height:10px;"></td>
						  </tr>				  
							<c:set var="Documents" value="0"/>
							<c:set var="PowerPoint" value="0"/>
							<c:set var="video" value="0"/>
							<c:set var="audio" value="0"/>
							<c:set var="image" value="0"/>				
							<c:set var="zip" value="0"/>
							<c:forEach items="${Lesson_Details.file_id}" var="File_id" varStatus="loop">
							<c:set var="Account_id" value="${param.Account_id}" scope="application"  />
							<c:set var="School_id" value="${param.School_id}" scope="application"  />
							<c:set var="Entity_id" value="${Lesson_Details.lesson_id}" scope="application"  />
							<!-- ------------------------------Documents------------------------------------------ -->
							<c:if test="${(Lesson_Details.fileCategory[loop.index]=='doc' || Lesson_Details.fileCategory[loop.index]=='txt' || Lesson_Details.fileCategory[loop.index]=='pdf' || Lesson_Details.fileCategory[loop.index]=='excel')&&Documents==0}">
							<c:if test="${Documents==0}">
							<tr><td colspan="2" class="fileCategory">Lesson Documents</td></tr>
							<c:set var="Documents" value="1"/>
							</c:if>
							<c:forEach items="${Lesson_Details.file_id}" var="File_id" varStatus="loop">
							<c:if test="${Lesson_Details.fileCategory[loop.index]=='doc' || Lesson_Details.fileCategory[loop.index]=='txt' || Lesson_Details.fileCategory[loop.index]=='pdf' || Lesson_Details.fileCategory[loop.index]=='excel'}">
							<tr id="Lesson_File${File_id}">
							<td width="5%"><img src="${pageContext.request.contextPath}/images/File_Extensions/${Lesson_Details.fileCategory[loop.index]}.jpg" width="20" height="20"></td>
							<td width="95%"><a href="${pageContext.request.contextPath}/File_Download?File_id=${File_id}&&Entity=Lessons&&EntityIdName=Lesson_id" target="_blank" class="assignmentLink"><c:out value="${Lesson_Details.realFileName[loop.index]}"/></a></td>	
							</tr>
							</c:if>
							</c:forEach>
							</c:if>
							<!-- ------------------------------Power Point------------------------------------------ -->					
							<c:if test="${Lesson_Details.fileCategory[loop.index]=='ppt'&&PowerPoint==0}">
							<c:if test="${PowerPoint==0}">
							<tr><td colspan="2" class="fileCategory">Lesson Power Point</td></tr>
							<c:set var="PowerPoint" value="1"/>
							</c:if>
							<c:forEach items="${Lesson_Details.file_id}" var="File_id" varStatus="loop">
							<c:if test="${Lesson_Details.fileCategory[loop.index]=='ppt'}">
							<tr id="Lesson_File${File_id}">
							<td width="5%"><img src="${pageContext.request.contextPath}/images/File_Extensions/${Lesson_Details.fileCategory[loop.index]}.jpg" width="20" height="20"></td>
							<td width="95%"><a href="${pageContext.request.contextPath}/File_Download?File_id=${File_id}&&Entity=Lessons&&EntityIdName=Lesson_id" target="_blank" class="assignmentLink"><c:out value="${Lesson_Details.realFileName[loop.index]}"/></a></td>	
							</tr>
							</c:if>
							</c:forEach>
							</c:if>	
							<!-- ------------------------------Videos------------------------------------------ -->						
							<c:if test="${Lesson_Details.fileCategory[loop.index]=='video'&&video==0}">
							<c:if test="${video==0}">
							<tr><td colspan="2" class="fileCategory">Lesson Videos</td></tr>
							<c:set var="video" value="1"/>
							</c:if>
							<c:forEach items="${Lesson_Details.file_id}" var="File_id" varStatus="loop">
							<c:if test="${Lesson_Details.fileCategory[loop.index]=='video'}">
							<tr id="Lesson_File${File_id}">
							<td width="5%"><img src="${pageContext.request.contextPath}/images/File_Extensions/${Lesson_Details.fileCategory[loop.index]}.jpg" width="20" height="20"></td>
							<td width="95%"><a href="${pageContext.request.contextPath}/File_Download?File_id=${File_id}&&Entity=Lessons&&EntityIdName=Lesson_id" target="_blank" class="assignmentLink"><c:out value="${Lesson_Details.realFileName[loop.index]}"/></a></td>	
							</tr>
							</c:if>
							</c:forEach>
							</c:if>	
							<!-- ------------------------------Audio------------------------------------------ -->						
							<c:if test="${Lesson_Details.fileCategory[loop.index]=='audio'&&audio==0}">
							<c:if test="${audio==0}">
							<tr><td colspan="2" class="fileCategory">Lesson Audios</td></tr>
							<c:set var="audio" value="1"/>
							</c:if>
							<c:forEach items="${Lesson_Details.file_id}" var="File_id" varStatus="loop">
							<c:if test="${Lesson_Details.fileCategory[loop.index]=='audio'}">
							<tr id="Lesson_File${File_id}">
							<td width="5%"><img src="${pageContext.request.contextPath}/images/File_Extensions/${Lesson_Details.fileCategory[loop.index]}.jpg" width="20" height="20"></td>
							<td width="95%"><a href="${pageContext.request.contextPath}/File_Download?File_id=${File_id}&&Entity=Lessons&&EntityIdName=Lesson_id" target="_blank" class="assignmentLink"><c:out value="${Lesson_Details.realFileName[loop.index]}"/></a></td>	
							</tr>
							</c:if>
							</c:forEach>
							</c:if>	
							<!-- ------------------------------Images------------------------------------------ -->						
							<c:if test="${Lesson_Details.fileCategory[loop.index]=='image'&&image==0}">
							<c:if test="${image==0}">
							<tr><td colspan="2" class="fileCategory">Lesson Images</td></tr>
							<c:set var="image" value="1"/>
							</c:if>
							<c:forEach items="${Lesson_Details.file_id}" var="File_id" varStatus="loop">
							<c:if test="${Lesson_Details.fileCategory[loop.index]=='image'}">
							<tr id="Lesson_File${File_id}">
							<td width="5%"><img src="${pageContext.request.contextPath}/images/File_Extensions/${Lesson_Details.fileCategory[loop.index]}.jpg" width="20" height="20"></td>
							<td width="95%"><a href="${pageContext.request.contextPath}/File_Download?File_id=${File_id}&&Entity=Lessons&&EntityIdName=Lesson_id" target="_blank" class="assignmentLink"><c:out value="${Lesson_Details.realFileName[loop.index]}"/></a></td>	
							</tr>
							</c:if>
							</c:forEach>
							</c:if>						
							<!-- ------------------------------Archives------------------------------------------ -->											
							<c:if test="${Lesson_Details.fileCategory[loop.index]=='zip'&&zip==0}">
							<c:if test="${zip==0}">
							<tr><td colspan="2" class="fileCategory">Lesson Archives</td></tr>
							<c:set var="zip" value="1"/>
							</c:if>
							<c:forEach items="${Lesson_Details.file_id}" var="File_id" varStatus="loop">
							<c:if test="${Lesson_Details.fileCategory[loop.index]=='zip'}">
							<tr id="Lesson_File${File_id}">
							<td width="5%"><img src="${pageContext.request.contextPath}/images/File_Extensions/${Lesson_Details.fileCategory[loop.index]}.jpg" width="20" height="20"></td>
							<td width="95%"><a href="${pageContext.request.contextPath}/File_Download?File_id=${File_id}&&Entity=Lessons&&EntityIdName=Lesson_id" target="_blank" class="assignmentLink"><c:out value="${Lesson_Details.realFileName[loop.index]}"/></a></td>	
							</tr>
							</c:if>
							</c:forEach>
							</c:if>								
							</c:forEach>
						  </table>
						 </td>
						</tr>
						</table>
					   </td>
				 </tr>
				</table>		
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