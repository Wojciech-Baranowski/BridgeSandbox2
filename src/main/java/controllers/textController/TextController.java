package controllers.textController;

import controllers.backgroundController.BackgroundController;
import engine.display.DisplayBean;
import engine.display.DrawableFactory;

import static controllers.backgroundController.BackgroundController.getBackgroundController;

public class TextController {

    private static final DrawableFactory drawableFactory = DisplayBean.getDisplay().getDrawableFactory();
    private static final BackgroundController getBackground = getBackgroundController();
    private static TextController textController;
    private final ProgramTitle programTitle;
    private final Points points;

    private TextController() {
        points = new Points(drawableFactory, getBackground.getBackground().getBackground());
        programTitle = new ProgramTitle(drawableFactory, getBackground.getBackground().getBackground());
    }

    public static TextController getTextController() {
        if (textController == null) {
            textController = new TextController();
        }
        return textController;
    }

    public void updatePoints(int[] points) {
        this.points.updatePoints(points);
    }

}
