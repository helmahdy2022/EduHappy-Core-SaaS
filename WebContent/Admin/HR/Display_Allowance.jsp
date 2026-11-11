<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>				
	<div class="widget-box">
	  <div style="overflow-x:auto;">
		<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			<h5>Allowance List</h5>
		</div>
		<div class="widget-content nopadding">		
			<c:choose>
				<c:when test="${not empty Allowance_List.allowance_id}">
				<table  id="allowance" class="table table-striped table-bordered" border="1" style="width:90%">
				   <thead>
				     <tr>
				       <th>ID</th>
				       <th>Allowance Name</th>
				       <th>Allowance Description</th>
				       <th>Edit</th>  
				       <th>Delete</th>    							          
				     </tr>
				   </thead>
					<tbody>
				<c:forEach items="${Allowance_List.allowance_id}" var="Allowance_id" varStatus="position">
				<tr>
					<td style="width:50px;">
						${Allowance_id}
					</td>
					<td><c:out value='${Allowance_List.allowance_Name[position.index]}'/> </td>
					<td><c:out value='${Allowance_List.allowance_Description[position.index]}'/></td>
					<td style="width:5%;">
 		    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
							document.getElementById('Allowance_Name').value='<c:out value='${Allowance_List.allowance_Name[position.index]}'/>';
							document.getElementById('Allowance_Description').value='<c:out value='${Allowance_List.allowance_Description[position.index]}'/>';
							document.getElementById('${Allowance_List.allowance_Type[position.index]}').checked=true;
							document.getElementById('${Allowance_List.period[position.index]}').checked=true;
							document.getElementById('Payroll_id').value='<c:out value='${Allowance_List.payroll_id}'/>';
							document.getElementById('Allowance_id').value='${Allowance_id}';
							if(${Allowance_List.allowance_Type[position.index]=='Fixed'})
							{
								document.getElementById('Fixed_Amount').value='<c:out value='${Allowance_List.amount[position.index]}'/>';
								document.getElementById('Fixed_Amount').disabled=false;
								document.getElementById('Percentage_Amount').disabled=true;
								document.getElementById('Percentage_Amount').value='';
							}
							else if(${Allowance_List.allowance_Type[position.index]=='Percentage'})
							{
								document.getElementById('Percentage_Amount').value='<c:out value='${Allowance_List.amount[position.index]}'/>';
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
		    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Allowance_id').value='${Allowance_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
					</td>
				</tr>
				</c:forEach>
					</tbody>
				</table>
				</c:when>
				<c:otherwise>
				<br>
				  		<div class="message">There are no Allowance types found under this payroll.</div>
				</c:otherwise>
			</c:choose>
		</div>
	  </div>
	</div>		         