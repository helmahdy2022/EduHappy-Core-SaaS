<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!--sidebar-menu-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> Menu</a>
 	<div style="text-align: center; margin-top: 50px; padding-bottom:30px; color: #fff; border-bottom: 1px solid #4d4d4d;">
	  <a href="#">
				<c:choose>
					<c:when test="${not empty Account_Details.fileName}">
								<img src="${pageContext.request.contextPath}/uploadFiles/Accounts/${Account_Details.account_id}/Admins/Photos/${Account_Details.admin_id}/${Account_Details.fileName}" width="80" class="img-circle">
					</c:when>
					<c:otherwise>
								<img src="${pageContext.request.contextPath}/images/Add_Photo.jpg"  width="80" class="img-circle">
					</c:otherwise>
				</c:choose>
	  </a>
	  <br/><span class="text">${Account_Details.first_Name} ${Account_Details.last_Name}</span>
  </div>
  <ul>
    <li 
	    <c:if test="${fn:contains(pageContext.request.servletPath, 'Admin_Main.jsp')}">
	     class="active" 
	    </c:if>        
    ><a href="${pageContext.request.contextPath}/Admin/Admin_Main.jsp" onclick="overlayOn();"><i class="icon icon-home"></i> <span>Dashboard</span></a> </li>

    <c:if test="${Account_Details.account_Owner==1}">
    <li 
    <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'New_School.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_School.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Manage_School')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> > 
    <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/school_icon.png"> <span>Schools </span></a>
      <ul>
		<li><a href="${pageContext.request.contextPath}/Admin/Schools/New_School.jsp" onclick="overlayOn();">New School</a></li>        
		<li><a href="${pageContext.request.contextPath}/Admin/Schools/Edit_School.jsp" onclick="overlayOn();">Edit School</a></li>             
      </ul>      
    </li>
	</c:if>



     <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Academic_Year'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">	
    <li   
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'New_School_Year.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_School_Year.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Insert_School_Year.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'New_Semester.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_Semester.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Manage_Year_Semester.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'New_Holiday.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Insert_Holiday.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'New_Daysoff.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_Daysoff.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Insert_Daysoff.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> >    
    <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/academicyear_icon.png"> <span>Academic Year</span></a>
      <ul>       
        <li><a href="${pageContext.request.contextPath}/Admin/Academic_Year/New_School_Year.jsp" onclick="overlayOn();">New Academic Year</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Academic_Year/Edit_School_Year.jsp" onclick="overlayOn();">Edit Academic Year</a></li>        
        <li><a href="${pageContext.request.contextPath}/Admin/Academic_Year/New_Semester.jsp" onclick="overlayOn();">New Year Semester</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Academic_Year/Edit_Semester.jsp" onclick="overlayOn();">Edit Year Semester</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Academic_Year/New_Holiday.jsp" onclick="overlayOn();">Holidays</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Academic_Year/New_Daysoff.jsp" onclick="overlayOn();">New Days-Off</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Academic_Year/List_Daysoff.jsp" onclick="overlayOn();">Edit Days-Off</a></li>        
      </ul>
      </li>
      </c:if>

    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Grades'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">	
    <li
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'New_Grade.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_Grade.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Manage_Grade.jsp')	    
	    ||fn:contains(pageContext.request.servletPath, 'Grade_Terms.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Dsiplay_Insert_Grade_Terms.jsp')	    
	    ||fn:contains(pageContext.request.servletPath, 'New_Grade_Semester.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_Grade_Semester.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Manage_Grade_Semester.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'New_Grading_Scale.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_Grading_Scale.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Manage_Grading_Scales.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Exam_Schedule.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Manage_Exam_Schedule.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> >   
     
     <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/grades_icon.png"> <span>Grades</span></a>
      <ul>
        <li><a href="${pageContext.request.contextPath}/Admin/Grades/New_Grade.jsp" onclick="overlayOn();">New Grade</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Grades/Edit_Grade.jsp" onclick="overlayOn();">Edit Grade</a></li>        
        <li><a href="${pageContext.request.contextPath}/Admin/Grades/Grade_Terms.jsp" onclick="overlayOn();">Grade Terms</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Grades/New_Grade_Semester.jsp" onclick="overlayOn();">New Grade Semesters</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Grades/Edit_Grade_Semester.jsp" onclick="overlayOn();">Edit Grade Semesters</a></li>        
		<li><a href="${pageContext.request.contextPath}/Admin/Grades/New_Grading_Scale.jsp" onclick="overlayOn();">New Grading Scale</a></li>
		<li><a href="${pageContext.request.contextPath}/Admin/Grades/Edit_Grading_Scale.jsp" onclick="overlayOn();">Manage Grading Scales</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Grades/Exam_Schedule.jsp" onclick="overlayOn();">Exam Schedule</a></li>
      </ul>
    </li>
    </c:if>

    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Classes'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">	    
     <li
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'New_Class.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_Class.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Manage_Class.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> >  
      
      <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/classroom_icon.png"> <span>Classrooms</span></a>
      <ul>
        <li><a href="${pageContext.request.contextPath}/Admin/Classes/New_Class.jsp" onclick="overlayOn();">New Classroom</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Classes/Edit_Class.jsp" onclick="overlayOn();">Edit Classroom</a></li>
      </ul>
    </li>
    </c:if>
 
 
    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Virtual_Classrooms'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">	    
     <li
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'Virtual_Classrooms.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> >  
      
      <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/classroom_icon.png"> <span>Virtual Classrooms</span></a>
      <ul>
        <li><a href="${pageContext.request.contextPath}/Admin/Virtual_Classrooms/Virtual_Classrooms.jsp" onclick="overlayOn();">Virtual Classrooms</a></li>
      </ul>
    </li>
    </c:if>
    
        
    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Courses'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">	    
    <li
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'New_Course.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_Course.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Manage_Course.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'New_Group.jsp')	    
	    ||fn:contains(pageContext.request.servletPath, 'Edit_Group.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Manage_Group.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Manage_Electives.jsp')	    
	    ||fn:contains(pageContext.request.servletPath, 'Semester_Exam.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> >  
     <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/courses_icon.png"> <span>Courses</span></a>
      <ul>
        <li><a href="${pageContext.request.contextPath}/Admin/Courses/New_Course.jsp" onclick="overlayOn();">New Course</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Courses/Edit_Course.jsp" onclick="overlayOn();">Edit Course</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Courses/New_Group.jsp" onclick="overlayOn();">Create Group</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Courses/Edit_Group.jsp" onclick="overlayOn();">Manage Groups</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Courses/Manage_Electives.jsp" onclick="overlayOn();">Manage Electives</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Courses/Semester_Exam.jsp" onclick="overlayOn();">Semester Exam</a></li>
      </ul>
    </li>
    </c:if>
    
    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Divisions'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">	    
    <li 
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'New_Division.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_Division.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Manage_Division.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> >
    
    <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/divisions_icon.png"> <span>Divisions</span></a>
      <ul>
        <li><a href="${pageContext.request.contextPath}/Admin/Divisions/New_Division.jsp" onclick="overlayOn();">New Division</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Divisions/Edit_Division.jsp" onclick="overlayOn();">Edit Divisions</a></li>
      </ul>
    </li>
    </c:if>

    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Students'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">
    <li 
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'New_Student.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Search_Student.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_Student.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Student_Groups.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Student_Electives.jsp')	    
	    ||fn:contains(pageContext.request.servletPath, 'Grading_Reports.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Search_Graduated_Student.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> >
    <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/students_icon.png"> <span>Students</span></a>
      <ul>
        <li><a href="${pageContext.request.contextPath}/Admin/Students/New_Student.jsp" onclick="overlayOn();">New Student</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Students/Search_Student.jsp" onclick="overlayOn();">Manage Students</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Students/Student_Groups.jsp" onclick="overlayOn();">Students Groups</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Students/Student_Electives.jsp" onclick="overlayOn();">Students Electives</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Students/Grading_Reports.jsp" onclick="overlayOn();">Grading Reports</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Students/Search_Graduated_Student.jsp" onclick="overlayOn();">Graduated Students</a></li>        
      </ul>
    </li>
    </c:if>
 
     <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Parents'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">
    <li 
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'New_Parent.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Search_Parent.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_Parent.jsp')    
	    ||fn:contains(pageContext.request.servletPath, 'Link_Parent_Student.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Remove_Link_Parent_Student')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> >
    <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/students_icon.png"> <span>Parents</span></a>
      <ul>
        <li><a href="${pageContext.request.contextPath}/Admin/Parents/New_Parent.jsp" onclick="overlayOn();">New Parent</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Parents/Search_Parent.jsp?Page=Edit_Parent.jsp" onclick="overlayOn();">Manage Parents</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Parents/Search_Parent.jsp?Page=Link_Parent_Student.jsp" onclick="overlayOn();">Link Parent to Student</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Parents/Search_Parent.jsp?Page=Remove_Link_Parent_Student" onclick="overlayOn();">Remove Link Parent to Student</a></li>
      </ul>
    </li>
    </c:if>
    
    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Teachers'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">    
    <li 
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'List_Teachers')
	    ||(fn:contains(pageContext.request.servletPath, 'New_Employee.jsp')&&param.emp=='teacher')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Teacher.jsp')	
	    ||fn:contains(pageContext.request.servletPath, 'Display_Teacher_Assign_Grades.jsp')	
	    ||fn:contains(pageContext.request.servletPath, 'Display_Manage_Teacher_Grades.jsp')	    
	    ||fn:contains(pageContext.request.servletPath, 'Display_Teacher_Assign_Classes.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Manage_Teacher_Classes.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Teacher_Assign_Courses.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Manage_Teacher_Courses.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Teacher_Assign_Content.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Manage_Teacher_Content.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Authority_Report.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> >   
    <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/teachers_icon.png"> <span>Teachers</span></a>
      <ul>
<%--         <li><a href="${pageContext.request.contextPath}/Admin/Teachers/New_Teacher.jsp">New Teacher</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Teachers/Search_Teacher.jsp?Page=Edit_Teacher">Manage Teachers</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Teachers/Search_Teacher.jsp?Page=Assign_Grades">Assign Grade</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Teachers/Search_Teacher.jsp?Page=Assign_Classes">Assign Classroom</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Teachers/Search_Teacher.jsp?Page=Assign_Courses">Assign Course</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Teachers/Search_Teacher.jsp?Page=Assign_Content">Assign Content</a></li> --%>

		<li><a href="${pageContext.request.contextPath}/Admin/HR/New_Employee.jsp?emp=teacher" onclick="overlayOn();">Add Teacher</a></li>
        <li><a href="${pageContext.request.contextPath}/List_Teachers?Page=Assign_Grades" onclick="overlayOn();">Assign Grade</a></li>
        <li><a href="${pageContext.request.contextPath}/List_Teachers?Page=Assign_Classes" onclick="overlayOn();">Assign Classroom</a></li>
        <li><a href="${pageContext.request.contextPath}/List_Teachers?Page=Assign_Courses" onclick="overlayOn();">Assign Course</a></li>
        <li><a href="${pageContext.request.contextPath}/List_Teachers?Page=Assign_Content" onclick="overlayOn();">Assign Content</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Teachers/Authority_Report.jsp" onclick="overlayOn();">Authority Report</a></li>        
      </ul>
    </li>
    </c:if>
    
    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Schedule'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">    
    <li
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'New_Sessions.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Insert_Sessions.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'New_Class_Schedule.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> >
     <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/schedule_icon.png"> <span>Schedule</span></a>
      <ul>
        <li><a href="${pageContext.request.contextPath}/Admin/Schedule/New_Sessions.jsp" onclick="overlayOn();">Manage Sessions</a></li>
        <li><a href="${pageContext.request.contextPath}/Admin/Schedule/New_Class_Schedule.jsp" onclick="overlayOn();">Build Schedule</a></li>
      </ul>
    </li>
	</c:if>

    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='YearEnd'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">
    <li 
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'Final_Grading.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Final_Grading_Report.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Promote_Students.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Deploy_Students.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Previous_Year_Grading_Report.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> > 
    <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/yearend_icon.png"> <span>Year End Procedures</span></a>
      <ul>
				<li><a href="${pageContext.request.contextPath}/Admin/YearEnd/Final_Grading.jsp" onclick="overlayOn();">Produce Final Grading</a></li>
				<li><a href="${pageContext.request.contextPath}/Admin/YearEnd/Final_Grading_Report.jsp" onclick="overlayOn();">Final Grading Report</a></li>
				<li>
				<c:choose>
				<c:when test="${Account_Details.account_id!='71'}">
				<a href="${pageContext.request.contextPath}/Admin/YearEnd/Promote_Students.jsp" onclick="overlayOn();">Promote Students</a>
				</c:when>
				<c:otherwise>
				<a href="" onclick="overlayOn();">Promote Students</a>
				</c:otherwise>
				</c:choose>
				</li>
				<li><a href="${pageContext.request.contextPath}/Admin/YearEnd/Deploy_Students.jsp" onclick="overlayOn();">Assign Students to Classes</a></li>				
				<li><a href="${pageContext.request.contextPath}/Admin/YearEnd/Previous_Year_Grading_Report.jsp" onclick="overlayOn();">Previous Years Grading Reports</a></li>				      
      </ul>
    </li>
    </c:if>
    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Financial_Accounting'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">
    <li 
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'Manage_Financial_Accounts.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Chart_Of_Accounts.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Journal_Entry.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'General_Ledger.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Trial_Balance.jsp')	
	    ||fn:contains(pageContext.request.servletPath, 'Balance_Sheet.jsp')	
	    ||fn:contains(pageContext.request.servletPath, 'Income_Statement.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> > 
    <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/Accounting.png"> <span>Financial Accounting</span></a>
      <ul>
				<li><a href="${pageContext.request.contextPath}/Admin/Financial_Accounting/Manage_Financial_Accounts.jsp" onclick="overlayOn();">Manage Accounts</a></li>	
				<li><a href="${pageContext.request.contextPath}/Admin/Financial_Accounting/Chart_Of_Accounts.jsp" onclick="overlayOn();">Chart of Accounts</a></li>		
				<li><a href="${pageContext.request.contextPath}/Admin/Financial_Accounting/Journal_Entry.jsp" onclick="overlayOn();">Journal Entry</a></li>									      
				<li><a href="${pageContext.request.contextPath}/Admin/Financial_Accounting/General_Ledger.jsp" onclick="overlayOn();">General Ledger</a></li>
				<li><a href="${pageContext.request.contextPath}/Admin/Financial_Accounting/Trial_Balance.jsp" onclick="overlayOn();">Trial Balance</a></li>	
				<li><a href="${pageContext.request.contextPath}/Admin/Financial_Accounting/Balance_Sheet.jsp" onclick="overlayOn();">Balance Sheet</a></li>
				<li><a href="${pageContext.request.contextPath}/Admin/Financial_Accounting/Income_Statement.jsp" onclick="overlayOn();">Income Statement</a></li>												
      </ul>
    </li> 
 	</c:if>
 
    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='HR'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">
    <li 
    <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'Payroll.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Department.jsp')
	    ||(fn:contains(pageContext.request.servletPath, 'New_Employee.jsp')&&param.emp=='all')
	    ||fn:contains(pageContext.request.servletPath, 'List_All_Employees')
	    ||fn:contains(pageContext.request.servletPath, 'Display_Employees.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_Teacher.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Edit_Admin.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Leave.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Overtime.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Allowance.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Apply_Allowance.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Deduction.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Apply_Deduction.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Run_Payroll.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Payroll_Reports.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Promote_Employee.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> > 
    <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/HR_icon.png"> <span>HR and Payroll</span></a>
      <ul>
		<li><a href="${pageContext.request.contextPath}/Admin/HR/Payroll.jsp" onclick="overlayOn();">Manage Payroll</a></li>        
		<li><a href="${pageContext.request.contextPath}/Admin/HR/Department.jsp" onclick="overlayOn();">Manage Department</a></li>   
		<li><a href="${pageContext.request.contextPath}/Admin/HR/New_Employee.jsp?emp=all" onclick="overlayOn();">Add Employee</a></li>
		<li><a href="${pageContext.request.contextPath}/List_All_Employees" onclick="overlayOn();">Edit Employee</a></li>
		<li><a href="${pageContext.request.contextPath}/Admin/HR/Promote_Employee.jsp" onclick="overlayOn();">Employee Promotion</a></li>  		
		<li><a href="${pageContext.request.contextPath}/Admin/HR/Leave.jsp" onclick="overlayOn();">Leave Types</a></li> 
		<li><a href="${pageContext.request.contextPath}/Admin/HR/Apply_Leave.jsp" onclick="overlayOn();">Apply Leave</a></li> 
		<li><a href="${pageContext.request.contextPath}/Admin/HR/Overtime.jsp" onclick="overlayOn();">Apply Overtime</a></li> 
		<li><a href="${pageContext.request.contextPath}/Admin/HR/Allowance.jsp" onclick="overlayOn();">Allowance Types</a></li> 
		<li><a href="${pageContext.request.contextPath}/Admin/HR/Apply_Allowance.jsp" onclick="overlayOn();">Apply Allowance</a></li>
		<li><a href="${pageContext.request.contextPath}/Admin/HR/Deduction.jsp" onclick="overlayOn();">Deduction Types</a></li> 	
		<li><a href="${pageContext.request.contextPath}/Admin/HR/Apply_Deduction.jsp" onclick="overlayOn();">Apply Deduction</a></li>			 
		<li><a href="${pageContext.request.contextPath}/Admin/HR/Run_Payroll.jsp" onclick="overlayOn();">Run Payroll</a></li> 		
		<li><a href="${pageContext.request.contextPath}/Admin/HR/Payroll_Reports.jsp" onclick="overlayOn();">Payroll Reports</a></li> 		
<%-- 		<li><a href="${pageContext.request.contextPath}/Admin/Schools/Edit_School.jsp">End of Service</a></li>  --%>							 								
						          
      </ul>      
    </li>
	</c:if>    
    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Fees'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">    
    <li 
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'Fee_Types.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Fee_Structure.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Fee_Payments.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Fee_Collection.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> > 
    <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/Fees.png"> <span>Fees Management</span></a>
      <ul>
				<li><a href="${pageContext.request.contextPath}/Admin/Fees/Fee_Types.jsp" onclick="overlayOn();">Fee Types</a></li>
				<li><a href="${pageContext.request.contextPath}/Admin/Fees/Fee_Structure.jsp" onclick="overlayOn();">Fee Structure</a></li>
				<li><a href="${pageContext.request.contextPath}/Admin/Fees/Fee_Payments.jsp" onclick="overlayOn();">Fee Payments</a></li>
				<li><a href="${pageContext.request.contextPath}/Admin/Fees/Fee_Collection.jsp" onclick="overlayOn();">Fee Collection</a></li>							      
      </ul>
    </li> 
	</c:if>
    
    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Expenses'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">    
    <li 
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'Expense_Types.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Manage_Expenses.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> > 
    <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/Expenses.png"><span>Expenses Management</span></a>
      <ul>
				<li><a href="${pageContext.request.contextPath}/Admin/Expenses/Expense_Types.jsp" onclick="overlayOn();">Expense Types</a></li>
				<li><a href="${pageContext.request.contextPath}/Admin/Expenses/Manage_Expenses.jsp" onclick="overlayOn();">Manage Expenses</a></li>																
      </ul>
    </li>
	</c:if>
	
    <c:set var="Privileged" value="No" />
    <c:forEach items="${AdminPrivileges}" var="Featur_Name">
    	<c:if test="${Featur_Name=='Transport'}">
    		<c:set var="Privileged" value="Yes" />
        </c:if>
    </c:forEach>
    <c:if test="${Privileged=='Yes' || Account_Details.account_Owner==1}">	
    <li 
     <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'Manage_Vehicle.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Manage_Route.jsp')
	    ||fn:contains(pageContext.request.servletPath, 'Transport_Fees.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> > 
    <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/Transport.png"> <span>Transport Management</span></a>
      <ul>
				<li><a href="${pageContext.request.contextPath}/Admin/Transport/Manage_Vehicle.jsp" onclick="overlayOn();">Manage Vehicle</a></li>
				<li><a href="${pageContext.request.contextPath}/Admin/Transport/Manage_Route.jsp" onclick="overlayOn();">Manage Route</a></li>	
				<li><a href="${pageContext.request.contextPath}/Admin/Transport/Transport_Fees.jsp" onclick="overlayOn();">Transport Fees</a></li>						      
      </ul>
    </li>        
	</c:if>
    <c:if test="${Account_Details.account_Owner==1}">
    <li 
    <c:choose>
	    <c:when test="${
	    fn:contains(pageContext.request.servletPath, 'Admin_Privileges.jsp')}">
	     class="submenu active" 
	    </c:when>
	    <c:otherwise>
	      class="submenu"
	    </c:otherwise>
    </c:choose> > 
    <a href="#"><img src="${pageContext.request.contextPath}/img/icons/sidebar/privilege_icon.png"> <span>Admin Privilege </span></a>
      <ul>
		<li><a href="${pageContext.request.contextPath}/Admin/Privileges/Admin_Privileges.jsp" onclick="overlayOn();">Manage Admin Privileges</a></li>                   
      </ul>      
    </li>
	</c:if>	 
 	<c:choose>
		<c:when test="${Account_Details.subscription_Valid==true}">
			    <li class="content"> <span>Your subscription is valid until</span>
			      <div class="progress progress-mini active progress-striped">
			        <div style="width: 100%;" class="bar"></div>
			      </div>
			      <span class="percent"></span>
			      <div class="stat"><c:out value="${Account_Details.subscription_Expiry}"/></div>
			    </li>
		 </c:when>
		<c:otherwise>
		    <li class="content"> <span>Your trial is valid until</span>
		      <div class="progress progress-mini progress-danger active progress-striped">
		        <div style="width: ${fn:substringBefore((100-Account_Details.trialDays_Left/7*100),'.')}%;" class="bar"></div>
		      </div>
		      <span class="percent">
		      <c:out value="${fn:substringBefore(100-(Account_Details.trialDays_Left/7*100),'.')}%"/>	      
		      </span>
		      <div class="stat"><c:out value="${Account_Details.trial_Expiry}"/></div>
				You have <b>${Account_Details.trialDays_Left}</b> Day<c:if test="${Account_Details.trialDays_Left>1}">s</c:if> left 		      
		</c:otherwise>
	</c:choose>
  </ul>
</div>
<!--sidebar-menu-->
<c:if test="${!(

fn:contains(pageContext.request.servletPath, 'New_Class_Schedule.jsp')
||fn:contains(pageContext.request.servletPath, 'Semester_Exam.jsp') 
|| fn:contains(pageContext.request.servletPath, 'Final_Grading.jsp')
|| fn:contains(pageContext.request.servletPath, 'Deploy_Students.jsp')
|| fn:contains(pageContext.request.servletPath, 'Account_Profile.jsp')
)}">
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery.min.js"></script> 
</c:if>
<script src="${pageContext.request.contextPath}/Global/lib/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/excanvas.min.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery-ui.min.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery.ui.custom.js"></script>
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery.flot.min.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery.flot.resize.min.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery.peity.min.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/fullcalendar.min.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/matrix.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/matrix.dashboard.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery.gritter.min.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/matrix.interface.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/matrix.chat.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery.validate.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/matrix.form_validation.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery.wizard.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery.uniform.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/select2.min.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/matrix.popover.js"></script> 


