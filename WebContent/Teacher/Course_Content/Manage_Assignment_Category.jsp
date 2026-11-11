<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">

<title>Course Content Main</title>
</head>
<script>
 function newCat(Course_id, selectedRow)
 {
 document.getElementById("Cat").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
 if (Course_id=="")
   {
   document.getElementById("Cat").innerHTML="";
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
	   document.getElementById("CatDetails").innerHTML="";
	   document.getElementById("Cat").innerHTML=xmlhttp.responseText;
	   var x = document.getElementsByClassName("selectedRow");
	   if(x[0]!=undefined) x[0].className = "";
	   document.getElementById(selectedRow).className="selectedRow";
     }
   };
   xmlhttp.open("GET","New_Assignment_Cat.jsp?Course_id="+Course_id,true);
 xmlhttp.send();
 }

 function showAssignmentCategoryDetails(Category_id, Course_id, Index, selectedRow)
 {
document.getElementById("Cat").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Category_id=="")
   {
   document.getElementById("Cat").innerHTML="";
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
     document.getElementById("Cat").innerHTML=xmlhttp.responseText;
	   var x = document.getElementsByClassName("selectedRow");
	   if(x[0]!=undefined) x[0].className = "";
	   document.getElementById(selectedRow).className="selectedRow";
     }
   };
   xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Category_Details?Category_id="+Category_id+"&&Course_id="+Course_id+"&&Index="+Index+"&&FromTeacher=Yes",true);
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
  <script src="${pageContext.request.contextPath}/Global/lib/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.form.js"></script> 
 <script>
  $(document).ready(function() 
{ 
    $('#form1').ajaxForm({target:'#Cat'}); 


}); 
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
              <a href="${pageContext.request.contextPath}/Teacher/Course_Content/Manage_Assignment_Category.jsp?Page=Assignment_Category">Assignment Category</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
  	<form class="form-horizontal" role="form" method="post" name="form1" id="form1" action="${pageContext.request.contextPath}/Manage_Assignment_Cat">
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
		  	<%@include file="../includes/Adsense_Top.jsp" %>
	          <h4>Assignment Category</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="fa fa-tags"></span> Assignment Category</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Assignment Category Content Authority List</code>
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
                          <th colspan="2" style="text-align:center;">New Category</th>   
                          <th colspan="2" style="text-align:center;">Manage Category</th>                                                                                                                                                                                       
                        </tr>
                      </thead>
                      <tbody>
					<c:forEach items="${TeacherContent.contentList}" var="ContentList"> 
				    <tr id="<c:out value='${ContentList[0]}${ContentList[2]}${ContentList[4]}'/>">
					    <td style="width:20px;"><i class="fa fa-circle text-warning fs8 pr15"></i></td>
						<td><c:out value="${ContentList[1]}"/></td>
						<td><c:out value="${ContentList[3]}"/></td>
						<td><c:out value="${ContentList[5]}"/></td>
						<td style="max-width:70px;border-right:transparent;text-align:center;">
							<div style="display: inline-block; width:75px;">							
 							<button type="button" class="btn btn-xs btn-system btn-block" onClick="newCat(${ContentList[4]}, '${ContentList[0]}${ContentList[2]}${ContentList[4]}');"><i class="fa fa-plus"></i> Add New </button>
							</div>					
						</td> 
						<td style="width:20px;border-left:transparent;"></td>
  						<td style="border-right:transparent;text-align:center;">

					         <select name="AssignmentCategory" class="form-control" id="AssignmentCategory"  onChange="showAssignmentCategoryDetails(this.value,${ContentList[4]}, this.selectedIndex, '${ContentList[0]}${ContentList[2]}${ContentList[4]}');">
					 			 <option value="">Select Category</option>
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
            <div class="bs-component">
                <table class="table table-bordered mbn">
                  <tbody>
				    <tr>
				    <td style="vertical-align:top;border-left:transparent">
					 <div id="Cat">
					 	<c:if test="${not empty sessionScope.message}">
					  		<div class="message">${sessionScope.message}</div> 
					  		<c:remove var="message" scope="session" />
					 	</c:if>
					 </div>
					<div id="CatDetails"></div>	
					<div id="DeleteMessage"></div>			    
 					<input type="hidden" name="FromTeacher" id="FromTeacher" value="Yes">
  		 			<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </td>
				    </tr>
                  </tbody>
                </table>  
            </div>
          </div>
        </div>
      </div>
      </form>
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