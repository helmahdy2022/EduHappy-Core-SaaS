<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="i" begin="1" end="${param.terms_no}">
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Term Details</h5>
</div>
<div class="widget-content nopadding">
<div class="control-group">
	<label class="control-label">Term Name *</label>
	<div class="controls">
		 <input name="Term_Name${i}" type="text" id="Term_Name"${i} class="span11" required maxlength="50"><input name="Terms_qty" type="hidden"  id="Term_qty" value="${param.terms_no}"/>
	</div>
</div>
<div class="control-group">
	<label class="control-label">Start Date *</label>
	<div class="controls">
		 <input name="Term_Start${i}" type="Date" id="Term_Start${i}" class="span11" required>
	</div>
</div>
<div class="control-group">
	<label class="control-label">End Date *</label>
	<div class="controls">
<input name="Term_End${i}" type="Date" id="Term_End${i}" class="span11" required>    
	</div>
</div>
<div class="control-group">
	<label class="control-label">Vacation Start *</label>
	<div class="controls">
<input name="Vacation_Start${i}" type="Date" id="Vacation_Start${i}" class="span11"  required>    
	</div>
</div>
<div class="control-group">
	<label class="control-label">Vacation End *</label>
	<div class="controls">
<input name="Vacation_End${i}" type="Date" id="Vacation_End${i}" class="span11" required>    
	</div>
</div>
</div>
 </c:forEach>
