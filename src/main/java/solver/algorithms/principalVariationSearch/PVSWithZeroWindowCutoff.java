package solver.algorithms.principalVariationSearch;

import gameLogic.game.Game;
import solver.algorithms.BaseNode;
import solver.algorithms.alphaBeta.AlphaBetaNode;
import solver.result.Result;

import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static java.lang.Math.abs;
import static java.lang.Math.max;

public class PVSWithZeroWindowCutoff extends PVSKillerHeuristicHighestFirst {

    @Override
    public Result solve(Game game) {
        return null;
    }

    public boolean solve(Game game, int numberOfCardsToTake) {
        numberOfVisitedNodes = 0;
        PVSNode pvsNode = new PVSNode(game);
        orderByFiguresDescending(pvsNode);
        return abs(principalVariationSearch(pvsNode, (byte) numberOfCardsToTake)) >= numberOfCardsToTake;
    }

    protected byte principalVariationSearch(PVSNode pvsNode, byte numberOfCardsToTake) {
        if (pvsNode.depth == BaseNode.allCardsNumber) {
            numberOfVisitedNodes++;
            if (pvsNode.allOutcomeCards[pvsNode.nsPoints][0] == -1) {
                for (int i = 0; i < AlphaBetaNode.allCardsNumber; i++) {
                    pvsNode.allOutcomeCards[pvsNode.nsPoints][i] = pvsNode.allPlayedCards[i];
                }
            }
            return (byte) (pvsNode.nsPoints * pvsNode.color);
        }
        byte score = -100;
        for (byte i = 0; i < pvsNode.cardsSize[pvsNode.currentPlayer]; i++) {
            if (pvsNode.isCardValid(i)) {
                if (score == -100) {
                    score = (byte) -playCard(pvsNode, i, (byte) -pvsNode.beta, (byte) -pvsNode.alpha, numberOfCardsToTake);
                } else {
                    score = (byte) -playCard(pvsNode, i, (byte) (-pvsNode.alpha - 1), (byte) -pvsNode.alpha, numberOfCardsToTake);
                    if (pvsNode.alpha < score && score < pvsNode.beta) {
                        score = (byte) -playCard(pvsNode, i, (byte) -pvsNode.beta, (byte) -score, numberOfCardsToTake);
                    }
                }
                pvsNode.alpha = (byte) max(pvsNode.alpha, score);
                if ((pvsNode.alpha >= pvsNode.beta) || ((pvsNode.color == 1) && (pvsNode.depth / 4 + pvsNode.nsPoints < numberOfCardsToTake))) {
                    break;
                }
            }
        }
        return pvsNode.alpha;
    }

    protected byte playCard(PVSNode pvsNode, byte currentCardIndex, byte alpha, byte beta, byte numberOfCardsToTake) {
        byte response;
        byte prevAlpha = pvsNode.alpha;
        byte prevBeta = pvsNode.beta;
        byte prevColor = pvsNode.color;
        pvsNode.playCard(currentCardIndex, alpha, beta);
        if (pvsNode.playedCardsSize != PLAYER_NUMBER) {
            response = principalVariationSearch(pvsNode, numberOfCardsToTake);
        } else {
            byte[] playedCards = {
                    pvsNode.playedCards[0],
                    pvsNode.playedCards[1],
                    pvsNode.playedCards[2],
                    pvsNode.playedCards[3]};
            byte lastStartingPlayer = pvsNode.startingPlayer;
            pvsNode.summarize();
            if (pvsNode.isSummarizeParity(lastStartingPlayer)) {
                pvsNode.playDummyCard();
                response = (byte) -principalVariationSearch(pvsNode, numberOfCardsToTake);
                pvsNode.revertPlayDummyCard();
            } else {
                response = principalVariationSearch(pvsNode, numberOfCardsToTake);
            }
            pvsNode.revertSummarize(playedCards, lastStartingPlayer);
        }
        pvsNode.revertPlayCard(currentCardIndex);
        pvsNode.alpha = prevAlpha;
        pvsNode.beta = prevBeta;
        pvsNode.color = prevColor;
        return response;
    }

}

