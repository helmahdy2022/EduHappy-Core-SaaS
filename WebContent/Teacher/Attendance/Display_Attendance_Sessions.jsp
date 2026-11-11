<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${not empty AttendanceSessions.session_id }">
<div class="panel-body pn">
 <div class="table-responsive">
  <div class="bs-component">
   <table class="table table-bordered mbn">
		<thead>
		<tr>
			<th>Session ID</th>
			<th>Time From</th>
			<th>Time To</th>
			<th>Action</th>			
		</tr>
		</thead>
		<c:forEach items="${AttendanceSessions.session_id}" var="ListSession_id" varStatus="position">
			<c:forEach items="${ListSession_id}" var="Session_id">
			<tr>
				<td>  
					${Session_id}
				</td>
				
				<td>
					<c:out value="${AttendanceSessions.time_From[position.index]}"/>
				</td>
				<td>
					<c:out value="${AttendanceSessions.time_To[position.index]}"/>
				</td>
			
				<td>  
					<a href="javascript:showStudents(${Session_id},'','${AttendanceSessions.time_From[position.index]}','${AttendanceSessions.time_To[position.index]}');">Take Attendance</a>
				</td>
			</tr>		
			</c:forEach>
		</c:forEach>
   </table>
  </div>
 </div>
</div>
 <div id="Students"></div>
</c:when>

<c:otherwise>
<div class="message"><c:out value="No Sessions available in the selected date"/></div>	
</c:otherwise>
</c:choose>



