<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Course Content Main</title>
</head>
<script>
 function newBook(Course_id, selectedRow)
 {
	 document.getElementById("BookMain").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
 if (Course_id=="")
   {
   document.getElementById("BookMain").innerHTML="";
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
	   document.getElementById("BookMain").innerHTML=xmlhttp.responseText;
	   var x = document.getElementsByClassName("selectedRow");
	   if(x[0]!=undefined) x[0].className = "";
	   document.getElementById(selectedRow).className="selectedRow";
	   $('body').updatePolyfill();
     }
   };
   xmlhttp.open("GET","${pageContext.request.contextPath}/Teacher/Course_Content/New_Book.jsp?Course_id="+Course_id,true);
 xmlhttp.send();
 }

 function editBook(Course_id, selectedRow)
 {
 document.getElementById("BookMain").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
 if (Course_id=="")
   {
   document.getElementById("BookMain").innerHTML="";
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
	   document.getElementById("BookMain").innerHTML="<div style='text-align:center'><img src='${pageContext.request.contextPath}/images/eBook.png'></div>";
	   document.getElementById("BookMain").innerHTML=xmlhttp.responseText;
	   var x = document.getElementsByClassName("selectedRow");
	   if(x[0]!=undefined) x[0].className = "";
	   document.getElementById(selectedRow).className="selectedRow";
     }
   };
   xmlhttp.open("GET","${pageContext.request.contextPath}/List_Books?Course_id="+Course_id,true);
 xmlhttp.send();
 }

 function editBookDetails(Book_id, Course_id)
 {
 document.getElementById("BookDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
 if (Book_id=="")
   {
   document.getElementById("BookDetails").innerHTML="";
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
	   document.getElementById("BookDetails").innerHTML=xmlhttp.responseText;
	   $('body').updatePolyfill();
     }
   };
   xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Book_Details?Book_id="+Book_id+"&Course_id="+Course_id,true);
 xmlhttp.send();
 }

 function deleteFile(File_id, Book_id, File_Name, Account_id, School_id)
 {
 if (Book_id=="" && File_Name=="")
   {
   document.getElementById("Book_File"+File_id).innerHTML="";
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

 	  document.getElementById("Book_File"+File_id).innerHTML="";
 	  document.getElementById("Book_File"+File_id).innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Delete_File?File_id="+File_id+"&&File_Name="+File_Name+"&&Account_id="+Account_id+"&&School_id="+School_id+"&&EntityIdName=Book_id&&Entity_id="+Book_id+"&&Directory=Books", true);
 xmlhttp.send();
 }
 
 function filesSelected()
 {
 	var x = document.getElementById("Book_Files");
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
       fd.append("Book_Files", document.getElementById('Book_Files').files[0]);
       var xhr = new XMLHttpRequest();
       xhr.upload.addEventListener("progress", uploadProgress, false);
       //xhr.addEventListener("load", uploadComplete, false);
       //xhr.addEventListener("error", uploadFailed, false);
       //xhr.addEventListener("abort", uploadCanceled, false);
       xhr.open("POST", "${pageContext.request.contextPath}/Manage_Book");
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
              <a href="${pageContext.request.contextPath}/Teacher/Course_Content/Manage_Book.jsp">e-Books</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
		  	<%@include file="../includes/Adsense_Top.jsp" %>
	          <h4> Course e-Books</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="fa fa-book"></span>e-Books</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">e-Books Content Authority List</code>
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
                          <th colspan="4" style="text-align:center;">Actions</th>                                                                                                                                                                
                        </tr>
                      </thead>
                      <tbody>
					<c:forEach items="${TeacherContent.contentList}" var="ContentList"> 
				    <tr id="${ContentList[0]}${ContentList[2]}${ContentList[4]}">
					    <td style="width:20px;"><i class="fa fa-circle text-warning fs8 pr15"></i></td>
						<td><c:out value="${ContentList[1]}"/></td>
						<td><c:out value="${ContentList[3]}"/></td>
						<td><c:out value="${ContentList[5]}"/></td>
						<td style="max-width:70px;border-right:transparent;text-align:center;">
							<div style="display: inline-block; width:70px;">
							<button type="button" class="btn btn-xs btn-system btn-block" onClick="newBook(${ContentList[4]},'${ContentList[0]}${ContentList[2]}${ContentList[4]}');"><i class="fa fa-upload"></i> Upload</button>							
							</div>					
						</td> 
						<td style="width:20px;border-left:transparent;"></td>
  						<td style="max-width:70px; border-right:transparent;text-align:center;">
							<div style="display: inline-block; width:70px;">
							<button type="button" class="btn btn-xs btn-primary btn-block"  onClick="editBook(${ContentList[4]},'${ContentList[0]}${ContentList[2]}${ContentList[4]}');"><i class="fa fa-edit"></i> Edit</button>
							</div>  						
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
            <div class="bs-component" id="BookMain">
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