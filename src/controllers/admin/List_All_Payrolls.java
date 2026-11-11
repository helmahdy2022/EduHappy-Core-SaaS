package controllers.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.beans.admin.Payroll_List;
import model.dbHelpers.admin.PayrollManagement;

/**
 * Servlet implementation class List_All_Payrolls
 */
@WebServlet("/List_All_Payrolls")
public class List_All_Payrolls extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_All_Payrolls() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//HttpSession session= request.getSession();
		//int admin_id = (Integer) session.getAttribute("Admin_Session");	
		//AccountManagement accountManagement = new AccountManagement();
		//int account_id = accountManagement.getAdminAccount(admin_id);
		PayrollManagement payrollManagement = new PayrollManagement();
		
		java.util.List<String> schoolList = new ArrayList<String>();
		schoolList = Arrays.asList(request.getParameter("SchoolList").split(","));
		Payroll_List Payroll_List = new Payroll_List(); 
		Payroll_List = payrollManagement.getAllPayrolls(schoolList);
	    request.setAttribute("Payroll_List", Payroll_List);
		String address = "/Admin/HR/Display_All_Payrolls.jsp";
	    RequestDispatcher dispatcher =	 request.getRequestDispatcher(address);
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
