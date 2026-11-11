<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Edit Class</title>

<script>
 function showGrades(str)
 {
 if (str=="")
   {
	 document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
	 document.getElementById("Class").innerHTML="<select><option >Select Class</option></select>";
	 document.getElementById("ClassDetails").innerHTML="";
	 document.getElementById("AuthorityDetails").innerHTML="";
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
	 document.getElementById("Class").innerHTML="<select><option >Select Class</option></select>";
	 document.getElementById("ClassDetails").innerHTML="";
	 document.getElementById("AuthorityDetails").innerHTML="";
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 // end of ShowGrade function
  function showClasses(str)
 {
 if (str=="")
   {
	 document.getElementById("Class").innerHTML="<select><option >Select Class</option></select>";
	 document.getElementById("ClassDetails").innerHTML="";
	 document.getElementById("AuthorityDetails").innerHTML="";
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
     document.getElementById("Class").innerHTML=xmlhttp.responseText;
	 document.getElementById("ClassDetails").innerHTML="";
	 document.getElementById("AuthorityDetails").innerHTML="";
	 document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Classes?Grade_id="+str,true);
 xmlhttp.send();
 }
 //==========End of showClasses function=================
  function showClassDetails(Class_id, Grade_id)
 {
document.getElementById("ClassDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Class_id=="")
   {
   document.getElementById("ClassDetails").innerHTML="";
	 document.getElementById("AuthorityDetails").innerHTML="";
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
     document.getElementById("ClassDetails").innerHTML=xmlhttp.responseText;
	 document.getElementById("AuthorityDetails").innerHTML="";
	 document.getElementById("DeleteMessage").innerHTML="";
     showAuthority(Class_id, Grade_id)
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Class_Details?Class_id="+Class_id,true);
 xmlhttp.send();
 }
  function showAuthority(Class_id, Grade_id)
  {
  if (Grade_id==""||Class_id=="")
    {
    document.getElementById("AuthorityDetails").innerHTML="";
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
      document.getElementById("AuthorityDetails").innerHTML=xmlhttp.responseText;
      }
    };
  xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Authority_Details?Grade_id="+Grade_id+"&Class_id="+Class_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Classrooms</a> <a href="${pageContext.request.contextPath}/Admin/Classes/Edit_Class.jsp" class="current">Edit Classroom</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Edit Classroom</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Edit Classroom</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" method="post"  id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Class">
			            <div class="control-group">
						  <label class="control-label">School Name</label>
			              <div class="controls">	              
				            <select name="School_id" id="School_id" class="span11" onChange="showGrades(this.value)" required>
				 			 <option value=""> 
							  	Select School
							  </option>
							  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
						       	<option value="${School_id}"><c:out value='${Schools.school_Name[position.index]}'/></option>
						      </c:forEach>	  
							</select>  											
						 </div>
			            </div>
						<div class="control-group">
						  <label class="control-label">Grade Name</label>
			              <div class="controls">
					         <select name="Grade" id="Grade" required class="span11" onChange="showClasses(this.value)">
					 			 <option value="">Select Grade</option>
					 		</select>  						 
	 					  </div>
	 					</div>	
						<div class="control-group">
						  <label class="control-label">Classroom Name</label>
			              <div class="controls">
					         <select name="Class" size="1" id="Class" class="span11" onChange="showClassDetails(this.value, Grade.value)">
					 			 <option>Select Class</option>
					 		</select>  						 
	 					  </div>
	 					</div>	 						            					      
				      <div id="ClassDetails" ></div> 
				      <div id="DeleteMessage" ></div> 
				      <div id="AuthorityDetails" ></div>       
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