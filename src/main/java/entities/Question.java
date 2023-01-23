package entities;

public class Question extends Entity {

    private String str;

    public Question(String str){
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    private void setStr(String str) {
        this.str = str;
    }
}
