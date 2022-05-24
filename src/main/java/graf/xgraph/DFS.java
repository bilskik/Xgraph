package graf.xgraph;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class DFS {
    private ArrayList<Graph> data;
    private final int  start;
    private final int indexNumber;

    public DFS(ArrayList<Graph> data, int start, int indexNumber) {
        this.data = data;
        this.start = start;
        this.indexNumber = indexNumber;
    }
    public boolean solve() {
        Stack<Integer> stack = new Stack<Integer>();
        int current = start;
        boolean[] visited = new boolean[indexNumber];
        for (boolean b : visited) {
            b = false;
        }

        for (Graph object : data) {
            visited [current] = true;
            if (object.getIndex1() == current && visited[object.getIndex2()] == false)
                stack.push(object.getIndex2());
            else if (object.getIndex2() == current && visited[object.getIndex1()] == false)
                stack.push(object.getIndex1());
            current = stack.pop();
        }
        for (boolean bool :visited) {
            if(bool == false)
                return false;
        }
        return true;
    }
}
