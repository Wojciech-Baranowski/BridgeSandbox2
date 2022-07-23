package gameLogic.game;

import gameLogic.Player;
import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.card.Deck;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static gameLogic.utils.ModuloOperator.modAdd;
import static gameLogic.Player.N;

@Getter
@Setter
public class Game {

    private final MoveValidator moveValidator;
    private final Deck deck;
    private List<Card>[] cards;
    private int[] points;
    private Color atu;
    private Player currentPlayer;
    private Player startingPlayer;
    private List<Card> playedCards;

    public Game() {
        deck = Deck.getDeck();
        this.moveValidator = new MoveValidator(this);
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

    private void makeMove(Card card) {
        playedCards.add(card);
        cards[currentPlayer.ordinal()].remove(card);
        currentPlayer = currentPlayer.nextPlayer();
    }

    private void summarizeRound() {
        currentPlayer = startingPlayer = chooseWinningPlayer();
        points[currentPlayer.ordinal() % (PLAYER_NUMBER / 2)]++;
        playedCards.clear();
    }

    private Player chooseWinningPlayer() {
        boolean isAnyCardAtu = playedCards.stream()
                .map(Card::getColor)
                .anyMatch(c -> c.equals(atu));
        Card winningCard;
        if (isAnyCardAtu) {
            winningCard = playedCards.stream()
                    .filter(c -> c.getColor().equals(atu))
                    .max(Comparator.comparingInt(c -> c.getFigure().ordinal()))
                    .orElse(null);
        } else {
            winningCard = playedCards.stream()
                    .filter(c -> c.getColor().equals(playedCards.get(startingPlayer.ordinal()).getColor()))
                    .max(Comparator.comparingInt(c -> c.getFigure().ordinal()))
                    .orElse(null);
        }
        int indexOfWinningPlayer = modAdd(playedCards.indexOf(winningCard), startingPlayer.ordinal(), PLAYER_NUMBER);
        return Player.values()[indexOfWinningPlayer];
    }

}
