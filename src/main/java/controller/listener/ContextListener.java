package controller.listener;

import database.connection_pool.HikariCP;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            HikariCP.getConnection();
        } catch (SQLException e) {
            LOG.error("SQLException error while initializing context", e);
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            HikariCP.close();
        } catch (SQLException e) {
            LOG.error("SQLException error while destroying context", e);
            e.printStackTrace();
        }
    }
}