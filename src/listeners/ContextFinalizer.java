package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
/**
 * Application Lifecycle Listener implementation class ContextFinalizer
 *
 */
@WebListener
public class ContextFinalizer implements ServletContextListener {

	 private static final Logger LOGGER = LoggerFactory.getLogger(ContextFinalizer.class);
	 /**
     * Default constructor. 
     */
    public ContextFinalizer() {
        // TODO Auto-generated constructor stub

    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d = null;
        while(drivers.hasMoreElements()) 
        {
            try 
            {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
                LOGGER.warn(String.format("Driver %s deregistered", d));
            } 
            catch (SQLException ex) 
            {
                LOGGER.warn(String.format("Error deregistering driver %s", d), ex);
            }
        }
	        try 
	        {
	            AbandonedConnectionCleanupThread.shutdown();
	        } 
	        catch (InterruptedException e) 
	        {
	        	LOGGER.warn("SEVERE problem cleaning up: " + e.getMessage());
	            e.printStackTrace();
	        }
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
