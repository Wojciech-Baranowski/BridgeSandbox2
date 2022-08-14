package controllers.editGameControllers.buttonController;

import controllers.editGameControllers.cardController.EditGameCardController;
import engine.button.SimpleButton;
import engine.button.radioButton.RadioButtonBundle;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import java.util.*;
import java.util.stream.Collectors;

import static controllers.editGameControllers.buttonController.EditGameButtonController.getEditGameButtonController;
import static controllers.editGameControllers.cardController.EditGameCardController.getEditGameCardController;
import static controllers.editGameControllers.textController.EditGameTextController.getEditGameTextController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.MAX_CARDS_PER_PLAYER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static java.lang.Math.max;

public class AcceptChanges {

    private class AcceptCommand implements Command {

        @Override
        public void execute() {
            if (isGameValid()) {
                getScene().switchCollection("game");
            } else {
                getEditGameTextController().getInvalidGameData().showText();
            }
        }

    }

    private final SimpleButton acceptChanges;

    AcceptChanges(DrawableFactory drawableFactory, Drawable background) {
        Drawable buttonBackground = drawableFactory.makeFramedRectangle(
                910 + background.getX(),
                546 + background.getY(),
                248,
                100,
                2,
                "gray",
                "lightBlue");

        Drawable buttonText = drawableFactory.makeText(
                "ACCEPT",
                29 + buttonBackground.getX(),
                28 + buttonBackground.getY(),
                "HBE48",
                "black");

        Drawable buttonDrawable = new DrawableComposition(buttonBackground, buttonText);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        acceptChanges = new SimpleButton(buttonDrawable, activationCombination, new AcceptCommand());
        getScene().addObjectHigherThan(acceptChanges, background);
    }

    private boolean isGameValid() {
        return areCardsProperlyChosen() && areChangersNotNull();
    }

    private boolean areChangersNotNull() {
        EditGameButtonController buttonController = getEditGameButtonController();
        return buttonController.getAtuChanger()
                .getChooseAtuButtonsBundle()
                .getSelectedRadioButtonIndex() >= 0
                && buttonController.getStartingPlayerChanger()
                .getChooseStartingPlayerButtonsBundle()
                .getSelectedRadioButtonIndex() >= 0;
    }

    private boolean areCardsProperlyChosen() {
        EditGameCardController cardController = getEditGameCardController();
        EditGameButtonController buttonController = getEditGameButtonController();

        return Arrays.stream(cardController.getChooseCards().getChooseCardsBundles())
                .filter(rbb -> rbb.getSelectedRadioButtonIndex() >= 0)
                .collect(Collectors.groupingBy(RadioButtonBundle::getSelectedRadioButtonIndex))
                .values()
                .stream()
                .map(Collection::size)
                .max(Comparator.naturalOrder())
                .orElse(0) <= buttonController.getCardsNumberChanger().getCardNumber();
    }

}
