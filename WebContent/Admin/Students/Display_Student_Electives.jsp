<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <div class="widget-box">
   <div style="overflow-x:auto;">
    <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
      <h5>Add students to elective course</h5>
    </div>
    <div class="widget-content nopadding">
	<div class="control-group">
	  <label class="control-label">Course Name</label>
            <div class="controls"> 			 		 						 
	         <select name="Course" id="Course" class="span11" onchange="showGroups(this.value)" required>
	 			 <option value="">Select Course</option>
				<c:forEach items="${Courses.course_Name}" var="Course" varStatus="loop">
			
					<option value="${Courses.course_id[loop.index]}">
						<c:out value="${Course}"></c:out>
					</option>	   						 			
				</c:forEach>
			</select> 		 					  
		  </div>
	</div>
<c:choose>
<c:when test="${not empty Students.student_id}">
<table class="table table-bordered data-table">
   <thead>
     <tr>
       <th></th>
       <th>ID</th>
       <th>Photo</th>
       <th>First Name</th>
       <th>Last Name</th>
       <th>Username</th> 
	   <th>Elective Courses</th>             
     </tr>
   </thead>
	<tbody>
<c:forEach items="${Students.student_id}" var="Student_id" varStatus="position">
<tr class="gradeA">
	<td><input name="Student_id${Student_id}" type="checkbox" value="${Student_id}"/></td>
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
		<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
	</c:otherwise>
	</c:choose>
	</td>
	<td style="width:100px;"><c:out value='${Students.first_Name[position.index]}'/> </td>
	<td style="width:200px;"><c:out value='${Students.last_Name[position.index]}'/></td>
	<td style="width:200px;"><c:out value='${Students.username[position.index]}'/> </td>
	<td style="width:450px;">		
		<table class="table table-bordered data-table">
		<c:forEach items="${Students.electives}" var="Elective" varStatus="loop">
			<c:if test="${Elective[0] == Student_id}">				
						<tr id="Student_Elective${Elective[0]}${Elective[1]}">
							<td style="width:300px;">
							<c:out value='${Elective[2]}'/>
							</td>
							<td>
							<input type="button" name="RemoveFromElective" value="X" class="btn btn-danger" onclick="removeFromElective('${Elective[0]}', '${Elective[1]}')"/>
							</td>
						</tr>							
			</c:if>
		</c:forEach>
		</table>
	</td>
</tr>
</c:forEach>
<tr class="gradeA">
	<td colspan="7">
	<input name="Class_id" type="hidden" value="${param.Class_id}"/>
       <div class="form-actions">
       <input type="submit" name="Submit" id="Submit" value="Add Student(s) to the selected elective course" class="btn btn-success">
       </div>		
	</td>
</tr>
</tbody>
</table>
</c:when>
<c:otherwise>
<br>
  		<div class="message">There are no students available in the selected class</div>
</c:otherwise>
</c:choose>
    </div>
   </div>
  </div>
