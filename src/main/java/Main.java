import engine.main.Engine;
import controllers.main.View;

public class Main {

    public static void main(String[] args) {
        Engine.getEngine().initializeEngine();
        View.getView().initializeView();
        Engine.getEngine().initializeListeners();
    }

}
