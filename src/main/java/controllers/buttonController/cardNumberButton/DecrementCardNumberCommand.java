package controllers.buttonController.cardNumberButton;

import engine.common.Command;
import gameLogic.game.Game;

import static controllers.cardController.CardController.getCardController;
import static controllers.historyController.HistoryController.getHistoryController;
import static gameLogic.game.Game.getGame;
import static java.lang.Math.max;

public class DecrementCardNumberCommand implements Command {

    @Override
    public void execute() {
        Game game = getGame();
        game.initializeGame(game.getAtu(), max(1, game.getStartingNumberOfCardsPerPlayer() - 1));
        getHistoryController().removeAllHistoryEntries();
        getCardController().reinitialize();
    }
}
