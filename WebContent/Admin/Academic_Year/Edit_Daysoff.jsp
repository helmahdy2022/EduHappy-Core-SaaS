<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Edit Days-off</title>
<script>

function showDaysoffDetails(Daysoff_id)
{
document.getElementById("DaysoffDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Daysoff_id=="")
  {
  document.getElementById("DaysoffDetails").innerHTML="";
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
      document.getElementById("DaysoffDetails").innerHTML=xmlhttp.responseText;
      $('body').updatePolyfill();
    }
  };
//xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Daysoff_Details?Daysoff_id="+Daysoff_id,true);
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Daysoff_Schools_Grades?Daysoff_id="+Daysoff_id,true);
xmlhttp.send();
}

function showSchools(str)
 {
    var checkbox = document.getElementById ("All_Schools");
    if (checkbox.checked) {
   	 document.getElementById("Schools").style = 'Display:none';
    }
    else 
    {
   	 document.getElementById("Schools").style = 'Display:block';
    }

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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Academic Year</a> <a href="${pageContext.request.contextPath}/Admin/Academic_Year/List_Daysoff.jsp" class="current">Edit Days-off</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Edit Days-off</h1>
		</div>
		<!--End-breadcrumbs-->
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Edit Days-off</h5>
		        </div>
		        <div class="widget-content nopadding">
				    <form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Manage_Daysoff">
		            <div class="control-group">
					  <label class="control-label">Days-off Name</label>		            
		              <div class="controls">
			            <select name="Daysoff_id" id="Daysoff_id" class="span11" onChange="showDaysoffDetails(this.value)">
						  <option value=""> 
						  	Select Days-off
						  </option>		          
					         <c:forEach items="${Daysoff.daysoff_id}" var="Daysoff_id" varStatus="position">
					          	<option value="${Daysoff_id}">
					          	<c:out value='${Daysoff.daysoff_Name[position.index]}'/> - <c:out value='${Daysoff.e_Date[position.index]}'/> - <c:out value='${Daysoff.s_Date[position.index]}'/>
					          	</option>
					          </c:forEach>			  
						</select> 
		              </div>
		            </div>					      
						<div id="DaysoffDetails"> </div>
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
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>

</body>
</html>