<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Parent Search</title>

 <script>
 function showParent(First_Name, Last_Name, Username, Parent_id, Page)
 {
 document.getElementById("Parent").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
 document.getElementById("Parent_Student").innerHTML="";
 if (First_Name==""&&Last_Name==""&&Username==""&&Parent_id=="") 
   {
   document.getElementById("Parent").innerHTML="";
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
     document.getElementById("Parent").innerHTML=xmlhttp.responseText;
     $('#parents').DataTable();
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Parents?First_Name="+First_Name+"&Last_Name="+Last_Name+"&Username="+Username+"&Parent_id="+Parent_id+"&Page="+Page,true);
 xmlhttp.send();
 }
 
 function showParentStudents(Parent_id)
 {
 document.getElementById("Parent_Student").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
 if (First_Name==""&&Last_Name==""&&Username==""&&Parent_id=="") 
   {
   document.getElementById("Parent_Student").innerHTML="";
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
     document.getElementById("Parent_Student").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Remove_Link_Parent_Student?Parent_id="+Parent_id,true);
 xmlhttp.send();
 }
 
 function removeParentStudent(Parent_id, Student_id)
 {
 document.getElementById("Student_Parent"+Student_id+Parent_id).innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";

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
    	document.getElementById("Student_Parent"+Student_id+Parent_id).innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Delete_Parent_Student?Parent_id="+Parent_id+"&&Student_id="+Student_id,true);
 xmlhttp.send();
 }
 </script>
</head>

<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> 
			<c:if test="${param.Page=='Edit_Parent.jsp'}">
		  		<a href="#" class="tip-bottom">Parents</a> <a href="${pageContext.request.contextPath}/Admin/Parents/Search_Parent.jsp?Page=Edit_Parent.jsp" class="current">
		  		Edit Parent
		  		</a> 
		    </c:if>
			<c:if test="${param.Page=='Link_Parent_Student.jsp'}">
		  		<a href="#" class="tip-bottom">Parents</a> <a href="${pageContext.request.contextPath}/Admin/Parents/Search_Parent.jsp?Page=Link_Parent_Student.jsp" class="current">
		  		Link Parent to Student
		  		</a> 
		    </c:if>
			<c:if test="${param.Page=='Remove_Link_Parent_Student'}">
		  		<a href="#" class="tip-bottom">Parents</a> <a href="${pageContext.request.contextPath}/Admin/Parents/Search_Parent.jsp?Page=Remove_Link_Parent_Student" class="current">
		  		Remove Link Parent to Student
		  		</a> 
		    </c:if>		    
		  </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Search Parent</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Search Parent</h5>
		        </div>
		        <div class="widget-content nopadding">
    			<form name="form1" id="form1" class="form-horizontal" action="">
					<div class="control-group">
					  <label class="control-label">First Name</label>
		              <div class="controls">
            			 <input type="text" name="First_Name" id="First_Name" class="span11" maxlength="50">					
 					  </div>
 					</div>
 					<div class="control-group">
					  <label class="control-label">Last Name</label>
		              <div class="controls">
            			 <input type="text" name="Last_Name" id="Last_Name" class="span11" maxlength="50">				
 					  </div>
 					</div>
 					<div class="control-group">
					  <label class="control-label">Username</label>
		              <div class="controls">
            			 <input name="Username" type="text" id="Username" class="span11">			
 					  </div>
 					</div> 					
  					<div class="control-group">
					  <label class="control-label">Parent ID</label>
		              <div class="controls">
            			 <input name="Parent_id" type="number" class="span11" id="Parent_id" max="1000000000">			
 					  </div>
 					</div> 
		            <div class="form-actions">
		            <input type="button" name="Submit" id="Submit" value="Search" class="btn btn-success" onClick="showParent(First_Name.value, Last_Name.value, Username.value, Parent_id.value, '${param.Page}')">
		            </div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
 				</form>
 			</div>
 		</div>
     </div>
   		<div id="Parent" class="span6">
   			<%@include file="../includes/Adsense_Inline.jsp" %>
   		</div>
    </div>
	<div class="row-fluid">
   		<div id="Parent_Student" class="span6">

   		</div>
	</div>
   </div>
  </div>
 	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"> <%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>