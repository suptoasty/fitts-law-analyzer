package src.controllers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import src.models.Model;
import src.views.GraphView;
import src.views.HelpView;

public class Controller {
    public static EventHandler<Event> onShapeClicked(final Model model, final FlowPane pane) {
        return new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                MouseEvent event = (MouseEvent) arg0; // cast to mouse event
                model.endTime = System.currentTimeMillis();
                // check if hit target
                model.getHitList().add(event.getTarget() instanceof Rectangle);
                System.out.println("Hit Target: " + (event.getTarget() instanceof Rectangle));

                if (model.getClickCount() < 10) {
                    model.setClickCount(model.getClickCount() + 1);

                    // get location data
                    double mouseX = event.getX();
                    double mouseY = event.getY();
                    model.setStartingMouseLocation(model.getEndingMouseLocation());

                    mouseX = ((MouseEvent) arg0).getScreenX();
                    mouseY = ((MouseEvent) arg0).getScreenY();
                    model.setEndingMouseLocation(new Point2D(mouseX, mouseY));

                    // record amplitude
                    Bounds shapeBounds = model.shape.localToScreen(model.shape.getBoundsInLocal());
                    double amp = model.getStartingMouseLocation().distance(model.getEndingMouseLocation());
                    // calc id
                    double id = model.log2((amp / shapeBounds.getWidth()) + 1);

                    double time = (model.endTime - model.startTime) / 1000;

                    model.getIdList().add(id);
                    model.getTimeList().add(time);

                    System.out.println("ID: " + id);

                    System.out.println(model);

                    // make new target
                    model.shape = new Rectangle(200, 200);

                    pane.getChildren().clear();
                    pane.getChildren().add(model.shape);

                    double x = (Math.random() * (pane.getWidth() - 0) + 0);
                    double y = (Math.random() * (pane.getHeight() - 0) + 0);
                    x -= model.shape.getWidth();
                    y -= model.shape.getHeight();
                    model.shape.setTranslateX(x);
                    model.shape.setTranslateY(y);

                } else {
                    pane.getChildren().clear();
                }

                model.startTime = System.currentTimeMillis();
            }

        };

    }

    public static EventHandler<Event> onViewGraph(final Model model, final Stage stage) {
        return new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                System.out.println("On Show Graph Fired!");

                // GraphModel vModel = new GraphModel(Model model);
                GraphController vController = new GraphController();
                GraphView vGraphView = new GraphView(stage, model, vController);
            }

        };

    }

    public static EventHandler<Event> onViewInfo(final Stage stage) {
        return new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                HelpView vHelpView = new HelpView(stage);
            }

        };

    }
}
