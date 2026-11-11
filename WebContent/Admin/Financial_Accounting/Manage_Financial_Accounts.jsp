<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>Manage Financial Accounts</title>

<script>
 function showFinancialCategories(School_id, Financial_Category_id)
 {
 if (School_id=="")
   {
	   document.getElementById("Financial_Category_id").innerHTML="<select><option value=''>Select Financial Category</option></select>";
	   document.getElementById("FinancialAccountsList").innerHTML="";
	 	$('#Financial_Account_Name').val('');
	 	$('#Financial_Account_Description').val('');
	 	$('#Financial_Account_id').val('');
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
     	document.getElementById("Financial_Category_id").innerHTML=xmlhttp.responseText; 
     	if(Financial_Category_id==0)
  	 	{
  	 		$('#Financial_Account_Name').val('');
  	 		$('#Financial_Account_Description').val('');
  	 		$('#Financial_Account_id').val('');  
  	     	document.getElementById("FinancialAccountsList").innerHTML="";
  	 	}
     	else
     	{
     		$('#Financial_Category_id').val(Financial_Category_id);
     	}
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Financial_Categories",true);
 xmlhttp.send();
 }
 
 function showFinancialAccountsList(Financial_Category_id, School_id)
 {
document.getElementById("FinancialAccountsList").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>"; 
if (Financial_Category_id=="")
   {
 	$('#Financial_Account_Name').val('');
 	$('#Financial_Account_Description').val('');
 	$('#Financial_Account_id').val('');
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
 	    document.getElementById("FinancialAccountsList").innerHTML=xmlhttp.responseText;
 	   $('#financial_Accounts').DataTable();
 		if(document.getElementById('Financial_Category_id').value!=0)
 		{
 		 	$('#Financial_Account_Name').val('')
 		 	$('#Financial_Account_Description').val('');;
 		 	$('#Financial_Account_id').val('');			
 		}
 		
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Financial_Accounts?Financial_Category_id="+Financial_Category_id+"&School_id="+School_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Financial Accounting</a> <a href="${pageContext.request.contextPath}/Admin/Financial_Accounting/Manage_Financial_Accounts.jsp" class="current">Manage Financial Accounts</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Manage Financial Accounts</h1>
		</div>
		<!--End-breadcrumbs-->
		<div style="margin-left:10px;color:blue;">${param.message}</div>		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Account</h5>
		        </div>
		        <div class="widget-content nopadding">
					<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Financial_Account">
		            <div class="control-group">
					  <label class="control-label">School Name *</label>
		              <div class="controls">
			            <select name="School_id" id="School_id" class="span11" onChange="showFinancialCategories(this.value, 0)" required>
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
					  <label class="control-label">Account Category *</label>
		              <div class="controls">
			         <select name="Financial_Category_id" id="Financial_Category_id" class="span11" onChange="showFinancialAccountsList(this.value, School_id.value)" required>
			 			 <option value="">Select Account Category</option>
			 		</select>  						
 					  </div>
 					</div>	
					<div class="control-group">
					  <label class="control-label">Account Name *</label>
		              <div class="controls">
						<input type="text" name="Financial_Account_Name" id="Financial_Account_Name" class="span11" required maxlength="100">  						
 					  </div>
 					</div>		           
		            <div class="control-group">
		              <label class="control-label">Description</label>
		              <div class="controls"> 
	 					<textarea name="Financial_Account_Description" id="Financial_Account_Description" class="span11" maxlength="1000"></textarea>		                
		              </div>
		            </div>
		            <div class="form-actions" style="text-align:center;">
		            <input type="hidden" name="Financial_Account_id" id="Financial_Account_id">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
		            <div class="requiredSignNote">Fields marked (*) are required</div>
				 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
				    </form>
		        </div>
		     </div>	
		   </div>	
			 <div id="FinancialAccountsList" class="span6">
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