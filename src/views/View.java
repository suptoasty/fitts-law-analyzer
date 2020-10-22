package src.views;

import javafx.animation.Animation.Status;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import src.controllers.Controller;
import src.models.Model;

public class View {
    final Stage stage;
    final Model model;
    final Controller controller;
    final MenuBar menu = new MenuBar();
    final Menu view = new Menu("View");
    final MenuItem graph = new MenuItem("Graph");
    final MenuItem info = new MenuItem("Info");

    public View(Stage stage, Model model, Controller controller) {
        this.stage = stage;
        this.model = model;
        this.controller = controller;

        stage.setTitle("Project 3");
        final BorderPane root = new BorderPane();
        final FlowPane flowpane = new FlowPane();

        // menu bar
        VBox vBox = new VBox(menu);
        menu.getMenus().add(view);
        view.getItems().add(graph);
        view.getItems().add(info);
        graph.addEventHandler(Event.ANY, controller.onViewGraph(model, stage));
        info.addEventHandler(Event.ANY, controller.onViewInfo(stage));
        root.setTop(vBox);
        // menu bar

        // game content
        root.setCenter(flowpane);

        // find way to move shape here

        flowpane.getChildren().add(model.shape);
        // model.shape.setTranslateX(model.getRandomShapePoint(flowpane,
        // model.shape).getX());
        // model.shape.setTranslateY(model.getRandomShapePoint(flowpane,
        // model.shape).getY());
        flowpane.addEventHandler(MouseEvent.MOUSE_CLICKED, controller.onShapeClicked(model, flowpane));
        //

        final Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.show();
    }
}