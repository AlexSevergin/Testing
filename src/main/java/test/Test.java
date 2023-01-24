package test;

public class Test {
    private int id;
    private String name;
    private String subject;
    private int difficulty;
    private String time;
    private int queries;

    public Test(String name, String subject, int difficulty, String time, int queries) {
        this.name = name;
        this.subject = subject;
        this.difficulty = difficulty;
        this.time = time;
        this.queries = queries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    private void setSubject(String subject) {
        this.subject = subject;
    }

    public int getDifficulty() {
        return difficulty;
    }

    private void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getTime() { return time; }

    private void setTime(String time) {
        this.time = time;
    }

    public int getQueries() {
        return queries;
    }

    private void setQueries(int queries) {
        this.queries = queries;
    }

}
