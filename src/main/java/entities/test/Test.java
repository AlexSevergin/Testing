package entities.test;

import entities.user.User;
import org.apache.log4j.Logger;

/**
 * This class is responsible for Test entity
 * @author Oleksandr Severhin
 */
public class Test {
    private int id;
    private String name;
    private String subject;
    private int difficulty;
    private String time;
    private int queries;

    /**
     * Creating a test by id given
     * @param id
     */
    public Test(int id) {
        this.id = id;
    }

    /**
     * Creating a Test
     * @param name
     * @param subject
     * @param difficulty
     * @param time
     * @param queries
     */
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


    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getTime() { return time; }

    public void setTime(String time) {
        this.time = time;
    }

    public int getQueries() {
        return queries;
    }

    public void setQueries(int queries) {
        this.queries = queries;
    }

}
