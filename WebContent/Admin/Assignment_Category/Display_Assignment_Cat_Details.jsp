<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
	<table class="childTable">
	<tr>
    <td ><label for="Category_Details">Assignment Category Name *</label></td>
    <td>
    <input type="text" name="Category_Name" id="Category_Name" class="bigText" required maxlength="100" value="<c:out value='${Assignment_Category_Details.category_Name}'/>">
    <input type="hidden" name="Category_id" id="Category_id" value="<%=request.getParameter("Category_id")%>">
    </td>
  </tr>
	<tr>
    <td><label for="Category_Grade">Category Grade *</label></td>
    <td>
    <input type="number" name="Category_Grade" id="Category_Grade" class="mediumNumber" required min="0" max="1000000000" value="<c:out value='${Assignment_Category_Details.category_Grade}'/>">
    </td>
  </tr>
  <tr>
   <td colspan="2"><input name="Contribute" type="checkbox" id="Contribute" 
   		<c:if test="${Assignment_Category_Details.contribute==1}">
		 	Checked
	    </c:if>
	    >
   		Contribute to the total final grade</td>
  </tr> 
    <tr>
   <td colspan="2"><input name="Create_Authority" type="checkbox" id="Create_Authority" 
   		<c:if test="${Assignment_Category_Details.create_Authority==1}">
		 	Checked
	    </c:if>
	    >
   Class Teacher can create Assignments under this Category</td>
  </tr> 
  <tr>
  <td colspan="2">
     <input type="hidden" name="Course" value="${param.Course_id}"/>
     <input type="hidden" name="Index" value="${param.Index}"/>
	 <div class="buttonHolder">
      <input type="submit" name="button" id="button" class="smallButton" value="Modify">
      <input type="button" name="Delete" id="Delete"  class="smallButton" value="Delete" onClick="showDeleteMessage()">
	 </div>
	 <div class="requiredSignNote">Fields marked (*) are required</div>
  </td>
  </tr>      
  </table>
