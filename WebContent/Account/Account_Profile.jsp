<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Account Profile</title>

 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/jquery-1.11.3.js"></script>  
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/exif.js"></script>  
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/ProcessPhoto.js"></script> 
 <script>
 
 $(document).ready(function() {

		$('form').submit(function(event) {
			event.preventDefault();
			var formData = new FormData(document.getElementsByName('form1')[0]);      
			formData.append("Admin_Photo", resized_image); 
			$.ajax({
	        	type: "POST",
	            url: "${pageContext.request.contextPath}/Manage_Account",
	            data: formData,
	            processData: false,
	            contentType: false,
	        })
	        
	         // using the done promise callback
     .done(function(data) {

         // log data to the console so we can see
         //console.log(data); 
        $("#content").empty().append($(data).find('#content'));
     });
	        // stop the form from submitting the normal way and refreshing the page
	        event.preventDefault(); 
	    });
	});

function deleteFile()
{
	resized_image = "";
	document.getElementById("Photo").style="display:none";
	document.getElementById("RemovePhoto").style="display:none";
	document.getElementById('custom-file-upload').style.backgroundImage = 'url("${pageContext.request.contextPath}/images/Add_Photo.jpg")';
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

function photoOnPageLoad()
{
	
	//document.getElementById('custom-file-upload-logo').style.backgroundImage ='url("${pageContext.request.contextPath}/uploadFiles/Accounts/${Admin_Details.account_id}/Admins/Photos/${Admin_Details.admin_id}/${Admin_Details.fileName}")';
	document.getElementById('custom-file-upload').style.backgroundImage ='url("${pageContext.request.contextPath}/uploadFiles/Accounts/${Admin_Details.account_id}/Admins/Photos/${Admin_Details.admin_id}/${Admin_Details.fileName}")';	  
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
<body  onload="<c:if test="${not empty Admin_Details.fileName}">photoOnPageLoad();</c:if>">
<c:if test="${sessionScope['Admin_Session']==null}">
<jsp:forward page="/Admin/Admin_Login.jsp"/>
</c:if>
<div>
	<%@include file="../Admin/includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../Admin/includes/AdminLeft.jsp" %> </div>
	<div  id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Account</a> <a href="${pageContext.request.contextPath}/Initiate_Profile" class="current">My Profile</a> </div>
		  <h1>My Profile</h1>
		</div>
		<!--End-breadcrumbs-->	
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>My Profile</h5>
		        </div>
		        <div class="widget-content nopadding">
    			<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Account">	            
<%--  		            <div class="control-group">
					  <label class="control-label">Organization Logo</label>
		              <div class="controls">		                 		                 
						<div id="spinner"> </div>
						 <canvas id="canvas-logo" width="200" height="200" style="border: 1px solid #ccc; display:none;"></canvas>			
						<label id="custom-file-upload-logo">			   
						  <img id="Logo" name="Logo" width="200px" style="display:none;"/>			  
						   <input type="file" name="Entity_Logo" id="Entity_Logo" class="upload" onchange="readLogo();">	   					   
						</label>
						<br>
						<div style="text-align:center; width:200px;">			
						<a href="#" id="RemovePhoto" onclick="deleteFile(${Admin_Details.admin_id}, ${Admin_Details.admin_id}, '${Admin_Details.fileName}', ${Admin_Details.account_id}, '');">
						<c:if test="${not empty Admin_Details.fileName}">
							Remove Photo
						</c:if>
						</a>
						</div>			
					  </div>
						<br>		
		            </div> --%>
  		            <div class="control-group">
					  <label class="control-label">Admin Photo</label>
		              <div class="controls">		                 
						<div id="spinner"> </div>
						 <canvas id="canvas" width="200" height="200" style="border: 1px solid #ccc; display:none;"></canvas>			
						<label id="custom-file-upload">			   
						  <img id="Photo" name="Photo" width="200px" style="display:none;"/>			  
						   <input type="file" name="Entity_Photo" id="Entity_Photo" class="upload" onchange="readImage();">	   					   
						</label>
						<br>
						<div style="text-align:center; width:200px;">			
						<a href="#" id="RemovePhoto" onclick="deleteFile(${Admin_Details.admin_id}, ${Admin_Details.admin_id}, '${Admin_Details.fileName}', ${Admin_Details.account_id}, '');">
						<c:if test="${not empty Admin_Details.fileName}">
							Remove Photo
						</c:if>
						</a>
						</div>			
					  </div>
		            </div>
 					<div class="control-group">
					  <label class="control-label">Payroll</label>
		              <div class="controls">								  
					 	<select name="Payroll_id" id="Payroll_id" class="span11" onchange="getDepartments(this.value);">
							<option value="">
								Select Payroll
							</option>	 	
							<c:forEach items="${Payroll_List.payroll_id}" var="Payroll_id" varStatus="loop">
								<option value="${Payroll_id}" <c:if test="${Admin_Details.payroll_id==Payroll_id}">Selected</c:if>>
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
	 					<input type="number" id="Initial_Basic" name="Initial_Basic" class="span11" required value="<c:if test='${Admin_Details.initial_Basic!=-1}'><c:out value='${Admin_Details.initial_Basic}'/></c:if>" step="0.01" min="0" max="1000000000">                
		              </div>
		            </div> 
					<div class="control-group">
					  <label class="control-label">First Name *</label>
		              <div class="controls">	           							
  						<input type="text" name="First_Name" id="First_Name" class="span11" required maxlength="50" value="<c:out value='${Admin_Details.first_Name}'/>">
 					  </div>
 					</div>
 					<div class="control-group">
					  <label class="control-label">Last Name *</label>
		              <div class="controls">              			         							
 						<input type="text" name="Last_Name" id="Last_Name" class="span11" required maxlength="50" value="<c:out value='${Admin_Details.last_Name}'/>">
 					  </div>
 					</div>
 					<div class="control-group">
					  <label class="control-label">Organization *</label>
		              <div class="controls">              			         							
						<input type="text" name="Organization" id="Organization" class="span11" required maxlength="255" value="<c:out value='${Account_Details.organization}'/>">
 					  </div>
 					</div> 					
  					<div class="control-group">
					  <label class="control-label">Country *</label>
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
					  <label class="control-label">City</label>
		              <div class="controls"> 							 							
 	 					<input type="text" name="City" id="City" class="span11"  maxlength="100" value="<c:out value='${Admin_Details.city}'/>">  	
 					  </div>
 					</div>
   					<div class="control-group">
					  <label class="control-label">ZIP Code / PO Box</label>
		              <div class="controls">							 					
 						<input name="PO_Box" type="number" class="span11" id="PO_Box" value="<c:if test="${Admin_Details.PO_Box!=0}"><c:out value='${Admin_Details.PO_Box}'/></c:if>" min="0" max="1000000000">
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Full Address *</label>
		              <div class="controls">						 								
						<input name="Full_Address" type="text" class="span11" id="Full_Address" value="<c:out value='${Admin_Details.full_Address}'/>" maxlength="255">
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Gender</label>
		              <div class="controls">			
						<select name="Gender" id="Gender" class="span11">
						  <option value="">Select Gender</option>			  
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
  					<div class="control-group">
					  <label class="control-label">Tel </label>
		              <div class="controls">	
           				<input name="Tel" type="text" class="span11" id="Tel" maxlength="100" value="<c:out value='${Admin_Details.tel}'/>" >	 						
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
  					<c:choose>
  					<c:when test="${Admin_Details.account_Owner==1}">
	  					<div class="control-group">
						  <label class="control-label">Email (Username)*</label>
			              <div class="controls">
							${Account_Details.email}
						  </div>
	          				<div id="UsernameMsg" style="width:60%;height:20px;display:inline-block"></div>		
						</div>  					
  					</c:when>
  					<c:otherwise>
 	  					<div class="control-group">
						  <label class="control-label">Username *</label>
			              <div class="controls">
							${Admin_Details.username}
						  </div>
	          				<div id="UsernameMsg" style="width:60%;height:20px;display:inline-block"></div>		
						</div> 
  					</c:otherwise>
  					</c:choose>
  					<div class="control-group">
					  <label class="control-label">Password *</label>
		              <div class="controls">
			             <input type="password" name="Password" id="Password" class="span11" pattern=".{8,}" title="8 characters minimum" required maxlength="255" autocomplete="off"  value="<c:out value='${Admin_Details.password}'/>">           
	 				  </div>	 				  			
 					</div> 	 											 										 									 					 					 					 																									 					
		           <c:if test="${Admin_Details.account_Owner==1}">
		            <div class="form-actions">
		            	<input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success" onClick="checkSession();">
		            </div>
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				   </c:if>				    
				    </form>
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

</body>
</html>