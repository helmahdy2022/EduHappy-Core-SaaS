<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Change Password</title>

</head>
 
<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300">
  <!-- For Demo Purposes - Theme Settings Pane -->
	<c:if test="${Parent_Account_Details.parent_First_Login!=1}"> 
		<%@include file="../../Student/includes/Theme_Settings.jsp" %>
	</c:if>
  <!-- End: Theme Settings Pane -->
  <div id="main">
<c:choose>
<c:when test="${Parent_Account_Details.parent_First_Login!=1}">
	<%@include file="../../Student/includes/ParentMenu.jsp" %> 
	<%@include file="../../Student/includes/ParentLeft.jsp" %>
</c:when>
<c:otherwise>
<link href="${pageContext.request.contextPath}/_css/Student_Main.css" rel="stylesheet" type="text/css">
  <!-- Theme CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/theme.css">
</c:otherwise>
</c:choose>
     <section id="content_wrapper">
		<c:if test="${Parent_Account_Details.parent_First_Login!=1}"> 
	      <!-- Start: Topbar-Dropdown -->
			<%@include file="../../Student/includes/Topbar_Dropdown.jsp" %>
	      <!-- End: Topbar-Dropdown -->  
	      <!-- Start: Topbar -->
	      <header id="topbar">
	        <div class="topbar-left">
	          <ol class="breadcrumb">
	            <li class="crumb-active">
	              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">Dashboard</a>
	            </li>
	            <li class="crumb-icon">
	              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">
	                <span class="glyphicon glyphicon-home"></span>
	              </a>
	            </li>
	            <li class="crumb-link">
	              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">Home</a>
	            </li>
	            <li class="crumb-trail">Profile</li>
	             <li class="crumb-link">
	              <a href="${pageContext.request.contextPath}/Parent/Profile/Change_Password.jsp">Change Password</a>
	            </li>
	          </ol>
	        </div>
			<c:if test="${Parent_Account_Details.parent_First_Login!=1}"> 
				<%@include file="../../Student/includes/Topbar_Right.jsp" %>
			</c:if>
	      </header>
	      <!-- End: Topbar -->
	 	</c:if>
       <section id="content" class="table-layout animated fadeIn">            
	     <div class="page-heading">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="fa fa-user"></span>Change Password</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Parent Profile</code>
              </div>
            </div>
  			<div class="message">${param.message}</div>             
          </div>	
	        </div>
       </section> 
      <!-- End: Content -->

      <!-- Begin: Page Scripts -->

		<%@include file="../../Student/includes/Page_Scripts.jsp" %>

      <!-- End: Page Scripts -->            
     </section>
    <!-- Start: Right Sidebar -->
		<c:if test="${Parent_Account_Details.parent_First_Login!=1}">			
			<%@include file="../../Student/includes/Topbar_Right_Content.jsp" %>
		</c:if>
    <!-- End: Right Sidebar -->
  </div>
</body>

</html>