package filters.parent;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Servlet Filter implementation class ParentSecurity
 */
@WebFilter("/ParentSecurity")
public class ParentSecurity implements Filter {

    /**
     * Default constructor. 
     */
    public ParentSecurity() {
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

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session= req.getSession(); 
		int Parent_id=0;
		if(session.getAttribute("Parent_Session")!=null)
		{
			Parent_id = (Integer) session.getAttribute("Parent_Session");

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
		if(Parent_id==0)
		{

			String urlPage = req.getServletPath();
			String ctx = req.getContextPath();
			if(urlPage!=null && !urlPage.equalsIgnoreCase("/Parent/Parent_Login.jsp")&&!urlPage.equalsIgnoreCase("/Academy/Parent_Signup.jsp")&&!urlPage.equalsIgnoreCase("/Parent/Parent_Forgot_Password.jsp")&&!urlPage.equalsIgnoreCase("/Parent/Parent_Reset_Password.jsp"))
			{
	   	 		StringBuffer url =  req.getRequestURL();
   	   	   	  	if(url!=null && url.toString().contains(".com") && !url.toString().contains("https"))
   	   			{
   	   	   	  		url.replace(0, 4, "https");
   	   			}   	   	  		
   	   	   	  	String uri = req.getRequestURI();
   	   	   	  	String base = url.substring(0, url.length() - uri.length() + ctx.length());
   				String address = base+"/Parent/Parent_Login.jsp?url="+urlPage;  	  
   				res.sendRedirect(address);
			}			
/*			String url = req.getServletPath();
			RequestDispatcher dispatcher =	 request.getRequestDispatcher("/Parent/Parent_Login.jsp?url="+url);
			dispatcher.forward(request, response);*/
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
