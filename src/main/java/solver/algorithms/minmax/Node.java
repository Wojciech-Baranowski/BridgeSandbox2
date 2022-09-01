package solver.algorithms.minmax;

import gameLogic.game.Game;
import solver.algorithms.BaseNode;

public class Node extends BaseNode {

    public boolean maximizing;

    Node(Game game) {
        super(game);
        depth = (byte) game.getPlayedCards().size();
        maximizing = game.getCurrentPlayer().ordinal() % 2 == 0;
    }

    public void playCard(byte index) {
        super.playCard(index);
        maximizing = !maximizing;
    }

    public void revertPlayCard(byte cardPlace) {
        super.revertPlayCard(cardPlace);
        maximizing = !maximizing;
    }

}
