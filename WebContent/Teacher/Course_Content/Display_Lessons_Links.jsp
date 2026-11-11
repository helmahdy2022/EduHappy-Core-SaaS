<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${not empty Lessons_List.lesson_Name}">
                <table  class="table table-bordered mbn">
                  <tbody>
				    <tr>
				    <td style="width:5px; background-color:#f2f2f2;"></td>
				    <td style="vertical-align:top;width:1px;border-right:1px solid #ccc">
						<div id="Lesson">
							<h4 style="font-size:20px; font-weight:bold;text-decoration: underline;">Lessons Links</h4>

							<div class="assignmentList" style="margin-top:0px;">
								<c:forEach items="${Lessons_List.lesson_Name}" var="Lesson" varStatus="loop">
									<div class="assignmentLinkHolder">
									<div style="width:15px;display:inline-block">
									<i class="glyphicon glyphicon-edit"></i>
									</div>
									<div style="width:175px;display:inline-block;vertical-align: top;border-bottom:1px solid #ccc">				
									<a href="javascript:editLesson(${Lessons_List.lesson_id[loop.index]}, ${Lessons_List.unit_id}, ${param.Course_id})" class="assignmentLink">
									<c:out value="${Lesson}"/>
									</a>
									</div>
									</div>
								</c:forEach>
							</div>											
						</div>			    
				    </td>

				    <td style="vertical-align:top;border-left:transparent;">
					 <div id="LessonDetails"> 
						<div style="text-align:center; width:90%;font-family:Times New Roman; font-size:48px; color:#0aaedb">Lessons List</div>
						<table class="table table-bordered mbn" style="width:90%;margin-left:70px">
						   <thead>
						     <tr>
						       <th>Lesson ID</th>
						       <th>Lesson Name</th>
						       <th>Start Date</th>
						       <th>End Date</th>      
						     </tr>
						   </thead>
							<tbody>
						<c:forEach items="${Lessons_List.lesson_Name}" var="Lesson" varStatus="position">
						<tr>
							<td style="width:50px;">
								<a href="javascript:editLesson(${Lessons_List.lesson_id[position.index]}, ${Lessons_List.unit_id}, ${param.Course_id})">${Lessons_List.lesson_id[position.index]}</a>
							</td>
							<td style="width:150px;">
								<a href="javascript:editLesson(${Lessons_List.lesson_id[position.index]}, ${Lessons_List.unit_id}, ${param.Course_id})">
								<c:out value='${Lesson}'/>
								</a>
							</td>
							<td style="width:100px;"><c:out value='${Lessons_List.lesson_Start[position.index]}'/> </td>
							<td style="width:100px;"><c:out value='${Lessons_List.lesson_End[position.index]}'/></td>
						</tr>
						</c:forEach>
							</tbody>
						</table>
					 </div>
					 <div id="DeleteMessage" ></div>
			    
				    </td>
				    <td style="width:5px;background-color:#f2f2f2"></td>
				    </tr>
                  </tbody>
                </table> 
	</c:when>
	<c:otherwise>
	<div class="message">There are no lessons associated with the selected unit</div>
	</c:otherwise>
</c:choose>



