package controllers.probabilityModeControllers.buttonController;

import engine.button.SimpleButton;
import engine.button.radioButton.RadioButtonBundle;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.card.Figure;
import gameLogic.player.Player;
import lombok.Getter;
import solver.probabilitySolver.CardProbability;
import solver.probabilitySolver.ProbabilitySolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static controllers.probabilityModeControllers.buttonController.ProbabilityModeButtonController.getProbabilityModeButtonController;
import static controllers.probabilityModeControllers.cardController.ProbabilityModeCardController.getProbabilityModeCardController;
import static controllers.probabilityModeControllers.textController.ProbabilityModeTextController.getProbabilityModeTextController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.card.Deck.getDeck;
import static gameLogic.game.GameConstants.FIGURE_NUMBER;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class ProbabilitySolverStarter {

    private class StartProbabilitySolverCommand implements Command {

        @Override
        public void execute() {
            if (isGameValid()) {
                getProbabilityModeTextController().getInvalidGameData().hideText();
                List<Card>[] cards = getChosenCards();
                Card[] playedCards = getPlayedCards();
                List<Card> remainingCards = getRemainingCards(cards);
                Player startingPlayer = getStartingPlayer();
                probabilitySolver.solve(cards, playedCards, remainingCards, startingPlayer);
                List<CardProbability>[] probabilities = probabilitySolver.getLastProbabilities();
                List<CardProbability>[] probabilitiesSums = probabilitySolver.getLastProbabilitiesSums();
                int lostTricks = probabilitySolver.getLostTricksNumber();
                getProbabilityModeTextController().getProbabilities()
                        .updateProbabilities(probabilities[lostTricks], probabilitiesSums[lostTricks]);
            } else {
                getProbabilityModeTextController().getInvalidGameData().showText();
            }
        }

        private boolean isGameValid() {
            return isStartingPlayerChosen()
                    && hasStartingPlayerAnyCard()
                    && arePlayedCardsChosen()
                    && doesTurnBelongToRightPlayer();
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

        private boolean doesTurnBelongToRightPlayer() {
            List<Boolean> playersWhichPlayedCards = Arrays.stream(getProbabilityModeButtonController()
                            .getPlayedCardsChanger()
                            .getChoosePlayedCardsButtonsBundles())
                    .map(RadioButtonBundle::getSelectedRadioButtonIndex)
                    .map(index -> index >= 0)
                    .toList();
            return playersWhichPlayedCards.stream().filter(p -> p).count() < 4
                    && (playersWhichPlayedCards.stream().filter(p -> p).count() != 2 || !playersWhichPlayedCards.get(1) || !playersWhichPlayedCards.get(3))
                    && (playersWhichPlayedCards.stream().filter(p -> p).count() != 2 || !playersWhichPlayedCards.get(0) || !playersWhichPlayedCards.get(2))
                    && (!playersWhichPlayedCards.get(0) || playersWhichPlayedCards.get(1))
                    && (!playersWhichPlayedCards.get(2) || playersWhichPlayedCards.get(3))
                    && (playersWhichPlayedCards.get(getProbabilityModeButtonController()
                    .getStartingPlayerChanger()
                    .getChooseStartingPlayerButtonsBundle()
                    .getSelectedRadioButtonIndex() * 2) || playersWhichPlayedCards.stream().noneMatch(p -> p));
        }

        private List<Card>[] getChosenCards() {
            List<Card>[] cards = new List[PLAYER_NUMBER + 1];
            for (int i = 0; i < PLAYER_NUMBER + 1; i++) {
                cards[i] = new ArrayList<>();
            }
            for (int i = 0; i < FIGURE_NUMBER; i++) {
                int ownerId = getProbabilityModeCardController()
                        .getChooseCards()
                        .getChooseCardsBundles()[i]
                        .getSelectedRadioButtonIndex();
                if (ownerId >= 0) {
                    cards[ownerId].add(getDeck().getCard(Figure.values()[i], Color.HEART));
                }
            }
            return cards;
        }

        private Card[] getPlayedCards() {
            Card[] cards = new Card[PLAYER_NUMBER];
            for (int i = 0; i < PLAYER_NUMBER; i++) {
                int index = getProbabilityModeButtonController()
                        .getPlayedCardsChanger()
                        .getChoosePlayedCardsButtonsBundles()[i]
                        .getSelectedRadioButtonIndex();
                if (index >= 0) {
                    cards[i] = getDeck().getCard(Figure.values()[index], Color.HEART);
                }
            }
            return cards;
        }

        private List<Card> getRemainingCards(List<Card>[] cards) {
            List<Card> allCards = new ArrayList<>();
            List<Card> playedCards = new ArrayList<>();
            for (int i = 0; i < FIGURE_NUMBER; i++) {
                allCards.add(getDeck().getCard(Figure.values()[i], Color.HEART));
                if (getProbabilityModeCardController()
                        .getChooseCards()
                        .getChooseCardsBundles()[i]
                        .getSelectedRadioButtonIndex() == PLAYER_NUMBER) {
                    playedCards.add(getDeck().getCard(Figure.values()[i], Color.HEART));
                }
            }
            for (int i = 0; i < PLAYER_NUMBER; i++) {
                allCards.removeAll(cards[i]);
            }
            allCards.removeAll(playedCards);
            return allCards;
        }

        private Player getStartingPlayer() {
            return Player.values()[getProbabilityModeButtonController()
                    .getStartingPlayerChanger()
                    .getChooseStartingPlayerButtonsBundle()
                    .getSelectedRadioButtonIndex() * 2];
        }

    }

    @Getter
    private final ProbabilitySolver probabilitySolver;
    private final SimpleButton probabilitySolverStarter;

    ProbabilitySolverStarter(DrawableFactory drawableFactory, Drawable background) {
        probabilitySolver = new ProbabilitySolver();
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

}
