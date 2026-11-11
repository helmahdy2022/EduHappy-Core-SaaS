<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Payment Receipt</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${pageContext.request.contextPath}/_css/Admin_Main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/fullcalendar.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/datepicker.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/colorpicker.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-wysihtml5.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/uniform.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/select2.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-media.css" />
<link href="${pageContext.request.contextPath}/Global/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/jquery.gritter.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/jquery-ui.css" />
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
</head>
<body>
	<div>		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div>
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Payment Receipt</h5>
		        </div>
		        <div class="widget-content nopadding">
			  		<div class="message">
			  		${param.message}<br/>
			  		<button class="btn btn-danger" onclick="window.close()">Close</button>
			  		</div>
		        </div>
		     </div>	
		   </div>	
		 </div>
		</div>
	</div>
</body>
</html>