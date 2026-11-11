<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <div id="topbar-dropmenu">
        <div class="topbar-menu row">
          <div class="col-xs-4 col-sm-2">
            <a href="${pageContext.request.contextPath}/Teacher/Messages/Initiate_Teacher_Messages.jsp" class="metro-tile" onclick="overlayOn();">
              <span class="glyphicon glyphicon-inbox"></span>
              <span class="metro-title">Messages</span>
            </a>
          </div>
          <div class="col-xs-4 col-sm-2">
            <a href="${pageContext.request.contextPath}/Get_Teacher_Details?Teacher_id=${Teacher_Account_Details.teacher_id}&&Employee_id=${Teacher_Account_Details.employee_id}&&Source=view" onClick="checkSession(); overlayOn();" class="metro-tile">
              <span class="glyphicon glyphicon-user"></span>
              <span class="metro-title">My Profile</span>
            </a>
          </div>
          <div class="col-xs-4 col-sm-2">
            <a href="mailto:info@edu-happy.com" class="metro-tile" onclick="overlayOn();">
              <span class="glyphicon glyphicon-headphones"></span>
              <span class="metro-title">Support</span>
            </a>
          </div>
          <div class="col-xs-4 col-sm-2">
            <a href="${pageContext.request.contextPath}/Teacher/Profile/Change_Password.jsp" class="metro-tile" onclick="overlayOn();">
              <span class="fa fa-gears"></span>
              <span class="metro-title">Settings</span>
            </a>
          </div>
          <div class="col-xs-4 col-sm-2">
            <a href="${pageContext.request.contextPath}/Help/EduHappy_Teacher_Guide.pdf" class="metro-tile" target=_blank>
              <span class="glyphicon glyphicon-question-sign"></span>
              <span class="metro-title">Help</span>
            </a>
          </div>
          <div class="col-xs-4 col-sm-2">
            <a href="${pageContext.request.contextPath}/Teacher/Teacher_Main.jsp" class="metro-tile" onclick="overlayOn();">
              <span class="glyphicon glyphicon-th"></span>
              <span class="metro-title">Authority</span>
            </a>
          </div>
        </div>
      </div>
      <br><br><br>