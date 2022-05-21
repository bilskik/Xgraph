package graf.xgraph;

import javafx.scene.Node;

public class Line extends Node {
    private final int index1;
    private final int index2;
    private final double value;

    public Line() {
        this.index1 = index1;
        this.index2 = index2;
        this.value = value;
    }

    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }

    public double getValue() {
        return value;
    }
}
