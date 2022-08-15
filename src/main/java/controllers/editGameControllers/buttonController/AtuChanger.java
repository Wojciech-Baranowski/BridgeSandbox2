package controllers.editGameControllers.buttonController;

import engine.button.radioButton.RadioButton;
import engine.button.radioButton.RadioButtonBundle;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import gameLogic.card.Color;
import lombok.Getter;

import java.util.Arrays;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.card.Color.DIAMOND;
import static gameLogic.card.Color.HEART;
import static gameLogic.game.GameConstants.COLOR_NUMBER;

public class AtuChanger {

    private final RadioButton[] chooseAtuButtons;
    @Getter
    private final RadioButtonBundle chooseAtuButtonsBundle;

    AtuChanger(DrawableFactory drawableFactory, Drawable background) {
        chooseAtuButtons = new RadioButton[COLOR_NUMBER + 1];
        for (int i = 0; i < COLOR_NUMBER + 1; i++) {
            Drawable inactiveButtonBackground = drawableFactory.makeFramedRectangle(
                    200 + 70 * i + background.getX(),
                    565,
                    60,
                    60,
                    2,
                    "gray",
                    "lightBlue");

            Drawable activeButtonBackground = drawableFactory.makeFramedRectangle(
                    200 + 70 * i + background.getX(),
                    565,
                    60,
                    60,
                    2,
                    "gray",
                    "yellow");

            Drawable buttonText = drawableFactory.makeText(
                    Color.values()[i].getSymbolString(),
                    6 + activeButtonBackground.getX(),
                    6 + activeButtonBackground.getY(),
                    "HBE48",
                    (i == DIAMOND.ordinal() || i == HEART.ordinal() ? "red" : "black"));

            Drawable inactiveButtonDrawable = new DrawableComposition(inactiveButtonBackground, buttonText);
            Drawable activeButtonDrawable = new DrawableComposition(activeButtonBackground, buttonText);
            InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
            chooseAtuButtons[i] = new RadioButton(inactiveButtonDrawable, activeButtonDrawable, activationCombination);

            getScene().addObjectHigherThan(chooseAtuButtons[i], background);
        }
        chooseAtuButtonsBundle = new RadioButtonBundle(Arrays.stream(chooseAtuButtons).toList());
    }

    public void updateChooseAtuButtonsBundle(Color color) {
        chooseAtuButtonsBundle.unset();
        chooseAtuButtonsBundle.update(chooseAtuButtons[color.ordinal()]);
    }

}
