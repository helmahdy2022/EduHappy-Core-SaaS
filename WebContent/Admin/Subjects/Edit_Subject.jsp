<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Edit Subject</title>

<script>
 function showGrades(str)
 {
 if (str=="")
   {
	 document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
	 document.getElementById("Subject").innerHTML="<select><option >Select Subject</option></select>";
	 document.getElementById("SubjectDetails").innerHTML="";
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
	 document.getElementById("Subject").innerHTML="<select><option >Select Subject</option></select>";
	 document.getElementById("SubjectDetails").innerHTML="";
	  document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 // end of ShowGrade function
  function showSubjects(str)
 {
 if (str=="")
   {
	 document.getElementById("Subject").innerHTML="<select><option >Select Subject</option></select>";
	 document.getElementById("SubjectDetails").innerHTML="";
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
     document.getElementById("Subject").innerHTML=xmlhttp.responseText;
	 document.getElementById("SubjectDetails").innerHTML="";
	  document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Subjects?Grade_id="+str,true);
 xmlhttp.send();
 }
 //==========End of showSubjects function=================
  function showSubjectDetails(Subject_id)
 {
 if (Subject_id=="")
   {
   document.getElementById("SubjectDetails").innerHTML="";
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
     document.getElementById("SubjectDetails").innerHTML=xmlhttp.responseText;
     document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Subject_Details?Subject_id="+Subject_id,true);
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
<div class="pagewrap">
<div class="header">
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
<div class="content">
	<div class="leftContent"></div>
	<div class="middleContent">
  
    <form name="form1" method="post" action="${pageContext.request.contextPath}/Manage_Subject">
      <table class="parentTable2">
     	<thead>
	      <tr><th colspan="3">Edit Subject</th></tr>
	    </thead>
          <tr>
          <td><label>School Name</label></td>
          <td><label>Grade</label></td>
          <td><label>Subject</label></td>
          </tr>
          <tr>
          <td>
           	<select name="School" id="School" onChange="showGrades(this.value)" required>
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
  		</td>         
          <td>
	         <select name="Grade" id="Grade" onChange="showSubjects(this.value)" required>
	 			 <option value="">Select Grade</option>
	 		</select> 		 			
		 </td>
         <td>
	         <select name="Subject" id="Subject" onChange="showSubjectDetails(this.value)" required>
	 			 <option value="">Select Subject</option>
	 		</select> 		 			
 		</td>
          </tr>
      </table>
      <div id="SubjectDetails" ></div> 
      <div id="DeleteMessage" ></div>
 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
    </form>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
</div>
<div class="footer">  <%@include file="../includes/AdminFooter.jsp" %></div>
</div>

</body>
</html>