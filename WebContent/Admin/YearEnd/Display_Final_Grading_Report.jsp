<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>		
<div class="widget-box">
  <div style="overflow-x:auto;">
	<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		      <h5>Final Grading Report for the year <font color="Red">${Students.year_Name}</font></h5>
	</div>
	<div class="widget-content nopadding">		
	<c:choose>
	<c:when test="${param.isReportProduced==true}">
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
			       <th>Final Grading %</th>
			       <th>Passing Grade %</th>
			       <th>Passing Status</th>
			       <th>Report Card</th>     
			     </tr>
			   </thead>
				<tbody>
			<c:forEach items="${Students.student_id}" var="Student_id" varStatus="position">
			<tr>
				<td style="width:50px;text-align:center;">
					<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_id}">${Student_id}</a>
				</td>
				<td style="width:50px;text-align:center;">
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
				<td style="text-align:center;"><c:out value='${Students.first_Name[position.index]}'/> </td>
				<td style="text-align:center;"><c:out value='${Students.last_Name[position.index]}'/></td>
				<td style="text-align:center;"><c:out value='${Students.username[position.index]}'/> </td>
				<td style="text-align:center;"><c:out value='${Students.final_Grading[position.index]}'/> </td>
				<td style="text-align:center;"><c:out value='${Students.passing_Grade}'/> </td>
				<td style="text-align:center;">
				<c:choose>
				<c:when test="${Students.passing_Status[position.index]=='Failed'}">
				<font color='red'><b><c:out value='${Students.passing_Status[position.index]}'/></b></font>
				</c:when>
				<c:otherwise>
				<font color='green'><b><c:out value='${Students.passing_Status[position.index]}'/></b></font>
				</c:otherwise>
				</c:choose>
				</td>
				<td> 
					<a href="javascript:window.open('${pageContext.request.contextPath}/Get_Report_Card?Student_id=${Student_id}
						&&User=Admin&&First_Name=${Students.first_Name[position.index]}&&Last_Name=${Students.last_Name[position.index]}
						&&Account_id=${Students.account_id[position.index]}&&School_id=${Students.school_id[position.index]}&&FileName=${Students.fileName[position.index]}&&Promoted=${Students.promoted}',
						'Full_Grading_Report','toolbar=no, menubar=no, location=no, width=1200,height=500, top=300, left=500')" class="catLink">								 							
							<c:out value="Report Card"/>
					</a>
				</td>
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
	</c:when>
	<c:otherwise>
		<div class="message"><c:out value="${param.message}"/></div>
	</c:otherwise>
	</c:choose>
	</div>
  </div>
</div>



