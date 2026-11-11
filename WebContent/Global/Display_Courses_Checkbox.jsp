<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>


</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
         	<div class="form-group">
                 <label class="col-lg-3 control-label">Select Subject(s)</label>
                 <div class="col-lg-8">
                   <div class="bs-component"> 					                    		     
		    		<div class="checkbox-custom mb5">            
	        			 <c:forEach items="${Courses.course_id}" var="Course_id" varStatus="position">	
	         			
	         			<input type="checkbox" name="Course${Course_id}" value="${Course_id}" id="Course${Course_id}" onChange="calculateFees(this.value, ${Courses.course_Price[position.index]});"/>
	         			<label for="Course${Course_id}">
	         			<c:out value='${Courses.course_Name[position.index]}'/> - Price: <c:out value='${Courses.course_Price[position.index]}'/> EGP /4 weeks
	           			</label>
	           			<br/>
	         			</c:forEach>				    
			 		</div>					   		
                   </div>
                 </div>                 
            </div>
</body>
</html>