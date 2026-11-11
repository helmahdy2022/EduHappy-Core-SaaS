<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Assignment Submission</title>
<script>

function filesSelected()
{
	var x = document.getElementById("Assignments_Files");
	var txt = "";
	if ('files' in x)
	{
		document.getElementById('file-selected').innerHTML="";
		for (var i = 0; i < x.files.length; i++) {
           
            var file = x.files[i];
            if ('name' in file) 
            {
                document.getElementById('file-selected').innerHTML = file.name + "<br>" +document.getElementById('file-selected').innerHTML;
            }
        }
	}
}

function uploadFile() {
    var fd = new FormData();
    fd.append("Assignments_Files", document.getElementById('Assignments_Files').files[0]);
    var xhr = new XMLHttpRequest();
    xhr.upload.addEventListener("progress", uploadProgress, false);
    xhr.open("POST", "${pageContext.request.contextPath}/Manage_Assignment_Submission");
    xhr.send(fd);
}
function uploadProgress(evt) {
  if (evt.lengthComputable) {
    var percentComplete = Math.round(evt.loaded * 100 / evt.total);
	if(document.getElementById('percent')!=null)
		{
	    	document.getElementById('percent').innerHTML = percentComplete.toString() + '%';
			document.getElementById('bar').style.width = percentComplete + '%';		
		}
  }
  else {
    document.getElementById('bar').innerHTML = 'unable to compute';
  }
}

