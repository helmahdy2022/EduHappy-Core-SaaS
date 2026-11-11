<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Course Content Main</title>
</head>
<script>
function showUnits(Units_no, Course_id)
{
if (Units_no=="" || Course_id=="")
  {
  document.getElementById("Units").innerHTML="";
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
	  
	  document.getElementById("Units").innerHTML=xmlhttp.responseText;	  
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Teacher/Course_Content/Display_Units.jsp?Units_no="+Units_no+ "&Course_id="+Course_id,true);
xmlhttp.send();
}

function showOneUnit(Add_New,last_i)
{
	
if (Add_New=="")
  {
  document.getElementById("OneUnit").innerHTML="";
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
	 document.getElementById("OneUnit").appendChild(newElement);
	 document.getElementById("i").value=parseInt(last_i)+1;
   	 document.getElementById("Units_qty").value=parseInt(last_i)+1;

    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Teacher/Course_Content/Display_Units.jsp?Add_New="+Add_New+"&last_i="+last_i,true);
xmlhttp.send();
}

function editUnits(Course_id, Units_no)
{
document.getElementById("Units").innerHTML="<div style='display: flex;justify-content: center;align-items: center;'><img src='${pageContext.request.contextPath}/images/Spinner.gif' align='middle'></div>";
if (Course_id=="")
  {
  document.getElementById("Units").innerHTML="";
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
    document.getElementById("Units").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Teacher/Course_Content/Display_Units.jsp?Units_no="+Units_no+"&&Course_id="+Course_id+"&&Edit=1",true);
xmlhttp.send();
}

function deleteUnit(Delete, Unit_id, i, last_i, Course_id, Units_qty)
{ 
	if (Units_qty==1)
	{
		document.getElementById("UnitLink"+Course_id).innerHTML="<input type='number' id='' name='' min='1' class='form-control' placeholder='Enter number of units' size='2' onChange='javascript:showUnits(this.value,"+Course_id+");'></input>";
		document.getElementById('Units').innerHTML="";
	}
if (Delete=="" && Unit_id=="")
  {
  document.getElementById("Unit_Row"+i).innerHTML="";

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
      document.getElementById("Unit_Row"+i).parentNode.removeChild(document.getElementById("Unit_Row"+i));
      document.getElementById("UnitLink"+Course_id).innerHTML="<a href='javascript:editUnits("+Course_id+","+last_i+");'>Manage Existing Units</a>";
      document.getElementById("Units").innerHTML=xmlhttp.responseText;
    }
  };
xmlhttp.open("GET","${pageContext.request.contextPath}/Manage_Unit?Delete="+Delete+"&&Unit_id="+Unit_id+"&&Course_id="+Course_id+"&&i="+i+"&&last_i="+last_i+"&&Units_qty="+Units_qty+"&&Page=Unit",true);
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
            <li class="crumb-trail">Course Content</li>
             <li class="crumb-link">
              <a href="${pageContext.request.contextPath}/Teacher/Course_Content/Manage_Unit.jsp">Course Units</a>
            </li>
          </ol>
        </div>
		<%@include file="../includes/Topbar_Right.jsp" %>
      </header>
      <!-- End: Topbar -->
       <section id="content" class="table-layout animated fadeIn">            
	        <div class="page-heading">
		  	<%@include file="../includes/Adsense_Top.jsp" %>
	          <h4> Course Units</h4>
	          <hr class="alt short">
          <div class="panel" id="spy4">
            <div class="panel-heading">
              <span class="panel-title">
                <span class="glyphicon glyphicon-th"></span>Units</span>
              <div class="pull-right hidden-xs">
                <code class="mr20">Course Units Content Authority List</code>
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
                          <th>Course</th> 
                          <th style="text-align:center;">Actions</th>                                                                                                                                                                
                        </tr>
                      </thead>
                      <tbody>
					<c:forEach items="${TeacherContent.contentList}" var="ContentList"> 
				    <tr id="${ContentList[1]}${ContentList[3]}${ContentList[5]}">
					    <td style="width:20px;"><i class="fa fa-circle text-warning fs8 pr15"></i></td>
						<td><c:out value="${ContentList[1]}"/></td>
						<td><c:out value="${ContentList[3]}"/></td>
						<td><c:out value="${ContentList[5]}"/></td>
						<td style="max-width:70px;border-right:transparent;text-align:center;">					
		 			  	<div id="UnitLink${ContentList[4]}">					 			
						<c:set var="UnitMap" value="${TeacherContent.contentunitmap}"/>
							<c:forEach items="${UnitMap}" var="Unit" >										
								<c:if test="${Unit.key == ContentList[4]}">
									<c:choose>
										<c:when test="${not empty Unit.value}">
			 						 				<div id="UnitLink${ContentList[4]}">
			 						 				<a href="javascript:editUnits(${ContentList[4]},${TeacherContent.contentunitmap[ContentList[4]].size()});">Manage Existing Units</a>
			 						 				</div>
									      </c:when> 
									      <c:otherwise>
		    											<input type="number" id="" name="" class="form-control" placeholder="Enter number of units" min="1" 
												onkeyup="javascript:showUnits(this.value,${ContentList[4]})"  onChange="javascript:showUnits(this.value,${ContentList[4]})"></input>	 
									      </c:otherwise> 
									</c:choose>
							</c:if>
							</c:forEach>
				 		</div>
						</td>
						<td style="width:20px;border-left:transparent;"></td>
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
					 <div id="Units"> 
					 	<c:if test="${not empty param.message}">
					  		<div class="message">${param.message}</div> 
					 	</c:if>
					 </div>			    
				    </td>
				    </tr>
                  </tbody>
                </table>  
            </div>
          </div>
        </div>
      </div>
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