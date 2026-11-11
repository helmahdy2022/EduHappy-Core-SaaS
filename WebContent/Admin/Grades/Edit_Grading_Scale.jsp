<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Grading Scales</title>

<script>

  function showGrades(str)
  {
  if (str=="")
    {
    document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
    document.getElementById("Grading_Scale_id").innerHTML="<select><option >Select Grading Scale</option></select>";
    document.getElementById("GradingScaleDetails").innerHTML="";
	 document.getElementById("DeleteMessage").innerHTML="";
    return;
    } 
  if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp1=new XMLHttpRequest();
    }
  else
    {// code for IE6, IE5
    xmlhttp1=new ActiveXObject("Microsoft.XMLHTTP");
    }
  xmlhttp1.onreadystatechange=function()
    {
    if (xmlhttp1.readyState==4 && xmlhttp1.status==200)
      {
    	document.getElementById("GradingScaleDetails").innerHTML="";
    	document.getElementById("Grade").innerHTML=xmlhttp1.responseText;
    	document.getElementById("Grading_Scale_id").innerHTML="<select><option >Select Grading Scale</option></select>";
   	 document.getElementById("DeleteMessage").innerHTML="";
      }
    };
  xmlhttp1.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
  xmlhttp1.send();
  }
  
  function showGradingScales(Grade_id)
  {
  if (Grade_id=="")
    {
    document.getElementById("Grading_Scale_id").innerHTML="<select><option >Select Grading Scale</option></select>";
    document.getElementById("GradingScaleDetails").innerHTML="";
	 document.getElementById("DeleteMessage").innerHTML="";
    return;
    } 
  if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp1=new XMLHttpRequest();
    }
  else
    {// code for IE6, IE5
    xmlhttp1=new ActiveXObject("Microsoft.XMLHTTP");
    }
  xmlhttp1.onreadystatechange=function()
    {
    if (xmlhttp1.readyState==4 && xmlhttp1.status==200)
      {
    	document.getElementById("GradingScaleDetails").innerHTML="";
    	document.getElementById("Grading_Scale_id").innerHTML=xmlhttp1.responseText;
   	 document.getElementById("DeleteMessage").innerHTML="";
      }
    };
  xmlhttp1.open("GET","${pageContext.request.contextPath}/List_Grading_Scales?Grade_id="+Grade_id,true);
  xmlhttp1.send();
  }
  
  function showGradingScaleDetails(Grading_Scale_id)
  {
  document.getElementById("GradingScaleDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
  if (Grading_Scale_id=="")
    {
    document.getElementById("GradingScaleDetails").innerHTML="";
	 document.getElementById("DeleteMessage").innerHTML="";
    return;
    } 
  if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp1=new XMLHttpRequest();
    }
  else
    {// code for IE6, IE5
    xmlhttp1=new ActiveXObject("Microsoft.XMLHTTP");
    }
  xmlhttp1.onreadystatechange=function()
    {
    if (xmlhttp1.readyState==4 && xmlhttp1.status==200)
      {
      document.getElementById("GradingScaleDetails").innerHTML=xmlhttp1.responseText;
 	 document.getElementById("DeleteMessage").innerHTML="";
      }
    };
  xmlhttp1.open("GET","${pageContext.request.contextPath}/Get_Grading_Scale_Details?Grading_Scale_id="+Grading_Scale_id,true);
  xmlhttp1.send();
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Grades</a> <a href="${pageContext.request.contextPath}/Admin/Grades/Edit_Grading_Scale.jsp" class="current">Edit Grading Scale</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Edit Grading Scale</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Edit Grading Scale</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Grading_Scales">
   		            <div class="control-group">
		              <label class="control-label">School Name</label>
		              <div class="controls">
			            <select name="School_id" size="1" class="span11" id="School_id" onChange="showGrades(this.value)" required>
						  <option value="" > 
						  	Select School
						  </option>			  
					         <c:forEach items="${Schools.school_id}" var="School_id" varStatus="position">
					          	<option value="${School_id}"><c:out value='${Schools.school_Name[position.index]}'/></option>
					          </c:forEach>	
						</select>    
		              </div>
		            </div> 
		            <div class="control-group">
		              <label class="control-label">Grade Name</label>
		              <div class="controls">	                
						  <select name="Grade" id="Grade" class="span11"  onchange="showGradingScales(this.value)" required>
						  <option value=""> 
						  	Select Grade
						  </option>
						  </select>	
		              </div>		              
		            </div>
		            <div class="control-group">
		              <label class="control-label">Grading Scales</label>
		              <div class="controls">	                
						<select name="Grading_Scale_id" id="Grading_Scale_id" class="span11" onchange="showGradingScaleDetails(this.value)" required>
						  <option value=""> 
						  	Select Grading Scale
						  </option>
						</select>	
		              </div>		              
		            </div>		            	
					     <div id="GradingScaleDetails" ></div>
					     <div id="DeleteMessage" ></div>  
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