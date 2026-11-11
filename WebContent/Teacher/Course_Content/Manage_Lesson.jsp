<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Course Content Main</title>
</head>
  <script src="${pageContext.request.contextPath}/Global/lib/js/jquery-1.11.1.min.js"></script>
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.form.js"></script>
 <script src="${pageContext.request.contextPath}/Global/lib/js/summernote.min.js"></script>
  <script>
  $(document).ready(function() 
{ 
    $('#form1').ajaxForm({target:'#LessonDetails'}); 
}); 
</script>
<script>
function showLesson(Unit_id,Course_id, selectedRow)
{
document.getElementById("LessonMain").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Unit_id=="")
  {
  document.getElementById("LessonMain").innerHTML="";
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
	  //document.getElementById("Lesson").innerHTML="";
	  document.getElementById("LessonMain").innerHTML=xmlhttp.responseText;
	   var x = document.getElementsByClassName("selectedRow");
	   if(x[0]!=undefined) x[0].className = "";
	   document.getElementById(selectedRow).className="selectedRow";
	   document.getElementById("hiddensSelectedRow").value = selectedRow;
	   // Init Summernote Plugin
	    $('.Description').summernote({
	      height: 255, //set editable area's height
	      focus: false, //set focus editable area after Initialize summernote
	      oninit: function() {},
	      onChange: function(contents, $editable) {},
	    });
		$('body').updatePolyfill();
     }
  };
xmlhttp.open("GET","Display_Lesson.jsp?Unit_id="+Unit_id+"&&Course_id="+Course_id,true);
xmlhttp.send();
}

function listLessons(Unit_id, Course_id, Page, Action, message, selectedRow)
{
document.getElementById("LessonMain").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Unit_id=="")
  {
  document.getElementById("LessonMain").innerHTML="";
  //document.getElementById("LessonDetails").innerHTML=""; 	
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
  		 if(message!='') document.getElementById("LessonMain").innerHTML='<div class="messageHeader">Lesson</div><div class="message">'+message+'</div>'; 
  		 else document.getElementById("LessonMain").innerHTML="<div style='text-align:center'><img src='${pageContext.request.contextPath}/images/Lessons_Banner.jpg'></div>"; 			 
  		 document.getElementById("LessonMain").innerHTML=xmlhttp.responseText;
		   var x = document.getElementsByClassName("selectedRow");
		   if(x[0]!=undefined) x[0].className = "";
		   document.getElementById(selectedRow).className="selectedRow";
		   document.getElementById("hiddensSelectedRow").value = selectedRow;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Lessons?Unit_id="+Unit_id+"&&Course_id="+Course_id+"&&Page="+Page+"&&Action="+Action,true);
xmlhttp.send();
}

function editLesson(Lesson_id,Unit_id, Course_id)
{
document.getElementById("LessonDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Lesson_id=="")
  {
  document.getElementById("LessonDetails").innerHTML="";
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

	  document.getElementById("LessonDetails").innerHTML=xmlhttp.responseText;
	   // Init Summernote Plugin
	    $('.Description').summernote({
	      height: 255, //set editable area's height
	      focus: false, //set focus editable area after Initialize summernote
	      oninit: function() {},
	      onChange: function(contents, $editable) {},
	    });
		$('body').updatePolyfill();
     }
  };
//xmlhttp.open("GET","Display_Lesson.jsp?Lesson_id="+Lesson_id,true);
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Lesson_Details?Lesson_id="+Lesson_id+"&&Unit_id="+Unit_id+"&&Course_id="+Course_id+"&&Page=Lesson&&Action=Edit",true);
xmlhttp.send();
}

