<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>		
				<div class="widget-box">
				  <div style="overflow-x:auto;">
					<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
						      <h5>Vehicles List</h5>
					</div>
					<div class="widget-content nopadding">		
						<c:choose>
							<c:when test="${not empty Vehicles_List.vehicle_id}">
							<table id="vehicles" class="table table-striped table-bordered" border="1" style="width:90%">
							   <thead>
							     <tr>
							       <th>Vehicle ID</th>
							       <th>Vehicle Name</th>
							       <th>Registration Number</th>
							       <th>Model Name</th> 
							       <th>Model Year</th>
							       <th>Vehicle Color</th>
							       <th>Edit</th>   
							       <th>Delete</th>    							          
							     </tr>
							   </thead>
								<tbody>
							<c:forEach items="${Vehicles_List.vehicle_id}" var="Vehicle_id" varStatus="position">
							<tr class="gradeA">
								<td style="width:50px;">
									${Vehicle_id}
								</td>
								<td><c:out value='${Vehicles_List.vehicle_Name[position.index]}'/> </td>
								<td><c:out value='${Vehicles_List.registration_Number[position.index]}'/></td>
								<td><c:out value='${Vehicles_List.model_Name[position.index]}'/> </td>
								<td><c:if test="${Vehicles_List.model_Year[position.index]!=0}"><c:out value='${Vehicles_List.model_Year[position.index]}'/></c:if></td>
								<td><c:out value='${Vehicles_List.vehicle_Color[position.index]}'/> </td>
								<td style="width:5%;">
					    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
										$('#Vehicle_Name').val('<c:out value='${Vehicles_List.vehicle_Name[position.index]}'/>');
										$('#Registration_Number').val('<c:out value='${Vehicles_List.registration_Number[position.index]}'/>');
										$('#Registration_Date').val('<c:out value='${Vehicles_List.registration_Date[position.index]}'/>');
										$('#Registration_Expiry').val('<c:out value='${Vehicles_List.registration_Expiry[position.index]}'/>');
										$('#Registration_Owner').val('<c:out value='${Vehicles_List.registration_Owner[position.index]}'/>');
										$('#Chassis_Number').val('<c:out value='${Vehicles_List.chassis_Number[position.index]}'/>');
										$('#Engine_Number').val('<c:out value='${Vehicles_List.engine_Number[position.index]}'/>');										
										$('#Model_Name').val('<c:out value='${Vehicles_List.model_Name[position.index]}'/>');
										$('#Model_Year').val('<c:if test="${Vehicles_List.model_Year[position.index]!=0}"><c:out value='${Vehicles_List.model_Year[position.index]}'/></c:if>');																	
										$('#Cubic_Capacity').val('<c:if test="${Vehicles_List.cubic_Capacity[position.index]!=0}"><c:out value='${Vehicles_List.cubic_Capacity[position.index]}'/></c:if>');
										$('#Vehicle_Color').val('<c:out value='${Vehicles_List.vehicle_Color[position.index]}'/>');											
										$('#Insurance_Number').val('<c:out value='${Vehicles_List.insurance_Number[position.index]}'/>');	
										$('#Insurance_Date').val('<c:out value='${Vehicles_List.insurance_Date[position.index]}'/>');
										$('#Insurance_Expiry').val('<c:out value='${Vehicles_List.insurance_Expiry[position.index]}'/>');
										$('#Vehicle_id').val('${Vehicle_id}');
										$('#School').val('${Vehicles_List.school_id}');													    		  
					    		  "><i class="icon-edit"></i></button>								
								 </td>
								<td style="width:5%;">
					    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Vehicle_id').value='${Vehicle_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
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