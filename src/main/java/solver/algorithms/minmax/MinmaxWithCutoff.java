package solver.algorithms.minmax;

import solver.Algorithm;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MinmaxWithCutoff extends Minmax implements Algorithm {

    protected byte minMax(Node node) {
        if (node.depth == Node.allCardsNumber) {
            numberOfVisitedNodes++;
            if(node.allOutcomeCards[node.nsPoints][0] == -1) {
                for(int i = 0; i < Node.allCardsNumber; i++) {
                    node.allOutcomeCards[node.nsPoints][i] = node.allPlayedCards[i];
                }
            }
            return node.nsPoints;
        }
        byte bestScore = (byte) (node.maximizing ? -100 : 100);
        for (byte i = 0; i < node.cardsSize[node.currentPlayer]; i++) {
            if (node.isCardValid(i)) {
                if(node.maximizing) {
                    bestScore = (byte) max(bestScore, playCard(node, i));
                } else {
                    bestScore = (byte) min(bestScore, playCard(node, i));
                }
                if (((node.maximizing) && ((Node.allCardsNumber - node.depth + 3) / 4 == bestScore - node.nsPoints))
                        || ((!node.maximizing) && (0 == bestScore - node.nsPoints))) {
                    break;
                }
            }
        }
        return bestScore;
    }
}

