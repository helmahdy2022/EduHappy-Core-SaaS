<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>New Assignment Category</title>
<script>
 function showGrades(str)
 {
 if (str=="")
   {
	   document.getElementById("Grade").innerHTML="<option>Select Grade</option>";
	   document.getElementById("Course").innerHTML="<option>Select Course</option>";
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
	   document.getElementById("Course").innerHTML="<option>Select Course</option>";
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
	   document.getElementById("Course").innerHTML="<option>Select Course</option>";
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
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Courses?Grade_id="+str,true);
 xmlhttp.send();
 }
 //==========End of showCourses function=================
</script>

</head>

<body>
<div class="header">
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
<div class="content">
	<div class="leftContent"></div>
	<div class="middleContent">
    <form name="form1" method="post" action="${pageContext.request.contextPath}/Manage_Assignment_Cat">
      <table class="parentTable2">
       	<thead>
	      <tr><th colspan="3">Create New Assignment Category</th></tr>
	    </thead>
        <tr>
          <td><label>School *</label></td>
          <td><label>Grade *</label></td>
          <td><label>Course *</label></td>
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
         <td>
	         <select name="Course" id="Course" required>
	 			 <option value="">Select Course</option>
	 		</select> 		 			
 		</td>
        </tr>
        </table>
        <table class="childTable">
        <tr>
          <td><label for="Category_Name">Assignment Category Name *</label></td>
          <td>
          <input type="text" name="Category_Name" id="Category_Name" class="bigText" maxlength="100" required>
          </td>
        </tr>
       <tr>
            <td><label>Assignment Category Grade *</label></td>
          <td><input name="Category_Grade" type="number" class="mediumNumber" id="Category_Grade" min="0" max="1000000000" required></td>

        </tr>
        
                                           
        <tr>
          <td colspan="2"><input name="Contribute" type="checkbox" id="Contribute" checked>Contribute to the total final grade</td>
        </tr> 

        <tr>
          <td colspan="2"><input name="Create_Authority" type="checkbox" id="Create_Authority">Class Teacher can create Assignments under this Category</td>
        </tr> 

        <tr>
          <td colspan="2"><div class="submitHolder"><input type="submit" name="button" id="button" value="Submit"></div>
          <div class="requiredSignNote">Fields marked (*) are required</div>
          </td>
        </tr>
      </table>
			<input type="hidden" name="FromTeacher" id="FromTeacher" value="No">
 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
    </form>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
</div>
<div class="footer">  <%@include file="../includes/AdminFooter.jsp" %></div>



</body>
</html>