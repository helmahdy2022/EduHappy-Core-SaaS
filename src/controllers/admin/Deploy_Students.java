package controllers.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.admin.Students;
import model.dbHelpers.admin.YearEndProcedures;

/**
 * Servlet implementation class Deploy_Student
 */
@WebServlet("/Deploy_Students")
public class Deploy_Students extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deploy_Students() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int student_id=0;
		int grade_id = Integer.parseInt(request.getParameter("Grade_id"));
		int class_id = Integer.parseInt(request.getParameter("Class_id"));
		YearEndProcedures yearEndProcedures = new YearEndProcedures();
		Students Students = new Students();
		Students = yearEndProcedures.getGradeStudents(grade_id);
		for(int s_id : Students.getStudent_id())
		{

			if(request.getParameter("Student_id"+s_id)!=null)
			{
				student_id = Integer.parseInt(request.getParameter("Student_id"+s_id));
			}
			yearEndProcedures.updateStudentClass(student_id, class_id);
		}
		//PrintWriter out = response.getWriter();
		//out.print("<script>showGradeStudents("+grade_id+");</script>");
		String address; 
		address = "/Admin/YearEnd/Display_Deploy_Students.jsp?Grade_id="+grade_id;
		RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
