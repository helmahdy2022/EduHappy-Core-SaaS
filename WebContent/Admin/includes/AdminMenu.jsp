<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="pathparts" value="${fn:split(pageContext.request.servletPath, '/')}" />
<c:set var="Directory_Name" value="${pathparts[fn:length(pathparts) - 2]}" /> 
<c:set var="Page_Name" value="${pathparts[fn:length(pathparts) - 1]}" /> 
<c:if test="${Account_Details.account_Owner==0 && !fn:contains(pageContext.request.servletPath, 'Admin_Main.jsp')}">
  <c:set var="Forward" value="Yes" />
  <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    <c:if test="${Featur_Name==Directory_Name}">
		<c:set var="Forward" value="No" />
    </c:if>
   </c:forEach>
    <c:if test="${Page_Name=='Account_Profile.jsp' || Page_Name=='Display_Account_Profile.jsp' || Directory_Name=='Messages'}">
    	<c:set var="Forward" value="No" />
    </c:if>   
   <c:if test="${Forward=='Yes'}">
	<jsp:forward page="/Admin/Admin_Main.jsp"/>
   </c:if>
</c:if>

<c:if test="${Account_Details.subscription_Valid==false && Account_Details.trial_Valid==false}">
	<jsp:forward page="/AdminLogin"/>
</c:if>
<html>
<head>
<style>
.blink_me {
  animation: blinker 2s linear infinite;
}

@keyframes blinker {
  30% {
    opacity: 0;
  }
}
</style>	
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-75892673-4"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-75892673-4');
</script>
<script>

	
var timeoutID;
function setup() {
    this.addEventListener("mousemove", resetTimer, false);
    this.addEventListener("mousedown", resetTimer, false);
    this.addEventListener("keypress", resetTimer, false);
    this.addEventListener("DOMMouseScroll", resetTimer, false);
    this.addEventListener("mousewheel", resetTimer, false);
    this.addEventListener("touchmove", resetTimer, false);
    this.addEventListener("MSPointerMove", resetTimer, false);
    startTimer();
}
setup();
 
function startTimer() {
    // wait 2 seconds before calling goInactive
    timeoutID = window.setTimeout(goInactive,   '${pageContext.session.maxInactiveInterval}000');
}
 
function resetTimer(e) {
    window.clearTimeout(timeoutID);
 
    goActive();
}
 
function goInactive() {
    // do something
	window.location = "${pageContext.request.contextPath}/Admin/Admin_Login.jsp";
}
 
function goActive() {
    // do something
         
    startTimer();
}


 function sessionExpired()
 {
	  if(xmlhttp.responseText=='SessionExpired')
		  {
	      	window.location.href = "${pageContext.request.contextPath}/Admin/Admin_Login.jsp";
		  }
 }
 function checkSession()
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
 			if(xmlhttp.responseText=='SessionExpired')
 			{
 				window.location.href = "${pageContext.request.contextPath}/Admin/Admin_Login.jsp";
 			}
 	     }
 	  };
 	xmlhttp.open("GET","${pageContext.request.contextPath}/Check_Session?User=Admin",true);
 	xmlhttp.send();
 }
 
</script>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${pageContext.request.contextPath}/_css/Admin_Main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/dataTables.bootstrap.min.css" />
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

<!--Header-part-->
<div id="header">
  <h1><a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp">EduHappy</a></h1>
