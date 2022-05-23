package graf.xgraph;

import java.util.ArrayList;

public class Djikstra {
    private PrewAndValue[] solved;
    private boolean [] visited;
    private final ArrayList data;
    private final int start;
    private final int finish;

    public Djikstra(ArrayList data, int howMany, int start, int finish) {
        for (int i = 0; i < howMany; i++) {
            solved[i].setPrevious(-1);
            solved[i].setValue(Double.MAX_VALUE);
            visited[i] = false;
        }
        this.data = data;
        this.start = start;
        this.finish = finish;
    }

    public PrewAndValue[] solve() {
        solved[start].setValue(0.0);

        while(!check()) {


        }
        return solved;
    }

    private boolean check(){
        for(boolean b : visited){
            if(!b)
                return false;
        }
        return true;
    }

}
