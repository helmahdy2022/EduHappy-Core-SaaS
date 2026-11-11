<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <title>Parent Account Message</title>
  <meta name="keywords" content="Parent Signup Message" />
  <meta name="description" content="Parent Account Mesage">
  <meta name="author" content="Eduhappy Academy">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="${pageContext.request.contextPath}/_css/Student_Main.css" rel="stylesheet" type="text/css"> 
  <!-- Font CSS (Via CDN) -->
  <link rel='stylesheet' type='text/css' href='https://fonts.googleapis.com/css?family=Open+Sans:300,400,600'>

  <!-- Theme CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/theme.css">

  <!-- Admin Forms CSS -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/admin-forms.css">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/summernote.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/icomoon.css">

<script async src="https://www.googletagmanager.com/gtag/js?id=UA-75892673-4"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-75892673-4');
</script>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-75892673-3', 'auto');
  ga('send', 'pageview');

</script>

</head>

<body class="external-page external-alt sb-l-c sb-r-c">

  <!-- Start: Main -->
  <div id="main" class="animated fadeIn">

    <!-- Start: Content-Wrapper -->
    <section id="content_wrapper">

      <!-- Begin: Content -->
      <section id="content">

        <div class="center-block mt70" style="max-width: 625px">
          <div class="row table-layout">

            <div class="col-xs-7 pln">
  			<c:choose>
  			<c:when test="${fn:contains(message, 'New Parent account created successfully')}">
               <h2 class="text-dark mbn confirmation-header"><i class="fa fa-check text-success"></i> Account Created</h2>	
				<!-- Facebook Pixel Code -->
				<script>
				!function(f,b,e,v,n,t,s)
				{if(f.fbq)return;n=f.fbq=function(){n.callMethod?
				n.callMethod.apply(n,arguments):n.queue.push(arguments)};
				if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
				n.queue=[];t=b.createElement(e);t.async=!0;
				t.src=v;s=b.getElementsByTagName(e)[0];
				s.parentNode.insertBefore(t,s)}(window, document,'script',
				'https://connect.facebook.net/en_US/fbevents.js');
				fbq('init', '631865571535028');
				fbq('track', 'CompleteRegistration');
				</script>
				<noscript><img height="1" width="1" style="display:none"
				src="https://www.facebook.com/tr?id=631865571535028&ev=CompleteRegistration&noscript=1"
				/></noscript>
				<!-- End Facebook Pixel Code -->               		
  			</c:when>
  			<c:otherwise>
                 <h2 class="text-dark mbn confirmation-header"><i class="fa fa-times"></i> Account not created</h2>
  			</c:otherwise>
  			</c:choose>
            </div>
          </div>

          <!-- Confirmation Panel -->
          <div class="panel mt15">
              <div class="panel-body pt30 p25 pb15">          

                <p class="lead">Hello Parent,</p>

                <hr class="alt short mv25">

                <p class="lh25 text-muted fs15">
                ${message}
                </p>              
              </div>
          </div>
        </div>

      </section>
      <!-- End: Content -->
		<%@include file="../../Student/includes/Page_Scripts.jsp" %>
    </section>
    <!-- End: Content-Wrapper -->

  </div>
  <!-- End: Main -->

  <!-- Page Javascript -->
  <script type="text/javascript">
  jQuery(document).ready(function() {

    "use strict";

    // Init Theme Core      
    Core.init();

    // Init Demo JS
    Demo.init();

  });
  </script>

  <!-- END: PAGE SCRIPTS -->

</body>

</html>
