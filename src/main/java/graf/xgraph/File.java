package graf.xgraph;

import java.io.FileWriter;
import java.io.IOException;

public class File {
    public static void toFile(int row, int column) throws IOException {
        FileWriter fileWriter = new FileWriter("GraphinFile", false);
        fileWriter.write(row + " " + column + "\n");
        fileWriter.close();
    }

    public static void toFile(int index, double value, boolean newLine) throws IOException {
        FileWriter fileWriter = new FileWriter("GraphinFile", true);
        if(newLine){
            fileWriter.write("\n");
        }
        fileWriter.write(index + " : " + value + " ");
        fileWriter.close();
    }
}
