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

      <!-- Start: Topbar-Dropdown -->
	<%@include file="../../Student/includes/Topbar_Dropdown.jsp" %>
      <!-- End: Topbar-Dropdown -->  
	<c:if test="${Parent_Account_Details.parent_First_Login!=1}">
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
            <li class="crumb-trail">Settings</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Parent/Profile/Change_Password.jsp">Change Password</a>
            </li>
          </ol>
        </div>
		<%@include file="../../Student/includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
     </c:if>
	   <%@include file="../../Student/includes/Adsense_Top.jsp" %>
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
			<form class="form-horizontal" name="form1" method="post" action="${pageContext.request.contextPath}/Parent_Change_Password">
             <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
                </div>
                    <table class="table table-bordered mbn">
					  <tr>
					  	<td>
					  		<label class="col-lg-3 control-label">Old Password *</label>
					  	</td>
					  	<td><input type="password" name="Old_Password" id="Old_Password" class="form-control" required pattern=".{8,}" title="8 characters minimum" maxlength="50"></td>
					 </tr>	
					  <tr>
					  	<td>
					  		<label class="col-lg-3 control-label">New Password *</label>
					  	</td>
					  	<td><input type="password" name="New_Password" id="New_Password" class="form-control" required pattern=".{8,}" title="8 characters minimum" maxlength="50"></td>
					 </tr>
					  <tr>
					  	<td>
					  		<label class="col-lg-3 control-label">Confirm New Password *</label>
					  	</td>
					  	<td><input type="password" name="Confirm_New_Password" id="Confirm_New_Password" class="form-control" required pattern=".{8,}" title="8 characters minimum" maxlength="50"></td>
					 </tr>
					  <tr>
					  	<td colspan="2">
						  	<div class="buttonHolder">
						  		<input type="submit" class="btn btn-success mr10" value="Change Password">
						  		<input type="hidden" name="First_Login" value="${param.First_Login}">
          					<div class="requiredSignNote">Fields marked (*) are required</div>	
						  	</div>					  	
					  	</td>
					 </tr>		 			 			 	   		   
			 		</table>
              </div>
             </div>
            </form>
          </div>	
	        </div>
       </section>  
      <!-- End: Content -->
	  <%@include file="../../Student/includes/Adsense_Bottom.jsp" %>
      <!-- Begin: Page Scripts -->

		<%@include file="../../Student/includes/Page_Scripts.jsp" %>

      <!-- End: Page Scripts -->            
     </section>
	<c:if test="${Parent_Account_Details.parent_First_Login!=1}">	
    	<!-- Start: Right Sidebar -->
			<%@include file="../../Student/includes/Topbar_Right_Content.jsp" %>
    	<!-- End: Right Sidebar -->
    </c:if>
  </div>
  </body>
</html>