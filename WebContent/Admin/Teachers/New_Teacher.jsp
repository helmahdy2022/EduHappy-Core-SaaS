<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>New Teacher</title> 
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/exif.js"></script>  
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/ProcessPhoto.js"></script>  
 <script>
 
 function getCheckedBoxes(school) {
	 var checkboxes = document.getElementsByClassName(school);
	 var checkboxesChecked = [];
	 // loop over them all
	 for (var i=0; i<checkboxes.length; i++) {
	    // And stick the checked ones onto an array...
	    if (checkboxes[i].checked) {
	       checkboxesChecked.push(checkboxes[i]);
	    }
	 }
	 // Return the array if it is non-empty, or null
	 return checkboxesChecked.length > 0 ? checkboxesChecked : null;
	 }

 function validateSchoolCheckbox()
 {
	 var checkedBoxes = getCheckedBoxes("school");
	 if(checkedBoxes==null)
		 {
		 	document.getElementById("validateMessage").innerHTML="<font color='red'>Please select at least one school</font>";
		 	return false;
		 }
	 else
		 {
		 	document.getElementById("validateMessage").innerHTML="";
		 }
		 
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
	<div  id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Teachers</a> <a href="${pageContext.request.contextPath}/Admin/Students/New_Teacher.jsp" class="current">New Teacher</a> </div>
		  <h1>New Teacher</h1>
		</div>
		<!--End-breadcrumbs-->	
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Teacher</h5>
		        </div>
		        <div class="widget-content nopadding">
    			<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Teacher">
		            <div class="control-group">
					  <label class="control-label">Teacher Photo</label>
		              <div class="controls">
						<div id="spinner"> </div>
						 <canvas id="canvas" width="200" height="200" style="border: 1px solid #ccc; display:none;"></canvas>			
						<label id="custom-file-upload">			   
						  <img id="Photo" name="Photo" width="200px" style="display:none;"/>						  
						   <input type="file" name="Entity_Photo" id="Entity_Photo" class="upload" onchange="readImage();">	   					   
						</label>
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
						  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
					       	<input type="checkbox" name="school${School_id}" id="school${School_id}" class="school" value="${School_id}"> 
					       	<c:out value='${Schools.school_Name[position.index]}'/>
					      <br>
					      <input type="hidden" name="hiddenschool${School_id}"  id="hiddenschool${School_id}" value="${School_id}">  
					      </c:forEach>	
					       <div id="validateMessage"></div>   
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
						<input name="PO_Box" type="number" id="PO_Box" class="span11"  min="0" max="1000000000">	 					
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Full Address *</label>
		              <div class="controls">
						<input name="Full_Address" type="text" id="Full_Address" class="span11" required maxlength="255">	 								
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
	 				    <div><font size="2"><i>Teacher must change this password at the first login</i></font></div>
	 				  </div>	 				  			
 					</div> 	 
 					
 					<font size="2" color="red"><b><i>Note: Teacher will use these username and password for login. To login as a Teacher <a href="${pageContext.request.contextPath}/Teacher/Teacher_Login.jsp" target=_blank>Click Here</a></i></b></font>
 									 										 									 					 					 					 																									 					
		            <div class="form-actions">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success" onclick="return validateSchoolCheckbox();">
		            </div>
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>
		   <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Important Notes</h5>
		        </div>
		       	<div class="widget-content nopadding">
					After you create the teacher profile, you will need to do the following three steps to give authority to the class teacher (who takes attendance, gives assignments, grade exams,...etc):<br/>				
					<ul>
					<li>
						Assign Grades to the teacher. You can do this from Teacher > Assign Grade.
					</li>
					<li>
						Assign Classes to the teacher. You can do this from Teacher > Assign Class.
					</li>												
					<li>
						Assign Courses to the teacher. You can do this from Teacher > Assign Course.
					</li>
					</ul>
		    		After you create the teacher profile, you will need to do the following two steps if you would like to give authority to teacher to upload content (like eBooks, lessons, standard assignments,...etc) for a certain course:<br/>
					<ul>
					<li>
						Assign Grades to the teacher. You can do this from Teacher > Assign Grade.
					</li>
					<li>
						Assign Content to the teacher. You can do this from Teacher > Assign Content.
					</li>												
					</ul>
		        </div>
		      </div>
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
		formData.append("Teacher_Photo1", resized_image); 
		if(document.getElementById("OkToSubmit").value=='OK')
		{
			$.ajax({
	        	type: "POST",
	            url: "${pageContext.request.contextPath}/Manage_Teacher",
	            data: formData,
	            processData: false,
	            contentType: false,
	        })
           .done(function(data) {
              $("#content").empty().append($(data).find('#content'));
           });
	        event.preventDefault(); 
		}
    });
});
</script>
</body>
</html>