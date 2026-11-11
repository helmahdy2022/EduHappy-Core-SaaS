<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Manage My Child Subscriptions</title> 
 <script>
 window.addEventListener( "pageshow", function ( event ) {
	  var historyTraversal = event.persisted || 
	                         ( typeof window.performance != "undefined" && 
	                              window.performance.navigation.type === 2 );
	  if ( historyTraversal ) {
	    // Handle page restore.
	    window.location.reload();
	  }
	});
var fees=0;
 function calculateFees(course_id, course_price)
 {
 if (course_id=="")
   {
 	   document.getElementById("Fees").innerHTML="Select Subject";
   return;
   } 

 if (document.getElementById('Course'+course_id).checked) 
 {
	 fees = fees+ course_price;
 } else 
 {
	 fees = fees - course_price;
 }
 if(fees!=0)
 	document.getElementById("Fees").innerHTML="<b>Total fees to be paid="+fees+" EGP. This will be renewed every 4 weeks";
 else
	 document.getElementById("Fees").innerHTML="";
 return fees;
 }
 
function checkselectedsubject()
{	    
	if (fees==0) 
	{
		document.getElementById("Fees").innerHTML="<b><i><font color='red'>You must select at least one subject</font></b></i>";
	return false;
	}	
}
function unsubscribeCourse(course_id, student_id)
{
if (course_id=="")
  {
	   document.getElementById("Unsubscribe"+course_id).innerHTML="";
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
 	 document.getElementById("Unsubscribe"+course_id).innerHTML=xmlhttp.responseText;
  }
};
xmlhttp.open("GET","${pageContext.request.contextPath}/Unsubscribe_Course?Course_id="+course_id+"&&Student_id="+student_id,true);
xmlhttp.send();
}
function resubscribeCourse(course_id, student_id)
{
if (course_id=="")
  {
	   document.getElementById("Unsubscribe"+course_id).innerHTML="";
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
 	 document.getElementById("Unsubscribe"+course_id).innerHTML=xmlhttp.responseText;
  }
};
xmlhttp.open("GET","${pageContext.request.contextPath}/Resubscribe_Course?Course_id="+course_id+"&&Student_id="+student_id,true);
xmlhttp.send();
}

 </script>
 <meta name="viewport" content="width=device-width, initial-scale=1.0" />

</head>
<body class="dashboard-page sb-l-o sb-r-c">

<!-------------------------------------------------------------+ 
  <body> Helper Classes: 
---------------------------------------------------------------+ 
  '.sb-l-o' - Sets Left Sidebar to "open"
  '.sb-l-m' - Sets Left Sidebar to "minified"
  '.sb-l-c' - Sets Left Sidebar to "closed"

  '.sb-r-o' - Sets Right Sidebar to "open"
  '.sb-r-c' - Sets Right Sidebar to "closed"
---------------------------------------------------------------+
 Example: <body class="example-page sb-l-o sb-r-c">
 Results: Sidebar left Open, Sidebar right Closed
--------------------------------------------------------------->

  <!-- For Demo Purposes - Theme Settings Pane -->
	<%@include file="../../Student/includes/Theme_Settings.jsp" %>
  <!-- End: Theme Settings Pane -->

  <!-- Start: Main -->
  <div id="main">

