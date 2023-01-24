package user;

import database.dbhandler.DBHandler;
import javax.security.auth.login.LoginException;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private Role role;
    private Status status;

    public User(String login, String password, String name, Role role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
        this.status = Status.ACTIVE;
    }

    public User(String login, String password) throws LoginException {
        return DBHandler.getInstance().logIn(login, password);
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

    private void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) { this.role = role; }

    private void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