</div>
<!--close-Header-part--> 
<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li class="">
    	<a title="" href="${pageContext.request.contextPath}/Initiate_Profile" onclick="overlayOn();"><i class="icon icon-user"></i> <span class="text">Welcome ${Account_Details.first_Name} <b class="caret"></b></span></a>
    </li>
    <li class="">
    	<a title="" href="${pageContext.request.contextPath}/Admin/Messages/Initiate_Admin_Messages.jsp" onclick="overlayOn();"><i class="icon icon-envelope"></i> <span class="text">Messages <span class="label label-important">${sessionScope['newAdminMessagesCount']}</span> <b class="caret"></b></span></a>
    </li>
     <li class="">
    	<a title="" href="${pageContext.request.contextPath}/Teacher/Teacher_Login.jsp" target=_blank><i class="icon icon-user" style="color:orange;"></i> <span class="text">Teacher Login</span></a>
    </li>
        <li class="" >
    	<a title="" href="${pageContext.request.contextPath}/Student/Student_Login.jsp" target=_blank><i class="icon icon-user" style="color:orange;"></i> <span class="text">Student Login</span></a>
    </li>      
    <li class="">
    	<a title="" href="${pageContext.request.contextPath}/Parent/Parent_Login.jsp" target=_blank><i class="icon icon-user" style="color:orange;"></i> <span class="text">Parent Login</span></a>
    </li>   
 		<c:choose>
		<c:when test="${Account_Details.subscription_Valid==true && Account_Details.account_Owner==1}">	
    	<li class="">
			<a title="" href="${pageContext.request.contextPath}/Subscription_Details" onclick="overlayOn();"><i class="icon icon-credit-card"></i> <span class="text">Upgrade Subscription<b class="caret"></b></span></a>
		</li>
    	<li class="">		
			<a title="" href="${pageContext.request.contextPath}/Get_Licenses_Details" onclick="overlayOn();"><i class="icon icon-info-sign"></i> <span class="text">Licenses Details<b class="caret"></b></span></a>						
		</li>
		<li class="">
			<a title="" href="${pageContext.request.contextPath}/Account/Cancel_Subscription.jsp" onclick="overlayOn();"><i class="icon icon-remove-circle"></i> <span class="text">Cancel Subscription<b class="caret"></b></span></a>
		</li>
		</c:when>
		<c:otherwise>
		<c:if test="${Account_Details.account_Owner==1 }">
    	<li class="">
    	
			<a title="" href="${pageContext.request.contextPath}/Subscription_Details" onclick="overlayOn();"><i class="icon icon-credit-card"></i> <span class="text" style="font-weight: bold; color: yellow">Subscribe Now<b class="caret"></b></span></a>
    	</li> 
    	</c:if>
		</c:otherwise>
		</c:choose>     	
     <li class="">
    	<a title="" href="${pageContext.request.contextPath}/Admin/Admin_Signout.jsp" onclick="overlayOn();"><i class="icon icon-share-alt"></i> <span class="text">Logout</span></a>
    </li>    
  </ul>
</div>

<!--close-top-Header-menu-->
<!--start-top-serch-->
 <c:if test="${Account_Details.subscription_Valid==false}">	
 <div id="search">
  <input type="text" placeholder="Search here..."/>
  <button type="submit" class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
</div> 
</c:if>
<!--close-top-serch-->
<div id="overlay">
  <div id="text"><img src="${pageContext.request.contextPath}/images/Loader.gif"></div>
</div>


<!-- The Modal -->
	<div id="myModal" class="modal" style="display:none;">
		<!-- Modal content -->
		<div class="modal-content" style="text-align:center;">
		<span class="close">x</span>
		    	<div style="width:100%;max-height: calc(100vh - 250px);overflow-y: auto;">  
		    	<h1>Confirm to Proceed</h1>
		    	You are about to delete this record from the database. This action is unrecoverable.<br/><br/>
		    	Please Confirm to proceed<br/><br/>	
				<input type="button" id="Confirm_Delete" name="Confirm_Delete" class="btn btn-danger" value="Confirm to Proceed" 
				onclick="hideModal();    
				var input = document.createElement('input');
				input.type = 'hidden';
			    input.name = 'ConfirmDelete';
			    input.value = 'Confirm to Proceed';
			    document.getElementsByName('form1')[0].appendChild(input);
			    document.getElementsByName('form1')[0].submit();">
				<br/><br/>
		    	</div>
		</div>
	</div>
<script>
var modal = document.getElementById('myModal');
var span = document.getElementsByClassName("close")[0];
span.onclick = function() {
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
function displayModal()
{
	modal.style.display = "block";
}
function hideModal()
{
	modal.style.display = "none";
}

function overlayOn() {
    document.getElementById("overlay").style.display = "block";
}
</script>


 
</body>
</html>