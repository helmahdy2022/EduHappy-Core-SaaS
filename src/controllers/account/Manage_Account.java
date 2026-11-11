package controllers.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import com.sendpulse.util.Subscribers;

import model.beans.account.Account_Details;
import model.dbHelpers.account.AccountManagement;
import model.dbHelpers.teacher.FileUploadManagement;


/**
 * Servlet implementation class Manage_Account
 */
@WebServlet("/Manage_Account")
public class Manage_Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manage_Account() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		long totalFilesSize = 1024*1024*50;
		long fileSize = 1024*1024*10;
		int fileQty = 10;
		@SuppressWarnings("unchecked")
		List<FileItem> items = (List<FileItem>) request.getAttribute("items");
		String appPath = request.getServletContext().getInitParameter("UploadedFilesPath");	
		ArrayList<String> uploadMessage = new ArrayList<String>();
		HttpSession session = request.getSession();
		String message = null;
		/////////////////////////////////Employee Details to be inserted in Employee table/////////////////////////////////////
		int payroll_id = 0;
		if(request.getParameter("Payroll_id")!=null && !request.getParameter("Payroll_id").equals(""))
		{
			payroll_id = Integer.parseInt(request.getParameter("Payroll_id"));
		}		
		int department_id = 0;
		if(request.getParameter("Department_id")!=null && !request.getParameter("Department_id").equals(""))
		{
			department_id = Integer.parseInt(request.getParameter("Department_id"));
		}		
		String designation = request.getParameter("Designation");
		String joining_Date = request.getParameter("Joining_Date");
		double initial_Basic = 0.0;
		if(request.getParameter("Initial_Basic")!=null && !request.getParameter("Initial_Basic").equals(""))
		{
			initial_Basic = Double.parseDouble(request.getParameter("Initial_Basic"));
		}		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String first_Name = request.getParameter("First_Name");
		String last_Name = request.getParameter("Last_Name");
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		String organization = request.getParameter("Organization");
		int country_id = Integer.parseInt(request.getParameter("Country_id"));
		String city = request.getParameter("City");
		int pO_Box = 0;
		if(request.getParameter("PO_Box")!=null && !request.getParameter("PO_Box").equals(""))
		{
			pO_Box = Integer.parseInt(request.getParameter("PO_Box"));	
		}			
		String full_Address = request.getParameter("Full_Address");	
		String gender = request.getParameter("Gender");
		String tel = request.getParameter("Tel");
		String mobile = request.getParameter("Mobile");
		String birth_Date = request.getParameter("Birth_Date");
		Account_Details account_Details;
		if(session.getAttribute("Account_Details")==null)
		{
			account_Details = new Account_Details();
			account_Details.setEmail(email);
		}
		else
		{
			account_Details = (Account_Details) session.getAttribute("Account_Details");
			account_Details.setEmail(account_Details.getEmail());
		}
		
		account_Details.setPayroll_id(payroll_id);
		account_Details.setDepartment_id(department_id);
		account_Details.setDesignation(designation);
		account_Details.setJoining_Date(joining_Date);
		account_Details.setInitial_Basic(initial_Basic);
		account_Details.setOrganization(organization);
		account_Details.setCountry_id(country_id);
		account_Details.setCity(city);
		account_Details.setPO_Box(pO_Box);
		account_Details.setFull_Address(full_Address);
		account_Details.setGender(gender);
		account_Details.setTel(tel);
		account_Details.setMobile(mobile);
		account_Details.setBirth_Date(birth_Date);
		account_Details.setFirst_Name(first_Name);
		account_Details.setLast_Name(last_Name);
		account_Details.setPassword(password);	
		///////////Get base URL //////////
		StringBuffer url = request.getRequestURL();
		if(url!=null && url.toString().contains(".com") && !url.toString().contains("https"))
		{
			url.replace(0, 4, "https");
		}
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String base = url.substring(0, url.length() - uri.length() + ctx.length());
		String partial_base = url.substring(0, url.length() - uri.length());
		//////////////////////////////////
		AccountManagement accountManagement = new AccountManagement();
		String address = null;
		String terms = request.getParameter("Terms");
		if(account_Details.getAccount_id()==0)
		{
			if(terms!=null&&!terms.equals(""))
			{
				message = accountManagement.createAccount(account_Details, base, partial_base);
				try {
					Subscribers subscriber = new Subscribers();
					subscriber.AddSubscriber(account_Details.getFirst_Name(), account_Details.getEmail());
				}
				catch(Exception e){
					
				}
				address = "/Account/Display_Signup.jsp?message="+message;
			}
			else
			{
				message="You have to agree to the terms and conditions as well as the privacy policy.";
			}

		}
		else
		{
			if(email!=null)
			{
				message="Sorry, you are trying to create new account while you are logged in into another account. Please log out first and then try again.";
				address = "/Account/Display_Signup.jsp?message="+message;
			}
			else
			{
				message = accountManagement.updateAccount(account_Details);
		    	///////////////////////////deal with file upload here////////////////////////////////////////////////		    	
		    	try {
					uploadMessage = FileUploadManagement.fileUpload(appPath, account_Details.getAccount_id(), -1 , "Admins","Photos", "Admin_id", null, account_Details.getAdmin_id(), -1 ,items, totalFilesSize, fileSize, fileQty);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	    	
		    	account_Details = accountManagement.getAccountDetails(account_Details.getAccount_id());
		    	session.setAttribute("Account_Details", account_Details);
			    request.setAttribute("message", message);
				request.setAttribute("uploadMessage", uploadMessage);
				address = "/Account/Display_Account_Profile.jsp";
			}
		}
  		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
  		dispatcher.forward(request, response);
	}

}
