package solver.algorithms.naive;

import gameLogic.card.Card;
import gameLogic.game.Game;
import solver.Algorithm;
import solver.result.Result;

import java.util.ArrayList;
import java.util.List;

public class Naive implements Algorithm {

    private long numberOfVisitedNodes;

    @Override
    public Result solve(Game game) {
        numberOfVisitedNodes = 0;
        return new Result(getAllCardsInNaiveOrder(new Game(game)), game.getStartingPlayer(), game.getAtu());
    }

    @Override
    public long getNumberOfVisitedNodes() {
        return numberOfVisitedNodes;
    }

    private List<Card> getAllCardsInNaiveOrder(Game game) {
        List<Card> cards = new ArrayList<>(game.getPlayedCards());
        while (!game.getCards()[game.getCurrentPlayer().ordinal()].isEmpty()) {
            for (int i = 0; i < game.getCards()[game.getCurrentPlayer().ordinal()].size(); i++) {
                numberOfVisitedNodes++;
                Card card = game.getCards()[game.getCurrentPlayer().ordinal()].get(i);
                if (game.isMoveValid(card)) {
                    cards.add(card);
                    game.playCard(card);
                    if (game.hasRoundEnded()) {
                        game.summarizeRound();
                    }
                    break;
                }
            }
        }
        return cards;
    }

}
