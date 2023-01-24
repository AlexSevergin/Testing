package user;

/**
 * This enum contains different roles the user might have.
 * @author Oleksandr Sverhin
 */
public enum Role {
    STUDENT("student"), ADMINISTRATOR("administrator");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){ return role;}
}