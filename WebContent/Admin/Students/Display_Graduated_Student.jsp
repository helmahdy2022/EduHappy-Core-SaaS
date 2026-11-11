<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		
<div class="widget-box">
  <div style="overflow-x:auto;">
	<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		      <h5>Search Results</h5>
	</div>
	<div class="widget-content nopadding">		
		<c:choose>
			<c:when test="${not empty Students.student_id}">
			<table id="students" class="table table-striped table-bordered" style="width:90%">
			   <thead>
			     <tr>
			       <th>ID</th>
			       <th>Photo</th>
			       <th>First Name</th>
			       <th>Last Name</th>
			       <th>Username</th>
			       <th>Report Card</th>       
			     </tr>
			   </thead>
				<tbody>
			<c:forEach items="${Students.student_id}" var="Student_id" varStatus="position">
			<tr>
				<td style="width:50px;">
					<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}" onclick="overlayOn();">${Student_id}</a>
				</td>
				<td style="width:50px;">
				<c:choose>
				<c:when test="${not empty Students.fileName[position.index] && Students.fileName[position.index]!=null}">
					<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}&&Graduated=Yes" onclick="overlayOn();">
					<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Students.account_id[position.index]}/Schools/${Students.school_id[position.index]}/Students/Photos/${Student_id}/${Students.fileName[position.index]}">
					</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}&&Graduated=Yes">
					<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
					</a>
				</c:otherwise>
				</c:choose>
				</td>
				<td style="width:100px;"><c:out value='${Students.first_Name[position.index]}'/> </td>
				<td style="width:200px;"><c:out value='${Students.last_Name[position.index]}'/></td>
				<td style="width:200px;"><c:out value='${Students.username[position.index]}'/> </td>
				<td><a href="javascript:window.open('${pageContext.request.contextPath}/Get_Report_Card?Student_id=${Student_id}
				&&User=Admin&&First_Name=${Students.first_Name[position.index]}&&Last_Name=${Students.last_Name[position.index]}
				&&Account_id=${Students.account_id[position.index]}&&School_id=${Students.school_id[position.index]}&&FileName=${Students.fileName[position.index]}',
				'Full_Grading_Report','toolbar=no, menubar=no, location=no, width=1200,height=500, top=300, left=500')" class="catLink">								 							
					Report Card
				</a></td>				
				
			</tr>
			</c:forEach>
				</tbody>
			</table>
			</c:when>
			<c:otherwise>
			<br>
			  		<div class="message">There are no Students found under the selected search criteria. Please change search criteria and try again.</div>
			</c:otherwise>
		</c:choose>
	</div>
  </div>
</div>

