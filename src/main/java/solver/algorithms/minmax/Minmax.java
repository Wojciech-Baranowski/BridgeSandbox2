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

public class Minmax implements Algorithm {

    protected long numberOfVisitedNodes;
    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        Node node = new Node(game);
        Response bestOutcome = minMax(node);
        return mapResponseToResult(game, bestOutcome);
    }

    @Override
    public long getNumberOfVisitedNodes() {
        return numberOfVisitedNodes;
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
            }
        }
        return bestResponse;
    }

    protected Response chooseBestResponse(Node node, Response currentBestResponse, byte currentCardIndex) {
        Response newResponse = getResponseAfterPlayingCard(node, currentCardIndex);
        if ((node.maximizing && newResponse.nsPoints > currentBestResponse.nsPoints)
                || (!node.maximizing && newResponse.nsPoints < currentBestResponse.nsPoints)) {
            newResponse.addData(node.cards[node.currentPlayer][currentCardIndex], node.depth);
            return newResponse;
        }
        return currentBestResponse;
    }

    protected Response getResponseAfterPlayingCard(Node node, byte currentCardIndex) {
        Response response;
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
        node.revertPlayCard();
        return response;
    }

    protected Result mapResponseToResult(Game game, Response response) {
        Deck deck = getDeck();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < game.getPlayedCards().size(); i++) {
            response.cards[i] = (byte) game.getPlayedCards().get(i).getId();
        }
        for (byte cardId : response.cards) {
            cards.add(deck.getCard(cardId));
        }
        return new Result(cards, game.getStartingPlayer(), game.getAtu());
    }

}
