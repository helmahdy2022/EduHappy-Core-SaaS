<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Edit Assignment Category</title>

<script>
 function showGrades(str)
 {
 if (str=="")
   {
	   document.getElementById("Grade").innerHTML="<option>Select Grade</option>";
	   document.getElementById("Course").innerHTML="<option>Select Course</option>";
	   document.getElementById("AssignmentCategory").innerHTML="<option>Select Category</option>";
	   document.getElementById("AssignmentCategoryDetails").innerHTML="";
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
	   document.getElementById("Course").innerHTML="<option>Select Course</option>";
	   document.getElementById("AssignmentCategory").innerHTML="<option>Select Category</option>";
	   document.getElementById("AssignmentCategoryDetails").innerHTML="";
	   document.getElementById("DeleteMessage").innerHTML="";
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
	   document.getElementById("AssignmentCategory").innerHTML="<option>Select Category</option>";
	   document.getElementById("AssignmentCategoryDetails").innerHTML="";
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
     document.getElementById("Course").innerHTML=xmlhttp.responseText;
	   document.getElementById("AssignmentCategory").innerHTML="<option>Select Category</option>";
	   document.getElementById("AssignmentCategoryDetails").innerHTML="";
	   document.getElementById("DeleteMessage").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Courses?Grade_id="+str,true);
 xmlhttp.send();
 }
 //==========End of showCourses function=================
   function showAssignmentCategory(str)
  {
  if (str=="")
    {
	   document.getElementById("AssignmentCategory").innerHTML="<option>Select Category</option>";
	   document.getElementById("AssignmentCategoryDetails").innerHTML="";
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
      document.getElementById("AssignmentCategory").innerHTML=xmlhttp.responseText;
	   document.getElementById("AssignmentCategoryDetails").innerHTML="";
	   document.getElementById("DeleteMessage").innerHTML="";
      }
    };
  xmlhttp.open("GET","${pageContext.request.contextPath}/List_Assignment_Cat?Course_id="+str,true);
  xmlhttp.send();
  }
  
  function showAssignmentCategoryDetails(Category_id)
  {
  if (Category_id=="")
    {
    document.getElementById("AssignmentCategoryDetails").innerHTML="";
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
      document.getElementById("AssignmentCategoryDetails").innerHTML=xmlhttp.responseText;
      document.getElementById("DeleteMessage").innerHTML="";
      }
    };
  xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Category_Details?Category_id="+Category_id,true);
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
<div class="header">
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
<div class="content">
	<div class="leftContent"></div>
	<div class="middleContent">
  
    <form name="form1" method="post" action="${pageContext.request.contextPath}/Manage_Assignment_Cat">
      <table class="parentTable2">
      	<thead>
	      <tr><th colspan="4">Edit Assignment Category</th></tr>
	    </thead>
          <tr>
          <td><label>School Name</label></td>
          <td><label>Grade</label></td>
          <td><label>Course</label></td>
          <td><label>Assignment Category</label></td>
          </tr>
          <tr>
          <td>
           	<select name="School" id="School" onChange="showGrades(this.value)">
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
         <select name="Grade" id="Grade" onChange="showCourses(this.value)">
 			 <option value="">Select Grade</option>
 		</select> 		 			
		 </td>
         <td>
	         <select name="Course" id="Course" onChange="showAssignmentCategory(this.value)">
	 			 <option value="">Select Course</option>
	 		</select> 	 			
 		</td>
          <td>
         <select name="AssignmentCategory" id="AssignmentCategory"  onChange="showAssignmentCategoryDetails(this.value)" >
 			 <option value="">Select Category</option>
 		</select> 	 			
        </td> 		
          </tr>
      </table>
      <div id="AssignmentCategoryDetails" > </div>
      <div id="DeleteMessage" > </div>  
     <input type="hidden" name="FromTeacher" id="FromTeacher" value="No">
     <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
    </form>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
</div>
<div class="footer">  <%@include file="../includes/AdminFooter.jsp" %></div>

</body>
</html>