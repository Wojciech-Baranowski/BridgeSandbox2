package controllers.gameControllers.cardController.handCard;

import controllers.gameControllers.buttonController.GameButtonController;
import controllers.gameControllers.buttonController.SolverTrigger;
import controllers.gameControllers.cardController.GameCardController;
import controllers.gameControllers.historyController.GameHistoryController;
import controllers.gameControllers.textController.GameTextController;
import engine.button.checkbox.CommandCheckbox;
import engine.common.Command;
import gameLogic.card.Card;
import gameLogic.game.Game;

import static controllers.gameControllers.buttonController.GameButtonController.getGameButtonController;
import static controllers.gameControllers.cardController.GameCardController.getGameCardController;
import static controllers.gameControllers.historyController.GameHistoryController.getGameHistoryController;
import static controllers.gameControllers.textController.GameTextController.getGameTextController;
import static gameLogic.game.Game.getGame;

public class HandCardPlayCommand implements Command {

    private final HandCard handCard;

    HandCardPlayCommand(HandCard handCard) {
        this.handCard = handCard;
    }

    @Override
    public void execute() {
        Game game = getGame();
        GameCardController gameCardController = getGameCardController();
        GameTextController gameTextController = getGameTextController();
        GameHistoryController gameHistoryController = getGameHistoryController();
        GameButtonController gameButtonController = getGameButtonController();
        Card card = handCard.getCard();
        if (game.isMoveValid(card)) {
            gameCardController.removeHandCard(handCard, game.getCurrentPlayer());
            gameCardController.repositionPlayerCards(game.getCurrentPlayer());
            gameCardController.addPlayedCard(card, game.getCurrentPlayer());
            game.playCard(card);
            if (game.hasRoundEnded()) {
                gameHistoryController.addHistoryEntry(game);
                gameCardController.removePlayedCards();
                game.summarizeRound();
                gameTextController.updatePoints();
            }
            gameCardController.updateOverlays(game.getCurrentPlayer());
        }
        CommandCheckbox solverTrigger = gameButtonController.getSolverTrigger().getSolverTrigger();
        if (solverTrigger.isSelected()) {
            solverTrigger.getOnCommand().execute();
        }
    }

}
