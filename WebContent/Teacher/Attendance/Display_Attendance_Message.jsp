<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <c:if test="${not empty param.message}">
		<div class="message">${param.message}</div> 
</c:if>
