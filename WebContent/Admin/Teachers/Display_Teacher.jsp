<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Teachers List</title>
 <script>
 </script>
</head>

<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Teachers</a> <a href="${pageContext.request.contextPath}/List_Teachers?Page=${param.Page}" class="current">${param.Page}</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Teacher List</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Teacher List</h5>
		        </div>
		        <div class="widget-content nopadding">
		<c:choose>
			<c:when test="${not empty Teachers.teacher_id}">
			<table class="table table-bordered data-table">
			   <thead>
			     <tr>
			       <th>ID</th>
			       <th>Photo</th>
			       <th>First Name</th>
			       <th>Last Name</th>
			       <th>Username</th>       
			     </tr>
			   </thead>
				<tbody>
					<c:forEach items="${Teachers.teacher_id}" var="Teacher_id" varStatus="position">
					<tr class="gradeA">
						<td style="width:50px;">
							<c:if test="${param.Page == 'Edit_Teacher' }">
								<a href="${pageContext.request.contextPath}/Get_Teacher_Details?Teacher_id=${Teacher_id}" onclick="overlayOn();">${Teacher_id}</a>
							</c:if>		
							<c:if test="${param.Page == 'Assign_Grades' ||  param.Page == 'Assign_Classes' || param.Page == 'Assign_Courses' || param.Page == 'Assign_Content'}">
								<a href="${pageContext.request.contextPath}/Get_Teacher_Authority?Teacher_id=${Teacher_id}&&Page=${param.Page}" onclick="overlayOn();">${Teacher_id}</a>
							</c:if>	
						</td>
						<td style="width:50px;">
						<c:choose>
						<c:when test="${not empty Teachers.fileName[position.index] && Teachers.fileName[position.index]!=null}">
							<c:if test="${param.Page == 'Edit_Teacher'}">
								<a href="${pageContext.request.contextPath}/Get_Teacher_Details?Teacher_id=${Teacher_id}" onclick="overlayOn();">
									<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Teachers.account_id[position.index]}/Teachers/Photos/${Teacher_id}/${Teachers.fileName[position.index]}">
								</a>
							</c:if>		
							<c:if test="${param.Page == 'Assign_Grades' ||  param.Page == 'Assign_Classes' || param.Page == 'Assign_Courses' || param.Page == 'Assign_Content'}">
								<a href="${pageContext.request.contextPath}/Get_Teacher_Authority?Teacher_id=${Teacher_id}&&Page=${param.Page}" onclick="overlayOn();">
									<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Teachers.account_id[position.index]}/Teachers/Photos/${Teacher_id}/${Teachers.fileName[position.index]}">
								</a>
							</c:if>	
						</c:when>
						<c:otherwise>
							<c:if test="${param.Page == 'Edit_Teacher' }">
								<a href="${pageContext.request.contextPath}/Get_Teacher_Details?Teacher_id=${Teacher_id}" onclick="overlayOn();">
									<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
								</a>
							</c:if>		
							<c:if test="${param.Page == 'Assign_Grades' ||  param.Page == 'Assign_Classes' || param.Page == 'Assign_Courses' || param.Page == 'Assign_Content'}">
								<a href="${pageContext.request.contextPath}/Get_Teacher_Authority?Teacher_id=${Teacher_id}&&Page=${param.Page}" onclick="overlayOn();">
									<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
								</a>
							</c:if>						
		
						</c:otherwise>
						</c:choose>
						</td>
						<td><c:out value='${Teachers.first_Name[position.index]}'/> </td>
						<td><c:out value='${Teachers.last_Name[position.index]}'/></td>
						<td><c:out value='${Teachers.username[position.index]}'/> </td>
					</tr>
					</c:forEach>
						</tbody>
					</table>
					</c:when>
					<c:otherwise>
					<br>
					  		<div class="message">There are no Teachers found under the selected search criteria. Please change search criteria and try again.</div>
					</c:otherwise>
				</c:choose>
 				</div>
 			  </div>
     		</div>
 		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
    	  </div>
   		</div>
  	</div>
 	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>