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
     * @param id
     */
    public Answer(int id) {
        this.id = id;
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

    public void setStr(String str) {
        this.str = str;
    }
}
