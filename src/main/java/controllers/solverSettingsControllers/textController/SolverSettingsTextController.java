package controllers.solverSettingsControllers.textController;


import engine.display.Drawable;
import engine.display.DrawableFactory;
import lombok.Getter;

import static controllers.solverSettingsControllers.backgroundController.SolverSettingsBackgroundController.getSolverSettingsBackgroundController;
import static engine.display.DisplayBean.getDisplay;

public class SolverSettingsTextController {

    private static SolverSettingsTextController solverSettingsTextController;
    @Getter
    private final CardNumber cardNumber;
    @Getter
    private final GamesNumber gamesNumber;
    private final Algorithms algorithms;
    @Getter
    private final TimeStatisticsText timeStatisticsText;
    @Getter
    private final VisitedNodesStatisticsText visitedNodesStatisticsText;

    private SolverSettingsTextController() {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        Drawable background = getSolverSettingsBackgroundController().getBackground().getBackground();
        Drawable buttonsSpace = getSolverSettingsBackgroundController().getButtonsSpace().getButtonsSpace();

        cardNumber = new CardNumber(drawableFactory, buttonsSpace);
        gamesNumber = new GamesNumber(drawableFactory, buttonsSpace);
        algorithms = new Algorithms(drawableFactory, background);
        timeStatisticsText = new TimeStatisticsText(drawableFactory, background);
        visitedNodesStatisticsText = new VisitedNodesStatisticsText(drawableFactory, background);
    }

    public static SolverSettingsTextController getSolverSettingsTextController() {
        if (solverSettingsTextController == null) {
            solverSettingsTextController = new SolverSettingsTextController();
        }
        return solverSettingsTextController;
    }

}
