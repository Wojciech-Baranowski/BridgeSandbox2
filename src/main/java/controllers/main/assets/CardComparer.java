package controllers.main.assets;

import gameLogic.card.Card;
import lombok.Getter;

import java.util.Comparator;

import static gameLogic.game.GameConstants.FIGURE_NUMBER;

@Getter
public class CardComparer {

    private static CardComparer cardComparer;
    private final Comparator<Card> ascendingComparator;
    private final Comparator<Card> descendingComparator;
    private final Comparator<Card> ascendingIntertwinedComparator;
    private final Comparator<Card> descendingIntertwinedComparator;


    private CardComparer() {
        ascendingComparator = Comparator.comparingInt(Card::getId);
        descendingComparator = Comparator.comparingInt(this::getDescendingComparableId);
        ascendingIntertwinedComparator = Comparator.comparingInt(this::getAscendingIntertwinedComparableId);
        descendingIntertwinedComparator = Comparator.comparingInt(this::getDescendingIntertwinedComparableId);
    }

    public static CardComparer getCardComparer() {
        if(cardComparer == null) {
            cardComparer = new CardComparer();
        }
        return cardComparer;
    }

    public Comparator<Card> getNextComparator(Comparator<Card> currentComparator) {
        if(currentComparator == ascendingComparator) {
            return descendingComparator;
        } else if(currentComparator == descendingComparator) {
            return ascendingIntertwinedComparator;
        } else if(currentComparator == ascendingIntertwinedComparator) {
            return descendingIntertwinedComparator;
        } else {
            return ascendingComparator;
        }
    }

    private int getDescendingComparableId(Card card) {
        return card.getColor().ordinal() * FIGURE_NUMBER + (FIGURE_NUMBER - card.getFigure().ordinal() - 1);
    }

    private int getAscendingIntertwinedComparableId(Card card) {
        return  (card.getId() < FIGURE_NUMBER)
                ? card.getId() + FIGURE_NUMBER
                : (card.getId() < 2 * FIGURE_NUMBER)
                    ? card.getId() - FIGURE_NUMBER
                    : card.getId();
    }

    private int getDescendingIntertwinedComparableId(Card card) {
        return  (getDescendingComparableId(card) < FIGURE_NUMBER)
                ? getDescendingComparableId(card) + FIGURE_NUMBER
                : (getDescendingComparableId(card) < 2 * FIGURE_NUMBER)
                    ? getDescendingComparableId(card) - FIGURE_NUMBER
                    : getDescendingComparableId(card);
    }

}
