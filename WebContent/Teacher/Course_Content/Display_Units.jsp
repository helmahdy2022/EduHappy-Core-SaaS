<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String  Course_id = request.getParameter("Course_id");
int i=0;
int n;
String last_i;
int Units = 0;
String Units_no = null;
Units_no = request.getParameter("Units_no");
String Edit = request.getParameter("Edit");
String Add_New =null;
Add_New = request.getParameter("Add_New");
//i = Integer.parseInt(Units_no);
if (Add_New!=null)
{
	Units_no="1";
	last_i=request.getParameter("last_i");

}
else
{
	last_i="0";
}

n = 1+ Integer.parseInt(last_i);
Units = Integer.parseInt(Units_no)+Integer.parseInt(last_i);	


if (Add_New==null)
{
%>

		<form class="form-horizontal" role="form" method="post" id="form1" action="${pageContext.request.contextPath}/Manage_Unit">
		   <div class="panel">
              <div class="panel-heading">
                <span class="glyphicon glyphicon-th"></span> Create and manage course units
              </div>
              <div class="panel-body">
<%}%>

<% 
			for (i=n; i < Units+1; i++)
			{		
%>

                  <div class="form-group" id="Unit_Row<%=i%>">
	                 <label class="col-lg-3 control-label">
						Unit Name
					    <%request.setAttribute("i", i-1);%>
					    <%=i%> *
	                 </label>
	            <!-- Input Fields -->
	
                    <div class="col-lg-8">
                      <div class="bs-component">
						<c:choose>
						<c:when test="${param.Edit!=null}">
						<div style="display:inline-block;width:90%;">
						 <input type="text" id="Unit_Name<%=i%>" name="Unit_Name<%=i%>" class="form-control" value="<c:out value='${TeacherContent.contentunitmap[param.Course_id][i][1]}'/>" required maxlength="255">
						</div>
						</c:when>
						<c:otherwise>
						<div style="display:inline-block;width:90%;">
						<input type="text" id="Unit_Name<%=i%>" name="Unit_Name<%=i%>" class="form-control" required maxlength="255">
						</div>
						</c:otherwise>
						</c:choose>	
						<c:choose>						 									
							<c:when test="${TeacherContent.contentunitmap[param.Course_id][i][0]!=null}">
    							<div style="display:inline-block;">

								 <input type="button" name ="Delete" id="Delete" value="Delete" class="btn btn-danger btn-block" style="display:inline-block;" onClick="deleteUnit(this.value,${TeacherContent.contentunitmap[param.Course_id][i][0]},<%=i%>, <%=Units-1%>, <%=Course_id%>, document.getElementById('Units_qty').value);"> 

    							</div>
							</c:when>
							<c:otherwise>							
								<div style="display:inline-block;">
								 <input type="button" name ="Delete" id="Delete" value="Delete" class="btn btn-danger btn-block" onClick="deleteUnit(this.value,0,<%=i%>,<%=Units-1%>,<%=Course_id%>, document.getElementById('Units_qty').value)">		      
								</div>
							</c:otherwise>
						</c:choose>	   						                
                      </div>
                    </div>
	              </div>            
					<c:choose>
					<c:when test="${TeacherContent.contentunitmap[param.Course_id][i][0]!=null}">
						<input type="hidden" id="Edit<%=i%>" name="Edit<%=i%>" value=<c:out value='<%=Edit%>'/>>
					</c:when>
					<c:otherwise>
						<input type="hidden" id="Edit<%=i%>" name="Edit<%=i%>" value="none">
					</c:otherwise>
					</c:choose>
					<input type="hidden" id="Unit_id<%=i%>" name="Unit_id<%=i%>" value="${TeacherContent.contentunitmap[param.Course_id][i][0]}">					
					<input  name="Units_qty" id="Units_qty" type="hidden" value="<c:out value='<%=Units%>'/>">    
<%

			}

%>			
				<div  id="OneUnit">

		        </div>

<%
			if ((Units!=0)&&(Add_New==null))
			{
				
%>	
               <div class="form-group" style="text-align:center;">
				<input name="Course_id" id="Course_id" type="hidden" value="<%=Course_id%>">
				<input name="i" id="i" type="hidden" value="<c:out value='<%=Units%>'/>">
				<button type="submit"  id="Submit" name="Submit" class="btn btn-success mr10"><i class="fa fa-save"></i> Save</button>
				<input type="button" id="Add_New" name="Add_New" class="btn btn-info mr10" value="Add New" onClick="showOneUnit(this.value,i.value);">
				<input name="Page" id="Page" type="hidden" value="Unit">
				<div class="requiredSignNote">Fields marked (*) are required</div>
			  </div> 
<%
				
			}
%>	
 
																			

<%
if (Add_New==null)
{
%>
 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
	</div>
	</div>
	</form>
<%
}
%>
