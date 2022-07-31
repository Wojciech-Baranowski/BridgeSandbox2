package controllers.cardController.handCard;

import controllers.backgroundController.BackgroundController;
import controllers.cardController.CardController;
import engine.common.Command;
import gameLogic.card.Card;
import gameLogic.game.Game;

import static controllers.backgroundController.BackgroundController.getBackgroundController;
import static controllers.cardController.CardController.getCardController;
import static gameLogic.game.Game.getGame;

public class HandCardPlayCommand implements Command {

    private final HandCard handCard;

    HandCardPlayCommand(HandCard handCard) {
        this.handCard = handCard;
    }

    @Override
    public void execute() {
        Game game = getGame();
        CardController cardController = getCardController();
        BackgroundController backgroundController = getBackgroundController();
        Card card = handCard.getCard();
        if (game.isMoveValid(card)) {
            cardController.removeHandCard(handCard, game.getCurrentPlayer());
            cardController.repositionPlayerCards(game.getCurrentPlayer());
            cardController.addPlayedCard(card, game.getCurrentPlayer());
            game.playCard(card);
            if (game.hasRoundEnded()) {
                cardController.removePlayedCards();
                game.summarizeRound();
            }
            cardController.updateOverlays();
            backgroundController.updateOverlays(game.getCurrentPlayer());
        }
    }

}
