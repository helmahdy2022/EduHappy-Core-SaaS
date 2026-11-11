package filters.student;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Servlet Filter implementation class StudentSecurity
 */
@WebFilter("/StudentSecurity")
public class StudentSecurity implements Filter {

    /**
     * Default constructor. 
     */
    public StudentSecurity() {
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
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session= req.getSession(); 
		int Student_id=0;
		if(session.getAttribute("Student_Session")!=null || request.getParameter("Student_id")!=null)
		{
			if(session.getAttribute("Student_Session")!=null)
				Student_id = (Integer) session.getAttribute("Student_Session");
			if(request.getParameter("Student_id")!=null )
				Student_id = Integer.parseInt(request.getParameter("Student_id"));
			// Check the user session for the salt cache, if none is present we create one
	        @SuppressWarnings("unchecked")
			Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>)
	        		session.getAttribute("csrfPreventionSaltCache");

	        if (csrfPreventionSaltCache == null){
	            csrfPreventionSaltCache = CacheBuilder.newBuilder()
	                .maximumSize(5000)
	                .expireAfterWrite(120, TimeUnit.MINUTES)
	                .build();

	            session.setAttribute("csrfPreventionSaltCache", csrfPreventionSaltCache);
	        }

	        // Generate the salt and store it in the users cache
	        String salt = RandomStringUtils.random(20, 0, 0, true, true, null, new SecureRandom());
	        csrfPreventionSaltCache.put(salt, Boolean.TRUE);

	        // Add the salt to the current request so it can be used
	        // by the page rendered in this request
	        //req.setAttribute("csrfPreventionSalt", salt);		
	        req.getServletContext().setAttribute("csrfPreventionSalt", salt);
		}
		if(Student_id==0 && session.getAttribute("Parent_Session")==null)
		{
			String url = req.getServletPath();
			RequestDispatcher dispatcher =	 request.getRequestDispatcher("/Student/Student_Login.jsp?url="+url);
			dispatcher.forward(request, response);
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
