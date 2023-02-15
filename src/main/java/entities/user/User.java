package entities.user;

import database.dbhandler.DBHandler;
import org.apache.log4j.Logger;
import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is responsible for User entity
 * @author Oleksandr Severhin
 */
public class User {
    public static final Logger LOG = Logger.getLogger(User.class);
    private String regex = "^([a-zA-Z]{2,})";
    private int id;
    private String login;
    private String password;
    private String name;
    private Role role;
    private Status status;

    /** This constructor is used when the user logs in */
    public User(String login) {
        this.login = login;
    }

    /**
     * This constructor is used when user goes through registration
     * @param login
     * @param password
     * @param name
     * @param role
     */
    public User(String login, String password, String name, Role role) throws LoginException {
        if (checkLogin(login)) {
            if (checkName(name)) {
                this.login = login;
                this.password = password;
                this.name = name;
                this.role = role;
                this.status = Status.ACTIVE;
                try {
                    DBHandler.getInstance().register(login, password, name);
                } catch (SQLException e) {
                    LOG.error(e.getMessage());
                    throw new LoginException(String.valueOf(2));
                }
            }
            else {
                throw new LoginException(String.valueOf(1));
            }
        }
        else {
            throw new LoginException(String.valueOf(0));
        }
    }

    /**
     * This constructor is used when user logs in
     * @param login
     * @param password
     */
    public User login(String login, String password) throws LoginException {
        return DBHandler.getInstance().logIn(login, password);
    }


    private boolean checkLogin(String login) {
        DBHandler db = DBHandler.getInstance();
        try {
            db.getUser(login);
        } catch (LoginException e) {
            return true;
        }
        return false;
    }


    private boolean checkName(String name) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) { this.role = role; }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
