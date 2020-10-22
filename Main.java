
import javafx.application.Application;
import javafx.stage.Stage;

import src.controllers.Controller;
import src.models.Model;
import src.views.View;

public class Main extends Application {
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        Model model = new Model();
        Controller controller = new Controller();
        View view = new View(stage, model, controller);

    }

}