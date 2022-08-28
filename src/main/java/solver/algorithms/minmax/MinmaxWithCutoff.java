package solver.algorithms.minmax;

import solver.Algorithm;

public class MinmaxWithCutoff extends Minmax implements Algorithm {

    protected Response minMax(Node node) {
        if (node.depth == Node.allCardsNumber) {
            numberOfVisitedNodes++;
            return new Response(node.nsPoints);
        }
        Response bestResponse = new Response((byte) (node.maximizing ? -100 : 100));
        for (byte i = 0; i < node.cardsSize[node.currentPlayer]; i++) {
            if (node.isCardValid(i)) {
                bestResponse = chooseBestResponse(node, bestResponse, i);
                if (((node.maximizing)
                            && ((Node.allCardsNumber - node.depth + 3) / 4 == bestResponse.nsPoints - node.nsPoints))
                        || ((!node.maximizing)
                            && (0 == bestResponse.nsPoints - node.nsPoints))) {
                    break;
                }
            }
        }
        return bestResponse;
    }

}

