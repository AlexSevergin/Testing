package database.dbhandler;

import user.Role;
import user.Status;

/**
 * This enum contains all the sql requests to the database.
 * @author Oleksandr Severhin
 */
public enum SqlRequests {
    USE("USE testing;"),
    REGISTER("INSERT INTO users (login, password, name, role, status) " +
            "VALUES (?, ?, ?, '" + Role.STUDENT.getRole() +"', '" + Status.ACTIVE.getStatus() + "');"),
    LOGIN(),
    GET_USER_BY_ID(),
    UPDATE_USER(),
    GET_USER_BY_LOGIN(),
    GET_USERS(),
    GET_USERS_BY_LOGIN(),
    GET_USERS_BY_NAME(),
    GET_USERS_BY_STATUS(),
    GET_USER_TESTS(),
    GET_USER_TESTS_BY_NAME(),
    GET_USER_TESTS_BY_RESULT(),
    INSERT_TEST(),
    GET_TEST(),
    UPDATE_TEST(),
    INSERT_QUESTION(),
    GET_QUESTION(),
    GET_QUESTIONS(),
    UPDATE_QUESTION(),
    DELETE_QUESTION(),
    INSERT_ANSWER(),
    GET_ANSWER(),
    GET_ANSWERS(),
    UPDATE_ANSWER(),
    DELETE_ANSWER(),
    QUESTION_HAS_ANSWER(),
    TEST_HAS_QUESTION(),
    USER_HAS_TEST();

    private String request;
    SqlRequests(String request){
        this.request = request;
    }
    public String getRequest(){ return request;}
}
