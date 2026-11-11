<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Manage Standard Assignments</title>
</head>
  <script src="${pageContext.request.contextPath}/Global/lib/js/jquery-1.11.1.min.js"></script>
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.form.js"></script>
  <script src="${pageContext.request.contextPath}/Global/lib/js/summernote.min.js"></script>
 
<script>

function showLessonsList(Unit_id,Grade_id, Course_id)
{
if (Unit_id=="")
  {
  document.getElementById("LessonsListGrade"+Grade_id+"Course"+Course_id).innerHTML="<select class='form-control' name='Lesson_idGrade"+Grade_id+"Course"+Course_id+"' id='Lesson_idGrade"+Grade_id+"Course"+Course_id+"'><option value=''>Select Lesson</option></select>";
  document.getElementById("Assignment").innerHTML="";
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
	  document.getElementById("LessonsListGrade"+Grade_id+"Course"+Course_id).innerHTML=xmlhttp.responseText;
	  document.getElementById("Assignment").innerHTML="";
	  document.getElementById("AssignmentDetails").innerHTML="";
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Lessons?Unit_id="+Unit_id+"&&Grade_id="+Grade_id+"&&Course_id="+Course_id+"&&Page=Assignment",true);
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
if (Category_id=="")
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
	   // Init Summernote Plugin
	    $('.Text').summernote({
	      height: 255, //set editable area's height
	      focus: false, //set focus editable area after Initialize summernote
	      oninit: function() {},
	      onChange: function(contents, $editable) {},
	    });	  
	    $('#form1').ajaxForm({target:'#AssignmentMain'}); 	  
	  //document.form1.action="${pageContext.request.contextPath}/Manage_Assignment";
	  //document.form1.enctype="multipart/form-data";

	  var x = document.getElementsByClassName("selectedRow");
	  if(x[0]!=undefined) x[0].className = "";
	  document.getElementById(selectedRow).className="selectedRow";
     }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Assignment_Details?Grade_id="+Grade_id+"&&Class_id="+Class_id+"&&Course_id="+Course_id+"&&Group_id="+Group_id+"&&Lesson_id="+Lesson_id+"&&Category_id="+Category_id+"&&Page="+Page+"&&type=Standard",true);
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
	  if(message!='') 
	  {
		  document.getElementById("AssignmentMain").innerHTML='<div class="messageHeader">Assignment</div><div class="message">'+message+'</div>';
	  }		 
	  else
	  {
		  if(!(xmlhttp.responseText.indexOf('There are no assignments')>=0))
		  	document.getElementById("AssignmentMain").innerHTML="<div text-align:center'><img src='${pageContext.request.contextPath}/images/Assignment_Image.png'></div>";
		  else
			  document.getElementById("AssignmentMain").innerHTML="";
	  }
	  document.getElementById("AssignmentMain").innerHTML=xmlhttp.responseText;
	  
	  if((Page!='Edit_Standard') && (Page!='Grade'))
		 {
			  if(document.getElementById("Give_Assignment").value=='Give Assignment')
			  {
			  	document.form1.action="${pageContext.request.contextPath}/Give_Assignment";
			  	document.form1.enctype="";
			  }

		 }
	  
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
	  //document.getElementsByClassName('assignmentList')[0].style.height=getComputedStyle(document.getElementsByClassName('middleContent')[0]).getPropertyValue('height'); 
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

function checkCatSelect(Grade_id, Course_id)
{
	if(document.getElementById("Category_idGrade"+Grade_id+"Course"+Course_id).value=="")
		document.getElementById("CatRequiredMsg_Grade"+Grade_id+"Course"+Course_id).innerHTML="<font color=red>required</font>";
	else		
		document.getElementById("CatRequiredMsg_Grade"+Grade_id+"Course"+Course_id).innerHTML="";
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
            <li class="crumb-trail">Course Content</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Teacher/Course_Content/Manage_Standard_Assignment.jsp">Standard Assignment</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
		  	<%@include file="../includes/Adsense_Top.jsp" %>
	          <h4>Standard Assignment</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="fa fa-columns"></span>Standard Assignment</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Standard Assignment Content Authority List</code>
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
                          <th>Course</th> 
                          <th>Category</th>   
                          <th>Unit</th> 
                          <th>Lesson</th>                           
                          <th colspan="4" style="text-align:center;">Action</th>                                                                                                                                                                                       
                        </tr>
                      </thead>
                      <tbody>
					<c:forEach items="${TeacherContent.contentList}" var="ContentList"> 
				    <tr id="Grade${ContentList[2]}Course${ContentList[4]}">
					    <td style="width:20px;"><i class="fa fa-circle text-warning fs8 pr15"></i></td>
						<td><c:out value="${ContentList[1]}"/></td>
						<td><c:out value="${ContentList[3]}"/></td>
						<td><c:out value="${ContentList[5]}"/></td>
						<td>
			 				<select class="form-control" name="Category_idGrade${ContentList[2]}Course${ContentList[4]}" id="Category_idGrade${ContentList[2]}Course${ContentList[4]}" 
			 				onchange="
			 				this.style.backgroundColor='#ffffff';
			 				document.getElementById('CatRequiredMsg_Grade${ContentList[2]}Course${ContentList[4]}').innerHTML='';
			 				document.getElementById('Assignment').innerHTML='';
			 				document.getElementById('AssignmentDetails').innerHTML=''">
			 						<option value="">
				 						Select Category
									</option>	   						 			
						 			 <c:forEach items="${TeacherContent.contentcategorymap}" var="CategoryMap">
							 			 <c:if test="${CategoryMap.key==ContentList[4]}">
								 			 <c:forEach items="${CategoryMap.value}" var="Category">
								 			 	<c:if test="${Category[0]!=null}">				 			 		
								 			 		<option value="${Category[0]}"><c:out value="${Category[1]}"/></option>		 			 		
								 			 	</c:if>
								 			 </c:forEach>
							 			 </c:if>
						 			 </c:forEach>		   						 			
			 				</select>
			 				<div id="CatRequiredMsg_Grade${ContentList[2]}Course${ContentList[4]}"></div>
			 			</td>
			 			<td>   						 			
						<c:choose>									
			     		 	<c:when test="${TeacherContent.contentunitmap ne null}">
				 				<select class="form-control" name="Unit_id" id="Unit_id" onChange="showLessonsList(this.value,'${ContentList[2]}','${ContentList[4]}'); this.style.backgroundColor='#ffffff';">
					 				<option value="">
					 				Select Unit
									</option>
					 				<c:forEach items="${TeacherContent.contentunitmap}" var="UnitMap">	
										<c:forEach items="${UnitMap.value}" var="UnitList">
											<c:if test="${UnitMap.key==ContentList[4] && UnitList[0]!=null}">
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
							<div id="LessonsListGrade${ContentList[2]}Course${ContentList[4]}"> 
			 				<select class="form-control" name="Lesson_idGrade${ContentList[2]}Course${ContentList[4]}" id="Lesson_idGrade${ContentList[2]}Course${ContentList[4]}">
			 					<option value="">
				 					Select Lesson
								</option>	   						 			
			 				</select>
			 				</div>
			 			</td>
						<td style="max-width:70px;border-right:transparent;text-align:center;">
							<div style="display: inline-block; width:75px;">							
				 			  	<button type="button" class="btn btn-xs btn-system btn-block" name="Create_AssignmentGrade${ContentList[2]}Course${ContentList[4]}" id="Create_AssignmentGrade${ContentList[2]}Course${ContentList[4]}"  
							   	onClick="showAssignment(${ContentList[2]},'', ${ContentList[4]},'',
				 			   	Lesson_idGrade${ContentList[2]}Course${ContentList[4]}.value, 
				 			   	Category_idGrade${ContentList[2]}Course${ContentList[4]}.value, 'New_Standard', 'Grade${ContentList[2]}Course${ContentList[4]}');document.getElementById('Category_idGrade${ContentList[2]}Course${ContentList[4]}').required=true;checkCatSelect(${ContentList[2]},${ContentList[4]});">
				 			   	<i class="fa fa-plus"></i> Add New 
				 			   	</button>
							</div>				
						</td> 
						<td style="width:20px;border-left:transparent;"></td>
  						<td style="border-right:transparent;text-align:center;">
					 		 	<button type="button" class="btn btn-xs btn-primary btn-block" name="Edit_AssignmentGrade${ContentList[2]}Course${ContentList[4]}" id="Edit_AssignmentGrade${ContentList[2]}Course${ContentList[4]}" 
					      		onClick="listAssignment(${ContentList[2]},'', ${ContentList[4]},'', 
					       		Lesson_idGrade${ContentList[2]}Course${ContentList[4]}.value, 
					       		Category_idGrade${ContentList[2]}Course${ContentList[4]}.value, 'Edit_Standard', '', 'Grade${ContentList[2]}Course${ContentList[4]}')">						
  				 			   	<i class="fa fa-edit"></i> Edit 
				 			   	</button>
  						</td>         
						<td style="width:20px;border-left:transparent;"></td>
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
			 <c:if test="${not empty message}">
		 		<div class="message">${message}
		 		<br>
		 		<c:forEach items="${uploadMessage}" var="uploadMessage">
		 		<c:out value="${uploadMessage}" />
		 		<br>
		 		</c:forEach>
		 		</div> 
			 </c:if> 
            </div>
          </div>
        </div>
      </div>
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