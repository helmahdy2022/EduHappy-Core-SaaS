<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>		
				<div class="widget-box">
				  <div style="overflow-x:auto;">
					<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
						      <h5>Financial Accounts List</h5>
					</div>
					<div class="widget-content nopadding">		
						<c:choose>
							<c:when test="${not empty Financial_Accounts_List.financial_Account_id}">
							<table id="financial_Accounts" class="table table-striped table-bordered" style="width:90%">
							   <thead>
							     <tr>
							       <th>Account ID</th>
							       <th>Account Name</th>
							       <th>Description</th>
							       <th >Edit</th> 
							       <th>Delete</th>   							          
							     </tr>
							   </thead>
								<tbody>
							<c:forEach items="${Financial_Accounts_List.financial_Account_id}" var="Financial_Account_id" varStatus="position">
							<tr>
								<td style="width:50px;">
									${Financial_Account_id}
								</td>
								<td style="width:30%;"><c:out value='${Financial_Accounts_List.financial_Account_Name[position.index]}'/> </td>
								<td style="width:40%;"><c:out value='${Financial_Accounts_List.financial_Account_Description[position.index]}'/> </td>
								<td style="width:5%;">
					    		  <button name="Modify" id="Modify" value="Edit" class="btn btn-info" onclick="
										$('#School_id').val('${Financial_Accounts_List.school_id}');
										showFinancialCategories(${Financial_Accounts_List.school_id}, ${Financial_Accounts_List.financial_Category_id});
										if(document.getElementById('School_id').value!='' && document.getElementById('School_id').value==${Financial_Accounts_List.school_id})
										{
											$('#Financial_Account_id').val('${Financial_Account_id}');
											$('#Financial_Account_Name').val('<c:out value='${Financial_Accounts_List.financial_Account_Name[position.index]}'/>');	
											$('#Financial_Account_Description').val('<c:out value='${Financial_Accounts_List.financial_Account_Description[position.index]}'/>');																					
											$('#Financial_Category_id').val('${Financial_Accounts_List.financial_Category_id}');	
										}															    		  
					    		  "><i class="icon-edit"></i></button>								
								 </td>
								<td style="width:5%;">
					    		  <button name="Delete" id="Delete" value="Delete" onClick="showDeleteMessage();document.getElementById('Financial_Account_id').value='${Financial_Account_id}';" class="btn btn-danger"><i class="icon-remove"></i></button> 								
								</td>
							</tr>
							</c:forEach>
								</tbody>
							</table>
							</c:when>
							<c:otherwise>
							<br>
							  		<div class="message">There are no Financial Accounts found under this school.</div>
							</c:otherwise>
						</c:choose>
					</div>
				  </div>
				</div>		     