<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>		
				<div class="widget-box">
				  <div style="overflow-x:auto;">
					<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
						      <h5>Employee Leave List</h5>
					</div>
					<div class="widget-content nopadding">		
						<c:choose>
							<c:when test="${not empty Employee_Leave_List.employee_Leave_id}">
							<table id="employee_Leaves" class="table table-striped table-bordered" border="1" style="width:90%">
							   <thead>
							     <tr>
							       <th>Employee Leave ID</th>
							       <th>Leave From</th>
							       <th>Leave To</th>
							       <th>Taken Leave Days</th>							       
							       <th>Edit</th>
							       <th>Delete</th>    							          
							     </tr>
							   </thead>
								<tbody>
							<c:forEach items="${Employee_Leave_List.employee_Leave_id}" var="Employee_Leave_id" varStatus="position">
							<tr>
								<td style="width:50px;">
									${Employee_Leave_id}
								</td>
								<td><c:out value='${Employee_Leave_List.leave_From[position.index]}'/> </td>
								<td><c:out value='${Employee_Leave_List.leave_To[position.index]}'/> </td>
								<td><c:out value='${Employee_Leave_List.leave_Days[position.index]}'/> </td>
								<td style="width:5%;">
					    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
										$('#Employee_id').val('${Employee_Leave_List.employee_id}');
										if(document.getElementById('Employee_id').value!='' && document.getElementById('Employee_id').value==${Employee_Leave_List.employee_id})
										{
											$('#Employee_Leave_id').val('${Employee_Leave_id}');
											$('#Leave_From').val('<c:out value='${Employee_Leave_List.leave_From[position.index]}'/>');	
											$('#Leave_To').val('<c:out value='${Employee_Leave_List.leave_To[position.index]}'/>');	
											$('#Leave_Days').val('<c:out value='${Employee_Leave_List.leave_Days[position.index]}'/>');																																									
											$('#Leave_id').val('${Employee_Leave_List.leave_id}');	
										}															    		  
					    		  "><i class="icon-edit"></i></button>								
								 </td>
								<td style="width:5%;">
					    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Employee_Leave_id').value='${Employee_Leave_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
								</td>
							</tr>
							</c:forEach>
								</tbody>
							</table>
							</c:when>
							<c:otherwise>
							<br>
							  		<div class="message">There are no Employee Leaves taken for this leave type.</div>
							</c:otherwise>
						</c:choose>
					</div>
				  </div>
				</div>		     