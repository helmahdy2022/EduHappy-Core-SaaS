<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Edit Parent</title>    
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/exif.js"></script>  
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/ProcessPhoto.js"></script>  
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
  function photoOnPageLoad()
  {
	  document.getElementById('custom-file-upload').style.backgroundImage ='url("${pageContext.request.contextPath}/uploadFiles/Accounts/${Parent_Details.account_id}/Parents/Photos/${param.Parent_id}/${Parent_Details.fileName}")';
  }
  

  function deleteFile(File_id, Parent_id, File_Name, Account_id)
  {
  if (Parent_id=="" && File_Name=="")
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
  xmlhttp.open("GET","${pageContext.request.contextPath}/Delete_File?File_id="+File_id+"&&File_Name="+File_Name+"&&Account_id="+Account_id+"&&EntityIdName=Parent_id&&Entity_id="+Parent_id+"&&Directory=Parents", true);
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
<c:if test="${not empty Parent_Details.fileName}">
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Parents</a> <a href="${pageContext.request.contextPath}/Admin/Parents/Search_Parent.jsp?Page=Edit_Parent.jsp" class="current">Edit Parent</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Edit Parent</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Edit Parent</h5>
		        </div>
		        <div class="widget-content nopadding">
 				   <form name="form1" id="form1" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Parent"  enctype="multipart/form-data">
		            <div class="control-group">
					  <label class="control-label">Parent Photo</label>
		              <div class="controls">
							<div id="spinner"> </div>
							 <canvas id="canvas" width="200" height="200" style="border: 1px solid #ccc; display:none;"></canvas>			
							<label id="custom-file-upload">			   
							  <img id="Photo" name="Photo" width="200px" style="display:none;"/>							  
							   <input type="file" name="Entity_Photo" id="Entity_Photo" class="upload" onchange="readImage();">	   					   
							</label>
							<br/>
							<div style="text-align:center; width:200px;">
							<a href="#" id="RemovePhoto" onclick="deleteFile(${Parent_Details.parent_id}, ${Parent_Details.parent_id}, '${Parent_Details.fileName}', ${Parent_Details.account_id});">
							<c:if test="${not empty Parent_Details.fileName}">
								Remove Photo
							</c:if>
							</a> 
							</div>							              
					  </div>
		            </div>	           
					<div class="control-group">
					  <label class="control-label">First Name *</label>
		              <div class="controls">
            			<input type="text" name="First_Name" id="First_Name" value="<c:out value='${Parent_Details.first_Name}'/>" class="span11" required maxlength="50">					
 					  </div>
 					</div>
 					<div class="control-group">
					  <label class="control-label">Last Name *</label>
		              <div class="controls">
            			<input type="text" name="Last_Name" id="Last_Name"  value="<c:out value='${Parent_Details.last_Name}'/>" class="span11" required maxlength="50"> 
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
				          	<c:if test="${Parent_Details.country_id==Country_id}">
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
	 					<input type="text" name="City" id="City" class="span11"  maxlength="100" required value="<c:out value='${Parent_Details.city}'/>">
 					  </div>
 					</div>
   					<div class="control-group">
					  <label class="control-label">ZIP Code / PO Box</label>
		              <div class="controls">	
						<input name="PO_Box" type="number" class="span11" id="PO_Box" value="<c:if test="${Parent_Details.PO_Box!=0}"><c:out value='${Parent_Details.PO_Box}'/></c:if>" min="0" max="1000000000">
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Full Address *</label>
		              <div class="controls">		
						<input name="Full_Address" type="text" class="span11" id="Full_Address" value="<c:out value='${Parent_Details.full_Address}'/>" required maxlength="255">
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Gender *</label>
		              <div class="controls">						
						<select name="Gender" id="Gender" class="span11" required>
						  <option 
				          	<c:if test="${Parent_Details.gender=='Male'}">
				       			selected
				       		</c:if>				  
						  > 
						  	Male
						  </option>
						   <option 
				          	<c:if test="${Parent_Details.gender=='Female'}">
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
         				<input name="Email" type="email" class="span11" id="Email" value="<c:out value='${Parent_Details.email}'/>" maxlength="255">	 						
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Tel</label>
		              <div class="controls">	
            			<input name="Tel" type="text" class="span11" id="Tel" value="<c:out value='${Parent_Details.tel}'/>" maxlength="100">	 							
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Mobile</label>
		              <div class="controls">
            			 <input name="Mobile" type="text" class="span11" id="Mobile" value="<c:out value='${Parent_Details.mobile}'/>" maxlength="100">	 					 		
 					  </div>
 					</div> 	
  					<div class="control-group">
					  <label class="control-label">Date of birth *</label>
		              <div class="controls">
            			<input name="Birth_Date" type="Date" class="span11"  value="<c:out value='${Parent_Details.birth_Date}'/>" required>            						
 					  </div>
 					</div> 	 					
  					<div class="control-group">
					  <label class="control-label">Username *</label>
		              <div class="controls">
			          	<input name="Username" type="text" id="Username" class="span11"  value="<c:out value='${Parent_Details.username}'/>" required pattern=".{8,}" title="8 characters minimum" maxlength="50" 
			          	onblur="checkUsername(this.value, '${Parent_Details.username}')"
			          	onChange="document.getElementById('UsernameMsg').innerHTML='';">
					  </div>
          				<div id="UsernameMsg" style="width:60%;height:20px;display:inline-block"></div>			
 					</div>
  					<div class="control-group">
					  <label class="control-label">Password *</label>
		              <div class="controls">
			           <input name="Password" type="text" id="Password" class="span11"  value="<c:out value='${Parent_Details.password}'/>" required pattern=".{8,}" title="8 characters minimum" maxlength="50">
			            <c:choose>
			            <c:when test="${Parent_Details.first_Login==1}">
			           		 <div><font size="2"><i>Parent must change this password at the first login</i></font></div>
			            </c:when>
			            <c:otherwise>
			            	<div><font size="2"><i>Parent has already changed the password at the first login</i></font></div>
			            </c:otherwise>
			            </c:choose>           
			            <input name="Parent_id" type="hidden" id="Parent_id" value="${Parent_Details.parent_id}">
	 				  </div>	 				  			
 					</div> 	 					
 					<font size="2" color="red"><b><i>Note: Parent will use these username and password for login. To login as a Parent <a href="${pageContext.request.contextPath}/Parent/Parent_Login.jsp" target=_blank>Click Here</a></i></b></font>
 									 										 									 					 					 					 																									 					
		            <div class="form-actions">
					    <input type="submit" name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="checkUsername(Username.value, '${Parent_Details.username}')">
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
formData.append("Parent_Photo1", resized_image);  
var frm = $('#form1');
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/Manage_Parent",// where you wanna post
        data: formData,
        processData: false,
        contentType: false,
    });
}

$(document).ready(function() {
	 
	$('form').submit(function(event) {
		event.preventDefault();
		var formData = new FormData(document.getElementsByName('form1')[0]);      
		formData.append("Parent_Photo1", resized_image); 
		if(document.getElementById("OkToSubmit").value=='OK')
				{
 				$.ajax({
 		        	type: "POST",
 		            url: "${pageContext.request.contextPath}/Manage_Parent",
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