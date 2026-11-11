<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function getParentSchoolCalendar()
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
    document.getElementById("SchoolCalendar").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_School_Calendar?Student_id="+document.getElementById("topbar-multiple").value,true);
xmlhttp.send();
}

function getSchoolCalendar()
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
    document.getElementById("SchoolCalendar").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_School_Calendar",true);
xmlhttp.send();
}

function hideAndDisplayStudentList()
{

if(document.getElementById("StudentList").style.display=='none')
	document.getElementById("StudentList").style = "display:block";
else
	document.getElementById("StudentList").style = "display:none";
}
</script>
        <div class="topbar-right">
  		<c:if test="${Source == 'Parent'}">
           <div  id="StudentList" style="display:none;">
            <label for="topbar-multiple" class="control-label pr10 fs11 text-muted">School Calendar</label>
            <select id="topbar-multiple" class="hidden" onchange="getParentSchoolCalendar();" >
              <optgroup label="Student Name:">              
              	<c:forEach items="${child.student_id}" var="Student_id"  varStatus="position"> 
              	<option value="${Student_id}">
                <c:out value='${child.first_Name[position.index]}'/>&nbsp;<c:out value='${child.last_Name[position.index]}'/>
              </option>
             </c:forEach>
              </optgroup>
            </select>                      
          </div>
        </c:if>
          <div class="ml15 ib va-m" id="toggle_sidemenu_r">
            <c:choose>
            <c:when test="${Source == 'Parent'}">
            	<a href="#" onclick="getParentSchoolCalendar();hideAndDisplayStudentList();" class="pl5">            
              	<i class="fa fa-sign-in fs22 text-primary"></i>
              	<!-- <span class="badge badge-danger badge-hero">3</span> -->
           		 </a>
            </c:when>
            <c:otherwise>
            	<a href="#" onclick="getSchoolCalendar();" class="pl5">            
              	<i class="fa fa-sign-in fs22 text-primary"></i>
              	<!-- <span class="badge badge-danger badge-hero">3</span> -->
           		 </a>
            </c:otherwise>
            </c:choose>            
          </div>
        </div>