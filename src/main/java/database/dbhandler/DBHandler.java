package database.dbhandler;

import entities.answer.Answer;
import database.connection_pool.Pool;
import org.apache.log4j.Logger;
import entities.question.Question;
import entities.test.Test;
import entities.user.Role;
import entities.user.Status;
import entities.user.User;
import javax.security.auth.login.LoginException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * This class connects to a database and implements all the methods from DBHandlerInterface.
 * @author Oleksandr Severhin
 */
public class DBHandler implements DBHandlerInterface {
    private static final Logger LOG = Logger.getLogger(DBHandler.class);
    private static DBHandler INSTANCE;
    private static Connection connection;
    {
        init();
    }

    /**
     * Gets connection from Pool
     * @return
     */
    private static Connection getConnection() {
        return Pool.getInstance().getConnection();
    }

    private void init() {
        connection = getConnection();
        try {
            connection.prepareStatement(SqlRequests.USE.getRequest()).execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static DBHandler getInstance() {
        if (INSTANCE == null)
            synchronized (Pool.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBHandler();
                }
            }
        return INSTANCE;
    }


}
