<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>		
				<div class="widget-box">
				  <div style="overflow-x:auto;">
					<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
						      <h5>Employee Overtime List</h5>
					</div>
					<div class="widget-content nopadding">		
						<c:choose>
							<c:when test="${not empty Overtime_List.overtime_id}">
							<table id="overtime" class="table table-striped table-bordered" border="1" style="width:90%">
							   <thead>
							     <tr>
							       <th>Overtime ID</th>
							       <th>Overtime Date</th>
							       <th>Overtime Hours</th>							       
							       <th>Edit</th> 
							       <th>Delete</th>    							          
							     </tr>
							   </thead>
								<tbody>
							<c:forEach items="${Overtime_List.overtime_id}" var="Overtime_id" varStatus="position">
							<tr class="gradeA">
								<td style="width:50px;">
									${Overtime_id}
								</td>
								<td><c:out value='${Overtime_List.overtime_Date[position.index]}'/> </td>
								<td><c:out value='${Overtime_List.overtime_Hours[position.index]}'/> </td>
								<td style="width:5%;">
					    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
										$('#Employee_id').val('${Overtime_List.employee_id}');
										if(document.getElementById('Employee_id').value!='' && document.getElementById('Employee_id').value==${Overtime_List.employee_id})
										{
											$('#Overtime_id').val('${Overtime_id}');
											$('#Overtime_Date').val('<c:out value='${Overtime_List.overtime_Date[position.index]}'/>');	
											$('#Overtime_Hours').val('<c:out value='${Overtime_List.overtime_Hours[position.index]}'/>');																																									
										}															    		  
					    		  "><i class="icon-edit"></i></button>								
								 </td>
								<td style="width:5%;">
					    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Overtime_id').value='${Overtime_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
								</td>
							</tr>
							</c:forEach>
								</tbody>
							</table>
							</c:when>
							<c:otherwise>
							<br>
							  		<div class="message">There are no Employee Overtime applied for this employee.</div>
							</c:otherwise>
						</c:choose>
					</div>
				  </div>
				</div>		     