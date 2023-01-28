package entities.answer;

/**
 * This class is responsible for an Answer entity
 * @author Oleksandr Severhin
 */
public class Answer {
    private int id;
    private String str;

    /**
     * Creating an Answer
     * @param str
     */
    public Answer(String str) {
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
