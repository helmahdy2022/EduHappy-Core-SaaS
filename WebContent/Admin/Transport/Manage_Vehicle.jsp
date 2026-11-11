<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Manage Vehicle</title> 
<script>
function showVehiclesList(School_id)
{
document.getElementById("VehiclesList").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (School_id=="" && document.getElementById('Vehicle_id').value!=0)
  {
	$('#Vehicle_Name').val('');
	$('#Registration_Number').val('');
	$('#Registration_Date').val('');
	$('#Registration_Expiry').val('');
	$('#Registration_Owner').val('');
	$('#Chassis_Number').val('');
	$('#Engine_Number').val('');										
	$('#Model_Name').val('');
	$('#Model_Year').val('');																	
	$('#Cubic_Capacity').val('');
	$('#Vehicle_Color').val('');											
	$('#Insurance_Number').val('');	
	$('#Insurance_Date').val('');
	$('#Insurance_Expiry').val('');
	$('#Vehicle_id').val('');
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
	    document.getElementById("VehiclesList").innerHTML=xmlhttp.responseText;
	    $('#vehicles').DataTable();
		if(document.getElementById('Vehicle_id').value!=0)
		{
			$('#Vehicle_Name').val('');
			$('#Registration_Number').val('');
			$('#Registration_Date').val('');
			$('#Registration_Expiry').val('');
			$('#Registration_Owner').val('');
			$('#Chassis_Number').val('');
			$('#Engine_Number').val('');										
			$('#Model_Name').val('');
			$('#Model_Year').val('');																	
			$('#Cubic_Capacity').val('');
			$('#Vehicle_Color').val('');											
			$('#Insurance_Number').val('');	
			$('#Insurance_Date').val('');
			$('#Insurance_Expiry').val('');
			$('#Vehicle_id').val('');				
		}
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_Vehicles?School_id="+School_id,true);
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
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Transport Management</a> <a href="${pageContext.request.contextPath}/Admin/Transport/Manage_Vehicle.jsp" class="current">Manage Vehicle</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Manage Vehicle</h1>
		</div>
		<!--End-breadcrumbs-->
		<div style="margin-left:10px;color:blue;">${param.message}</div>		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Vehicle Registration</h5>
		        </div>
		        <div class="widget-content nopadding">
    			<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Vehicle">	  
		            <div class="control-group">
					  <label class="control-label">School Name *</label>
		              <div class="controls">
			            <select name="School" id="School" class="span11" onChange="showVehiclesList(this.value)" required>
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
            			<input type="text" name="Vehicle_Name" id="Vehicle_Name" class="span11" required maxlength="100">					
 					  </div>
 					</div>
					<div class="control-group">
					  <label class="control-label">Registration Number *</label>
		              <div class="controls">
            			<input type="text" name="Registration_Number" id="Registration_Number" class="span11" required maxlength="50">					
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Registration Date *</label>
		              <div class="controls">
            			<input name="Registration_Date" type="Date" class="span11" id="Registration_Date" required>			
 					  </div>
 					</div> 					
   					<div class="control-group">
					  <label class="control-label">Registration Expiry *</label>
		              <div class="controls">
            			<input name="Registration_Expiry" type="Date" class="span11" id="Registration_Expiry" required>			
 					  </div>
 					</div> 	
 					<div class="control-group">
					  <label class="control-label">Registration Owner</label>
		              <div class="controls">
            			<input type="text" name="Registration_Owner" id="Registration_Owner" class="span11" maxlength="100">				
 					  </div>
 					</div>
   					<div class="control-group">
					  <label class="control-label">Chassis Number</label>
		              <div class="controls">
	 					<input name="Chassis_Number" type="text" class="span11" id="Chassis_Number" maxlength="50">			
 					  </div>
 					</div>
    				<div class="control-group">
					  <label class="control-label">Engine Number</label>
		              <div class="controls">
	 					<input name="Engine_Number" type="text" class="span11" id="Engine_Number" maxlength="50">			
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Model Name</label>
		              <div class="controls">
	 					<input name="Model_Name" type="text" class="span11" id="Model_Name" maxlength="100">			
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Model Year</label>
		              <div class="controls">
	 					<input name="Model_Year" type="number" class="span11" id="Model_Year" maxlength="10" min="0" <c:if test="${Student_Details.PO_Box!=0}"><c:out value='${Student_Details.PO_Box}'/></c:if>>			
 					  </div>
 					</div> 					
  					<div class="control-group">
					  <label class="control-label">Cubic Capacity</label>
		              <div class="controls">
	 					 <input name="Cubic_Capacity" type="number" class="span11" id="Cubic_Capacity" maxlength="10" min="0">			
 					  </div>
 					</div> 	
  					<div class="control-group">
					  <label class="control-label">Vehicle Color</label>
		              <div class="controls">
	 					 <input name="Vehicle_Color" type="text" class="span11" id="Vehicle_Color"  maxlength="50">			
 					  </div>
 					</div> 
					<div class="control-group">
					  <label class="control-label">Insurance Number</label>
		              <div class="controls">
            			<input type="text" name="Insurance_Number" id="Insurance_Number" class="span11" maxlength="50">					
 					  </div>
 					</div>
  					<div class="control-group">
					  <label class="control-label">Insurance Date</label>
		              <div class="controls">
            			<input name="Insurance_Date" type="Date" class="span11" id="Insurance_Date">			
 					  </div>
 					</div>
   					<div class="control-group">
					  <label class="control-label">Insurance Expiry</label>
		              <div class="controls">
            			<input name="Insurance_Expiry" type="Date" class="span11" id="Insurance_Expiry">			
 					  </div>
 					</div>  					 	
		            <div class="form-actions">
		            <input type="hidden" name="Vehicle_id" id="Vehicle_id">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>	
		     <div id="VehiclesList" class="span6">
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