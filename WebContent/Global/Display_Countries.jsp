<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <select name="Country_id" id="Country_id" required>
  <option value=""> 
  	Select Country
  </option>
  <c:forEach items="${Countries.country_id}" var="Country_id" varStatus="position">
  	<option value="${Country_id}"><c:out value='${Countries.country_Name[position.index]}'/></option>
  </c:forEach>
</select>
