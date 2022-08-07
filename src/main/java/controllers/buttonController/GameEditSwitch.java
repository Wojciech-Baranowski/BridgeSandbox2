package controllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class GameEditSwitch {

    private static class SwitchToEditGameCommand implements Command {

        @Override
        public void execute() {

        }

    }

    private final SimpleButton gameEditSwitch;

    GameEditSwitch(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                10 + buttonsSpace.getX(),
                320 + buttonsSpace.getY(),
                166,
                40,
                2,
                "gray",
                "lightBlue");

        Drawable text = drawableFactory.makeText(
                "Edit game",
                8 + background.getX(),
                6 + background.getY(),
                "HBE32",
                "black");

        DrawableComposition drawable = new DrawableComposition(background, text);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        gameEditSwitch = new SimpleButton(drawable, activationCombination, new SwitchToEditGameCommand());
        getScene().addObjectHigherThan(gameEditSwitch, buttonsSpace);
    }

}
