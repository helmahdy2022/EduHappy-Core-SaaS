package controllers.student;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.admin.Authority_Report;
import model.dbHelpers.admin.GradeManagement;
import model.dbHelpers.admin.StudentManagement;
import model.dbHelpers.admin.TeacherAuthorityManagement;

/**
 * Servlet implementation class Get_MyTeachers
 */
@WebServlet("/Get_MyTeachers")
public class Get_MyTeachers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_MyTeachers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int student_id = 0;
		String source = null;
		if(request.getParameter("Student_id")!=null && !request.getParameter("Student_id").equals(""))
		{
			student_id = Integer.parseInt(request.getParameter("Student_id"));
			source = "Parent";
		}
		else
		{
			student_id = (Integer) session.getAttribute("Student_Session");	
			source = "Student";
		}
		StudentManagement studentManagement = new StudentManagement();
		String sClass_id = studentManagement.getStudentClass(student_id);
		int class_id=0;
		if(sClass_id!=null)
		{
			class_id = Integer.parseInt(sClass_id);
			GradeManagement gradeManagement = new GradeManagement();
			int grade_id = gradeManagement.getGradeByClass(class_id);
			TeacherAuthorityManagement teacherAuthorityManagement = new TeacherAuthorityManagement();
			Authority_Report authority_Report = new Authority_Report();	
			authority_Report = teacherAuthorityManagement.getAuthorityPerGrade(grade_id, 0, class_id, student_id);
			request.setAttribute("MyTeachers", authority_Report);
		}		
		String address = "/Student/MyTeachers/Display_MyTeachers.jsp?Source="+source+"&&Student_id="+student_id;
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
