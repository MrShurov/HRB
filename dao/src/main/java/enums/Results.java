package enums;

public enum Results {
    WIN1("WIN1"),
    DRAW("DRAW"),
    WIN2("WIN2");

    private String result;

    private Results(final String result){
        this.result = result;
    }

    public String getResult(){
        return result;
    }

    @Override
    public String toString(){
        return this.result;
    }

    public String getName(){
        return this.name();
    }
}
