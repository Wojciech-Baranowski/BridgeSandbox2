package solver.newAlgorithms.gameState;

public interface GameState {

    void doMove(int cardToPlayIndex);

    void undoMove();

    int[] getIndicesOfPlayableCardsOnCurrentPlayerHand();

    long getNumberOfGeneratedGameStates();

    int[][] getResults();

    int getStartingPairPoints();

    int getTotalNumberOfPoints();

    boolean isGameFinished();

    boolean isRoundBeginning();

    boolean isNSOnMove();

    boolean isPairOnMoveTwiceInARow();
}
