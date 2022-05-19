package graf.xgraph;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        Generator generator = new Generator();
        generator.generate();
        //generator.write();
        try {
            generator.toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("GraphinFile");
        try {
            Reader reader = new Reader("GraphinFile");
            reader.scanFile();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}