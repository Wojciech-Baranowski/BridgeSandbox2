import engine.main.Engine;
import view.main.View;

public class Main {

    public static void main(String[] args) {
        Engine.getEngine().initializeEngine();
        View.getView().initializeView();
        Engine.getEngine().initializeListeners();
    }

}
