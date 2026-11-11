<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br/> 
<c:forEach items="${TeacherAuthority.authorityList}" var="AuthorityList">	
	<c:if test="${AuthorityList[2] == Grade_id && AuthorityList[4]==Class_id && AuthorityList[6]==Course_id && (AuthorityList[8]== Group_id ||AuthorityList[8]==null)}">
		<div class="alert alert-dark light alert-dismissable">
		  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
		  <i class="fa fa-arrow-circle-o-right"></i>
		  <strong><c:out value="${AuthorityList[1]} > ${AuthorityList[3]}  > ${AuthorityList[5]} > ${AuthorityList[7]} ${AuthorityList[9]}"/></strong>
		</div>
	</c:if>
</c:forEach>

<c:if test="${param.Current=='Current' }">
<div style="border:0px solid black;float:center;text-align:center;"><font size="4" ><b>According to your schedule, you have the following Current Session in Progress Now:</b></font></div>
</c:if>

<c:choose>
<c:when test="${empty AttendanceStudents.student_id}">
<div class="message"><c:out value="There are no students available to take attendance for"/></div>	
</c:when>
<c:otherwise>
<!-- Input Fields -->
<div class="panel">
	<div class="panel-heading">
		<span class="fa fa-calendar"></span>
		Session Date: ${param.Session_Date}
		<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>

		<span class="fa fa-clock-o"></span>
		Session Time: ${param.Time_From} - ${param.Time_To} 
		
    </div>
    <div class="panel-body"> 				 
	          <div class="panel-body pn">
	            <div class="table-responsive">
	              <div class="bs-component">
	                  <table class="table table-bordered mbn">		
						<thead>
						<tr>
						<th>ID</th>
						<th>Photo</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th style="text-align:center;">
						<label><input type="checkbox" id="AllPresent" name="AllPresent"  onchange="
						if (this.checked)
						{
					
									manageAttendance('All', 'Present');
									 <c:forEach items="${AttendanceStudents.student_id}" var="Student_id" varStatus="position">
				
											document.getElementById('Present${Student_id}').disabled=true;
											document.getElementById('Present${Student_id}').className='attendanceButtonSelected';					
											document.getElementById('Absent${Student_id}').disabled=false;
											document.getElementById('Absent${Student_id}').className='attendanceButton';
											document.getElementById('Excused${Student_id}').disabled=false;
											document.getElementById('Excused${Student_id}').className='attendanceButton';
											document.getElementById('Late${Student_id}').disabled=false;
											document.getElementById('Late${Student_id}').className='attendanceButton';
											document.getElementById('AllAbsent').checked=false;
											document.getElementById('AllExcused').checked=false;	
											document.getElementById('AllLate').checked=false;
										
									</c:forEach>
						}
						else
						{
									manageAttendance('All', 'Present', 'Delete');
									<c:forEach items="${AttendanceStudents.student_id}" var="Student_id" varStatus="position">
											document.getElementById('Present${Student_id}').disabled=false;
											document.getElementById('Present${Student_id}').className='attendanceButton';
											document.getElementById('Absent${Student_id}').disabled=false;
											document.getElementById('Absent${Student_id}').className='attendanceButton';
											document.getElementById('Excused${Student_id}').disabled=false;
											document.getElementById('Excused${Student_id}').className='attendanceButton';
											document.getElementById('Late${Student_id}').disabled=false;
											document.getElementById('Late${Student_id}').className='attendanceButton';	
									</c:forEach>
						}
							"/></label>					
					</th>
					<th style="text-align:center;">
						<label><input type="checkbox" id="AllAbsent" name="AllAbsent"  onchange="
						if (this.checked)
						{
					
									manageAttendance('All', 'Absent');
									 <c:forEach items="${AttendanceStudents.student_id}" var="Student_id" varStatus="position">
											document.getElementById('Present${Student_id}').disabled=false;
											document.getElementById('Present${Student_id}').className='attendanceButton';
											document.getElementById('Absent${Student_id}').disabled=true;
											document.getElementById('Absent${Student_id}').className='attendanceButtonSelected';
											document.getElementById('Excused${Student_id}').disabled=false;
											document.getElementById('Excused${Student_id}').className='attendanceButton';
											document.getElementById('Late${Student_id}').disabled=false;
											document.getElementById('Late${Student_id}').className='attendanceButton';	
											document.getElementById('AllPresent').checked=false;
											document.getElementById('AllExcused').checked=false;	
											document.getElementById('AllLate').checked=false;
									</c:forEach>
						}
						else
						{
									manageAttendance('All', 'Absent', 'Delete');
									<c:forEach items="${AttendanceStudents.student_id}" var="Student_id" varStatus="position">
											document.getElementById('Present${Student_id}').disabled=false;
											document.getElementById('Present${Student_id}').className='attendanceButton';
											document.getElementById('Absent${Student_id}').disabled=false;
											document.getElementById('Absent${Student_id}').className='attendanceButton';
											document.getElementById('Excused${Student_id}').disabled=false;
											document.getElementById('Excused${Student_id}').className='attendanceButton';
											document.getElementById('Late${Student_id}').disabled=false;
											document.getElementById('Late${Student_id}').className='attendanceButton';
									</c:forEach>
						}
							"/></label>					
					</th>
					<th style="text-align:center;">
						<label><input type="checkbox" id="AllExcused" name="AllExcused"  onchange="
						if (this.checked)
						{
					
									manageAttendance('All', 'Excused');
									 <c:forEach items="${AttendanceStudents.student_id}" var="Student_id" varStatus="position">
											document.getElementById('Present${Student_id}').disabled=false;
											document.getElementById('Present${Student_id}').className='attendanceButton';
											document.getElementById('Absent${Student_id}').disabled=false;
											document.getElementById('Absent${Student_id}').className='attendanceButton';
											document.getElementById('Excused${Student_id}').disabled=true;
											document.getElementById('Excused${Student_id}').className='attendanceButtonSelected';
											document.getElementById('Late${Student_id}').disabled=false;
											document.getElementById('Late${Student_id}').className='attendanceButton';
											document.getElementById('AllPresent').checked=false;
											document.getElementById('AllAbsent').checked=false;	
											document.getElementById('AllLate').checked=false;	
									</c:forEach>
						}
						else
						{
									manageAttendance('All', 'Excused', 'Delete');
									<c:forEach items="${AttendanceStudents.student_id}" var="Student_id" varStatus="position">
											document.getElementById('Present${Student_id}').disabled=false;
											document.getElementById('Present${Student_id}').className='attendanceButton';
											document.getElementById('Absent${Student_id}').disabled=false;
											document.getElementById('Absent${Student_id}').className='attendanceButton';
											document.getElementById('Excused${Student_id}').disabled=false;
											document.getElementById('Excused${Student_id}').className='attendanceButton';
											document.getElementById('Late${Student_id}').disabled=false;
											document.getElementById('Late${Student_id}').className='attendanceButton';
									</c:forEach>
						}
							"/></label>					
					</th>
					<th style="text-align:center;">
						<label><input type="checkbox" id="AllLate" name="AllLate"  onchange="
						if (this.checked)
						{
					
									manageAttendance('All', 'Late');
									 <c:forEach items="${AttendanceStudents.student_id}" var="Student_id" varStatus="position">
											document.getElementById('Present${Student_id}').disabled=false;
											document.getElementById('Present${Student_id}').className='attendanceButton';
											document.getElementById('Absent${Student_id}').disabled=false;
											document.getElementById('Absent${Student_id}').className='attendanceButton';
											document.getElementById('Excused${Student_id}').disabled=false;
											document.getElementById('Excused${Student_id}').className='attendanceButton';
											document.getElementById('Late${Student_id}').disabled=true;
											document.getElementById('Late${Student_id}').className='attendanceButtonSelected';
											document.getElementById('AllPresent').checked=false;
											document.getElementById('AllAbsent').checked=false;	
											document.getElementById('AllExcused').checked=false;	
									</c:forEach>
						}
						else
						{
									manageAttendance('All', 'Late', 'Delete');
									<c:forEach items="${AttendanceStudents.student_id}" var="Student_id" varStatus="position">
											document.getElementById('Present${Student_id}').disabled=false;
											document.getElementById('Present${Student_id}').className='attendanceButton';
											document.getElementById('Absent${Student_id}').disabled=false;
											document.getElementById('Absent${Student_id}').className='attendanceButton';
											document.getElementById('Excused${Student_id}').disabled=false;
											document.getElementById('Excused${Student_id}').className='attendanceButton';
											document.getElementById('Late${Student_id}').disabled=false;
											document.getElementById('Late${Student_id}').className='attendanceButton';	
									</c:forEach>
						}
							"/></label>					
					</th>
					<th colspan="2" style="text-align:center;">Reset</th>								
				</tr>
				</thead>					   		
				<c:forEach items="${AttendanceStudents.student_id}" var="Student_id" varStatus="position">
					<tr> 			
				   		<td>
				   			<c:out value="${Student_id}"/>
				   		</td>
					  	<td style="width:50px;">
							<c:choose>
							<c:when test="${not empty AttendanceStudents.fileName[position.index] && AttendanceStudents.fileName[position.index]!=null}">
								<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${AttendanceStudents.account_id[position.index]}/Schools/${AttendanceStudents.school_id[position.index]}/Students/Photos/${Student_id}/${AttendanceStudents.fileName[position.index]}">
							</c:when>
							<c:otherwise>
								<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
							</c:otherwise>
							</c:choose>						  						 
					  	</td>			   		
				   		<td>
				   			<c:out value="${AttendanceStudents.first_Name[position.index]}"/>
				   		</td>
				   		<td>
				   			<c:out value="${AttendanceStudents.last_Name[position.index]}"/>
				   		</td>
				   		<td style="text-align:center;">
							<input type="button" id="Present${Student_id}" value="Present" 
							onclick="manageAttendance(${Student_id}, this.value); 
							document.getElementById('Present${Student_id}').disabled=true;
							document.getElementById('Present${Student_id}').className='attendanceButtonSelected';
							document.getElementById('Absent${Student_id}').disabled=false;
							document.getElementById('Absent${Student_id}').className='attendanceButton';
							document.getElementById('Excused${Student_id}').disabled=false;
							document.getElementById('Excused${Student_id}').className='attendanceButton';
							document.getElementById('Late${Student_id}').disabled=false;
							document.getElementById('Late${Student_id}').className='attendanceButton';
							document.getElementById('AllPresent').checked=false;
							document.getElementById('AllAbsent').checked=false;	
							document.getElementById('AllExcused').checked=false;
							document.getElementById('AllLate').checked=false;"		
							<c:choose>
							      <c:when test="${AttendanceStudents.status[position.index]=='Present'}">
							      			disabled class="attendanceButtonSelected"
							      </c:when>
							      <c:otherwise>
									class="attendanceButton"
							      </c:otherwise>
							</c:choose>
							 >
				   		</td>
				   		<td style="text-align:center;">
							<input type="button" id= "Absent${Student_id}" value="Absent"  
							onclick="manageAttendance(${Student_id}, this.value); 
							document.getElementById('Absent${Student_id}').disabled=true;
							document.getElementById('Absent${Student_id}').className='attendanceButtonSelected';
							document.getElementById('Present${Student_id}').disabled=false;
							document.getElementById('Present${Student_id}').className='attendanceButton';		
							document.getElementById('Excused${Student_id}').disabled=false;
							document.getElementById('Excused${Student_id}').className='attendanceButton';		
							document.getElementById('Late${Student_id}').disabled=false;
							document.getElementById('Late${Student_id}').className='attendanceButton';
							document.getElementById('AllPresent').checked=false;
							document.getElementById('AllAbsent').checked=false;	
							document.getElementById('AllExcused').checked=false;
							document.getElementById('AllLate').checked=false;"
							<c:choose>
							      <c:when test="${AttendanceStudents.status[position.index]=='Absent'}">
							      			disabled class="attendanceButtonSelected"
							      </c:when>
							      <c:otherwise>
									class="attendanceButton"   
							      </c:otherwise>
							</c:choose>
							>
			   			</td>
			   			<td style="text-align:center;">
							<input type="button" id="Excused${Student_id}" value="Excused" 
							onclick="manageAttendance(${Student_id}, this.value);
							document.getElementById('Excused${Student_id}').disabled=true;
							document.getElementById('Excused${Student_id}').className='attendanceButtonSelected';
							document.getElementById('Present${Student_id}').disabled=false;
							document.getElementById('Present${Student_id}').className='attendanceButton';
							document.getElementById('Absent${Student_id}').disabled=false;
							document.getElementById('Absent${Student_id}').className='attendanceButton';		
							document.getElementById('Late${Student_id}').disabled=false;
							document.getElementById('Late${Student_id}').className='attendanceButton';
							document.getElementById('AllPresent').checked=false;
							document.getElementById('AllAbsent').checked=false;	
							document.getElementById('AllExcused').checked=false;
							document.getElementById('AllLate').checked=false;"		
							<c:choose>
							      <c:when test="${AttendanceStudents.status[position.index]=='Excused'}">
							      			disabled class="attendanceButtonSelected"
							      </c:when>
							      <c:otherwise>
									class="attendanceButton"	      
							      </c:otherwise>
							</c:choose>
							>		   			
			   			</td>
			   			<td style="text-align:center;">
							<input type="button" id="Late${Student_id}" value="Late" 
							onclick="manageAttendance(${Student_id}, this.value); 
							document.getElementById('Late${Student_id}').disabled=true;
							document.getElementById('Late${Student_id}').className='attendanceButtonSelected';
							document.getElementById('Present${Student_id}').disabled=false;
							document.getElementById('Present${Student_id}').className='attendanceButton';		
							document.getElementById('Absent${Student_id}').disabled=false;
							document.getElementById('Absent${Student_id}').className='attendanceButton';	
							document.getElementById('Excused${Student_id}').disabled=false;
							document.getElementById('Excused${Student_id}').className='attendanceButton';
							document.getElementById('AllPresent').checked=false;
							document.getElementById('AllAbsent').checked=false;	
							document.getElementById('AllExcused').checked=false;
							document.getElementById('AllLate').checked=false;"
							<c:choose>
							      <c:when test="${AttendanceStudents.status[position.index]=='Late'}">
							      			disabled class="attendanceButtonSelected"
							      </c:when>
							      <c:otherwise>
									class="attendanceButton"	      
							      </c:otherwise>
							</c:choose>		
							>
			   			</td>
			   
				   		<td style="text-align:center;">
							<input type="button" value="reset" class="btn btn-xs btn-info btn-block" onclick="
							manageAttendance(${Student_id}, this.value);
							document.getElementById('AllPresent').checked=false;
							document.getElementById('AllAbsent').checked=false;	
							document.getElementById('AllExcused').checked=false;
							document.getElementById('AllLate').checked=false;
							document.getElementById('Present${Student_id}').disabled=false;
							document.getElementById('Present${Student_id}').className='attendanceButton';	
							document.getElementById('Absent${Student_id}').disabled=false;
							document.getElementById('Absent${Student_id}').className='attendanceButton';	
							document.getElementById('Excused${Student_id}').disabled=false;
							document.getElementById('Excused${Student_id}').className='attendanceButton';
							document.getElementById('Late${Student_id}').disabled=false;
							document.getElementById('Late${Student_id}').className='attendanceButton';
							">
				   		</td>			
					</tr>
			</c:forEach>
			</table>
		   </div>
	      </div>
	     </div>				
	</div>
</div>

</c:otherwise>
</c:choose>

<div id="Students"></div>
 	
