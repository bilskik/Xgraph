package graf.xgraph;

import java.util.*;

public class BFS {
    private ArrayList<Graph> arr;
    private int row;
    private int col;
    private Queue<Integer> q;
    BFS(ArrayList<Graph> arr, int rowNumber, int colNumber) {
        this.arr = arr;
        this.row = rowNumber;
        this.col = colNumber;
    }
    public boolean solver() {           //main bfs solver
        int vertices[] =  new int[row*col];
        boolean visited[] = new boolean[row*col];
        int v;
        int nb[];
        int a = 0;
        q = new LinkedList<Integer>();
        Arrays.fill(visited, Boolean.FALSE);
        q.add(arr.get(0).getIndex1());
        visited[0] = true;
        while(!q.isEmpty()) {
            v = q.element();
            q.remove();
            nb = find_nb(v);
            for(int i=0; i<nb.length; i++) {
                if(nb[i] == -1)
                    continue;
                if(visited[nb[i]])
                    continue;
                q.add(nb[i]);
                visited[nb[i]] = true;
            }
        }
        for (boolean b : visited)
            if (!b)
                return false;
        return true;
    }
    private int[] find_nb(int index) {
        int nb[] = new int[4];
        Arrays.fill(nb,-1);
        int iter = 0;
        for(int i=0; i<arr.size(); i++) {
            if(arr.get(i).getValue() <= 0)
                continue;
            if(index == arr.get(i).getIndex1()) {
                if(check_add(nb,arr.get(i).getIndex1()))
                    nb[iter++] = arr.get(i).getIndex2();
            }
            else if(index == arr.get(i).getIndex2()) {
                if(check_add(nb,arr.get(i).getIndex2()))
                    nb[iter++] = arr.get(i).getIndex1();
            }
        }
        return nb;
    }
    private boolean check_add(int []nb, int value) {
        for(int i=0; i<nb.length; i++) {
            if(value == nb[i])
                return false;
        }
        return true;
    }
}
