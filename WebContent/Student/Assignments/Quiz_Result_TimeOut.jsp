<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="form1" action="${pageContext.request.contextPath}/Student/Assignments/Initiate_Assignments.jsp" method="post" target="parentwindow" onsubmit="window.parent.close()">
<br><br><br><br><br><br><br><br>
<table align="center" width="400" border="1">
<tr>
<td>

You scored <c:out value="${param.student_Score}"/> out of <c:out value="${param.max_Score }"/>

<tr>
<td align="center">
<input type="submit" value="Close Window and return to Assignment Page">
</td>
</tr>
</table>
 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
</form>

