package engine.main;

public class Logger {

    public static final boolean LOG = true;

    public static void log(String text) {
        if (LOG) {
            System.out.println(text);
        }
    }

}
