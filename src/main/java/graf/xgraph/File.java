package graf.xgraph;

import java.io.FileWriter;
import java.io.IOException;

public class File {
    String pathName;

    public File(String path) {
    this.pathName = path;
    }

    public void toFile(int row, int column) throws IOException {
        FileWriter fileWriter = new FileWriter( pathName, false);
        fileWriter.write(row + " " + column + "\n");
        fileWriter.close();
    }

    public void toFile(int index, double value, boolean newLine) throws IOException {
        FileWriter fileWriter = new FileWriter(pathName, true);
        if(newLine){
            fileWriter.write("\n");
        }
        fileWriter.write(index + " : " + value + " ");
        fileWriter.close();
    }
}
