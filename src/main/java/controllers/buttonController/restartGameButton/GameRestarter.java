package controllers.buttonController.restartGameButton;

import engine.button.SimpleButton;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class GameRestarter {

    private final SimpleButton gameRestarter;

    public GameRestarter(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                10 + buttonsSpace.getX(),
                220 + buttonsSpace.getY(),
                220,
                40,
                2,
                "gray",
                "lightBlue");

        Drawable text = drawableFactory.makeText(
                "Restart game",
                8 + background.getX(),
                6 + background.getY(),
                "HBE32",
                "black");

        DrawableComposition drawable = new DrawableComposition(background, text);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        gameRestarter = new SimpleButton(drawable, activationCombination, new RestartGameCommand());
        getScene().addObjectHigherThan(gameRestarter, buttonsSpace);
    }

}
