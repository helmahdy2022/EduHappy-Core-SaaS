<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <!-- Start: Right Sidebar -->
    <aside id="sidebar_right" class="nano affix">
      <!-- Start: Sidebar Right Content -->
      <div class="sidebar-right-content nano-content">
        <div class="tab-block sidebar-block br-n">
          <ul class="nav nav-tabs tabs-border nav-justified hidden">
            <li class="active">
              <a href="#sidebar-right-tab1" data-toggle="tab">Tab 1</a>
            </li>
            <li>
              <a href="#sidebar-right-tab2" data-toggle="tab">Tab 2</a>
            </li>
            <li>
              <a href="#sidebar-right-tab3" data-toggle="tab">Tab 3</a>
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
            <div id="sidebar-right-tab2" class="tab-pane"></div>
            <div id="sidebar-right-tab3" class="tab-pane"></div>
          </div> 
          <!-- end: .tab-content -->
        </div>

      </div>
    </aside>
    <!-- End: Right Sidebar --> 
