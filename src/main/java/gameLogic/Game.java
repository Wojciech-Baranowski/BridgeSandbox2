package gameLogic;

import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.card.Deck;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Game {

    private final Deck deck;
    private List<Card>[] cards;
    private int[] points;
    private Color atu;
    private Player currentPlayer;
    private Player startingPlayer;
    private List<Card> playedCards;

    public Game() {
        deck = Deck.getDeck();
    }

    public void initializeGame() {

    }

    public void initializeGame(List<Card>[] cards, Color atu) {

    }

    public void playCard(Card card) {

    }

}
