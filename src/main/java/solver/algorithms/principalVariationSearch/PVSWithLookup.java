package solver.algorithms.principalVariationSearch;

import gameLogic.game.Game;
import solver.result.Result;

import static gameLogic.game.GameConstants.PLAYER_NUMBER;

public class PVSWithLookup extends PVSWithCutoff {

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        PVSLookupNode pvsNode = new PVSLookupNode(game);
        orderByFiguresDescending(pvsNode);
        byte bestOutcome = principalVariationSearch(pvsNode);
        return Result.mapResponseToResult(game, pvsNode.allOutcomeCards, bestOutcome);
    }

    @Override
    public void preprocessing() {
        if (PVSLookupNode.winningPlayerTable == null) {
            PVSLookupNode.initializeWinningPlayerLookupTable();
        }
    }

    protected byte playCard(PVSLookupNode pvsNode, byte currentCardIndex, byte alpha, byte beta) {
        byte response;
        byte prevAlpha = pvsNode.alpha;
        byte prevBeta = pvsNode.beta;
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
            response = principalVariationSearch(pvsNode);
            pvsNode.revertSummarize(playedCards, lastStartingPlayer);
        }
        pvsNode.revertPlayCard(currentCardIndex);
        pvsNode.alpha = prevAlpha;
        pvsNode.beta = prevBeta;
        return response;
    }

}
