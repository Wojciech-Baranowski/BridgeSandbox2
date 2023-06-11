package solver.newAlgorithms;

import lombok.Getter;
import solver.newAlgorithms.gameState.GameState;

import static gameLogic.game.GameConstants.PLAYER_NUMBER;

@Getter
public class BaseNode {

    private final GameState gameState;
    private final int depth;

    public BaseNode(GameState gameState) {
        this.gameState = gameState;
        this.depth = getStartingDepth(gameState);
    }

    private int getStartingDepth(GameState gameState) {
        int depth = 0;
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            depth += gameState.getHandsCards()[i].length;
        }
        return depth;
    }
}
