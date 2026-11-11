<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Edit Employee</title>  
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/exif.js"></script>  
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/ProcessPhoto.js"></script> 

<meta charset="utf-8">
 <script>
 
 function showDeleteMessage()
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
	  sessionExpired();
		      document.getElementById("DeleteMessage").innerHTML=xmlhttp.responseText;
		      //document.getElementById("OneSession").innerHTML="";
		      }
		    };
		  xmlhttp.open("GET","${pageContext.request.contextPath}/Global/DeleteMessage.jsp",true);
		  xmlhttp.send();

 }
 function cancelDelete()
 {
     document.getElementById("DeleteMessage").innerHTML= "";

 }

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
 
 function photoOnPageLoad()
 {
	  document.getElementById('custom-file-upload').style.backgroundImage ='url("${pageContext.request.contextPath}/uploadFiles/Accounts/${Admin_Details.account_id}/Admins/Photos/${Admin_Details.admin_id}/${Admin_Details.fileName}")';
 }
 
 function uploadPhoto(){               
	 	 
	 	var formData = new FormData(document.getElementsByName('form1')[0]);      
     formData.append("Admin_Photo1", resized_image);  
     var frm = $('#form1');
         $.ajax({
             type: "POST",
             url: "${pageContext.request.contextPath}/Manage_Admin",
             data: formData,
             processData: false,
             contentType: false,
         });
}

 function deleteFile(File_id, Admin_id, File_Name, Account_id, School_id)
 {
 if (Admin_id=="" && File_Name=="")
   {
   document.getElementById("Photo").innerHTML="";
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

   	resized_image = "";
   	document.getElementById("Photo").style="display:none";
   	document.getElementById("RemovePhoto").style="display:none";
 	  document.getElementById('custom-file-upload').style.backgroundImage = 'url("${pageContext.request.contextPath}/images/Add_Photo.jpg")';;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Delete_File?File_id="+File_id+"&&File_Name="+File_Name+"&&Account_id="+Account_id+"&&School_id="+School_id+"&&EntityIdName=Admin_id&&Entity_id="+Admin_id+"&&Directory=Admins", true);
 xmlhttp.send();
 }

 function checkUsername(Username, Current_Username)
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
if(Username!=Current_Username)
	{
	 	xmlhttp.open("GET","${pageContext.request.contextPath}/Check_Username?Username="+Username,true);
	 	xmlhttp.send();
	}
else
	{
		var formData = document.forms['form1'];
	    var input = document.createElement('input');
	    input.type = 'hidden';
	    input.name = 'OkToSubmit';
	    input.id = 'OkToSubmit';
	    input.value = 'OK';
	    formData.appendChild(input);
	}
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
 </script>
</head>

<body onload="<c:if test="${not empty Admin_Details.fileName}">photoOnPageLoad();</c:if>">
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
<div  id="main">
	<div  id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">HR and Payroll</a> <a href="${pageContext.request.contextPath}/List_All_Employees" class="current">Edit Employee</a> </div>
		  <h1>Edit Employee</h1>
		</div>
		<!--End-breadcrumbs-->	
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Edit Employee</h5>
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
						<a href="#" id="RemovePhoto" onclick="deleteFile(${Admin_Details.admin_id}, ${Admin_Details.admin_id}, '${Admin_Details.fileName}', ${Admin_Details.account_id}, ${Admin_Details.school_id});">
						<c:if test="${not empty Admin_Details.fileName}">
							Remove Photo
						</c:if>
						</a>
						</div>			

					  </div>
		            </div>	
		            <c:if test="${Admin_Details.account_Owner!=1}">
		            <div class="control-group">
					  <label class="control-label">School Name *</label>
		              <div class="controls"> 								              
						  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
					       	<input type="checkbox" name="school${School_id}" id="School" class="school" onchange="getAllPayrolls()" value="${School_id}"
								<c:forEach items="${Admin_Details.school_id}" var="Admin_School_id" varStatus="index">
						       		<c:if test="${Admin_School_id==School_id}">
					       			Checked
					       		   </c:if>
				       		    </c:forEach>
					       	> <c:out value='${Schools.school_Name[position.index]}'/>
					      <br>
					      	<input type="hidden" name="hiddenschool${School_id}"  id="hiddenschool${School_id}" value="${School_id}">  
					      </c:forEach>
					       <div id="validateMessage"></div>   
					  </div>
		            </div>	
		            </c:if>            
  					<div class="control-group">
					  <label class="control-label">Payroll</label>
		              <div class="controls">								  
					 	<select name="Payroll_id" id="Payroll_id" class="span11" onchange="getDepartments(this.value);">
							<option value="">
								Select Payroll
							</option>	 	
						<c:forEach items="${Payroll_List.payroll_id}" var="Payroll_id" varStatus="loop">
							<option value="${Payroll_id}"<c:if test="${Admin_Details.payroll_id==Payroll_id}">Selected</c:if>>
								School Name: <c:out value='${Payroll_List.payroll_School_Name[loop.index]}'/> - Payroll: <c:out value='${Payroll_List.payroll_Name[loop.index]}'/>
							</option>	   						 			
						</c:forEach>
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
						<c:forEach items="${Department_List.department_id}" var="Department_id" varStatus="loop">
							<option value="${Department_id}" <c:if test="${Admin_Details.department_id==Department_id}">Selected</c:if>>
								<c:out value='${Department_List.department_Name[loop.index]}'/>
							</option>	   						 			
						</c:forEach>
						</select>
 					  </div>
 					</div>
					<div class="control-group">
					  <label class="control-label">Designation</label>
		              <div class="controls">	
            			<input type="text" name="Designation" id="Designation" class="span11" maxlength="100" value="<c:out value='${Admin_Details.designation}'/>">				
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Date of joining *</label>
		              <div class="controls">
            			<input name="Joining_Date" type="Date" class="span11" id="Joining_Date"  value="<c:out value='${Admin_Details.joining_Date}'/>" required>			
 					  </div>
 					</div> 
		            <div class="control-group">
		              <label class="control-label">Initial Basic Salary *</label>
		              <div class="controls"> 
	 					<input type="number" id="Initial_Basic" name="Initial_Basic" class="span11" required value="<c:out value='${Admin_Details.initial_Basic}'/>" step="0.01" min="0" max="1000000000">                
		              </div>
		            </div> 
		            <div class="control-group">
		              <label class="control-label">Employee Group</label>
		              <div class="controls">
						Admin   
						<input type="hidden" name="Employee_Group" id="Employee_Group" value="Admin">             
		              </div>
		            </div>
					<div class="control-group">
					  <label class="control-label">First Name *</label>
		              <div class="controls">	
           				<input type="text" name="First_Name" id="First_Name" class="span11" required  maxlength="50" value="<c:out value='${Admin_Details.first_Name}'/>">           							
 					  </div>
 					</div>
 					<div class="control-group">
					  <label class="control-label">Last Name *</label>
		              <div class="controls">   
            			<input type="text" name="Last_Name" id="Last_Name" class="span11" required  maxlength="50" value="<c:out value='${Admin_Details.last_Name}'/>">            			         							
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
					       	<option value="${Country_id}"
				          	<c:if test="${Admin_Details.country_id==Country_id}">
				       			selected
				       		</c:if>		       	
					       	><c:out value='${Countries.country_Name[position.index]}'/></option>
					      </c:forEach>
			  			</select>		  								   					  
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">City *</label>
		              <div class="controls"> 
          	 			<input type="text" name="City" id="City" class="span11"  maxlength="100" required value="<c:out value='${Admin_Details.city}'/>">	 							 							
 					  </div>
 					</div>
   					<div class="control-group">
					  <label class="control-label">ZIP Code / PO Box</label>
		              <div class="controls">
						<input name="PO_Box" type="number" id="PO_Box" class="span11" min="0" max="1000000000" value="<c:if test="${Admin_Details.PO_Box!=0}"><c:out value='${Admin_Details.PO_Box}'/></c:if>">							 					
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Full Address *</label>
		              <div class="controls">
						<input name="Full_Address" type="text" id="Full_Address" class="span11" required maxlength="255" value="<c:out value='${Admin_Details.full_Address}'/>">							 								
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Gender *</label>
		              <div class="controls">			
						<select name="Gender" id="Gender" class="span11" required>
						  <option 
				          	<c:if test="${Admin_Details.gender=='Male'}">
				       			selected
				       		</c:if>				  
						  > 
						  	Male
						  </option>
						   <option 
				          	<c:if test="${Admin_Details.gender=='Female'}">
				       			selected
				       		</c:if>				   
						   > 
						  	Female
						  </option>
						</select>
 					  </div>
 					</div>
  					<c:if test="${Admin_Details.account_Owner!=1}">
  					<div class="control-group">
					  <label class="control-label">Email</label>
		              <div class="controls">
						<input name="Email" type="email" class="span11" id="Email" maxlength="255" value="<c:out value='${Admin_Details.email}'/>">	 							
 					  </div>
 					</div>
 					</c:if>
  					<div class="control-group">
					  <label class="control-label">Tel</label>
		              <div class="controls">	
           				<input name="Tel" type="text" class="span11" id="Tel" value="<c:out value='${Admin_Details.tel}'/>" maxlength="100">	 						
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Mobile</label>
		              <div class="controls">	
           				 <input name="Mobile" type="text" class="span11" id="Mobile" value="<c:out value='${Admin_Details.mobile}'/>" maxlength="100">	 					 		
 					  </div>
 					</div> 	
  					<div class="control-group">
					  <label class="control-label">Date of birth *</label>
		              <div class="controls">
            			<input name="Birth_Date" type="Date" class="span11" value="<c:out value='${Admin_Details.birth_Date}'/>" required>            						
 					  </div>
 					</div> 					
  					<div class="control-group">
					  <label class="control-label">Username *</label>
		              <div class="controls">
				          <input name="Username" type="text" id="Username" class="span11" required  pattern=".{8,}" title="8 characters minimum" maxlength="50" <c:if test="${Admin_Details.account_Owner==1}">disabled</c:if> value="<c:out value='${Admin_Details.username}'/>"
				           onblur="checkUsername(this.value,'${Admin_Details.username}')" 
				           onChange="document.getElementById('UsernameMsg').innerHTML='';">
					  </div>
          				<div id="UsernameMsg" style="width:60%;height:20px;display:inline-block"></div>		
					</div>
  					<c:if test="${Admin_Details.account_Owner!=1}">
  					<div class="control-group">
					  <label class="control-label">Password *</label>
		              <div class="controls">
			             <input name="Password" type="text" id="Password" class="span11" required  pattern=".{8,}" title="8 characters minimum" maxlength="50" value="<c:out value='${Admin_Details.password}'/>">         
	 				  </div>	 				  			
 					</div> 
 					</c:if>	
 					<c:if test="${Admin_Details.account_Owner==1}">
 					<input type="hidden" name="Owner_Password" value="Owner">
 					</c:if>
 					<input name="Admin_id" type="hidden" class="text" id="Admin_id" value="${Admin_Details.admin_id}"> 										 										 									 					 					 					 																									 					
		            <div class="form-actions">
					    <input type="submit" name="Modify" id="Modify" value="Edit" class="btn btn-info" onClick="checkUsername(Username.value, '${Admin_Details.username}');return validateSchoolCheckbox();">
					    <input type="button" name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage()" class="btn btn-danger"> 
		            </div>
		            <div id="DeleteMessage" ></div> 
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>				    
				    </form>
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
		formData.append("Admin_Photo1", resized_image); 
		
		if(document.getElementById("OkToSubmit").value=='OK')
		{
			$.ajax({
	        	type: "POST",
	            url: "${pageContext.request.contextPath}/Manage_Admin",
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