package controllers.editGameControllers.buttonController;

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
        chooseStartingPlayerButtons = new RadioButton[PLAYER_NUMBER];
        int[] textX = {14, 16, 14, 8};
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            Drawable inactiveButtonBackground = drawableFactory.makeFramedRectangle(
                    270 + 70 * i + background.getX(),
                    477,
                    60,
                    60,
                    2,
                    "gray",
                    "lightBlue");

            Drawable activeButtonBackground = drawableFactory.makeFramedRectangle(
                    270 + 70 * i + background.getX(),
                    477,
                    60,
                    60,
                    2,
                    "gray",
                    "yellow");

            Drawable buttonText  = drawableFactory.makeText(
                    Player.values()[i].getSymbolString(),
                    textX[i] + activeButtonBackground.getX(),
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
    }

    public void updateChooseStartingPlayerButtons(Player player) {
        chooseStartingPlayerButtonsBundle.unset();
        chooseStartingPlayerButtonsBundle.update(chooseStartingPlayerButtons[player.ordinal()]);
    }

}
