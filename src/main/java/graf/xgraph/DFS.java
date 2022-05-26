package graf.xgraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class DFS {
    private ArrayList<Graph> data;
    private final int  start;
    private final int indexNumber;
    private ArrayList<ArrayList<Integer>> vertex = new ArrayList<>();
    public DFS(ArrayList<Graph> data, int start, int indexNumber) {
        this.data = data;
        this.start = start;
        this.indexNumber = indexNumber;
            for (Graph object: data) {
                int src = object.getIndex1();
                int dest = object.getIndex2();
                vertex.get(src).add(dest);
                vertex.get(dest).add(src);
            }
        }
    public boolean solve() {
        Stack<Integer> stack = new Stack<Integer>();
        int current = start;
        boolean[] visited = new boolean[indexNumber];
        for (boolean b : visited) {
            b = false;
        }
        stack.add(current);
        visited[current] = true;
        while(!stack.isEmpty()){
        current = stack.pop();
        for(Integer integer: vertex.get(current)){
            if(!visited[integer]){
                stack.push(integer);
            }
        }
        visited[current] = true;
        }
        for (boolean bool :visited) {
            if(bool == false)
                return false;
        }
        return true;
    }
}
