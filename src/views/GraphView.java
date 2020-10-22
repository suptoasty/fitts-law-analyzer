package src.views;

import java.text.DecimalFormat;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.controllers.GraphController;
import src.models.Model;

public class GraphView {

    public GraphView(Stage stage, Model model, GraphController controller) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);

        final BorderPane root = new BorderPane();
        Scene dialogScene = new Scene(root, dialog.getWidth(), dialog.getHeight());

        // chart
        final LineChart<Number, Number> chart = new LineChart<Number, Number>(new NumberAxis("ID", 0, 10, 10),
                new NumberAxis("TIME", 0, 10, 10));
        chart.setLegendVisible(false);
        final XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();

        Point2D a = new Point2D(model.getIdList().get(0), model.getTimeList().get(0));
        Point2D b = new Point2D(model.getIdList().get(model.getIdList().size() - 1),
                model.getTimeList().get(model.getTimeList().size() - 1));

        dataSeries.getData().add(new XYChart.Data<>(model.getIdList().get(0), model.getTimeList().get(0)));
        dataSeries.getData().add(new XYChart.Data<>(model.getIdList().get(model.getIdList().size() - 1),
                model.getTimeList().get(model.getTimeList().size() - 1)));

        chart.setTitle("Line of Regression: " + new DecimalFormat("#.##").format(a.magnitude()) + " + "
                + new DecimalFormat("#.##").format(b.magnitude()) + " * ID");

        // for (int i = 0; i < model.getIdList().size(); i++) {
        // if (model.getHitList().get(i)) {
        // dataSeries.getData().add(new XYChart.Data<>(model.getIdList().get(i),
        // model.getTimeList().get(i)));
        // }
        // }

        chart.getData().add(dataSeries);

        // chart
        final ScatterChart<Number, Number> scatterChart = new ScatterChart<Number, Number>(
                new NumberAxis("ID", 0, 10, 10), new NumberAxis("TIME", 0, 10, 10));
        scatterChart.setLegendVisible(false);
        final XYChart.Series<Number, Number> scatterSeries = new XYChart.Series<>();

        for (int i = 0; i < model.getIdList().size(); i++) {
            if (model.getHitList().get(i)) {
                scatterSeries.getData().add(new XYChart.Data<>(model.getIdList().get(i), model.getTimeList().get(i)));
            }
        }

        scatterChart.getData().add(scatterSeries);

        final HBox hBox = new HBox(chart, scatterChart);
        root.setCenter(hBox);

        dialog.setScene(dialogScene);
        dialog.show();
    }

}
