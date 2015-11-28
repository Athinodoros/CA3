
package xml;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Rihards
 */
@WebListener
public class PeriodicTask implements ServletContextListener{

    private ScheduledExecutorService scheduler;
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate((Runnable) new XmlReaderDemo().call(), 0, 24, TimeUnit.HOURS);  
                                                                                    ////change to 24 hours
        } catch (Exception ex) {
            Logger.getLogger(PeriodicTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }
    
}
