<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Leave</title>
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
		  document.getElementById("LeaveList").innerHTML="";
			$('#Leave_Name').val('');
			$('#Leave_Description').val('');
			$('#Leave_id').val('');
			document.getElementById("Paid").checked=true;
			document.getElementById("Annually").checked=true;
			document.getElementById("All_Employees").checked=true;			
			document.getElementById('All_Employees_Balance').disabled=false;
			document.getElementById('Balance').innerHTML='';
			$('#Carry_Forward').val('');
			$('#All_Employees_Balance').val('');
			document.getElementById('Add').style='display:none'
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Payrolls?School_id="+School_id+"&Dropdown=1",true);
xmlhttp.send();
}
function showLeaveList(Payroll_id)
{
document.getElementById("LeaveList").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Payroll_id=="" && document.getElementById('Leave_id').value!=0)
  {
	$('#Leave_Name').val('');
	$('#Leave_Description').val('');
	$('#Leave_id').val('');
	document.getElementById("Paid").checked=true;
	document.getElementById("Annually").checked=true;
	document.getElementById("All_Employees").checked=true;
	document.getElementById('All_Employees_Balance').disabled=false;
	document.getElementById('Balance').innerHTML='';
	$('#Carry_Forward').val('');
	$('#All_Employees_Balance').val('');
	document.getElementById('Add').style='display:none'

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
	    document.getElementById("LeaveList").innerHTML=xmlhttp.responseText;
	    $('#leaves').DataTable();
		if(document.getElementById('Leave_id').value!=0)
		{
			$('#Leave_Name').val('');
			$('#Leave_Description').val('');
			$('#Leave_id').val('');
			document.getElementById("Paid").checked=true;
			document.getElementById("Annually").checked=true;
			document.getElementById("All_Employees").checked=true;
			document.getElementById('All_Employees_Balance').disabled=false;
			document.getElementById('Balance').innerHTML='';
			$('#Carry_Forward').val('');
			$('#All_Employees_Balance').val('');
			document.getElementById('Add').style='display:none'
		}
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Leaves?Payroll_id="+Payroll_id,true);
xmlhttp.send();
}
function showOneBalance(Add_New,last_i)
{
	//alert(last_i)
if(last_i=="")
{
	last_i="0"
}
document.getElementById("Add_New").style="dispay:block";
if (Add_New=="")
  {
  document.getElementById("Balance").innerHTML="";
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
	 var newElement = document.createElement('div');
	 newElement.innerHTML = xmlhttp.responseText;
	 document.getElementById("Balance").appendChild(newElement);
	 document.getElementById("i").value=parseInt(last_i)+1;
   	 //document.getElementById("Balance_qty").value=parseInt(last_i)+1;
   	 if(document.getElementById("Balance_qty")!=null)
   	 {
   	   	 document.getElementById("Balance_qty").value=parseInt(document.getElementById("Balance_qty").value)+1;
   	 }
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Admin/HR/Display_Balance.jsp?Add_New="+Add_New+"&last_i="+last_i,true);
xmlhttp.send();
}
function getBalance(Leave_id)
{

if(Leave_id=="")
	{
		document.getElementById('Balance').innerHTML="";
	}
if(parseInt(document.getElementById("Leave_Balance_no").value)>0)
	{
		document.getElementById("Add").style="display:block";
	}
else
	{
		document.getElementById("Add").style="display:none";
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
	  document.getElementById("Balance").innerHTML = xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Leave_Balance?Leave_id="+Leave_id,true);
xmlhttp.send();
}
function deleteBalance(Delete, Leave_Balance_id, i, last_i, Leave_id, Balance_qty)
{ 
if (Balance_qty==1)
{
	document.getElementById('Balance').innerHTML="";
}
if (Delete=="" && Leave_Balance_id=="")
  {
  	document.getElementById("Balance_Row"+i).innerHTML="";
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
sessionExpired();
//alert("i="+i);
//alert(document.getElementById("Balance_Row"+i))
if(document.getElementById("Balance_Row"+i)!=null)
{
	document.getElementById("Balance_Row"+i).parentNode.removeChild(document.getElementById("Balance_Row"+i));  
}
xmlhttp.open("GET","${pageContext.request.contextPath}/Delete_Balance?Delete="+Delete+"&&Leave_Balance_id="+Leave_Balance_id+"&&Leave_id="+Leave_id+"&&i="+i+"&&last_i="+last_i+"&&Balance_qty="+Balance_qty+"&&Page=Balance",true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">HR and Payroll</a> <a href="${pageContext.request.contextPath}/Admin/HR/Leave.jsp" class="current">Leave Type</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Create New Leave Type</h1>
		</div>
		<!--End-breadcrumbs-->
		<div class="container-fluid">
		  <hr>
		<div style="margin-left:10px;color:blue;">${param.message}</div>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Leave Type</h5>
		        </div>
		        <div class="widget-content nopadding">
				  <form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Manage_Leave">		          
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
			           	<select name="Payroll_id" id="Payroll_id" class="span11" onChange="showLeaveList(this.value)" required>
			 			 <option value=""> 
						  	Select Payroll
						  </option> 
						</select> 
					 </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Leave Name *</label>
		              <div class="controls">
		                <input type="text" name="Leave_Name" id="Leave_Name" class="span11"  placeholder="Leave name" required maxlength="100">
		              </div>
		            </div>	           
		            <div class="control-group">
		              <label class="control-label">Description</label>
		              <div class="controls"> 
	 					<textarea name="Leave_Description" id="Leave_Description" class="span11" maxlength="1000"></textarea>		                
		              </div>
		            </div>          
		            <div class="control-group">
		              <label class="control-label">Type *</label>
		              <div class="controls">
					  <input type="radio" name="Payment_Type" id="Paid" value="Paid" checked> Paid<br>		              
					  <input type="radio" name="Payment_Type" id="Unpaid" value="Unpaid">Unpaid<br>	                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Period *</label>
		              <div class="controls">
					  <input type="radio" name="Period" id="Annually" value="Annually" checked>Annually<br>		              
					  <input type="radio" name="Period" id="Monthly" value="Monthly">Monthly<br>	                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Balance *</label>
		              <div class="controls">
					  <input type="radio" name="Balance" id="All_Employees" value="All Employees" onChange="document.getElementById('Balance').innerHTML='';
					  document.getElementById('Add').style='display:none';
					  document.getElementById('All_Employees_Balance').disabled=false" checked>All Employees
					  <input type="number" id="All_Employees_Balance" name="All_Employees_Balance" class="span11" style="width:100px;" value="" step="0.01" required min="0" max="1000000000" required> Days
					  <br>		              
					  <input type="radio" name="Balance" id="Experience_Based" value="Add_New"              
		              onChange="if(document.getElementById('Leave_Balance_no').value>0) {getBalance(Leave_id.value);} else {showOneBalance(this.value,0)};
		              document.getElementById('Add').style='display:block';
		              document.getElementById('All_Employees_Balance').value='';
		              document.getElementById('All_Employees_Balance').disabled=true;">
		              Experience Based<br>
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label"></label>
		              <div class="controls">
						
						<div id="Balance"></div>     
						<input name="i" id="i" type="hidden" value="<c:out value='document.getElementById("Balance_qty").value'/>">
			            <div id="Add" style="display:none;">
						<div style="margin-top:10px;text-align:center;width:86%">
						<input type="button" id="Add_New" name="Add_New" class="btn btn-info" value="Add New"
						 onClick="if(document.getElementById('Balance_qty')!=null) {showOneBalance(this.value,document.getElementById('Balance_qty').value)} else {showOneBalance(this.value,i.value)};">
						<input name="Page" id="Page" type="hidden" value="Balance">
						</div>
						</div> 						         
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Carry Forward Balance</label>
		              <div class="controls"> 
	 					<input type="number" id="Carry_Forward" name="Carry_Forward" class="span11" style="width:200px;" value="" step="0.01" min="0" max="1000000000"> Days             
		              </div>
		            </div> 		            
		            <div class="form-actions">
		            <input type="hidden" name="Leave_id" id="Leave_id">
		            <input type="hidden" name="Leave_Balance_no" id="Leave_Balance_no">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
	            	<div class="requiredSignNote"><label>Fields marked (*) are required</label></div> 
 		 			<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
		          </form>
		        </div>
		      </div>
		     </div>
		    <div id="LeaveList" class="span6">
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