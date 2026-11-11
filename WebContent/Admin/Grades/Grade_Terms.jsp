<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Grade Terms</title>
<script> 
  function showYears(str)
  {
  if (str=="")
    {
    document.getElementById("Years").innerHTML="<select class='span11'><option>Select Year</option></select>";
    document.getElementById("Grade").innerHTML="<select class='span11'><option >Select Grade</option></select>";
    document.getElementById("GradeTermsOptions").innerHTML="";
    document.getElementById("GradeTermsDetails").innerHTML="";
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
      document.getElementById("Years").innerHTML=xmlhttp.responseText;
      document.getElementById("Grade").innerHTML="<select class='span11'><option >Select Grade</option></select>";
      document.getElementById("GradeTermsOptions").innerHTML="";
      document.getElementById("GradeTermsDetails").innerHTML="";
      
      }
    };
  xmlhttp.open("GET","${pageContext.request.contextPath}/List_School_Years_Grades?School_id="+str,true);
  xmlhttp.send();
  }

  function showGrades(str, School_Year_id)
  {
  if (str=="" || School_Year_id=="")
    {
    document.getElementById("Grade").innerHTML="<select class='span11'><option >Select Grade</option></select>";
    document.getElementById("GradeTermsOptions").innerHTML="";
    document.getElementById("GradeTermsDetails").innerHTML="";
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
      document.getElementById("GradeTermsOptions").innerHTML="";
      document.getElementById("GradeTermsDetails").innerHTML="";
      }
    };
  xmlhttp1.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+str,true);
  xmlhttp1.send();
  }
  
 
  function showGradeTermsOptions(Grade_id, School_Year_id)
  {
  if (Grade_id=="")
    {
    document.getElementById("GradeTermsOptions").innerHTML="";
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
    	 document.getElementById("GradeTermsDetails").innerHTML="";
    	 document.getElementById("GradeTermsOptions").innerHTML=xmlhttp.responseText;
         $('body').updatePolyfill();
      }
    };
  xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Grade_Terms_Details?Grade_id="+Grade_id+"&&School_Year_id="+School_Year_id,true);
  xmlhttp.send();
  }
  
  function showTermsNo(str, Grade_id)
  {

 if (str=="")
    {

    document.getElementById("Terms_No").innerHTML="";
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
      
 	    var checkbox = document.getElementById ("Inherit");;
 	     if (checkbox.checked) {
 	    	 document.getElementById("Terms_No").innerHTML="<input type='submit' name='button' id='button' value='Submit'>";
 	    	 document.getElementById("GradeTermsDetails").innerHTML="";
 	    	 
 	     }
 	     else 
 	     {
 	    	 document.getElementById("Terms_No").innerHTML=xmlhttp.responseText;
 	     }
 	   
 	   
      }
    };
  xmlhttp.open("GET","Display_Terms_No.jsp?Grade_id="+Grade_id,true);
  xmlhttp.send();
  }
  
  function showTerms(str, Grade_id)
  {
document.getElementById("GradeTermsDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (str=="" || str==0)
    {
    document.getElementById("GradeTermsDetails").innerHTML="";
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
      document.getElementById("GradeTermsDetails").innerHTML=xmlhttp.responseText;
      $('body').updatePolyfill();
      }
    };
  xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Grade_Terms_Details?Terms_no="+str+"&Grade_id="+Grade_id,true);
  xmlhttp.send();
  }
  
  function showOneTerm(str,last_i, School_Year_id, Grade_id)
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
 	 var newElement = document.createElement('div');
 	 newElement.innerHTML = xmlhttp.responseText;
 	 document.getElementById("OneTerm").appendChild(newElement);
      document.getElementById("i").value=parseInt(last_i)+1;
      document.getElementById("Terms_qty").value=parseInt(last_i)+1;
      $('body').updatePolyfill();
      }
    };
  xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Grade_Terms_Details?Add_New="+str+"&last_i="+last_i+"&School_Year_id="+School_Year_id+"&Grade_id="+Grade_id,true);
  xmlhttp.send();
  }
  
  function deleteTerm(str,last_i,i, School_Year_id, Grade_id, Grade_Term_id)
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
 	 //document.getElementById("vcell"+i).innerHTML=xmlhttp.responseText;
   	 var tbl = document.getElementById("tcell"+i);
	 tbl.parentNode.removeChild(tbl);
	 
   	 var tb2 = document.getElementById("wcell"+i);
	 tb2.parentNode.removeChild(tb2);
      }
    };
  xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Grade_Terms_Details?Delete="+str+"&last_i="+last_i+"&i="+i+"&School_Year_id="+School_Year_id+"&Grade_id="+Grade_id+"&Grade_Term_id="+Grade_Term_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Grade Terms</a> <a href="${pageContext.request.contextPath}/Admin/Grades/Grade_Terms.jsp" class="current">Manage Grade Terms</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Manage Grade Terms</h1>
		</div>
		<!--End-breadcrumbs-->
				
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Manage Grade Terms</h5>
		        </div>
		        <div class="widget-content nopadding">  
    				<form name="form1" method="post" id="form1" class="form-horizontal" action="${pageContext.request.contextPath}/Manage_Grade_Terms">   					 				
		            <div class="control-group">
					  <label class="control-label">School Name</label>
		              <div class="controls">
			            <select name="School_id" id="School_id" class="span11" onChange="showYears(this.value)">
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
				        <div  id="Years" >
							<select class="span11" >
							  <option value=""> 
							  	Select Year
							  </option>
							  </select>
				        </div>					  
 					  </div>
 					</div>		            					      
					<div class="control-group">
					  <label class="control-label">Grade Name</label>
		              <div class="controls"> 						
				        <div  id="Grade" >
							<select class="span11" >
							  <option value=""> 
							  	Select Grade
							  </option>
							  </select>
				        </div>					  
 					  </div>
 					</div>
					       <div id="GradeTermsOptions" ></div>
					       <div id="GradeTermsDetails" ></div>
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