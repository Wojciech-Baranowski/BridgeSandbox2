package controllers.buttonController.cardNumberButton;

import engine.common.Command;
import gameLogic.game.Game;

import static controllers.cardController.CardController.getCardController;
import static controllers.historyController.HistoryController.getHistoryController;
import static gameLogic.game.Game.getGame;
import static gameLogic.game.GameConstants.DECK_SIZE;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static java.lang.Math.min;

public class IncrementCardNumberCommand implements Command {

    @Override
    public void execute() {
        Game game = getGame();
        game.initializeGame(game.getAtu(), min(DECK_SIZE / PLAYER_NUMBER, game.getStartingNumberOfCardsPerPlayer() + 1));
        getHistoryController().removeAllHistoryEntries();
        getCardController().reinitialize();
    }

}
