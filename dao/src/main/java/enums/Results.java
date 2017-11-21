package enums;

public enum Results {
    WIN1("Win 1 team"),
    DRAW("Draw"),
    WIN2("Win 2 team");

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
