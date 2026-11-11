package com.paymob.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.account.Account_Details;
import model.beans.admin.Parent_Details;
import model.beans.parent.Login;
import model.dbHelpers.Parent.CheckLoginQuery;
import model.dbHelpers.admin.ParentManagement;

/**
 * Servlet implementation class Refresh_Parent
 */
@WebServlet("/Refresh_Parent")
public class Refresh_Parent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Refresh_Parent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int parent_id = Integer.parseInt(request.getParameter("Parent_id"));
		HttpSession session= request.getSession();
		session.setAttribute("Parent_Session", parent_id);
		Parent_Details parent_Details = new Parent_Details();
		ParentManagement parentManagement = new ParentManagement();
		parent_Details = parentManagement.getParent(parent_id);		
		CheckLoginQuery c = new CheckLoginQuery();
		Login login = new Login();
		login.setUsername(parent_Details.getUsername());
		login.setPassword(parent_Details.getPassword());
		//Login Parent_Old_Session = (Login) session.getAttribute("Parent_Login");
		Account_Details Parent_New_Account_Details =c.doCheckLogin(login);
		session.setAttribute("child",Parent_New_Account_Details.getChild());
		session.setAttribute("Parent_Account_Details", Parent_New_Account_Details);
		String message = request.getParameter("message");
		String address = "/Parent/Child/Display_Add_Child.jsp?message="+message;
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
