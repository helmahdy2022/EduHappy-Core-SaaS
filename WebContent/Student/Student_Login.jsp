<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">   
<head>
        <title>Student Login</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-login.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/Global/font-awesome/css/font-awesome.css" />
		<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
 		<script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/jquery-1.11.3.js"></script> 
        <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/js/matrix.login.js"></script>

		<!-- Global site tag (gtag.js) - Google Analytics -->
		<script async src="https://www.googletagmanager.com/gtag/js?id=UA-75892673-4"></script>
		<script>
		  window.dataLayer = window.dataLayer || [];
		  function gtag(){dataLayer.push(arguments);}
		  gtag('js', new Date());
		
		  gtag('config', 'UA-75892673-4');
		</script>
</head>
<body style="background-color:#eeeeee">
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
										class="breadcrumb_last" aria-current="page">Student Login</span></span></span></small>
							<h2>Student Login</h2>
						</div>
					</div>
					<div class="nv-content-wrap entry-content">
		<c:choose>
		<c:when test="${sessionScope['Student_Session']!=null&&param.loggedIn!=true}">
			<jsp:forward page="/StudentLogin"/>
		</c:when>
		<c:otherwise>
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
		            <form name="form1" style="width:100%" id="loginform" method="post" class="form-vertical"  action="${pageContext.request.contextPath}/StudentLogin"">
		                <div class="control-group">
					<div style="width:100%; font-size: 13px; line-height: 40px;">
						<span >You need first to create <u>student profile</u> with username and password from Admin portal in order to be able to login to Student portal here</span>
					</div>

		                    <div class="controls" style="display: flex;">
								<div style="width:10%; font-size: 15px; font-weight: bold; line-height: 40px;"><span >Username</span></div>
		                        <div class="main_input_box" style="float:right; width:75%;">
		  						<input type="text" name="username" id="username" placeholder="Username" required maxlength="255"  <c:if test="${param.mode=='Demo'}">value="student1"</c:if>/>                      </div>
		                    </div>
		                </div>
		                <div class="control-group">
		                    <div class="controls" style="display: flex;">
								<div style="width:10%; font-size: 15px; font-weight: bold;line-height: 40px;"><span >Password</span></div>
		                        <div class="main_input_box" style="float:right; width:75%;">
									<input type="password" name="password" id="password" required placeholder="Password" autocomplete="off" <c:if test="${param.mode=='Demo'}">value="password"</c:if>/>		                        </div>
		                    </div>
		                </div>
		                <c:if test="${param.mode=='Demo'}">
		                Username: student1 <br/>
		                Password: password
		                </c:if>
		                <input type="hidden" name="url" value="${param.url}">
		                <div class="form-actions" style="text-align:center;">
		                <input type="submit" value="Login" class="btn btn-success">
		                </div>               
		 		 		<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
		            </form>				
				</c:when>
				<c:otherwise>
					<p style="margin-bottom: 10px;margin-top: 15px;padding-left: 30px;padding-right: 30px;text-align: center; color:#000; font-size:1.2em">${param.message}	</p>			
				</c:otherwise>
				</c:choose>
		        </div> 		
		</c:otherwise>
		</c:choose>
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
</body>
</html>
