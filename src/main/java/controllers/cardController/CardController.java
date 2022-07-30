package controllers.cardController;

import controllers.backgroundController.HandCardSpace;
import gameLogic.game.Game;

public class CardController {

    private final Game game;
    private final HandCardSpace handCardSpace;
    private final HandCards handCards;
    public CardController(Game game, HandCardSpace handCardSpace) {
        this.game = game;
        this.handCardSpace = handCardSpace;
        handCards = new HandCards(handCardSpace.getHandCardSlots());
        handCards.initializeHandsCards(game, handCardSpace);
    }

}
