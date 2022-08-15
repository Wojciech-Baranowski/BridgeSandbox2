package controllers.main.assets;

import gameLogic.card.Card;
import lombok.Getter;

import java.util.Comparator;

import static gameLogic.game.GameConstants.FIGURE_NUMBER;
import static java.util.Comparator.comparingInt;

@Getter
public enum CardComparator {

    ASCENDING(comparingInt(CardComparator::getAscendingComparableId)),
    DESCENDING(comparingInt(CardComparator::getDescendingComparableId)),
    ASCENDING_INTERTWINED(comparingInt(CardComparator::getAscendingIntertwinedComparableId)),
    DESCENDING_INTERTWINED(comparingInt(CardComparator::getDescendingIntertwinedComparableId));

    private final Comparator<Card> comparator;

    CardComparator(Comparator<Card> comparator) {
        this.comparator = comparator;
    }

    public CardComparator getNextComparator() {
        return switch (this) {
            case ASCENDING -> DESCENDING;
            case DESCENDING -> ASCENDING_INTERTWINED;
            case ASCENDING_INTERTWINED -> DESCENDING_INTERTWINED;
            case DESCENDING_INTERTWINED -> ASCENDING;
        };
    }

    private static int getAscendingComparableId(Card card) {
        return card.getId();
    }

    private static int getDescendingComparableId(Card card) {
        return card.getColor().ordinal() * FIGURE_NUMBER + (FIGURE_NUMBER - card.getFigure().ordinal() - 1);
    }

    private static int getAscendingIntertwinedComparableId(Card card) {
        return (card.getId() < FIGURE_NUMBER)
                ? card.getId() + FIGURE_NUMBER
                : (card.getId() < 2 * FIGURE_NUMBER)
                ? card.getId() - FIGURE_NUMBER
                : card.getId();
    }

    private static int getDescendingIntertwinedComparableId(Card card) {
        return (getDescendingComparableId(card) < FIGURE_NUMBER)
                ? getDescendingComparableId(card) + FIGURE_NUMBER
                : (getDescendingComparableId(card) < 2 * FIGURE_NUMBER)
                ? getDescendingComparableId(card) - FIGURE_NUMBER
                : getDescendingComparableId(card);
    }

}
