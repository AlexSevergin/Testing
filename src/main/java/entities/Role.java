package entities;

public enum Role {
    STUDENT("Student"),
    ADMINISTRATOR("Admin");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return (long) ordinal();
    }

    public String getName() {
        return name;
    }
}