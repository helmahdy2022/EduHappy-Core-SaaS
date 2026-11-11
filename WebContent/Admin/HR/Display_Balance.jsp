<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String  Leave_id = request.getParameter("Leave_id");
int i=0;
int n;
String last_i;
int Balance = 0;
String Balance_no = null;
Balance_no = request.getParameter("Balance_no");
String Edit = request.getParameter("Edit");
String Add_New =null;
Add_New = request.getParameter("Add_New");
//i = Integer.parseInt(Units_no);
if (Add_New!=null)
{
	Balance_no="1";
	last_i=request.getParameter("last_i");
}
else
{
	last_i="0";
}

n = 1+ Integer.parseInt(last_i);
Balance = Integer.parseInt(Balance_no)+Integer.parseInt(last_i);	

			for (i=n; i < Balance+1; i++)
			{		
%>

                  <div id="Balance_Row<%=i%>">
	                 <div style="display:inline-block;width:45%;">
						Years on board
					    <%request.setAttribute("i", i-1);%>* 
					 </div>
	                 <div style="display:inline-block;width:45%;">
	                	Balance Count
	                 </div>
	            <!-- Input Fields -->
                    <div>
                      <div>
						<c:choose>
						<c:when test="${param.Edit!=null}">
						<div style="display:inline-block;width:45%;">
						 <input type="number" id="Balance_Years<%=i%>" name="Balance_Years<%=i%>" class="span11" value="<c:out value='${Leave_Balance_List.balance_Years[i]}'/>" step="0.01" required min="0" max="1000000000" required>
						 </div>
						 <div style="display:inline-block;width:45%;">
						 <input type="number" id="Balance_Count<%=i%>" name="Balance_Count<%=i%>" class="span11" value="<c:out value='${Leave_Balance_List.balance_Count[i]}'/>" step="0.01" required min="0" max="1000000000" required>
						</div>
						<input type="hidden" name="Leave_Balance_id<%=i%>" id="Leave_Balance_id<%=i%>" value="${Leave_Balance_List.leave_Balance_id[i]}">
						</c:when>
						<c:otherwise>
						<div style="display:inline-block;width:45%;margin-bottom:10px">
						<input type="number" id="Balance_Years<%=i%>" name="Balance_Years<%=i%>" class="span11"  step="0.01" required min="0" max="1000000000" required>
						</div>
						<div style="display:inline-block;width:45%;margin-bottom:10px">				
						<input type="number" id="Balance_Count<%=i%>" name="Balance_Count<%=i%>" class="span11"  step="0.01" required min="0" max="1000000000" required>
						</div>
						</c:otherwise>
						</c:choose>	
						<c:choose>						 									
							<c:when test="${Leave_Balance_List.leave_Balance_id[i]!=null}">
    							<div style="display:inline-block;">

								 <button type="button" name ="Delete" id="Delete" value="Delete" class="btn btn-danger btn-block" style="display:inline-block;width:30px" onClick="deleteBalance(this.value,${Leave_Balance_List.leave_Balance_id[i]},<%=i%>, <%=Balance-1%>, <%=Leave_id%>, document.getElementById('Balance_qty').value);"> 
								 X
								 </button>
    							</div>
							</c:when>
							<c:otherwise>							
								<div style="display:inline-block;">
								 <button type="button" name ="Delete" id="Delete" value="Delete" class="btn btn-danger btn-block" style="display:inline-block;width:30px" onClick="deleteBalance(this.value,0,<%=i%>,<%=Balance-1%>,<%=Leave_id%>, document.getElementById('Balance_qty').value)">		      
								 X
								 </button>
								</div>
							</c:otherwise>
						</c:choose>	   						                
                      </div>
                    </div>
	              </div>            
<%

			}
			if (Add_New==null)
			{
%>			
					<input  name="Balance_qty" id="Balance_qty" type="hidden" value="<c:out value='<%=Balance%>'/>">   
<%
			}
			else
			{
%>
					<input  name="Balance_qty" id="Balance_qty" type="hidden" value="<c:out value='<%=last_i%>'/>"> 
<%
			}
%>