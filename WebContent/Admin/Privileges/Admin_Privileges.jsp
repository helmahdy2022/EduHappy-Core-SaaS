<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Admin Privileges</title>

<script>
function showAdmins()
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
		document.getElementById("Admin_id").innerHTML=xmlhttp.responseText;
	}
};
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Admins",true);
xmlhttp.send();
}
 
 function showFeatures(Admin_id)
 {
 document.getElementById("Features").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
 if (Admin_id=="")
   {
	   document.getElementById("Features").innerHTML="";
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
     	document.getElementById("Features").innerHTML=xmlhttp.responseText; 
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Features?Admin_id="+Admin_id,true);
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
</script>

</head>
<body onload="showAdmins();">
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Admin Privileges</a> <a href="${pageContext.request.contextPath}/Admin/Privileges/Admin_Privileges.jsp" class="current">Manage Admin Privileges</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Admin Privileges</h1>
		</div>
		<!--End-breadcrumbs-->
		<div style="margin-left:10px;color:blue;">${param.message}</div>		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Admin Privileges</h5>
		        </div>
		        <div class="widget-content nopadding">
					<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Privileges">
		            <div class="control-group">
					  <label class="control-label">Admin Name *</label>
		              <div class="controls">
			            <select name="Admin_id" id="Admin_id" class="span11" onChange="showFeatures(this.value)" required>
			 			 <option value=""> 
						  	Select Admin
						  </option>  
						</select>  								              
					  </div>
		            </div>
			        <div id="Features"></div> 												           
		            <div class="form-actions">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>	
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>	
		     <div class="span6">
		     <div class="widget-box">
				  <div id="DeleteMessage"></div>
			 </div>
			 </div>
		 </div>
		</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>