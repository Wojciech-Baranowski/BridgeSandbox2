package solver.newAlgorithms.algorithm;

import solver.newAlgorithms.AlgorithmProxy;
import solver.newAlgorithms.gameState.GameState;
import solver.newAlgorithms.node.BaseNode;

public class SssStar extends AlgorithmProxy {

    //pvs 424 4240 398 0.0040
    //negaScout 371 3718 342 0.0034
    //mtd 468 4682 408 0.0041
    //negaC* 493 4933 430 0.0043
    //sss* 471 4710 410 0.0041
    //dual* 453 4533 394 0.0039
    //negamaxAB 443 4437 374 0.0037

    @Override
    public int solve(GameState gameState) {
        int result = sssStar(new BaseNode(gameState), gameState.isNSOnMove() ? 1 : -1);
        return Math.abs(result);
    }

    private int sssStar(BaseNode baseNode, int color) {
        int score = 1000;
        int alpha;
        do {
            alpha = score;
            score = negamaxAlphaBeta(baseNode, alpha - 1, alpha, color);
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
