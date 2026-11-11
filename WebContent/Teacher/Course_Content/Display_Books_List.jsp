<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${not empty Books.book_id}">
  <table class="table table-bordered mbn">
    <tbody>
    <tr>
	<td style="width:5px; background-color:#f2f2f2;"></td>
    <td style="vertical-align:top;width:1px;border-right:1px solid #ccc">
		<div id="Book">					
			<h4 style="font-size:20px; font-weight:bold;text-decoration: underline;">eBooks Links</h4>
			<div class="assignmentList">
			<c:forEach items="${Books.book_id}" var="Book_id" varStatus="loop">
			<div class="assignmentLinkHolder">
			<div style="width:15px;display:inline-block">
			<i class="fa fa-book"></i>
			</div>
			<div style="width:175px;display:inline-block;vertical-align: top;border-bottom:1px solid #ccc">
			<a href="javascript:editBookDetails(${Book_id}, ${param.Course_id });" class="assignmentLink">
			<c:out value="${Books.title[loop.index]}"/>
			</a>
			</div>
			</div>
			</c:forEach>
			</div>						
		</div>			    
    </td>
    <td style="vertical-align:top;border-left:transparent">
	 <div id="BookDetails"> 
		<div style="text-align:center; width:90%;font-family:Times New Roman; font-size:48px; color:#0aaedb">eBooks List</div>
		<table class="table table-bordered mbn" style="width:90%;margin-left:70px">
		   <thead>
		     <tr>
		       <th>Book ID</th>
		       <th>Book Title</th>
		       <th>ISBN</th>
		       <th>Author</th>      
		     </tr>
		   </thead>
			<tbody>
		<c:forEach items="${Books.book_id}" var="Book_id" varStatus="position">
		<tr>
			<td style="width:50px;">
				<a href="javascript:editBookDetails(${Book_id}, ${param.Course_id });">${Book_id}</a>
			</td>
			<td style="width:150px;">
				<a href="javascript:editBookDetails(${Book_id}, ${param.Course_id });">
				<c:out value='${Books.title[position.index]}'/>
				</a>
			</td>
			<td style="width:100px;"><c:out value='${Books.ISBN[position.index]}'/> </td>
			<td style="width:100px;"><c:out value='${Books.author[position.index]}'/></td>
		</tr>
		</c:forEach>
			</tbody>
		</table>
	 </div>				    
    <div id="DeleteMessage" ></div>
    </td>
	<td style="width:5px;background-color:#f2f2f2"></td>
    </tr>
    </tbody>
  </table>  
</c:when>
<c:otherwise>
<div class="message"><c:out value="There are no books to edit"/></div>
</c:otherwise>
</c:choose>
