<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Edit School Year</title>
 
</head>
<script>
 function showYears(str)
 {
 if (str=="")
   {
	 document.getElementById("Year_Details").innerHTML="";
	 document.getElementById("School_Year_id").innerHTML="<option>Select Year</option>";
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
	   document.getElementById("Year_Details").innerHTML="";
	   document.getElementById("School_Year_id").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_School_Years?School_id="+str,true);
 xmlhttp.send();
 }

 
 function showYearsDetails(str, School_Year_id)
 {
document.getElementById("Year_Details").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (str==""&& School_Year_id=="")
   {
   document.getElementById("Year_Details").innerHTML="";
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
     document.getElementById("Year_Details").innerHTML=xmlhttp.responseText;
     $('body').updatePolyfill();
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_School_Year_Details?Terms_no="+str+ "&School_Year_id="+School_Year_id,true);
 xmlhttp.send();
 }
 
 function showOneTerm(str,last_i)
 {
	
 if (str==""&&last_i=="")
   {
   document.getElementById("OneTerm").innerHTML="";
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
	 if(last_i=="") last_i=0;
	 var newElement = document.createElement('div');
	 newElement.innerHTML = xmlhttp.responseText;
	 document.getElementById("OneTerm").appendChild(newElement);
     document.getElementById("i").value=parseInt(last_i)+1;
     document.getElementById("Terms_qty").value=parseInt(last_i)+1;
     $('body').updatePolyfill();
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_School_Year_Details?Add_New="+str+"&last_i="+last_i,true);
 xmlhttp.send();
 }
 
 function deleteTerm(str,last_i,i, School_Year_id, Term_id)
 {
 if (str=="")
   {
   document.getElementById("tcell"+i).innerHTML="";
   document.getElementById("vcell"+i).innerHTML="";
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
	 //document.getElementById("tcell"+i).innerHTML=xmlhttp.responseText;
	 var tbl = document.getElementById("tcell"+i);
	 tbl.parentNode.removeChild(tbl);
	 
	 var tb2 = document.getElementById("wcell"+i);
	 tb2.parentNode.removeChild(tb2);
	 //document.getElementById("vcell"+i).innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_School_Year_Details?Delete="+str+"&last_i="+last_i+"&i="+i+"&School_Year_id="+School_Year_id+"&Term_id="+Term_id,true);
 xmlhttp.send();
 }
 

 
 function showNoVacation(str, i)
 {

if (str=="")
   {

   document.getElementById("vcell"+i).innerHTML="";
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
     
	    var checkbox = document.getElementById ("No_Vacation");;
	     if (checkbox.checked) {
	    	 document.getElementById("Vacation_Start"+i).disabled=true;
	    	 document.getElementById("Vacation_End"+i).disabled=true;
	     }
	     else 
	     {
	    	 document.getElementById("Vacation_Start"+i).disabled=false;
	    	 document.getElementById("Vacation_End"+i).disabled=false;
	     }
	   
	   
     }
   };
 xmlhttp.open("GET","",true);
 xmlhttp.send();
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Academic Year</a> <a href="${pageContext.request.contextPath}/Admin/Academic_Year/Edit_School_Year.jsp" class="current">Edit Academic Year</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Edit Academic Year</h1>
		</div>
		<!--End-breadcrumbs-->
				
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Edit Academic Year</h5>
		        </div>
		        <div class="widget-content nopadding">
   					<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_School_Year">    				
		            <div class="control-group">
					  <label class="control-label">School Name</label>
		              <div class="controls">
						<select name="School" id="School" class="span11" onChange="showYears(this.value)">
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
					  <label class="control-label">Academic Year Name</label>
		              <div class="controls">
			 			<select  name="School_Year_id" size="1" class="span11" id="School_Year_id" onChange="showYearsDetails(0,this.value)">
						  <option > 
						  	Select Year
						  </option>
						</select> 						
 					  </div>
 					</div>		            					      
						 <div id="Year_Details" ></div> 
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