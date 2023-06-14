package solver.newAlgorithms;

import solver.newAlgorithms.gameState.GameState;
import solver.newAlgorithms.node.BaseNode;

public class Negamax extends AlgorithmProxy {

    @Override
    public int solve(GameState gameState) {
        int result = negamax(new BaseNode(gameState), gameState.isNSOnMove() ? 1 : -1);
        int res = Math.abs(result);
        return res;
    }

    public int negamax(BaseNode node, int color) {
        if (node.isTerminal()) {
            return color * node.getValue();
        }
        int value = -1000;
        for (int child : node.getChildren()) {
            node.stepIn(child);
            value = Math.max(value, -negamax(new BaseNode(node), -color));
            node.stepOut();
        }
        return value;
    }
}
