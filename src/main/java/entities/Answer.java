package entities;

public class Answer extends Entity {

    private String str;

    public Answer(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    private void setStr(String str) {
        this.str = str;
    }
}
