<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div style="overflow-x:auto;">
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Fee Structure Details</h5>
</div>
 
 <%int n=0;%>
	<c:choose>
	<c:when test="${empty Fee_Types_List.fee_id}">
	<div class="message">No Fee Types have been created for this Fee Structure. Please create fee types and then try again.</div>
	</c:when>
	<c:otherwise>
		<table class="table table-bordered data-table">
 			<tr>			
 				<td></td>
 			  	<c:forEach items="${Fee_Types_List.fee_id}" var="Fee_id" varStatus="position">
 			  		<c:if test = "${Fee_id!=null}">		
		 				<td><label><c:out value='${Fee_Types_List.fee_Name[position.index]}'/></label></td>
		 			</c:if>
		      </c:forEach>	
	 		</tr>			
				<c:forEach items="${Grades.grade_id}" var="Grade_id" varStatus="position">
		 		<tr>
			 		<td><label><c:out value='${Grades.grade_Name[position.index]}'/></label></td>
 
 					<c:forEach items="${Fee_Types_List.fee_id}" var="Fee_id" varStatus="position">
 						<c:if test = "${Fee_id!=null}">		
						 <%			 						
						 n=n+1;
						 request.setAttribute("n", n);
						%>			
							<td>
			       			<c:set var="Existing_Fee_Amount" value=""></c:set>
			       			<c:forEach items="${Fee_Structure.fee_id}" var="Existing_Fee_id" varStatus="loop">
			       				<c:if test = "${Existing_Fee_id==Fee_id && Fee_Structure.grade_id[loop.index]==Grade_id}">
									<c:set var="Existing_Fee_Amount" value="${Fee_Structure.fee_Amount[loop.index]}"></c:set>							
			       				</c:if>	
			       			</c:forEach>	
							<input name="Fee_id${n}" id="Fee_id<%out.print(n);%>" type="hidden" value="${Fee_id}"></input>
							<input name="cell_count${n}" id="cell_count${n}" type="hidden" value="${n}"></input>
							<input name="Grade_id${n}" id="Grade_id" type="hidden" value="${Grade_id}"></input>						
							<input type="number" step="0.01" class="span11" name="Fee_Amount${n}" id="Fee_Amount${n}" value="${Existing_Fee_Amount}">									
							</td>
						</c:if>
					</c:forEach>
				 </tr>
		      </c:forEach>	
			<tr>
       	 	
       	 	<td colspan="20">
       	 	<div class="submitHolder">
			<input name="Fees_Count" id="Fees_Count" type="hidden" value="${fn:length(Fee_Types_List.fee_id)}"></input>
			<input name="Grades_Count" id="Grades_Count" type="hidden" value="${fn:length(Grades.grade_id)}"></input>        	 	
       	 	<input type="submit" name="Submit" id="Submit" value="Submit" class="btn btn-success" onclick="checkSession();">
       	 	</div>
       	 	</td>
  			</tr> 
 			</table>
			</c:otherwise>
		</c:choose>
</div> 