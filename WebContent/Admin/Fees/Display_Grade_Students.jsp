<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="widget-box">
  <div style="overflow-x:auto;">
	<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		      <h5>Select student(s) from the list</h5>
	</div>
	<div class="widget-content nopadding">		
		<c:choose>
			<c:when test="${not empty Students.student_id}">
			<table id="grade_Students" class="table table-striped table-bordered" border="1" style="width:90%">
			   <thead>
			     <tr>
			       <th>ID</th>
			       <th>Photo</th>
			       <th>First Name</th>
			       <th>Last Name</th>
			       <th>Username</th>   
			       <th>Action</th>    
			     </tr>
			   </thead>
				<tbody>
			<c:forEach items="${Students.student_id}" var="Student_id" varStatus="position">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}" onclick="overlayOn();">${Student_id}</a>
				</td>
				<td>
				<c:choose>
				<c:when test="${not empty Students.fileName[position.index] && Students.fileName[position.index]!=null}">
					<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}" onclick="overlayOn();">
					<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Students.account_id[position.index]}/Schools/${Students.school_id[position.index]}/Students/Photos/${Student_id}/${Students.fileName[position.index]}">
					</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}" onclick="overlayOn();">
					<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
					</a>
				</c:otherwise>
				</c:choose>
				</td>
				<td><c:out value='${Students.first_Name[position.index]}'/> </td>
				<td><c:out value='${Students.last_Name[position.index]}'/></td>
				<td><c:out value='${Students.username[position.index]}'/> </td>
				<td style="text-align:center">
					<button type="button" class="btn btn-info" name="Pay${Student_id}" id="Pay${Student_id}" value="Pay${Student_id}" onclick="window.open('${pageContext.request.contextPath}/Get_Fee_Payments?Student_id=${Student_id}', 'Payment_Window', 'toolbar=no, menubar=no, location=no, width=900,height=500, top=300, left=500');">Pay</button>
				</td>
			</tr>
			</c:forEach>
			</tbody>
			</table>
			</c:when>
			<c:otherwise>
			<br>
			  	<div class="message">There are no students to assign payments</div>
			</c:otherwise>
		</c:choose>
	</div>
  </div>
</div>





