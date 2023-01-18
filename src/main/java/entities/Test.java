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

    private boolean setName(String name) {
        if () {
            this.name = name;
            return true;
        }
        else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    private boolean setSubject(String subject) {
        if () {
            this.subject = subject;
            return true;
        }
        else {
            return false;
        }
    }

    public String getSubject() {
        return subject;
    }

    private boolean setDifficulty(String difficulty) {
        if () {
            this.difficulty = difficulty;
            return true;
        }
        else {
            return false;
        }
    }

    public String getDifficulty() {
        return difficulty;
    }

    private boolean setTime(String time) {
        if () {
            this.time = time;
            return true;
        }
        else {
            return false;
        }
    }

    public String getTime() {
        return time;
    }

    private boolean setQueries(String queries) {
        if () {
            this.queries = queries;
            return true;
        }
        else {
            return false;
        }
    }

    public String getQueries() {
        return queries;
    }
}
