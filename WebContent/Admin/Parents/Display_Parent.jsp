<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${param.Page=='Edit_Parent.jsp'}">
	<c:set var="Page" value="Get_Parent_Details"/>
</c:if>
<c:if test="${param.Page=='Link_Parent_Student.jsp'}">
	<c:set var="Page" value="Admin/Parents/Link_Parent_Student.jsp"/>
</c:if>
<c:if test="${param.Page=='Remove_Link_Parent_Student'}">
	<c:set var="Page" value="Remove_Link_Parent_Student"/>
</c:if>		
<div class="widget-box">
  <div style="overflow-x:auto;">
	<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
		      <h5>Search Results</h5>
	</div>
	<div class="widget-content nopadding">		
		<c:choose>
			<c:when test="${not empty Parents.parent_id}">
			<table id="parents" class="table table-striped table-bordered" style="width:90%">
			   <thead>
			     <tr>
			       <th>ID</th>
			       <th>Photo</th>
			       <th>First Name</th>
			       <th>Last Name</th>
			       <th>Username</th>       
			     </tr>
			   </thead>
				<tbody>
			<c:forEach items="${Parents.parent_id}" var="Parent_id" varStatus="position">
			<tr>
				<td style="width:50px;">
					<a href="${pageContext.request.contextPath}/Get_Parent_Details?Parent_id=${Parent_id}" onclick="overlayOn();">${Parent_id}</a>
				</td>
				<td style="width:50px;">
 				<c:choose>
				<c:when test="${Page=='Remove_Link_Parent_Student'}">
					<c:choose>
					<c:when test="${not empty Parents.fileName[position.index] && Parents.fileName[position.index]!=null}">
						<a href="#" onclick="showParentStudents(${Parent_id});">
						<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Parents.account_id[position.index]}/Parents/Photos/${Parent_id}/${Parents.fileName[position.index]}">
						</a>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="showParentStudents(${Parent_id});">
						<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
						</a>
					</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${not empty Parents.fileName[position.index] && Parents.fileName[position.index]!=null}">
						<a href="${pageContext.request.contextPath}/${Page}?Parent_id=${Parent_id}&&Page=${param.Page}&&Parent_First_Name=${Parents.parent_First_Name[position.index]}&&Parent_Last_Name=${Parents.parent_Last_Name[position.index]}" onclick="overlayOn();">
						<img width="50" src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Parents.account_id[position.index]}/Parents/Photos/${Parent_id}/${Parents.fileName[position.index]}">
						</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/${Page}?Parent_id=${Parent_id}&&Page=${param.Page}" onclick="overlayOn();">
						<img width="50" src="${pageContext.request.contextPath}/images/Add_Photo.jpg">
						</a>
					</c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose> 	
				</td>
				<td style="width:100px;"><c:out value='${Parents.parent_First_Name[position.index]}'/> </td>
				<td style="width:200px;"><c:out value='${Parents.parent_Last_Name[position.index]}'/></td>
				<td style="width:200px;"><c:out value='${Parents.username[position.index]}'/> </td>
			</tr>
			</c:forEach>
				</tbody>
			</table>
			</c:when>
			<c:otherwise>
			<br>
			  		<div class="message">There are no Parents found under the selected search criteria. Please change search criteria and try again.</div>
			</c:otherwise>
		</c:choose>
	</div>
  </div>
</div>

