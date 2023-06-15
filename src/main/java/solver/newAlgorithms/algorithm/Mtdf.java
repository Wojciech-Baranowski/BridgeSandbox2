package solver.newAlgorithms.algorithm;

import solver.newAlgorithms.AlgorithmProxy;
import solver.newAlgorithms.gameState.GameState;
import solver.newAlgorithms.node.BaseNode;

public class Mtdf extends AlgorithmProxy {

    @Override
    public int solve(GameState gameState) {
        int result = mtdf(new BaseNode(gameState), gameState.getTotalNumberOfPoints() / 2, gameState.isNSOnMove() ? 1 : -1);
        return Math.abs(result);
    }

    public int mtdf(BaseNode node, int initialGuess, int color) {
        int g = initialGuess;
        int upperBound = 1000;
        int lowerBound = -1000;
        while (lowerBound < upperBound) {
            int beta = Math.max(g, lowerBound + 1);
            g = alphaBeta(node, beta - 1, beta, color);
            if (g < beta) {
                upperBound = g;
            } else {
                lowerBound = g;
            }
        }
        return g;
    }

    private int alphaBeta(BaseNode node, int alpha, int beta, int color) {
        if (node.isTerminal()) {
            return color * node.getValue();
        }
        int value = -1000;
        for (int child : node.getChildren()) {
            node.stepIn(child);
            value = Math.max(value, -alphaBeta(new BaseNode(node), -beta, -alpha, -color));
            node.stepOut();
            alpha = Math.max(alpha, value);
            if (alpha >= beta) {
                break;
            }
        }
        return value;
    }
}
