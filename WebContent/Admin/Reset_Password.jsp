<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">   
<head>
        <title>Reset Password</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-login.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/Global/font-awesome/css/font-awesome.css" />
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
 		<script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/jquery-1.11.3.js"></script> 
        <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/js/matrix.login.js"></script>
</head>
<body style="background-color:#eeeeee">
	<c:choose>
	<c:when test="${sessionScope['Admin_Session']!=null&&param.loggedIn!=true}">
		<jsp:forward page="/AdminLogin"/>
	</c:when>
	<c:otherwise>
		<div class="wrapper">
			<%@include file="../Admin/includes/Signup_Header.jsp" %>
			<main id="content" class="neve-main" role="main">
			<div class="container single-page-container">
				<div class="row">
					<div class="nv-single-page-wrap col">
						<div class="nv-page-title-wrap nv-big-title">
							<div class="nv-page-title ">
								<small class="nv--yoast-breadcrumb neve-breadcrumbs-wrapper"><span><span><a
											href="https://edu-happy.com/">Home</a> » <span
											class="breadcrumb_last" aria-current="page">Reset Password</span></span></span></small>
								<h2>Reset Password</h2>
							</div>
						</div>
						<div class="nv-content-wrap entry-content">
					        <div id="loginbox">           
								<c:choose>
								<c:when test="${param.loggedIn!=true}">

										<c:if test="${param.message!=null}">
										 <div class="control-group normal_text" style="margin-top: 30px; background-color:#fff"> 
											<div style="margin-bottom: 10px; text-align: left; color:red; height:0px;">
											${param.message}
											</div>
										 </div>
										</c:if>              
						            <form name="form1" id="loginform" method="post" class="form-vertical"  action="${pageContext.request.contextPath}/Reset_Password">
						                <div class="control-group">
						                    <div class="controls" style="display: flex;">
						                    	<div style="width:20%; font-size: 15px; font-weight: bold; line-height: 40px;"><span >New Password</span></div>
						                        <div class="main_input_box" style="float:right; width:75%;">
						                            <input type="password" name="New_Password" id="New_Password" pattern=".{8,}" title="8 characters minimum" required placeholder="New Password" autocomplete="off"/>
						                        </div>
						                    </div>
						                </div>
						                <div class="control-group">
						                    <div class="controls" style="display: flex;">
						                    	<div style="width:20%; font-size: 15px; font-weight: bold; line-height: 40px;"><span >Confirm Password</span></div>
						                        <div class="main_input_box" style="float:right; width:75%;">
						                            <input type="password" name="Confirm_New_Password" id="Confirm_New_Password" pattern=".{8,}" title="8 characters minimum" required placeholder="Confirm New Password" autocomplete="off"/>
						                        </div>
						                    </div>
						                </div>
						                <div class="form-actions" style="text-align:center;">
						                <span class="pull-right"><input type="submit" value="Save" class="btn btn-success"></span>
						                </div>               
							 			<input type="hidden" name="Admin_id" value="<c:out value='${param.Admin_id}'/>"/>
							 			<input type="hidden" name="Forget_Password_Token" value="<c:out value='${param.Forget_Password_Token}'/>"/>
						 		 		<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
						            </form>				
								</c:when>
								<c:otherwise>
									<p style="margin-bottom: 10px;margin-top: 15px;padding-left: 30px;padding-right: 30px;text-align: center; color:#000; font-size:1.2em">${param.message}	</p>			
								</c:otherwise>
								</c:choose>
					        </div>
						</div>
					</div>
				</div>
			</div>
			</main>
			<%@include file="../Admin/includes/Signup_Footer.jsp" %>
		</div> 
	 
	<!-- Start of HubSpot Embed Code -->
	<script type="text/javascript" id="hs-script-loader" async defer src="//js-eu1.hs-scripts.com/24991813.js"></script>
	<!-- End of HubSpot Embed Code -->   
	</c:otherwise>
	</c:choose>      
</body>
</html>
