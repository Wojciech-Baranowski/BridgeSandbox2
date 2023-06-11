package solver.algorithms.principalVariationSearch;


import gameLogic.game.Game;
import solver.Algorithm;
import solver.algorithms.BaseNode;
import solver.algorithms.alphaBeta.AlphaBetaNode;
import solver.result.Result;

import static gameLogic.game.GameConstants.PLAYER_NUMBER;
import static java.lang.Math.max;

public class PrincipalVariationSearch implements Algorithm {

    public long numberOfVisitedNodes;

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        PVSNode PVSNode = new PVSNode(game);
        byte bestOutcome = principalVariationSearch(PVSNode);
        return Result.mapResponseToResult(game, PVSNode.allOutcomeCards, bestOutcome, numberOfVisitedNodes);
    }

    protected byte principalVariationSearch(PVSNode PVSNode) {
        if (PVSNode.depth == BaseNode.allCardsNumber) {
            numberOfVisitedNodes++;
            if (PVSNode.allOutcomeCards[PVSNode.nsPoints][0] == -1) {
                for (int i = 0; i < AlphaBetaNode.allCardsNumber; i++) {
                    PVSNode.allOutcomeCards[PVSNode.nsPoints][i] = PVSNode.allPlayedCards[i];
                }
            }
            return (byte) (PVSNode.nsPoints * PVSNode.color);
        }
        byte score = -100;
        for (byte i = 0; i < PVSNode.cardsSize[PVSNode.currentPlayer]; i++) {
            if (PVSNode.isCardValid(i)) {
                if (score == -100) {
                    score = (byte) -playCard(PVSNode, i, (byte) -PVSNode.beta, (byte) -PVSNode.alpha);
                } else {
                    score = (byte) -playCard(PVSNode, i, (byte) (-PVSNode.alpha - 1), (byte) -PVSNode.alpha);
                    if (PVSNode.alpha < score && score < PVSNode.beta) {
                        score = (byte) -playCard(PVSNode, i, (byte) -PVSNode.beta, (byte) -score);
                    }
                }
                PVSNode.alpha = (byte) max(PVSNode.alpha, score);
                if (PVSNode.alpha >= PVSNode.beta) {
                    break;
                }
            }
        }
        return PVSNode.alpha;
    }

    protected byte playCard(PVSNode pvsNode, byte currentCardIndex, byte alpha, byte beta) {
        byte response;
        byte prevAlpha = pvsNode.alpha;
        byte prevBeta = pvsNode.beta;
        byte prevColor = pvsNode.color;
        pvsNode.playCard(currentCardIndex, alpha, beta);
        if (pvsNode.playedCardsSize != PLAYER_NUMBER) {
            response = principalVariationSearch(pvsNode);
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
                response = (byte) -principalVariationSearch(pvsNode);
                pvsNode.revertPlayDummyCard();
            } else {
                response = principalVariationSearch(pvsNode);
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
