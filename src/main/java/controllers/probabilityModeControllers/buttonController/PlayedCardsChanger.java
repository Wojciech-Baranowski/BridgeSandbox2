package controllers.probabilityModeControllers.buttonController;

import engine.button.radioButton.RadioButton;
import engine.button.radioButton.RadioButtonBundle;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import lombok.Getter;

import java.util.Arrays;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.FIGURE_NUMBER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class PlayedCardsChanger {

    private final RadioButton[][] choosePlayedCardsButtons;
    @Getter
    private final RadioButtonBundle[] choosePlayedCardsButtonsBundles;

    PlayedCardsChanger(DrawableFactory drawableFactory, Drawable[] cardSlots) {
        choosePlayedCardsButtons = new RadioButton[PLAYER_NUMBER][FIGURE_NUMBER];
        choosePlayedCardsButtonsBundles = new RadioButtonBundle[PLAYER_NUMBER];
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            for (int j = 0; j < FIGURE_NUMBER; j++) {
                Drawable inactiveButton = drawableFactory.makeFramedRectangle(
                        17 * j + 2 + cardSlots[i].getX(),
                        -15 + cardSlots[i].getY(),
                        17,
                        17,
                        2,
                        "gray",
                        "lightBlue");

                Drawable activeButton = drawableFactory.makeFramedRectangle(
                        inactiveButton.getX(),
                        inactiveButton.getY(),
                        inactiveButton.getW(),
                        inactiveButton.getH(),
                        2,
                        "gray",
                        "yellow");
                InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
                choosePlayedCardsButtons[i][j] = new RadioButton(inactiveButton, activeButton, activationCombination);
                getScene().addObjectHigherThan(choosePlayedCardsButtons[i][j], cardSlots[i]);
            }
            choosePlayedCardsButtonsBundles[i] =
                    new RadioButtonBundle(Arrays.stream(choosePlayedCardsButtons[i]).toList());
        }
    }
}
