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
    document.getElementById("Grade").innerHTML="<option >Select Grade</option>";
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
      document.getElementById("Grade").innerHTML=xmlhttp1.responseText;
      }
    };
  xmlhttp1.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
  xmlhttp1.send();
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Grades</a> <a href="${pageContext.request.contextPath}/Admin/Grades/New_Grading_Scale.jsp" class="current">New Grading Scale</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>New Grading Scale</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Grading Scale</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Grading_Scales">
   		            <div class="control-group">
		              <label class="control-label">School Name *</label>
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
		              <label class="control-label">Grade Name *</label>
		              <div class="controls">	                
						<select name="Grade" id="Grade" class="span11" required>
						  <option value=""> 
						  	Select Grade
						  </option>
						</select>	
		              </div>		              
		            </div>	
		            <div class="control-group">
		              <label class="control-label">Lowest Grading Percentage *</label>
		              <div class="controls">
						<input type="number" name="low" min="0" max="100" class="span11" required/>%          						                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Highest Grading Percentage *</label>
		              <div class="controls">	
						<input type="number" name="high" min="0" max="100" class="span11" required/>%          				                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Letter / Level *</label>
		              <div class="controls">
						<input type="text" name="letter"  class="span11" required maxlength="50"/>						         						                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Description</label>
		              <div class="controls">						           						                
						<input type="text" name="description"  class="span11" maxlength="1000"/>
		              </div>
		            </div>		            	            	            	            		            
		            <div class="form-actions">
		            <input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
		            </div>
	            	<div class="requiredSignNote"><label>Fields marked (*) are required</label></div>
 		 			<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>		            		            		            		            	            				
    				</form>

		        </div>
		     </div>	
		   </div>
		   <div  class="span6">
		   	<div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Quick Hints</h5>
		        </div>
				Grading scale is the letter or the word or may be the level that is mapped to a certain range of grading percentage. 
				For instance, a letter A+ may represent any grading percentage between 95% and 100% while letter A may represent any grading percentage between 90% and 94%. 
				This Grading Scale, if defined, will be displayed in the grading report next to each percentage that the student achieves per course in the terms, the semester exams, total semester grade, or the final course grade.        
				 <hr>				      
		    </div>
			<div class="widget-box">
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
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