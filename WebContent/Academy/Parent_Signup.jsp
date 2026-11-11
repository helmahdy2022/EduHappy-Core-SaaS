<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <title>New Parent Account</title>
  <meta name="keywords" content="Parent Signup" />
  <meta name="description" content="Parent New Account">
  <meta name="author" content="Eduhappy Academy">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <link href="${pageContext.request.contextPath}/_css/Student_Main.css" rel="stylesheet" type="text/css"> 
  <!-- Font CSS (Via CDN) -->
  <link rel='stylesheet' type='text/css' href='https://fonts.googleapis.com/css?family=Open+Sans:300,400,600'>

  <!-- Theme CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/theme.css">

  <!-- Admin Forms CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/admin-forms.css">


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


</script>
</head>

<body  onload="listCountries()" class="external-page sb-l-c sb-r-c">
  <!-- For Demo Purposes - Theme Settings Pane -->

  <!-- End: Theme Settings Pane -->
  <!-- Start: Main -->
  <div id="main" class="animated fadeIn">

    <!-- Start: Content-Wrapper -->
    <section id="content_wrapper">

      <!-- begin canvas animation bg -->
      <div id="canvas-wrapper">
        <canvas id="demo-canvas"></canvas>
      </div>

      <!-- Begin: Content -->
      <section id="content" class="">

        <div class="admin-form theme-info mw700" style="margin-top: 3%;" id="login1">

          <div class="row mb15 table-layout">

            <div class="col-xs-6 va-m pln">
				 <a href="https://edu-happy.com/academy">
				 <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo">
				 </a>	
            </div>

            <div class="col-xs-6 text-right va-b pr5">
              <div class="login-links">
                <a href="${pageContext.request.contextPath}/Parent/Parent_Login.jsp" class="" title="Sign In">Sign In</a>
                <span class="text-white"> | </span>
                <a href="pages_register.html" class="active" title="Register">Register</a>
              </div>

            </div>

          </div>

          <div class="panel panel-info mt10 br-n">
            <form method="post" action="${pageContext.request.contextPath}/Manage_Parent_Account" name="form1" id="form1" class="form-vertical" >
              <div class="panel-body p25 bg-light">
                <div class="section-divider mt10 mb40">
                  <span>Set up your Parent account</span>
                </div>
                <!-- .section-divider -->

                <div class="section row">
                  <div class="col-md-6">
                    <label for="firstname" class="field prepend-icon">
                      <input type="text" name="First_Name" id="First_Name" class="gui-input" placeholder="First Name *" required maxlength="50">
                      <label for="firstname" class="field-icon">
                        <i class="fa fa-user"></i>
                      </label>
                    </label>
                  </div>
                  <!-- end section -->

                  <div class="col-md-6">
                    <label for="lastname" class="field prepend-icon">
                      <input type="text" name="Last_Name" id="Last_Name" class="gui-input" placeholder="Last Name *" required maxlength="50">
                      <label for="lastname" class="field-icon">
                        <i class="fa fa-user"></i>
                      </label>
                    </label>
                  </div>
                  <!-- end section -->
                </div>
                <!-- end .section row section -->

                <div class="section">
                  <label for="email" class="field prepend-icon">
                   <input type="email" name="Email" id="Email" class="gui-input" placeholder="Email *" required maxlength="255"/>
                    <label for="email" class="field-icon">
                      <i class="fa fa-envelope"></i>
                    </label>
                  </label>
                </div>
                <!-- end section -->

                <div class="section row">
                  <div class="col-md-6">
                    <label for="firstname" class="field prepend-icon">
                      <select name="Country_id" id="Country_id" style="height:42px; width:100%"  class="gui-select" required><option value="">Select Country</option></select> 
                    </label>
                  </div>
                  <!-- end section -->

                  <div class="col-md-6">
                    <label for="lastname" class="field prepend-icon">
                     <input type="text" name="City" id="City" required maxlength="255" placeholder="City *" required maxlength="100" class="gui-input" /> 
                      <label for="lastname" class="field-icon">
                        <i class="fa fa-city"></i>
                      </label>
                    </label>
                  </div>
                  <!-- end section -->
                </div>

                <div class="section">
                    <label for="username" class="field prepend-icon">
                      <input type="text" name="Mobile" id="Mobile" class="gui-input" placeholder="Mobile *" required maxlength="100"/>
                      <label for="username" class="field-icon">
                        <i class="fa fa-mobile"></i>
                      </label>
                    </label>

                  <!-- end .smart-widget section -->
                </div>

                <div class="section">
                    <label for="username" class="field prepend-icon">
                      <input type="text" name="Username" id="Username" class="gui-input" placeholder="Username *" required pattern=".{8,}" title="8 characters minimum" maxlength="255" onblur="checkUsername(this.value)" />
                      <label for="username" class="field-icon">
                        <i class="fa fa-user"></i>
                      </label>
                    </label>
					<div id="UsernameMsg" style="height:0px;"></div>
                  <!-- end .smart-widget section -->
                </div>
                <!-- end section -->

                <div class="section">
                  <label for="password" class="field prepend-icon">
                    <input type="password" name="Password" id="Password" class="gui-input" placeholder="Password *" required pattern=".{8,}" title="8 characters minimum" maxlength="255" autocomplete="off"/>
                    <label for="password" class="field-icon">
                      <i class="fa fa-unlock-alt"></i>
                    </label>
                  </label>
                </div>
                <!-- end section -->


                <div class="section-divider mv40">
                  <span>Review the Terms</span>
                </div>
                <!-- .section-divider -->
 				<span>
 				By creating an account, you agree to our
 				<a href="${pageContext.request.contextPath}/Account/Terms_and_Conditions.jsp" target=_blank>Terms and Conditions</a> and 
 				<a href="${pageContext.request.contextPath}/Account/Privacy_Policy.jsp" target=_blank>Privacy Policy</a></p> 
 				<input type="hidden" name="Terms" id="Terms" value="on">
 				</span>
               <!-- end section -->
              </div>
              <!-- end .form-body section -->
              <div class="panel-footer clearfix">
                <button type="submit" class="button btn-primary pull-right">Create Account</button>
              </div>
              <!-- end .form-footer section -->
 		 		<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
            </form>
          </div>
        </div>

      </section>
      <!-- End: Content -->
      <!-- Begin: Page Footer -->

		<%@include file="../../Student/includes/Page_Scripts.jsp" %>

      <!-- End: Page Footer -->      
    </section>
    <!-- End: Content-Wrapper -->

  </div>
  <!-- End: Main -->

  <!-- BEGIN: PAGE SCRIPTS -->

  <!-- jQuery -->


  <!-- Page Javascript -->
  <script type="text/javascript">
  jQuery(document).ready(function() {
    "use strict";
    // Init Theme Core      
    Core.init();

    // Init Demo JS
    Demo.init();

    // Init CanvasBG and pass target starting location
    CanvasBG.init({
      Loc: {
        x: window.innerWidth / 2.1,
        y: window.innerHeight / 4.2
      },
    });
  });
  
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

  <!-- END: PAGE SCRIPTS -->

</body>

</html>
