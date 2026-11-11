<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="widget-box">
	<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		      <h5>Select student(s) from the list</h5>
	</div>
	<div class="widget-content nopadding">		
		<c:choose>
			<c:when test="${not empty Students.student_id}">
			<table class="table table-bordered data-table">
			   <thead>
			     <tr>
			       <th>Select</th>
			       <th>ID</th>
			       <th>Photo</th>
			       <th>First Name</th>
			       <th>Last Name</th>
			       <th>Username</th>       
			     </tr>
			   </thead>
				<tbody>
			<c:forEach items="${Students.student_id}" var="Student_id" varStatus="position">
			<tr class="gradeA">
				<td>
					<input type="checkbox" name="Student_id${Student_id}" id="Student_id${Student_id}" value="${Student_id}">
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}">${Student_id}</a>
				</td>
				<td>
				<c:choose>
				<c:when test="${not empty Students.fileName[position.index] && Students.fileName[position.index]!=null}">
					<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}">
					<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Students.account_id[position.index]}/Schools/${Students.school_id[position.index]}/Students/Photos/${Student_id}/${Students.fileName[position.index]}">
					</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}">
					<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
					</a>
				</c:otherwise>
				</c:choose>
				</td>
				<td><c:out value='${Students.first_Name[position.index]}'/> </td>
				<td><c:out value='${Students.last_Name[position.index]}'/></td>
				<td><c:out value='${Students.username[position.index]}'/> </td>
			</tr>
			</c:forEach>
			<tr>
			<td colspan="6">
			<div class="form-actions">
			<input type="submit" name="Submit" id="Submit" value="Assign Students to Classroom" class="btn btn-success" onclick="checkSession();">
			</div>
			</td>
			</tr>
			</tbody>
			</table>
			</c:when>
			<c:otherwise>
			<br>
			  	<div class="message">There are no students to select</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>





