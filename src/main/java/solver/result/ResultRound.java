package solver.result;

import gameLogic.card.Card;
import gameLogic.player.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class ResultRound {

    private final List<Card> cards;
    private final Player startingPlayer;
    private final Player winningPlayer;
    private final boolean isPointNS;

    ResultRound(List<Card> cards, Player startingPlayer, Player winningPlayer) {
        this.cards = new ArrayList<>(cards);
        this.startingPlayer = startingPlayer;
        this.winningPlayer = winningPlayer;
        this.isPointNS = (winningPlayer.ordinal() % 2 == 0);
    }

}
