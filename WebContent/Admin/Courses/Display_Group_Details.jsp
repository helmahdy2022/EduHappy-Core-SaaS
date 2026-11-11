<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Group Details</h5>
</div>
<div class="widget-content nopadding">
<div class="control-group">
	<label class="control-label">Group Name *</label>
	<div class="controls">  		 
		<input type="text" name="Group_Name" id="Group_Name" class="span11" value="<c:out value='${Group_Details.group_Name}'/>" class="mediumText" required>
		<input type="hidden" name="Group_id" id="Group_id" value="${Group_Details.group_id}">
    </div>
</div>	
<div class="form-actions">
    <input type="submit" name="Modify" id="Modify" value="Edit" class="btn btn-info">
    <input type="button" name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage()" class="btn btn-danger">            
</div>
<div class="requiredSignNote"><label>Fields marked (*) are required</label></div>
</div>


  <div class="widget-box">
   <div style="overflow-x:auto;">
    <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
      <h5>List of students in the group</h5>
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
			<tr class="gradeA" id="Student_Group${Student_id}">
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
				<div style="text-align:center;"><input type="button" class="btn btn-danger" name="RemoveFromGroup" value="Remove From Group" onclick="removeFromGroup('${Student_id}', '${Group_Details.group_id}')"/></div>
			</td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
		</c:when>
		<c:otherwise>
		<br>
		  		<div class="message">There are no students available in this group</div>
		</c:otherwise>
		</c:choose>
    </div>
   </div>
  </div>

