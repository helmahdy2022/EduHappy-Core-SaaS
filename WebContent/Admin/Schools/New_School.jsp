<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>School Main</title>

</head>

<body>
<div>
	<%@include file="../includes/AdminMenu.jsp" %> 
</div>
	<div class="leftContent"><%@include file="../includes/AdminLeft.jsp" %> </div>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Schools</a> <a href="${pageContext.request.contextPath}/Admin/Schools/New_School.jsp" class="current">New School</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Create New School</h1>
		</div>
		<!--End-breadcrumbs-->

		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New School</h5>
		        </div>
		        <div class="widget-content nopadding">
				  <form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Manage_School">		          
		            <div class="control-group">
		              <label class="control-label">School Name *</label>
		              <div class="controls">
		                <input type="text" name="name" id="name" class="span11"  placeholder="School name" required maxlength="100">
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Country *</label>
		              <div class="controls">
		                <select name="Country_id" id="Country_id" class="span11" required>
						  <option value=""> 
						  	Select Country
						  </option>
					         <c:forEach items="${Countries.country_id}" var="Country_id" varStatus="position">
					          	<option value="${Country_id}"><c:out value='${Countries.country_Name[position.index]}'/></option>
					          </c:forEach>
		                </select>
		              </div>
		            </div>		           
		            <div class="control-group">
		              <label class="control-label">City *</label>
		              <div class="controls">
	 					<input type="text" name="City" id="City" class="span11"  placeholder="City name" maxlength="100" required> 		                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">ZIP Code / PO Box</label>
		              <div class="controls">
						<input name="PO_Box" type="number" id="PO_Box" class="span11" min="0" max="1000000000">		                
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Full Address</label>
		              <div class="controls">
		                <input name="Full_Address" type="text" class="span11" placeholder="Full Address" id="Full_Address" maxlength="255">
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Website</label>
		              <div class="controls">
		                <input name="Website" type="text" class="span11" placeholder="Website URL" id="Website" maxlength="255">
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Email</label>
		              <div class="controls">
		                <input name="Email" type="email" class="span11" placeholder="Email" id="Email" maxlength="255">
		              </div>
		            </div>
		            <div class="control-group">
		              <label class="control-label">Weekend (school closed)</label>
		              <div class="controls">
		   			         <label><b><i>Select only the days when the school is CLOSED:</i></b></label>             
		 			         <c:forEach items="${Days.day_id}" var="Day_id" varStatus="position">	
					          	<label>
					          	<input type="checkbox" name="Weekend${Day_id}" value="${Day_id}" id="Weekend${Day_id}" /><c:out value='${Days.day_Name[position.index]}'/>
					            </label>
					          </c:forEach>
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
		  	 <%@include file="../includes/Adsense_Inline.jsp" %>
		    </div>
		   </div>
	</div>
	<div class="rightContent"><%@include file="../includes/AdminRight.jsp" %></div>
<div class="row-fluid">
<div id="footer" class="span12">  <%@include file="../includes/AdminFooter.jsp" %></div></div>
</body>
</html>