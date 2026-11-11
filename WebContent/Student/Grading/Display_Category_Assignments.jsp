<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${not empty Category_Assignments.categoryAssignmentList}">
   <div class="col-md-12">
     <div class="panel panel-visible" id="spy2">
       <div class="panel-heading">
         <div class="panel-title hidden-xs">
           <span class="glyphicon glyphicon-tasks"></span>${Category_Assignments.course_Name} - ${Category_Assignments.category_Name}</div>
         </div>
         <div class="panel-body pn">
            <table class="table table-striped table-hover" id="datatable2" cellspacing="0" width="100%">
              <thead>
                <tr>
                  <th style="text-align:center;">Assignment Subject</th>
                  <th style="text-align:center;">Student Points</th>
                  <th style="text-align:center;">Out of (Max Possible Points)</th>
                  <th style="text-align:center;">%</th>
                  <th style="text-align:center;">Remarks</th>
                </tr>
              </thead>
              <tbody>
				<c:forEach items="${Category_Assignments.categoryAssignmentList}" var="CategoryAssignmentList" >
				  <c:if test="${CategoryAssignmentList[0]!=null}">	
					<tr>			
					   <td style="text-align:center;">  
						<c:out value="${CategoryAssignmentList[0]}"/>
					   </td>
					   <td style="text-align:center;">  
						<c:out value="${CategoryAssignmentList[1]}"/>
					   </td>
					   <td style="text-align:center;">  
						<c:out value="${CategoryAssignmentList[2]}"/>
					   </td>
					   <td style="text-align:center;">  
						<c:out value="${CategoryAssignmentList[3]}"/>%
					   </td>
					   <td style="text-align:center;">  
						<c:out value="${CategoryAssignmentList[4]}"/>
					   </td>
				    </tr>
				  </c:if>
				</c:forEach>
              </tbody>
            </table>
          </div>
     </div>
   </div>
</c:when>
<c:otherwise>
  		<div class="message">There are no graded assignments under this category</div>
</c:otherwise>
</c:choose>
