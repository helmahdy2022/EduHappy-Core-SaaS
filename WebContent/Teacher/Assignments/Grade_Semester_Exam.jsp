<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${TeacherAuthority.authorityList}" var="AuthorityList">	
<c:if test="${AuthorityList[2] == param.Grade_id && AuthorityList[4]==param.Class_id && AuthorityList[6]==param.Course_id}">
	<div class="alert alert-dark light alert-dismissable">
	  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
	  <i class="fa fa-arrow-circle-o-right"></i>
	  <strong><c:out value="${AuthorityList[1]} > ${AuthorityList[3]}  > ${AuthorityList[5]} > ${AuthorityList[7]} ${AuthorityList[9]}"/></strong>
	  <c:set var="selectedRow" value="${AuthorityList[1]}${AuthorityList[3]}${AuthorityList[5]}${AuthorityList[7]}"/>	
	</div>
</c:if>
</c:forEach>
<!-- Input Fields -->
<div class="panel">
	<div class="panel-heading">
		<span class="fa fa-check-square"></span> Grade Semester Exam
    </div>
    <div class="panel-body"> 				 
		<c:choose>
	    <c:when test="${not empty Students.studentList}">	
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
					<th>Points</th>
					<th>Out of</th>
					<th colspan="2" style="text-align:center;">Action</th>								
				</tr>
				</thead>					   		
			  	<c:forEach items="${Students.studentList}" var="Student" varStatus="position">
			 		<c:if test="${Student[0]!=null}">
					<tr> 			
				   		<td>
				   		<c:out value="${Student[0]}"/>
				   		</td>
					  	<td style="width:50px;">
							<c:choose>
							<c:when test="${not empty Students.fileName[position.index] && Students.fileName[position.index]!=null}">
								<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Students.account_id[position.index]}/Schools/${Students.school_id[position.index]}/Students/Photos/${Student[0]}/${Students.fileName[position.index]}">
							</c:when>
							<c:otherwise>
								<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
							</c:otherwise>
							</c:choose>							  						 
					  	</td>			   		
				   		<td>
				   		<c:out value="${Student[1]}"/>
				   		</td>
				   		<td>
				   		<c:out value="${Student[2]}"/>
				   		</td>
				   		<td>
				   		<div id="Grade_Box${Student[0]}" >			
						   <c:choose>														 									
							 <c:when test="${Student[3] == null}">
								<input name="Student_Grade${Student[0]}" id="Student_Grade${Student[0]}" type="number" min="0" class="form-control">		
							 </c:when>
							 <c:otherwise>
						      	<c:out value="${Student[3]}"></c:out>
						      	<input name="Student_Grade${Student[0]}" type="hidden"  id="Student_Grade${Student[0]}">
							 </c:otherwise>
						   </c:choose>			
				   		</div>
				   		<div id="Grade_Required${Student[0]}" style="color:red"></div>	
				   		</td>
				   		<td>
							<c:out value="${Students.semester_Exam_Grade}"/>
			   			</td>
				   		<td style="text-align:center;width:130px;">
					   		<div class="buttonHolder">
					   		<input name="Grade${Student[0]}" type="button" class="btn btn-sm btn-success btn-block" style="width:130px;" id="Grade${Student[0]}" 
							<c:choose>						 									
								<c:when test="${Student[3] == null}">
									value="Grade"		 			
								</c:when>
								<c:otherwise>
									value="Edit"					      
								</c:otherwise>
							</c:choose>
				   			onClick="manageSemesterGrading(Student_Grade${Student[0]}.value,${Student[0]},Semester_Exam_id.value, Grade${Student[0]}.value, '${Students.semester_Exam_Grade}');					   				
				   			">	
							</div>
				   		</td>	
				   		<td style="text-align:center;width:130px;">
					   		<div id="DeleteArea${Student[0]}">
					   		<c:if test="${Student[3] != null}">
						   		<div class="buttonHolder">
						   		<input name="Delete${Student[0]}" type="button" class="btn btn-sm btn-danger btn-block" style="width:130px;" id="Delete${Student[0]}" value="Delete"
						   		onClick="manageSemesterGrading(Student_Grade${Student[0]}.value,${Student[0]},Semester_Exam_id.value, Delete${Student[0]}.value, '${Students.semester_Exam_Grade}');"
						   		>
						   		</div>
							</c:if>
							</div>  
						</td>		
					</tr>
				
					</c:if>
			</c:forEach>
			<tr>
				<td colspan="8">
					<div class="buttonHolder">
						   	<button name="submit" type="submit" class="btn btn-success mr10" style="width:133px;" id="submit" value="Grade All" onClick="checkSession();">Grade All</button>
						   	<button name="submit" type="submit" class="btn btn-danger mr10" id="submit" value="Delete All Grades" onClick="checkSession();">Delete All Grades</button>
					</div>										
				</td>
			</tr>
			</table>
		   </div>
	      </div>
	        </div>				
			<input name="Semester_Exam_id" type="hidden"  id="Semester_Exam_id" value="${param.Semester_Exam_id}">
			<input name="Grade_id" type="hidden"  id="Grade_id" value="${param.Grade_id}">
			<input name="Class_id" type="hidden"  id="Class_id" value="${param.Class_id}">
			<input name="Course_id" type="hidden"  id="Course_id" value="${param.Course_id}">
			<input name="Group_id" type="hidden"  id="Group_id" value="${param.Group_id}">
			<input name="selectedRow" type="hidden" id="selectedRow" value="${selectedRow}">
			<input name="Semester_Exam_Grade" type="hidden"  id="Semester_Exam_Grade" value="<c:out value='${Students.semester_Exam_Grade}'/>">	
	    </c:when>
	    <c:otherwise>
	 			 <div class="message">There are no students available to be graded</div>	
	    </c:otherwise>
		</c:choose>
	</div>
</div>
