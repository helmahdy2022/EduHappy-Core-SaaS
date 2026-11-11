<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>New Sessions</title>

 <script>
 function showGrades(str)
 {
 if (str=="")
   {
	   document.getElementById("Grade").innerHTML="<option>Select Grade</option>";
	   document.getElementById("Sessions").innerHTML="";
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
     document.getElementById("Sessions").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
 xmlhttp.send();
 }

 function showSessions(str, Grade_id)
 {
 document.getElementById("Sessions").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
 if (str=="" && Grade_id=="")
   {
   document.getElementById("Sessions").innerHTML="";
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
     document.getElementById("Sessions").innerHTML=xmlhttp.responseText;
     $('body').updatePolyfill();
     //document.getElementById("OneSession").innerHTML="";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Manage_Sessions?Sessions_no="+str+ "&Grade_id="+Grade_id,true);
 xmlhttp.send();
 }
 
 function showOneSession(str,last_i, Grade_id)
 {

 if (str=="")
   {
   document.getElementById("OneSession").innerHTML="";
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
 	 var newElement = document.createElement('div');
	 newElement.innerHTML = xmlhttp.responseText;
	 //x=parseInt(last_i)+1;
	 //newElement.innerHTML = "<table><tr><td style='padding:0px;'>Session "+x+"</td><td><input name='Time_From"+x+"' type='time' id='Time_From"+x+"' required></td></tr></table>";
	 document.getElementById("OneSession").appendChild(newElement);
     document.getElementById("i").value=parseInt(last_i)+1;
     document.getElementById("Sessions_qty").value=parseInt(last_i)+1;
     $('body').updatePolyfill();
     
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Manage_Sessions?Add_New="+str+"&last_i="+last_i+"&Grade_id="+Grade_id,true);
 xmlhttp.send();
 }
 
 function deleteSession(str,last_i,i, Grade_id, Session_id, Session_qty)
 {

 if (str=="")
   {
   document.getElementById("cell"+i).innerHTML="";
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

 		 var tbl = document.getElementById("cell"+i);
		 tbl.parentNode.removeChild(tbl);
	 	if(document.querySelectorAll('#Delete').length==0)
	 		{
			 var sessiontbl1 = document.getElementById("cell"+i);
			 if(sessiontbl1!=null)
			 sessiontbl1.parentNode.removeChild(sessiontbl1);
			 
			 var sessiontbl2 = document.getElementById("sessionTable");
			 if(sessiontbl2!=null)
				 sessiontbl2.parentNode.removeChild(sessiontbl2); 
			 var submitBtn = document.getElementById("Submit");
			 if(submitBtn!=null) 
				 submitBtn.parentNode.removeChild(submitBtn);
			 var addNewBtn = document.getElementById("Add_New");
			 if(addNewBtn!=null) 
				 addNewBtn.parentNode.removeChild(addNewBtn);
	 		} 
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Manage_Sessions?Delete="+str+"&last_i="+last_i+"&i="+i+"&Grade_id="+Grade_id+"&Session_id="+Session_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Schedule Sessions</a> <a href="${pageContext.request.contextPath}/Admin/Schedule/New_Sessions.jsp" class="current">New Schedule Sessions</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>New Schedule Sessions</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>New Schedule Sessions</h5>
		        </div>
		        <div class="widget-content nopadding">
    				<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Insert_Sessions">					
		            <div class="control-group">
					  <label class="control-label">School Name</label>
		              <div class="controls">						              
						<select name="School" id="School" class="span11" onChange="showGrades(this.value)">
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
					  <label class="control-label">Grade Name</label>
		              <div class="controls">					
				         <select name="Grade" id="Grade" class="span11" onChange="showSessions(0, this.value)">
				 			 <option value="">Select Grade</option>
				 		</select>
 					  </div>
 					</div>	
 					<div  id="Sessions" ></div>
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
						A session is a unit of time in a day. 
						Each grade has its own day sessions. 
						From example, Grade One might have 6 sessions while Grade 6 might have 7 sessions. 
						These sessions may also be different in their time from and time to. 
						For example, session one in Grade one might start at 8:00 AM and ends at 8:45 AM while session one in Grade two might start at 8:00 AM and end at 9:00 AM. 
						Therefore, the system manages the sessions of each grade separately. 
						That means each grade will have to have its own sessions.    
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