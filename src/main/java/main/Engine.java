package main;

public class Engine {

    public static void initializeEngine() {
        new BeanConfig().buildBeans();
    }

    private Engine(){}

}
