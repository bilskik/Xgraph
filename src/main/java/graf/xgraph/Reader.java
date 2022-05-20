package graf.xgraph;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
    Scanner sc;
    private int readedRow;
    private int readedColumn;
    private ArrayList<Line> readedLines = new ArrayList<Line>();

    public int getReadedColumn() {
        return readedColumn;
    }

    public int getReadedRow() {
        return readedRow;
    }

    public ArrayList<Line> getReadedLines() {
        return readedLines;
    }

    public Reader(String pathName) throws IOException {
        sc = new Scanner(new java.io.File(pathName), StandardCharsets.UTF_8);
    }

    public void scanFile () {
        System.out.println("Row & Column");
        if (sc.hasNextLine()){
            scanFirstLine(sc.nextLine());
        }
        System.out.println("Array");
        int currentLine = 0;
        while (sc.hasNextLine()) {
            // System.out.println(sc.nextLine());
            scanNextLine(sc.nextLine(), currentLine);
            currentLine++;
        }
        System.out.println(readedRow + " " + readedColumn);
    }

    public void scanFirstLine(String s) {
        Pattern pattern1 = Pattern.compile("(\\d+)\\s+(\\d+)");
        Matcher matcher1 = pattern1.matcher(s);
        if (!matcher1.matches()) {
            throw new IllegalArgumentException("Line does not match the expected pattern");
        }
        s.split("\\s+");
        int first = 0;
        int second = 0;
        if (matcher1.matches()) {
            first = Integer.parseInt(matcher1.group(1));
            second = Integer.parseInt(matcher1.group(2));
        }
        System.out.println(first + " " + second);
        System.out.println(first+second);
        readedRow = first;
        readedColumn = second;
    }
    public void scanNextLine(String s,int line) {
        Pattern pattern2 = Pattern.compile("(\\d+)\\s:\\s+(\\d+.\\d+)\\s");
        Pattern pattern3 = Pattern.compile("(\\d+)\\s:\\s+(\\d+.\\d+)\\s+(\\d+)\\s:\\s+(\\d+.\\d+)\\s");
        Matcher matcher2 = pattern2.matcher(s);
        Matcher matcher3 = pattern3.matcher(s);
        if(!matcher2.matches() && !matcher3.matches())
        {
            throw new IllegalArgumentException("Line does not match the expected pattern");
        }
        s.split("\\s+");
        int first = 0;
        double second = 0.0;
        if (matcher2.matches()) {
            first = Integer.parseInt(matcher2.group(1));
            second = Double.parseDouble(matcher2.group(2));
            readedLines.add(new Line(line, first,second));
        }
        int third = 0;
        double fourth = 0.0;
        if (matcher3.matches()) {
            first = Integer.parseInt(matcher3.group(1));
            second = Double.parseDouble(matcher3.group(2));
            third = Integer.parseInt(matcher3.group(3));
            fourth = Double.parseDouble(matcher3.group(4));
            readedLines.add(new Line(line, first,second));
            readedLines.add(new Line(line, third,fourth));
        }
        System.out.println(first + " " + second + " " + third + " " + fourth);
    }
}
