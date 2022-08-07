package controllers.buttonController.restartGameButton;

import engine.common.Command;
import gameLogic.game.Game;

import static controllers.cardController.CardController.getCardController;
import static controllers.historyController.HistoryController.getHistoryController;
import static controllers.textController.TextController.getTextController;
import static gameLogic.game.Game.getGame;

public class RestartGameCommand implements Command {

    @Override
    public void execute() {
        Game game = getGame();
        game.initializeGame(game.getAtu(), game.getStartingNumberOfCardsPerPlayer());
        getHistoryController().removeAllHistoryEntries();
        getCardController().reinitialize();
        getTextController().updatePoints();
    }

}
