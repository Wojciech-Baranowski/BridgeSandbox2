package solver.newAlgorithms.gameState;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static gameLogic.game.GameConstants.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class GameState {

    private static final int[] helperArray = new int[DECK_SIZE / PLAYER_NUMBER];
    private final int totalNumberOfCards;
    private final int initialPlayer;
    private final int atu;

    private int[][] handsCards;
    private int[][] numberOfHandsCardsPerColor;
    private int[] numberOfHandsCards;
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
        for (int i = cardToPlayIndexInHand; i < this.numberOfHandsCards[this.currentPlayer] - 1; i++) {
            this.handsCards[this.currentPlayer][i] = this.handsCards[this.currentPlayer][i + 1];
        }
        this.numberOfHandsCardsPerColor[this.currentPlayer][this.playedCards[this.numberOfPlayedCards] / FIGURE_NUMBER]--;
        this.numberOfHandsCards[this.currentPlayer]--;
        this.currentPlayer = (this.currentPlayer + 1) % PLAYER_NUMBER;
        this.numberOfPlayedCards++;
        this.numberOfGeneratedGameStates++;
    }

    private void unplayCard() {
        this.numberOfPlayedCards--;
        this.currentPlayer = (this.currentPlayer - 1 + PLAYER_NUMBER) % PLAYER_NUMBER;
        this.numberOfHandsCards[this.currentPlayer]++;
        this.numberOfHandsCardsPerColor[this.currentPlayer][this.playedCards[this.numberOfPlayedCards] / FIGURE_NUMBER]++;
        for (int i = this.numberOfHandsCards[this.currentPlayer] - 1; i > this.playedCardsIndicesInHands[this.numberOfPlayedCards]; i--) {
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
        this.points[winnerPlayer % 2]++;
        this.startingPlayer = winnerPlayer;
        this.currentPlayer = winnerPlayer;
        this.numberOfPreviousStartingPlayers++;
        this.numberOfPlayedRounds++;
    }

    private void unresolveRound() {
        this.numberOfPlayedRounds--;
        this.numberOfPreviousStartingPlayers--;
        this.points[this.startingPlayer % 2]--;
        this.currentPlayer = this.previousStartingPlayers[this.numberOfPreviousStartingPlayers];
        this.startingPlayer = this.previousStartingPlayers[this.numberOfPreviousStartingPlayers];
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            this.tableCards[i] = this.playedTableCards[this.numberOfPlayedRounds][i];
        }
    }

    private int resolveRoundWinner() {
        int winner = this.startingPlayer;
        for (int i = (this.startingPlayer + 1) % PLAYER_NUMBER, j = 1; j < PLAYER_NUMBER; i = (i + 1) % PLAYER_NUMBER, j++) {
            boolean cardColorEqualsWinnerCardColor = this.tableCards[i] / FIGURE_NUMBER == this.tableCards[winner] / FIGURE_NUMBER;
            boolean cardFigureIsGreaterThanWinnerCardFigure = this.tableCards[i] % FIGURE_NUMBER > this.tableCards[winner] % FIGURE_NUMBER;
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
                this.results[startingPairPoints][i] = this.playedCards[i];
            }
        }
    }

    public int[] getIndicesOfPlayableCardsOnCurrentPlayerHand() {
        int numberOfPlayableCardsOnCurrentPlayerHand = 0;
        for (int i = 0; i < this.numberOfHandsCards[this.currentPlayer]; i++) {
            boolean currentPlayerStartsTheRound = this.startingPlayer == this.currentPlayer;
            boolean cardColorMatchesStartingPlayerTableCardColor = this.handsCards[this.currentPlayer][i] / FIGURE_NUMBER == this.tableCards[this.startingPlayer] / FIGURE_NUMBER;
            boolean playerHasVoidInStartingPlayerTableCardColor = this.numberOfHandsCardsPerColor[this.currentPlayer][this.handsCards[this.currentPlayer][i] / FIGURE_NUMBER] == 0;
            if (currentPlayerStartsTheRound || cardColorMatchesStartingPlayerTableCardColor || playerHasVoidInStartingPlayerTableCardColor) {
                helperArray[numberOfPlayableCardsOnCurrentPlayerHand] = i;
                numberOfPlayableCardsOnCurrentPlayerHand++;
            }
        }
        int[] indicesOfPlayableCardsOnCurrentPlayerHand = new int[numberOfPlayableCardsOnCurrentPlayerHand];
        for (int i = 0; i < numberOfPlayableCardsOnCurrentPlayerHand; i++) {
            indicesOfPlayableCardsOnCurrentPlayerHand[i] = helperArray[i];
        }
        return indicesOfPlayableCardsOnCurrentPlayerHand;
    }

    public int getStartingPairPoints() {
        return this.points[this.initialPlayer % 2];
    }
}
