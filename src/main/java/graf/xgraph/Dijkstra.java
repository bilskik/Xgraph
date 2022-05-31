package graf.xgraph;

import java.util.ArrayList;

public class Dijkstra {
    private int index;
    private PrewAndValue[] solved;
    private boolean [] visited;
    private final ArrayList<Graph> data;
    private final int start;
    private final int finish;

    public Dijkstra(ArrayList data, int index, int start, int finish) {
        this.index = index;
        solved = new PrewAndValue[index];
        visited = new boolean[index];
        for (int i = 0; i < index; i++) {
            solved[i] = new PrewAndValue();
            solved[i].setPrevious(-1);
            solved[i].setValue(Double.MAX_VALUE);
            visited[i] = false;
        }
        this.data = data;
        this.start = start;
        this.finish = finish;
    }

    public PrewAndValue[] solve() {             //main djikstra function
        solved[start].setValue(0.0);

        while(!check()) {
            double tmp = Double.MAX_VALUE;
            int j = 0;
            for(int i = 0; i < index; i++)
                if(solved[i].getValue() <= tmp && visited[i] == false ){
                    tmp = solved[i].getValue();
                    j = i;
                }
            int i = 0;
            for(Graph object: data){
                if(!visited[j]){
                    if(object.getIndex1() == j && solved[object.getIndex2()].getValue() > (solved[object.getIndex1()].getValue() + object.getValue())) {
                        solved[object.getIndex2()].setValue(solved[object.getIndex1()].getValue() + object.getValue());
                        solved[object.getIndex2()].setPrevious(object.getIndex1());
                    }
                    if(object.getIndex2() == j && solved[object.getIndex1()].getValue() > (solved[object.getIndex2()].getValue() + object.getValue())) {
                    solved[object.getIndex1()].setValue(solved[object.getIndex2()].getValue() + object.getValue());
                    solved[object.getIndex1()].setPrevious(object.getIndex2());
                    }
                }
                i++;
            }
            visited[j] = true;

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
