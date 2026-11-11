<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:choose>
 <c:when test="${not empty Year_Terms.year_Term_Name}">
	<div class="control-group">
		<label class="control-label">Select Term(s)</label>
		<div class="controls">
			<c:forEach items="${Year_Terms.year_Term_Name}" var="Year_Term" varStatus="loop">
				&nbsp;&nbsp;&nbsp;<input name="Year_Term_id${Year_Terms.year_Term_id[loop.index]}" type="checkbox" value="${Year_Terms.year_Term_id[loop.index]}"><c:out value='${Year_Term}'/><br>					 			
			</c:forEach>
	    </div>
	</div>
 </c:when>
 <c:otherwise>
 <c:out value="There are no terms to select"/>
 </c:otherwise>
 </c:choose>
