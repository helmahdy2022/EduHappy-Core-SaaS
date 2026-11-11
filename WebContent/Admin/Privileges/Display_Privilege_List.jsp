<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="control-group">
  <label class="control-label">Feature Name *</label>
  <div class="controls">
    <c:forEach items="${Privilege_List.feature_id}" var="Feature_id" varStatus="position">
    	<input type="checkbox" name="Feature${Feature_id}" id="Feature${Feature_id}" value="${Feature_id}" 
			<c:forEach items="${Admin_Privilege.feature_id}" var="Admin_Privilege_id" varStatus="loop">
				<c:if test="${Admin_Privilege_id==Feature_id}">
					Checked
				</c:if> 
			</c:forEach>   	
    	> 
    	<c:out value='${Privilege_List.feature_Name[position.index]}'/>
    	<br><br>
   		<input type="hidden" name="hiddenFeature${Feature_id}"  id="hiddenFeature${Feature_id}" value="${Feature_id}">  
    </c:forEach>						
  </div>
</div>