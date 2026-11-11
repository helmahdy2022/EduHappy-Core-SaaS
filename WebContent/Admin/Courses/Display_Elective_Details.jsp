<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <div class="widget-box">
   <div style="overflow-x:auto;">
    <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
      <h5>List of students in the elective course</h5>
    </div>
    <div class="widget-content nopadding">
		<c:choose>
		<c:when test="${not empty Students.student_id}">
		<table class="table table-bordered data-table">
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
			<tr class="gradeA" id="Student_Elective${Student_id}${param.Course_id}">
			<td style="width:50px;">
				<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}"><c:out value='${Student_id}'/></a>
			</td>
			<td style="width:50px;">
			<c:choose>
			<c:when test="${not empty Students.fileName[position.index] && Students.fileName[position.index]!=null}">
				<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}">
				<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Students.account_id[position.index]}/Schools/${Students.school_id[position.index]}/Students/Photos/${Student_id}/${Students.fileName[position.index]}">
				</a>
			</c:when>
			<c:otherwise>
				<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
			</c:otherwise>
			</c:choose>
			</td>
			<td style="width:100px;"><c:out value='${Students.first_Name[position.index]}'/> </td>
			<td style="width:200px;"><c:out value='${Students.last_Name[position.index]}'/></td>
			<td style="width:200px;"><c:out value='${Students.username[position.index]}'/> </td>
			<td style="width:450px;">		
				<div style="text-align:center;">
				<input type="button" class="btn btn-danger" name="RemoveFromElective" value="Remove From Elective" onclick="removeFromElective('${Student_id}', '${param.Course_id}')"/>				
				</div>
			</td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
		</c:when>
		<c:otherwise>
		<br>
		  		<div class="message">There are no students assigned to this elective course yet</div>
		</c:otherwise>
		</c:choose>
    </div>
   </div>
  </div>

