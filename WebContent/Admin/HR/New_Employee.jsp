<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>New Employee</title> 
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
function getAllPayrolls()
{
	var SchoolList = []
	var checkboxes = document.querySelectorAll('input[type=checkbox]:checked')

	for (var i = 0; i < checkboxes.length; i++) {
		SchoolList.push(checkboxes[i].value)
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
    	document.getElementById("Payroll_id").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_All_Payrolls?SchoolList="+SchoolList,true);
xmlhttp.send();
}

function getDepartments(Payroll_id)
{
	if (Payroll_id=="")
	  {
		   document.getElementById("Department_id").innerHTML="";
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
    	document.getElementById("Department_id").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Departments_By_Payroll?Payroll_id="+Payroll_id,true);
xmlhttp.send();
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

function getCheckbobValues()
{
	var array = []
	var checkboxes = document.querySelectorAll('input[type=checkbox]:checked')

	for (var i = 0; i < checkboxes.length; i++) {
	  array.push(checkboxes[i].value)
	}
	alert(array);
}
 
 </script>
</head>

<!-- <body onload="getAllPayrolls();"> -->
<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
<div  id="main">
	<div  id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">HR and Payroll</a> <a href="${pageContext.request.contextPath}/Admin/HR/New_Employee.jsp" class="current">New Employee</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>New Employee</h1>
		</div>
		<!--End-breadcrumbs-->	
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Employee</h5>
		        </div>
		        <div class="widget-content nopadding">
    			<form name="form1" method="post" id="form1" class="form-horizontal" action="">
		            <div class="control-group">
					  <label class="control-label">Employee Photo</label>
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
					       	<input type="checkbox" name="school${School_id}" id="school${School_id}" class="school" value="${School_id}" onchange="getAllPayrolls()"> 
					       	<c:out value='${Schools.school_Name[position.index]}'/>
					      <br>
					      <input type="hidden" name="hiddenschool${School_id}"  id="hiddenschool${School_id}" value="${School_id}">  
					      </c:forEach>	
					       <div id="validateMessage"></div>   
					  </div>
		            </div>	            
  					<div class="control-group">
					  <label class="control-label">Payroll</label>
		              <div class="controls">								  
						 <select name="Payroll_id" id="Payroll_id" class="span11" onchange="getDepartments(this.value);">
						  <option value=""> 
						  	Select Payroll
						  </option>
			  			</select> 					   					  
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Department</label>
		              <div class="controls">								  
						 <select name="Department_id" id="Department_id" class="span11">
						  <option value=""> 
						  	Select Department
						  </option>
			  			</select> 					   					  
 					  </div>
 					</div>
					<div class="control-group">
					  <label class="control-label">Designation</label>
		              <div class="controls">	
            			<input type="text" name="Designation" id="Designation" class="span11" maxlength="100">				
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Date of joining *</label>
		              <div class="controls">
            			<input name="Joining_Date" type="Date" class="span11" id="Joining_Date" required>			
 					  </div>
 					</div> 
		            <div class="control-group">
		              <label class="control-label">Initial Basic Salary *</label>
		              <div class="controls"> 
	 					<input type="number" id="Initial_Basic" name="Initial_Basic" class="span11" required value="" step="0.01" min="0" max="1000000000">                
		              </div>
		            </div> 
		            <div class="control-group">
		              <label class="control-label">Employee Group *</label>
		              <div class="controls">
					  <input type="radio" name="Employee_Group" id="Teacher" value="Teacher" checked>Teacher<br>		              
					  <input type="radio" name="Employee_Group" id="Admin" value="Admin">Admin<br>	                
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
	 				    <div><font size="2"><i>For Teachers, Teacher must change this password at the first login</i></font></div>
	 				  </div>	 				  			
 					</div> 	 
 					
 					<%-- <font size="2" color="red"><b><i>Note: Employee will use these username and password for login. To login as a Teacher <a href="${pageContext.request.contextPath}/Teacher/Teacher_Login.jsp" target=_blank>Click Here</a></i></b></font> --%>
 									 										 									 					 					 					 																									 					
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
					After you create the employee profile and if the employee is a teacher, you will need to do the following three steps to give authority to the class teacher (who takes attendance, gives assignments, grade exams,...etc):<br/>				
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
				<div class="widget-box">
			  	 <%@include file="../includes/Adsense_Inline.jsp" %>
				</div>	
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
		//formData.append("Teacher_Photo1", resized_image); 
		formData.append("Employee_Photo1", resized_image); 
		if(document.getElementById("OkToSubmit").value=='OK')
		{
        	if(document.getElementById("Teacher").checked==true)
    		{
    			address="${pageContext.request.contextPath}/Manage_Teacher";
    		}
    	else
    		{
    			address="${pageContext.request.contextPath}/Manage_Admin";
    		}
			$.ajax({
	        	type: "POST",
	        	url: address,
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