package graf.xgraph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BFSTest {

    @Test
    public void bfs_test_1() {
        Generator generator = new Generator(10,10,2,3);
        generator.generate(0);
        BFS check_graph = new BFS(generator.getPrzejscia(),generator.getRowNumber(), generator.getColumnNumber());
        assertTrue(check_graph.solver());
    }
    @Test
    public void bfs_test_2() {
        Generator generator = new Generator(10,10,0,10);
        generator.generate(1);
        BFS check_graph = new BFS(generator.getPrzejscia(),generator.getRowNumber(), generator.getColumnNumber());
        assertFalse(check_graph.solver());
    }

}