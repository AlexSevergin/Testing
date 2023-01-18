package entities;

public class User {

    private int id;
    private String login;
    private String password;
    private String name;
    private String role;
    private String status;

    public User(String login, String password, String name, String role) {
        //this.id =   <--------- method that finds last id
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
        this.status = "active";
    }

    public int getId() {
        return id;
    }

    private boolean setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    private boolean setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    private boolean setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    private boolean setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
