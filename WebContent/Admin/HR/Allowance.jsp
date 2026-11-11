<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Allowance Types</title>
<script>
function listPayrolls(School_id)
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
		  document.getElementById("Payroll_id").innerHTML=xmlhttp.responseText;
		  document.getElementById("AllowanceList").innerHTML="";
			$('#Allowance_Name').val('');
			$('#Allowance_Description').val('');
			$('#Allowance_id').val('');
			document.getElementById("Fixed_Amount").checked=true;
			document.getElementById("Annually").checked=true;
			document.getElementById('Fixed_Amount').disabled=false;
			$('#Fixed_Amount').val('');
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Payrolls?School_id="+School_id+"&Dropdown=1",true);
xmlhttp.send();
}
function showAllowanceList(Payroll_id)
{
document.getElementById("AllowanceList").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Payroll_id=="" && document.getElementById('Allowance_id').value!=0)
  {
	$('#Allowance_Name').val('');
	$('#Allowance_Description').val('');
	$('#Allowance_id').val('');
	document.getElementById("Fixed_Amount").checked=true;
	document.getElementById("Annually").checked=true;
	document.getElementById('Fixed_Amount').disabled=false;
	$('#Fixed_Amount').val('');

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
	    document.getElementById("AllowanceList").innerHTML=xmlhttp.responseText;
	    $('#allowance').DataTable();
		if(document.getElementById('Allowance_id').value!=0)
		{
			$('#Allowance_Name').val('');
			$('#Allowance_Description').val('');
			$('#Allowance_id').val('');
			document.getElementById("Fixed_Amount").checked=true;
			document.getElementById("Annually").checked=true;
			document.getElementById('Fixed_Amount').disabled=false;
			$('#Fixed_Amount').val('');
		}
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Allowance?Payroll_id="+Payroll_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">HR and Payroll</a> <a href="${pageContext.request.contextPath}/Admin/HR/Allowance.jsp" class="current">Allowance Types</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Create New Allowance Type</h1>
		</div>
		<!--End-breadcrumbs-->
		<div class="container-fluid">
		  <hr>
		<div style="margin-left:10px;color:blue;">${param.message}</div>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Allowance Type</h5>
		        </div>
		        <div class="widget-content nopadding">
				  <form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Manage_Allowance">		          
		            <div class="control-group">
					  <label class="control-label">School Name *</label>
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
					  <label class="control-label">Payroll Name *</label>
		              <div class="controls">	              										
			           	<select name="Payroll_id" id="Payroll_id" class="span11" onChange="showAllowanceList(this.value)" required>
			 			 <option value=""> 
						  	Select Payroll
						  </option> 
						</select> 
					 </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Allowance Name *</label>
		              <div class="controls">
		                <input type="text" name="Allowance_Name" id="Allowance_Name" class="span11"  placeholder="Allowance name" required maxlength="100">
		              </div>
		            </div>	           
		            <div class="control-group">
		              <label class="control-label">Description</label>
		              <div class="controls"> 
	 					<textarea name="Allowance_Description" id="Allowance_Description" class="span11" maxlength="1000"></textarea>		                
		              </div>
		            </div>          
		            <div class="control-group">
		              <label class="control-label">Type *</label>
		              <div class="controls">
					  <input type="radio" name="Allowance_Type" id="Fixed" value="Fixed" checked onChange="
					  document.getElementById('Fixed_Amount').disabled=false;
					  document.getElementById('Percentage_Amount').disabled=true;
					  document.getElementById('Percentage_Amount').value='';">Fixed Amount
					  <input type="number" id="Fixed_Amount" name="Fixed_Amount" class="span11" style="width:100px;" value="" step="0.01" min="0" max="1000000000" required><br><br> 		              
					  <input type="radio" name="Allowance_Type" id="Percentage" value="Percentage" onChange="
					  document.getElementById('Percentage_Amount').disabled=false;
					  document.getElementById('Fixed_Amount').disabled=true;
					  document.getElementById('Fixed_Amount').value='';">Percentage (or Multiple) of basic	                
					  <input type="number" id="Percentage_Amount" name="Percentage_Amount" class="span11" style="width:100px;" value="" step="0.01" min="0" max="1000000000" required disabled>%<br><br> 
					  <input type="radio" name="Allowance_Type" id="Specific" value="Specific" onchange="
					  document.getElementById('Fixed_Amount').disabled=true;
					  document.getElementById('Percentage_Amount').disabled=true;
					  document.getElementById('Fixed_Amount').value='';
					  document.getElementById('Percentage_Amount').value='';">Employee Specific<br>	
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Period *</label>
		              <div class="controls">
					  <input type="radio" name="Period" id="Annually" value="Annually" checked>Annually<br>		              
					  <input type="radio" name="Period" id="Monthly" value="Monthly">Monthly<br>	                
		              </div>
		            </div>           
		            <div class="form-actions">
		            <input type="hidden" name="Allowance_id" id="Allowance_id">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
	            	<div class="requiredSignNote"><label>Fields marked (*) are required</label></div> 
 		 			<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
		          </form>
		        </div>
		      </div>
		     </div>
		    <div id="AllowanceList" class="span6">
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>		    
		    </div>
		    <div class="widget-box">
				  <div id="DeleteMessage"></div>
			</div>
		    </div>
		   </div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<div class="row-fluid">
<div id="footer" class="span12">  <%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>