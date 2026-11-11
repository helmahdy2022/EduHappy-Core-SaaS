<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <div id="topbar-dropmenu">
        <div class="topbar-menu row">
			<c:choose>
				<c:when test="${Source=='Student'}">
		         <div class="col-xs-4 col-sm-2">
		            <a href="${pageContext.request.contextPath}/Student/Messages/Initiate_Student_Messages.jsp" class="metro-tile" onclick="overlayOn();">
		              <span class="glyphicon glyphicon-inbox"></span>
		              <span class="metro-title">Messages</span>
		            </a>
		          </div>
		          <div class="col-xs-4 col-sm-2">
		 			<a href="${pageContext.request.contextPath}/Get_Student_Details?Student_id=${Student_Account_Details.student_id}&&Source=view" onClick="checkSession(); overlayOn();" class="metro-tile">
		              <span class="glyphicon glyphicon-user"></span>
		              <span class="metro-title">My Profile</span>
		            </a>
		          </div>
		          <div class="col-xs-4 col-sm-2">
		            <a href="mailto:info@edu-happy.com" class="metro-tile">
		              <span class="glyphicon glyphicon-headphones"></span>
		              <span class="metro-title">Support</span>
		            </a>
		          </div>
		          <div class="col-xs-4 col-sm-2">
		            <a href="${pageContext.request.contextPath}/Student/Profile/Change_Password.jsp" class="metro-tile" onclick="overlayOn();">
		              <span class="fa fa-gears"></span>
		              <span class="metro-title">Settings</span>
		            </a>
		          </div>
		          <div class="col-xs-4 col-sm-2">
		            <a href="${pageContext.request.contextPath}/Help/EduHappy_Student_Guide.pdf" class="metro-tile" target=_blank>
		              <span class="glyphicon glyphicon-question-sign"></span>
		              <span class="metro-title">Help</span>
		            </a>
		          </div>
		          <div class="col-xs-4 col-sm-2">
		            <a href="${pageContext.request.contextPath}/Student/ExamSchedule/Exam_Schedule.jsp" class="metro-tile" onclick="overlayOn();">
		              <span class="glyphicon glyphicon-th"></span>
		              <span class="metro-title">Exam Schedule</span>
		            </a>
		          </div>		          			
				</c:when>
				<c:otherwise>
		         <div class="col-xs-4 col-sm-2">
		            <a href="${pageContext.request.contextPath}/Parent/Messages/Initiate_Parent_Messages.jsp" class="metro-tile" onclick="overlayOn();">
		              <span class="glyphicon glyphicon-inbox"></span>
		              <span class="metro-title">Messages</span>
		            </a>
		          </div>
		          <div class="col-xs-4 col-sm-2">
		 			<a href="${pageContext.request.contextPath}/Get_Parent_Details?Parent_id=${Parent_Account_Details.parent_id}&&Source=view" onClick="checkSession(); overlayOn();" class="metro-tile">
		              <span class="glyphicon glyphicon-user"></span>
		              <span class="metro-title">My Profile</span>
		            </a>
		          </div>
		          <div class="col-xs-4 col-sm-2">
		            <a href="mailto:info@edu-happy.com" class="metro-tile">
		              <span class="glyphicon glyphicon-headphones"></span>
		              <span class="metro-title">Support</span>
		            </a>
		          </div>
		          <div class="col-xs-4 col-sm-2">
		            <a href="${pageContext.request.contextPath}/Parent/Profile/Change_Password.jsp" class="metro-tile" onclick="overlayOn();">
		              <span class="fa fa-gears"></span>
		              <span class="metro-title">Settings</span>
		            </a>
		          </div>
		          <div class="col-xs-4 col-sm-2">
		            <a href="${pageContext.request.contextPath}/Help/EduHappy_Student_Guide.pdf" class="metro-tile" target=_blank>
		              <span class="glyphicon glyphicon-question-sign"></span>
		              <span class="metro-title">Help</span>
		            </a>
		          </div>
				</c:otherwise>
			</c:choose>
        </div>
      </div>