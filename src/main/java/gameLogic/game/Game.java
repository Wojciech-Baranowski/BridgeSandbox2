package gameLogic.game;

import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.card.Deck;
import gameLogic.player.Player;
import lombok.Getter;

import java.util.*;
import java.util.stream.Stream;

import static gameLogic.card.Deck.getDeck;
import static gameLogic.game.GameConstants.DECK_SIZE;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static gameLogic.player.Player.N;

public class Game {

    private static Game game;
    private static Game lastGame;
    private final MoveValidator moveValidator;
    private final RoundJudge roundJudge;
    private final Deck deck;
    @Getter
    private List<Card>[] cards;
    @Getter
    private int[] points;
    @Getter
    private Color atu;
    @Getter
    private Player currentPlayer;
    @Getter
    private Player startingPlayer;
    @Getter
    private List<Card> playedCards;
    @Getter
    private int startingNumberOfCardsPerPlayer;

    private Game() {
        deck = getDeck();
        moveValidator = new MoveValidator();
        roundJudge = new RoundJudge();
        startingNumberOfCardsPerPlayer = DECK_SIZE / PLAYER_NUMBER;
    }

    public Game(Game game) {
        deck = getDeck();
        moveValidator = new MoveValidator();
        roundJudge = new RoundJudge();
        cards = new List[PLAYER_NUMBER];
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            cards[i] = new ArrayList<>(game.getCards()[i]);
        }
        points = new int[PLAYER_NUMBER / 2];
        points[0] = game.getPoints()[0];
        points[1] = game.getPoints()[1];
        atu = game.getAtu();
        currentPlayer = game.getCurrentPlayer();
        startingPlayer = game.getStartingPlayer();
        playedCards = new ArrayList<>(game.getPlayedCards());
        startingNumberOfCardsPerPlayer = game.getStartingNumberOfCardsPerPlayer();
    }

    public static Game getGame() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }

    public void switchBackToLastGame() {
        if(lastGame != null) {
            game = new Game(lastGame);
        }
    }

    public static Game getGameMultipliedInstance() {
        return new Game();
    }

    public void initializeGame(Color atu, int numberOfCardsPerPlayer) {
        List<Card>[] cards = deck.dealCards(numberOfCardsPerPlayer);
        startingNumberOfCardsPerPlayer = numberOfCardsPerPlayer;
        initializeGame(atu, cards, N);
    }

    public void initializeGame(Color atu, List<Card>[] cards, Player startingPlayer) {
        this.startingNumberOfCardsPerPlayer = cards[0].size();
        this.cards = cards;
        this.points = new int[PLAYER_NUMBER / 2];
        this.atu = atu;
        this.currentPlayer = startingPlayer;
        this.startingPlayer = startingPlayer;
        this.playedCards = new LinkedList<>();
        Arrays.fill(points, 0);
        lastGame = new Game(game);
    }

    public void initializeGame(Color atu, List<Card>[] cards, List<Card> playedCards,
                               Player startingPlayer, Player currentPlayer) {
        initializeGame(atu, cards, currentPlayer);
        this.startingPlayer = startingPlayer;
        this.playedCards = playedCards;
    }

    public boolean isMoveValid(Card card) {
        List<Card> playerCards = new LinkedList<>(cards[currentPlayer.ordinal()]);
        Color colorOfFirstCardPlayed = playedCards.isEmpty() ? null : playedCards.get(0).getColor();
        return moveValidator.isMoveValid(playerCards, colorOfFirstCardPlayed, card);
    }

    public boolean hasRoundEnded() {
        return playedCards.size() == PLAYER_NUMBER;
    }

    public void playCard(Card card) {
        List<Card> playerCards = new LinkedList<>(cards[currentPlayer.ordinal()]);
        makeMove(playerCards, card);
    }

    public void summarizeRound() {
        currentPlayer = startingPlayer = roundJudge.chooseWinningPlayer(playedCards, startingPlayer, atu);
        points[currentPlayer.ordinal() % (PLAYER_NUMBER / 2)]++;
        playedCards.clear();
    }

    public Player getWinningPlayer() {
        return roundJudge.chooseWinningPlayer(playedCards, startingPlayer, atu);
    }

    public void switchToNextPlayer() {
        currentPlayer = startingPlayer = startingPlayer.nextPlayer();
    }

    public boolean isGameOngoing() {
        return (long) startingNumberOfCardsPerPlayer * PLAYER_NUMBER !=
                Stream.of(cards).mapToLong(Collection::size).sum();
    }

    private void makeMove(List<Card> playerCards, Card card) {
        playedCards.add(card);
        playerCards.remove(card);
        cards[currentPlayer.ordinal()] = playerCards;
        currentPlayer = currentPlayer.nextPlayer();
    }

}
