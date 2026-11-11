<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>		
				<div class="widget-box">
				  <div style="overflow-x:auto;">
					<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
						      <h5>Employee Promotion List</h5>
					</div>
					<div class="widget-content nopadding">		
						<c:choose>
							<c:when test="${not empty Employee_Promotion_List.employee_Promotion_id}">
							<table  id="promotions" class="table table-striped table-bordered" border="1" style="width:90%">
							   <thead>
							     <tr>
							       <th>Promotion ID</th>
							       <th>Promotion Date</th>
							       <th>Basic Salary</th>
							       <th>New Designation</th>								       
							       <th>Edit</th>
							       <th>Delete</th>    							          
							     </tr>
							   </thead>
								<tbody>
							<c:forEach items="${Employee_Promotion_List.employee_Promotion_id}" var="Employee_Promotion_id" varStatus="position">
							<tr class="gradeA">
								<td style="width:50px;">
									${Employee_Promotion_id}
								</td>
								<td><c:out value='${Employee_Promotion_List.promotion_Date[position.index]}'/> </td>
								<td><c:out value='${Employee_Promotion_List.basic_Salary[position.index]}'/> </td>
								<td><c:out value='${Employee_Promotion_List.promotion_Designation[position.index]}'/> </td>
								<td style="width:5%;">
					    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
										$('#Employee_id').val('${Employee_Promotion_List.employee_id}');
										if(document.getElementById('Employee_id').value!='' && document.getElementById('Employee_id').value==${Employee_Promotion_List.employee_id})
										{
											$('#Employee_Promotion_id').val('${Employee_Promotion_id}');
											$('#Promotion_Date').val('<c:out value='${Employee_Promotion_List.promotion_Date[position.index]}'/>');	
											$('#Basic_Salary').val('<c:out value='${Employee_Promotion_List.basic_Salary[position.index]}'/>');
											$('#Promotion_Designation').val('<c:out value='${Employee_Promotion_List.promotion_Designation[position.index]}'/>');																																									
										}															    		  
					    		  "><i class="icon-edit"></i></button>								
								 </td>
								<td style="width:5%;">
					    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Employee_Promotion_id').value='${Employee_Promotion_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
								</td>
							</tr>
							</c:forEach>
								</tbody>
							</table>
							</c:when>
							<c:otherwise>
							<br>
							  		<div class="message">There are no Employee Promotions applied for this employee.</div>
							</c:otherwise>
						</c:choose>
					</div>
				  </div>
				</div>		     