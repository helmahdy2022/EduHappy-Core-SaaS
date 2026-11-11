<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
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
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_School_Calendar_Teacher",true);
xmlhttp.send();
}

</script>
        <div class="topbar-right">
<!--           <div class="ib topbar-dropdown">
            <label for="topbar-multiple" class="control-label pr10 fs11 text-muted">Reporting Period</label>
            <select id="topbar-multiple" class="hidden">
              <optgroup label="Filter By:">
                <option value="1-1">Last 30 Days</option>
                <option value="1-2" selected="selected">Last 60 Days</option>
                <option value="1-3">Last Year</option>
              </optgroup>
            </select>
          </div> -->
          <div class="ml15 ib va-m" id="toggle_sidemenu_r">
            <a href="#" onclick="getSchoolCalendar();" class="pl5">
              <i class="fa fa-sign-in fs22 text-primary"></i>
              <!-- <span class="badge badge-danger badge-hero">3</span> -->
            </a>
          </div>
        </div>