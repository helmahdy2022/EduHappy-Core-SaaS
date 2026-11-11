<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>				
	<div class="widget-box">
	  <div style="overflow-x:auto;">
		<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			<h5>Deduction List</h5>
		</div>
		<div class="widget-content nopadding">		
			<c:choose>
				<c:when test="${not empty Deduction_List.deduction_id}">
				<table id="deduction" class="table table-striped table-bordered" border="1" style="width:90%">
				   <thead>
				     <tr>
				       <th>ID</th>
				       <th>Deduction Name</th>
				       <th>Deduction Description</th>
				       <th>Edit</th>
					   <th>Delete</th>   							          
				     </tr>
				   </thead>
					<tbody>
				<c:forEach items="${Deduction_List.deduction_id}" var="Deduction_id" varStatus="position">
				<tr>
					<td style="width:50px;">
						${Deduction_id}
					</td>
					<td><c:out value='${Deduction_List.deduction_Name[position.index]}'/> </td>
					<td><c:out value='${Deduction_List.deduction_Description[position.index]}'/></td>
					<td style="width:5%;">
 		    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
							document.getElementById('Deduction_Name').value='<c:out value='${Deduction_List.deduction_Name[position.index]}'/>';
							document.getElementById('Deduction_Description').value='<c:out value='${Deduction_List.deduction_Description[position.index]}'/>';
							document.getElementById('${Deduction_List.deduction_Type[position.index]}').checked=true;
							document.getElementById('${Deduction_List.period[position.index]}').checked=true;
							document.getElementById('Payroll_id').value='<c:out value='${Deduction_List.payroll_id}'/>';
							document.getElementById('Deduction_id').value='${Deduction_id}';
							if(${Deduction_List.deduction_Type[position.index]=='Fixed'})
							{
								document.getElementById('Fixed_Amount').value='<c:out value='${Deduction_List.amount[position.index]}'/>';
								document.getElementById('Fixed_Amount').disabled=false;
								document.getElementById('Percentage_Amount').disabled=true;
								document.getElementById('Percentage_Amount').value='';
							}
							else if(${Deduction_List.deduction_Type[position.index]=='Percentage'})
							{
								document.getElementById('Percentage_Amount').value='<c:out value='${Deduction_List.amount[position.index]}'/>';
								document.getElementById('Fixed_Amount').disabled=true;
								document.getElementById('Percentage_Amount').disabled=false;
								document.getElementById('Fixed_Amount').value='';
							}
							else
							{
								document.getElementById('Fixed_Amount').disabled=true;
								document.getElementById('Percentage_Amount').disabled=true;
								document.getElementById('Fixed_Amount').value='';
								document.getElementById('Percentage_Amount').value='';
							}
							"><i class="icon-edit"></i></button>								
					 </td>
					<td style="width:5%;">
		    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Deduction_id').value='${Deduction_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
					</td>
				</tr>
				</c:forEach>
					</tbody>
				</table>
				</c:when>
				<c:otherwise>
				<br>
				  		<div class="message">There are no Deduction types found under this payroll.</div>
				</c:otherwise>
			</c:choose>
		</div>
	  </div>
	</div>		         