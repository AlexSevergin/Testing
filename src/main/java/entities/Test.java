package entities;

public class Test {

    private int id;
    private String name;
    private String subject;
    private String difficulty;
    private String time;
    private String queries;

    public Test(String name, String subject, String difficulty, String time, String queries) {
        //this.id =     <------- method that creates id for a Test
        // exceptions needed
        this.name = name;
        this.subject = subject;
        this.difficulty = difficulty;
        this.time = time;
        this.queries = queries;
    }

    public int getId() {
        return id;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    private void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    private void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    private void setQueries(String queries) {
        this.queries = queries;
    }

    public String getQueries() {
        return queries;
    }
}
