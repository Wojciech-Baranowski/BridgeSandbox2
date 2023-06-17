package solver.newAlgorithms.algorithm;

import solver.newAlgorithms.AlgorithmProxy;
import solver.newAlgorithms.gameState.GameState;
import solver.newAlgorithms.node.BaseNode;

public class PrincipalVariationSearch extends AlgorithmProxy {

    @Override
    public int solve(GameState gameState) {
        int result = principalVariationSearch(new BaseNode(gameState), -1000, 1000, gameState.isNSOnMove() ? 1 : -1);
        return Math.abs(result);
    }

    private int principalVariationSearch(BaseNode node, int alpha, int beta, int color) {
        if (node.isTerminal()) {
            return color * node.getValue();
        }
        int score = -1000;
        int[] children = node.getChildren();
        for (int i = 0; i < children.length; i++) {
            if (i == 0) {
                node.stepIn(children[i]);
                score = Math.max(score, -principalVariationSearch(new BaseNode(node), -beta, -alpha, -color));
                node.stepOut();
            } else {
                node.stepIn(children[i]);
                score = Math.max(score, -principalVariationSearch(new BaseNode(node), -alpha - 1, -alpha, -color));
                node.stepOut();
                if (alpha < score && score < beta) {
                    node.stepIn(children[i]);
                    score = -principalVariationSearch(new BaseNode(node), -beta, -alpha, -color);
                    node.stepOut();
                }
            }
            alpha = Math.max(alpha, score);
            if (alpha >= beta) {
                break;
            }
        }
        return alpha;
    }
}
