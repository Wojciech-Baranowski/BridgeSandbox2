package engine.main;

public class Engine {

    public static void initializeEngine() {
        new BeanConfig().buildBeans();
    }

    private Engine(){}

    public static void main(String[] args) {
        initializeEngine();
    }

}
