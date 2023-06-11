package solver.newAlgorithms.gameState;

import gameLogic.card.Card;
import gameLogic.game.Game;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static gameLogic.game.GameConstants.*;

public class GameStateFactory {

    public GameState createGameState(Game game) {
        int totalNumberOfCards = getNumberOfCards(game.getCards(), game.getPlayedCards());
        int initialPlayer = game.getStartingPlayer().ordinal();
        int atu = game.getAtu().ordinal();

        int[][] handsCards = rewriteHandsCards(game.getCards());
        int[][] numberOfHandsCardsPerColor = rewriteNumberOfHandsCardsPerColor(game.getCards());
        int[] numberOfHandsCards = rewriteNumberOfHandsCards(game.getCards());
        int[] tableCards = rewriteTableCards(game.getPlayedCards());
        int[] points = rewritePoints(game.getPoints());
        int startingPlayer = game.getStartingPlayer().ordinal();
        int currentPlayer = game.getCurrentPlayer().ordinal();

        int numberOfGeneratedGameStates = 0;
        int[][] results = initializeResults(game.getStartingNumberOfCardsPerPlayer() + 1, totalNumberOfCards);

        int[][] playedTableCards = new int[game.getStartingNumberOfCardsPerPlayer()][PLAYER_NUMBER];
        int[] playedCards = new int[totalNumberOfCards];
        int[] playedCardsIndicesInHands = new int[totalNumberOfCards];
        int[] previousStartingPlayers = new int[game.getStartingNumberOfCardsPerPlayer()];
        int numberOfPlayedCards = 0;
        int numberOfPlayedRounds = 0;
        int numberOfPreviousStartingPlayers = 0;

        return new GameState(
                totalNumberOfCards,
                initialPlayer,
                atu,
                handsCards,
                numberOfHandsCardsPerColor,
                numberOfHandsCards,
                tableCards,
                points,
                startingPlayer,
                currentPlayer,
                numberOfGeneratedGameStates,
                results,
                playedTableCards,
                playedCards,
                playedCardsIndicesInHands,
                previousStartingPlayers,
                numberOfPlayedCards,
                numberOfPlayedRounds,
                numberOfPreviousStartingPlayers
        );
    }

    private int[][] initializeResults(int numberOfPossibleScoreResults, int totalNumberOfCards) {
        int[][] results = new int[numberOfPossibleScoreResults][totalNumberOfCards];
        for (int i = 0; i < numberOfPossibleScoreResults; i++) {
            results[i][0] = -1;
        }
        return results;
    }

    private int[][] rewriteHandsCards(List<Card>[] handsCards) {
        int[][] handsCardsCopy = new int[PLAYER_NUMBER][DECK_SIZE / PLAYER_NUMBER];
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            for (int j = 0; j < handsCards[i].size(); j++) {
                handsCardsCopy[i][j] = handsCards[i].get(j).getId();
            }
        }
        return handsCardsCopy;
    }

    private int[][] rewriteNumberOfHandsCardsPerColor(List<Card>[] handsCards) {
        int[][] handsCardsPerColor = new int[PLAYER_NUMBER][PLAYER_NUMBER];
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            for (int j = 0; j < handsCards[i].size(); j++) {
                handsCardsPerColor[i][handsCards[i].get(j).getId() / FIGURE_NUMBER]++;
            }
        }
        return handsCardsPerColor;
    }

    private int[] rewriteNumberOfHandsCards(List<Card>[] handsCards) {
        int[] numberOfHandsCards = new int[PLAYER_NUMBER];
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            numberOfHandsCards[i] = handsCards[i].size();
        }
        return numberOfHandsCards;
    }

    private int[] rewriteTableCards(List<Card> playedCards) {
        int[] playedCardsCopy = new int[PLAYER_NUMBER];
        for (int i = 0; i < playedCards.size(); i++) {
            playedCardsCopy[i] = playedCards.get(i).getId();
        }
        return playedCardsCopy;
    }

    private int[] rewritePoints(int[] points) {
        return Arrays.copyOf(points, PLAYER_NUMBER / 2);
    }

    private int getNumberOfCards(List<Card>[] handsCards, List<Card> playedCards) {
        return (int) Stream.concat(Arrays.stream(handsCards).flatMap(Collection::stream), playedCards.stream()).count();
    }
}
