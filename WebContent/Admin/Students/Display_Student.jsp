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
			       <c:if test="${param.Page == 'Link_Parent_Student'}">
			       <th></th>
			       </c:if>
			       <th>ID</th>
			       <th>Photo</th>
			       <th>First Name</th>
			       <th>Last Name</th>
			       <th>Username</th>       
			     </tr>
			   </thead>
				<tbody>
			<c:forEach items="${Students.student_id}" var="Student_id" varStatus="position">
			<tr>
			  <c:if test="${param.Page == 'Link_Parent_Student'}">
			    <td><input type="checkbox" name="Student" value="${Student_id}"></td>
			  </c:if>
				<td style="width:50px;">
					<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}" onclick="overlayOn();">${Student_id}</a>
				</td>
				<td style="width:50px;">
				<c:choose>
				<c:when test="${not empty Students.fileName[position.index] && Students.fileName[position.index]!=null}">
					<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}" onclick="overlayOn();">
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
				<td style="width:100px;"><c:out value='${Students.first_Name[position.index]}'/> </td>
				<td style="width:200px;"><c:out value='${Students.last_Name[position.index]}'/></td>
				<td style="width:200px;"><c:out value='${Students.username[position.index]}'/> </td>
			</tr>
			</c:forEach>
				</tbody>
			</table>
			<c:if test="${param.Page == 'Link_Parent_Student'}">
		        <div class="form-actions">
		            <input type="button" name="Submit" id="Submit" value="Link parent to the selected student" class="btn btn-success" onClick="linkParentStudent(getCheckedBoxes('Student'), '${param.Parent_id}');">
		        </div>
				 	<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
			</c:if>
			</c:when>
			<c:otherwise>
			<br>
			  		<div class="message">There are no Students found under the selected search criteria. Please change search criteria and try again.</div>
			</c:otherwise>
		</c:choose>
	</div>
  </div>
</div>

