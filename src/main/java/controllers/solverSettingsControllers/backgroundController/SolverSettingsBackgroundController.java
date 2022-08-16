package controllers.solverSettingsControllers.backgroundController;

import engine.display.DrawableFactory;
import lombok.Getter;

import static engine.display.DisplayBean.getDisplay;

public class SolverSettingsBackgroundController {

    private static SolverSettingsBackgroundController solverSettingsBackgroundController;
    @Getter
    private final Background background;
    @Getter
    private final ButtonsSpace buttonsSpace;

    private SolverSettingsBackgroundController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        background = new Background(drawableFactory);
        buttonsSpace = new ButtonsSpace(drawableFactory, background.getBackground());
    }

    public static SolverSettingsBackgroundController getSolverSettingsBackgroundController() {
        if (solverSettingsBackgroundController == null) {
            solverSettingsBackgroundController = new SolverSettingsBackgroundController();
        }
        return solverSettingsBackgroundController;
    }

}
