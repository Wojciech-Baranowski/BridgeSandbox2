package controllers.solverSettingsControllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class StatisticsSwitch {

    private static class SwitchToStatisticsCommand implements Command {

        @Override
        public void execute() {

        }

    }

    private final SimpleButton statisticsSwitch;

    StatisticsSwitch(DrawableFactory drawableFactory, Drawable background) {
        Drawable buttonBackground = drawableFactory.makeFramedRectangle(
                944 + background.getX(),
                594 + background.getY(),
                238,
                64,
                2,
                "gray",
                "lightBlue");

        Drawable buttonText = drawableFactory.makeText(
                "Statistics",
                18 + buttonBackground.getX(),
                10 + buttonBackground.getY(),
                "HBE48",
                "black");

        DrawableComposition drawable = new DrawableComposition(buttonBackground, buttonText);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        statisticsSwitch = new SimpleButton(drawable, activationCombination, new SwitchToStatisticsCommand());
        getScene().addObjectHigherThan(statisticsSwitch, background);
    }

}