<%@include file="../../Student/includes/ParentMenu.jsp" %>
<%@include file="../../Student/includes/ParentLeft.jsp" %>
    <!-- Start: Content-Wrapper -->
    <section id="content_wrapper">

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
            <li class="crumb-trail">Manage Subscriptions</li>
          </ol>
        </div>
		<%@include file="../../Student/includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
	  <%@include file="../../Student/includes/Adsense_Top.jsp" %>
      <!-- Begin: Content -->
      <section id="content" class="animated fadeIn">
        <!-- Admin-panels -->
        <div class="admin-panels fade-onload">

          <div class="row">
            <div class="col-md-12 admin-grid">
              <div class="panel sort-disable" id="p0">
                <div class="panel-heading">
                  <span class="panel-title">Manage Subscriptions</span>
                </div>
  <!-- Add Child form -->
  <div id="content"> 
  		<div class="panel-body">
      			<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Payment"  onsubmit="return checkselectedsubject();">                                            					                    		     
	         	<c:if test="${not empty SubscribedCourses.course_id}">
	         	<label >Following are the subjects that your child  
	             <c:forEach items="${child.student_id}" var="Student_id"  varStatus="position">
					<c:if test="${Student_id == param.Student_id}">	             
	                  	 <font style="color:blue"><c:out value='${child.first_Name[position.index]}'/>&nbsp;<c:out value='${child.last_Name[position.index]}'/></font>		             			
					</c:if>
	             </c:forEach>
	         	is enrolled in. You can click "Cancel Subscription" button next to the subject that you want to Unsubscribe from:</label>
	         	<div class="form-group">
	                 <label class="col-lg-3 control-label"></label>
	                 <div class="table-responsive">
	                   <div class="bs-component"> 					                    		     
			    		<div class="mb5">            
		        			<table  class="table table-bordered mbn">
							<tr align="center" class="scheduleSession"><td>Subject Name</td><td>Group Name</td><td>Subscription Fees</td><td>Running until</td><td>Subscription Renewal Date</td><td>Action</td></tr>
		        			<c:forEach items="${SubscribedCourses.course_id}" var="Course_id" varStatus="position">	
		         			<tr align="center">
		         			<td>
		         			<label for="Course${Course_id}">
		         			<c:out value='${SubscribedCourses.course_Name[position.index]}'/>
		           			</label>
		           			</td>
		         			<td>
		         			<label>
		         			<c:out value='${SubscribedCourses.group_Name[position.index]}'/>
		           			</label>
		           			</td>
			         		<td>
		         			<label>
		         			<c:out value='${SubscribedCourses.course_Price[position.index]}'/> EGP /month
		           			</label>
		           			</td>
			         		<td>
		         			<label>
		         			<c:out value='${SubscribedCourses.course_end_date[position.index]}'/>
		           			</label>
		           			<c:choose>
		           			<c:when test="${SubscribedCourses.course_Canceled[position.index]==0}">		         			
								<br><i>Will be extended 4 weeks after renewal unless canceled</i>
		           			</c:when>
		           			<c:otherwise>
		           				<br><i>Your child will continue until the above date</i>
		           			</c:otherwise>
		           			</c:choose>	 		           			
		           			</td>
		         			<td>
		           			<c:choose>
		           			<c:when test="${SubscribedCourses.course_Canceled[position.index]==0}">		         			
		         			<label>
		         			<c:out value='${SubscribedCourses.payment_renewal_date[position.index]} '/>
		           			</label>
		           			</c:when>
		           			<c:otherwise>
		           			Canceled
		           			</c:otherwise>
		           			</c:choose>	           			
		           			</td>
		           			<td>
		           			<div class="buttonHolder">
		           			<c:choose>
		           			<c:when test="${SubscribedCourses.course_Canceled[position.index]==0}">
							 <button type="button" id="Cancel${Course_id}" name="Cancel${Course_id}" value="Cancel Subscription" class="btn btn-sm btn-danger" onclick="displayModal(${Course_id});"><i class="fa fa-ban"></i> Cancel Subscription</button>
		           			</c:when>
		           			<c:otherwise>
							 <button type="button" id="Cancel${Course_id}" name="Cancel${Course_id}" value="Subscription Canceled" class="btn btn-sm btn-danger" disabled ><i class="fa fa-ban"></i> Subscription Canceled</button>
							 <button type="button" id="Undo${Course_id}" name="Undo${Course_id}" value="Undo" class="btn btn-sm btn-success" onclick="displayModalUndo(${Course_id});">Undo</button>		           			
		           			</c:otherwise>
		           			</c:choose>
		           			</div>
		           			</td>
		           			</tr>
		           			<tr><td colspan="5"><div id="Unsubscribe${Course_id}"></div></td></tr>
		         			</c:forEach>
		         			</table>				    
				 		</div>					   		
	                   </div>
	                 </div>                 
	            </div>
	            <hr>
	            </c:if>
	         <c:if test="${not empty Courses.course_id}">
	         	<label >Following are the subjects that your child is not enrolled in. You can select one or more to subscribe for and then click Proceed to Payment:</label>
	         	<div class="form-group">

	                 <label class="col-lg-3 control-label"></label>
	                 <div class="col-lg-8">
	                   <div class="bs-component"> 					                    		     
			    		<div class="checkbox-custom mb5">            

		        			<c:forEach items="${Courses.course_id}" var="Course_id" varStatus="position">	
		         						<input type="checkbox" name="Course${Course_id}" value="${Course_id}" id="Course${Course_id}" onChange="calculateFees(this.value, ${Courses.course_Price[position.index]});"/>
					         			<label for="Course${Course_id}">
					         			<c:out value='${Courses.course_Name[position.index]}'/> - Price: <c:out value='${Courses.course_Price[position.index]}'/> EGP /4 weeks
					           			</label>		         				
		         				
		           			<br/>
		         			</c:forEach>		         							    
				 		</div>					   		
	                   </div>
	                 </div>                 
	            </div>
                 <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
                      <div class="bs-component">  
						<div id="Fees"></div>	                      
                      </div>
                    </div>
                  </div>	             					 					
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
					<div style="text-align:center" >
							 <c:if test="${Parent_Account_Details.has_saved_token==true}">
							 Important Note: Our billing system will collect the fees from your subscribed payment method. Payment will be done immediately without asking for credit card details.<br> 
							 </c:if>
							 <input type="hidden" name="Student_id" value="${param.Student_id}">							
							 <button type="submit" id="Submit" name="Submit" value="Save" class="btn btn-success mr10"><i class="fa fa-money"></i> Proceed to Payment</button>
					</div>
                    </div>
                  </div>  
				 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				 </c:if>
				    </form>
  				</div>
  </div>
  <!-- end of Add Child Form -->
              </div>
            </div>
            <!-- end: .col-md-12.admin-grid -->

          </div>
          <!-- end: .row -->

          <!-- end: .row -->

        </div>

      </section>
      <!-- End: Content -->
	  <%@include file="../../Student/includes/Adsense_Bottom.jsp" %>
      <!-- Begin: Page Footer -->
      <footer id="content-footer">
		<%@include file="../../Student/includes/ParentFooter.jsp" %>
		<%@include file="../../Student/includes/Page_Scripts.jsp" %>
      </footer>
      <!-- End: Page Footer -->

    </section>
    <!-- End: Content-Wrapper -->

    <!-- Start: Right Sidebar -->
			<%@include file="../../Student/includes/Topbar_Right_Content.jsp" %>
    <!-- End: Right Sidebar -->

  </div>
  <!-- End: Main -->
