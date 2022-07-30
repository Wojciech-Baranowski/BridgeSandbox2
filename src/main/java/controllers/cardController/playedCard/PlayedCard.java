package controllers.cardController.playedCard;

import engine.display.Drawable;
import gameLogic.card.Card;
import lombok.Getter;

import static controllers.main.assets.CardDrawables.getCardDrawable;

public class PlayedCard {

    @Getter
    private final Drawable drawable;

    PlayedCard(Card card, int x, int y) {
        drawable = getCardDrawable(card.getId());
        drawable.setX(x);
        drawable.setY(y);
    }

}
