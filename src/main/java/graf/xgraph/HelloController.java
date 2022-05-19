package graf.xgraph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
    public int row,col;
    public double to,from;
    private int checker = 0;

    public void confirm(ActionEvent event) {
        try {
            row = Integer.parseInt(row_field.getText());
            col = Integer.parseInt(col_field.getText());
            to = Double.parseDouble(from_field.getText());
            from = Double.parseDouble(to_field.getText());
        }
        catch(NumberFormatException e) {
            System.out.println("only numbers are allowed");
        }
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
        Generator generator = new Generator(row,col);
        generator.generate();
        generator.write();
        try {
            generator.toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Clear_Screen(ActionEvent event) {
        ;
    }
    public void Clear_Path(ActionEvent event) {
        ;
    }
    public void Read_From_File(ActionEvent event) {
        ;
    }
    public void Save_To_File(ActionEvent event) {
        String save_path = save_text.getText();
        File f = new File(save_path);
    }

}