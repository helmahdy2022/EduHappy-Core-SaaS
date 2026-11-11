<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<title>Semester Exams</title>
 <script src="${pageContext.request.contextPath}/Global/lib/js/jquery-1.11.1.min.js"></script>
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/ajaxForm_jquery.form.js"></script>
 <script type="application/javascript" src="${pageContext.request.contextPath}/Global/lib/jquery.validate.js"></script> 
 <script>
$(document).ready(function() 
{ 
    $('#form1').ajaxForm({target:'#SemesterExamDetails'}); 
}); 
</script>

 <script type="text/javascript">
 function gradeSemester(Semester_Exam_id,Grade_id, Class_id, Course_id, Group_id, selectedRow)
 {
 document.getElementById("SemesterExamDetails").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
 if (Semester_Exam_id=="")
   {
   document.getElementById("SemesterExamDetails").innerHTML="";
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

 	  document.getElementById("SemesterExamDetails").innerHTML=xmlhttp.responseText;
	  var x = document.getElementsByClassName("selectedRow");
	  if(x[0]!=undefined) x[0].className = "";
	  document.getElementById(selectedRow).className="selectedRow";
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Get_Semester_Grading?Semester_Exam_id="+Semester_Exam_id+"&&Grade_id="+Grade_id+"&&Class_id="+Class_id+"&&Course_id="+Course_id+"&&Group_id="+Group_id,true);
 xmlhttp.send();
 }
 
 function manageSemesterGrading(Student_Grade, Student_id, Semester_Exam_id, GradeButton, Semester_Exam_Grade)
 {
 if (Student_Grade=="" && Student_id=="" && Semester_Exam_id=="")
   {
   document.getElementById("Grade_Box"+Student_id).innerHTML="";
   return;
   } 
 if(Student_Grade==""&&GradeButton=="Grade")
 {
	document.getElementById("Grade_Required"+Student_id).innerHTML="required";
	return;
 }
else
 {
	document.getElementById("Grade_Required"+Student_id).innerHTML="";
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

 	  document.getElementById("Grade_Box"+Student_id).innerHTML=xmlhttp.responseText;
 	  if (GradeButton=="Grade")
 		  {
 		  	document.getElementById("Grade"+Student_id).value='Edit';	
 		  	
 		  	///show delete button here////
 				
 		  	if ((Student_Grade!="") && (parseInt(Student_Grade)<=parseInt(Semester_Exam_Grade)) && (parseInt(Student_Grade)>=0))
 				{
 					document.getElementById("DeleteArea"+Student_id).innerHTML="<input name='Delete"+Student_id+"' type='button' class='btn btn-sm btn-danger btn-block' style='width:130px;' id='Delete"+Student_id+"' value='Delete' "
 					+"onClick='manageSemesterGrading(Student_Grade"+Student_id+".value,"+Student_id+", Semester_Exam_id.value, Delete"+Student_id+".value,"+Semester_Exam_Grade+" )'>";
 				}
 			
 		  	
 		  }
 	  else
 		  {
 		  	document.getElementById("Grade"+Student_id).value='Grade';
 		  }
 	  if (GradeButton=="Delete")
 	  {
 		  document.getElementById("DeleteArea"+Student_id).innerHTML="";
 	  }
     }
   };
 xmlhttp.open("GET","${pageContext.request.contextPath}/Manage_Semester_Grading?Student_Grade="+Student_Grade+"&&Student_id="+Student_id+"&&Semester_Exam_id="+Semester_Exam_id+"&&GradeButton="+GradeButton+"&&Semester_Exam_Grade="+Semester_Exam_Grade, true);
 xmlhttp.send();
 }
 </script>


