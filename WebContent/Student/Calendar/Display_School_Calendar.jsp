<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <!-- Start: Right Sidebar -->
    <aside id="sidebar_right" class="nano affix">
      <!-- Start: Sidebar Right Content -->
      <div class="sidebar-right-content nano-content">
        <div class="tab-block sidebar-block br-n">
          <ul class="nav nav-tabs tabs-border nav-justified">
            <li class="active">
              <a href="#sidebar-right-tab1" data-toggle="tab">Academic Year</a>
            </li>
            <li>
              <a href="#sidebar-right-tab2" data-toggle="tab">Terms</a>
            </li>
            <li>
              <a href="#sidebar-right-tab3" data-toggle="tab">Days Off</a>
            </li>
          </ul>
          <div class="tab-content br-n">
            <div id="sidebar-right-tab1" class="tab-pane active">
              <h5 class="title-divider text-muted mb20"> School Calendar
                <span class="pull-right"> <c:out value='${School_Year.year_Name}'/>
                  <i class="fa fa-caret-down ml5"></i>
                </span>
              </h5>
              <h5 class="title-divider text-muted mt30 mb10">Academic Year</h5>
              <div class="row">
                <div class="col-xs-5">
                  <h3 class="text-primary mn pl5"><c:out value='${School_Year.year_Name}'/></h3>
                </div>
                <div class="col-xs-7 text-right">
                  <h3 class="text-success-dark mn">
                    <i class="fa fa-caret-right"></i> Current Year </h3>
                </div>
              </div>

              <h5 class="title-divider text-muted mt25 mb10">Year Start</h5>
              <div class="row">
                <div class="col-xs-5">
                  <h3 class="text-primary mn pl5"><c:out value='${School_Year.YS_date}'/></h3>
                </div>
                <div class="col-xs-7 text-right">
                  <h3 class="text-success-dark mn">
                    <i class="fa fa-caret-right"></i> Start Date </h3>
                </div>
              </div>

              <h5 class="title-divider text-muted mt25 mb10">Year End</h5>
              <div class="row">
                <div class="col-xs-5">
                  <h3 class="text-primary mn pl5"><c:out value='${School_Year.YE_date}'/></h3>
                </div>
                <div class="col-xs-7 text-right">
                  <h3 class="text-success-dark mn">
                    <i class="fa fa-caret-right"></i> End Date </h3>
                </div>
              </div>

              <h5 class="title-divider text-muted mt40 mb20"> National Holidays
                <span class="pull-right text-primary fw600">${fn:length(Holidays_Details.existingHolidays)} Holidays</span>
              </h5>
			<c:forEach items="${Holidays_Details.existingHolidays}" var="Holiday" >
			  <c:if test="${Holiday[0]!=null}">
	            <div class="media">
	              <a class="media-left" href="#">
	                <img src="${pageContext.request.contextPath}/img/avatars/Holiday_Avatar.jpg" class="mw40 br64" alt="holder-img">
	              </a>
	              <div class="media-body">
	                <h5 class="media-heading"><c:out value='${Holiday[1]}'/>
	                  <small class="text-muted"></small>
	                </h5>
	                Holiday Starts
	                <a class="text-system" href="#"> <c:out value='${Holiday[2]}'/> </a>
	                <br/>
	                Holiday Ends&nbsp;&nbsp;
	                <a class="text-system" href="#"> <c:out value='${Holiday[3]}'/> </a>
	              </div>
	            </div>
              </c:if>
            </c:forEach>
            </div>
            <div id="sidebar-right-tab2" class="tab-pane">
              <h5 class="title-divider text-muted mb20"> School Calendar
                <span class="pull-right"> <c:out value='${School_Year.year_Name}'/>
                  <i class="fa fa-caret-down ml5"></i>
                </span>
              </h5>
			  <c:choose>
				<c:when test="${param.Grade_Existing_Terms==false}">
              	 <h5 class="title-divider text-muted mt40 mb20"> Year Terms
                	<span class="pull-right text-primary fw600">${fn:length(School_Year.year_Terms)} Terms</span>
                 </h5>
	            <c:forEach items="${School_Year.year_Terms}" var="Term" varStatus="position">
	            <c:if test="${Term[0]!=null}">
	            <div class="media">
	              <a class="media-left" href="#">
	                <img src="${pageContext.request.contextPath}/img/avatars/Terms_Avatar.jpg" class="mw40 br64" alt="holder-img">
	              </a>
	              <div class="media-body">
	                <h5 class="media-heading"><c:out value='${Term[1]}'/>
	                  <small class="text-muted"></small>
	                </h5>
	                Term Starts
	                <a class="text-system" href="#"> <c:out value='${Term[2]}'/> </a>
	                <br/>
	                Term Ends&nbsp;&nbsp;
	                <a class="text-system" href="#"> <c:out value='${Term[3]}'/> </a>
					<br/>
	                Vacation Starts
	                <a class="text-system" href="#"> <c:out value='${Term[2]}'/> </a>
	                <br/>
	                Vacation Ends&nbsp;&nbsp;
	                <a class="text-system" href="#"> <c:out value='${Term[3]}'/> </a>					
	              </div>
	            </div>
	            </c:if>
	            </c:forEach>
				</c:when>
				<c:otherwise>
              	 <h5 class="title-divider text-muted mt40 mb20"> Grade Terms
                	<span class="pull-right text-primary fw600">${fn:length(Grade_Terms.grade_Terms_List)} Terms</span>
                 </h5>
	            <c:forEach items="${Grade_Terms.grade_Terms_List}" var="Term" varStatus="position">
	            <c:if test="${Term[0]!=null}">
	            <div class="media">
	              <a class="media-left" href="#">
	                <img src="${pageContext.request.contextPath}/img/avatars/Terms_Avatar.jpg" class="mw40 br64" alt="holder-img">
	              </a>
	              <div class="media-body">
	                <h5 class="media-heading"><c:out value='${Term[1]}'/>
	                  <small class="text-muted"></small>
	                </h5>
	                Term Starts
	                <a class="text-system" href="#"> <c:out value='${Term[2]}'/> </a>
	                <br/>
	                Term Ends&nbsp;&nbsp;
	                <a class="text-system" href="#"> <c:out value='${Term[3]}'/> </a>
					<br/>
	                Vacation Starts
	                <a class="text-system" href="#"> <c:out value='${Term[2]}'/> </a>
	                <br/>
	                Vacation Ends&nbsp;&nbsp;
	                <a class="text-system" href="#"> <c:out value='${Term[3]}'/> </a>					
	              </div>
	            </div>
	            </c:if>
	            </c:forEach>
				</c:otherwise>
			  </c:choose>            
            </div>
            <div id="sidebar-right-tab3" class="tab-pane">
              <h5 class="title-divider text-muted mb20"> School Calendar
                <span class="pull-right"> <c:out value='${School_Year.year_Name}'/>
                  <i class="fa fa-caret-down ml5"></i>
                </span>
              </h5>
              <h5 class="title-divider text-muted mt40 mb20"> Days Off
                <span class="pull-right text-primary fw600">${fn:length(Daysoff_Details.daysoff_id)} Days-off</span>
              </h5>
				<c:forEach items="${Daysoff_Details.daysoff_id}" var="Daysof_id" varStatus="position">
				<c:if test="${Daysof_id!=null}">
	            <div class="media">
	              <a class="media-left" href="#">
	                <img src="${pageContext.request.contextPath}/img/avatars/Days-off_Avatar.jpg" class="mw40 br64" alt="holder-img">
	              </a>
	              <div class="media-body">
	                <h5 class="media-heading"><c:out value='${Daysoff_Details.daysoff_Name[position.index]}'/>
	                  <small class="text-muted"></small>
	                </h5>
	                Term Starts
	                <a class="text-system" href="#"> <c:out value='${Daysoff_Details.s_Date[position.index]}'/> </a>
	                <br/>
	                Term Ends&nbsp;&nbsp;
	                <a class="text-system" href="#"> <c:out value='${Daysoff_Details.e_Date[position.index]}'/> </a>				
	              </div>
	            </div>
 				</c:if>
				</c:forEach>
          </div> 
          <!-- end: .tab-content -->
        </div>
      </div>
    </aside>
    <!-- End: Right Sidebar --> 