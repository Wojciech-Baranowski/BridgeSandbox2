package solver.newAlgorithms.node;

import lombok.Getter;
import solver.newAlgorithms.gameState.GameState;

@Getter
public abstract class BaseNode implements Node {

    private final GameState gameState;
    private final int depth;

    public BaseNode(GameState gameState) {
        this.gameState = gameState;
        this.depth = gameState.getTotalNumberOfCards();
    }

    public BaseNode(BaseNode baseNode) {
        this.gameState = baseNode.getGameState();
        this.depth = baseNode.getDepth() - 1;
    }

    public int[] getBranchesIndices() {
        return this.gameState.getIndicesOfPlayableCardsOnCurrentPlayerHand();
    }

    public void stepIn(int branchIndex) {
        this.gameState.doMove(branchIndex);
    }

    public void stepOut() {
        this.gameState.undoMove();
    }
}
