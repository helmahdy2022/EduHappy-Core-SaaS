<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="widget-content nopadding">
<table class="table table-bordered data-table">
   <thead>
     <tr>
       <th>ID</th>
       <th>Photo</th>
       <th>First Name</th>
       <th>Last Name</th>
       <th>Username</th> 
	   <th>Remove link to this student</th>             
     </tr>
   </thead>
	<tbody>
<c:forEach items="${Students.student_id}" var="Student_id" varStatus="position">
<tr class="gradeA" id="Student_Parent${Student_id}${param.Parent_id}">
	<td style="text-align:center">
		<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}" onclick="overlayOn();">${Student_id}</a>
	</td>
	<td style="text-align:center">
	<c:choose>
	<c:when test="${not empty Students.fileName[position.index] && Students.fileName[position.index]!=null}">
		<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}" onclick="overlayOn();">
		<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Students.account_id[position.index]}/Schools/${Students.school_id[position.index]}/Students/Photos/${Student_id}/${Students.fileName[position.index]}">
		</a>
	</c:when>
	<c:otherwise>
		<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
	</c:otherwise>
	</c:choose>
	</td>
	<td style="text-align:center"><c:out value='${Students.first_Name[position.index]}'/> </td>
	<td style="text-align:center"><c:out value='${Students.last_Name[position.index]}'/></td>
	<td style="text-align:center"><c:out value='${Students.username[position.index]}'/> </td>
	<td style="text-align:center">		
		<input type="button" name="RemoveParentStudentLink" value="X" class="btn btn-danger" onclick="removeParentStudent('${param.Parent_id}', '${Student_id}')"/>
	</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>