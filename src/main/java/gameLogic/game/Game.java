package gameLogic.game;

import gameLogic.Player;
import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.card.Deck;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static gameLogic.Player.N;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

@Getter
@Setter
public class Game {

    private final MoveValidator moveValidator;
    private final RoundJudge roundJudge;
    private final Deck deck;
    private List<Card>[] cards;
    private int[] points;
    private Color atu;
    private Player currentPlayer;
    private Player startingPlayer;
    private List<Card> playedCards;

    public Game() {
        deck = Deck.getDeck();
        this.moveValidator = new MoveValidator();
        this.roundJudge = new RoundJudge();
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
        List<Card> playerCards = cards[currentPlayer.ordinal()];
        Color colorOfFirstCardPlayed = playerCards.isEmpty() ? null : playedCards.get(0).getColor();
        if (moveValidator.isMoveValid(playerCards, colorOfFirstCardPlayed, card)) {
            makeMove(card);
        }
        if (playerCards.size() == PLAYER_NUMBER) {
            summarizeRound();
        }
    }

    private void makeMove(Card card) {
        playedCards.add(card);
        cards[currentPlayer.ordinal()].remove(card);
        currentPlayer = currentPlayer.nextPlayer();
    }

    private void summarizeRound() {
        currentPlayer = startingPlayer = roundJudge.chooseWinningPlayer(playedCards, startingPlayer, atu);
        points[currentPlayer.ordinal() % (PLAYER_NUMBER / 2)]++;
        playedCards.clear();
    }

}
