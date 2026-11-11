<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">   
<head>
        <title>Reset Password</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-login.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/Global/font-awesome/css/font-awesome.css" />
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
 		<script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/jquery-1.11.3.js"></script> 
        <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/js/matrix.login.js"></script>
    </head>
    <body>
	<div style="height:130px;"></div>
		<c:choose>
		<c:when test="${sessionScope['Parent_Session']!=null&&param.loggedIn!=true}">
			<jsp:forward page="/ParentLogin"/>
		</c:when>
		<c:otherwise>
		     <div id="loginbox">           
				<c:choose>
				<c:when test="${param.loggedIn!=true}">
						 <div class="control-group normal_text" style="margin-top: 30px;"> 
						 <h3>
						 <a href="https://edu-happy.com/academy">
						 <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo">
						 </a>
						 </h3>
						 <p style="margin-bottom: 10px;margin-top: 15px;padding-left: 30px;padding-right: 30px;text-align: center;"><em>Reset Password</em></p>
						<c:if test="${param.message!=null}">
							<div style="margin-bottom: 10px; text-align: left; color:red; height:0px;">
							${param.message}
							</div>
						</c:if>
						 </div>              
		            <form name="form1" id="loginform" method="post" class="form-vertical"  action="${pageContext.request.contextPath}/Parent_Reset_Password">
		                <div class="control-group">
		                    <div class="controls">
		                        <div class="main_input_box">
		                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" name="New_Password" id="New_Password" pattern=".{8,}" title="8 characters minimum" required placeholder="New Password" autocomplete="off"/>
		                        </div>
		                    </div>
		                </div>
		                <div class="control-group">
		                    <div class="controls">
		                        <div class="main_input_box">
		                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" name="Confirm_New_Password" id="Confirm_New_Password" pattern=".{8,}" title="8 characters minimum" required placeholder="Confirm New Password" autocomplete="off"/>
		                        </div>
		                    </div>
		                </div>
		                <div class="form-actions" style="text-align:center;">
		                <span class="pull-right"><input type="submit" value="Save" class="btn btn-success"></span>
		                </div>               
			 			<input type="hidden" name="Parent_id" value="<c:out value='${param.Parent_id}'/>"/>
			 			<input type="hidden" name="Forget_Password_Token" value="<c:out value='${param.Forget_Password_Token}'/>"/>
		 		 		<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
		            </form>				
				</c:when>
				<c:otherwise>
					<p style="margin-bottom: 10px;margin-top: 15px;padding-left: 30px;padding-right: 30px;text-align: center; color:#fff; font-size:1.2em">${param.message}	</p>			
				</c:otherwise>
				</c:choose>
		        </div> 		
		</c:otherwise>
		</c:choose>
    </body>
</html>
