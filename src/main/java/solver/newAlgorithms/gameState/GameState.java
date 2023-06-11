package solver.newAlgorithms.gameState;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static gameLogic.game.GameConstants.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class GameState {

    private final int totalNumberOfCards;
    private final int initialPlayer;
    private final int atu;

    private int[][] handsCards;
    private int[] numberOfCardsPerHand;
    private int[] tableCards;
    private int[] points;
    private int startingPlayer;
    private int currentPlayer;

    private long numberOfGeneratedGameStates;
    private int[][] results;

    private int[][] playedTableCards;
    private int[] playedCards;
    private int[] playedCardsIndicesInHands;
    private int[] previousStartingPlayers;
    private int numberOfPlayedCards;
    private int numberOfPlayedRounds;
    private int numberOfPreviousStartingPlayers;


    public void doMove(int cardToPlayIndex) {
        playCard(cardToPlayIndex);
        if (this.startingPlayer == this.currentPlayer) {
            resolveRound();
        }
        if (this.totalNumberOfCards == this.numberOfPlayedCards) {
            summarizeGame();
        }
    }

    public void undoMove() {
        if (this.startingPlayer == this.currentPlayer) {
            unresolveRound();
        }
        unplayCard();
    }

    private void playCard(int cardToPlayIndexInHand) {
        this.tableCards[this.currentPlayer] = this.handsCards[this.currentPlayer][cardToPlayIndexInHand];
        this.playedCards[this.numberOfPlayedCards] = this.handsCards[this.currentPlayer][cardToPlayIndexInHand];
        this.playedCardsIndicesInHands[this.numberOfPlayedCards] = cardToPlayIndexInHand;
        for (int i = cardToPlayIndexInHand; i < this.numberOfCardsPerHand[this.currentPlayer] - 1; i++) {
            this.handsCards[this.currentPlayer][i] = this.handsCards[this.currentPlayer][i + 1];
        }
        this.numberOfCardsPerHand[this.currentPlayer]--;
        this.currentPlayer = (this.currentPlayer + 1) % PLAYER_NUMBER;
        this.numberOfPlayedCards++;
        this.numberOfGeneratedGameStates++;
    }

    private void unplayCard() {
        this.numberOfPlayedCards--;
        this.currentPlayer = (this.currentPlayer - 1 + PLAYER_NUMBER) % PLAYER_NUMBER;
        this.numberOfCardsPerHand[this.currentPlayer]++;
        for (int i = this.numberOfCardsPerHand[this.currentPlayer] - 1; i > this.playedCardsIndicesInHands[this.numberOfPlayedCards]; i--) {
            this.handsCards[this.currentPlayer][i] = this.handsCards[this.currentPlayer][i - 1];
        }
        this.handsCards[this.currentPlayer][this.playedCardsIndicesInHands[this.numberOfPlayedCards]] = this.playedCards[this.numberOfPlayedCards];
    }

    private void resolveRound() {
        int winnerPlayer = resolveRoundWinner();
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            this.playedTableCards[this.numberOfPlayedRounds][i] = this.tableCards[i];
        }
        this.previousStartingPlayers[this.numberOfPreviousStartingPlayers] = this.startingPlayer;
        this.points[winnerPlayer / 2]++;
        this.startingPlayer = winnerPlayer;
        this.currentPlayer = winnerPlayer;
        this.numberOfPreviousStartingPlayers++;
        this.numberOfPlayedRounds++;
    }

    private void unresolveRound() {
        this.numberOfPlayedRounds--;
        this.numberOfPreviousStartingPlayers--;
        this.currentPlayer = this.previousStartingPlayers[this.numberOfPreviousStartingPlayers];
        this.startingPlayer = this.previousStartingPlayers[this.numberOfPreviousStartingPlayers];
        this.points[this.startingPlayer / 2]--;
    }

    private int resolveRoundWinner() {
        int winner = this.startingPlayer;
        for (int i = this.startingPlayer + 1, j = 1; j < PLAYER_NUMBER; i = (i + 1) % PLAYER_NUMBER, j++) {
            boolean cardColorEqualsWinnerCardColor = this.tableCards[i] / FIGURE_NUMBER == this.tableCards[winner] / FIGURE_NUMBER;
            boolean cardFigureIsGreaterThanWinnerCardFigure = this.tableCards[i] % COLOR_NUMBER > this.tableCards[winner] % COLOR_NUMBER;
            boolean cardColorEqualsAtuColor = this.tableCards[i] / FIGURE_NUMBER == this.atu;
            boolean winnerCardColorNotEqualsAtu = this.tableCards[winner] / FIGURE_NUMBER != this.atu;
            if ((cardColorEqualsWinnerCardColor && cardFigureIsGreaterThanWinnerCardFigure) || (cardColorEqualsAtuColor && winnerCardColorNotEqualsAtu)) {
                winner = i;
            }
        }
        return winner;
    }

    private void summarizeGame() {
        int startingPairPoints = getStartingPairPoints();
        if (this.results[startingPairPoints][0] == -1) {
            for (int i = 0; i < this.totalNumberOfCards; i++) {
                this.results[startingPairPoints][i] = playedCards[i];
            }
        }
    }

    public int getStartingPairPoints() {
        return this.points[this.initialPlayer / 2];
    }
}