function deleteFile(File_id, Give_Assignment_id, Student_id, File_Name, Account_id, School_id)
{
if (Give_Assignment_id=="" && Student_id=="" && File_Name=="")
  {
  document.getElementById("Submission_Files"+File_id).innerHTML="";
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

	  document.getElementById("Submission_Files"+File_id).innerHTML="";
	  document.getElementById("Submission_Files"+File_id).innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Delete_File?File_id="+File_id+"&&File_Name="+File_Name+"&&Account_id="+Account_id+"&&School_id="+School_id+"&&EntityIdName=Give_Assignment_id&&Entity_id="+Give_Assignment_id+"&&SubEntityIdName=Student_id&&Directory=AssignmentSubmissions&&SubDirectory=Students&&SubEntity_id="+Student_id, true);
xmlhttp.send();
}

</script>
</head>

<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300">

  <!-- For Demo Purposes - Theme Settings Pane -->
	<%@include file="../includes/Theme_Settings.jsp" %>
  <!-- End: Theme Settings Pane -->
  <div id="main">
	<%@include file="../includes/StudentMenu.jsp" %> 
	<%@include file="../includes/StudentLeft.jsp" %>

     <section id="content_wrapper">

      <!-- Start: Topbar-Dropdown -->
	<%@include file="../includes/Topbar_Dropdown.jsp" %>
      <!-- End: Topbar-Dropdown -->  
      <!-- Start: Topbar -->
      <header id="topbar">
        <div class="topbar-left">
          <ol class="breadcrumb">
            <li class="crumb-active">
              <a href="${pageContext.request.contextPath}/Student/Student_Main.jsp">Dashboard</a>
            </li>
            <li class="crumb-icon">
              <a href="${pageContext.request.contextPath}/Student/Student_Main.jsp">
                <span class="glyphicon glyphicon-home"></span>
              </a>
            </li>
            <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Student_Main.jsp">Home</a>
            </li>
            <li class="crumb-trail">Student's Assignments</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Student/Assignments/Initiate_Assignments.jsp">Assignments</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
  	   <%@include file="../includes/Adsense_Top.jsp" %>
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
	          <h4>Student Assignments</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="glyphicon glyphicon-tasks"></span>Assignment Submission</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Student Assignments</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
					  <table class="table table-bordered mbn">
						<c:forEach items="${Assignments.courseAssignments}" var="Assignment">
						<c:set var="CourseNameX" value="${Assignment[1]} ${Assignment[3]}"/>
					<c:if test="${Assignment[9] == param.Assignment_id}">
						<c:if test="${CourseNameX!=CourseNameY}">
					   <tr>
					   <td>
						 <c:out value="${Assignment[1]} ${Assignment[3]}"/>
					  </td>		  
					  <td style="text-align:right;">
					  	<a href="${pageContext.request.contextPath}/Student/Assignments/Initiate_Assignments.jsp">Current Assignments</a>
					  </td>
					   </tr>
					   <c:set var="CourseNameY" value="${Assignment[1]} ${Assignment[3]}"/>
					   </c:if>
					  <tr>	  
					  <td rowspan="4">
					  </td>  
					  <td style="background-color:grey;">
						<c:if test="${not empty Assignment[5]}">
						 <div style="color:#fff;"><label>Assignment Name: </label> <c:out value="${Assignment[4]}"/></div>
					  	</c:if>
					  </td>
					   </tr>
					  <tr>	   
					    <td>						
						<c:if test="${not empty Assignment[5]}">
							<div class="assignmentGrade">Max possible points:</div><div class="contentLessonField" style="width:5px;"><c:out value="${Assignment[5]}"/></div> 
							<div class="contentLessonStart" style="margin-left:150px;">Start Date:</div> <div class="contentLessonField"><c:out value="${Assignment[6]}"/></div>
							<div class="contentLessonEnd"> Due Date:</div> <div class="contentLessonField"><c:out value="${Assignment[7]}"/></div>
					  	
					  	<c:forEach items="${Assignments.assignmentFiles}" var="assignmentFiles">					  	
					  	</c:forEach>
					  	</c:if>
					  </td>
					   </tr>
					  <tr>
					  <td>
						<div>			
						<c:if test="${not empty Assignment[5]}">
						<label>Description:	</label>
						</c:if>							
							<div style="white-space:pre-wrap;"><c:out value="${Assignment[8]}" escapeXml="false"/></div>
						</div>										
					  </td>
					   </tr>		   		   
					  <tr>
						<c:set var="fileExists" value="No"/>
						<c:forEach items="${Assignments.assignmentFiles}" var="files">
						<c:if test="${files[0]==Assignment[9]}">
						<c:set var="fileExists" value="Yes"/>
						</c:if>
						</c:forEach>
						<c:if test="${fileExists=='Yes'}">
						  <td>
							<c:set var="Assignment_id1" value="${Assignment[9]}"/>
							<label>Attached Files:</label><br/>			
							<c:forEach items="${Assignments.assignmentFiles}" var="assignmentFiles">						
							<c:if test="${assignmentFiles[0]== Assignment[9]}">
							<c:set var="AttemptsAllowed" value="${Assignment[10]}"/>
							<c:set var="AttemptCount" value="0"/>
							<img src="${pageContext.request.contextPath}/images/File_Extensions/${assignmentFiles[5]}.jpg" width="20" height="20">
							<a href="${pageContext.request.contextPath}/File_Download?File_id=${assignmentFiles[4]}
							&&Entity=Assignments&&EntityIdName=Assignment_id&&Entity_id=${Assignment[9]}
							&&Course_id=${Assignment[0]}" target="_blank"><c:out value="${assignmentFiles[3]}"/></a>													
							<br/>
							</c:if>
							</c:forEach>			
						  </td>			
						</c:if>
					   </tr>
					</c:if>
					</c:forEach>
					<tr>
					<td></td>
					<td>
					<form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Manage_Assignment_Submission" enctype="multipart/form-data" onsubmit="return validateForm()">
               <div class="panel-body">
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Submission Notes *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">   												                    
						<textarea id="Submission_Notes" name="Submission_Notes" class="Submission_Notes" style="height: 400px;"><c:if test="${param.IsSubmissionExist==true}"><c:out value="${SubmissionNotes}"/></c:if></textarea>
                      </div>
                    </div>
                  </div>  
 				  <div class="form-group"> 
   				   <label class="col-lg-3 control-label">Upload Assignment Files</label>
                   <div class="admin-form">
                      <div class="col-lg-8">
						<div class="bs-component"> 
		                   <div class="section">
		                    <label class="field prepend-icon append-button file" onChange="filesSelected();">
		                      <span class="button btn-primary">Choose File</span>
		                      <input type="file" name="Assignments_Files" class="gui-file" id="Assignments_Files" multiple  onChange="document.getElementById('uploader1').value = this.value;"/>
		                      <input type="text" class="gui-input" id="uploader1" placeholder="Please Select A File"><span id="file-selected"></span>
		                      <label class="field-icon">
		                        <i class="fa fa-upload"></i>
		                      </label>
		                    </label>                    
		                  </div>
	                  	</div> 
                      </div>               
                    </div> 
                   </div>                	
                   <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						 <div class="progress">
		        				<div class="bar" id="bar"></div >
		        				<div class="percent" id="percent">0%</div >
		    			 </div>              
                      </div>
                    </div>
                  </div>


				 <c:if test="${param.IsSubmissionExist==true}">
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						 <table class="uploadedFilesTable">	
							<tr>
							<td colspan="3"><label>Submission Files:</label></td>
							</tr>
							<c:forEach items="${Submission_Files.file_id}" var="File_id" varStatus="loop">	
								<tr id="Submission_Files${File_id}">
								<td width="10%"><img src="${pageContext.request.contextPath}/images/File_Extensions/${Submission_Files.fileCategory[loop.index]}.jpg" width="20" height="20"></td>
								<td>
									<a href="${pageContext.request.contextPath}/File_Download?File_id=${File_id}
									&&Entity=AssignmentSubmissions&&EntityIdName=Give_Assignment_id&&SubEntity=Students&&SubEntityIdName=Student_id
									&&Account_id=${param.Account_id}&&School_id=${param.School_id}&&Entity_id=${param.Give_Assignment_id}&&SubEntity_id=${param.Student_id}" 
									target="_blank" class="assignmentLink"><c:out value="${Submission_Files.realFileName[loop.index]}"/></a>
								</td>			
								<td><img src="${pageContext.request.contextPath}/images/delete.jpg" width="20" height="20" border="1" onClick="deleteFile(${File_id}, ${param.Give_Assignment_id}, ${param.Student_id},'${Submission_Files.fileName[loop.index]}', ${param.Account_id}, ${param.School_id});"></td>
								</tr>
							</c:forEach>
						 </table>        
                      </div>
                    </div>
                  </div> 
                  </c:if>

	                   <div class="form-group">
	                    <label class="col-lg-3 control-label"></label>
	                    <div class="col-lg-8">
						<div style="text-align:center" >
							<input name="Student_id" type="hidden"  id="Student_id" value="${param.Student_id}">
							<input name="Give_Assignment_id" type="hidden"  id="Give_Assignment_id" value="${param.Give_Assignment_id}">
							<button name="submit_button" type="submit" class="btn btn-success mr10" id="submit_button" onClick="uploadFile();checkSession();"><i class="fa fa-save"></i> Save</button>				
						</div>
	                    </div>
	                  </div>  
						<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
                	</div>
                	</form>
					</td>
					</tr>
			 		</table>		
                </div>
              </div>
            </div>
          </div>	
	        </div>
       </section>  
      <!-- End: Content -->
	  <%@include file="../includes/Adsense_Bottom.jsp" %>
      <!-- Begin: Page Scripts -->

		<%@include file="../includes/Page_Scripts.jsp" %>

      <!-- End: Page Scripts -->            
     </section>
    <!-- Start: Right Sidebar -->
			<%@include file="../includes/Topbar_Right_Content.jsp" %>
    <!-- End: Right Sidebar -->
  </div>

   <script src="${pageContext.request.contextPath}/Global/lib/js/summernote.min.js"></script>
   <script>
  $(document).ready(function() {
	    $('.Submission_Notes').summernote({
		      height: 255, //set editable area's height
		      focus: false, //set focus editable area after Initialize summernote
		      oninit: function() {},
		      onChange: function(contents, $editable) {},
		    });
	});
  </script>
  </body>
</html>