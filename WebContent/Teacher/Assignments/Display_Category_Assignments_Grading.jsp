<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Category Assignments Grading</title>
<c:choose>
<c:when test="${param.User=='Admin'}">
 	<link href="${pageContext.request.contextPath}/_css/Admin_Main.css" rel="stylesheet" type="text/css">
</c:when>
<c:otherwise>
 	<link href="${pageContext.request.contextPath}/_css/Teacher_Main.css" rel="stylesheet" type="text/css">
</c:otherwise>
</c:choose>
</head>

<body style="background-color:#fff;">
<c:forEach items="${TeacherAuthority.authorityList}" var="AuthorityList">	
	<c:if test="${param.User!='Admin' && AuthorityList[2] == param.Grade_id && AuthorityList[4] == param.Class_id && AuthorityList[6]== param.Course_id && (AuthorityList[8]== param.Group_id ||AuthorityList[8]==null)}">
		<div class="authority"><c:out value="${AuthorityList[1]} > ${AuthorityList[3]}  > ${AuthorityList[5]} > ${AuthorityList[7]} ${AuthorityList[9]}"/></div>
		<c:set var="Course_Name" value="${AuthorityList[7]}"/>
	</c:if>
</c:forEach>
<c:choose>
<c:when test="${not empty Category_Assignments_Grading.categoryAssignmentList}">
	<div>
	<br/>
		<div style="float:center;margin:0 auto; width:800px;"><b>Category Name:</b> <c:out value="${param.Category_Name}"/></div> <br/> 
		<div style="float:center;margin:0 auto; width:800px;"><b>Student Name:</b> &nbsp;<c:out value=" ${param.First_Name} ${param.Last_Name}"></c:out></div>
		<table class="dataTable" style="width:800px;">
			<tr>		
			<td>  
			<label>Assignment Subject</label>
			</td>
			<td>  
			<label>Student Points</label>
			</td>
			<td>  
			<label>Out of (Max Possible Points)</label>
			</td>
			<td>  
			<label>%</label>
			</td>
			</tr>
		<c:forEach items="${Category_Assignments_Grading.categoryAssignmentList}" var="CategoryAssignmentList" >
			<c:if test="${CategoryAssignmentList[0]!=null}">	
			<tr>
			<td>  
			<c:out value="${CategoryAssignmentList[0]}"/>
			</td>
			<td>  
			<c:out value="${CategoryAssignmentList[1]}"/>
			</td>
			<td>  
			<c:out value="${CategoryAssignmentList[2]}"/>
			</td>
			<td>  
			<c:out value="${CategoryAssignmentList[3]}"/>%
			</td>
			</tr>
			</c:if>
		</c:forEach>	
		</table>
	</div>
</c:when>
<c:otherwise>
<br/>
<div class="message"><c:out value="There are no graded assignments under this category for the selected student"/></div>
</c:otherwise>
</c:choose> 
</body>
</html>