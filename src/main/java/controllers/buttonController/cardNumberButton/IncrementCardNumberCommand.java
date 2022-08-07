package controllers.buttonController.cardNumberButton;

import engine.common.Command;
import gameLogic.game.Game;

import static controllers.cardController.CardController.getCardController;
import static controllers.historyController.HistoryController.getHistoryController;
import static controllers.textController.TextController.getTextController;
import static gameLogic.game.Game.getGame;
import static gameLogic.game.GameConstants.MAX_CARDS_PER_PLAYER;
import static java.lang.Math.min;

public class IncrementCardNumberCommand implements Command {

    @Override
    public void execute() {
        Game game = getGame();
        game.initializeGame(game.getAtu(), min(MAX_CARDS_PER_PLAYER, game.getStartingNumberOfCardsPerPlayer() + 1));
        getHistoryController().removeAllHistoryEntries();
        getCardController().reinitialize();
        getTextController().updatePoints();
    }

}
