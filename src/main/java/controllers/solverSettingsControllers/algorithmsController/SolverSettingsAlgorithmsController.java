package controllers.solverSettingsControllers.algorithmsController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static controllers.solverSettingsControllers.backgroundController.SolverSettingsBackgroundController.getSolverSettingsBackgroundController;
import static engine.display.DisplayBean.getDisplay;

public class SolverSettingsAlgorithmsController {

    private static SolverSettingsAlgorithmsController solverSettingsAlgorithmsController;
    @Getter
    private AlgorithmsChanger algorithmsChanger;

    private SolverSettingsAlgorithmsController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable background = getSolverSettingsBackgroundController().getBackground().getBackground();

        algorithmsChanger = new AlgorithmsChanger(drawableFactory, background);
    }

    public static SolverSettingsAlgorithmsController getSolverSettingsAlgorithmsController() {
        if (solverSettingsAlgorithmsController == null) {
            solverSettingsAlgorithmsController = new SolverSettingsAlgorithmsController();
        }
        return solverSettingsAlgorithmsController;
    }

}
