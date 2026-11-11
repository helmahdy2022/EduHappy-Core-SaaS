<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>				
	<div class="widget-box">
	  <div style="overflow-x:auto;">
		<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			<h5>Payrolls List</h5>
		</div>
		<div class="widget-content nopadding">		
			<c:choose>
				<c:when test="${not empty Payroll_List.payroll_id}">
				<table id="payrolls" class="table table-striped table-bordered" border="1" style="width:90%">
				   <thead>
				     <tr>
				       <th>ID</th>
				       <th>Payroll Name</th>
				       <th>Payroll Description</th>
				       <th>Edit</th>
				       <th>Delete</th>    							          
				     </tr>
				   </thead>
					<tbody>
				<c:forEach items="${Payroll_List.payroll_id}" var="Payroll_id" varStatus="position">
				<tr class="gradeA">
					<td style="width:50px;">
						${Payroll_id}
					</td>
					<td><c:out value='${Payroll_List.payroll_Name[position.index]}'/> </td>
					<td><c:out value='${Payroll_List.payroll_Description[position.index]}'/></td>
					<td style="width:5%;">
 		    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
							document.getElementById('Payroll_Name').value='<c:out value='${Payroll_List.payroll_Name[position.index]}'/>';
							document.getElementById('Payroll_Description').value='<c:out value='${Payroll_List.payroll_Description[position.index]}'/>';
							document.getElementById('Working_Days').value='${Payroll_List.working_Days[position.index]}';
							$('#Working_Time_From').val('<c:out value='${Payroll_List.working_Time_From[position.index]}'/>');
							$('#Working_Time_To').val('<c:out value='${Payroll_List.working_Time_To[position.index]}'/>');
							document.getElementById('Overtime_Normal').value='${Payroll_List.overtime_Normal[position.index]}';
							document.getElementById('Overtime_Weekend').value='${Payroll_List.overtime_Weekend[position.index]}';
							document.getElementById('Overtime_Holidays').value='${Payroll_List.overtime_Holidays[position.index]}';
							document.getElementById('Overtime_Daysoff').value='${Payroll_List.overtime_Daysoff[position.index]}';
							document.getElementById('Debit_Account_id').value='${Payroll_List.debit_Account[position.index]}';
							document.getElementById('Credit_Account_id').value='${Payroll_List.credit_Account[position.index]}';
							document.getElementById('Payroll_id').value='${Payroll_id}';				    		  
		    		  "><i class="icon-edit"></i></button>								
					 </td>
					<td style="width:5%;">
		    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Payroll_id').value='${Payroll_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
					</td>
				</tr>
				</c:forEach>
					</tbody>
				</table>
				</c:when>
				<c:otherwise>
				<br>
				  		<div class="message">There are no Payrolls found under this school.</div>
				</c:otherwise>
			</c:choose>
		</div>
	  </div>
	</div>		         