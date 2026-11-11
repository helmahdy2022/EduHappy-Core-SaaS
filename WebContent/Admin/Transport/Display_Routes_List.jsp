<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>		
				<div class="widget-box">
				  <div style="overflow-x:auto;">
					<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
						      <h5>Routes List</h5>
					</div>
					<div class="widget-content nopadding">		
						<c:choose>
							<c:when test="${not empty Routes_List.route_id}">
							<table id="routes" class="table table-striped table-bordered" border="1" style="width:90%">
							   <thead>
							     <tr>
							       <th>Route ID</th>
							       <th>Route Name</th>
							       <th>Driver Name</th>
							       <th>Matron Name</th>
							       <th>Edit</th> 
							       <th>Delete</th>   							          
							     </tr>
							   </thead>
								<tbody>
							<c:forEach items="${Routes_List.route_id}" var="Route_id" varStatus="position">
							<tr>
								<td style="width:50px;">
									${Route_id}
								</td>
								<td style="width:30%;"><c:out value='${Routes_List.route_Name[position.index]}'/> </td>
								<td style="width:20%;"><c:out value='${Routes_List.driver_Name[position.index]}'/> </td>
								<td style="width:20%;"><c:out value='${Routes_List.matron_Name[position.index]}'/> </td>
								<td style="width:5%;">
					    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
										$('#School_id').val('${Routes_List.school_id}');
										if(document.getElementById('School_id').value!='' && document.getElementById('School_id').value==${Routes_List.school_id})
										{
											$('#Route_id').val('${Route_id}');
											$('#Route_Name').val('<c:out value='${Routes_List.route_Name[position.index]}'/>');	
											<c:choose>
											  <c:when test="${Routes_List.driver_id[position.index]!=0}">
												$('#Driver_id').val('<c:out value='${Routes_List.driver_id[position.index]}'/>');
											  </c:when>
											  <c:otherwise>
												$('#Driver_id').val('');
											  </c:otherwise>
											</c:choose>	
											<c:choose>
											  <c:when test="${Routes_List.matron_id[position.index]!=0}">
												$('#Matron_id').val('<c:out value='${Routes_List.matron_id[position.index]}'/>');	
											  </c:when>
											  <c:otherwise>
												$('#Matron_id').val('');
											  </c:otherwise>
											</c:choose>											
											$('#Debit_Account_id').val('<c:out value='${Routes_List.debit_Account[position.index]}'/>');
											$('#Credit_Account_id').val('<c:out value='${Routes_List.credit_Account[position.index]}'/>');																																										
											$('#Vehicle_id').val('${Routes_List.vehicle_id}');	
										}															    		  
					    		  "><i class="icon-edit"></i></button>								
								 </td>
								<td style="width:5%;">
					    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Route_id').value='${Route_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
								</td>
							</tr>
							</c:forEach>
								</tbody>
							</table>
							</c:when>
							<c:otherwise>
							<br>
							  		<div class="message">There are no Vehicles found under this school.</div>
							</c:otherwise>
						</c:choose>
					</div>
				  </div>
				</div>		     