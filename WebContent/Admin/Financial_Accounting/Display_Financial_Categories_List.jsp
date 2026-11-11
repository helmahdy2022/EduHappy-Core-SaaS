<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select name="Financial_Category_id" id="Financial_Category_id" class="span11">
		<option value="">
			Select Financial Category
		</option>	 	
	<c:forEach items="${Financial_Categories_List.financial_Category_id}" var="Financial_Category_id" varStatus="loop">

		<option value="${Financial_Category_id}">
			<c:out value="${Financial_Categories_List.financial_Category_Name[loop.index]}"></c:out>
		</option>	   						 			
	</c:forEach>
	</select>
