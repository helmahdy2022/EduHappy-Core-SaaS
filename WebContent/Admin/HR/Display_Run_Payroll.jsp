<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>				
	<div class="widget-box">
	  <div style="overflow-x:auto;">
		<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			<h5>Employees Payroll for the month of <font color="red">${param.Payroll_Month} ${param.Payroll_Year}</font></h5>
		</div>
		<div class="widget-content nopadding">		
		<c:choose>
		  <c:when test="${param.Payroll_Exist==true}">
			<div class="message">The Payroll for this month is already processed. Please check the Payroll Reports.</div>
		  </c:when>
		  <c:otherwise>
			<c:choose>
				<c:when test="${not empty Payroll.employee_id}">
				<table class="table table-bordered data-table">
				   <thead>
				     <tr>
				       <th>Employee ID</th>
				       <th>Employee Name</th>
				       <th>Basic Salary</th>
				       <th>Unpaid Leave</th>
				       <th>Overtime</th>  
			 		   <c:forEach items="${Allowance_List.allowance_id}" var="Allowance_id" varStatus="position">
			 			<th><c:out value='${Allowance_List.allowance_Name[position.index]}'/></th> 
			 		   </c:forEach>				       							          
			 		   <c:forEach items="${Deduction_List.deduction_id}" var="Deduction_id" varStatus="position">
			 			<th><c:out value='${Deduction_List.deduction_Name[position.index]}'/></th> 
			 		   </c:forEach>
			 		   <th>Net Salary</th>
				     </tr>
				   </thead>
					<tbody>
				<c:forEach items="${Payroll.employee_id}" var="Employee_id" varStatus="position">
				<tr class="gradeA">
					<td style="width:50px;">
						${Employee_id}
					</td>
					<td><c:out value='${Payroll.first_Name[position.index]}'/> <c:out value='${Payroll.last_Name[position.index]}'/></td>
					<td><c:out value='${Payroll.basic_Salary[position.index]}'/> </td>
					<td><c:out value='${Payroll.unpaid_Leave[position.index]}'/> </td>
					<td><c:out value='${Payroll.overtime_Earning[position.index]}'/> </td>
					<c:forEach items="${Payroll.employee_Allowance_Amount}" var="Allowance_Amount" varStatus="loop">			 			
		   			<c:if test="${Allowance_Amount[0]==Employee_id}">		   			
		   			<td>
		 			<c:out value='${Allowance_Amount[2]}'/>
		 			<input type="hidden" name="Employee_id${Employee_id}Allowance_id${Allowance_Amount[1]}" id="Employee_id${Employee_id}Allowance_id${Allowance_Amount[1]}" value="${Allowance_Amount[1]}">
		 			<input type="hidden" name="Employee_id${Employee_id}Allowance_Amount${Allowance_Amount[1]}" id="Employee_id${Employee_id}Allowance_Amount${Allowance_Amount[1]}" value="${Allowance_Amount[2]}">
		 			</td>		 			
		 			</c:if>		 			
		 			</c:forEach> 						
					<c:forEach items="${Payroll.employee_Deduction_Amount}" var="Deduction_Amount" varStatus="loop">			 			
		   			<c:if test="${Deduction_Amount[0]==Employee_id}">		   			
		   			<td>
		 			<c:out value='${Deduction_Amount[2]}'/>
		 			<input type="hidden" name="Employee_id${Employee_id}Deduction_id${Deduction_Amount[1]}" id="Employee_id${Employee_id}Deduction_id${Deductione_Amount[1]}" value="${Deduction_Amount[1]}">
		 			<input type="hidden" name="Employee_id${Employee_id}Deduction_Amount${Deduction_Amount[1]}" id="Employee_id${Employee_id}Deduction_Amount${Deduction_Amount[1]}" value="${Deduction_Amount[2]}">
		 			</td>		 			
		 			</c:if>		 			
		 			</c:forEach>
		 			<td>
		 			 <c:out value='${Payroll.net_Salary[position.index]}'/>
		 			</td>
				</tr>
				<input type="hidden" name="Payroll_id" id="Payroll_id" value="${param.Payroll_id}">
				<input type="hidden" name="Employee_id${Employee_id}" id="Employee_id${Employee_id}" value="${Employee_id}">
				<input type="hidden" name="Basic_Salary${Employee_id}" id="Basic_Salary${Employee_id}" value="${Payroll.basic_Salary[position.index]}">				
				<input type="hidden" name="Unpaid_Leave${Employee_id}" id="Unpaid_Leave${Employee_id}" value="${Payroll.unpaid_Leave[position.index]}">	
				<input type="hidden" name="Overtime${Employee_id}" id="Overtime${Employee_id}" value="${Payroll.overtime_Earning[position.index]}">	
				<input type="hidden" name="Net_Salary${Employee_id}" id="Net_Salary${Employee_id}" value="${Payroll.net_Salary[position.index]}">	
				</c:forEach>
				<tr>
				<td style="text-align:right" colspan="${fn:length(Allowance_List.allowance_id)+fn:length(Deduction_List.deduction_id)+5}"><b>Total Salaries</b></td>
				<td><b><c:out value='${Payroll.total_Salaries}'/></b></td>	
				</tr>			
				</tbody>
				</table>
	           <div class="form-actions">
	           <input type="submit" name="Submit" id="Submit" value="Process Payroll" class="btn btn-success">
				<input type="hidden" name="Total_Salaries" id="Total_Salaries" value="${Payroll.total_Salaries}">	
			   </div> 
				</c:when>
				<c:otherwise>
				<br>
				  	<div class="message">There are no Employees found under this payroll.</div>
				</c:otherwise>
			</c:choose>
		  </c:otherwise>
		</c:choose>
		</div>
	  </div>
	</div>		         