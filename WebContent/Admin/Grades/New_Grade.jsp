<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>New Grades</title>
</head>

<body>

<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Grades</a> <a href="${pageContext.request.contextPath}/Admin/Grades/New_Grade.jsp" class="current">New Grade</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>New Grade</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Grade</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Manage_Grade">
   		            <div class="control-group">
		              <label class="control-label">School Name*</label>
		              <div class="controls">
			            <select name="School_id" class="span11" id="School_id" required>
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
		              <label class="control-label">Grade Name *</label>
		              <div class="controls">
          				<input type="text" name="Grade_Name" id="Grade_Name" class="span11" required maxlength="50">		                
		              </div>
		            </div>	
		            <div class="control-group">
		              <label class="control-label">Passing Percentage *</label>
		              <div class="controls">
          				<input name="Passing_Grade" type="number" class="span11" id="Passing_Grade" min="0" max="100" required>%		                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Max Possible Points *</label>
		              <div class="controls">
          				<input name="Max_Grade" type="number" class="span11" id="Max_Grade" min="0" max="1000000000" required>		                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Grade Index *</label>
		              <div class="controls">
           				<input type="number" name="Grade_Index" id="Grade_Index" class="span11" min="-1000000000" max="1000000000" required>		                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Grading Report Options</label>
		              <div class="controls">
           				<input type="radio" name="DisplayHideReport" id="DisplayReport" style="width:18px; height:18px; margin-top:0" value="1" checked>	Display Grading Report to students by default	<br/>               
           				<input type="radio" name="DisplayHideReport" id="HideReport" style="width:18px; height:18px; margin-top:0" value="0">	Hide Grading Report from students by default
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
				In most schools, education is divided into three levels: elementary school, middle or junior high school, and high school. Children are usually divided by age groups into grades, ranging from kindergarten and first grade for the youngest children, up to twelfth grade as the final year of high school.
				For K-12 schools, for example, grades will start from KG1 and KG2 up to Grade 12. For universities, grades can be Year One, Year Two and so on. However, there is no limitation on the number of grades you would like to create in the system.
				<hr>				      
				Grade Index field is responsible for the order of the grades. So for example, Grade One should take index 1 and Grade two should take index 2 and so on till Grade Twelve which should take index 12. For KG1 we recommend giving it index (-1) and for KG2 we recommend giving it index (0).		   
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