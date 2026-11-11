<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
 window.onbeforeunload = function (e) {

   	if(window.opener.document.getElementById("Attempt_Window${sessionScope['Attempt_id']}")!=null)
	{
		window.opener.document.getElementById("Attempt_Window${sessionScope['Attempt_id']}").innerHTML="Window Closed";
	}  
}; 
</script>
<form id="form1" action="${pageContext.request.contextPath}/SubmitQuizScore" method="post" target="parentwindow" onsubmit="window.parent.close()">
<br><br><br><br><br><br><br><br>
<table align="center" width="400" border="1">
<tr>
<td>
<c:choose>
<c:when test="${param.attempt_Duration==0 && sessionScope['student_Score']!=-1 && sessionScope['student_Score']!=''&& sessionScope['student_Score']!=null}">
<c:set var="student_Score" value="${sessionScope['student_Score'] }"/>
</c:when>
<c:otherwise>
<c:set var="student_Score" value="${param.student_Score}"/>
</c:otherwise>
</c:choose>

<c:choose>
<c:when test="${param.attempt_Duration==0 && sessionScope['score_Scale']!=-1 && sessionScope['score_Scale']!=''&& sessionScope['score_Scale']!=null}">
<c:set var="score_Scale" value="${sessionScope['score_Scale'] }"/>
</c:when>
<c:otherwise>
<c:set var="score_Scale" value="${param.score_Scale}"/>
</c:otherwise>
</c:choose>

You scored <c:out value="${param.rounded_Score}"/>% 
<%-- <input type="hidden" name="Student_id" value="${sessionScope['Student_Session']}">
<input type="hidden" name="Assignment_id" value="${sessionScope['Assignment_id']}">
<input type="hidden" name="Attempt_id" value="${sessionScope['Attempt_id']}"> --%>
<input type="hidden" name="student_Score" value="${student_Score}">
<input type="hidden" name="score_Scale" value="${score_Scale}">
<input type="hidden" name="attempt_Duration" value="${param.attempt_Duration}">

</td>
</tr>
<tr>
<td align="center">
<input type="submit" value="Submit to finish your attempt and save your score">
</td>
</tr>
</table>
 		 <input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
</form>

