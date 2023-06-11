package solver.newAlgorithms.gameState;

import gameLogic.card.Card;
import gameLogic.game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static gameLogic.card.Color.*;
import static gameLogic.card.Figure.*;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static gameLogic.player.Player.N;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameStateTest {

    private static final GameStateFactory gameStateFactory = new GameStateFactory();
    private GameState gameState;

    @BeforeEach
    public void initialize() {
        this.gameState = initializeGameState();
    }

    private GameState initializeGameState() {
        List<Card> NCards = List.of(new Card(_9, CLUB), new Card(J, CLUB), new Card(_10, SPADE), new Card(A, SPADE));
        List<Card> ECards = List.of(new Card(_5, CLUB), new Card(A, CLUB), new Card(_3, HEART), new Card(Q, HEART));
        List<Card> SCards = List.of(new Card(K, CLUB), new Card(_3, DIAMOND), new Card(_4, DIAMOND), new Card(Q, DIAMOND));
        List<Card> WCards = List.of(new Card(_4, CLUB), new Card(_10, DIAMOND), new Card(_2, HEART), new Card(_4, SPADE));
        List<Card>[] cards = new List[]{NCards, ECards, SCards, WCards};
        Game game = Game.getGame();
        game.initializeGame(HEART, cards, N);
        return gameStateFactory.createGameState(game);
    }

    @Test
    public void gameState_doMove_test() {
        //Given
        int[] indicesInHandsOfCardsToPlay = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] playedCardsIdsOrder = new int[]{7, 3, 11, 2, 14, 21, 47, 27, 12, 15, 26, 9, 41, 51, 36, 23};
        int[] startingPlayersOrder = new int[]{0, 2, 1, 3};
        int[] resultPoints = new int[]{1, 3};

        //When
        Arrays.stream(indicesInHandsOfCardsToPlay).forEach(i -> this.gameState.doMove(i));

        //Then
        assertArrayEquals(indicesInHandsOfCardsToPlay, this.gameState.getPlayedCardsIndicesInHands());
        assertArrayEquals(playedCardsIdsOrder, this.gameState.getPlayedCards());
        assertArrayEquals(startingPlayersOrder, this.gameState.getPreviousStartingPlayers());
        assertArrayEquals(resultPoints, this.gameState.getPoints());
        assertArrayEquals(new int[PLAYER_NUMBER], this.gameState.getNumberOfHandsCards());
        IntStream.range(0, PLAYER_NUMBER).forEach(i -> assertArrayEquals(new int[PLAYER_NUMBER], this.gameState.getNumberOfHandsCardsPerColor()[i]));
        assertEquals(16, this.gameState.getNumberOfPlayedCards());
        assertEquals(16 / PLAYER_NUMBER, this.gameState.getNumberOfPlayedRounds());
    }

    @Test
    public void gameState_undoMove_test() {
        //Given
        GameState initialGameState = initializeGameState();
        int[][] startingNumberOfHandsCardsPerColor = new int[][]{{2, 0, 0, 2}, {2, 0, 2, 0}, {1, 3, 0, 0}, {1, 1, 1, 1}};
        int[] indicesInHandsOfCardsToPlay = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};

        //When
        Arrays.stream(indicesInHandsOfCardsToPlay).forEach(i -> this.gameState.doMove(i));
        Arrays.stream(indicesInHandsOfCardsToPlay).forEach(i -> this.gameState.undoMove());

        //Then
        IntStream.range(0, PLAYER_NUMBER).forEach(i -> assertArrayEquals(initialGameState.getHandsCards()[i], this.gameState.getHandsCards()[i]));
        IntStream.range(0, PLAYER_NUMBER).forEach(i -> assertArrayEquals(startingNumberOfHandsCardsPerColor[i], this.gameState.getNumberOfHandsCardsPerColor()[i]));
        assertArrayEquals(initialGameState.getNumberOfHandsCards(), this.gameState.getNumberOfHandsCards());
        assertArrayEquals(initialGameState.getPoints(), this.gameState.getPoints());
        assertEquals(initialGameState.getStartingPlayer(), this.gameState.getStartingPlayer());
        assertEquals(initialGameState.getCurrentPlayer(), this.gameState.getCurrentPlayer());
    }
}
