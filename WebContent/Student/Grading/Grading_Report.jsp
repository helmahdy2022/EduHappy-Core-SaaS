<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${displayHideReport==0}">
<h2>The reports are not yet published.</h2>
</c:when>
<c:otherwise>
		<table class="table table-bordered mbn">
	    	<thead>
	    		<tr>
		    		<th colspan="50">
		    			GRADING REPORT FOR THE YEAR
		    			<font color="red"> ${Student_Grading.year_Name}</font>
		    			<c:if test="${param.Final_Report=='Yes'}">
		    			 FINAL
		    			</c:if>
		    		</th>
	    		</tr>
	  		</thead>
		 <thead>
		 <tr>
		 <th style="width:300px;">Course</th>
		<c:forEach items="${Student_Grading.term_Grading_Map}" var="Term_Grading_Map" begin="0" end="0">
		<c:forEach items="${Term_Grading_Map.value}" var="Term">
		 <th colspan="2"><c:out value="${Term[1]}"/></th>
		 <c:if test="${Term[6]!=null}">
		 <th colspan="2"><c:out value="${Term[7]}"/></th>
		 <th colspan="2"><c:out value="${Term[10]}"/></th>
		</c:if>
		 <c:if test="${Term[13]!=null}">
		 <th colspan="2">Final Year</th>
		</c:if>
		</c:forEach>
		</c:forEach>
		 </tr>	
		 </thead>
		<c:forEach items="${Student_Grading.term_Grading_Map}" var="Term_Grading_Map">
		<c:forEach items="${Term_Grading_Map.value}" var="Course"  begin="0" end="0">
		 <tr>
		 <td>	  
			<div class="contentLessonOtherTitle"><img id="${Course[2]}" name="+" src="${pageContext.request.contextPath}/images/+.jpg" width="20" height="20"  
			onClick="checkSessionForCat(${Course[2]}, ${param.Student_id}, '${param.User}');">
			</div>
		  <div style="width:300px;"><c:out value="${Course[3]}"/></div>
		 	<div id="Categories${Course[2]}" style="text-align:right;">
		 
		 	</div>
		 </td>
		<c:forEach items="${Student_Grading.term_Grading_Map}" var="Term_Grading_Map">
		 <c:forEach items="${Term_Grading_Map.value}" var="Term_Grading" >
		  <c:if test="${Term_Grading[2] == Course[2]}">
		   <td>		
			<c:choose>
			<c:when test="${Term_Grading[4]!=null}">
			<c:out value="${Term_Grading[4]}"/>% 
			</c:when>
			<c:otherwise>
			-
			</c:otherwise>
			</c:choose>
		  </td>
		  <td>
			<c:choose>
			<c:when test="${Term_Grading[5]!=null}">
			<c:out value="${Term_Grading[5]}"/>
			</c:when>
			<c:otherwise>
			-
			</c:otherwise>
			</c:choose>
		  </td>
		 <c:if test="${Term_Grading[6]!=null}">
		  <td>
			<c:out value="${Term_Grading[8]}"/>
		  </td>
		  <td>
		 <c:choose>
		  <c:when test="${Term_Grading[9]!=null}">
		   <c:out value="${Term_Grading[9]}"/>
		  </c:when>
		  <c:otherwise>
		   -
		  </c:otherwise>
		 </c:choose>
		</td>	
		<td>
		<c:out value="${Term_Grading[11]}"/>
		</td>
		<td>
		<c:choose>
		<c:when test="${Term_Grading[12]!=null}">
		<c:out value="${Term_Grading[12]}"/>
		</c:when>
		<c:otherwise>
		-
		</c:otherwise>
		</c:choose>
		</td>		
		</c:if>
		<c:if test="${Term_Grading[13]!=null}">		
		<td>
		<c:out value="${Term_Grading[13]}"/>
		</td>
		<td>
		<c:choose>
		<c:when test="${Term_Grading[14]!=null}">
		<c:out value="${Term_Grading[14]}"/>
		</c:when>
		<c:otherwise>
		-
		</c:otherwise>
		</c:choose>
		</td>
		</c:if>
		</c:if>
		
		</c:forEach>
		</c:forEach>
		 </tr>
		</c:forEach>
		</c:forEach>
		<tr><td colspan="50" class="gradingFooter">Overall Grade <c:out value="${Student_Grading.overall_Grade}"/>%</td></tr>	
	 </table>
</c:otherwise>
</c:choose>
