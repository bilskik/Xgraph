package graf.xgraph;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.shape.*;

import java.io.IOException;

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

    public int row,col;
    public double to,from;
    private Generator generator;

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
        System.out.println(graph_cons.getText());
        if(graph_cons.getText().equals(tmp))
            graph_cons.setText("Inconsistent");
        else
            graph_cons.setText("Consistent");
    }
    public void Generate_Graph(ActionEvent event) {
        generator = new Generator(row,col,to,from);
        generator.generate();
        generator.write();
        display_Graph();

    }
    public void Clear_Screen(ActionEvent event) {
        ;
    }
    public void Clear_Path(ActionEvent event) {
        ;
    }
    public void Read_From_File(ActionEvent event) throws IOException {
        try {
            String read_path = read_text.getText();
            Reader read_class = new Reader(read_path);
            read_class.scanFile();
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
        int X_position = 20;
        int Y_position = 235;
        int tmp_row = 0;
        Button[] buttons = new Button[row*col];
        for(int i=0; i<row*col; i++)
            buttons[i] =  new Button();
        for(int i=0; i<row*col; i++) {
            buttons[i].setLayoutX(X_position);
            buttons[i].setLayoutY(Y_position);
            stage.getChildren().add(buttons[i]);
            X_position+=40;
            tmp_row++;
            if(tmp_row == row) {
                Y_position+=40;
                X_position=20;
                tmp_row = 0;
            }
        }
        display_line_between_graph();
    }
    public void display_line_between_graph() {
        int X_position_start = 38;
        int X_position_finish = 60;
        int Y_position_start = 247;
        int Y_position_finish = 247;
        int tmp_row = 0;
        int row_checker = 0;
        Line[] lines = new Line[row*col];
        /* Testing lines:
        Line line = new Line();
        line.setStartX(20);
        line.setStartY(30);
        line.setEndX(500);
        line.setEndY(31);
        stage.getChildren().add(line);
        */
        for(int i=0; i<row*col; i++) {
            lines[i] = new Line();
            lines[i].setStartX(X_position_start);
            lines[i].setStartY(Y_position_start);
            lines[i].setEndX(X_position_finish);
            lines[i].setEndY(Y_position_finish);
            stage.getChildren().add(lines[i]);
            X_position_start+=40;
            X_position_finish+=40;
            tmp_row++;
            if(tmp_row + 1 == row) {
                Y_position_start+=40;
                Y_position_finish+=40;
                X_position_start = 38;
                X_position_finish = 60;
                tmp_row=0;
                row_checker++;
            }
            if(row_checker == col)
                break;

        }


    }
}