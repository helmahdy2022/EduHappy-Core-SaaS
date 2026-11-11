<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Final Grading Report</title>

<script>
 function showGrades(School_id, School_year_id)
 {
 if (School_id==""||School_year_id=="")
   {
	   document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
	   document.getElementById("FinalGradingReport").innerHTML="";
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
      //showSchoolYears(School_id);
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Grades?School_id="+School_id,true);
 xmlhttp.send();
 }
function showSchoolYears(School_id)
{
	 if (School_id=="")
	   {
		   document.getElementById("Grade").innerHTML="<select><option >Select Grade</option></select>";
		   document.getElementById("School_year_id").innerHTML="<select><option >Select Year</option></select>";
		   document.getElementById("FinalGradingReport").innerHTML="";
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
	      document.getElementById("School_year_id").innerHTML=xmlhttp.responseText;
	     }
	   };
	 xmlhttp.open("GET","${pageContext.request.contextPath}/List_Previous_Years?School_id="+School_id,true);
	 xmlhttp.send();
}
function getPreviousGradingReport(School_id, School_year_id, Grade_id)
{
document.getElementById("FinalGradingReport").innerHTML="<div style='margin-left:500px;margin-top:200px;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (School_id=="" || Grade_id=="")
   {
	   document.getElementById("FinalGradingReport").innerHTML="";
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
     document.getElementById("FinalGradingReport").innerHTML=xmlhttp.responseText;
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Previous_Grading_Report?School_id="+School_id+"&School_year_id="+School_year_id+"&Grade_id="+Grade_id,true);
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
		  <div id="breadcrumb"> <a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Year End Procedures</a> <a href="${pageContext.request.contextPath}/Admin/YearEnd/Previous_Year_Grading_Report.jsp" class="current">Previous Years Grading Reports</a> </div>
		  <%@include file="../includes/Adsense_Top.jsp" %>
		  <h1>Previous Years Grading Reports</h1>
		</div>
		<!--End-breadcrumbs-->
		
		<div class="container-fluid">
		  <hr>
		  <div class="row-fluid">
		    <div class="span6">
		      <div class="widget-box">
		        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		          <h5>Previous Years Grading Reports</h5>
		        </div>
		        <div class="widget-content nopadding">
					<form name="form1" method="post" id="form1" class="form-horizontal" action="">	
		            <div class="control-group">
					  <label class="control-label">School Name</label>
		              <div class="controls"> 								              
			            <select name="School_id" id="School_id" class="span11" onChange="showSchoolYears(this.value)" required>
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
				        <select name="School_year_id" id="School_year_id" class="span11" onChange="showGrades(School_id.value, this.value)" required>
			 			 <option value=""> 
						  	Select Year
						  </option>
						</select> 				  
					  </div>
		            </div>	
		            <div class="control-group">
					  <label class="control-label">Grade Name</label>
		              <div class="controls"> 								              
				        <select name="Grade" id="Grade" class="span11" required onChange="getPreviousGradingReport(School_id.value, School_year_id.value, this.value)">
				 			 <option value="">Select Grade</option>
				 		</select> 						  
					  </div>
		            </div>			            	            
        			<div id="FinalGradingReport"></div>
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
<div id="footer" class="span12"> <%@include file="../includes/AdminFooter.jsp" %></div></div>

</body>
</html>