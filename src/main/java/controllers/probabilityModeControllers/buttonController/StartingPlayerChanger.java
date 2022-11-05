package controllers.probabilityModeControllers.buttonController;

import engine.button.radioButton.RadioButton;
import engine.button.radioButton.RadioButtonBundle;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import gameLogic.player.Player;
import lombok.Getter;

import java.util.Arrays;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class StartingPlayerChanger {

    private final RadioButton[] chooseStartingPlayerButtons;
    @Getter
    private final RadioButtonBundle chooseStartingPlayerButtonsBundle;

    StartingPlayerChanger(DrawableFactory drawableFactory, Drawable background) {
        chooseStartingPlayerButtons = new RadioButton[PLAYER_NUMBER / 2];
        for (int i = 0; i < PLAYER_NUMBER / 2; i++) {
            Drawable inactiveButtonBackground = drawableFactory.makeFramedRectangle(
                    270 + 90 * i + background.getX(),
                    600 + background.getY(),
                    60,
                    60,
                    2,
                    "gray",
                    "lightBlue");

            Drawable activeButtonBackground = drawableFactory.makeFramedRectangle(
                    inactiveButtonBackground.getX(),
                    inactiveButtonBackground.getY(),
                    inactiveButtonBackground.getW(),
                    inactiveButtonBackground.getH(), 
                    2,
                    "gray",
                    "yellow");

            Drawable buttonText  = drawableFactory.makeText(
                    Player.values()[i * 2].getSymbolString(),
                    14 + activeButtonBackground.getX(),
                    10 + activeButtonBackground.getY(),
                    "HBE48",
                    "black");

            Drawable inactiveButtonDrawable = new DrawableComposition(inactiveButtonBackground, buttonText);
            Drawable activeButtonDrawable = new DrawableComposition(activeButtonBackground, buttonText);
            InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
            chooseStartingPlayerButtons[i] =
                    new RadioButton(inactiveButtonDrawable, activeButtonDrawable, activationCombination);

            getScene().addObjectHigherThan(chooseStartingPlayerButtons[i], background);
        }
        chooseStartingPlayerButtonsBundle = new RadioButtonBundle(Arrays.stream(chooseStartingPlayerButtons).toList());
        chooseStartingPlayerButtonsBundle.update(chooseStartingPlayerButtons[0]);
    }

}
