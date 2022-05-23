package graf.xgraph;

public class PrewAndValue {
    int previous;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    double value;

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public PrewAndValue(){
        previous = -1;
        value = 0.0;
    }
}
