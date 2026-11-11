<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>New Subject</title>
<script>
 function showGrades(str)
 {
 if (str=="")
   {
	 document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
	 document.getElementById("Course").innerHTML="";
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
     document.getElementById("Course").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 
 // end of ShowGrade function
 function showCourses(str)
 {
 if (str=="")
   {
   document.getElementById("Course").innerHTML="";
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
     document.getElementById("Course").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Courses_No_Subject?Grade_id="+str,true);
 xmlhttp.send();
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
	      <tr><th colspan="2">Create New Subject</th></tr>
	    </thead>
        <tr>
          <td><label>School *</label></td>
           <td><label>Grade *</label></td>
           </tr>
           <tr>
          <td>
            <select name="School_id" id="School_id"  onChange="showGrades(this.value)" required>
 			 <option value=""> 
			  	Select School
			  </option>
			  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
		       	<option value="${School_id}"><c:out value='${Schools.school_Name[position.index]}'/></option>
		      </c:forEach>	  
			</select>       
  		</td>        
          <td>
	         <select name="Grade" id="Grade" onChange="showCourses(this.value)" required>
	 			 <option value="">Select Grade</option>
	 		</select> 		 			
		 </td>
        </tr>
        </table>
        <table class="childTable">
        <tr>
          <td><label for="Subject_Name">Subject Name *</label></td>
          <td>
          <input type="text" name="Subject_Name" id="Subject_Name" class="mediumText" required maxlength="50">
          </td>
        </tr>
         <tr>
          <td colspan="2"><div id="Course"> </div></td>
        </tr>
        <tr>
          <td colspan="2"><div class="submitHolder"><input type="submit" name="button" id="button" value="Submit" class="smallButton"></div>
          <div class="requiredSignNote">Fields marked (*) are required</div>
          </td>
        </tr>
      </table>
 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
    </form>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
</div>
<div class="footer">  <%@include file="../includes/AdminFooter.jsp" %></div>
</div>
</body>
</html>