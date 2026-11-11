<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!doctype html>
<html>
<head>
<title>Assignments</title>
 </head>
 <script src="${pageContext.request.contextPath}/Global/lib/js/jquery-1.11.1.min.js"></script>
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.form.js"></script>
  <script src="${pageContext.request.contextPath}/Global/lib/js/summernote.min.js"></script>
  
  
<script>

function showLessonsList(Unit_id,Class_id, Course_id, Group_id)
{
if (Unit_id=="")
  {
  document.getElementById("LessonsList"+Class_id+Course_id+Group_id).innerHTML="<select class='form-control' name='Lesson_id"+Class_id+Course_id+Group_id+"' id='Lesson_id"+Class_id+Course_id+Group_id+"'><option value=''>Select Lesson</option></select>";
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
	  document.getElementById("LessonsList"+Class_id+Course_id+Group_id).innerHTML=xmlhttp.responseText;
	  document.getElementById("Assignment").innerHTML="";
	  document.getElementById("AssignmentDetails").innerHTML="";
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Lessons?Unit_id="+Unit_id+"&&Class_id="+Class_id+"&&Course_id="+Course_id+"&&Group_id="+Group_id+"&&Page=Assignment",true);
xmlhttp.send();
}

function showAssignment(Grade_id, Class_id, Course_id, Group_id, Lesson_id, Category_id, Page, selectedRow)

{
document.getElementById("AssignmentMain").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Grade_id==""&& Category_id==""&& Course_id==""&& Lesson_id=="")
  {
  document.getElementById("AssignmentMain").innerHTML="";
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
	  document.getElementById("AssignmentMain").innerHTML=xmlhttp.responseText;
	  //document.getElementById("Assignment").innerHTML="";
	  //document.form1.action="${pageContext.request.contextPath}/Manage_Assignment";
	 // document.form1.enctype="multipart/form-data";
	  var x = document.getElementsByClassName("selectedRow");
	  if(x[0]!=undefined) x[0].className = "";
	  document.getElementById(selectedRow).className="selectedRow";
	   // Init Summernote Plugin
	    $('.Text').summernote({
	      height: 255, //set editable area's height
	      focus: false, //set focus editable area after Initialize summernote
	      oninit: function() {},
	      onChange: function(contents, $editable) {},
	    });
		$('#form1').ajaxForm({target:'#AssignmentMain'});  
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Assignment_Details?Grade_id="+Grade_id+"&&Class_id="+Class_id+"&&Course_id="+Course_id+"&&Group_id="+Group_id+"&&Lesson_id="+Lesson_id+"&&Category_id="+Category_id+"&&Page="+Page,true);
xmlhttp.send();
}

function listAssignment(Grade_id, Class_id, Course_id, Group_id, Lesson_id, Category_id, Page, message, selectedRow)
{
	  document.getElementById("AssignmentMain").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
	if (Grade_id==""&& Class_id==""&& Course_id==""&& Lesson_id==""&& Category_id=="")
  {
  document.getElementById("AssignmentMain").innerHTML="";
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
	  if(message!='') document.getElementById("AssignmentMain").innerHTML='<div class="messageHeader">Assignment</div><div class="message">'+message+'</div>';
	  else document.getElementById("AssignmentMain").innerHTML="";
	  document.getElementById("AssignmentMain").innerHTML=xmlhttp.responseText;
	  
/* 	  if((Page!='Edit') && (Page!='Grade'))
		 {
			  if(document.getElementById("Give_Assignment").value=='Give Assignment')
			  {
			  	document.form1.action="${pageContext.request.contextPath}/Give_Assignment";
			  	document.form1.enctype="";
			  }

		 } */
	  
	  var x = document.getElementsByClassName("selectedRow");
	  if(x[0]!=undefined) x[0].className = "";
	  document.getElementById(selectedRow).className="selectedRow";

    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Assignments?Grade_id="+Grade_id+"&&Class_id="+Class_id+"&&Course_id="+Course_id+"&&Group_id="+Group_id+"&&Lesson_id="+Lesson_id+"&&Category_id="+Category_id+"&&Page="+Page,true);
xmlhttp.send();
}

function editAssignment(Assignment_id,Grade_id,Class_id, Course_id,Lesson_id,Category_id, Page)
{
	document.getElementById("AssignmentDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
	if (Assignment_id=="")
  {
  document.getElementById("AssignmentDetails").innerHTML="";
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

	  document.getElementById("AssignmentDetails").innerHTML=xmlhttp.responseText;
	   // Init Summernote Plugin
	    $('.Text').summernote({
	      height: 255, //set editable area's height
	      focus: false, //set focus editable area after Initialize summernote
	      oninit: function() {},
	      onChange: function(contents, $editable) {},
	    });
		$('#form1').ajaxForm({target:'#AssignmentDetails'});     
     }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Assignment_Details?Assignment_id="+Assignment_id+"&&Grade_id="+Grade_id+"&&Class_id="+Class_id+"&&Course_id="+Course_id+"&&Lesson_id="+Lesson_id+"&&Category_id="+Category_id+"&&Page="+Page,true);
xmlhttp.send();
}

function giveAssignment(Assignment_id,Grade_id,Class_id, Course_id, Group_id, Lesson_id,Category_id, Page)
{
document.getElementById("AssignmentDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Assignment_id=="")
  {
  document.getElementById("AssignmentDetails").innerHTML="";
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
	  document.getElementById("AssignmentDetails").innerHTML=xmlhttp.responseText;
	  $('#form1').ajaxForm({target:'#AssignmentDetails'});  
	   $('body').updatePolyfill();
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Assignment_Details?Assignment_id="+Assignment_id+"&&Grade_id="+Grade_id+"&&Class_id="+Class_id+"&&Course_id="+Course_id+"&&Group_id="+Group_id+"&&Lesson_id="+Lesson_id+"&&Category_id="+Category_id+"&&Page=Give",true);
xmlhttp.send();
}
function manageGrading(Assignment_Grade, Student_id, Assignment_id, GradeButton, Max_Grade, Remarks)
{
if (Assignment_Grade=="" && Student_id=="" && Assignment_id=="")
  {
  document.getElementById("Grade_Box"+Student_id).innerHTML="";
  return;
  } 
if(Assignment_Grade==""&&GradeButton=="Grade")
  {
	document.getElementById("Grade_Required"+Student_id).innerHTML="required";
	return;
  }
else
  {
	document.getElementById("Grade_Required"+Student_id).innerHTML="";
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

	  document.getElementById("Remarks_Box"+Student_id).innerHTML=Remarks;
	  document.getElementById("Grade_Box"+Student_id).innerHTML=xmlhttp.responseText;
	  
	  if (GradeButton=="Grade")
		  {
		  	document.getElementById("Grade"+Student_id).value='Edit';	
		  	
		  	///show delete button here////
				
		  	if ((Assignment_Grade!="") && (parseInt(Assignment_Grade)<=parseInt(Max_Grade)) && (parseInt(Assignment_Grade)>=0))
				{
					document.getElementById("DeleteArea"+Student_id).innerHTML="<input name='Delete"+Student_id+"' type='button' class='btn btn-sm btn-danger btn-block' style='width:130px;' id='Delete"+Student_id+"' value='Delete' onClick='manageGrading(Assignment_Grade"+Student_id+".value,"+Student_id+", Assignment_id.value, Delete"+Student_id+".value,"+Max_Grade+")'>";
				}
			document.getElementById("Remarks_Box"+Student_id).innerHTML=Remarks + "<input name=Remarks"+Student_id+" type=hidden id=Remarks"+Student_id+" value='"+Remarks+"'>";
			
		  	
		  }
	  else
		  {
		  	document.getElementById("Grade"+Student_id).value='Grade';
		  	if(GradeButton=="Delete") Remarks='';
		  	document.getElementById("Remarks_Box"+Student_id).innerHTML="<input name=Remarks"+Student_id+" type=text class=form-control max=255 id=Remarks"+Student_id+" value='"+Remarks+"'>";	
		  }
	  if (GradeButton=="Delete")
	  {
		  document.getElementById("DeleteArea"+Student_id).innerHTML="";
	  }
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Manage_Grading?Assignment_Grade="+Assignment_Grade+"&&Student_id="+Student_id+"&&Assignment_id="+Assignment_id+"&&GradeButton="+GradeButton+"&&Max_Grade="+Max_Grade+"&&Remarks="+Remarks, true);
xmlhttp.send();
} 

function gradeAssignment(Assignment_id,Grade_id, Class_id, Course_id, Group_id, Lesson_id,Category_id, Page)
{
document.getElementById("AssignmentDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Assignment_id=="")
  {
  document.getElementById("AssignmentDetails").innerHTML="";
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
	  document.getElementById("AssignmentDetails").innerHTML=xmlhttp.responseText;
	  $('#form1').ajaxForm({target:'#AssignmentDetails'});  
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Assignment_Details?Assignment_id="+Assignment_id+"&&Grade_id="+Grade_id+"&&Class_id="+Class_id+"&&Course_id="+Course_id+"&&Group_id="+Group_id+"&&Lesson_id="+Lesson_id+"&&Category_id="+Category_id+"&&Page=Grade",true);
xmlhttp.send();
}



function deleteFile(File_id, Assignment_id, File_Name, Account_id, School_id)
{
if (Assignment_id=="" && File_Name=="")
  {
  document.getElementById("Assignment_File"+File_id).innerHTML="";
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

	  document.getElementById("Assignment_File"+File_id).innerHTML="";
	  document.getElementById("Assignment_File"+File_id).innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Delete_File?File_id="+File_id+"&&File_Name="+File_Name+"&&Account_id="+Account_id+"&&School_id="+School_id+"&&EntityIdName=Assignment_id&&Entity_id="+Assignment_id+"&&Directory=Assignments", true);
xmlhttp.send();
}

function checkScorm()
{
	var x = document.getElementById("Assignments_Files");
	var txt = "";
	if ('files' in x)
	{
        for (var i = 0; i < x.files.length; i++) {
           
            var file = x.files[i];
            if ('name' in file) 
            {
                alert(file.name);
            }
        }
	} 
	//alert(txt);
	
	//alert(document.getElementById("Assignments_Files").value.split(/(\\|\/)/g).pop());

//var file = document.forms['form1']['Assignments_Files'].files[0];
//alert(file)
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
	//document.getElementById("Assignment_File"+File_Name).innerHTML="";
	  //document.getElementById("Assignment_File"+File_Name).innerHTML=xmlhttp.responseText;
  }
};
//xmlhttp.open("GET","Delete_Assignment_File.jsp?File_Name="+File_Name+"&&Assignment_id="+Assignment_id, true);
//xmlhttp.send();
}
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
    //xhr.addEventListener("load", uploadComplete, false);
    //xhr.addEventListener("error", uploadFailed, false);
    //xhr.addEventListener("abort", uploadCanceled, false);
    xhr.open("POST", "${pageContext.request.contextPath}/Manage_Assignment");
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
function showMore()
{
	document.getElementById('fullText').style.display='block';
	document.getElementById('breifText').style.display='none';
	//document.getElementsByClassName('assignmentList')[0].style.height=getComputedStyle(document.getElementById('content')).getPropertyValue('height'); 
}
function showLess()
{
	document.getElementById('fullText').style.display='none';
	document.getElementById('breifText').style.display='block'
	document.getElementsByClassName('assignmentList')[0].style.height='auto';
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
</script>

<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300">

<c:set var="Page" value="${param.Page}"/>
<c:if test="${Page==null}">
  	 <c:set var="Page" value="New"/>
</c:if>
  <!-- For Demo Purposes - Theme Settings Pane -->
	<%@include file="../includes/Theme_Settings.jsp" %>
  <!-- End: Theme Settings Pane -->
  <div id="main">
	<%@include file="../includes/TeacherMenu.jsp" %> 
	<%@include file="../includes/TeacherLeft.jsp" %>
     <section id="content_wrapper">

      <!-- Start: Topbar-Dropdown -->
	<%@include file="../includes/Topbar_Dropdown.jsp" %>
      <!-- End: Topbar-Dropdown -->  
      <!-- Start: Topbar -->
      <header id="topbar">
        <div class="topbar-left">
          <ol class="breadcrumb">
            <li class="crumb-active">
              <a href="${pageContext.request.contextPath}/Teacher/Teacher_Main.jsp">Dashboard</a>
            </li>
            <li class="crumb-icon">
              <a href="${pageContext.request.contextPath}/Teacher/Teacher_Main.jsp">
                <span class="glyphicon glyphicon-home"></span>
              </a>
            </li>
            <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Teacher/Teacher_Main.jsp">Home</a>
            </li>
            <li class="crumb-trail">Assignments</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Teacher/Assignments/New_Assignment.jsp?Page=New">New Assignment</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->		
		    <c:if test="${Page=='New'}">
		  	 	<c:set var="heading" value="New" />
		    </c:if>
		    <c:if test="${Page=='Edit'}">
		  	 	<c:set var="heading" value="Edit" />
		    </c:if>
		    <c:if test="${Page=='Grade'}">
		  	 	<c:set var="heading" value="Grade" />
		    </c:if> 
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
		  	<%@include file="../includes/Adsense_Top.jsp" %>
	          <h4>${heading} Assignment</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="glyphicon glyphicon-tasks"></span> ${heading} Assignment</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">${heading} Assignment Class Authority List</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
                    <table class="table table-bordered mbn">
                      <thead>
                        <tr >
                          <th></th>
                          <th>School</th>
                          <th>Grade</th>
                          <th>Class</th>
                          <th>Course</th> 
                          <th>Category</th>   
                          <th>Unit</th> 
                          <th>Lesson</th>                           
                          <th colspan="4" style="text-align:center;">Action</th>                                                                                                                                                                                       
                        </tr>
                      </thead>
                      <tbody>
					<c:forEach items="${TeacherAuthority.authorityList}" var="AuthorityList">
				    <tr id="${AuthorityList[0]}${AuthorityList[2]}${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}">
					    <td style="width:20px;"><i class="fa fa-circle text-warning fs8 pr15"></i></td>
						<td><c:out value="${AuthorityList[1]}"/></td>
						<td><c:out value="${AuthorityList[3]}"/></td>
						<td><c:out value="${AuthorityList[5]}"/></td>
						<td><c:out value="${AuthorityList[7]}  ${AuthorityList[9]}"/></td>
						<td>
							<c:set var="Cats" value="${TeacherAuthority.categorymap}"/>
							<c:if test="${TeacherContent.contentcategorymap.containsKey(AuthorityList[6])}">
								<c:set var="Cats" value="${TeacherContent.contentcategorymap}"/>
							</c:if>
			 				<select class="form-control" name="Category_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" id="Category_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" onchange="
							this.style.backgroundColor='#ffffff';
							var GetOut=0;
							<c:forEach items="${Cats}" var="Category">	
								<c:forEach items="${Category.value}" var="CategoryList">
									<c:if test="${Category.key==AuthorityList[6] && CategoryList[0]!=null}">
			 							<c:if test="${Page=='New'}">
						 					
						 					if (this.value == ${CategoryList[0]} && ${CategoryList[2]}=='1' && GetOut==0) 
						 					{
						 					document.getElementById('Create_Assignment'+${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}).disabled=false;
						 					GetOut=1;
						 					}
						 					else 
						
						 					{
						 						if (GetOut!=1)
						 						{
						 						document.getElementById('Create_Assignment'+${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}).disabled=true;
						 						
						 						//document.getElementById('tooltip').disable=false;
						 						Getout=1;
						 						
						 						}
						 					}
			 							</c:if>
			 					
			 							<c:if test="${Page=='Edit'}">
						 					if (this.value == ${CategoryList[0]} && ${CategoryList[2]}=='1' && GetOut==0) 
						 					{
						 					document.getElementById('Edit_Assignment'+${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}).disabled=false;
						 					GetOut=1;
						 					}
						 					else 
						
						 					{
						 						if (GetOut!=1)
						 						{
						 						document.getElementById('Edit_Assignment'+${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}).disabled=true;
						 						Getout=1;
						 						}
						 					}
			 							</c:if>
									</c:if>
								</c:forEach>	
							</c:forEach> 	
			 							" 
			 							>
			 						<option value="">
				 						Select Category
									</option>	   						 			
				 					<c:forEach items="${Cats}" var="Category">	
										<c:forEach items="${Category.value}" var="CategoryList">
											<c:if test="${Category.key==AuthorityList[6] && CategoryList[0]!=null}">
						   						<option value="${CategoryList[0]}">
						   							<c:out value="${CategoryList[1]}"/>
						   						</option>
			   								</c:if>
			   							</c:forEach>	
									</c:forEach> 		   						 			
			 				</select>
			 			</td>
			 			<td>   						 			
							<c:set var="Units" value="${TeacherAuthority.unitmap}"/>
							<c:if test="${TeacherContent.contentunitmap.containsKey(AuthorityList[6])}">
								<c:set var="Units" value="${TeacherContent.contentunitmap}"/>
							</c:if>		
			  				<c:choose>												
				     		 	<c:when test="${Units ne null}">
					 				<select class="form-control" name="Unit_id" id="Unit_id" onChange="showLessonsList(this.value,'${AuthorityList[4]}','${AuthorityList[6]}','${AuthorityList[8]}'); this.style.backgroundColor='#ffffff';">
						 				<option value="">
						 				Select Unit
										</option>
						 				<c:forEach items="${Units}" var="Unit">	
											<c:forEach items="${Unit.value}" var="UnitList">
												<c:if test="${Unit.key==AuthorityList[6] && UnitList[0]!=null}">
								   					<option value="${UnitList[0]}">
								   					<c:out value="${UnitList[1]}"/>
								   					</option>
					   							</c:if>
					   						</c:forEach>	
										</c:forEach> 								
					  				</select>
				     			 </c:when>
				      		<c:otherwise>
								<a href="${pageContext.request.contextPath}/Teacher/Course_Content/Manage_Unit.jsp">Create Units</a>		      
				     		</c:otherwise>
							</c:choose>	   						 			
			 			</td>
			 			<td>
							<div id="LessonsList${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}"> 
			 				<select class="form-control" name="Lesson_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" id="Lesson_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" onChange="document.getElementById('AssignmentDetails').innerHTML=''">
			 					<option value="">
				 					Select Lesson
								</option>	   						 			
			 				</select>
			 				</div>
			 			</td>
						<c:if test="${Page=='New'}">
							<td style="max-width:70px;border-right:transparent;text-align:center;">
								<div style="display: inline-block; width:75px;">							
									<button class="btn btn-xs btn-system btn-block" type="button" name="Create_Assignment${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" id="Create_Assignment${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" disabled value="Create Assignment" 
								   	onClick="showAssignment(${AuthorityList[2]},${AuthorityList[4]}, ${AuthorityList[6]},'${AuthorityList[8]}',
					 			   	Lesson_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}.value, 
					 			   	Category_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}.value, '${Page}', '${AuthorityList[0]}${AuthorityList[2]}${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}')"			   	
					 			   	><i class="fa fa-plus"></i> Add New </button>
								</div>				
							</td> 
							<td style="width:20px;border-left:transparent;"></td>
	  						<td style="border-right:transparent;text-align:center;">
					 	 		 	<button  class="btn btn-xs btn-primary btn-block" type="button" name="Give_Assignment" id="Give_Assignment" value="Give Assignment"
					       		 	onClick="listAssignment(${AuthorityList[2]},${AuthorityList[4]}, ${AuthorityList[6]},'${AuthorityList[8]}', 
					       		 	Lesson_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}.value, 
					      		 	Category_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}.value, 'Give', '', '${AuthorityList[0]}${AuthorityList[2]}${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}')"
					      		 	><i class="fa fa-edit"></i> Give Assignment </button>				 			   	
	  						</td>         
							<td style="width:20px;border-left:transparent;"></td>
						</c:if>

				  		<c:if test="${Page=='Edit'}">
				 			<td>
					 		 	<button type="button"  class="btn btn-xs btn-primary btn-block" name="Edit_Assignment${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" id="Edit_Assignment${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" disabled value="Create Assignment" 
					      		onClick="listAssignment(${AuthorityList[2]},${AuthorityList[4]}, ${AuthorityList[6]},'${AuthorityList[8]}', 
					       		Lesson_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}.value, 
					       		Category_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}.value, '${Page}', '', '${AuthorityList[0]}${AuthorityList[2]}${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}')">
					   			<i class="fa fa-edit"></i> Edit Assignment </button>
					   		</td>	
				   		</c:if>									    				 			 
						<c:if test="${Page=='Grade'}">
				 			<td>
				 	 			<button type="button" class="btn btn-xs btn-primary btn-block" name="Grade_Assignment" id="Grade_Assignment" value="Grade Assignment"
				      			onClick="listAssignment(${AuthorityList[2]},${AuthorityList[4]}, ${AuthorityList[6]},'${AuthorityList[8]}',
				       			Lesson_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}.value, 
				       			Category_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}.value, '${Page}', '', '${AuthorityList[0]}${AuthorityList[2]}${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}')">
				  				<i class="fa fa-edit"></i> Grade Assignment </button>
				  			</td>
				   		</c:if>	
				    </tr>
				    </c:forEach>
                      </tbody>
                    </table>  
                </div>
              </div>
            </div>
          </div>	
	        </div>
       </section>  
      <div class="panel" id="spy4">
        <div class="panel-body pn">
          <div class="table-responsive">
            <div class="bs-component" id="AssignmentMain">
			 	<c:if test="${not empty param.message}">
			  		<div class="message">${param.message}</div> 
			 	</c:if>  
            </div>
          </div>
        </div>
      </div>
<!--        </form> -->
	  <%@include file="../includes/Adsense_Bottom.jsp" %>
      <!-- End: Content -->

      <!-- Begin: Page Scripts -->

		<%@include file="../includes/Page_Scripts.jsp" %>

      <!-- End: Page Scripts -->            
     </section>
    <!-- Start: Right Sidebar -->
			<%@include file="../includes/Topbar_Right_Content.jsp" %>
    <!-- End: Right Sidebar -->
  </div>
</body>
</html>