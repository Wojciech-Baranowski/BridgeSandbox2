package gameLogic.game;

import gameLogic.card.Card;
import gameLogic.card.Color;

import java.util.List;

public class MoveValidator {

    private final Game game;

    MoveValidator(Game game) {
        this.game = game;
    }

    boolean isMoveValid(Card card) {
        List<Card> currentPlayerCards = game.getCards()[game.getCurrentPlayer().ordinal()];
        Color colorOfFirstCardPlayed = game.getPlayedCards().get(game.getStartingPlayer().ordinal()).getColor();
        return isCardInPlayerHand(currentPlayerCards, card)
                && isCardInSuitableColor(currentPlayerCards, colorOfFirstCardPlayed, card);
    }

    private boolean isCardInPlayerHand(List<Card> currentPlayerCards, Card card) {
        return currentPlayerCards.contains(card);
    }

    private boolean isCardInSuitableColor(List<Card> currentPlayerCards, Color colorOfFirstCardPlayed, Card card) {
        return isNoneOfPlayerCardsStartingCardColor(currentPlayerCards, colorOfFirstCardPlayed)
                || isCardInFirstCardColor(colorOfFirstCardPlayed, card);
    }

    private boolean isNoneOfPlayerCardsStartingCardColor(List<Card> currentPlayerCards, Color colorOfFirstCardPlayed) {
        return currentPlayerCards.stream()
                .map(Card::getColor)
                .noneMatch(c -> c.equals(colorOfFirstCardPlayed));
    }

    private boolean isCardInFirstCardColor(Color colorOfFirstCardPlayed, Card card) {
        return card.getColor().equals(colorOfFirstCardPlayed);
    }

}
