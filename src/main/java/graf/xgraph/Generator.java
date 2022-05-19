package graf.xgraph;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Generator {
    private final int rowNumber;
    private final int columnNumber;
    private final double from;
    private final double to;
    Generator(int row, int col, double to, double from) {
        this.rowNumber = row;
        this.columnNumber = col;
        this.to = to;
        this.from = from;
    }
    private ArrayList<Line> przejscia = new ArrayList<Line>();
    Random random = new Random();

    public void generate() {
        for (int i = 0; i < columnNumber * rowNumber - 1; i++) {
            if (((i + 1) % columnNumber != 0) && (i < columnNumber * (rowNumber - 1))) {
                przejscia.add(new Line(i, i + 1, rand_generator()));
                przejscia.add(new Line(i, i + columnNumber, rand_generator()));
                //generuj 2 prawo i dĂłl
            }
            if ((i + 1) % columnNumber == 0) {
                przejscia.add(new Line(i, i + columnNumber, rand_generator()));
                //generuj w dĂłl
            }
            if (i >= columnNumber * (rowNumber - 1)) {
                przejscia.add(new Line(i, i + 1, rand_generator()));
                //generuj w prawo
            }
        }
    }
    private double rand_generator() {
        return from + (to - from)*random.nextDouble();
    }

    public void write() {
        for (Line object : przejscia) {
            System.out.println(object.getIndex1() + " <---> " + object.getIndex2() + " value: " + object.getValue());
        }
    }
    public void toFile() throws IOException {
        File file = new File("Siema");
        file.toFile(rowNumber,columnNumber);
        int currentindex = 0;
        boolean newLine;
        for (Line line: przejscia) {
            if (currentindex == line.getIndex1()){
                newLine = false;
            }
            else {
                currentindex = line.getIndex1();
                newLine = true;
            }
            file.toFile(line.getIndex2(),line.getValue(), newLine);
        }
    }

}
