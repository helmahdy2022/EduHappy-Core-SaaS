<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  

<c:choose>
<c:when test="${param.Action=='Delete'}">
	<html>
	<head>
	<meta charset="utf-8">
	<title>Manage Semester Exam</title>	
	</head>	
	<body>
		<div>
			<%@include file="../includes/AdminMenu.jsp" %> 
		</div>
			<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
			<div id="content">
			<!--breadcrumbs-->
			<div id="content-header">
			  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Courses</a> <a href="${pageContext.request.contextPath}/Admin/Courses/Semester_Exam.jsp" class="current">Semester Exam</a> </div>
			  <h1>Semester Exam</h1>
			</div>
			<!--End-breadcrumbs-->
				
				<div class="container-fluid">
				  <hr>
				  <div class="row-fluid">
				    <div class="span6">
				      <div class="widget-box">
				        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
				          <h5>Manage Semester Exam</h5>
				        </div>
				        <div class="widget-content nopadding">
		
					  		<div class="message">${param.message}</div>
				        </div>
				     </div>	
				   </div>	
				 </div>
				</div>
			</div>
			<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
		
		<!--Footer-part-->
		<div class="row-fluid">
		<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
	</body>
	</html>
</c:when>
<c:otherwise>
	<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
	<h5>Semester Exam Details</h5>
	</div>
  	<div class="message">${param.message}</div>
</c:otherwise>
</c:choose>
<script>
cancelDelete();
</script>
