<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>New Student</title> 
 
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/exif.js"></script>  
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/ProcessPhoto.js"></script>  

 <script>

 function showGrades(School_id)
 {
 if (School_id=="")
   {
	   document.getElementById("Grade").innerHTML="<option value=''>Select Grade</option>";
	   document.getElementById("Class").innerHTML="<option value=''>Select Class</option>";
	   document.getElementById("Route_id").innerHTML="<option value=''>Select Route</option>";
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
	   document.getElementById("Class").innerHTML="<option value=''>Select Class</option>";
	   showRoutes(School_id);
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+School_id,true);
 xmlhttp.send();
 }
 
 function showClasses(str)
 {
 if (str=="")
   {
	   document.getElementById("Class").innerHTML="<option value=''>Select Class</option>";
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
     document.getElementById("Class").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Classes?Grade_id="+str,true);
 xmlhttp.send();
 }
 
 function showRoutes(School_id)
 {
 if (School_id=="")
   {
	   document.getElementById("Route_id").innerHTML="<option value=''>Select Route</option>";
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
     document.getElementById("Route_id").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_School_Routes?School_id="+School_id,true);
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
 
 </script>
</head>

<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
<div id="main">
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Students</a> <a href="${pageContext.request.contextPath}/Admin/Students/New_Student.jsp" class="current">New Student</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>New Student</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Student</h5>
		        </div>
		        <div class="widget-content nopadding">
    			<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Student" enctype="multipart/form-data">
		            <div class="control-group">
					  <label class="control-label">Student Photo</label>
		              <div class="controls">
							<div id="spinner"> </div>
							 <canvas id="canvas" width="200" height="200" style="border: 1px solid #ccc; display:none;"></canvas>			
							<Label id="custom-file-upload">			   
							  <img id="Photo" name="Photo" width="200px" style="display:none;"/>
							  
							   <input type="file" name="Entity_Photo" id="Entity_Photo" class="upload" onchange="readImage();">	   					   
							</Label>
							<br>
							<div style="text-align:center; width:200px;">							
							<a href="#" id="RemovePhoto" onclick="deleteFile();">
							</a>  								         
							</div>     
					  </div>
		            </div>	  
		            <div class="control-group">
					  <label class="control-label">School Name *</label>
		              <div class="controls">
			            <select name="School" id="School" class="span11" onChange="showGrades(this.value);" required>
			 			 <option value=""> 
						  	Select School
						  </option>
						  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
					       	<option value="${School_id}"><c:out value='${Schools.school_Name[position.index]}'/></option>
					      </c:forEach>	  
						</select>  								              
					  </div>
		            </div>	            
					<div class="control-group">
					  <label class="control-label">Grade Name *</label>
		              <div class="controls"> 
				         <select name="Grade" id="Grade" class="span11" onChange="showClasses(this.value)" required>
				 			 <option value="">Select Grade</option>
				 		</select> 			 		 						
 					  </div>
 					</div>	
					<div class="control-group">
					  <label class="control-label">Classroom Name *</label>
		              <div class="controls">
				         <select name="Class" id="Class" class="span11" required>
				 			 <option value="">Select Class</option>
				 		</select> 						
 					  </div>
 					</div>
					<div class="control-group">
					  <label class="control-label">First Name *</label>
		              <div class="controls">
            			<input type="text" name="First_Name" id="First_Name" class="span11" required maxlength="50">					
 					  </div>
 					</div>
 					<div class="control-group">
					  <label class="control-label">Last Name *</label>
		              <div class="controls">
            			<input type="text" name="Last_Name" id="Last_Name" class="span11" required maxlength="50">				
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Country of Nationality*</label>
		              <div class="controls">
						 <select name="Country_id" id="Country_id" class="span11" required>
						  <option value=""> 
						  	Select Country
						  </option>
						  <c:forEach items="${Countries.country_id}" var="Country_id" varStatus="position">
					       	<option value="${Country_id}"><c:out value='${Countries.country_Name[position.index]}'/></option>
					      </c:forEach>
			  			</select>			
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">City *</label>
		              <div class="controls">
	 					<input type="text" name="City" id="City" class="span11"  maxlength="100" required>			
 					  </div>
 					</div>
   					<div class="control-group">
					  <label class="control-label">ZIP Code / PO Box</label>
		              <div class="controls">
	 					<input name="PO_Box" type="number" class="span11" id="PO_Box" min="0" max="1000000000">		
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Full Address *</label>
		              <div class="controls">
	 					<input name="Full_Address" type="text" class="span11" id="Full_Address" required maxlength="255">			
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Gender *</label>
		              <div class="controls">
						<select name="Gender" id="Gender"  class="span11" required>
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
  					<div class="control-group">
					  <label class="control-label">Email</label>
		              <div class="controls">
	 					<input name="Email" type="email" class="span11" id="Email" maxlength="255">			
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Tel</label>
		              <div class="controls">
	 					<input name="Tel" type="text" class="span11" id="Tel" maxlength="100">			
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Mobile</label>
		              <div class="controls">
	 					 <input name="Mobile" type="text" class="span11" id="Mobile" maxlength="100">			
 					  </div>
 					</div> 	
  					<div class="control-group">
					  <label class="control-label">Date of birth *</label>
		              <div class="controls">
            			<input name="Birth_Date" type="Date" class="span11" id="Birth_Date" required>			
 					  </div>
 					</div>
   					<div class="control-group">
					  <label class="control-label">Transport - Route Name</label>
		              <div class="controls">
						 <select name="Route_id" id="Route_id" class="span11">
						  <option value=""> 
						  	Select Route
						  </option>
			  			</select>			
 					  </div>
 					</div>					
  					<div class="control-group">
					  <label class="control-label">Username *</label>
		              <div class="controls">
						<input name="Username" type="text" id="Username" class="span11" required pattern=".{8,}" title="8 characters minimum" maxlength="50" onblur="checkUsername(this.value)">
					  </div>
          				<div id="UsernameMsg" style="text-align: right"></div>			
 					</div>
  					<div class="control-group">
					  <label class="control-label">Password *</label>
		              <div class="controls">
	 					<input name="Password" type="text" id="Password" class="span11" required pattern=".{8,}" title="8 characters minimum" maxlength="50">
	 				    <div><font size="2"><i>Student must change this password at the first login</i></font></div>
	 				  </div>	 				  			
 					</div> 	 
 					
 					<font size="2" color="red"><b><i>Note: Student will use these username and password for login. To login as a Student <a href="${pageContext.request.contextPath}/Student/Student_Login.jsp" target=_blank>Click Here</a></i></b></font>
 									 										 									 					 					 					 																									 					
		            <div class="form-actions">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>	
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
		 </div>
		</div>
	</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
<script>
$(document).ready(function() {
	$('form').submit(function(event) {
		event.preventDefault();
		var formData = new FormData(document.getElementsByName('form1')[0]);      
		formData.append("Student_Photo1", resized_image); 
		
		if(document.getElementById("OkToSubmit").value=='OK')
			{
				$.ajax({
		        	type: "POST",
		            url: "${pageContext.request.contextPath}/Manage_Student",
		            data: formData,
		            processData: false,
		            contentType: false,
		        })
	           .done(function(data) {
	              $("#main").empty().append($(data).find('#content'));
	              $('html, body').animate({ scrollTop: 0 }, 0);
	           });
		        event.preventDefault(); 
			}
    });
});
</script>
</body>
</html>