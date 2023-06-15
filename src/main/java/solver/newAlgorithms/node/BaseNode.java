package solver.newAlgorithms.node;

import lombok.Getter;
import solver.newAlgorithms.gameState.GameState;

@Getter
public class BaseNode implements Node {

    public final GameState gameState;
    private final boolean repeatedNode;

    public BaseNode(GameState gameState) {
        this.gameState = gameState;
        this.repeatedNode = false;
    }

    public BaseNode(BaseNode baseNode) {
        this.gameState = baseNode.getGameState();
        if (baseNode.isRepeatedNode()) {
            this.repeatedNode = false;
        } else {
            this.repeatedNode = baseNode.getGameState().isRoundBeginning() && baseNode.getGameState().isPairOnMoveTwiceInARow();
        }
    }

    public void stepIn(int branchIndex) {
        if (!this.repeatedNode) {
            this.gameState.doMove(branchIndex);
        }
    }

    public void stepOut() {
        if (!this.repeatedNode) {
            this.gameState.undoMove();
        }
    }

    public int[] getChildren() {
        if (!this.repeatedNode) {
            return this.gameState.getIndicesOfPlayableCardsOnCurrentPlayerHand();
        } else {
            return new int[1];
        }
    }

    public int getValue() {
        return this.gameState.getStartingPairPoints();
    }

    public boolean isTerminal() {
        return this.getGameState().isGameFinished();
    }
}
