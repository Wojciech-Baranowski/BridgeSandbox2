package solver.newAlgorithms.algorithm;

import solver.newAlgorithms.AlgorithmProxy;
import solver.newAlgorithms.gameState.GameState;
import solver.newAlgorithms.node.BaseNode;

public class DualStar extends AlgorithmProxy {

    @Override
    public int solve(GameState gameState) {
        int result = dualStar(new BaseNode(gameState), gameState.isNSOnMove() ? 1 : -1);
        return Math.abs(result);
    }

    private int dualStar(BaseNode baseNode, int color) {
        int score = -1000;
        int alpha;
        do {
            alpha = score;
            score = negamaxAlphaBeta(baseNode, alpha, alpha + 1, color);
        } while (score != alpha);
        return score;
    }

    private int negamaxAlphaBeta(BaseNode node, int alpha, int beta, int color) {
        if (node.isTerminal()) {
            return color * node.getValue();
        }
        int value = -1000;
        for (int child : node.getChildren()) {
            node.stepIn(child);
            value = Math.max(value, -negamaxAlphaBeta(new BaseNode(node), -beta, -alpha, -color));
            node.stepOut();
            alpha = Math.max(alpha, value);
            if (alpha >= beta) {
                break;
            }
        }
        return value;
    }
}
