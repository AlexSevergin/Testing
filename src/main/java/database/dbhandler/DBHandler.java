package database.dbhandler;

import entities.answer.Answer;
import database.connection_pool.Pool;
import entities.user.Role;
import org.apache.log4j.Logger;
import entities.question.Question;
import entities.test.Test;
import entities.user.Status;
import entities.user.User;
import javax.security.auth.login.LoginException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            connection.prepareStatement(SqlRequests.USE).execute();
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
    public void use() {

    }

    @Override
    public boolean register(String login, String password, String name) {
        boolean res = false;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.REGISTER)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, Role.STUDENT.getRole());
            pstmt.setString(5, Status.ACTIVE.getStatus());
            pstmt.executeUpdate();
            res = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public User logIn(String login, String password) throws LoginException {
        User user = new User(login);
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.LOGIN,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                boolean completed = resultSet.first();
                if (completed) {
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setRole(Role.valueOf(resultSet.getString("role")));
                    user.setStatus(Status.valueOf(resultSet.getString("status").toUpperCase()));
                } else {
                    throw new LoginException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            throw new LoginException();
        }
        return user;
    }

    @Override
    public User getUser(int userId) throws SQLException, LoginException {
        return null;
    }

    @Override
    public User getUser(String login) throws SQLException, LoginException {
        return null;
    }

    @Override
    public void updateUserPassword(int id, String password) throws SQLException {

    }

    @Override
    public void updateUserName(int id, String name) throws SQLException {

    }

    @Override
    public void makeUserAdmin(int id) throws SQLException {

    }

    @Override
    public void blockUser(int id, Status status) throws SQLException {

    }

    @Override
    public void unblockUser(int id, Status status) throws SQLException {

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
    public Map<String, Double> getUserTests(int userId, int offset, int limit) throws SQLException {
        return null;
    }

    @Override
    public Map<String, Double> getUserTestsByName(int userId, int offset, int limit) throws SQLException {
        return null;
    }

    @Override
    public Map<String, Double> getUserTestsByResult(int userId, int offset, int limit) throws SQLException {
        return null;
    }

    @Override
    public boolean insertTest(String name, String subject, int difficulty, String time, int queries) {
        return false;
    }

    @Override
    public Test getTest(int id) {
        return null;
    }

    @Override
    public List<Test> getTests(int offset, int limit) {
        return null;
    }

    @Override
    public List<Test> getTestsByName(int offset, int limit) {
        return null;
    }

    @Override
    public List<Test> getTestsByDifficulty(int offset, int limit) {
        return null;
    }

    @Override
    public List<Test> getTestsByTime(int offset, int limit) {
        return null;
    }

    @Override
    public List<Test> getTestsByQueries(int offset, int limit) {
        return null;
    }

    @Override
    public void updateTestName(int testId, String name) {

    }

    @Override
    public void updateTestSubject(int testId, String subject) {

    }

    @Override
    public void updateTestDifficulty(int testId, int difficulty) {

    }

    @Override
    public void updateTestTime(int testId, String time) {

    }

    @Override
    public void updateTestQueries(int testId, int queries) {

    }

    @Override
    public boolean insertQuestion(String str) throws SQLException {
        return false;
    }

    @Override
    public Question getQuestion(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Question> getQuestions(int testId, int offset, int limit) throws SQLException {
        return null;
    }

    @Override
    public void updateQuestion(int id) throws SQLException {

    }

    @Override
    public void deleteQuestion(String str) throws SQLException {

    }

    @Override
    public boolean insertAnswer(String str) throws SQLException {
        return false;
    }

    @Override
    public Answer getAnswer(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Answer> getAnswers(int testId, int offset, int limit) throws SQLException {
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