<script src="${pageContext.request.contextPath}/Global/lib/js/matrix.tables.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/bootstrap-datepicker.js"></script> 

<script src="${pageContext.request.contextPath}/Global/lib/js/masked.js"></script>
<script src="${pageContext.request.contextPath}/Global/lib/js/matrix.form_common.js"></script>
<script src="${pageContext.request.contextPath}/Global/lib/js/bootstrap-colorpicker.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/bootstrap-wysihtml5.js"></script>
<c:if test="${!(
fn:contains(pageContext.request.servletPath, 'New_Class_Schedule.jsp')
||fn:contains(pageContext.request.servletPath, 'Semester_Exam.jsp') 
|| fn:contains(pageContext.request.servletPath, 'Final_Grading.jsp')
|| fn:contains(pageContext.request.servletPath, 'Deploy_Students.jsp')
|| fn:contains(pageContext.request.servletPath, 'Account_Profile.jsp')
)}">
<script src="${pageContext.request.contextPath}/Global/lib/jquery-1.11.3.js"></script> 
</c:if> 
<script src="${pageContext.request.contextPath}/Global/lib/js/jquery.dataTables.min.js"></script> 
<script src="${pageContext.request.contextPath}/Global/lib/js/dataTables.bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/Global/js-webshim/minified/polyfiller.js"></script>

<!-- <script src="//cdn.jsdelivr.net/webshim/1.14.5/polyfiller.js"></script>  --> 
 
<script type="text/javascript">
  // This function is called from the pop-up menus to transfer to
  // a different page. Ignore if the value returned is a null string:
  function goPage (newURL) {

      // if url is empty, skip the menu dividers and reset the menu selection to default
      if (newURL != "") {
      
          // if url is "-", it is this page -- reset the menu:
          if (newURL == "-" ) {
              resetMenu();            
          } 
          // else, send page to designated URL            
          else {  
            document.location.href = newURL;
          }
      }
  }

// resets the menu selection upon entry to this page:
function resetMenu() {
   document.gomenu.selector.selectedIndex = 2;
}
  
webshims.setOptions('forms-ext', {types: 'date'});
webshims.polyfill('forms forms-ext');
//webshims.cfg.no$Switch='true';
$.webshims.formcfg = {
en: {
    dFormat: '/',
    dateSigns: '/',
    patterns: {
        d: "mm/dd/yy"
    }
}
};
</script>