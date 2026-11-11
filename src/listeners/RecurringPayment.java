package listeners;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.paymob.util.PaymentRenewal;

/**
 * Application Lifecycle Listener implementation class RecurringPayment
 *
 */
@WebListener
public class RecurringPayment implements ServletContextListener {
 
	private ScheduledExecutorService scheduler;
	
	/**
     * Default constructor. 
     */
    public RecurringPayment() {
        // TODO Auto-generated constructor stub
    }
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
 
    	final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Africa/Cairo"));
    	ZonedDateTime nextRun = now.withHour(11).withMinute(0).withSecond(0);    
    	if (now.compareTo(nextRun) > 0) {
    		nextRun = nextRun.plusDays(1);
    	}
    	final Duration initialDelay = Duration.between(now, nextRun);

    	System.out.println("Time until first run: " + initialDelay);
    	System.out.println("Now: " + now);
    	
    	scheduler = Executors.newSingleThreadScheduledExecutor();
    	scheduler.scheduleAtFixedRate(new PaymentRenewal(), initialDelay.toMillis(), Duration.ofDays(1).toMillis(), TimeUnit.MILLISECONDS);
    }
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    	scheduler.shutdownNow();
    }
	
}
