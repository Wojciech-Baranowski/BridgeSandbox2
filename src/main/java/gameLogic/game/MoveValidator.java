package gameLogic.game;

import gameLogic.card.Card;
import gameLogic.card.Color;

import java.util.List;

public class MoveValidator {

    MoveValidator() {
    }

    boolean isMoveValid(List<Card> currentPlayerCards, Color colorOfFirstCardPlayed, Card card) {
        return  isCardInPlayerHand(currentPlayerCards, card)
                && isCardInSuitableColor(currentPlayerCards, colorOfFirstCardPlayed, card);
    }

    private boolean isCardInPlayerHand(List<Card> currentPlayerCards, Card card) {
        return currentPlayerCards.contains(card);
    }

    private boolean isCardInSuitableColor(List<Card> currentPlayerCards, Color colorOfFirstCardPlayed, Card card) {
        return isCardFirstInRound(colorOfFirstCardPlayed)
                || isNoneOfPlayerCardsStartingCardColor(currentPlayerCards, colorOfFirstCardPlayed)
                || isCardInStartingCardColor(colorOfFirstCardPlayed, card);
    }

    private boolean isCardFirstInRound(Color colorOfFirstCardPlayed) {
        return colorOfFirstCardPlayed == null;
    }

    private boolean isNoneOfPlayerCardsStartingCardColor(List<Card> currentPlayerCards, Color colorOfFirstCardPlayed) {
        return currentPlayerCards.stream()
                .map(Card::getColor)
                .noneMatch(c -> c.equals(colorOfFirstCardPlayed));
    }

    private boolean isCardInStartingCardColor(Color colorOfFirstCardPlayed, Card card) {
        return card.getColor().equals(colorOfFirstCardPlayed);
    }

}
