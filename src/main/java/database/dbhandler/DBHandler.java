package database.dbhandler;

import database.connection_pool.HikariCP;
import entities.answer.Answer;
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
import java.util.ArrayList;
import java.util.HashMap;
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
        try {
            init();
        } catch (SQLException e) {
            LOG.error("Error in DBHandler while initializing HikariCP connection pool", e);
            e.printStackTrace();
        }
    }

    /**
     * Gets connection from Pool
     */
    private static Connection getConnection() throws SQLException {
        return HikariCP.getConnection();
    }

    private void init() throws SQLException {
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
            synchronized (HikariCP.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBHandler();
                }
            }
        return INSTANCE;
    }

    @Override
    public boolean register(String login, String password, String name) throws SQLException {
        boolean res;
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
            throw new SQLException(e.getMessage());
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
    public User getUser(int userId) throws LoginException {
        User user;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_USER,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, userId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                boolean completed = resultSet.first();
                if (completed) {
                    String login = resultSet.getString("login");
                    user = new User(login);
                    user.setId(userId);
                    user.setPassword(resultSet.getString("password"));
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
    public User getUser(String login) throws LoginException {
        User user;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_USER_BY_LOGIN,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setString(1, login);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                boolean completed = resultSet.first();
                if (completed) {
                    user = new User(login);
                    user.setId(resultSet.getInt("id"));
                    user.setPassword(resultSet.getString("password"));
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
    public int updateUserPassword(int id, String password) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.UPDATE_USER_PASSWORD,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, password);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public int updateUserName(int id, String name) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.UPDATE_USER_NAME,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public int makeUserAdmin(int id) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.MAKE_USER_ADMIN,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public int blockUser(int id, Status status) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.BLOCK_USER,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public int unblockUser(int id, Status status) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.UNBLOCK_USER,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public List<User> getUsers(int limit, int offset) throws SQLException {
        List<User> users;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_USERS)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                users = resultSetToUserList(rs);
            }
        }
        return users;
    }

    @Override
    public List<User> getUsersByLogin(int limit, int offset) throws SQLException {
        List<User> users;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_USERS_BY_LOGIN)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                users = resultSetToUserList(rs);
            }
        }
        return users;
    }

    @Override
    public List<User> getUsersByName(int limit, int offset) throws SQLException {
        List<User> users;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_USERS_BY_NAME)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                users = resultSetToUserList(rs);
            }
        }
        return users;
    }

    @Override
    public List<User> getUsersByStatus(int limit, int offset) throws SQLException {
        List<User> users;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_USERS_BY_STATUS)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                users = resultSetToUserList(rs);
            }
        }
        return users;
    }

    @Override
    public Map<String, Double> getUserTests(int userId, int limit, int offset) throws SQLException {
        Map<String, Double> testWithResults;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_USER_TESTS)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, limit);
            pstmt.setInt(3, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                testWithResults = resultSetToMap(rs);
            }
        }
        return testWithResults;
    }

    @Override
    public Map<String, Double> getUserTestsByName(int userId, int limit, int offset) throws SQLException {
        Map<String, Double> testWithResults;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_USER_TESTS_BY_NAME)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, limit);
            pstmt.setInt(3, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                testWithResults = resultSetToMap(rs);
            }
        }
        return testWithResults;
    }

    @Override
    public Map<String, Double> getUserTestsByResult(int userId, int limit, int offset) throws SQLException {
        Map<String, Double> testWithResults;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_USER_TESTS_BY_RESULT)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, limit);
            pstmt.setInt(3, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                testWithResults = resultSetToMap(rs);
            }
        }
        return testWithResults;
    }

    @Override
    public boolean insertTest(String name, String subject, int difficulty, String time, int queries) throws SQLException {
        boolean res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.INSERT_TEST)) {
            pstmt.setString(1, name);
            pstmt.setString(2, subject);
            pstmt.setInt(3, difficulty);
            pstmt.setString(4, time);
            pstmt.setInt(5, queries);
            pstmt.executeUpdate();
            res = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }
        return res;
    }

    @Override
    public Test getTest(int id) throws SQLException {
        Test test;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_TEST,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                boolean completed = resultSet.first();
                if (completed) {
                    test = new Test(id);
                    test.setName(resultSet.getString("name"));
                    test.setName(resultSet.getString("subject"));
                    test.setDifficulty(resultSet.getInt("difficulty"));
                    test.setTime(resultSet.getString("time"));
                    test.setQueries(resultSet.getInt("queries"));
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            throw new SQLException();
        }
        return test;
    }

    @Override
    public List<Test> getTests(int limit, int offset) throws SQLException {
        List<Test> tests;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_TESTS)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                tests = resultSetToTestList(rs);
            }
        }
        return tests;
    }

    @Override
    public List<Test> getTestsByName(int limit, int offset) throws SQLException {
        List<Test> tests;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_TESTS_BY_NAME)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                tests = resultSetToTestList(rs);
            }
        }
        return tests;
    }

    @Override
    public List<Test> getTestsByDifficulty(int limit, int offset) throws SQLException {
        List<Test> tests;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_TESTS_BY_DIFFICULTY)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                tests = resultSetToTestList(rs);
            }
        }
        return tests;
    }

    @Override
    public List<Test> getTestsByTime(int limit, int offset) throws SQLException {
        List<Test> tests;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_TESTS_BY_TIME)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                tests = resultSetToTestList(rs);
            }
        }
        return tests;
    }

    @Override
    public List<Test> getTestsByQueries(int limit, int offset) throws SQLException {
        List<Test> tests;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_TESTS_BY_QUERIES)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                tests = resultSetToTestList(rs);
            }
        }
        return tests;
    }

    @Override
    public int updateTestName(int testId, String name) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.UPDATE_TEST_NAME,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, testId);
            pstmt.setString(2, name);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public int updateTestSubject(int testId, String subject) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.UPDATE_TEST_SUBJECT,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, testId);
            pstmt.setString(2, subject);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public int updateTestDifficulty(int testId, int difficulty) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.UPDATE_TEST_DIFFICULTY,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, testId);
            pstmt.setInt(2, difficulty);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public int updateTestTime(int testId, String time) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.UPDATE_TEST_TIME,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, testId);
            pstmt.setString(2, time);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public int updateTestQueries(int testId, int queries) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.UPDATE_TEST_QUERIES,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, testId);
            pstmt.setInt(2, queries);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public boolean insertQuestion(String str) throws SQLException {
        boolean res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.INSERT_QUESTION)) {
            pstmt.setString(1, str);
            pstmt.executeUpdate();
            res = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }
        return res;
    }

    @Override
    public Question getQuestion(int id) throws SQLException {
        Question question = new Question(id);
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_QUESTION,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                boolean completed = resultSet.first();
                if (completed) {
                    question.setStr(resultSet.getString("string"));
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            throw new SQLException();
        }
        return question;
    }

    @Override
    public List<Question> getQuestions(int testId, int limit, int offset) throws SQLException {
        List<Question> questions;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_QUESTIONS)) {
            pstmt.setInt(1, testId);
            pstmt.setInt(2, limit);
            pstmt.setInt(3, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                questions = resultSetToQuestionList(rs);
            }
        }
        return questions;
    }

    @Override
    public int updateQuestion(int id, String str) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.UPDATE_QUESTION,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, str);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public int deleteQuestion(int id) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.DELETE_QUESTION,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public boolean insertAnswer(String str) throws SQLException {
        boolean res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.INSERT_ANSWER)) {
            pstmt.setString(1, str);
            pstmt.executeUpdate();
            res = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }
        return res;
    }

    @Override
    public Answer getAnswer(int id) throws SQLException {
        Answer answer = new Answer(id);
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_ANSWER,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                boolean completed = resultSet.first();
                if (completed) {
                    answer.setStr(resultSet.getString("string"));
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            throw new SQLException();
        }
        return answer;
    }

    @Override
    public List<Answer> getAnswers(int questionId, int limit, int offset) throws SQLException {
        List<Answer> answers;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.GET_ANSWERS)) {
            pstmt.setInt(1, questionId);
            pstmt.setInt(2, limit);
            pstmt.setInt(3, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                answers = resultSetToAnswerList(rs);
            }
        }
        return answers;
    }

    @Override
    public int updateAnswer(int id, String str) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.UPDATE_ANSWER,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, str);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public int deleteAnswer(int id) throws SQLException {
        int res;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.DELETE_ANSWER,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            try {
                res = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.error(e.getMessage());
                throw new SQLException();
            }
        }
        return res;
    }

    @Override
    public boolean questionHasAnswer(int questionId, int answerId) throws SQLException {
        boolean res = false;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.QUESTION_HAS_ANSWER)) {
            pstmt.setInt(1, questionId);
            pstmt.setInt(2, answerId);
            pstmt.executeUpdate();
            res = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }
        return res;
    }

    @Override
    public boolean testHasQuestion(int testId, int questionId) throws SQLException {
        boolean res = false;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.TEST_HAS_QUESTION)) {
            pstmt.setInt(1, testId);
            pstmt.setInt(2, questionId);
            pstmt.executeUpdate();
            res = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }
        return res;
    }

    @Override
    public boolean userHasTest(int userId, int testId, double result) throws SQLException {
        boolean res = false;
        try (PreparedStatement pstmt = connection.prepareStatement(SqlRequests.USER_HAS_TEST)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, testId);
            pstmt.setDouble(3, result);
            pstmt.executeUpdate();
            res = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }
        return res;
    }

    /**
     * Converts a result set after sql request to a list with Users
     * @param rs - ResultSet
     * @return List (User)
     * @throws SQLException - when an SQL error occurs
     */
    private List<User> resultSetToUserList(ResultSet rs) throws SQLException {
        List<User> res = new ArrayList<>();
        while (rs.next()) {
            String login = rs.getString("login");
            User user = new User(login);
            user.setId(rs.getInt("id"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setRole(Role.valueOf(rs.getString("role").toUpperCase()));
            user.setStatus(Status.valueOf(rs.getString("status").toUpperCase()));
            res.add(user);
        }
        return res;
    }

    /**
     * Converts a result set after sql request to a list with Tests
     * @param rs - ResultSet
     * @return List (Test)
     * @throws SQLException - when an SQL error occurs
     */
    private List<Test> resultSetToTestList(ResultSet rs) throws SQLException {
        List<Test> res = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            Test test = new Test(id);
            test.setName(rs.getString("name"));
            test.setSubject(rs.getString("subject"));
            test.setDifficulty(rs.getInt("difficulty"));
            test.setTime(rs.getString("time"));
            test.setQueries(rs.getInt("queries"));
            res.add(test);
        }
        return res;
    }

    /**
     * Converts a result set after sql request to a list with Questions
     * @param rs - ResultSet
     * @return List (Question)
     * @throws SQLException - when an SQL error occurs
     */
    private List<Question> resultSetToQuestionList(ResultSet rs) throws SQLException {
        List<Question> res = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            Question question = new Question(id);
            question.setStr(rs.getString("string"));
            res.add(question);
        }
        return res;
    }

    /**
     * Converts a result set after sql request to a list with Answers
     * @param rs - ResultSet
     * @return List (Answer)
     * @throws SQLException - when an SQL error occurs
     */
    private List<Answer> resultSetToAnswerList(ResultSet rs) throws SQLException {
        List<Answer> res = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            Answer answer = new Answer(id);
            answer.setStr(rs.getString("string"));
            res.add(answer);
        }
        return res;
    }

    /**
     * Converts a result set after sql request to a map with test name and user's result
     * @param rs - ResultSet
     * @return Map (String, Double)
     * @throws SQLException - when an SQL error occurs
     */
    private Map<String, Double> resultSetToMap(ResultSet rs) throws SQLException {
        Map<String, Double> testWithResults = new HashMap<>();
        while(rs.next()) {
            String testName = rs.getString("name");
            double result = rs.getDouble("result");
            testWithResults.put(testName, result);
        }
        return testWithResults;
    }
}