<body class="tables-basic-page" data-spy="scroll" data-target="#nav-spy" data-offset="300">

  <!-- For Demo Purposes - Theme Settings Pane -->
	<%@include file="../includes/Theme_Settings.jsp" %>
  <!-- End: Theme Settings Pane -->
  <div id="main">
	<%@include file="../includes/TeacherMenu.jsp" %> 
	<%@include file="../includes/TeacherLeft.jsp" %>
     <section id="content_wrapper">

      <!-- Start: Topbar-Dropdown -->
	<%@include file="../includes/Topbar_Dropdown.jsp" %>
      <!-- End: Topbar-Dropdown -->  
      <!-- Start: Topbar -->
      <header id="topbar">
        <div class="topbar-left">
          <ol class="breadcrumb">
            <li class="crumb-active">
              <a href="${pageContext.request.contextPath}/Teacher/Teacher_Main.jsp">Dashboard</a>
            </li>
            <li class="crumb-icon">
              <a href="${pageContext.request.contextPath}/Teacher/Teacher_Main.jsp">
                <span class="glyphicon glyphicon-home"></span>
              </a>
            </li>
            <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Teacher/Teacher_Main.jsp">Home</a>
            </li>
            <li class="crumb-trail">Semester Exam</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Teacher/Assignments/Semester_Exams.jsp">Semester Exam</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
		<form name="form1" id="form1" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/Manage_AllSemesterGrading" >
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
		  	<%@include file="../includes/Adsense_Top.jsp" %>
	          <h4>Semester Exam</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="fa fa-check-square"></span> Semester Exam Details</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Semester Exam Class Authority List</code>
              </div>
            </div>
            <div class="panel-body pn">
              <div class="table-responsive">
                <div class="bs-component">
                    <table class="table table-bordered mbn">
                      <thead>
                        <tr >
                          <th></th>
                          <th>School</th>
                          <th>Grade</th>
                          <th>Class</th>
                          <th>Course</th> 
                          <th>Exam</th>                              
                          <th colspan="4" style="text-align:center;">Action</th>                                                                                                                                                                                       
                        </tr>
                      </thead>
                      <tbody>
					<c:forEach items="${TeacherAuthority.authorityList}" var="AuthorityList">
				    <tr id="${AuthorityList[1]}${AuthorityList[3]}${AuthorityList[5]}${AuthorityList[7]}">
					    <td style="width:20px;"><i class="fa fa-circle text-warning fs8 pr15"></i></td>
						<td><c:out value="${AuthorityList[1]}"/></td>
						<td><c:out value="${AuthorityList[3]}"/></td>
						<td><c:out value="${AuthorityList[5]}"/></td>
						<td><c:out value="${AuthorityList[7]}  ${AuthorityList[9]}"/></td>
						<td>
							<select class="form-control" name="Semester_Exam_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" id="Semester_Exam_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" onChange="this.style.backgroundColor='#ffffff';document.getElementById('SemesterExamDetails').innerHTML='';">
								<option value="">
									Select Exam
								</option>
								<c:forEach items="${TeacherAuthority.semesterExamsmap}" var="Exam">	
								<c:forEach items="${Exam.value}" var="ExamList">
									<c:if test="${Exam.key==AuthorityList[6] && ExamList[0]!=null}">
					   					<option value="${ExamList[0]}">
					   					<c:out value="${ExamList[1]}"/>
					   					</option>
				 					</c:if>
				 				</c:forEach>	
							</c:forEach> 								
								</select>
			 			</td>
			 			<td>   						 			
			 	 			<button type="button" class="btn btn-xs btn-primary btn-block" value="Grade Semester Exam" name="Grade_Semester_Exam${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" id="Grade_Semester_Exam${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}" 
			 	 			onclick="document.getElementById('Semester_Exam_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}').required=true;
			 	 			 gradeSemester(Semester_Exam_id${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}.value,${AuthorityList[2]},${AuthorityList[4]}, ${AuthorityList[6]},'${AuthorityList[8]}', '${AuthorityList[1]}${AuthorityList[3]}${AuthorityList[5]}${AuthorityList[7]}')">
							<i class="glyphicon glyphicon-th"></i> Grade Semester Exam </button>
							<script>
							$(document).ready(function () {
							
							    $('#form1').validate({ 
							
							    });
							
							    $('#Grade_Semester_Exam${AuthorityList[4]}${AuthorityList[6]}${AuthorityList[8]}').click( function() { 
							        $("#form1").valid();
							    });
							
							})
			
							</script>  						 			
			 			</td>
				    </tr>
				    </c:forEach>
                      </tbody>
                    </table>  
                </div>
              </div>
            </div>
          </div>	
	        </div>
       </section>  
      <div class="panel" id="spy4">
        <div class="panel-body pn">
          <div class="table-responsive">
            <div class="bs-component">
                <table class="table table-bordered mbn">
                  <tbody>
				    <tr>
				    <td style="vertical-align:top;border-left:transparent">
					 <div id="SemesterExam"></div>
					 <div id="SemesterExamDetails"> ${param.message}</div>
				    </td>
				    </tr>
                  </tbody>
                </table>  
            </div>
          </div>
        </div>
      </div>
  		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
      </form>
	  <%@include file="../includes/Adsense_Bottom.jsp" %>
      <!-- End: Content -->

      <!-- Begin: Page Scripts -->

		<%@include file="../includes/Page_Scripts.jsp" %>

      <!-- End: Page Scripts -->            
     </section>
    <!-- Start: Right Sidebar -->
			<%@include file="../includes/Topbar_Right_Content.jsp" %>
    <!-- End: Right Sidebar -->
  </div>
  </body>
</html>