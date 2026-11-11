<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Edit Student</title>    
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/exif.js"></script>  
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/ProcessPhoto.js"></script>  
 <script>
 
 function showGrades(School_id)
 {
 if (School_id=="")
   {
	   document.getElementById("Grade").innerHTML="<option>Select Grade</option>";
	   document.getElementById("Class").innerHTML="<option>Select Class</option>";
	   document.getElementById("Route_id").innerHTML="<option value=''>Select Route</option>";
	   document.getElementById("DeleteMessage").innerHTML="";
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
     document.getElementById("Class").innerHTML="<option>Select Class</option>";
     showRoutes(School_id);
     document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+School_id,true);
 xmlhttp.send();
 }
 //==========End of showGrades function=================
 function showClasses(str)
 {
 if (str=="")
   {
	   document.getElementById("Class").innerHTML="<option>Select Class</option>";
	   document.getElementById("DeleteMessage").innerHTML="";
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
     document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Classes?Grade_id="+str,true);
 xmlhttp.send();
 }
 //==========End of showClasses function=================
 
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
  function photoOnPageLoad()
  {
	  document.getElementById('custom-file-upload').style.backgroundImage ='url("${pageContext.request.contextPath}/uploadFiles/Accounts/${Student_Details.account_id}/Schools/${Student_Details.school_id}/Students/Photos/${param.Student_id}/${Student_Details.fileName}")';
  }
  

  function deleteFile(File_id, Student_id, File_Name, Account_id, School_id)
  {
  if (Student_id=="" && File_Name=="")
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
  xmlhttp.open("GET","${pageContext.request.contextPath}/Delete_File?File_id="+File_id+"&&File_Name="+File_Name+"&&Account_id="+Account_id+"&&School_id="+School_id+"&&EntityIdName=Student_id&&Entity_id="+Student_id+"&&Directory=Students", true);
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
  
</script>

</head>

<body 
<c:if test="${not empty Student_Details.fileName}">
onLoad=photoOnPageLoad();
</c:if>
>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
<div id="main">
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Students</a> 
		  <c:choose>
		  <c:when test="${param.Graduated!='Yes'}">		  
		  <a href="${pageContext.request.contextPath}/Admin/Students/Search_Student.jsp" class="current">Edit Student</a> 
		  </c:when>
		  <c:otherwise>
		  <a href="${pageContext.request.contextPath}/Admin/Students/Search_Graduated_Student.jsp" class="current">Edit Graduated Student</a>		  
		  </c:otherwise>
		  </c:choose>
		  </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>

		  <c:choose>
		  <c:when test="${param.Graduated!='Yes'}">		  
		  <h1>Edit Student</h1>
		  </c:when>
		  <c:otherwise>
		  <h1>Edit Graduated Student</h1>		  
		  </c:otherwise>
		  </c:choose>
		  

		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Edit Student</h5>
		        </div>
		        <div class="widget-content nopadding">
 				   <form name="form1" id="form1" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Student"  enctype="multipart/form-data">
		            <div class="control-group">
					  <label class="control-label">Student Photo</label>
		              <div class="controls">
							<div id="spinner"> </div>
							 <canvas id="canvas" width="200" height="200" style="border: 1px solid #ccc; display:none;"></canvas>			
							<label id="custom-file-upload">			   
							  <img id="Photo" name="Photo" width="200px" style="display:none;"/>							  
							   <input type="file" name="Entity_Photo" id="Entity_Photo" class="upload" onchange="readImage();">	   					   
							</label>
							<br/>
							<div style="text-align:center; width:200px;">
							<a href="#" id="RemovePhoto" onclick="deleteFile(${Student_Details.student_id}, ${Student_Details.student_id}, '${Student_Details.fileName}', ${Student_Details.account_id}, ${Student_Details.school_id});">
							<c:if test="${not empty Student_Details.fileName}">
								Remove Photo
							</c:if>
							</a> 
							</div>							              
					  </div>
		            </div>	
		            <div class="control-group">
					  <label class="control-label">School Name *</label>
		              <div class="controls">								              
			           	<select name="School" id="School" class="span11" onChange="showGrades(this.value)" required <c:if test="${param.Graduated=='Yes'}">disabled</c:if>>
			 			 <option value=""> 
						  	Select School
						  </option>
						  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
					       	<option value="${School_id}"
					       	   <c:if test="${Student_Details.school_id==School_id}">
				       			selected	       		   
				       		   </c:if>
					       	><c:out value='${Schools.school_Name[position.index]}'/></option>
					      </c:forEach>	  
						</select>
						<c:if test="${param.Graduated=='Yes'}">
							<input type="hidden" name="School" value="${Student_Details.school_id}">
						</c:if>
					  </div>
		            </div>	            
					<div class="control-group">
					  <label class="control-label">Grade Name *</label>
		              <div class="controls"> 			 		 						
				         <select name="Grade" id="Grade" class="span11" onChange="showClasses(this.value)" required <c:if test="${param.Graduated=='Yes'}">Disabled</c:if>>
				 			 <option value="">Select Grade</option>
						  	<c:forEach items="${Grades.grade_id}" var="Grade_id" varStatus="position">
					          	<option value="${Grade_id}"
					          	<c:if test="${Student_Details.grade_id==Grade_id}">
					       			selected
					       		</c:if>
					          	><c:out value='${Grades.grade_Name[position.index]}'/></option>
				          </c:forEach>  			 
				 		</select> 
						<c:if test="${param.Graduated=='Yes'}">
							<input type="hidden" name="Grade" value="${Student_Details.grade_id}">
						</c:if>				 		
 					  </div>
 					</div>	
					<div class="control-group">
					  <label class="control-label">Classroom Name *</label>
		              <div class="controls">						 
			 	         <select name="Class" id="Class" class="span11" <c:if test="${param.Graduated!='Yes'}">required</c:if>  <c:if test="${param.Graduated=='Yes'}">Disabled</c:if>>
				 			 <option value="">Select Classroom</option>
					  	<c:forEach items="${Classes.class_id}" var="Class_id" varStatus="position">
				          	<option value="${Class_id}"
				          	<c:if test="${Student_Details.class_id==Class_id}">
				       			selected
				       		</c:if>
				          	><c:out value='${Classes.class_Name[position.index]}'/></option>
			          </c:forEach>	 			 
				 		</select>
						<c:if test="${param.Graduated=='Yes'}">
							<input type="hidden" name="Class" value="${Student_Details.class_id}">
						</c:if>	
 					  </div>
 					</div>
					<div class="control-group">
					  <label class="control-label">First Name *</label>
		              <div class="controls">
            			<input type="text" name="First_Name" id="First_Name" value="<c:out value='${Student_Details.first_Name}'/>" class="span11" required maxlength="50">					
 					  </div>
 					</div>
 					<div class="control-group">
					  <label class="control-label">Last Name *</label>
		              <div class="controls">
            			<input type="text" name="Last_Name" id="Last_Name"  value="<c:out value='${Student_Details.last_Name}'/>" class="span11" required maxlength="50"> 
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
				          	<c:if test="${Student_Details.country_id==Country_id}">
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
	 					<input type="text" name="City" id="City" class="span11"  maxlength="100" required value="<c:out value='${Student_Details.city}'/>">
 					  </div>
 					</div>
   					<div class="control-group">
					  <label class="control-label">ZIP Code / PO Box</label>
		              <div class="controls">	
						<input name="PO_Box" type="number" class="span11" id="PO_Box" value="<c:if test="${Student_Details.PO_Box!=0}"><c:out value='${Student_Details.PO_Box}'/></c:if>" min="0" max="1000000000">
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Full Address *</label>
		              <div class="controls">		
						<input name="Full_Address" type="text" class="span11" id="Full_Address" value="<c:out value='${Student_Details.full_Address}'/>" required maxlength="255">
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Gender *</label>
		              <div class="controls">						
						<select name="Gender" id="Gender" class="span11" required>
						  <option 
				          	<c:if test="${Student_Details.gender=='Male'}">
				       			selected
				       		</c:if>				  
						  > 
						  	Male
						  </option>
						   <option 
				          	<c:if test="${Student_Details.gender=='Female'}">
				       			selected
				       		</c:if>				   
						   > 
						  	Female
						  </option>
						</select>									
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Email</label>
		              <div class="controls">	
         				<input name="Email" type="email" class="span11" id="Email" value="<c:out value='${Student_Details.email}'/>" maxlength="255">	 						
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Tel</label>
		              <div class="controls">	
            			<input name="Tel" type="text" class="span11" id="Tel" value="<c:out value='${Student_Details.tel}'/>" maxlength="100">	 							
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Mobile</label>
		              <div class="controls">
            			 <input name="Mobile" type="text" class="span11" id="Mobile" value="<c:out value='${Student_Details.mobile}'/>" maxlength="100">	 					 		
 					  </div>
 					</div> 	
  					<div class="control-group">
					  <label class="control-label">Date of birth *</label>
		              <div class="controls">
            			<input name="Birth_Date" type="Date" class="span11"  value="<c:out value='${Student_Details.birth_Date}'/>" required>            						
 					  </div>
 					</div> 
   					<div class="control-group">
					  <label class="control-label">Transport - Route Name</label>
		              <div class="controls">
						 <select name="Route_id" id="Route_id" class="span11">
						  <option value=""> 
						  	Select Route
						  </option>
						  	<c:forEach items="${Routes_List.route_id}" var="Route_id" varStatus="position">
					          	<option value="${Route_id}"
					          	<c:if test="${Student_Details.route_id==Route_id}">
					       			selected
					       		</c:if>
					          	><c:out value='${Routes_List.route_Name[position.index]}'/></option>
				          </c:forEach>  								  
			  			</select>			
 					  </div>
 					</div>	 					
  					<div class="control-group">
					  <label class="control-label">Username *</label>
		              <div class="controls">
			          	<input name="Username" type="text" id="Username" class="span11"  value="<c:out value='${Student_Details.username}'/>" required pattern=".{8,}" title="8 characters minimum" maxlength="50" 
			          	onblur="checkUsername(this.value, '${Student_Details.username}')"
			          	onChange="document.getElementById('UsernameMsg').innerHTML='';">
					  </div>
          				<div id="UsernameMsg" style="width:60%;height:20px;display:inline-block"></div>			
 					</div>
  					<div class="control-group">
					  <label class="control-label">Password *</label>
		              <div class="controls">
			           <input name="Password" type="text" id="Password" class="span11"  value="<c:out value='${Student_Details.password}'/>" required pattern=".{8,}" title="8 characters minimum" maxlength="50">
			            <c:choose>
			            <c:when test="${Student_Details.first_Login==1}">
			           		 <div><font size="2"><i>Student must change this password at the first login</i></font></div>
			            </c:when>
			            <c:otherwise>
			            	<div><font size="2"><i>Student has already changed the password at the first login</i></font></div>
			            </c:otherwise>
			            </c:choose>           
			            <input name="Student_id" type="hidden" id="Student_id" value="${Student_Details.student_id}">
			            <input name="Graduated" type="hidden" id="Graduated" value="${param.Graduated}">
	 				  </div>	 				  			
 					</div> 	 					
 					<font size="2" color="red"><b><i>Note: Student will use these username and password for login. To login as a Student <a href="${pageContext.request.contextPath}/Student/Student_Login.jsp" target=_blank>Click Here</a></i></b></font>
 									 										 									 					 					 					 																									 					
		            <div class="form-actions">
					    <input type="submit" name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="checkUsername(Username.value, '${Student_Details.username}')">
					    <input type="button" name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage()" class="btn btn-danger"> 
		            </div>
		            <div id="DeleteMessage" ></div> 
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
function uploadPhoto(){               
	 
 	var formData = new FormData(document.getElementsByName('form1')[0]);// yourForm: form selector      
formData.append("Student_Photo1", resized_image);  
var frm = $('#form1');
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/Manage_Student",// where you wanna post
        data: formData,
        processData: false,
        contentType: false,
    });
}

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