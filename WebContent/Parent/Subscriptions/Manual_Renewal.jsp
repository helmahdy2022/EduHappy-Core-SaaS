<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Renew My Children Subscriptions</title> 
 
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
            <li class="crumb-trail">Subscriptions Manual Renewal</li>
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
                  <span class="panel-title">Manual Overdue Subscriptions Renewal</span>
                </div>
  <!-- Add Child form -->
  <div id="content"> 
  		<div class="panel-body">
	             <c:choose>
	             <c:when test="${not empty ChildRenewal.student_id}">      			                                      					                    		     
	             <c:forEach items="${ChildRenewal.student_id}" var="Student_id"  varStatus="position">
	                   	<form name="form${Student_id}" method="post" id="form${Student_id}" class="form-horizontal" action="${pageContext.request.contextPath}/Manual_Renewal">   
				         	<div class="form-group">
				                 <label class="col-lg-3 control-label"></label>
				                 <div class="col-lg-8">
				                   <div class="bs-component"> 					                    		     
						    		<div class="mb5">            
					        			<table  class="table table-bordered mbn">
					        			<tr>
					        			<td width="50%">
						                  <label>
											Child Name: <c:out value='${ChildRenewal.first_Name[position.index]}'/>&nbsp;<c:out value='${ChildRenewal.last_Name[position.index]}'/>						                  
						                  </label>					        			
					        			</td>
					        			<td width="50%">
					        				<button type="submit" id="Submit${Student_id}" name="Submit" value="Save" class="btn btn-success mr10"><i class="fa fa-money"></i> Manually renew subscriptions</button>						
					        			</td>
					        			</tr>
					        			<c:forEach var="Courses" items="${ChildRenewal.dueRenewal[Student_id]}">
					         			<tr>
					         			<td></td>
					         			<td>
					         			<label for="Course${Courses[0]}">
					         			<c:out value='${Courses[1]}'/> - Price: <c:out value='${Courses[2]}'/> EGP /4 weeks - Due Date: <c:out value='${Courses[3]}'/> 
					           			</label>
					           			</td>
					           			</tr>
					           			<br/>
					         			</c:forEach>
					         			</table>	
					         						    
							 		</div>					   		
				                   </div>
				                 </div>                 
				            </div>
				            

                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
					<div style="text-align:center" >						
					</div>
                    </div>
                  </div>  
                 <input type="hidden" name="Student_id${Student_id}" id="Student_id${Student_id}" value="${Student_id}"/>
				 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
	             </c:forEach>
				</c:when>
				<c:otherwise>
				<div>No overdue subscription</div>
				</c:otherwise>
				</c:choose>				    
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