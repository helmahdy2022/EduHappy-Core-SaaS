<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    
<head>
<title>EduHappy Production</title><meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-login.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Global/font-awesome/css/font-awesome.css" />
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/jquery-1.11.3.js"></script> 
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-75892673-4"></script>
<script>
window.dataLayer = window.dataLayer || [];
function gtag(){dataLayer.push(arguments);}
gtag('js', new Date());

gtag('config', 'UA-75892673-4');
</script>

		 <script>
		
		 function listCountries()
		 { 
		 if (window.XMLHttpRequest)
		   {// code for IE7+, Firefox, Chrome, Opera, Safari
		   xmlhttp=new XMLHttpRequest();
		   }
		 else
		   {// code for IE6, IE5
		   xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		   }
		 xmlhttp.onreadystatechange=function()
		   {
		      if (xmlhttp.readyState==4 && xmlhttp.status==200)
		     {
				  document.getElementById("Country_id").innerHTML=xmlhttp.responseText;
		     }
		   };
		 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Countries",true);
		 xmlhttp.send();
		 }
		 function checkUsername(Username)
		 {
		 if (Username=="")
		   {
			   document.getElementById("UsernameMsg").innerHTML="";
		   return;
		   } 
		 if (window.XMLHttpRequest)
		   {// code for IE7+, Firefox, Chrome, Opera, Safari
		   xmlhttp=new XMLHttpRequest();
		   }
		 else
		   {// code for IE6, IE5
		   xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		   }
		 xmlhttp.onreadystatechange=function()
		   {
		      if (xmlhttp.readyState==4 && xmlhttp.status==200)
		     {
		     document.getElementById("UsernameMsg").innerHTML=xmlhttp.responseText;
		     }
		   };
		 xmlhttp.open("GET","${pageContext.request.contextPath}/Check_Username?Username="+Username,true);
		 xmlhttp.send();
		 }
 
		 $(function(){
			    //$("#form1").validate();    
			    $("#form1").on('submit', function(e){
			        if(document.getElementById("OkToSubmit").value!='OK')
			        {
			            e.preventDefault();
			        }
			    });
			});


		 </script>


</head>
<body onload="listCountries();" style="background-color:#eeeeee">
<c:if test="${param.Entity=='Admin'}">
<jsp:forward page="/Admin/Admin_Login.jsp?mode=Demo"/>
</c:if>
<c:if test="${param.Entity=='Teacher'}">
<jsp:forward page="/Teacher/Teacher_Login.jsp?mode=Demo"/>
</c:if>
<c:if test="${param.Entity=='Student'}">
<jsp:forward page="/Student/Student_Login.jsp?mode=Demo"/>
</c:if>
<c:if test="${param.Entity=='Parent'}">
<jsp:forward page="/Parent/Parent_Login.jsp?mode=Demo"/>
</c:if>
<%-- <c:if test="${param.Entity==null}">
<c:redirect url="http://edu-happy.com/free-demo-2"/>
</c:if> --%>
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
						<p></p>
						<div id="loginbox">
							<form name="form1" id="form1" method="post" class="form-vertical" action="${pageContext.request.contextPath}/Manage_Account">
								<div style="display: flex;">
								<div class="control-group" style="width:100%;">
									<div class="controls" style="display: flex;">
									<div style="width:25%; font-size: 15px; font-weight: bold;"><span >First Name</span></div>
										<div class="main_input_box" style="float:right; width:75%;">
											
											<input
												type="text" name="First_Name" id="First_Name"
												placeholder="First Name" required maxlength="50" />
										</div>
									</div>
								</div>
								<div class="control-group" style="width:100%;">
									<div class="controls" style="display: flex;">
										<div style="width:25%; font-size: 15px; font-weight: bold;"><span>Last Name</span></div>
										<div class="main_input_box" style="float:right; width:75%;">
											<input
												type="text" name="Last_Name" id="Last_Name"
												placeholder="Last Name" required maxlength="50" />
										</div>
									</div>
								</div>
								</div>
								<div style="display: flex;">
								<div class="control-group" style="width:100%;">
									<div class="controls" style="display: flex;">
										<div style="width:25%; font-size: 15px; font-weight: bold;"><span>Country</span></div>									
										<div class="main_input_box" style="float:right; width:75%; background-color:#fff">
											<select
												name="Country_id" id="Country_id"
												required><option value="">Select Country</option></select>
										</div>
									</div>
								</div>
								<div class="control-group" style="width:100%;">
									<div class="controls" style="display: flex;">
										<div style="width:25%; font-size: 15px; font-weight: bold;"><span>Organization</span></div>	
										<div class="main_input_box" style="float:right; width:75%;">
											<input
												type="text" name="Organization" id="Organization" required
												maxlength="255" placeholder="Organization" required />
										</div>
									</div>
								</div>
								</div>
								<div style="display: flex;">
								<div class="control-group" style="width:100%;">
									<div class="controls" style="display: flex;">
										<div style="width:25%; font-size: 15px; font-weight: bold;"><span>Username</span></div>									
										<div class="main_input_box" style="float:right; width:75%;">
											<input type="email" name="Email" id="Email"
												placeholder="E-mail (Username)" required maxlength="50"
												onblur="checkUsername(this.value)" />
											<div id="UsernameMsg" style="height: 0px;"></div>
										</div>
									</div>
								</div>
								<div class="control-group" style="width:100%;">
									<div class="controls" style="display: flex;">
										<div style="width:25%; font-size: 15px; font-weight: bold;"><span>Password</span></div>
										<div class="main_input_box" style="float:right; width:75%;">
											<input
												type="password" name="Password" id="Password"
												placeholder="Password" required pattern=".{8,}"
												title="8 characters minimum" maxlength="50"
												autocomplete="off" />
										</div>
									</div>
								</div>
								</div>
								<p
									style="margin-bottom: 15px; margin-top: 15px; margin-left: 40px; padding-left: 30px; padding-right: 30px; text-align: left; color: #000;">
									By creating an account, you agree to our <a
										href="${pageContext.request.contextPath}/Account/Terms_and_Conditions.jsp"
										target=_blank>Terms and Conditions</a> and <a
										href="${pageContext.request.contextPath}/Account/Privacy_Policy.jsp"
										target=_blank>Privacy Policy</a>
								</p>
								<input type="hidden" name="Terms" id="Terms" value="on">
								<div class="form-actions" style="text-align: center;">
									<input type="submit" value="Sign up" class="btn btn-success">
								</div>
								<div
									style="text-align: left; margin-left: 40px; padding-left: 30px;">
									<p style="color: #000;">
										Already have an account? <a
											href="${pageContext.request.contextPath}/Admin/Admin_Login.jsp"
											style="color: red;">Login</a>
									</p>
								</div>
								<input type="hidden" name="csrfPreventionSalt"
									value="<c:out value='${csrfPreventionSalt}'/>" />
							</form>
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