function deleteFile(File_id, Lesson_id, File_Name, Account_id, School_id)
{
if (Lesson_id=="" && File_Name=="")
  {
  document.getElementById("Lesson_File"+File_id).innerHTML="";
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
	  document.getElementById("Lesson_File"+File_id).innerHTML="";
	  document.getElementById("Lesson_File"+File_id).innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Delete_File?File_id="+File_id+"&&File_Name="+File_Name+"&&Account_id="+Account_id+"&&School_id="+School_id+"&&EntityIdName=Lesson_id&&Entity_id="+Lesson_id+"&&Directory=Lessons", true);

xmlhttp.send();
}

function filesSelected()
{
	var x = document.getElementById("Lessons_Files");
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
    fd.append("Lessons_Files", document.getElementById('Lessons_Files').files[0]);
    var xhr = new XMLHttpRequest();
    xhr.upload.addEventListener("progress", uploadProgress, false);
    //xhr.addEventListener("load", uploadComplete, false);
    //xhr.addEventListener("error", uploadFailed, false);
    //xhr.addEventListener("abort", uploadCanceled, false);
    xhr.open("POST", "${pageContext.request.contextPath}/Manage_Lesson");
    xhr.send(fd);
}
function uploadProgress(evt) {
  if (evt.lengthComputable) {
    var percentComplete = Math.round(evt.loaded * 100 / evt.total);
    document.getElementById('percent').innerHTML = percentComplete.toString() + '%';
		document.getElementById('bar').style.width = percentComplete + '%';
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
 <%
String Action=request.getParameter("Action");
if (Action==null)
{
Action="Nothing";
}
%>
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
              <a href="${pageContext.request.contextPath}/Teacher/Course_Content/Manage_Lesson.jsp?Page=Lesson&&Action=New">Lessons</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
		  	<%@include file="../includes/Adsense_Top.jsp" %>
	          <h4> Course Lessons</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="glyphicon glyphicon-pencil"></span>Lessons</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Course Lessons Content Authority List</code>
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
                          <th colspan="4" style="text-align:center;">Units</th>                                                                                                                                                                
                        </tr>
                      </thead>
                      <tbody>
					<c:forEach items="${TeacherContent.contentList}" var="ContentList"> 
				    <tr id="${ContentList[0]}${ContentList[2]}${ContentList[4]}">
					    <td style="width:20px;"><i class="fa fa-circle text-warning fs8 pr15"></i></td>
						<td><c:out value="${ContentList[1]}"/></td>
						<td><c:out value="${ContentList[3]}"/></td>
						<td><c:out value="${ContentList[5]}"/></td>
						<td>		
			 				<c:forEach items="${TeacherContent.contentunitmap}" var="UnitMap">
			 				<c:if test="${UnitMap.key == ContentList[4]}">			 			
							<c:choose>
								<c:when test="${not empty UnitMap.value}">
			   						 <select name="Unit_id" id="Unit_id" class="form-control" onChange="
			   						 	<%if (Action.equals("New"))
			   						 	{ %>
			
			   						 	showLesson(this.value, ${ContentList[4]}, '${ContentList[0]}${ContentList[2]}${ContentList[4]}')
										<%}
			   						 	else
			   						 	{
			   						 	%>
										listLessons(this.value, ${ContentList[4]}, 'Lesson', 'Edit','', '${ContentList[0]}${ContentList[2]}${ContentList[4]}')
										<%}%>; this.style.backgroundColor='#ffffff';">
			   						 <option value="">
			   						 Select Unit
									</option>
										
						 				<c:forEach items="${UnitMap.value}" var="Unit">
						 					<c:if test="${UnitMap.key==ContentList[4] && Unit[1]!=null}">
											   	<option value="${Unit[0]}">
											   		<c:out value="${Unit[1]}"/>
											   	</option>
											</c:if>
										</c:forEach>	
																					
			   						 </select>
								</c:when>
								<c:otherwise>
															
								<a href="${pageContext.request.contextPath}/Teacher/Course_Content/Manage_Unit.jsp">Create Units</a>		      
								</c:otherwise>
								</c:choose>
								</c:if>	
							</c:forEach>  						
						</td>
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
            <div class="bs-component" id="LessonMain">
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
 		<input type="hidden" name="hiddenSelectedRow" id="hiddensSelectedRow">	
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