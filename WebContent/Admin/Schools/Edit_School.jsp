<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">

<title>Manage School</title>

<script>

function showSchoolDetails(School_id)
 {
document.getElementById("SchoolDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (School_id=="")
   {
   document.getElementById("SchoolDetails").innerHTML="";
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
	  document.getElementById("SchoolDetails").innerHTML=xmlhttp.responseText;
      document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_School_Details?School_id="+School_id,true);
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

<body>

<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>

	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Schools</a> <a href="${pageContext.request.contextPath}/Admin/Schools/Edit_School.jsp" class="current">Edit School</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Edit School</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Edit School</h5>
		        </div>
		        <div class="widget-content nopadding">
				    <form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Manage_School">
		            <div class="control-group">
					  <label class="control-label">School Name</label>		            
		              <div class="controls">
				            <select name="School_id" id="School_id" class="span11" onChange="showSchoolDetails(this.value)">
							  <option value=""> 
							  	Select School
							  </option>						  
						         <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
						          	<option value="${School_id}"><c:out value='${Schools.school_Name[position.index]}'/></option>
						          </c:forEach>			  			
							</select>
		              </div>
		            </div>					      
						<div id="SchoolDetails" > </div>
						<div id="DeleteMessage" > </div> 
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>	
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
		 </div>
		</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>


<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12">  <%@include file="../includes/AdminFooter.jsp" %></div></div>

</body>
</html>