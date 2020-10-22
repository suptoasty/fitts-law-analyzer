package src.views;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelpView {

    public HelpView(Stage stage) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        dialog.setTitle("Info");

        final BorderPane root = new BorderPane();
        Scene dialogScene = new Scene(root, dialog.getWidth(), dialog.getHeight());

        FlowPane flowpane = new FlowPane();
        root.setCenter(flowpane);

        Text info = new Text();
        flowpane.getChildren().add(info);

        info.setText("The purpose of this application is to empirically validate the premise of Fitts Law\n"
                + "Click the center of the square to play.\n" + "Go to View/Graph to see results.\n");

        dialog.setScene(dialogScene);
        dialog.show();
    }

}
