<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>		
				<div class="widget-box">
				  <div style="overflow-x:auto;">
					<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
						      <h5>Expenses List</h5>
					</div>
					<div class="widget-content nopadding">		
						<c:choose>
							<c:when test="${not empty Expenses_List.expense_id}">
							<table id="expenses" class="table table-striped table-bordered" border="1" style="width:90%">
							   <thead>
							     <tr>
							       <th>Expense ID</th>
							       <th>Expense Date</th>
							       <th>Expense Type</th>
							       <th>Expense Amount</th> 
							       <th>Payment Method</th>
							       <th>Edit</th>
								   <th>Delete</th>   							          
							     </tr>
							   </thead>
								<tbody>
							<c:forEach items="${Expenses_List.expense_id}" var="Expense_id" varStatus="position">
							<tr>
								<td style="width:50px;">
									${Expense_id}
								</td>
								<td><c:out value='${Expenses_List.expense_Date[position.index]}'/></td>
								<td><c:out value='${Expenses_List.expense_Type_Name[position.index]}'/> </td>
								<td><c:out value='${Expenses_List.expense_Amount[position.index]}'/></td>
								<td><c:out value='${Expenses_List.payment_Method[position.index]}'/> </td>
								<td style="width:5%;">
					    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
										$('#Expense_Date').val('<c:out value='${Expenses_List.expense_Date[position.index]}'/>');
										$('#Reference_Number').val('<c:out value='${Expenses_List.reference_Number[position.index]}'/>');
										$('#Paid_To').val('<c:out value='${Expenses_List.paid_To[position.index]}'/>');
										$('#Expense_Amount').val('<c:out value='${Expenses_List.expense_Amount[position.index]}'/>');
										$('#Payment_Method').val('<c:out value='${Expenses_List.payment_Method[position.index]}'/>');										
										$('#Phone_Number').val('<c:out value='${Expenses_List.phone_Number[position.index]}'/>');																	
										$('#Expense_Details').val('<c:out value='${Expenses_List.expense_Details[position.index]}'/>');
										$('#Expense_id').val('${Expense_id}');
										$('#Expense_Type_id').val('${Expenses_List.expense_Type_id}');													    		  
					    		  "><i class="icon-edit"></i></button>								
								 </td>
								<td style="width:5%;">
					    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Expense_id').value='${Expense_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
								</td>
							</tr>
							</c:forEach>
								</tbody>
							</table>
							</c:when>
							<c:otherwise>
							<br>
							  		<div class="message">There are no Expenses found under this school.</div>
							</c:otherwise>
						</c:choose>
					</div>
				  </div>
				</div>		     