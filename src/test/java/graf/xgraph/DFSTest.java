package graf.xgraph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DFSTest {
    @Test
    public void dfs_test_1() {
        Generator generator = new Generator(10,10,2,3);
        generator.generate(0);
        DFS check_graph = new DFS(generator.getPrzejscia(),0, 10*10);
        assertTrue(check_graph.solve());
    }
    @Test
    public void dfs_test_2() {
        Generator generator = new Generator(10,10,2,3);
        generator.generate(1);
        DFS check_graph = new DFS(generator.getPrzejscia(),0, 10*10);
        assertFalse(check_graph.solve());
    }
}