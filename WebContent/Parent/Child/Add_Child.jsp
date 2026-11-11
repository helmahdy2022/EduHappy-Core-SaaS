<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>New Child</title> 
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
 function showGrades(School_id)
 {
 if (School_id=="")
   {
	   document.getElementById("Grade").innerHTML="<option value=''>Select Grade</option>";
	   document.getElementById("Course").innerHTML="";
 	   document.getElementById("Fees").innerHTML="";
 	   fees=0;
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
	   sessionExpired();
       document.getElementById("Grade").innerHTML=xmlhttp.responseText;
	   document.getElementById("Course").innerHTML="";
 	   document.getElementById("Fees").innerHTML="";
 	   fees=0;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+School_id,true);
 xmlhttp.send();
 }
 
 function showCourses(str)
 {
 if (str=="")
   {
	   document.getElementById("Course").innerHTML="";
	   document.getElementById("Fees").innerHTML="";
	   fees=0;
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
	  sessionExpired();
     document.getElementById("Course").innerHTML=xmlhttp.responseText;
	   document.getElementById("Fees").innerHTML="";
 	   fees=0;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Courses_Checkbox?Grade_id="+str,true);
 xmlhttp.send();
 } 

function deleteFile()
{

 	resized_image = "";
 	document.getElementById("Photo").style="display:none";
 	document.getElementById("RemovePhoto").style="display:none";
 	document.getElementById('custom-file-upload').style.backgroundImage = 'url("${pageContext.request.contextPath}/images/Add_Photo.jpg")';
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
 </script>
 
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
fbq('init', '1040199250140879');
fbq('track', 'PageView');
</script>
<noscript><img height="1" width="1" style="display:none"
src="https://www.facebook.com/tr?id=1040199250140879&ev=PageView&noscript=1"
/></noscript>
<!-- End Facebook Pixel Code -->
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
            <li class="crumb-trail">Add Child</li>
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
                  <span class="panel-title">Add Child</span>
                </div>
  <!-- Add Child form -->
  <div id="content">
  		<div class="panel-body">
      			<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Payment"  onsubmit="return checkselectedsubject();">
									   		
 					
                  <div class="form-group">
                    <label class="col-lg-3 control-label">First Name *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">  
						<input type="text" name="First_Name" id="First_Name" class="form-control" required maxlength="50">	                      
                      </div>
                    </div>
                  </div>
                                    	
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Last Name *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">  
						<input type="text" name="Last_Name" id="Last_Name" class="form-control" required maxlength="50">	                      
                      </div>
                    </div>
                  </div>
                     <div class="form-group">
                    <label class="col-lg-3 control-label">Gender *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">
						<select name="Gender" id="Gender"  class="form-control" required>
						  <option value="">Select Gender</option>
						  <option > 
						  	Male
						  </option>
						   <option > 
						  	Female
						  </option>
						</select>                      
                      </div>
                    </div>
                  </div>                
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Mobile</label>
                    <div class="col-lg-8">
                      <div class="bs-component">  
						<input name="Mobile" type="text" class="form-control" id="Mobile" maxlength="100">		                      
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Date of birth *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">  	
						<input name="Birth_Date" type="Date" class="form-control" id="Birth_Date" required>	                      
                      </div>
                    </div>
                  </div>
 
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Username *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">  	
						<input name="Username" type="text" id="Username" class="form-control" required pattern=".{8,}" title="8 characters minimum" maxlength="50" onblur="checkUsername(this.value)">
						<div id="UsernameMsg" style="text-align: right"></div>                      
                      </div>
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Password *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">  
						<input name="Password" type="text" id="Password" class="form-control" required pattern=".{8,}" title="8 characters minimum" maxlength="50">								                      
                      </div>
                    </div>
                  </div> 
                  <div class="form-group">
                    <label class="col-lg-3 control-label">School Name *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">
			            <select name="School" id="School" class="form-control" onChange="showGrades(this.value);" required>
			 			 <option value=""> 
						  	Select School
						  </option>
						  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
					       	<option value="${School_id}"><c:out value='${Schools.school_Name[position.index]}'/></option>
					      </c:forEach>	  
						</select>                      
                      </div>
                    </div>
                  </div>
 
                   <div class="form-group">
                    <label class="col-lg-3 control-label">Grade Name *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">
			            <select name="Grade" id="Grade" class="form-control"  onChange="showCourses(this.value)" required>
			 			 <option value="">Select Grade</option> 
						</select>                      
                      </div>
                    </div>
                  </div>
                                              					                    		     
					<div id="Course">
			    
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
							 <button type="submit" id="Submit" name="Submit" value="Save" class="btn btn-success mr10"><i class="fa fa-money"></i> Proceed to Payment</button>
						<div class="requiredSignNote">Fields marked (*) are required</div>
					</div>
                    </div>
                  </div>  
				 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
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

</body>

</html>