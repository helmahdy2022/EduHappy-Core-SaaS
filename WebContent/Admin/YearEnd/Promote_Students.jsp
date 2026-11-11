<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Year End Procedures</title>
</head>

<body>

<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Year End Procedures</a> <a href="${pageContext.request.contextPath}/Admin/YearEnd/Promote_Students.jsp" class="current">Promote Students</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Promote Students</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Promote Students</h5>
		        </div>
		        <div class="widget-content nopadding" id="PromoteStudents">
					<form name="form1" id="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Promote_Students">
		            <div class="control-group">
						This process will Promote Students to the appropriate Grade and to the next School Year.
						<br>The process may take several minutes...
						<br> Please select the school and click Proceed.
		            </div>	
		            <div class="control-group">
					  <label class="control-label">School Name</label>
		              <div class="controls"> 								              
				         <select name="School_id" id="School_id" class="span11" required>
							 <option value="0"> 
						  	Select School
						  </option>
						  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
					       	<option value="${School_id}"><c:out value='${Schools.school_Name[position.index]}'/></option>
					      </c:forEach>	  
						</select>
					  </div>
		            </div>	
 					<div id="Year_Name"></div>
		            <div class="form-actions">
		            <input type="button" id="Promote_Students_btn"  onclick="displayModal();" value="Promote Students" class="btn btn-success" >
		            </div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>
		   <div  class="span6">
		   	<div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Quick Hints</h5>
		        </div>
					This process will Promote Students to the appropriate Grade and to the next School Year.
					<br>The process may take several minutes...
					<br> Please select the school and click Proceed.   
		   			<hr>
		   </div>
			<div class="widget-box">
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
			</div>	
		   </div>	
		 </div>
		</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>

<!--Footer-part-->
<div class="row-fluid">
<div class="footer">  <%@include file="../includes/AdminFooter.jsp" %></div></div>

<!-- The Modal -->
	<div id="promoteStudentsModal" class="modal" style="display:none">
		<!-- Modal content -->
		<div class="modal-content" style="text-align:center;">
		<span class="closePromoteStudents" style="float:right;cursor: pointer;">×</span>
		    	<div style="width:100%;max-height: calc(100vh - 250px);overflow-y: auto;">  
		    	<h1>Promote Students</h1>
		    	You are about to Promote Students from the current year to the next year.<br/><br/> You can't undo the changes made after this process.<br/><br/>
		    	Please Confirm to proceed<br/><br/>	
		    	<input type="submit" id="Confirm_Promote_Students" name="Confirm_Promote_Students" class="btn btn-danger" value="Confirm" 
		    	onClick="hideModal(); document.getElementsByName('form1')[0].submit();" onclick="checkSession();">
		    	<br/><br/>
		    	</div>
		</div>
	</div>

<script>
// Get the modal
var modal = document.getElementById('promoteStudentsModal');

// Get the button that opens the modal
//var btn = document.getElementById("myBtn");
var link = document.getElementById("Promote_Students_btn");
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("closePromoteStudents")[0];

// When the user clicks the button, open the modal
/* btn.onclick = function() {
    modal.style.display = "block";
} */

//Promote_Students_btn.onclick = function() {
//    modal.style.display = "block";
//}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
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