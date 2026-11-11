<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Transport Fees Details</h5>
</div>
 
 <%int n=0;%>
	<c:choose>
	<c:when test="${empty Routes_List.route_id}">
	<div class="message">There are no routes found under this criteria</div>
	</c:when>
	<c:otherwise>
		<table class="table table-bordered data-table">
 			<tr>			
 				<td></td>
		 		<td><label>Transport Fees</label></td>
	 		</tr>			
				<c:forEach items="${Routes_List.route_id}" var="Route_id" varStatus="position">
		 		<tr>
			 		<td><label>Vehicle Name: <c:out value='${Routes_List.vehicle_Name[position.index]}'/> -- Route Name: <c:out value='${Routes_List.route_Name[position.index]}'/></label></td>	
						 <%			 						
						 n=n+1;
						 request.setAttribute("n", n);
						%>			
							<td>
							<c:set var="Existing_Trasport_Fees" value=""></c:set>
							<c:forEach items="${Transport_Fees.route_id}" var="Existing_Route_id" varStatus="position">
							<c:if test="${Existing_Route_id==Route_id}">
							<c:set var="Existing_Trasport_Fees" value="${Transport_Fees.transport_Fees[position.index]}" />
							</c:if>
							</c:forEach>						
							<input name="Route_id${n}" id="Route_id<%out.print(n);%>" type="hidden" value="${Route_id}"></input>
							<input name="cell_count${n}" id="cell_count${n}" type="hidden" value="${n}"></input>					
							<input type="number" step="0.01" class="span11" name="Transport_Fees${n}" id="Transport_Fees${n}" value="${Existing_Trasport_Fees}">									
							</td>
				 </tr>
		      </c:forEach>	
			<tr>
       	 	
       	 	<td colspan="20">
       	 	<div class="submitHolder">
			<input name="Routes_Count" id="Routes_Count" type="hidden" value="${fn:length(Routes_List.route_id)}"></input>       	 	
       	 	<input type="submit" name="Submit" id="Submit" value="Submit" class="btn btn-success" onclick="checkSession();">
       	 	</div>
       	 	</td>
  			</tr> 
 			</table>
	</c:otherwise>
	</c:choose>
 