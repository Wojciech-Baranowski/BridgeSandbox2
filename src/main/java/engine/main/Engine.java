package engine.main;

import engine.input.InputBean;
import engine.scene.SceneBean;

public class Engine {

    private static Engine engine;

    public void initializeEngine() {
        new BeanConfig().buildBeans();
    }

    public void initializeListeners() {
        InputBean.getInput().initializeListeners();
        SceneBean.getScene().initializeListeners();
    }

    public static Engine getEngine() {
        if (engine == null) {
            engine = new Engine();
        }
        return engine;
    }

    private Engine() {
    }

    public static void main(String[] args) {
        new Engine().initializeEngine();
    }

}
