package solver.algorithms.minmax;

import gameLogic.card.Card;
import gameLogic.card.Deck;
import gameLogic.game.Game;
import solver.Algorithm;
import solver.result.Result;

import java.util.ArrayList;
import java.util.List;

import static gameLogic.card.Deck.getDeck;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Minmax implements Algorithm {

    protected long numberOfVisitedNodes;
    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        Node node = new Node(game);
        byte bestScore = minMax(node);
        return Result.mapResponseToResult(game, node.allOutcomeCards, bestScore);
    }

    @Override
    public long getNumberOfVisitedNodes() {
        return numberOfVisitedNodes;
    }

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
            }
        }
        return bestScore;
    }

    protected byte playCard(Node node, byte currentCardIndex) {
        byte response;
        node.playCard(currentCardIndex);
        if (node.playedCardsSize != PLAYER_NUMBER) {
            response = minMax(node);
        } else {
            byte[] playedCards = {node.playedCards[0], node.playedCards[1], node.playedCards[2], node.playedCards[3]};
            byte lastStartingPlayer = node.startingPlayer;
            node.summarize();
            response = minMax(node);
            node.revertSummarize(playedCards, lastStartingPlayer);
        }
        node.revertPlayCard(currentCardIndex);
        return response;
    }

}
