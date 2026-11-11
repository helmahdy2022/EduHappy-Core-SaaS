<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>				
	<div class="widget-box">
	  <div style="overflow-x:auto;">
		<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			<h5>Fee Types List</h5>
		</div>
		<div class="widget-content nopadding">		
			<c:choose>
				<c:when test="${not empty Fee_Types_List.fee_id}">
				<table id="fee_Types" class="table table-striped table-bordered" border="1" style="width:90%">
				   <thead>
				     <tr>
				       <th>ID</th>
				       <th>Fee Name</th>
				       <th>Fee Description</th>
				       <th>Payment Mode</th> 
				       <th>Edit</th>
				       <th>Delete</th>    							          
				     </tr>
				   </thead>
					<tbody>
				<c:forEach items="${Fee_Types_List.fee_id}" var="Fee_id" varStatus="position">
				<tr>
					<td style="width:50px;">
						${Fee_id}
					</td>
					<td><c:out value='${Fee_Types_List.fee_Name[position.index]}'/> </td>
					<td><c:out value='${Fee_Types_List.fee_Description[position.index]}'/></td>
					<td><c:out value='${Fee_Types_List.payment_Mode[position.index]}'/> </td>
					<td style="width:5%;">
		    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
							document.getElementById('Fee_Name').value='<c:out value='${Fee_Types_List.fee_Name[position.index]}'/>';
							document.getElementById('Fee_Description').value='<c:out value='${Fee_Types_List.fee_Description[position.index]}'/>';
							document.getElementById('${Fee_Types_List.payment_Mode[position.index]}').checked=true;
							document.getElementById('Debit_Account_id').value='${Fee_Types_List.debit_Account[position.index]}';
							document.getElementById('Credit_Account_id').value='${Fee_Types_List.credit_Account[position.index]}';
							document.getElementById('Fee_id').value='${Fee_id}';				    		  
		    		  "><i class="icon-edit"></i></button>								
					 </td>
					<td style="width:5%;">
		    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Fee_id').value='${Fee_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
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