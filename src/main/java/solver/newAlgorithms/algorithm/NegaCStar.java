package solver.newAlgorithms.algorithm;

import solver.newAlgorithms.AlgorithmProxy;
import solver.newAlgorithms.gameState.GameState;
import solver.newAlgorithms.node.BaseNode;

public class NegaCStar extends AlgorithmProxy {

    @Override
    public int solve(GameState gameState) {
        int result = negaCStar(new BaseNode(gameState), gameState.isNSOnMove() ? 1 : -1);
        return Math.abs(result);
    }

    private int negaCStar(BaseNode node, int color) {
        int min = -1000;
        int max = 1000;
        int score = min;
        int iterations = 0;
        while (min != max) {
            int alpha = (min + max) / 2;
            score = negamaxAlphaBeta(node, alpha, alpha + 1, color);
            if (score > alpha) {
                min = score;
            } else {
                max = score;
            }
            iterations++;
            if (iterations > 10) {
                return negamaxAlphaBeta(node, -1000, 1000, color);
            }
        }
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
