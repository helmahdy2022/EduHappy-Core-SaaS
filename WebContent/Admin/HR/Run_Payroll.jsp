<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Run Payroll</title>
<script>
function listPayrolls(School_id)
{ 
if(School_id=="")
	{
		document.getElementById("Payroll").innerHTML="";
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
		  document.getElementById("Payroll_id").innerHTML=xmlhttp.responseText;
		  document.getElementById("Payroll").innerHTML="";
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Payrolls?School_id="+School_id+"&Dropdown=1",true);
xmlhttp.send();
}
function runPayroll(Payroll_id)
{ 
document.getElementById("Payroll").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if(Payroll_id=="")
	{
		document.getElementById("Payroll").innerHTML="";
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
		  document.getElementById("Payroll").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Run_Payroll?Payroll_id="+Payroll_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">HR and Payroll</a> <a href="${pageContext.request.contextPath}/Admin/HR/Run_Payroll.jsp" class="current">Run Payroll</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Run Payroll</h1>
		</div>
		<!--End-breadcrumbs-->
		<form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Process_Payroll">		
		<div class="container-fluid">
		  <hr>
		<div style="margin-left:10px;color:blue;">${param.message}</div>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Run Payroll</h5>
		        </div>
		        <div class="widget-content nopadding">          
		            <div class="control-group">
					  <label class="control-label">School Name </label>
		              <div class="controls">	              										
			           	<select name="School" id="School" class="span11" onChange="listPayrolls(this.value);" required>
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
					  <label class="control-label">Payroll Name </label>
		              <div class="controls">	              										
			           	<select name="Payroll_id" id="Payroll_id" class="span11" onChange="runPayroll(this.value)" required>
			 			 <option value=""> 
						  	Select Payroll
						  </option> 
						</select> 
					 </div>
		            </div>
		        </div>
		      </div>
		     </div>
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
		    <div class="widget-box">
				  <div id="Payroll"></div>
			</div>
		    </div>
		   </div>
	 		<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
          </form>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<div class="row-fluid">
<div id="footer" class="span12">  <%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>