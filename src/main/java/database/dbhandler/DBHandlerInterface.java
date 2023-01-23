package database.dbhandler;

import entities.*

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DBHandlerInterface {

    /**
     * Insert a row in table user
     * @param login
     * @param password
     * @param name
     * @return true/false
     */
    boolean register(String login, String password, String name);

    /**
     * Checks if login and password are the same as in the database
     * @param login
     * @param password
     * @return User object with all fields set
     * @throws LoginException when login or password is wrong
     */
    User logIn(String login, String password) throws LoginException;

    /**
     * Get User from the database by id given
     * @param userId
     * @return User object
     * @throws SQLException
     * @throws LoginException if there is no user with such id
     * @see User
     */
    User getUser(int userId) throws SQLException, LoginException;

    /**
     * Updates User in the database
     * @param login
     * @param password
     * @param name
     * @param role
     * @param status
     */
    void updateUser(String login, String password, String name, Role role, Status status) throws SQLException;

    /**
     * Get User from the database by the name given
     * @param login
     * @return User object
     * @throws SQLException
     * @throws LoginException if there is no user with such id
     * @see User
     */
    User getUser(String login) throws SQLException, LoginException;

    /**
     * Gets list with all users by parts (for paging)
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return ArrayList with all users
     * @throws SQLException
     */
    List<User> getUsers(int offset, int limit) throws SQLException;

    /**
     * Gets list with all users sorted by login by parts (for paging)
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return ArrayList with all users ordered by login
     * @throws SQLException
     */
    List<User> getUsersByLogin(int offset, int limit) throws SQLException;

    /**
     * Gets list with all users sorted by name by parts (for paging)
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return ArrayList with all users ordered by name
     * @throws SQLException
     */
    List<User> getUsersByName(int offset, int limit) throws SQLException;

    /**
     * Gets list with all users sorted by status by parts (for paging)
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return ArrayList with all users ordered by status
     * @throws SQLException
     */
    List<User> getUsersByStatus(int offset, int limit) throws SQLException;

    /**
     * Gets map of tests (and results) the user passed by parts (for paging) by users id
     * @param userId
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return Hashmap (Test, result)
     * @throws SQLException
     */
    Map<Test, Double> getUserTests(int userId, int offset, int limit) throws SQLException;

    /**
     * Gets map of tests (and results) sorted by name the user passed by parts (for paging) by users id
     * @param userId
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return Hashmap (Test, result) ordered by name
     * @throws SQLException
     */
    Map<Test, Double> getUserTestsByName(int userId, int offset, int limit) throws SQLException;

    /**
     * Gets map of tests (and results) sorted by result the user passed by parts (for paging) by users id
     * @param userId
     * @param fromHighest - result from highest to lowest or vice versa
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return Hashmap (Test, result) ordered by result
     * @throws SQLException
     */
    Map<Test, Double> getUserTestsByResult(int userId, boolean fromHighest, int offset, int limit) throws SQLException;

    /**
     * Inserts a row in table Test
     * @param name
     * @param subject
     * @param difficulty
     * @param time
     * @param queries
     * @return true/false
     */
    boolean insertTest(String name, String subject, int difficulty, String time, int queries);

    /**
     * Returns the Test Object with all fields set
     * @param name
     * @return Test
     */
    Test getTest(String name);

    /**
     * Updates the test by id given
     * @param testId
     * @param name
     * @param subject
     * @param difficulty
     * @param time
     * @param queries
     */
    void updateTest(int testId, String name, String subject, int difficulty, String time, int queries);

    /**
     * Insert a row in table Question by getting a String (question itself)
     * @param str
     * @return true/false (if everything ok/if the Question already exists in the database)
     */
    boolean insertQuestion(String str) throws SQLException;

    /**
     * Get Question from database by a String given
     * @param str
     * @return Question with all fields set
     */
    Question getQuestion(String str) throws SQLException;

    /**
     * Updates Question by a String given
     * @param str
     */
    void updateQuestion(String str) throws SQLException;

    /**
     * Deletes Question from the database by a String given
     * @param str
     * @throws SQLException
     */
    void deleteQuestion(String str) throws SQLException;

    /**
     * Insert a row in table Answer by getting a String (answer itself)
     * @param str
     * @return (if everything ok/if the Question already exists in the database)
     */
    boolean insertAnswer(String str) throws SQLException;

    /**
     * Get Question from database by a String given
     * @param str
     * @return Question with all fields set
     */
    Answer getAnswer(String str) throws SQLException;

    /**
     * Updates Answer by a String given
     * @param str
     */
    void updateAnswer(String str) throws SQLException;

    /**
     * Deletes Answer from the database by a String given
     * @param str
     * @throws SQLException
     */
    void deleteAnswer(String str) throws SQLException;

    /**
     * Adds a raw in the table question_has_answer
     * @param questionId
     * @param answerId
     * @return true/false
     * @throws SQLException
     */
    boolean questionHasAnswer(int questionId, int answerId) throws SQLException;

    /**
     * Adds a raw in the table test_has_question
     * @param testId
     * @param questionId
     * @return true/false
     * @throws SQLException
     */
    boolean testHasQuestion(int testId, int questionId) throws SQLException;

    /**
     * Adds a raw in the table user_has_test
     * @param userId
     * @param testId
     * @return true/false
     * @throws SQLException
     */
    boolean userHasTest(int userId, int testId) throws SQLException;
}
