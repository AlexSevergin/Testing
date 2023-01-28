package entities.question;

/**
 * This class is responsible for Question entity
 * @author Oleksandr Severhin
 */
public class Question {
    private int id;
    private String str;

    /**
     * Creating a Question
     * @param str
     */
    public Question(String str){
        this.str = str;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getStr() {
        return str;
    }

    private void setStr(String str) {
        this.str = str;
    }
}
