<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
a#OnlineMeeting:link, a#OnlineMeeting:visited {
  background-color: #f44336;
  color: white;
  padding: 5px 5px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
}

a#OnlineMeeting:hover, a#OnlineMeeting:active {
  background-color: red;
}
</style>
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Schedule Details</h5>
</div>
 
 <%int n=0;%>
		<input name="Class_id" id="Class_id" type="hidden" class="text" value="${param.Class_id}"></input>
	<c:choose>
	<c:when test="${empty Class_Schedule.sessions}">
	<div class="message">No Sessions have been created for this schedule. Please build sessions and then try again.</div>
	</c:when>
	<c:otherwise>
	<div style="overflow-x:auto;white-space: nowrap;">
		<table class="table table-bordered data-table" style="display: inline-block; overflow: auto; white-space: nowrap;">
 			<tr>
 			
 				<td></td>
 			  	<c:forEach items="${Class_Schedule.sessions}" var="Session" varStatus="position">
 			  		<c:if test = "${Session[0]!=null}">		
		 				<td><label><c:out value='${Session[1]}'/> - <c:out value='${Session[2]}'/> </label></td>
		 			</c:if>
		      </c:forEach>	
	 		</tr>
			
				<c:forEach items="${Class_Schedule.day_id}" var="Day_id" varStatus="position">
		 		<tr>
			 		<td><label><c:out value='${Class_Schedule.day_Name[position.index]}'/></label></td>
 
 					<c:forEach items="${Class_Schedule.sessions}" var="Session" varStatus="position">
 						<c:if test = "${Session[0]!=null}">		
						 <%			 						
						 n=n+1;
						 request.setAttribute("n", n);
						%>			
							<td>
							<input name="Session_id${n}" id="Session_id<%out.print(n);%>" type="hidden" value="${Session[0]}"></input>
							<input name="cell_count${n}" id="cell_count${n}" type="hidden" value="${n}"></input>
							<input name="Session_Count" id="Session_Count" type="hidden" value="${Class_Schedule.session_Count}"></input>
							<input name="Days_Count" id="Days_Count" type="hidden" value="${Class_Schedule.days_Count}"></input>
							<input name="Day_id${n}" id="Day_id" type="hidden" value="${Day_id}"></input>
							<b>${n}<c:if test="${n <10}">&nbsp;&nbsp;</c:if></b>
			       					<c:set var="Existing_Course" value=""></c:set>
									<c:set var="Existing_Group" value=""></c:set>
									<c:set var="Teachr_Name" value=""></c:set>	
									<c:set var="If_Conflict" value=""></c:set>
			       			<c:forEach items="${Class_Schedule.courseExist}" var="Course" varStatus="loop">
			       				<c:if test = "${Course[0]==Day_id && Course[1]==Session[0]}">
									<c:set var="Existing_Course" value="${Course[2]}"></c:set>
									<c:set var="Existing_Group" value="${Course[3]}"></c:set>
									<c:set var="Teachr_Name" value="${Course[4]}"></c:set>	
									<c:set var="If_Conflict" value="${Course[5]}"></c:set>
					       			<c:if test="${Course[7]=='true'}">
										<c:set var="Online_Meeting_URL" value="${Course[6]}"></c:set>
						       		</c:if>								
			       				</c:if>	
			       			</c:forEach>							
							<select class="smallList" name="Course${n}" id="Course"  onchange="showTeacher(this.value,'${n}', '${param.Class_id}')">
								<option>Select Course </option>
							  	<c:forEach items="${Class_Schedule.course_id}" var="Course_id" varStatus="position">
						       		<option value="${Course_id},<c:choose><c:when test="${Class_Schedule.group_id[position.index]!=null}">${Class_Schedule.group_id[position.index]}</c:when><c:otherwise>0</c:otherwise></c:choose>"
						       				<c:if test = "${Existing_Course==Course_id && Existing_Group==Class_Schedule.group_id[position.index]}">
						       				selected
						       				</c:if>							       		
						       		><c:out value='${Class_Schedule.course_Name[position.index]}'/> <c:out value='${Class_Schedule.group_Name[position.index]}'/> 
						       		<c:if test = "${Class_Schedule.elective[position.index]==1}">
						       		(Elective)	
						       		</c:if>
						       		</option>
						     	</c:forEach>
						     		<option value="Break"
						       			<c:forEach items="${Class_Schedule.courseExist}" var="Course">
						       				<c:if test = "${Course[0]==Day_id && Course[1]==Session[0] && Course[2]==null}">
						       				selected
						       				<c:set var="Online_Meeting_URL" value="${null}"></c:set>
						       				</c:if>	
						       			</c:forEach>
						       		>
						       		Break</option>	
							</select>
							
							<div id="Teacher_Name${n}">
							<c:forEach items="${Class_Schedule.course_id}" var="Course_id" varStatus="position">				
						       				<c:if test = "${Existing_Course==Course_id && Existing_Group==Class_Schedule.group_id[position.index]}">
						       				<c:out value='${Teachr_Name}'/>
						       					<c:if test = "${Teachr_Name==null}">
						       						no teacher assigned<br/>
						       					</c:if>
						       					${If_Conflict}						       				
						       				</c:if>	
						    </c:forEach>
						    </div>		
							
							<div>				
						       	<c:if test = "${Online_Meeting_URL!=null}">
						       		<c:out value='Online Meeting - '/>					       		
						       		<a href="${Online_Meeting_URL}" target=_blank id="OnlineMeeting">Click Here</a>	
						       		<c:set var="Online_Meeting_URL" value="${null}"></c:set>					       				
						       	</c:if>	
						    </div>							    								
					</td>
						</c:if>
					</c:forEach>
				 </tr>
		      </c:forEach>	
			<tr>
       	 	<td colspan="20"><div class="submitHolder"><input type="submit" name="Submit" id="Submit" value="Submit" class="btn btn-success" onclick="checkSession();"></div></td>
  			</tr> 
 			</table>
 			</div>
			</c:otherwise>
		</c:choose>
 