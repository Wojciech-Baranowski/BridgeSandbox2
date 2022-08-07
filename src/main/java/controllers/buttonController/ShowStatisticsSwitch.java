package controllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class ShowStatisticsSwitch {

    private static class SwitchToStatisticsCommand implements Command {

        @Override
        public void execute() {

        }

    }

    private final SimpleButton showStatisticsSwitch;

    ShowStatisticsSwitch(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                10 + buttonsSpace.getX(),
                470 + buttonsSpace.getY(),
                250,
                40,
                2,
                "gray",
                "lightBlue");

        Drawable text = drawableFactory.makeText(
                "Show statistics",
                8 + background.getX(),
                6 + background.getY(),
                "HBE32",
                "black");

        DrawableComposition drawable = new DrawableComposition(background, text);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        showStatisticsSwitch = new SimpleButton(drawable, activationCombination, new SwitchToStatisticsCommand());
        getScene().addObjectHigherThan(showStatisticsSwitch, buttonsSpace);
    }

}
