package solver.result;

import gameLogic.card.Card;
import gameLogic.card.Color;
import gameLogic.card.Deck;
import gameLogic.game.Game;
import gameLogic.game.RoundJudge;
import gameLogic.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static gameLogic.card.Deck.getDeck;
import static gameLogic.game.GameConstants.PLAYER_NUMBER;

@Getter
public class Result {

    private final List<ResultRound> resultRounds;
    @Setter
    private int[] points;

    public Result(List<Card> cards, Player startingPlayer, Color atu) {
        resultRounds = new ArrayList<>();
        points = new int[PLAYER_NUMBER / 2];
        List<Card> roundCards = new ArrayList<>();

        for (Card card : cards) {
            roundCards.add(card);
            if (roundCards.size() == PLAYER_NUMBER) {
                Player winningPlayer = new RoundJudge().chooseWinningPlayer(roundCards, startingPlayer, atu);
                resultRounds.add(new ResultRound(roundCards, startingPlayer, winningPlayer));
                startingPlayer = winningPlayer;
                roundCards.clear();
            }
        }

        for (ResultRound round : resultRounds) {
            int index = round.isPointNS() ? 0 : 1;
            points[index]++;
        }

    }

    public static Result mapResponseToResult(Game game, byte[][] allOutcomeCards, byte bestScore) {
        Deck deck = getDeck();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < game.getPlayedCards().size(); i++) {
            allOutcomeCards[bestScore][i] = (byte) game.getPlayedCards().get(i).getId();
        }
        for (byte cardId : allOutcomeCards[bestScore]) {
            cards.add(deck.getCard(cardId));
        }
        return new Result(cards, game.getStartingPlayer(), game.getAtu());
    }

}
