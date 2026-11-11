<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${not empty Assignments_List.subject}">
<table class="table table-bordered mbn">
<tbody>
  <tr>
  <td style="width:5px; background-color:#f2f2f2;"></td>
  <td style="vertical-align:top;width:1px;border-right:1px solid #ccc">
	<div id="Assignment"> 
		<h4 style="font-size:20px; font-weight:bold;text-decoration: underline;">Assignments List</h4>
		<div class="assignmentList">
		<c:forEach items="${Assignments_List.subject}" var="Subject" varStatus="loop">
		<c:if test="${param.Page =='Edit' || param.Page =='Edit_Standard'}" >
			<c:set var="link" value="javascript:editAssignment(${Assignments_List.assignment_id[loop.index]},${param.Grade_id},${param.Class_id},${param.Course_id},${param.Lesson_id},${param.Category_id}, '${param.Page}')"/>
		</c:if>
		<c:if test="${param.Page=='New'|| param.Page=='Give'}" >
			<c:set var="link" value="javascript:giveAssignment(${Assignments_List.assignment_id[loop.index]},${param.Grade_id},${param.Class_id},${param.Course_id},'${param.Group_id}', ${param.Lesson_id},${param.Category_id}, '${param.Page}')"/>
		</c:if>
		<c:if test="${param.Page=='Grade'}" >
			<c:set var="link" value="javascript:gradeAssignment(${Assignments_List.assignment_id[loop.index]},${param.Grade_id},${param.Class_id},${param.Course_id}, '${param.Group_id}', ${param.Lesson_id},${param.Category_id}, '${param.Page}')"/>
		</c:if>		
		<div class="assignmentLinkHolder">
		<div class="assignmentLinkHolder">
		<div style="width:15px;display:inline-block">
		<i class="glyphicon glyphicon-th-list"></i>
		</div>
		<div style="width:175px;display:inline-block;vertical-align: top;border-bottom:1px solid #ccc">		
			<a class="assignmentLink" href="${link}">
				<c:out value="${Subject}"></c:out>
				<c:if test="${Assignments_List.standard[loop.index]=='1'}">
				<font size="2" color="green">(Standard)</font>
				</c:if>
			</a>
		</div>
		</div>
		
		</div>
		</c:forEach>
		</div>

   </div>			    
  </td>
  <td style="vertical-align:top;border-left:transparent">
	<div id="AssignmentDetails" >
		<div style="text-align:center; width:90%;font-family:Times New Roman; font-size:48px; color:#0aaedb">Assignments List</div>
		<table class="table table-bordered mbn" style="width:90%;margin-left:70px">
		   <thead>
		     <tr>
		       <th>Assignment ID</th>
		       <th>Assignment Subject</th>
		       <th>Max Possible Points</th>
		       <th>Assignment Type</th>      
		     </tr>
		   </thead>
			<tbody>
		<c:forEach items="${Assignments_List.subject}" var="Subject" varStatus="position">
			<c:if test="${param.Page =='Edit' || param.Page =='Edit_Standard'}" >
				<c:set var="link" value="javascript:editAssignment(${Assignments_List.assignment_id[position.index]},${param.Grade_id},${param.Class_id},${param.Course_id},${param.Lesson_id},${param.Category_id}, '${param.Page}')"/>
			</c:if>
			<c:if test="${param.Page=='New'|| param.Page=='Give'}" >
				<c:set var="link" value="javascript:giveAssignment(${Assignments_List.assignment_id[position.index]},${param.Grade_id},${param.Class_id},${param.Course_id},'${param.Group_id}', ${param.Lesson_id},${param.Category_id}, '${param.Page}')"/>
			</c:if>
			<c:if test="${param.Page=='Grade'}" >
				<c:set var="link" value="javascript:gradeAssignment(${Assignments_List.assignment_id[position.index]},${param.Grade_id},${param.Class_id},${param.Course_id}, '${param.Group_id}', ${param.Lesson_id},${param.Category_id}, '${param.Page}')"/>
			</c:if>		
		<tr>
			<td style="width:50px;">
				<a href="${link}">${Assignments_List.assignment_id[position.index]}</a>
			</td>
			<td style="width:150px;">
				<a href="${link}">
				<c:out value='${Subject}'/>
				</a>
			</td>
			<td style="width:100px;"><c:out value='${Assignments_List.max_Grade[position.index]}'/> </td>
			<td style="width:100px;">
				<c:if test="${Assignments_List.standard[position.index]=='1'}">
				<font size="2" color="green">Standard</font>
				</c:if>			
			</td>
		</tr>
		</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="DeleteMessage" ></div>
  </td>
  <td style="width:5px;background-color:#f2f2f2"></td>
  </tr>
</tbody>
</table>  
</c:when>
<c:otherwise>
	<div class="message"><c:out value="There are no assignments"></c:out></div>
</c:otherwise>
</c:choose>

