package solver.algorithms.killerHeuristic;

import gameLogic.game.Game;
import solver.algorithms.BaseNode;

public class Node extends BaseNode {

    public byte depth;
    public boolean maximizing;
    public byte alpha;
    public byte beta;

    Node(Game game) {
        super(game);
        depth = (byte) game.getPlayedCards().size();
        maximizing = game.getCurrentPlayer().ordinal() % 2 == 0;
        alpha = -100;
        beta = 100;
    }

    public void playCard(byte index) {
        super.playCard(index);
        maximizing = !maximizing;
        depth++;
    }

    public void revertPlayCard(byte cardPlace) {
        super.revertPlayCard(cardPlace);
        depth--;
        maximizing = !maximizing;
    }

}
