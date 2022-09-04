package solver.algorithms.alphaBetaWithMemory;

import java.util.Arrays;

public class NodeInfo implements Comparable {

    public byte startingPlayer;
    public byte depth;
    public byte nsPoints;
    public byte playedCardsSize;
    public byte[] allPlayedCards;

    public NodeInfo(Node node) {
        startingPlayer = node.startingPlayer;
        depth = node.depth;
        nsPoints = node.nsPoints;
        playedCardsSize = node.playedCardsSize;
        allPlayedCards = Arrays.copyOf(node.allPlayedCards, node.allPlayedCards.length);
    }

    @Override
    public int compareTo(Object o) {
        return equals((NodeInfo) o) ? 0 : 1;
    }

    public boolean equals(NodeInfo node) {
        if (startingPlayer == node.startingPlayer
                && depth == node.depth
                && nsPoints == node.nsPoints) {
            for (int i = 1; i <= playedCardsSize; i++) {
                if (allPlayedCards[depth - i] != node.allPlayedCards[depth - i]) {
                    return false;
                }
            }
            byte[] allPlayedCardsSorted = Arrays.copyOf(allPlayedCards, depth);
            byte[] nodeAllPlayedCardsSorted = Arrays.copyOf(node.allPlayedCards, depth);
            Arrays.sort(allPlayedCardsSorted);
            Arrays.sort(nodeAllPlayedCardsSorted);
            for (int i = 0; i < depth; i++) {
                if (allPlayedCardsSorted[i] != nodeAllPlayedCardsSorted[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
