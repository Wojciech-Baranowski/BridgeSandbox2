import engine.main.Engine;
import controllers.main.Controller;

public class Main {

    public static void main(String[] args) {
        Engine.getEngine().initializeEngine();
        Controller.getController().initializeController();
        Engine.getEngine().initializeListeners();
    }

}
