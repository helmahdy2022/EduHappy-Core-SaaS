<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

 	 <c:if test="${not empty message}">
  		<div class="message">${message}
  		<br>
  		<c:forEach items="${uploadMessage}" var="uploadMessage">
  		<c:out value="${uploadMessage}" />
  		<br>
  		</c:forEach>
  		</div> 
	 	 <c:if test="${fn:contains(message, 'Assignment Successfully Deleted')}">
	   		<script>
	   		document.getElementById("DeleteMessage").innerHTML="";
	   		document.getElementById("Assignment").innerHTML="";
	   		</script>
		 </c:if>
 	 </c:if>
 