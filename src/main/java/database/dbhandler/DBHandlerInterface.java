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
     * Insert a row in table user
     * @param login - user login
     * @param password - user password
     * @param name - name of the suer
     * @return true/false
     */
    boolean register(String login, String password, String name) throws SQLException;

    /**
     * Checks if login and password are the same as in the database
     * @param login - user login
     * @param password - user password
     * @return User object with all fields set
     * @throws LoginException when login or password is wrong
     */
    User logIn(String login, String password) throws LoginException;

    /**
     * Get User from the database by id given
     * @param userId - user id
     * @return User object
     * @throws SQLException - when sql error occurs
     * @throws LoginException if there is no user with such id
     * @see User
     */
    User getUser(int userId) throws SQLException, LoginException;

    /**
     * Get User from the database by the name given
     * @param login - user login
     * @return User object
     * @throws SQLException - when sql error occurs
     * @throws LoginException if there is no user with such id
     * @see User
     */
    User getUser(String login) throws SQLException, LoginException;

    /**
     * Updates User password in the database
     * @param id - user id
     * @param password - new user password
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int updateUserPassword(int id, String password) throws SQLException;

    /**
     * Updates User name in the database
     * @param id - id of the user
     * @param name - new name of the user
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int updateUserName(int id, String name) throws SQLException;

    /**
     * Makes used an admin
     * @param id - id of the user
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int makeUserAdmin(int id) throws SQLException;

    /**
     * Blocks User (status = blocked)
     * @param id - id of the user
     * @param status - blocked status
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int blockUser(int id, Status status) throws SQLException;

    /**
     * Unblocks User (status = active)
     * @param id - id of the user
     * @param status - active status
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int unblockUser(int id, Status status) throws SQLException;

    /**
     * Gets list with all users by parts (for paging)
     * @param limit  - depends on number of users displayed on the page
     * @param offset - depends on the displayed page
     * @return ArrayList with all users
     * @throws SQLException - when sql error occurs
     */
    List<User> getUsers(int limit, int offset) throws SQLException;

    /**
     * Gets list with all users sorted by login by parts (for paging)
     * @param limit  - depends on number of users displayed on the page
     * @param offset - depends on the displayed page
     * @return ArrayList with all users ordered by login
     * @throws SQLException - when sql error occurs
     */
    List<User> getUsersByLogin(int limit, int offset) throws SQLException;

    /**
     * Gets list with all users sorted by name by parts (for paging)
     * @param limit  - depends on number of users displayed on the page
     * @param offset - depends on the displayed page
     * @return ArrayList with all users ordered by name
     * @throws SQLException - when sql error occurs
     */
    List<User> getUsersByName(int limit, int offset) throws SQLException;

    /**
     * Gets list with all users sorted by status by parts (for paging)
     * @param limit  - depends on number of users displayed on the page
     * @param offset - depends on the displayed page
     * @return ArrayList with all users ordered by status
     * @throws SQLException - when sql error occurs
     */
    List<User> getUsersByStatus(int limit, int offset) throws SQLException;

    /**
     * Gets Map of tests names and user results for the test user passed by parts (for paging) by users id
     * @param userId - id of the user
     * @param limit  - depends on number of tests displayed on the page
     * @param offset - depends on the displayed page
     * @return Map (String, Double)
     * @throws SQLException - when sql error occurs
     */
    Map<String, Double> getUserTests(int userId, int limit, int offset) throws SQLException;

    /**
     * Gets Map of tests names and user results for the test user passed sorted by test name by parts (for paging) by users id
     * @param userId - id of the user
     * @param limit  - depends on number of tests displayed on the page
     * @param offset - depends on the displayed page
     * @return Map (String, Double)
     * @throws SQLException - when sql error occurs
     */
    Map<String, Double> getUserTestsByName(int userId, int limit, int offset) throws SQLException;

    /**
     * Gets Map of tests names and user results for the test user passed sorted by user results by parts (for paging) by users id
     * @param userId - id of the user
     * @param limit  - depends on number of tests displayed on the page
     * @param offset - depends on the displayed page
     * @return Map (String, Double)
     * @throws SQLException - when sql error occurs
     */
    Map<String, Double> getUserTestsByResult(int userId, int limit, int offset) throws SQLException;

    /**
     * Inserts a row in table Test
     * @param name - name of the test
     * @param subject - subject of the test
     * @param difficulty - test difficulty
     * @param time - test time
     * @param queries - amount of queries test has
     * @return true/false
     * @throws SQLException - when sql error occurs
     */
    boolean insertTest(String name, String subject, int difficulty, String time, int queries) throws SQLException;

    /**
     * Returns the Test Object with all fields set
     * @param id - test id
     * @return Test
     * @throws SQLException - when sql error occurs
     */
    Test getTest(int id) throws SQLException;

    /**
     * Returns a List with Tests by parts (for paging)
     * @param limit  - depends on number of tests displayed on the page
     * @param offset - depends on the displayed page
     * @return List<Test>
     * @throws SQLException - when sql error occurs
     */
    List <Test> getTests(int limit, int offset) throws SQLException;

    /**
     * Returns a List with Tests sorted by name by parts (for paging)
     * @param limit  - depends on number of tests displayed on the page
     * @param offset - depends on the displayed page
     * @return List<Test>
     * @throws SQLException - when sql error occurs
     */
    List <Test> getTestsByName(int limit, int offset) throws SQLException;

    /**
     * Returns a List with Tests sorted by difficulty by parts (for paging)
     * @param limit  - depends on number of tests displayed on the page
     * @param offset - depends on the displayed page
     * @return List<Test>
     * @throws SQLException - when sql error occurs
     */
    List <Test> getTestsByDifficulty(int limit, int offset) throws SQLException;

    /**
     * Returns a List with Tests sorted by time by parts (for paging)
     * @param limit  - depends on number of tests displayed on the page
     * @param offset - depends on the displayed page
     * @return List<Test>
     * @throws SQLException - when sql error occurs
     */
    List <Test> getTestsByTime(int limit, int offset) throws SQLException;

    /**
     * Returns a List with Tests sorted by queries by parts (for paging)
     * @param limit  - depends on number of tests displayed on the page
     * @param offset - depends on the displayed page
     * @return List<Test>
     * @throws SQLException - when sql error occurs
     */
    List <Test> getTestsByQueries(int limit, int offset) throws SQLException;

    /**
     * Updates the test name by id given
     * @param testId - test id
     * @param name - new test name
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int updateTestName(int testId, String name) throws SQLException;

    /**
     * Updates the test subject by id given
     * @param testId - test id
     * @param subject - new test subject
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int updateTestSubject(int testId, String subject) throws SQLException;

    /**
     * Updates the test difficulty by id given
     * @param testId - test id
     * @param difficulty - new test difficulty
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int updateTestDifficulty(int testId, int difficulty) throws SQLException;

    /**
     * Updates the test time by id given
     * @param testId - test id
     * @param time - new test time
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int updateTestTime(int testId, String time) throws SQLException;

    /**
     * Updates the test queries (amount of Questions) by id given
     * @param testId - test id
     * @param queries - new amount of test queries
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int updateTestQueries(int testId, int queries) throws SQLException;


    /**
     * Insert a row in table Question by getting a String (question itself)
     * @param str - question itself
     * @return true/false (if everything ok/if the Question already exists in the database)
     * @throws SQLException - when sql error occurs
     */
    boolean insertQuestion(String str) throws SQLException;

    /**
     * Get Question from database by an id given
     * @param id - question id
     * @return Question with all fields set
     */
    Question getQuestion(int id) throws SQLException;

    /**
     * Get Question from database by a testId given by parts (for paging)
     * @param testId - test id
     * @param limit  - depends on number of tests displayed on the page
     * @param offset - depends on the displayed page
     * @return List with Questions in the test
     */
    List<Question> getQuestions(int testId, int limit, int offset) throws SQLException;

    /**
     * Updates Question by an id given
     * @param id - question id
     * @param str - updated question itself
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int updateQuestion(int id, String str) throws SQLException;

    /**
     * Deletes Question from the database by an id given
     * @param id - question id
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int deleteQuestion(int id) throws SQLException;

    /**
     * Insert a row in table Answer by getting a String (answer itself)
     * @param str - answer itself
     * @return true - if everything ok or
     * false - if the Question already exists in the database
     * @throws SQLException - when sql error occurs
     */
    boolean insertAnswer(String str) throws SQLException;

    /**
     * Get Answer from database by an id given
     * @param id - answer id
     * @return Question with all fields set
     * @throws SQLException - when sql error occurs
     */
    Answer getAnswer(int id) throws SQLException;

    /**
     * Get List with Answers from database by parts (for paging) by a questionId given
     * @param questionId - question id
     * @param limit  - depends on number of tests displayed on the page
     * @param offset - depends on the displayed page
     * @return List with Answers in the test
     * @throws SQLException - when sql error occurs
     */
    List<Answer> getAnswers(int questionId, int limit, int offset) throws SQLException;

    /**
     * Updates Answer by a String given
     * @param id - answer id
     * @param str - updated answer itself
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int updateAnswer(int id, String str) throws SQLException;

    /**
     * Deletes Answer from the database by an id given
     * @param id - answer id
     * @return row count
     * @throws SQLException - when sql error occurs
     */
    int deleteAnswer(int id) throws SQLException;

    /**
     * Adds a raw in the table question_has_answer
     * @param questionId - question id
     * @param answerId - answer id
     * @return true/false
     * @throws SQLException - when sql error occurs
     */
    boolean questionHasAnswer(int questionId, int answerId) throws SQLException;

    /**
     * Adds a raw in the table test_has_question
     * @param testId - test id
     * @param questionId - question id
     * @return true/false
     * @throws SQLException - when sql error occurs
     */
    boolean testHasQuestion(int testId, int questionId) throws SQLException;

    /**
     * Adds a raw in the table user_has_test
     * @param userId - user id
     * @param testId - test id
     * @param result - result the user has for passing the test (in %)
     * @return true/false
     * @throws SQLException - when sql error occurs
     */
    boolean userHasTest(int userId, int testId, double result) throws SQLException;
}
