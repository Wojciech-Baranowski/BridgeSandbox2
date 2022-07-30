package controllers.cardController;

import engine.display.Drawable;
import gameLogic.card.Card;
import gameLogic.player.Player;
import lombok.Getter;

import static controllers.main.assets.CardDrawables.getCardDrawable;

public class HandCard {

    @Getter
    private final Drawable drawable;
    private final Card card;
    private final Player owner;

    public HandCard(Card card, Player owner, int x, int y) {
        this.card = card;
        this.owner = owner;
        drawable = getCardDrawable(card.getId());
        drawable.getDrawable().setX(x);
        drawable.getDrawable().setY(y);
    }

}
