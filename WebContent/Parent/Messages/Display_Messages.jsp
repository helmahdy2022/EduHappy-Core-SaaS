<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="messageTo">${param.First_Name} ${param.Last_Name}</div>
<div class="chat-messages" id="chat-messages">
<div id="chat-messages-inner">
	 <table class="table table-bordered data-table">
	 
		<c:forEach items="${messages_List.message_id}" var="Message_id" varStatus="position">
		<tr>
		<td>
		<div class="messageDate"><c:out value='${messages_List.message_Date[position.index]}'/> - <c:out value='${messages_List.message_Time[position.index]}'/></div>
		<c:choose>
		
		<c:when test="${messages_List.sender_id[position.index]==sessionScope['Parent_Session'] }">
				<div class="messagesSender"><c:out value="${messages_List.message_Body[position.index]}"/></div>
		</c:when>
		<c:otherwise>
				<div class="messagesRecepient"><c:out value="${messages_List.message_Body[position.index]}"/></div>
		</c:otherwise>
		</c:choose>
		</td>
		</tr>		
		</c:forEach>
	 </table>	 
</div>
</div>
<div class="chat-message well" style="text-align:center;">

	 <textarea name="message_Body" id="message_Body" class="span11" maxlength="2000"></textarea>
	 <input type="hidden" name="User_id" id="User_id" value="${param.User_id}">
	 <div class="buttonHolder">
		 <br><input type="button" class="btn-success" value="Send" onClick="createMessage(message_Body.value, User_id.value, '${param.First_Name}', '${param.Last_Name}', '${param.User_Type}', '${param.Account_Owner}');">
	 </div>
</div>
