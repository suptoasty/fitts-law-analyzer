package src.models;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.sound.midi.SysexMessage;

import javafx.geometry.Point2D;
import javafx.scene.effect.Light.Point;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Model {
    private ArrayList<Observer> observers = new ArrayList<>();
    private Point2D startingMouseLocation = new Point2D(0, 0);
    private Point2D endingMouseLocation = new Point2D(0, 0);

    private ArrayList<Double> idList = new ArrayList<>();
    private ArrayList<Double> timeList = new ArrayList<>();

    public Rectangle shape = new Rectangle(200, 200);
    private ArrayList<Boolean> hitList = new ArrayList<>();

    public double startTime = System.currentTimeMillis();
    public double endTime = System.currentTimeMillis();
    private int clickCount = 0;

    public static double log2(double N) {
        return (double) (Math.log((double) N) / Math.log(2));
    }

    public ArrayList<Boolean> getHitList() {
        return hitList;
    }

    public void setHitList(ArrayList<Boolean> hitList) {
        this.hitList = hitList;
    }

    public ArrayList<Double> getTimeList() {
        return timeList;
    }

    public void setTimeList(ArrayList<Double> timeList) {
        this.timeList = timeList;
    }

    public ArrayList<Double> getIdList() {
        return idList;
    }

    public void setIdList(ArrayList<Double> idList) {
        this.idList = idList;
    }

    public void addObserver(final Observer observer) {
        observers.add(observer);
    }

    public Point2D getEndingMouseLocation() {
        return endingMouseLocation;
    }

    public void setEndingMouseLocation(Point2D endingMouseLocation) {
        this.endingMouseLocation = endingMouseLocation;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public Point2D getStartingMouseLocation() {
        return startingMouseLocation;
    }

    public void setStartingMouseLocation(Point2D startingMouseLocation) {
        this.startingMouseLocation = startingMouseLocation;
    }

    public void updateObserver() {
        observers.stream().forEach(o -> o.update());
    }

    public static interface Observer {
        void update();
    }

    public Point2D getRandomPoint(FlowPane pane) {
        double x = (Math.random() * (pane.getWidth() - 0) + 0);
        double y = (Math.random() * (pane.getHeight() - 0) + 0);

        return new Point2D(x, y);
    }

    public Point2D getRandomShapePoint(FlowPane pane, Rectangle shape) {
        Point2D point = getRandomPoint(pane);
        return point.subtract(new Point2D(shape.getWidth(), shape.getHeight()));
    }

    public void translateShape(Rectangle shape, Point2D newLoc) {
        shape.setTranslateX(newLoc.getX());
        shape.setTranslateY(newLoc.getY());
    }

    public String toString() {
        return "Mouse Start: " + startingMouseLocation + " | Mouse End: " + endingMouseLocation + " | Click Count: "
                + clickCount;
    }

}
