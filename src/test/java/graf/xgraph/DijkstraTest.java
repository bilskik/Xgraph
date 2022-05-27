package graf.xgraph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    public void djikstra_test_1() {
        Generator generator = new Generator(10,10,2,3);
        generator.generate(0);
        Dijkstra d = new Dijkstra(generator.getPrzejscia(), generator.getRowNumber()* generator.getColumnNumber(), 0,5);
        assertNotNull(d.solve());
    }

}