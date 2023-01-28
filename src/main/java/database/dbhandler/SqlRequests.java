package database.dbhandler;

import entities.user.Status;

/**
 * This class contains all the sql requests to the database.
 * @author Oleksandr Severhin
 */
public class SqlRequests {
    protected static final String USE = "USE testing;";
    protected static final String REGISTER = "INSERT INTO user (login, password, name, role, status) " +
            "VALUES ('?', '?', '?', '?', '?');";
    protected static final String LOGIN = "SELECT * FROM user WHERE login = '?' and password = '?';";
    protected static final String GET_USER = "SELECT * FROM user WHERE id = ?;";
    protected static final String GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login = '?';";
    protected static final String UPDATE_USER_PASSWORD = "UPDATE user SET password = '?' WHERE id = ?;";
    protected static final String UPDATE_USER_NAME = "UPDATE user SET name = '?' WHERE id = ?;";
    protected static final String MAKE_USER_ADMIN = "UPDATE user SET role = '?' WHERE id = ?;";
    protected static final String BLOCK_USER = "UPDATE user SET status = '" + Status.BLOCKED.getStatus() + "' WHERE id = ?;";
    protected static final String UNBLOCK_USER = "UPDATE user SET status = '" + Status.ACTIVE.getStatus() + "' WHERE id = ?;";
    protected static final String GET_USERS = "SELECT * FROM user LIMIT ? OFFSET ?;";
    protected static final String GET_USERS_BY_LOGIN = "SELECT * FROM user ORDER BY login LIMIT ? OFFSET ?;";
    protected static final String GET_USERS_BY_NAME = "SELECT * FROM user ORDER BY name LIMIT ? OFFSET ?;";
    protected static final String GET_USERS_BY_STATUS = "SELECT * FROM user ORDER BY status LIMIT ? OFFSET ?;";
    protected static final String GET_USER_TESTS = "SELECT test.name, result FROM test INNER JOIN user_has_test " +
            "ON test.id = Test_id WHERE User_id = ? LIMIT ? OFFSET ?;";
    protected static final String GET_USER_TESTS_BY_NAME = "SELECT test.name, result FROM test INNER JOIN user_has_test " +
            "ON test.id = Test_id WHERE User_id = ? ORDER BY test.name LIMIT ? OFFSET ?;";
    protected static final String GET_USER_TESTS_BY_RESULT = "SELECT test.name, result FROM test INNER JOIN user_has_test " +
            "ON test.id = Test_id WHERE User_id = ? ORDER BY result LIMIT ? OFFSET ?;";
    protected static final String INSERT_TEST = "INSERT INTO test (name, subject, difficulty, time, queries) VALUES ('?', '?', ?, '?', ?);";
    protected static final String GET_TEST = "SELECT * FROM test WHERE id = ?";
    protected static final String GET_TESTS = "SELECT * FROM test LIMIT ? OFFSET ?;";
    protected static final String GET_TEST_BY_NAME = "SELECT * FROM test ORDER BY name LIMIT ? OFFSET ?;";
    protected static final String GET_TEST_BY_DIFFICULTY = "SELECT * FROM test ORDER BY difficulty LIMIT ? OFFSET ?;";
    protected static final String GET_TEST_BY_TIME = "SELECT * FROM test ORDER BY time LIMIT ? OFFSET ?;";
    protected static final String GET_TEST_BY_QUERIES = "SELECT * FROM test ORDER BY queries LIMIT ? OFFSET ?;";
    protected static final String UPDATE_TEST_NAME = "UPDATE test SET name = '?' WHERE id = ?;";
    protected static final String UPDATE_TEST_SUBJECT = "UPDATE test SET subject = '?' WHERE id = ?;";
    protected static final String UPDATE_TEST_DIFFICULTY = "UPDATE test SET difficulty = ? WHERE id = ?;";
    protected static final String UPDATE_TEST_TIME = "UPDATE test SET time = '?' WHERE id = ?;";
    protected static final String UPDATE_TEST_QUERIES = "UPDATE test SET queries = ? WHERE id = ?;";
    protected static final String INSERT_QUESTION = "INSERT INTO question (string) VALUES ('?');";
    protected static final String GET_QUESTION = "SELECT * FROM test WHERE id = ?;";
    protected static final String GET_QUESTIONS = "SELECT question.string FROM question INNER JOIN test_has_question " +
            "ON question.id = Question_id WHERE Test_id = ? LIMIT ? OFFSET ?;";
    protected static final String UPDATE_QUESTION = "UPDATE question SET string = '?' WHERE id = ?;";
    protected static final String DELETE_QUESTION = "DELETE FROM question WHERE id = ?;";
    protected static final String INSERT_ANSWER = "INSERT INTO answer (string) VALUES ('?')";
    protected static final String GET_ANSWER = "SELECT * FROM test WHERE id = ?";
    protected static final String GET_ANSWERS = "SELECT answer.string FROM answer INNER JOIN question_has_answer " +
            "ON answer.id = Answer_id WHERE Question_id = ? LIMIT ? OFFSET ?;";
    protected static final String UPDATE_ANSWER = "UPDATE answer SET string = '?' WHERE id = ?;";
    protected static final String DELETE_ANSWER = "DELETE FROM answer WHERE id = ?;";
    protected static final String QUESTION_HAS_ANSWER = "INSERT INTO question_has_answer (Question_id, Answer_id) VALUES (?, ?);";
    protected static final String TEST_HAS_QUESTION = "INSERT INTO test_has_question (Test_id, Question_id) VALUES (?, ?);";
    protected static final String USER_HAS_TEST = "INSERT INTO user_has_test (User_id, Test_id, result) VALUES (?, ?, ?);";
}
