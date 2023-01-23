package database.dbhandler;

import database.connection_pool.Pool;
import entities.*;
import org.apache.log4j.Logger;

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

    @Override
    public boolean register(String login, String password, String name) {
        return false;
    }

    @Override
    public User logIn(String login, String password) throws LoginException {
        return null;
    }

    @Override
    public User getUser(int userId) throws SQLException, LoginException {
        return null;
    }

    @Override
    public void updateUser(String login, String password, String name, Role role, Status status) throws SQLException {

    }

    @Override
    public User getUser(String login) throws SQLException, LoginException {
        return null;
    }

    @Override
    public List<User> getUsers(int offset, int limit) throws SQLException {
        return null;
    }

    @Override
    public List<User> getUsersByLogin(int offset, int limit) throws SQLException {
        return null;
    }

    @Override
    public List<User> getUsersByName(int offset, int limit) throws SQLException {
        return null;
    }

    @Override
    public List<User> getUsersByStatus(int offset, int limit) throws SQLException {
        return null;
    }

    @Override
    public Map<Test, Double> getUserTests(int userId, int offset, int limit) throws SQLException {
        return null;
    }

    @Override
    public Map<Test, Double> getUserTestsByName(int userId, int offset, int limit) throws SQLException {
        return null;
    }

    @Override
    public Map<Test, Double> getUserTestsByResult(int userId, boolean fromHighest, int offset, int limit) throws SQLException {
        return null;
    }

    @Override
    public boolean insertTest(String name, String subject, int difficulty, String time, int queries) {
        return false;
    }

    @Override
    public Test getTest(String name) {
        return null;
    }

    @Override
    public void updateTest(int testId, String name, String subject, int difficulty, String time, int queries) {

    }

    @Override
    public boolean insertQuestion(String str) throws SQLException {
        return false;
    }

    @Override
    public Question getQuestion(String str) throws SQLException {
        return null;
    }

    @Override
    public void updateQuestion(String str) throws SQLException {

    }

    @Override
    public void deleteQuestion(String str) throws SQLException {

    }

    @Override
    public boolean insertAnswer(String str) throws SQLException {
        return false;
    }

    @Override
    public Answer getAnswer(String str) throws SQLException {
        return null;
    }

    @Override
    public void updateAnswer(String str) throws SQLException {

    }

    @Override
    public void deleteAnswer(String str) throws SQLException {

    }

    @Override
    public boolean questionHasAnswer(int questionId, int answerId) throws SQLException {
        return false;
    }

    @Override
    public boolean testHasQuestion(int testId, int questionId) throws SQLException {
        return false;
    }

    @Override
    public boolean userHasTest(int userId, int testId) throws SQLException {
        return false;
    }
}
