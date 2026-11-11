<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<select name="School_Year_id" id="School_Year_id" onChange="showGrades(${param.School_id}, this.value)" class="span11">
  <option value=""> 
  	Select Year
  </option>
   <c:forEach items="${School_Years.school_Year_id}" var="School_Year_id" varStatus="position">
   	<option value="${School_Year_id}"><c:out value='${School_Years.year_Name[position.index]}'/></option>
   </c:forEach>	
	</select>
	
