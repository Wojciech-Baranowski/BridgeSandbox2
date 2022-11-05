package controllers.editGameControllers.buttonController;

import controllers.editGameControllers.cardController.EditGameCardController;
import engine.button.SimpleButton;
import engine.button.radioButton.RadioButtonBundle;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.player.Player;

import java.util.*;
import java.util.stream.Collectors;

import static controllers.editGameControllers.buttonController.EditGameButtonController.getEditGameButtonController;
import static controllers.editGameControllers.cardController.EditGameCardController.getEditGameCardController;
import static controllers.editGameControllers.textController.EditGameTextController.getEditGameTextController;
import static controllers.gameControllers.buttonController.GameButtonController.getGameButtonController;
import static controllers.gameControllers.cardController.GameCardController.getGameCardController;
import static controllers.gameControllers.historyController.GameHistoryController.getGameHistoryController;
import static controllers.gameControllers.textController.GameTextController.getGameTextController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.card.Deck.getDeck;
import static gameLogic.game.Game.getGame;
import static gameLogic.game.GameConstants.*;

public class AcceptChanges {

    private static class AcceptCommand implements Command {

        @Override
        public void execute() {
            if (isGameValid()) {
                getEditGameTextController().getInvalidGameData().hideText();
                getScene().switchCollection("game");
                initializeGame();
                getGameButtonController().getSolverTrigger().getSolverTrigger().setSelected(false);
            } else {
                getEditGameTextController().getInvalidGameData().showText();
            }
        }

        private boolean isGameValid() {
            return areChangersNotNull() && areCardsProperlyChosen();
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

            List<Integer> numbersOfPlayerCards = Arrays.stream(cardController.getChooseCards().getChooseCardsBundles())
                    .filter(rbb -> rbb.getSelectedRadioButtonIndex() >= 0)
                    .collect(Collectors.groupingBy(RadioButtonBundle::getSelectedRadioButtonIndex))
                    .values()
                    .stream()
                    .map(Collection::size)
                    .toList();

            int playersWithAnyCards = numbersOfPlayerCards.size();
            int cardNumber = buttonController.getCardsNumberChanger().getCardNumber();

            int minNumberOfCards = numbersOfPlayerCards
                    .stream()
                    .min(Comparator.naturalOrder())
                    .orElse(MAX_CARDS_PER_PLAYER);

            int maxNumberOfCards = numbersOfPlayerCards
                    .stream()
                    .max(Comparator.naturalOrder())
                    .orElse(0);

            return minNumberOfCards == maxNumberOfCards
                    && maxNumberOfCards == cardNumber
                    && playersWithAnyCards == PLAYER_NUMBER;
        }

        private void initializeGame() {
            getGame().initializeGame(getChosenAtuColor(), getChosenCards(), getChosenPlayer());
            getGameHistoryController().removeAllHistoryEntries();
            getGameCardController().reinitialize();
            getGameTextController().updatePoints();
            getGameTextController().updateAtu();
        }

        private Color getChosenAtuColor() {
            EditGameButtonController buttonController = getEditGameButtonController();
            int colorOrdinal = buttonController
                    .getAtuChanger()
                    .getChooseAtuButtonsBundle()
                    .getSelectedRadioButtonIndex();
            return Color.values()[colorOrdinal];
        }

        private Player getChosenPlayer() {
            EditGameButtonController buttonController = getEditGameButtonController();
            int playerOrdinal = buttonController
                    .getStartingPlayerChanger()
                    .getChooseStartingPlayerButtonsBundle()
                    .getSelectedRadioButtonIndex();
            return Player.values()[playerOrdinal];
        }

        private List<Card>[] getChosenCards() {
            List<Card>[] chosenCards = new List[PLAYER_NUMBER];
            for (int i = 0; i < PLAYER_NUMBER; i++) {
                chosenCards[i] = new ArrayList<>();
            }
            for (int i = 0; i < DECK_SIZE; i++) {
                int cardOwnersOrdinal = getEditGameCardController()
                        .getChooseCards()
                        .getChooseCardsBundles()[i]
                        .getSelectedRadioButtonIndex();
                if (cardOwnersOrdinal >= 0) {
                    chosenCards[cardOwnersOrdinal].add(getDeck().getCard(i));
                }
            }
            return chosenCards;
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

}
