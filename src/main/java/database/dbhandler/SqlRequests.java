package database.dbhandler;

public enum SqlRequests {
    USE("USE testing;"),
    ;

    private String request;
    SqlRequests(String request){
        this.request = request;
    }
    public String getRequest(){ return request;}
}
