package controllers.buttonController.nextPlayerButton;

import engine.common.Command;
import gameLogic.game.Game;

import static controllers.cardController.CardController.getCardController;
import static gameLogic.game.Game.getGame;

public class SwitchToNextPlayerCommand implements Command {

    @Override
    public void execute() {
        Game game = getGame();
        game.switchToNextPlayer();
        getCardController().updateOverlays(game.getCurrentPlayer());
    }
}
