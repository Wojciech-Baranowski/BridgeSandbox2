package gameLogic.card;

import lombok.Getter;

import java.util.List;

import static gameLogic.GameConstants.DECK_SIZE;

@Getter
public class Deck {

    private final Card[] cards;

    public Deck() {
        cards = new Card[DECK_SIZE];
    }

    public List<Card>[] dealCards(int numberOfCardsPerPlayer) {
        return null;
    }

    public Card getCard(int id) {
        return null;
    }

    public Card getCard(Figure figure, Color color) {
        return null;
    }

}
