<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Edit Division</title>
<script>
 function showGrades(str)
 {
 if (str=="")
   {
	 document.getElementById("Grade").innerHTML="<select><option>Select Grade</option></select>";
	 document.getElementById("Division").innerHTML="<select><option>Select Division</option></select>";
	 document.getElementById("DivisionDetails").innerHTML="";
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
     document.getElementById("Grade").innerHTML=xmlhttp.responseText;
	 document.getElementById("Division").innerHTML="<select><option>Select Division</option></select>";
	 document.getElementById("DivisionDetails").innerHTML="";
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 // end of ShowGrade function
  function showDivisions(str)
 {
 if (str=="")
   {
	 document.getElementById("Division").innerHTML="<select><option>Select Division</option></select>";
	 document.getElementById("DivisionDetails").innerHTML="";
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
     document.getElementById("Division").innerHTML=xmlhttp.responseText;
     document.getElementById("DivisionDetails").innerHTML="";
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Divisions?Grade_id="+str,true);
 xmlhttp.send();
 }
 //==========End of showDivisions function=================
  function showDivisionDetails(Division_id)
 {
document.getElementById("DivisionDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Division_id=="")
   {
   document.getElementById("DivisionDetails").innerHTML="";
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
     document.getElementById("DivisionDetails").innerHTML=xmlhttp.responseText;
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Division_Details?Division_id="+Division_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Divisions</a> <a href="${pageContext.request.contextPath}/Admin/Divisions/Edit_Division.jsp" class="current">Edit Division</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Edit Division</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Edit Division</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Division">    				
			            <div class="control-group">
						  <label class="control-label">School Name</label>
			              <div class="controls">	              
				           	<select name="School" id="School" class="span11" onChange="showGrades(this.value)">
				 			 <option value=""> 
							  	Select School
							  </option>
							  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
						       	<option value="${School_id}"
						       	   <c:if test="${Student_Details.school_id==School_id}">
					       			selected
					       		   </c:if>
						       	><c:out value='${Schools.school_Name[position.index]}'/></option>
						      </c:forEach>	  
							</select>							 											
						 </div>
			            </div>
						<div class="control-group">
						  <label class="control-label">Grade Name</label>
			              <div class="controls"> 						 
					        <select name="Grade" id="Grade" class="span11" onChange="showDivisions(this.value)">
					 			 <option value="">Select Grade</option>
					 		</select> 	
	 					  </div>
	 					</div>	
						<div class="control-group">
						  <label class="control-label">Division Name</label>
			              <div class="controls">
					        <select name="Division" size="1" id="Division" class="span11" onChange="showDivisionDetails(this.value)">
					 			 <option value="">Select Division</option>
					 		</select> 					 		  						 
	 					  </div>
	 					</div>	 						            					      
				      <div id="DivisionDetails" ></div>  
				      <div id="DeleteMessage" ></div>        
				      <div id="DeleteMessage" ></div> 
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
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>