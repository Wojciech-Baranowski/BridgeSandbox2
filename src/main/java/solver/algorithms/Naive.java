package solver.algorithms;

import gameLogic.card.Card;
import gameLogic.game.Game;
import solver.Algorithm;
import solver.Result;

import java.util.ArrayList;
import java.util.List;

public class Naive implements Algorithm {

    @Override
    public Result solve(Game game) {
        return new Result(getAllCardsInNaiveOrder(game), game.getStartingPlayer(), game.getAtu());
    }

    private List<Card> getAllCardsInNaiveOrder(Game game) {
        List<Card> cards = new ArrayList<>(game.getPlayedCards());
        while(!game.getCards()[game.getCurrentPlayer().ordinal()].isEmpty()) {
            for(int i = 0; i < game.getCards()[game.getCurrentPlayer().ordinal()].size(); i++) {
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
