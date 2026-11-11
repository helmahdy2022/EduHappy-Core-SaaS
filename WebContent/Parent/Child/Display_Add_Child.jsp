<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Add Child</title>
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
	              <a href="${pageContext.request.contextPath}/Parent/Child/Add_Child.jsp">Add Child</a>
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
                <span class="fa fa-user"></span>Add Child</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Add Child</code>
              </div>
            </div>
				 	 <c:if test="${not empty param.message}">
				  		<div class="message">${param.message}
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
						fbq('track', 'Purchase');
						</script>
						<noscript><img height="1" width="1" style="display:none"
						src="https://www.facebook.com/tr?id=631865571535028&ev=Purchase&noscript=1"
						/></noscript>
						<!-- End Facebook Pixel Code -->				  		
				  		</div> 
				 	 </c:if>  			            
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