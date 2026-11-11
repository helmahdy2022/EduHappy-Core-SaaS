package filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.google.common.cache.Cache;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminIllegalOperation
 */
@WebFilter("/AdminIllegalOperation")


public class IllegalOperation implements Filter {

    /**
     * Default constructor. 
     */
    public IllegalOperation() {
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		HttpServletRequest req = (HttpServletRequest) request;
		String salt = null;
		List<FileItem> items = null;
		HttpServletRequest httpRequest = null;
        if (ServletFileUpload.isMultipartContent(req)) 
        {
            // If not, then return the request unchanged.
    		try {
    			FileItemFactory factory = new DiskFileItemFactory();
    			ServletFileUpload upload = new ServletFileUpload(factory);
    			upload.setHeaderEncoding("UTF-8"); 
    			items = upload.parseRequest(req);
    			//items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
    			for (FileItem item : items) 
    	        {               
    				if (item.isFormField()) 
    	            {
    	                String fieldName = item.getFieldName();
    	                String fieldValue = item.getString();
    	                if(fieldName.equals("csrfPreventionSalt"))
    	                	salt = fieldValue;
    	            }
    	        }
    		} catch (FileUploadException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

            // Parse HttpServletRequest.
            httpRequest = parseRequest(req, items);
        }
        else
        {
        	salt = (String) req.getParameter("csrfPreventionSalt");
        	httpRequest = req;
        }
        //items.add(request.getParameter("Student_Resized_Photo"));
        httpRequest.setAttribute("items", items);

        // Validate that the salt is in the cache
        @SuppressWarnings("unchecked")
		Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>) req.getSession().getAttribute("csrfPreventionSaltCache");
        if(salt!=null&&csrfPreventionSaltCache!=null&&csrfPreventionSaltCache.getIfPresent(salt)==null)
        {
        	csrfPreventionSaltCache.put(salt, Boolean.TRUE);
        }
        String path = req.getServletPath();
		if (	path.startsWith("/Account")
				||path.contains("Activate_Account")
				||path.contains("Activate_Parent_Account")
				||path.contains("Manage_Parent_Account")
				||path.startsWith("/Admin")
				|| path.startsWith("/Teacher")
				|| path.startsWith("/Student")
				|| path.startsWith("/Parent")
				|| path.startsWith("/Academy")
				|| path.contains("/Subscription_Response")
				|| path.contains("/Subscription_Listener")
				|| path.contains("/Enroll_Child")
				|| path.contains("/Payment_Response")
				|| path.contains("/Manage_Payment")	
				|| path.contains("/Refresh_Parent")
				|| path.contains("/Manual_Renewal")
				|| path.contains("/Get_Due_Renewal")
				|| path.contains("/Subscription_Cancel")
				|| path.contains("/EduHappy_The_Complete_Guide.pdf")
				|| path.contains("/EduHappy.mp4")
				|| path.contains("/Reset_Password.jsp")
				|| path.contains("/Reset_Password")
				|| path.contains("/Forgot_Password")
				|| path.contains("/Parent_Reset_Password.jsp")
				|| path.contains("/Parent_Reset_Password")
				|| path.contains("/Parent_Forgot_Password")
				|| path.contains("/Destroy_Session")
				|| req.getDispatcherType().toString().equalsIgnoreCase("FORWARD")
				|| (req.getMethod().equalsIgnoreCase("GET")&&req.getHeader("referer")!=null)
				|| (req.getMethod().equalsIgnoreCase("POST") && ((csrfPreventionSaltCache != null  && salt != null && csrfPreventionSaltCache.getIfPresent(salt) != null)||req.getHeader("referer").contains("/Account/Signup.jsp"))))
		
		{			
            // Continue with filter chain.
			HttpSession session= req.getSession(); 
			if(
					session.getAttribute("Admin_Session")!=null 
					|| path.contains("AdminLogin")||path.contains("css")
					||path.contains("jquery-1.11.3.js") ||path.contains("matrix.login.js") ||path.contains("jquery.min.js") || path.contains("ProcessPhoto.js") || path.contains("exif.js") 
					||path.contains("images")|| path.contains("img") || path.contains("fonts") || path.contains("font-awesome")
					|| path.contains("Admin_Login.jsp")
					|| session.getAttribute("Teacher_Session")!=null || path.contains("TeacherLogin")||  path.contains("Teacher_Login.jsp")
					|| session.getAttribute("Student_Session")!=null || path.contains("StudentLogin")||  path.contains("Student_Login.jsp")
					|| session.getAttribute("Parent_Session")!=null || path.contains("ParentLogin")||  path.contains("Parent_Login.jsp")					
					|| path.contains("Account")
					|| path.contains("Academy")
					|| path.contains("/Subscription_Response")
					|| path.contains("/Subscription_Listener")
					||path.contains("Manage_Parent_Account")
					|| path.contains("/Enroll_Child")
					|| path.contains("/Payment_Response")
					|| path.contains("/Manual_Renewal")
					|| path.contains("/Get_Due_Renewal")
					|| path.contains("/Subscription_Cancel")
					|| path.contains("/EduHappy_The_Complete_Guide.pdf")
					|| path.contains("/EduHappy.mp4")
					|| path.contains("/List_Countries")
					|| path.contains("/Global/Display_Countries.jsp")
					|| path.contains("/Check_Username")
					|| path.contains("/Forgot_Password")
					|| path.contains("/Reset_Password")
					|| path.contains("/Display_Forgot_Password.jsp")
					|| path.contains("/Display_Reset_Password.jsp")
					|| path.contains("/Parent_Reset_Password")
					|| path.contains("/Parent_Forgot_Password"))
			{
				chain.doFilter(httpRequest, response);
			}
				
			else
			{
				PrintWriter out= response.getWriter();
				out.print("SessionExpired");
			}
		} 
		else 
		{			
			if(req.getMethod().equalsIgnoreCase("POST"))
			{				
				String referer = req.getHeader("referer");
				if(referer!=null)
				{
					String address = null;
					if(
							referer.contains("/Admin/")
							||referer.contains("Get_Teacher_Details")
							||referer.contains("Get_Student_Details")
							||referer.contains("Subscription_Details")
							||referer.contains("/AdminLogin"))
						address = "/Admin/Admin_Login.jsp";
					if(referer.contains("/Teacher/"))
						address = "/Teacher/Teacher_Login.jsp";
					if(referer.contains("/Student/"))
						address = "/Student/Student_Login.jsp";
					if(referer.contains("/Parent/"))
						address = "/Parent/Parent_Login.jsp";
					if(address!=null)
					{
						RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				  		dispatcher.forward(request, response);
					}
					else
					{
						PrintWriter out= response.getWriter();
						out.print("Session Expired");
						out.print("referer="+referer);
					}
				}								
			}
			else
			{
				PrintWriter out= response.getWriter();
				out.print("Illegal Operation");
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	
    private HttpServletRequest parseRequest(HttpServletRequest request, List<FileItem> multipartItems) throws ServletException, UnsupportedEncodingException {

        // Prepare the request parameter map.
        Map<String, String[]> parameterMap = new HashMap<String, String[]>();
        // Loop through multipart request items.
        for (FileItem multipartItem : multipartItems) {
            if (multipartItem.isFormField()) {
                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                processFormField(multipartItem, parameterMap);
            } else {
                // Process form file field (input type="file").
                processFileField(multipartItem, request);
            }
        }
        // Wrap the request with the parameter map which we just created and return it.
        return wrapRequest(request, parameterMap);
    }

    /**
     * Process multipart request item as regular form field. The name and value of each regular
     * form field will be added to the given parameterMap.
     * @param formField The form field to be processed.
     * @param parameterMap The parameterMap to be used for the HttpServletRequest.
     * @throws UnsupportedEncodingException 
     */
    private void processFormField(FileItem formField, Map<String, String[]> parameterMap) throws UnsupportedEncodingException {
        String name = formField.getFieldName();
        String value = formField.getString("UTF-8");
        String[] values = parameterMap.get(name);

        if (values == null) {
            // Not in parameter map yet, so add as new value.
            parameterMap.put(name, new String[] { value });
        } else {
            // Multiple field values, so add new value to existing array.
            int length = values.length;
            String[] newValues = new String[length + 1];
            System.arraycopy(values, 0, newValues, 0, length);
            newValues[length] = value;
            parameterMap.put(name, newValues);
        }
    }

    /**
     * Process multipart request item as file field. The name and FileItem object of each file field
     * will be added as attribute of the given HttpServletRequest. If a FileUploadException has
     * occurred when the file size has exceeded the maximum file size, then the FileUploadException
     * will be added as attribute value instead of the FileItem object.
     * @param fileField The file field to be processed.
     * @param request The involved HttpServletRequest.
     */
    private void processFileField(FileItem fileField, HttpServletRequest request) {
        if (fileField.getName().length() <= 0) {
            // No file uploaded.
            request.setAttribute(fileField.getFieldName(), null);
        }
        else 
        {
            // File uploaded with good size.
            request.setAttribute(fileField.getFieldName(), fileField);
        }
    }

    // Utility (may be refactored to public utility class) ----------------------------------------

    /**
     * Wrap the given HttpServletRequest with the given parameterMap.
     * @param request The HttpServletRequest of which the given parameterMap have to be wrapped in.
     * @param parameterMap The parameterMap to be wrapped in the given HttpServletRequest.
     * @return The HttpServletRequest with the parameterMap wrapped in.
     */
    private static HttpServletRequest wrapRequest(
        HttpServletRequest request, final Map<String, String[]> parameterMap)
    {
        return new HttpServletRequestWrapper(request) {
            public Map<String, String[]> getParameterMap() {
                return parameterMap;
            }
            public String[] getParameterValues(String name) {
                return parameterMap.get(name);
            }
            public String getParameter(String name) {
                String[] params = getParameterValues(name);
                return params != null && params.length > 0 ? params[0] : null;
            }
            public Enumeration<String> getParameterNames() {
                return Collections.enumeration(parameterMap.keySet());
            }
        };
    }

}
