<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Cancel Subscription</title>

<script>
function cancelSubscription()
{
	document.getElementById("Cancel_Supscription").innerHTML=
		"<div style='width:10%; margin: 0 auto; height:300px;line-height:300px;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
		
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
	  sessionExpired();
			  document.getElementById("Cancel_Supscription").innerHTML=xmlhttp.responseText;
	     }
	   };
	 xmlhttp.open("GET","${pageContext.request.contextPath}/Cancel_Supscription",true);
	 xmlhttp.send();
}

</script>

</head>
<body>
<c:if test="${sessionScope['Admin_Session']==null}">
<jsp:forward page="/Admin/Admin_Login.jsp"/>
</c:if>

<div>
	<%@include file="../Admin/includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../Admin/includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Settings</a> <a href="${pageContext.request.contextPath}/Account/Cancel_Subscription.jsp" class="current">Cancel Subscription</a> </div>
		  <h1>Cancel Subscription</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Cancel Subscription</h5>
		        </div>
		        <div class="widget-content nopadding" id="Cancel_Supscription">				
		            <div class="control-group">
						<font size="3">Cancel Subscription will cancel the autobilling. <br/><br/>
						If you cancel the subscription, your credit card will not be charged automatically at the end of your current subscription, hence the subscription will not be automatically renewed.<br/><br/>
						After cancellation, you will still be able to access the system until the next renewal date and if you would like to keep accessing the system after that date you will need to subscribe again. <br/><br/>
						You will always be able to renew your subscription after cancellation through your new subscription link.<br/><br/>
						If you still need to cancel your subscription, please click on "Cancel Subscription" button below.<br/><br/>
						</font>
		            </div>
		            <div class="form-actions">
		            <input type="button" id="Cancel_Subscription_btn" class="btn btn-danger" onclick="displayModal();" value="Cancel Subscription">
		            </div>			
		        </div>
		     </div>	
		   </div>	
		 </div>
		</div>
	</div>
	<div class="rightContent"><%@include file="../Admin/includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"> <%@include file="../Admin/includes/AdminFooter.jsp" %></div></div>


<!-- The Modal -->
	<div id="cancelSubscriptionModal" class="modal" style="display:none;">
		<!-- Modal content -->
		<div class="modal-content" style="text-align:center;">
		<span class="close" onclick="modal.style.display = 'none';">x</span>
		    	<div style="width:100%;max-height: calc(100vh - 250px);overflow-y: auto;">  
		    	<h1>Cancel Subscription</h1>
		    	You are about to cancel your subscription<br/><br/>
		    	Please Confirm to proceed<br/><br/>	
		    	<input type="button" id="Confirm_Cancel_Subscription" name="Confirm_Cancel_Subscription" class="btn btn-danger" value="Confirm" onClick="cancelSubscription();hideModal();"><br/><br/>
		    	</div>
		</div>
	</div>

<script>
// Get the modal
var modal = document.getElementById('cancelSubscriptionModal');

// Get the button that opens the modal
//var btn = document.getElementById("myBtn");
var link = document.getElementById("Cancel_Subscription_btn");
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
/* btn.onclick = function() {
    modal.style.display = "block";
} */

Cancel_Subscription_btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
    alert()
}

// When the user clicks anywhere outside of the modal, close it
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
</script>	
</body>
</html>