<!-- The Modal -->
	<div id="myModal" class="modal" style="display:none;">
		<!-- Modal content -->
		<div class="modal-content" style="text-align:center;">
		<span class="close">x</span>
		    	<div style="width:100%;max-height: calc(100vh - 250px);overflow-y: auto;">  
		    	<h1>Confirm to Proceed</h1>
		    	You are about to unsubscribe from the selected subject.<br/><br/>
		    	Please Confirm to proceed<br/><br/>	
				<input type="button" id="Confirm_Delete" name="Confirm_Delete" class="btn btn-danger" value="Confirm to Proceed" onclick="hideModal();    
				var input = document.createElement('input');
				input.type = 'hidden';
			    input.name = 'ConfirmDelete';
			    input.value = 'Confirm to Proceed';">
				<br/><br/>
		    	</div>
		</div>
	</div>
	
		<div id="myModalUndo" class="modal" style="display:none;">
		<!-- Modal content -->
		<div class="modal-content" style="text-align:center;">
		<span class="closeUndo">x</span>
		    	<div style="width:100%;max-height: calc(100vh - 250px);overflow-y: auto;">  
		    	<h1>Confirm to Proceed</h1>
		    	You are about to undo subscription cancellation and return back to subscribed state.<br/><br/>
		    	Please Confirm to proceed<br/><br/>	
				<input type="button" id="Confirm_Delete" name="Confirm_Delete" class="btn btn-success" value="Confirm to Proceed" onclick="hideModalUndo();    
				var input = document.createElement('input');
				input.type = 'hidden';
			    input.name = 'ConfirmDelete';
			    input.value = 'Confirm to Proceed';">
				<br/><br/>
		    	</div>
		</div>
	</div>
<script>
var modal = document.getElementById('myModal');
var modalUndo = document.getElementById('myModalUndo');
var span = document.getElementsByClassName("close")[0];
var spanUndo = document.getElementsByClassName("closeUndo")[0];
span.onclick = function() {
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
spanUndo.onclick = function() {
    modalUndo.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modalUndo) {
        modalUndo.style.display = "none";
    }
}
var course_id=0;
function displayModal(selected_course_id)
{
	modal.style.display = "block";
	course_id= selected_course_id;
}
function hideModal()
{
	modal.style.display = "none";
	unsubscribeCourse(course_id, ${param.Student_id});
	course_id=0;
}

function displayModalUndo(selected_course_id)
{
	modalUndo.style.display = "block";
	course_id= selected_course_id;
}
function hideModalUndo()
{
	modalUndo.style.display = "none";
	resubscribeCourse(course_id, ${param.Student_id});
	course_id=0;
}
</script>

</body>

</html>