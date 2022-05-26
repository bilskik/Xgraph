package graf.xgraph;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.shape.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HelloController {
    @FXML
    private TextField row_field;
    @FXML
    private TextField col_field;
    @FXML
    private TextField from_field;
    @FXML
    private TextField to_field;
    @FXML
    private Button confirm;
    @FXML
    private Button graph_cons;
    @FXML
    private Button generate;
    @FXML
    private Button clear_screen;
    @FXML
    private Button clear_path;
    @FXML
    private Button read_but;
    @FXML
    private TextField read_text;
    @FXML
    private Button save_but;
    @FXML
    private TextField save_text;
    @FXML
    private AnchorPane stage;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Label path_value;

    Button buttons[] = null;
    Line lines[];
    int start = -1;
    int finish = -1;
    int tmp_finish = -1;
    int click = 0;
    ArrayList<Graph> arr;
    public int row,col;
    public double to,from;
    private Generator generator;
    private BFS check_graph;
    private DFS check_graph_dfs;
    int mode = 0;
    double value = 0;

    public void confirm(ActionEvent event) throws IOException {
        try {
            row = Integer.parseInt(row_field.getText());
            col = Integer.parseInt(col_field.getText());
            to = Double.parseDouble(from_field.getText());
            from = Double.parseDouble(to_field.getText());
            if(to > from)
                errors("From cannot be greater than to!");
            if(row < 1 )
                errors("Rows cannot be less than 1!");
            if(col < 1)
                errors("Columns cannot be less than 1!");
            if(to < 0)
                errors("to cannot be less than 0!");
            if (from < 0)
                errors("from cannot be less than 0!");

        }
        catch(NumberFormatException e) {
            errors("Only numbers are allowed!");
        }
    }
    private void errors(String output) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText(output);
        errorAlert.showAndWait();
    }
    public void graph(ActionEvent event) {
        String tmp = "Consistent";
        mode = 0;
        if(graph_cons.getText().equals(tmp)) {
            graph_cons.setText("Inconsistent");
            mode = 1;
        }
        else {
            graph_cons.setText("Consistent");
            mode = 0;
        }
    }
    public void Generate_Graph(ActionEvent event) {
        if(buttons != null) {
            clear();
        }
        generator = new Generator(row,col,to,from);
        generator.generate(mode);
        generator.write();
        arr = generator.getPrzejscia();
        check_graph = new BFS(arr,generator.getRowNumber(), generator.getColumnNumber());
        check_graph_dfs = new DFS(arr,0, row*col);
        boolean bfs = check_graph.solver();
      //  if(!bfs)
         //   errors("Graph isn't consistent!");
        boolean dfs = check_graph_dfs.solve();
        if(!dfs)
            errors("Graph isn't consistent!");
        display_Graph();

    }
    public void Clear_Screen(ActionEvent event) {
        clear();
    }
    public void Clear_Path(ActionEvent event) {
        click = 0;
        start = -1;
        finish = -1;
        int iter = 0;
        for(int i=0; i< buttons.length; i++) {
            buttons[i].setStyle(null);
        }
        for(Graph object : arr) {
            lines[iter] = choose_color(lines[iter],object.getValue());
            iter++;
        }
        path_value.setText(null);
    }
    public void Read_From_File(ActionEvent event) throws IOException {
        try {
            String read_path = read_text.getText();
            Reader read_class = new Reader(read_path);
            read_class.scanFile();
            row = read_class.getReadedRow();
            col = read_class.getReadedColumn();
            arr = read_class.getReadedLines();
            check_graph = new BFS(arr,read_class.getReadedRow(), read_class.getReadedColumn());
            boolean bfs = check_graph.solver();
            if(!bfs)
                errors("Graph isn't consistent!");
            display_Graph();
        }
        catch(IOException e) {
            errors("Can't read from file,invalid path or file doesn't exist!");
        }
    }
    public void Save_To_File(ActionEvent event) {
        try {
            String save_path = save_text.getText();
            generator.toFile(save_path);
        } catch (IOException e) {
            errors("Can't save to file,invalid path or invalid file name!");
        }
        catch (NullPointerException a) {
            errors("You have to generate graph to save it!");
        }

    }
    public void display_Graph() {
        scroll = new ScrollPane();
        int change_positon_x = 40;
        int change_position_y = 40;
        int X_position = 20;
        int Y_position = 235;
        int tmp_col = 0;
        buttons = new Button[row*col];
        for(int i=0; i<row*col; i++) {
            final int buttonId = i;
            buttons[i] =  new Button();
            buttons[i].setPrefSize(1,1);
            buttons[i].setLayoutX(X_position);
            buttons[i].setLayoutY(Y_position);
            stage.getChildren().add(buttons[i]);
            buttons[i].setOnAction(e -> {

                if(click == 0) {
                    start = buttonId;
                    click++;
                }
                else {
                    finish = buttonId;
                    click++;
                }
                if(start != -1 && finish != -1 && click == 2) {
                    click = 0;
                    int [] arr_index = new int[col*(row-1) + row*(col-1)];
                    PrewAndValue[] solved;
                    Dijkstra d = new Dijkstra(arr,row*col,start,finish);
                    solved = d.solve();
                    arr_index = reverse_element(arr_index,solved);
                    draw_path(arr_index);
                    path_value.setText(Double.toString(value));
                }
            });
            X_position+=change_positon_x;
            tmp_col++;
            if(tmp_col == col) {
                Y_position+=change_position_y;
                X_position=20;
                tmp_col = 0;
            }
        }
        display_line_between_graph();
    }

    public void display_line_between_graph() {
        int X_position_start_across = 38;
        int X_position_finish_across = 60;
        int Y_position_start_across = 247;
        int Y_position_finish_across = 247;
        int X_position_start_vertical = 30;
        int X_position_finish_vertical = 30;
        int Y_position_start_vertical = 259;
        int Y_position_finish_vertical = 276;
        int i = 0;
        int tmp_col = 0;
        int tmp_row = 0;
        int line_number = col*(row-1) + row*(col-1);
        lines = new Line[line_number];
        for(Graph object : arr) {
            if(object.getIndex1()  + 1  == object.getIndex2()) {
                lines[i] = new Line();
                lines[i].setStartX(X_position_start_across);
                lines[i].setStartY(Y_position_start_across);
                lines[i].setEndX(X_position_finish_across);
                lines[i].setEndY(Y_position_finish_across);
                lines[i] = choose_color(lines[i], object.getValue());
                stage.getChildren().add(lines[i]);
                X_position_start_across += 40;
                X_position_finish_across += 40;
                tmp_col++;
                if (tmp_col + 1 == col) {
                    Y_position_start_across += 40;
                    Y_position_finish_across += 40;
                    X_position_start_across = 38;
                    X_position_finish_across = 60;
                    tmp_col = 0;
                }
            }
            else {
                lines[i] = new Line();
                lines[i].setStartX(X_position_start_vertical);
                lines[i].setStartY(Y_position_start_vertical);
                lines[i].setEndX(X_position_finish_vertical);
                lines[i].setEndY(Y_position_finish_vertical);
                lines[i] = choose_color(lines[i],object.getValue());
                stage.getChildren().add(lines[i]);
                X_position_start_vertical += 40;
                X_position_finish_vertical += 40;
                tmp_row++;
                if(tmp_row == col) {
                    Y_position_start_vertical +=40;
                    Y_position_finish_vertical +=40;
                    X_position_start_vertical = 30;
                    X_position_finish_vertical = 30;
                    tmp_row = 0;
                }
            }
            i++;
        }
    }
    private Line choose_color(Line line, double value) {
        double divisor = (from - to)/7;
        if(value >= to && value < to+divisor)
            line.setStroke(Color.DARKBLUE);
        else if(value >= to+divisor && value < to+2*divisor)
            line.setStroke(Color.LIGHTBLUE);
        else if(value >= to+2*divisor && value < to+3*divisor)
            line.setStroke(Color.GREEN);
        else if(value >= to+3*divisor && value < to+4*divisor)
            line.setStroke(Color.YELLOW);
        else if(value >= to+4*divisor && value < to+5*divisor)
            line.setStroke(Color.ORANGE);
        else if(value >= to+5*divisor && value <= from)
            line.setStroke(Color.RED);
        return line;
    }
    private int[] reverse_element(int [] arr_index, PrewAndValue[] solved) {
        Arrays.fill(arr_index,-1);
        int iter = 0;
        int tmp;
        int i = finish;
        arr_index[iter++] = finish;
        while(i != start){
            i = solved[i].getPrevious();
            arr_index[iter] = i;
            iter++;
        }
        for (int a = 0; a < arr_index.length / 2; a++) {
            tmp = arr_index[a];
            arr_index[a] = arr_index[arr_index.length - a - 1];
            arr_index[arr_index.length - a - 1] = tmp;
        }
        value = Math.round(solved[finish].getValue());
        return arr_index;
    }
    private void draw_path(int [] path) {
        int a;
        int iter=0;
        for(Graph object : arr) {
            for(int i=0; i< path.length; i++) {
                a = i;
                if(path[i] == -1)
                    continue;
                else if(object.getIndex1() == path[i-1] && object.getIndex2() == path[i] || object.getIndex2() == path[i-1] && object.getIndex1() == path[i]) {
                    lines[iter].setStroke(Color.BLACK);
                    buttons[path[i-1]].setStyle("-fx-background-color: #000000; ");
                }

            }
            iter++;
        }
        buttons[finish].setStyle("-fx-background-color: #000000; ");

    }
    private void clear() {
        for(int i=0; i< lines.length; i++)
            stage.getChildren().remove(lines[i]);
        for(int i=0; i< buttons.length; i++)
            stage.getChildren().remove(buttons[i]);
        path_value.setText(null);
        click = 0;
        finish = -1;
        start = -1;
    }
}