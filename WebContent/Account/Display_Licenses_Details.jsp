<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Licenses Details</title>

</head>
<body>

<div>
	<%@include file="../Admin/includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../Admin/includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Settings</a> <a href="${pageContext.request.contextPath}/Get_Licenses_Details" class="current">Licenses Information</a> </div>
		  <h1>Licenses Details</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Students Licenses</h5>
		        </div>
		        <div class="widget-content nopadding">
					<form name="form1" method="post" id="form1" class="form-horizontal" action="">
		            <div class="control-group" style="text-align:top;">
					  <!-- <div style="width:250px;border: 1px solid red;display:inline-block"> -->
					   <label class="control-label">Total current students Licenses</label>
		              <label class="control-label" style="text-align:left;margin-left:20px;">
							<c:out value="${Account_Details.no_Of_Students}"/> Students	              
					  </label>
		            </div>
					<div class="control-group">
					  <label class="control-label">Number of Used students Licenses</label>
		              <label class="control-label" style="text-align:left;margin-left:20px;">
 						<c:out value="${Account_Details.no_Of_Created_Students}"/> Students
 					  </label>
 					</div>	
					<div class="control-group">
					  <label class="control-label">Remaining students Licenses</label>
		              <label class="control-label" style="text-align:left;margin-left:20px;">
						<c:out value="${Account_Details.no_Of_Students - Account_Details.no_Of_Created_Students}"/> Students 						
 					  </label>
 					</div>
				    </form>
		        </div>
		     </div>	
		   </div>	
		 </div>
		</div>
	</div>
	<div class="rightContent"><%@include file="../Admin/includes/AdminRight.jsp" %></div>

<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"> <%@include file="../Admin/includes/AdminFooter.jsp" %></div></div>
</body>
</html>