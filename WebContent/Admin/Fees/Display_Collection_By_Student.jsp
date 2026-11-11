<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="span6" style="width:80%">
<div class="widget-box">
<div style="overflow-x:auto;">
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Collection by Student</h5>
</div>
	<c:choose>
	<c:when test="${empty Fee_Payment_Amount.student_Payment_Amount}">
	<div class="message">
		No Fee Collections found for these criteria.<br>
		<c:if test="${param.Student_id!=0}">
		<button class="btn btn-info" onclick="showCollectionByStudent(School.value, Year_id.value, Grade.value, 0);"> Go Back</button>
		</c:if>
	</div>
	</c:when>
	<c:otherwise>
		<div class="control-group"> 
		  <div class="controls" style="width:30%; height:30px; margin-top:15px; margin-left:35px;">
		    <select class="span11" onchange="showCollectionByStudent(School.value, Year_id.value, Grade.value, this.value);">
		   	  <option value="">----All Students----</option>
		 	    <c:forEach items="${Students.student_id}" var="Student_id" varStatus="position">
			 	<option value="${Student_id}" <c:if test="${param.Student_id==Student_id}">selected</c:if>><c:out value='${Students.first_Name[position.index]}'/> <c:out value='${Students.last_Name[position.index]}'/></option>
			  </c:forEach> 
		    </select>
		  </div>
		</div>
		<table class="table table-bordered data-table">
 			<tr>			
 			<thead>
 			<tr>
 			<th>Student ID</th>
 			<th>Student Name</th> 			 			
 			<c:forEach items="${Fee_Types_List.fee_id}" var="Fee_id" varStatus="position">
 			<th><c:out value='${Fee_Types_List.fee_Name[position.index]}'/></th> 
 			</c:forEach>
 			<th>Transport Fees</th> 			
 			<th>Total</th> 			 			 			
 			</tr>
 			</thead>
 			<tbody>
   			<c:forEach items="${Fee_Payment_Amount.student_id}" var="Student_id" varStatus="loop">

 			<tr>
   			<td>
  			${Fee_Payment_Amount.student_id[loop.index]}
 			</td>
   			<td>
  			${Fee_Payment_Amount.student_Name[loop.index]}
 			</td>

			<c:forEach items="${Fee_Payment_Amount.student_Payment_Amount}" var="Student_Payment" varStatus="position">

 			<c:if test="${Student_Payment[0]==Student_id}">
   			<td>
 			<c:out value='${Student_Payment[1]}'/>
 			</td>
 			</c:if>
 			</c:forEach> 	 			 			 						
 			<td style="font-weight:bold;">${Fee_Payment_Amount.total_Payment[loop.index]}</td>
 			</tr>
	 		</c:forEach>
	 		<tr>
	 		<td colspan="${fn:length(Fee_Types_List.fee_id)+3}" style="text-align:right; font-weight:bold;">
	 		Total All Students
	 		</td>
	 		<td style="font-weight:bold;">
	 		${Fee_Payment_Amount.totalPayment}
	 		</td>
	 		</tr>		
 			</tbody>

 			</table>
			</c:otherwise>
		</c:choose>
 </div>
 </div>
 </div>