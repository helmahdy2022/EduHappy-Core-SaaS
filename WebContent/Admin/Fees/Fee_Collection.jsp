<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Fee Collection</title>
<script>
function showYears(School_id)
{
if (School_id=="")
  {
  	 document.getElementById("Year_id").innerHTML="<select><option value=''>Select Year</option></select>";
	 document.getElementById("Grade").innerHTML="<select><option value=''>Select Grade</option></select>";
	 document.getElementById("Collection").innerHTML="";
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
    document.getElementById("Year_id").innerHTML=xmlhttp.responseText;
    document.getElementById("Grade").innerHTML="<select><option value=''>Select Grade</option></select>";
    document.getElementById("Collection").innerHTML="";

    
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/List_School_Years_Grades?School_id="+School_id,true);
xmlhttp.send();
}

function showGrades(str, School_Year_id)
 {
 if (str=="" || School_Year_id=="")
   {
	 document.getElementById("Grade").innerHTML="<select><option value=''>Select Grade</option></select>";
	 document.getElementById("Collection").innerHTML="";
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
     document.getElementById("Grade").innerHTML=xmlhttp.responseText;
     document.getElementById("Collection").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }
 // end of ShowGrade function
 function showCollectionByFee(School_id, School_Year_id, Grade_id, Fee_id)
 {
	 document.getElementById("Collection").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
	 if (School_id=="" && School_Year_id=="" && Grade_id=="")
	   {
		 document.getElementById("Collection").innerHTML="";
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
	     document.getElementById("Collection").innerHTML=xmlhttp.responseText;
	     }
	   };
	 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Collection_By_Fee?School_id="+School_id+"&School_Year_id="+School_Year_id+"&Grade_id="+Grade_id+"&Fee_id="+Fee_id,true);
	 xmlhttp.send(); 
 }
 function showCollectionByReceipt(School_id, School_Year_id, Grade_id, Receipt_id)
 {
	 document.getElementById("Collection").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
	 if (School_id=="" && School_Year_id=="" && Grade_id=="")
	   {
		 document.getElementById("Collection").innerHTML="";
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
	     document.getElementById("Collection").innerHTML=xmlhttp.responseText;
	     }
	   };
	 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Collection_By_Receipt?School_id="+School_id+"&School_Year_id="+School_Year_id+"&Grade_id="+Grade_id+"&Receipt_id="+Receipt_id,true);
	 xmlhttp.send(); 
 }
 
 function showCollectionByStudent(School_id, School_Year_id, Grade_id, Student_id)
 {
	 document.getElementById("Collection").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
	 if (School_id=="" && School_Year_id=="" && Grade_id=="")
	   {
		 document.getElementById("Collection").innerHTML="";
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
	     document.getElementById("Collection").innerHTML=xmlhttp.responseText;
	     }
	   };
	 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Collection_By_Student?School_id="+School_id+"&School_Year_id="+School_Year_id+"&Grade_id="+Grade_id+"&Student_id="+Student_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Fees Management</a> <a href="${pageContext.request.contextPath}/Admin/Fees/Fee_Collection.jsp" class="current">Fee Collection</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Fee Collection</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Fee Collection</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" id="form1" method="post" id="form1" class="form-horizontal" action="">
			            <div class="control-group">
						  <label class="control-label">School Name</label>
			              <div class="controls">	              										
				           	<select name="School" id="School" class="span11" onChange="showYears(this.value)">
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
						  <label class="control-label">Academic Year Name</label>
			              <div class="controls">					 
							<select id="Year_id" name="Year_id" class="span11" onChange="showGrades(School.value, this.value)">
								  <option value="" > 
								  	Select Year
								  </option>
							</select>
	 					  </div>
	 					</div>
						<div class="control-group">
						  <label class="control-label">Grade Name</label>
			              <div class="controls">						 
					        <select name="Grade" id="Grade" class="span11" onchange="document.getElementById('Collection').innerHTML=''">
					 			 <option value="">Select Grade</option>
					 		</select> 
	 					  </div>
	 					</div>		 						            					      
     					<div class="buttonHolder">
     					<button type="button" class="btn btn-info" onclick="showCollectionByFee(School.value, Year_id.value, Grade.value, 0);">Get Collection by Fee</button>
     					<button type="button" class="btn btn-info" onclick="showCollectionByReceipt(School.value, Year_id.value, Grade.value, 0);">Get Collection by Receipt</button>
     					<button type="button" class="btn btn-info" onclick="showCollectionByStudent(School.value, Year_id.value, Grade.value, 0);">Get Collection by Student</button>
     					</div>
				    </form>
		        </div>
		     </div>	
		   </div>		
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
		 </div>
		<div id="Collection" class="row-fluid"></div>
		</div> 
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<!--Footer-part-->
<div class="row-fluid">
<div id="footer" class="span12"><%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>