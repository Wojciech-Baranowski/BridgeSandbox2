package gameLogic.card;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static gameLogic.game.GameConstants.*;
import static java.lang.Math.min;

@Getter
public class Deck {

    private static Deck deck;
    private final Card[] cards;

    private Deck() {
        cards = new Card[DECK_SIZE];
        for (int i = 0; i < DECK_SIZE; i++) {
            cards[i] = new Card(i);
        }
    }

    public static Deck getDeck() {
        if(deck == null) {
            deck = new Deck();
        }
        return deck;
    }

    public List<Card>[] dealCards(int numberOfCardsPerPlayer) {
        numberOfCardsPerPlayer = min(numberOfCardsPerPlayer, FIGURE_NUMBER);
        List<Card>[] cardsToDeal = new List[PLAYER_NUMBER];
        List<Card> shuffledCards = getShuffledCards();
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            cardsToDeal[i] = new LinkedList<>();
            for (int j = 0; j < numberOfCardsPerPlayer; j++) {
                cardsToDeal[i].add(shuffledCards.get(j + i * numberOfCardsPerPlayer));
            }
        }
        return cardsToDeal;
    }

    private List<Card> getShuffledCards() {
        List<Card> shuffledCards = Arrays.asList(cards);
        Collections.shuffle(shuffledCards);
        return shuffledCards;
    }

    public Card getCard(int id) {
        return cards[id];
    }

    public Card getCard(Figure figure, Color color) {
        int id = figure.ordinal() + color.ordinal() * FIGURE_NUMBER;
        return cards[id];
    }

}
