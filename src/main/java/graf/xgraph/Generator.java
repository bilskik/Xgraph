package graf.xgraph;
import java.util.ArrayList;
import java.util.Random;

public class Generator {
    private static final int rowNumber = 10;
    private static final int columnNumber = 10;
    private static final ArrayList<Line> przejscia = new ArrayList<Line>();
    Random random = new Random();

    public void generate() {
        for (int i = 0; i < columnNumber * rowNumber - 1; i++) {
            if (((i + 1) % columnNumber != 0) && (i < columnNumber * (rowNumber - 1))) {
                przejscia.add(new Line(i, i + 1, random.nextDouble()));
                przejscia.add(new Line(i, i + columnNumber, random.nextDouble()));
                //generuj 2 prawo i dĂłl
            }
            if ((i + 1) % columnNumber == 0) {
                przejscia.add(new Line(i, i + columnNumber, random.nextDouble()));
                //generuj w dĂłl
            }
            if (i >= columnNumber * (rowNumber - 1)) {
                przejscia.add(new Line(i, i + 1, random.nextDouble()));
                //generuj w prawo
            }
        }
    }

    public void write() {
        for (Line object : przejscia) {
            System.out.println(object.getIndex1() + " <---> " + object.getIndex2() + " value: " + object.getValue());
        }
    }

}
