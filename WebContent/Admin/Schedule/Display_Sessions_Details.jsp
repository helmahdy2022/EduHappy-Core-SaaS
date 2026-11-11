<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		 <c:choose>
			<c:when test="${param.n!=null || param.No_Existing_Sessions == 1}">
			    <c:if test="${param.No_Existing_Sessions == 1}">
 					<div class="control-group">
						<label class="control-label">Number of Sessions</label>
						<div class="controls">
							<input name="Sessions_no" type="number" id="Sessions_no" class="span11" min="0" onchange="showSessions(this.value, '${param.Grade_id}')" onkeyup="showSessions(this.value, '${param.Grade_id}')"/>
						</div>
					</div>	
    			</c:if>
						<c:forEach var="i" begin="${param.n}" end="${param.Sessions}">	
 	 					<table class="table table-bordered data-table" id="cell${i}">					
							<c:if test="${param.Sessions!=0 && param.Function!='Add Session' && i==1}">
							<tr>
							<td></td>
							<td>Time From</td>
							<td>Time To</td>
							<td></td>
							</tr>
							</c:if>	 	 				
					 		<tr>
					            <td>Session ${i}</td>		
					            <td><input name="Time_From${i}" type="time" id="Time_From${i}" class="span11" required></td>		
					            <td><input name="Time_To${i}" type="time" id="Time_To${i}" class="span11" required></td>
					            <td>
					            <input type="button" name="Delete" id="Delete"  class="btn btn-danger" value="Delete" onClick="deleteSession(cell${i},${i}, Session_Index${i}.value, '${param.Grade_id}')">
					       		<input name="Session_Index${i}" type="hidden" value="${i}">
					            <input name="Sessions_qty" type="hidden" value="${param.Sessions}">
					            </td>			 
					 		</tr>
					 	</table>		 					
						</c:forEach>
					 	<c:if test="${param.No_Existing_Sessions == 1 && param.Sessions!=0}">
						<div class="submitHolder"><input type="submit" name="Submit" id="Submit" value="Submit" class="btn btn-success"></div>
						 </c:if>			 
			</c:when>
			<c:otherwise>
				<c:if test="${param.Function!='Delete'}">
					<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
					<h5>Sessions Details</h5>
					</div>
					<table class="table table-bordered data-table" id="sessionTable">
					<tr>
					<td></td>
					<td><label>Time From</label></td>
					<td><label>Time To</label></td>
					<td></td>
					</tr>		
		         <%int i=0; %>
		         <c:forEach items="${Sessions_Details.existingSessions}" var="Session" varStatus="position">
					<c:if test="${Session[0]!=null}">
						<%					
						i=i+1;
						request.setAttribute("i", i);%>
					<tr id="cell${i}">						
			       	 	<td><label>Session ${i}</label></td>
			            <td>
			            <input name="Time_From${i}" type="time" class="span11" id="Time_From${i}" value="<c:out value='${Session[1]}'/>" required/>
			            </td>
			            <td>
			            <input name="Time_To${i}" type="time" class="span11" id="Time_To${i}" value="<c:out value='${Session[2]}'/>" required/>
			            </td>
	       	 			<td>
	       	 			<c:if test="${Session[3]==null}">
	       	 			<input type="button" name="Delete" id="Delete"  class="btn btn-danger" value="Delete"  onClick="deleteSession(this,${i}, Session_Index${i}.value, '${param.Grade_id}','${Session[0]}')"/>
	       	 			</c:if>
	       	 			<input name="Session_Index${i}" type="hidden" value="${i}"/>
			            <input name="Session_id${i}" type="hidden" value="${Session[0]}"/>
			            <input name="Sessions_qty" id="Sessions_qty" type="hidden" value="${param.Session_qty}"/>	 
	       	 			</td>
			 		</tr>
						</c:if>
					</c:forEach>
					<tr>	
					<td colspan="4">	
					<div id="OneSession">
			        </div>
			        </td> 
					</tr> 
					<tr> 
		       	 	<td colspan="4">
			       	 	<input type="button" name="Add_New" id="Add_New" value="Add Session" class="btn btn-info" onClick="showOneSession(this.value,i.value, '${param.Grade_id}')">
			       	 	<input type="submit" name="Submit" id="Submit" value="Submit" class="btn btn-success">
			       	 	<input name="i" id="i" type="hidden" value="${i}"/>
		       	 	</td>
			        </tr> 
  				</table>
				</c:if>			
			</c:otherwise>
		</c:choose> 	

