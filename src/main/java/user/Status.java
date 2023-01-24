package user;

/**
 * This enum contains different status of the user.
 * @author Oleksandr Sverhin
 */
public enum Status {
    ACTIVE("active"), BLOCKED("blocked");

    private String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus(){ return status;}
}
