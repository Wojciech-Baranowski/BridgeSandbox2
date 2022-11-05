package controllers.probabilityModeControllers.buttonController;

import engine.button.SimpleButton;
import engine.button.radioButton.RadioButtonBundle;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import java.util.Arrays;
import java.util.List;

import static controllers.probabilityModeControllers.buttonController.ProbabilityModeButtonController.getProbabilityModeButtonController;
import static controllers.probabilityModeControllers.cardController.ProbabilityModeCardController.getProbabilityModeCardController;
import static controllers.probabilityModeControllers.textController.ProbabilityModeTextController.getProbabilityModeTextController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class ProbabilitySolverStarter {

    private class StartProbabilitySolverCommand implements Command {

        @Override
        public void execute() {
            if (isGameValid()) {
                getProbabilityModeTextController().getInvalidGameData().hideText();
            } else {
                getProbabilityModeTextController().getInvalidGameData().showText();
            }
        }

    }

    private final SimpleButton probabilitySolverStarter;

    ProbabilitySolverStarter(DrawableFactory drawableFactory, Drawable background) {
        Drawable buttonBackground = drawableFactory.makeFramedRectangle(
                560 + background.getX(),
                594 + background.getY(),
                254,
                64,
                2,
                "gray",
                "lightBlue");

        Drawable buttonText = drawableFactory.makeText(
                "Compute",
                28 + buttonBackground.getX(),
                10 + buttonBackground.getY(),
                "HBE48",
                "black");

        DrawableComposition drawable = new DrawableComposition(buttonBackground, buttonText);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        probabilitySolverStarter =
                new SimpleButton(drawable, activationCombination, new StartProbabilitySolverCommand());
        getScene().addObjectHigherThan(probabilitySolverStarter, background);
    }

    private boolean isGameValid() {
        return isStartingPlayerChosen()
                && hasStartingPlayerAnyCard()
                && arePlayedCardsChosen()
                && doesTurnBelongToNS();
    }

    private boolean isStartingPlayerChosen() {
        return getProbabilityModeButtonController()
                .getStartingPlayerChanger()
                .getChooseStartingPlayerButtonsBundle()
                .getSelectedRadioButtonIndex() >= 0;
    }

    private boolean hasStartingPlayerAnyCard() {
        int startingPlayerId = getProbabilityModeButtonController()
                .getStartingPlayerChanger()
                .getChooseStartingPlayerButtonsBundle()
                .getSelectedRadioButtonIndex() * 2;
        return Arrays.stream(getProbabilityModeCardController()
                        .getChooseCards()
                        .getChooseCardsBundles())
                .map(RadioButtonBundle::getSelectedRadioButtonIndex)
                .anyMatch(index -> index == startingPlayerId);
    }

    private boolean arePlayedCardsChosen() {
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            int playedCardId = getProbabilityModeButtonController()
                    .getPlayedCardsChanger()
                    .getChoosePlayedCardsButtonsBundles()[i]
                    .getSelectedRadioButtonIndex();
            if (playedCardId >= 0 && getProbabilityModeCardController()
                    .getChooseCards()
                    .getChooseCardsBundles()[playedCardId]
                    .getSelectedRadioButtonIndex() != i) {
                return false;
            }
        }
        return true;
    }

    private boolean doesTurnBelongToNS() {
        List<Boolean> playersWhichPlayedCards = Arrays.stream(getProbabilityModeButtonController()
                        .getPlayedCardsChanger()
                        .getChoosePlayedCardsButtonsBundles())
                .map(RadioButtonBundle::getSelectedRadioButtonIndex)
                .map(index -> index >= 0)
                .toList();
        return playersWhichPlayedCards.stream().filter(p -> p).count() < 4
                && (playersWhichPlayedCards.stream().filter(p -> p).count() != 2
                || !playersWhichPlayedCards.get(1)
                || !playersWhichPlayedCards.get(3))
                && (playersWhichPlayedCards.stream().filter(p -> p).count() != 2
                || !playersWhichPlayedCards.get(0)
                || !playersWhichPlayedCards.get(2))
                && (!playersWhichPlayedCards.get(0) || playersWhichPlayedCards.get(1))
                && (!playersWhichPlayedCards.get(2) || playersWhichPlayedCards.get(3));
    }
}
