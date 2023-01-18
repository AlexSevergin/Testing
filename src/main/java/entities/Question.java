package entities;

public class Question {

    private int id;
    private String str;

    public Question(String str){
        //this.id =   <--------- method that finds last id
        this.str = str;
    }

    public int getId() {
        return id;
    }

    private boolean setStr(String str) {
        if() {
            this.str = str;
            return true;
        }
        else
            return false;
    }

    public String getStr() {
        return str;
    }
}
