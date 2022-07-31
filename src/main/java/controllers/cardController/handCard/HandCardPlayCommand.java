package controllers.cardController.handCard;

import controllers.backgroundController.BackgroundController;
import controllers.cardController.CardController;
import controllers.historyController.HistoryController;
import controllers.textController.TextController;
import engine.common.Command;
import gameLogic.card.Card;
import gameLogic.game.Game;

import static controllers.backgroundController.BackgroundController.getBackgroundController;
import static controllers.cardController.CardController.getCardController;
import static controllers.historyController.HistoryController.getHistoryController;
import static controllers.textController.TextController.getTextController;
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
        TextController textController = getTextController();
        HistoryController historyController = getHistoryController();
        Card card = handCard.getCard();
        if (game.isMoveValid(card)) {
            cardController.removeHandCard(handCard, game.getCurrentPlayer());
            cardController.repositionPlayerCards(game.getCurrentPlayer());
            cardController.addPlayedCard(card, game.getCurrentPlayer());
            game.playCard(card);
            if (game.hasRoundEnded()) {
                historyController.addHistoryEntry(game);
                cardController.removePlayedCards();
                game.summarizeRound();
                textController.updatePoints(game.getPoints());
            }
            cardController.updateOverlays();
            backgroundController.updateOverlays(game.getCurrentPlayer());
        }
    }

}
