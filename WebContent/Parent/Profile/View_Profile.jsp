<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>My Profile</title>
</head>
<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300">

  <!-- For Demo Purposes - Theme Settings Pane -->
	<%@include file="../../Student/includes/Theme_Settings.jsp" %>
  <!-- End: Theme Settings Pane -->
  <div id="main">
	<%@include file="../../Student/includes/ParentMenu.jsp" %> 
	<%@include file="../../Student/includes/ParentLeft.jsp" %>
     <section id="content_wrapper">

      <!-- Start: Topbar-Dropdown -->
	<%@include file="../../Student/includes/Topbar_Dropdown.jsp" %>
      <!-- End: Topbar-Dropdown -->  
      <!-- Start: Topbar -->
      <header id="topbar">
        <div class="topbar-left">
          <ol class="breadcrumb">
            <li class="crumb-active">
              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">Dashboard</a>
            </li>
            <li class="crumb-icon">
              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">
                <span class="glyphicon glyphicon-home"></span>
              </a>
            </li>
            <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Parent/Parent_Main.jsp">Home</a>
            </li>
            <li class="crumb-trail">My Profile</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Get_Parent_Details?Parent_id=${Parent_Account_Details.student_id}&&Source=view" onClick="checkParentSession();">My Profile</a>
            </li>
          </ol>
        </div>
		<%@include file="../../Student/includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
	   <%@include file="../../Student/includes/Adsense_Top.jsp" %>
       <section id="content" class="table-layout animated fadeIn">            
	     <div class="page-heading">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="fa fa-user"></span>My Profile</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Parent Profile</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
                </div>
			      <table class="table table-bordered mbn">
						<tr>
				          <td><label>My Photo</label></td>						
				  		  <td>
							<c:choose>
							<c:when test="${not empty Parent_Account_Details.fileName && Parent_Account_Details.fileName!=null}">
								<img style="width:200px; margin-right:15px;" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Parent_Account_Details.account_id}/Parents/Photos/${Parent_Account_Details.parent_id}/${Parent_Account_Details.fileName}">
							</c:when>
							<c:otherwise>
								<img style="width:200px; margin-right:15px;" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
							</c:otherwise>
							</c:choose>		
				  		  </td> 						
						</tr>
			        <tr>
			         <tr>
			          <td><label>First Name</label></td>
			          <td>
			            <c:out value='${Parent_Details.first_Name}'/>
			          </td>
					</tr>
					<tr>
			          <td><label>Last Name</label></td>
			 		  <td>
			            <c:out value='${Parent_Details.last_Name}'/>
			          </td>
			          </tr>       
			        <tr>
			          <td><label>Country of Nationality</label></td>
			          <td>
						<c:out value='${Parent_Details.country_Name}'/>
			  		</td>
					</tr>
					<tr>
			          <td><label>City</label></td>
			          <td>
				 		<c:out value='${Parent_Details.city}'/>  		 			
			 		 </td>
			        </tr>
			        <tr>
			          <td><label>ZIP Code / PO Box</label></td>
			          <td><c:out value='${Parent_Details.PO_Box}'/></td>
					</tr>
					<tr>
			          <td><label>Full Address</label></td>
			          <td><c:out value='${Parent_Details.full_Address}'/></td>
			        </tr>
			        <tr>
			          <td><label>Gender</label></td>
						<td>
						<c:out value='${Parent_Details.gender}'/>
						 </td>
					</tr>
					<tr>
			          <td><label>Email</label></td>
			          <td>
			          <c:out value='${Parent_Details.email}'/>
					  </td>
			        </tr>
					<tr>
			           <td><label>Tel</label></td>
			            <td>
			            <c:out value='${Parent_Details.tel}'/>
			            </td>
			        </tr> 
					<tr>
			           <td><label>Mobile</label></td>
			            <td>
			            <c:out value='${Parent_Details.mobile}'/>
			            </td>
			        </tr>               
					<tr>
			           <td><label>Date of birth</label></td>
			            <td>
			            <c:out value='${Parent_Details.birth_Date}'/>
			            </td>
			        </tr>
			        <tr>
			            <td><label>Username</label></td>
			          <td>
						<c:out value='${Parent_Details.username}'/>
			          </td>
					</tr>
			          </table>
              </div>
            </div>
          </div>	
	        </div>
       </section>  
      <!-- End: Content -->
	  <%@include file="../../Student/includes/Adsense_Bottom.jsp" %>
      <!-- Begin: Page Scripts -->

		<%@include file="../../Student/includes/Page_Scripts.jsp" %>

      <!-- End: Page Scripts -->            
     </section>
    <!-- Start: Right Sidebar -->
			<%@include file="../../Student/includes/Topbar_Right_Content.jsp" %>
    <!-- End: Right Sidebar -->
  </div>
  </body>
</html>