<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>				
	<div class="widget-box">
	  <div style="overflow-x:auto;">
		<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			<h5>Departments List</h5>
		</div>
		<div class="widget-content nopadding">		
			<c:choose>
				<c:when test="${not empty Department_List.department_id}">
				<table  id="departments" class="table table-striped table-bordered" border="1" style="width:90%">
				   <thead>
				     <tr>
				       <th>ID</th>
				       <th>Department Name</th>
				       <th>Department Description</th>
				       <th>Edit</th>    	
				       <th>Delete</th>    							          
				     </tr>
				   </thead>
					<tbody>
				<c:forEach items="${Department_List.department_id}" var="Department_id" varStatus="position">
				<tr>
					<td style="width:50px;">
						${Department_id}
					</td>
					<td><c:out value='${Department_List.department_Name[position.index]}'/> </td>
					<td><c:out value='${Department_List.department_Description[position.index]}'/></td>
					<td style="width:5%;">
 		    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
							document.getElementById('Department_Name').value='<c:out value='${Department_List.department_Name[position.index]}'/>';
							document.getElementById('Department_Description').value='<c:out value='${Department_List.department_Description[position.index]}'/>';
							document.getElementById('Department_id').value='${Department_id}';				    		  
		    		  "><i class="icon-edit"></i></button>								
					 </td>
					<td style="width:5%;">
		    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Department_id').value='${Department_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
					</td>
				</tr>
				</c:forEach>
					</tbody>
				</table>
				</c:when>
				<c:otherwise>
				<br>
				  		<div class="message">There are no Departments found under this school.</div>
				</c:otherwise>
			</c:choose>
		</div>
	  </div>
	</div>		         