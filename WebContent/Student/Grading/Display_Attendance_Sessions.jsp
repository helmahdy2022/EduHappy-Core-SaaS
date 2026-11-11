<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${not empty Attendance_Sessions.attendanceList}">
   <div class="col-md-12">
     <div class="panel panel-visible" id="spy2">
       <div class="panel-heading">
         <div class="panel-title hidden-xs">
           <span class="glyphicon glyphicon-tasks"></span>${Attendance_Sessions.course_Name} - Attendance</div>
         </div>
         <div class="panel-body pn">
            <table class="table table-striped table-hover" id="datatable2" cellspacing="0" width="100%">
              <thead>
                <tr>
                  <th style="text-align:center;">id</th>
                  <th style="text-align:center;">Date</th>
                  <th style="text-align:center;">Time From</th>
                  <th style="text-align:center;">Time To</th>
                  <th style="text-align:center;">Status</th> 
                  <th style="text-align:center;">Points</th> 
                  <th style="text-align:center;">Out of</th> 
                  <th style="text-align:center;">%</th>                                                                       
                </tr>
              </thead>
              <tbody>
				<c:forEach items="${Attendance_Sessions.attendanceList}" var="Attendance_Session" >
				  <c:if test="${Attendance_Session[0]!=null}">
					<tr>			
					   <td style="text-align:center;">  
						<c:out value="${Attendance_Session[0]}"/>
					   </td>
					   <td style="text-align:center;">  
						<c:out value="${Attendance_Session[1]}"/>
					   </td>
					   <td style="text-align:center;">  
						<c:out value="${Attendance_Session[2]}"/>
					   </td>
					   <td style="text-align:center;">  
						<c:out value="${Attendance_Session[3]}"/>
					   </td>
					   <td style="text-align:center;">  
						<c:out value="${Attendance_Session[4]}"/>
					   </td>
					   <td style="text-align:center;">  
						<c:out value="${Attendance_Session[5]}"/>
					   </td>
					   <td style="text-align:center;">  
						<c:out value="${Attendance_Session[6]}"/>
					   </td>
					   <td style="text-align:center;">  
						<c:out value="${Attendance_Session[7]}"/>%
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
  		<div class="message">There are no graded sessions under this course</div>
</c:otherwise>
</c:choose>