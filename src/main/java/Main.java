import engine.main.Engine;
import controllers.main.Initializer;

public class Main {

    public static void main(String[] args) {
        Engine.getEngine().initializeEngine();
        Initializer.getInitializer().initializeController();
        Engine.getEngine().initializeListeners();
    }

}
