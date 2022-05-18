module graf.xgraph {
    requires javafx.controls;
    requires javafx.fxml;


    opens graf.xgraph to javafx.fxml;
    exports graf.xgraph;
}