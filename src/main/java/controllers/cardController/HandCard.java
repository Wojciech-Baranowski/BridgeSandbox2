package controllers.cardController;

import engine.common.Visual;
import gameLogic.card.Card;
import gameLogic.player.Player;

public class HandCard {

    private Visual visual;
    private Card card;
    private Player owner;

    public HandCard(Card card, Player owner) {
        this.card = card;
        this.owner = owner;
    }

}
