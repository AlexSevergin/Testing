package database.dbhandler;

import entities.answer.Answer;
import entities.question.Question;
import entities.test.Test;
import entities.user.Status;
import entities.user.User;
import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * This interface contains all the methods to interact with the database.
 * @author Oleksandr Severhin
 */
public interface DBHandlerInterface {

    /**
     * It's needed to use the testing database
     */
    void use();

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
     * Updates User password in the database
     * @param id
     * @param password
     */
    void updateUserPassword(int id, String password) throws SQLException;

    /**
     * Updates User name in the database
     * @param id
     * @param name
     */
    void updateUserName(int id, String name) throws SQLException;

    /**
     * Makes used an admin
     * @param id
     * @throws SQLException
     */
    void makeUserAdmin(int id) throws SQLException;

    /**
     * Blocks User (status = blocked)
     * @param id
     * @param status
     */
    void blockUser(int id, Status status) throws SQLException;

    /**
     * Unblocks User (status = active)
     * @param id
     * @param status
     */
    void unblockUser(int id, Status status) throws SQLException;

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
     * @param limit  - depends on number of users displayed on the page
     * @return ArrayList with all users
     * @throws SQLException
     */
    List<User> getUsers(int offset, int limit) throws SQLException;

    /**
     * Gets list with all users sorted by login by parts (for paging)
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of users displayed on the page
     * @return ArrayList with all users ordered by login
     * @throws SQLException
     */
    List<User> getUsersByLogin(int offset, int limit) throws SQLException;

    /**
     * Gets list with all users sorted by name by parts (for paging)
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of users displayed on the page
     * @return ArrayList with all users ordered by name
     * @throws SQLException
     */
    List<User> getUsersByName(int offset, int limit) throws SQLException;

    /**
     * Gets list with all users sorted by status by parts (for paging)
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of users displayed on the page
     * @return ArrayList with all users ordered by status
     * @throws SQLException
     */
    List<User> getUsersByStatus(int offset, int limit) throws SQLException;

    /**
     * Gets Map of tests names and user results for the test user passed by parts (for paging) by users id
     * @param userId
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return Map (String, Double)
     * @throws SQLException
     */
    Map<String, Double> getUserTests(int userId, int offset, int limit) throws SQLException;

    /**
     * Gets Map of tests names and user results for the test user passed sorted by test name by parts (for paging) by users id
     * @param userId
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return Map (String, Double)
     * @throws SQLException
     */
    Map<String, Double> getUserTestsByName(int userId, int offset, int limit) throws SQLException;

    /**
     * Gets Map of tests names and user results for the test user passed sorted by user results by parts (for paging) by users id
     * @param userId
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return Map (String, Double)
     * @throws SQLException
     */
    Map<String, Double> getUserTestsByResult(int userId, int offset, int limit) throws SQLException;

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
     * @param id
     * @return Test
     */
    Test getTest(int id);

    /**
     * Returns a List with Tests by parts (for paging)
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return List<Test>
     */
    List <Test> getTests(int offset, int limit);

    /**
     * Returns a List with Tests sorted by name by parts (for paging)
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return List<Test>
     */
    List <Test> getTestsByName(int offset, int limit);

    /**
     * Returns a List with Tests sorted by difficulty by parts (for paging)
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return List<Test>
     */
    List <Test> getTestsByDifficulty(int offset, int limit);

    /**
     * Returns a List with Tests sorted by time by parts (for paging)
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return List<Test>
     */
    List <Test> getTestsByTime(int offset, int limit);

    /**
     * Returns a List with Tests sorted by queries by parts (for paging)
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return List<Test>
     */
    List <Test> getTestsByQueries(int offset, int limit);

    /**
     * Updates the test name by id given
     * @param testId
     * @param name
     */
    void updateTestName(int testId, String name);

    /**
     * Updates the test subject by id given
     * @param testId
     * @param subject
     */
    void updateTestSubject(int testId, String subject);

    /**
     * Updates the test difficulty by id given
     * @param testId
     * @param difficulty
     */
    void updateTestDifficulty(int testId, int difficulty);

    /**
     * Updates the test time by id given
     * @param testId
     * @param time
     */
    void updateTestTime(int testId, String time);

    /**
     * Updates the test queries (amount of Questions) by id given
     * @param testId
     * @param queries
     */
    void updateTestQueries(int testId, int queries);


    /**
     * Insert a row in table Question by getting a String (question itself)
     * @param str
     * @return true/false (if everything ok/if the Question already exists in the database)
     */
    boolean insertQuestion(String str) throws SQLException;

    /**
     * Get Question from database by an id given
     * @param id
     * @return Question with all fields set
     */
    Question getQuestion(int id) throws SQLException;

    /**
     * Get Question from database by a testId given by parts (for paging)
     * @param testId
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return List with Questions in the test
     */
    List<Question> getQuestions(int testId, int offset, int limit) throws SQLException;

    /**
     * Updates Question by an id given
     * @param id
     */
    void updateQuestion(int id) throws SQLException;

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
     * Get Answer from database by an id given
     * @param id
     * @return Question with all fields set
     */
    Answer getAnswer(int id) throws SQLException;

    /**
     * Get List with Answers from database by parts (for paging) by a questionId given
     * @param testId
     * @param offset - depends on the displayed page
     * @param limit  - depends on number of tests displayed on the page
     * @return List with Answers in the test
     */
    List<Answer> getAnswers(int testId, int offset, int limit) throws SQLException;

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
