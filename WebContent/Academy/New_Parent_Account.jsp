<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    
<head>
        <title>New Parent Account</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/_css/matrix-login.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/Global/font-awesome/css/font-awesome.css" />
		<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
 		<script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/jquery-1.11.3.js"></script> 
<!--         <script src="js/jquery.min.js"></script>  
        <script src="js/matrix.login.js"></script>  -->		
			<!-- Global site tag (gtag.js) - Google Analytics -->
			<script async src="https://www.googletagmanager.com/gtag/js?id=UA-75892673-4"></script>
			<script>
			  window.dataLayer = window.dataLayer || [];
			  function gtag(){dataLayer.push(arguments);}
			  gtag('js', new Date());
			
			  gtag('config', 'UA-75892673-4');
			</script>

		 <script>
		
		 function listCountries()
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
				  document.getElementById("Country_id").innerHTML=xmlhttp.responseText;
		     }
		   };
		 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Countries",true);
		 xmlhttp.send();
		 }
		 function checkUsername(Username)
		 {
		 if (Username=="")
		   {
			   document.getElementById("UsernameMsg").innerHTML="";
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
		     document.getElementById("UsernameMsg").innerHTML=xmlhttp.responseText;
		     }
		   };
		 xmlhttp.open("GET","${pageContext.request.contextPath}/Check_Username?Username="+Username,true);
		 xmlhttp.send();
		 }
 
		 $(function(){
			    //$("#form1").validate();    
			    $("#form1").on('submit', function(e){
			        if(document.getElementById("OkToSubmit").value!='OK')
			        {
			            e.preventDefault();
			        }
			    });
			});
		 </script>

    </head>
    <body onload="listCountries()" style="margin-top:0px">
        <div id="loginbox" >           
            <form name="form1" id="form1" method="post" class="form-vertical"  action="${pageContext.request.contextPath}/Manage_Parent_Account">
				 <div class="control-group normal_text" > 
				 
				 <a href="http://www.edu-happy.com">
				 <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo">
				 </a>
				 			  
				 <p style="margin-bottom: 5px;margin-top: 15px;padding-left: 30px;padding-right: 30px;text-align: center;"><em>Create Parent Account</em></p>
				 </div>
                <div class="control-group" style="display:block;">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lb"><i class="icon-user"> </i></span><input type="text" name="First_Name" id="First_Name" placeholder="First Name *" required maxlength="50"/>                           
                        </div>                   
                    </div>
                </div>
                  <div class="control-group" style="display:block;">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lb"><i class="icon-user"> </i></span><input type="text" name="Last_Name" id="Last_Name" placeholder="Last Name *" required maxlength="50"/>                           
                        </div>                        
                    </div>
                </div>
                 <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lb"><i class="icon-user"> </i></span><select name="Country_id" id="Country_id" style="height:38px;" required><option value="">Select Country</option></select> 	                       
                        </div>
                    </div>
                </div>
                  <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lb"><i class="icon-user"> </i></span><input type="text" name="City" id="City" required maxlength="255" placeholder="City *" required maxlength="100"/>                       
                        </div>
                    </div>
                </div>
                 <div class="control-group">
                     <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lb"><i class="icon-user"> </i></span><input type="email" name="Email" id="Email" placeholder="Email *" required maxlength="255"/>                       
                        </div>
                    </div>
                </div>                
                   <div class="control-group">
                      <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lb"><i class="icon-user"> </i></span><input type="text" name="Mobile" id="Mobile" placeholder="Mobile *" required maxlength="100"/>                       
                        </div>
                    </div>
                </div>  
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-envelope"> </i></span><input type="text" name="Username" id="Username" placeholder="Username *" required pattern=".{8,}" title="8 characters minimum" maxlength="255" onblur="checkUsername(this.value)" />
                        	<div id="UsernameMsg" style="height:0px;"></div>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" name="Password" id="Password" placeholder="Password *" required pattern=".{8,}" title="8 characters minimum" maxlength="255" autocomplete="off"/>
                        </div>
                    </div>
                    </div>

 				<p style="margin-bottom: 15px;margin-top: 15px; margin-left:40px;padding-left: 30px;padding-right: 30px;text-align: left;color:#fff;">By creating an account, you agree to our
 				<a href="${pageContext.request.contextPath}/Account/Terms_and_Conditions.jsp" target=_blank>Terms and Conditions</a> and 
 				<a href="${pageContext.request.contextPath}/Account/Privacy_Policy.jsp" target=_blank>Privacy Policy</a></p> 
 				<input type="hidden" name="Terms" id="Terms" value="on">
                <div class="form-actions" style="text-align:center;">
                    <input type="submit" value="Sign up" class="btn btn-success">
                </div>
                <div style="text-align:left;margin-left:40px;padding-left: 30px;">
                   <p style="color:#fff;"> Already have an account? <a href="${pageContext.request.contextPath}/Admin/Admin_Login.jsp" style="color:red;">Login</a></p>
                
                </div>                
 		 		<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
 		 		
            </form>
        </div>  
         
    </body>
</html>



