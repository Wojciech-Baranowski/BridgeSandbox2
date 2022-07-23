package gameLogic;

import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.card.Deck;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static gameLogic.GameConstants.PLAYER_NUMBER;
import static gameLogic.Player.N;

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

    public void initializeGame(Color atu, int numberOfCardsPerPlayer) {
        List<Card>[] cards = deck.dealCards(numberOfCardsPerPlayer);
        initializeGame(atu, cards);
    }

    public void initializeGame(Color atu, List<Card>[] cards) {
        this.cards = cards;
        this.points = new int[PLAYER_NUMBER / 2];
        this.atu = atu;
        this.currentPlayer = N;
        this.startingPlayer = N;
        this.playedCards = new LinkedList<>();
        Arrays.fill(points, 0);
    }

    public void playCard(Card card) {

    }

}
