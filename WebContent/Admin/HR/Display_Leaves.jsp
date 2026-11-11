<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>				
	<div class="widget-box">
	  <div style="overflow-x:auto;">
		<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			<h5>Leaves List</h5>
		</div>
		<div class="widget-content nopadding">		
			<c:choose>
				<c:when test="${not empty Leave_List.leave_id}">
				<table id="leaves" class="table table-striped table-bordered" border="1" style="width:90%">
				   <thead>
				     <tr>
				       <th>ID</th>
				       <th>Leave Name</th>
				       <th>Leave Description</th>
				       <th>Edit</th>
				       <th>Delete</th>    							          
				     </tr>
				   </thead>
					<tbody>
				<c:forEach items="${Leave_List.leave_id}" var="Leave_id" varStatus="position">
				<tr>
					<td style="width:50px;">
						${Leave_id}
					</td>
					<td><c:out value='${Leave_List.leave_Name[position.index]}'/> </td>
					<td  style="width:45%"><c:out value='${Leave_List.leave_Description[position.index]}'/></td>
					<td style="width:5%;">
 		    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
							document.getElementById('Leave_Name').value='<c:out value='${Leave_List.leave_Name[position.index]}'/>';
							document.getElementById('Leave_Description').value='<c:out value='${Leave_List.leave_Description[position.index]}'/>';
							document.getElementById('${Leave_List.payment_Type[position.index]}').checked=true;
							document.getElementById('${Leave_List.period[position.index]}').checked=true;
							document.getElementById('Payroll_id').value='<c:out value='${Leave_List.payroll_id}'/>';
							document.getElementById('Leave_id').value='${Leave_id}';
							if(${Leave_List.balance_no[position.index]>0})
							{
								document.getElementById('Experience_Based').checked=true;
								document.getElementById('All_Employees_Balance').disabled=true;
								document.getElementById('All_Employees_Balance').value='';
							}
							else
							{
								document.getElementById('All_Employees').checked=true;
								document.getElementById('All_Employees_Balance').disabled=false;
								if(${Leave_List.all_Employees_Balance[position.index]!=0})
								{
									document.getElementById('All_Employees_Balance').value='<c:out value='${Leave_List.all_Employees_Balance[position.index]}'/>';
								}
								else
								{
									document.getElementById('All_Employees_Balance').value='';
								}
							}
								if(${Leave_List.carry_Forward[position.index]!=0})
								{
									document.getElementById('Carry_Forward').value='<c:out value='${Leave_List.carry_Forward[position.index]}'/>';
								}
								else
								{
									document.getElementById('Carry_Forward').value='';
								}
							document.getElementById('Leave_Balance_no').value='<c:out value='${Leave_List.balance_no[position.index]}'/>';
							getBalance(${Leave_id});				    		  
		    		  "><i class="icon-edit"></i></button>								
					 </td>
					<td style="width:5%;">
		    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Leave_id').value='${Leave_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
					</td>
				</tr>
				</c:forEach>
					</tbody>
				</table>
				</c:when>
				<c:otherwise>
				<br>
				  		<div class="message">There are no Leaves found under this payroll.</div>
				</c:otherwise>
			</c:choose>
		</div>
	  </div>
	</div>		         