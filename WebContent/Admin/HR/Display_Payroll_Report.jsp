<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>				
	<div class="widget-box">
	  <div style="overflow-x:auto;">
		<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			<c:if test="${param.Payroll_Month!='null' && param.Payroll_Year!='null'}">
			<h5>Employees Payroll for the month of <font color="red">${param.Payroll_Month} ${param.Payroll_Year}</font></h5>
			</c:if>
		</div>
		<div class="widget-content nopadding">		
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
			 		   <c:forEach items="${Allowance_List.allowance_Name}" var="Allowance_Name" varStatus="position">
			 			<th><c:out value='${Allowance_Name}'/></th> 
			 		   </c:forEach>				       							          
			 		   <c:forEach items="${Deduction_List.deduction_Name}" var="Deduction_Name" varStatus="position">
			 			<th><c:out value='${Deduction_Name}'/></th> 
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
				 			<c:forEach items="${Allowance_List.allowance_Name}" var="Allowance_Name">
				 				<c:if test="${Allowance_Name==Allowance_Amount[1]}">
				 					<c:out value='${Allowance_Amount[2]}'/>
				 				</c:if>
				 			</c:forEach>
				 			</td>		 			
				 			</c:if>		 			
				 			</c:forEach> 						
							<c:forEach items="${Payroll.employee_Deduction_Amount}" var="Deduction_Amount" varStatus="loop">			 			
				   			<c:if test="${Deduction_Amount[0]==Employee_id}">		   			
				   			<td>
				 			<c:forEach items="${Deduction_List.deduction_Name}" var="Deduction_Name">
				 				<c:if test="${Deduction_Name==Deduction_Amount[1]}">
				 					<c:out value='${Deduction_Amount[2]}'/>
				 				</c:if>
				 			</c:forEach>	
				 			</td>		 			
				 			</c:if>		 			
				 			</c:forEach>
				 			<td>
				 			 <c:out value='${Payroll.net_Salary[position.index]}'/>
				 			</td>
						</tr>
						</c:forEach>
						<tr>
						<td style="text-align:right" colspan="${fn:length(Allowance_List.allowance_Name)+fn:length(Deduction_List.deduction_Name)+5}"><b>Total Salaries</b></td>
						<td><b><c:out value='${Payroll.total_Salaries}'/></b></td>	
						</tr>
					</tbody>
				</table>
	           <c:if test="${Payroll.payroll_Month == param.Current_Month && Payroll.payroll_Year == param.Current_Year}">
		           <div class="form-actions">
		           <input type="button" name="Delete" id="Delete" value="Delete Payroll Report" class="btn btn-danger" onClick="showDeleteMessage();">
		           </div> 
	           </c:if>				
				</c:when>
				<c:otherwise>
				<br>
				  	<div class="message">There are no Employees found under this payroll.</div>
				</c:otherwise>
			</c:choose>
		</div>
	  </div>	
	</div>	         