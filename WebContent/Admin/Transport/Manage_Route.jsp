<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Manage Route</title>

<script>
 function showVehicles(School_id, Vehicle_id)
 {
 if (School_id=="")
   {
	   document.getElementById("Vehicle_id").innerHTML="<select><option value=''>Select Vehicle</option></select>";
	   document.getElementById("RoutesList").innerHTML="";
	 	$('#Route_Name').val('');
	 	$('#Route_id').val('');
		$('#Driver_id').val('');
 		$('#Matron_id').val('');
	 	$('#Debit_Account_id').val('');
		$('#Credit_Account_id').val('');
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
     	document.getElementById("Vehicle_id").innerHTML=xmlhttp.responseText; 
	  	showFinancialAccounts(School_id);
     	if(Vehicle_id==0)
  	 	{
  	 		$('#Route_Name').val('');
  	 		$('#Route_id').val('');  
  	     	document.getElementById("RoutesList").innerHTML="";
  			$('#Driver_id').val('');
  	 		$('#Matron_id').val('');
  	     	$('#Debit_Account_id').val('');
  			$('#Credit_Account_id').val('');
  	 	}
     	else
     	{
     		$('#Vehicle_id').val(Vehicle_id);
     	}
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Vehicles?School_id="+School_id+"&Dropdown=1",true);
 xmlhttp.send();
 }
 
 function showRoutesList(Vehicle_id)
 {
document.getElementById("RoutesList").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>"; 
if (Vehicle_id=="" && document.getElementById('Route_id').value!=0)
   {
 	$('#Route_Name').val('');
 	$('#Route_id').val('');
 	$('#Driver_id').val('');
 	$('#Matron_id').val('');
	$('#Debit_Account_id').val('');
	$('#Credit_Account_id').val('');
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
 	    document.getElementById("RoutesList").innerHTML=xmlhttp.responseText;
 	   $('#routes').DataTable();
 		if(document.getElementById('Route_id').value!=0)
 		{
 		 	$('#Route_Name').val('');
 		 	$('#Route_id').val('');
 		 	$('#Driver_id').val('');
 		 	$('#Matron_id').val('');
 		 	$('#Debit_Account_id').val('');
 			$('#Credit_Account_id').val('');
 		}
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Routes?Vehicle_id="+Vehicle_id,true);
 xmlhttp.send();
 }
 function showAdmins()
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
 		document.getElementById("Driver_id").innerHTML=xmlhttp.responseText;
 		document.getElementById("Matron_id").innerHTML=xmlhttp.responseText;
 	}
 };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Admins",true);
 xmlhttp.send();
 }
 function showFinancialAccounts(School_id)
 {
 if (School_id=="")
   {
 	   document.getElementById("Debit_Account_id").innerHTML="<select><option value=''>Select Financial Account</option></select>";
 	   document.getElementById("Credit_Account_id").innerHTML="<select><option value=''>Select Financial Account</option></select>";

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
     	document.getElementById("Debit_Account_id").innerHTML=xmlhttp.responseText; 
     	document.getElementById("Credit_Account_id").innerHTML=xmlhttp.responseText; 
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Financial_Accounts?School_id="+School_id+"&Dropdown=1",true);
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
<body onload="showAdmins();">
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Transport Management</a> <a href="${pageContext.request.contextPath}/Admin/Transport/Manage_Route.jsp" class="current">Manage Route</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Manage Route</h1>
		</div>
		<!--End-breadcrumbs-->
		<div style="margin-left:10px;color:blue;">${param.message}</div>		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Route</h5>
		        </div>
		        <div class="widget-content nopadding">
					<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Route">
		            <div class="control-group">
					  <label class="control-label">School Name *</label>
		              <div class="controls">
			            <select name="School_id" id="School_id" class="span11" onChange="showVehicles(this.value, 0)" required>
			 			 <option value=""> 
						  	Select School
						  </option>
						  <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
					       	<option value="${School_id}"><c:out value='${Schools.school_Name[position.index]}'/></option>
					      </c:forEach>	  
						</select>  								              
					  </div>
		            </div>
					<div class="control-group">
					  <label class="control-label">Vehicle Name *</label>
		              <div class="controls">
			         <select name="Vehicle_id" id="Vehicle_id" class="span11" onChange="showRoutesList(this.value)" required>
			 			 <option value="">Select Vehicle</option>
			 		</select>  						
 					  </div>
 					</div>	
					<div class="control-group">
					  <label class="control-label">Route Name *</label>
		              <div class="controls">
						<input type="text" name="Route_Name" id="Route_Name" class="span11" required maxlength="100">  						
 					  </div>
 					</div>	
		            <div class="control-group">
					  <label class="control-label">Driver Name</label>
		              <div class="controls">
			            <select name="Driver_id" id="Driver_id" class="span11">
			 			 <option value=""> 
						  	Select Driver
						  </option>  
						</select>  								              
					  </div>
		            </div>
		            <div class="control-group">
					  <label class="control-label">Matron Name</label>
		              <div class="controls">
			            <select name="Matron_id" id="Matron_id" class="span11">
			 			 <option value=""> 
						  	Select Matron
						  </option>  
						</select>  								              
					  </div>
		            </div>
			        <div class="control-group">
					  <label class="control-label">Debit Account *</label>
			          <div class="controls">	              										
				         <select name="Debit_Account_id" id="Debit_Account_id" class="span11" required>
				 		   <option value=""> 
							  Select Financial Account
						   </option> 
					     </select> 
					  </div>
			        </div>
			        <div class="control-group">
					  <label class="control-label">Credit Account *</label>
			          <div class="controls">	              										
				        <select name="Credit_Account_id" id="Credit_Account_id" class="span11" required>
				 		  <option value=""> 
							 Select Financial Account
						  </option> 
						</select> 
					  </div>
			        </div>	 						           
		            <div class="form-actions" style="text-align:center;">
		            <input type="hidden" name="Route_id" id="Route_id">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>	
		     <div id="RoutesList" class="span6">
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>		     
		     </div>
		     <div class="span6">
		     <div class="widget-box">
				  <div id="DeleteMessage"></div>
			 </div>
			 </div>
		 </div>
		</div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>