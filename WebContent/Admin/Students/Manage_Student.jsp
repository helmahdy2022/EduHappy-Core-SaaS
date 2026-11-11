<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Manage Student</title>

</head>

<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
<div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Students</a> 
		  <c:choose>
		  <c:when test="${Graduated!='Yes'}">		  
		  <a href="${pageContext.request.contextPath}/Admin/Students/Search_Student.jsp" class="current">Edit Student</a> 
		  </c:when>
		  <c:otherwise>
		  <a href="${pageContext.request.contextPath}/Admin/Students/Search_Graduated_Student.jsp" class="current">Edit Graduated Student</a>		  
		  </c:otherwise>
		  </c:choose>
		  </div>
		  <h1>Manage Student</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Manage Student</h5>
		        </div>
		        <div class="widget-content nopadding">
				 	 <c:if test="${not empty message}">
				  		<div class="message">${message}
				  		<br>
				  		<c:forEach items="${uploadMessage}" var="uploadMessage">
				  		<c:out value="${uploadMessage}" />
				  		<br>
				  		</c:forEach>
				  		</div> 
				 	 </c:if>
		        </div>
		     </div>	
		   </div>	
		 </div>
		</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
</div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>