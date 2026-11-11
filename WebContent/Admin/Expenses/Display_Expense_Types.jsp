<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>				
	<div class="widget-box">
	  <div style="overflow-x:auto;">
		<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			<h5>Expense Types List</h5>
		</div>
		<div class="widget-content nopadding">		
			<c:choose>
				<c:when test="${not empty Expense_Types_List.expense_Type_id}">
				<table id="expense_Types" class="table table-striped table-bordered" border="1" style="width:90%">
				   <thead>
				     <tr>
				       <th>ID</th>
				       <th>Expense Name</th>
				       <th>Expense Description</th>
				       <th>Edit</th> 
				       <th>Delete</th>   							          
				     </tr>
				   </thead>
					<tbody>
				<c:forEach items="${Expense_Types_List.expense_Type_id}" var="Expense_Type_id" varStatus="position">
				<tr>
					<td style="width:50px;">
						${Expense_Type_id}
					</td>
					<td><c:out value='${Expense_Types_List.expense_Type_Name[position.index]}'/> </td>
					<td><c:out value='${Expense_Types_List.expense_Type_Description[position.index]}'/></td>
					<td style="width:5%;">
 		    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
							document.getElementById('Expense_Type_Name').value='<c:out value='${Expense_Types_List.expense_Type_Name[position.index]}'/>';
							document.getElementById('Expense_Type_Description').value='<c:out value='${Expense_Types_List.expense_Type_Description[position.index]}'/>';
							document.getElementById('Debit_Account_id').value='${Expense_Types_List.debit_Account[position.index]}';
							document.getElementById('Credit_Account_id').value='${Expense_Types_List.credit_Account[position.index]}';
							document.getElementById('Expense_Type_id').value='${Expense_Type_id}';				    		  
		    		  "><i class="icon-edit"></i></button>								
					 </td>
					<td style="width:5%;">
		    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Expense_Type_id').value='${Expense_Type_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
					</td>
				</tr>
				</c:forEach>
					</tbody>
				</table>
				</c:when>
				<c:otherwise>
				<br>
				  		<div class="message">There are no Fee Types found under this account.</div>
				</c:otherwise>
			</c:choose>
		</div>
	  </div>
	</div>		         