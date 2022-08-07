package controllers.main.assets;

import gameLogic.card.Card;

import java.util.Comparator;

import static gameLogic.game.GameConstants.FIGURE_NUMBER;

public class CardComparer {

    private static CardComparer cardComparer;
    private final Comparator<Card> ascendingComparator;
    private final Comparator<Card> descendingComparator;

    private CardComparer() {
        ascendingComparator = Comparator.comparingInt(Card::getId);
        descendingComparator = Comparator.comparingInt(this::getDescendingComparableId);
    }

    public static CardComparer getCardComparer() {
        if(cardComparer == null) {
            cardComparer = new CardComparer();
        }
        return cardComparer;
    }

    public Comparator<Card> getAscendingComparator() {
        return ascendingComparator;
    }

    public Comparator<Card> getDescendingComparator() {
        return descendingComparator;
    }

    private int getDescendingComparableId(Card card) {
        return card.getColor().ordinal() * FIGURE_NUMBER + (FIGURE_NUMBER - card.getFigure().ordinal() - 1);
    }

}
