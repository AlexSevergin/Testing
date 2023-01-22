package entities;

public class Answer {

    private int id;
    private String str;

    public Answer(String str) {
        //this.id =   <--------- method that finds last id
        this.str = str;
    }

    public int getId() {
        return id;
    }

    private void setStr(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
