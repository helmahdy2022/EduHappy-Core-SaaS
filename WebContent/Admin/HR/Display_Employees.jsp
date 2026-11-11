<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Employees List</title>

</head>
<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">HR and Payroll</a> <a href="${pageContext.request.contextPath}/List_All_Employees" class="current">Edit Employee</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Employees List</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		      <div style="overflow-x:auto;">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Employees List</h5>
		        </div>
				<div class="widget-content nopadding">		
					<c:choose>
						<c:when test="${not empty Employees.employee_id}">
						<table id="employees" class="table table-striped table-bordered" style="width:90%">
						   <thead>
						     <tr>
						       <th>Employee ID</th>
						       <th>Photo</th>
						       <th>First Name</th>
						       <th>Last Name</th>
						       <th>Username</th>
						       <th>Employee Group</th>       
						     </tr>
						   </thead>
							<tbody>
						<c:forEach items="${Employees.employee_id}" var="Employee_id" varStatus="position">
						<tr>
							<c:choose>
							 <c:when test="${Employees.employee_Group[position.index]=='Teacher'}">
								<td style="width:50px;">
										<a href="${pageContext.request.contextPath}/Get_Teacher_Details?Employee_id=${Employee_id}" onclick="overlayOn();">${Employee_id}</a>	
								</td>
								<td style="width:50px;">
								<c:choose>
								<c:when test="${not empty Employees.fileName[position.index] && Employees.fileName[position.index]!=null}">
										<a href="${pageContext.request.contextPath}/Get_Teacher_Details?Employee_id=${Employee_id}" onclick="overlayOn();">
											<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Employees.account_id[position.index]}/Teachers/Photos/${Employees.teacherAndAdmin_id[position.index]}/${Employees.fileName[position.index]}">
										</a>	
								</c:when>
								<c:otherwise>
										<a href="${pageContext.request.contextPath}/Get_Teacher_Details?Employee_id=${Employee_id}" onclick="overlayOn();">
											<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
										</a>					
								</c:otherwise>
								</c:choose>
								</td>
							  </c:when>
							  <c:otherwise>
								<td style="width:50px;">
										<a href="${pageContext.request.contextPath}/Get_Admin_Details?Employee_id=${Employee_id}" onclick="overlayOn();">${Employee_id}</a>	
								</td>
								<td style="width:50px;">
								<c:choose>
								<c:when test="${not empty Employees.fileName[position.index] && Employees.fileName[position.index]!=null}">
										<a href="${pageContext.request.contextPath}/Get_Admin_Details?Employee_id=${Employee_id}" onclick="overlayOn();">
											<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Employees.account_id[position.index]}/Admins/Photos/${Employees.teacherAndAdmin_id[position.index]}/${Employees.fileName[position.index]}">
										</a>	
								</c:when>
								<c:otherwise>
										<a href="${pageContext.request.contextPath}/Get_Admin_Details?Employee_id=${Employee_id}" onclick="overlayOn();">
											<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
										</a>					
								</c:otherwise>
								</c:choose>
								</td>
							  </c:otherwise>
							</c:choose>
							<td><c:out value='${Employees.first_Name[position.index]}'/> </td>
							<td><c:out value='${Employees.last_Name[position.index]}'/></td>
							<td><c:out value='${Employees.username[position.index]}'/> </td>
							<td><c:out value='${Employees.employee_Group[position.index]}'/> </td>
						</tr>
						</c:forEach>
							</tbody>
						</table>
						</c:when>
						<c:otherwise>
						<br>
						  	<div class="message">There are no Employees found under this account.</div>
						</c:otherwise>
					</c:choose>
				</div>
 		</div>
 		</div>
     </div>
		<%@include file="../includes/Adsense_Inline.jsp" %>
    </div>

   </div>
  </div>
 	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
<script>
$(document).ready(function() {
    $('#employees').DataTable();
} );
</script>
</body>
</html>