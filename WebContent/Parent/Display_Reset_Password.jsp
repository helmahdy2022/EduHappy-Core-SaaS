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
		<link href="${pageContext.request.contextPath}/Global/font-awesome/css/font-awesome.css" rel="stylesheet" />
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
 		<script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/jquery-1.11.3.js"></script> 	
		<script>
		  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');
		
		  ga('create', 'UA-75892673-3', 'auto');
		  ga('send', 'pageview');
		
		</script>
		 <script>
		 </script>

    </head>
    <body>
		<c:choose>
		<c:when test="${sessionScope['Parent_Session']!=null&&param.loggedIn!=true}">
			<jsp:forward page="/ParentLogin"/>
		</c:when>
		<c:otherwise>
		   <div id="loginbox">           
			 <div class="control-group normal_text"> 
			 <h3>
			 <a href="https://edu-happy.com/academy">
			 <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo" />
			 </a>
			 </h3>
			 <div style="height:150px;"></div>
			 <p style="margin-bottom: 15px;margin-top: 15px;padding-left: 30px;padding-right: 30px;text-align: center;font-size:1.3em">
				${param.message}
			 </p>
			 </div>
		    </div> 
		</c:otherwise>
		</c:choose>    
    </body>
</html>

