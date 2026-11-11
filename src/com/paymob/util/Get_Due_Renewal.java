package com.paymob.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.parent.Child;

/**
 * Servlet implementation class Get_Due_Renewal
 */
@WebServlet("/Get_Due_Renewal")
public class Get_Due_Renewal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Due_Renewal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		//int parent_id = 0 ;
		String address = null;
		if(session.getAttribute("Parent_Session")!=null)
		{
			int parent_id = (Integer) session.getAttribute("Parent_Session");
			ManualRenewal manualRenewal = new ManualRenewal();
			Child child = manualRenewal.GetDueRenewal(parent_id);
			request.setAttribute("ChildRenewal", child);	
			address = "/Parent/Subscriptions/Manual_Renewal.jsp";
		}
		else
		{
			String url = "/Get_Due_Renewal";
			address = "/Parent/Parent_Login.jsp?url="+url;
		}

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
