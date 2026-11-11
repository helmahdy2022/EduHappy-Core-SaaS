<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!doctype html>
<html lang="en">  
<head>
	<title>Sign up Response</title><meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-login.css" />
	<link href="${pageContext.request.contextPath}/Global/font-awesome/css/font-awesome.css" rel="stylesheet" />
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
	<script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/jquery-1.11.3.js"></script> 	
	<script>
	<script type="text/javascript">
	     var capterra_vkey = '4b18fb315bf029b38d6be8c67af70ee3',
	     capterra_vid = '2106103',
	     capterra_prefix = (('https:' == document.location.protocol) ? 'https://ct.capterra.com' : 'http://ct.capterra.com');
	
	     (function() {
	       var ct = document.createElement('script'); ct.type = 'text/javascript'; ct.async = true;
	       ct.src = capterra_prefix + '/capterra_tracker.js?vid=' + capterra_vid + '&vkey=' + capterra_vkey;
	       var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ct, s);
	     })();	 
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
										class="breadcrumb_last" aria-current="page">Sign up</span></span></span></small>
							<h2>7-Day FREE trial</h2>
						</div>
					</div>
					<div class="nv-content-wrap entry-content">
				        <div id="loginbox">           
							 <div class="control-group normal_text" style="background-color:#fff"> 
								 <p style="margin-bottom: 15px;margin-top: 15px;padding-left: 30px;padding-right: 30px;text-align: center;font-size:1.3em; color:#000">
				  					${param.message}
								 </p>
							 </div>
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
</body>
</html>
