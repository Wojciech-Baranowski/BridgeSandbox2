package solver.algorithms.minmax;

import gameLogic.game.Game;
import solver.Algorithm;
import solver.result.Result;

public class MinmaxWithCutoff extends Minmax implements Algorithm {

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        Node node = new Node(game);
        Response bestOutcome = minMax(node);
        return mapResponseToResult(game, bestOutcome);
    }

    protected Response minMax(Node node) {
        if (node.depth == Node.allCardsNumber) {
            numberOfVisitedNodes++;
            return new Response(node.nsPoints);
        }
        Response bestResponse = new Response((byte) (node.maximizing ? -100 : 100));
        for (byte i = 0; i < node.cardsSize[node.currentPlayer]; i++) {
            if (node.isCardValid(i)) {
                bestResponse = chooseBestResponse(node, bestResponse, i);
                if (((node.maximizing) && ((Node.allCardsNumber - node.depth + 3) / 4 == bestResponse.nsPoints - node.nsPoints))
                        || ((!node.maximizing) && (0 == bestResponse.nsPoints - node.nsPoints))) {
                    break;
                }
            }
        }
        return bestResponse;
    }

}

