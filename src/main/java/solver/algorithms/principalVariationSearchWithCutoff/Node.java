package solver.algorithms.principalVariationSearchWithCutoff;

import gameLogic.game.Game;
import solver.algorithms.BaseNode;

public class Node extends BaseNode {

    public byte color;
    public byte alpha;
    public byte beta;

    Node(Game game) {
        super(game);
        color = (byte) (game.getCurrentPlayer().ordinal() % 2 == 0 ? 1 : -1);
        alpha = -100;
        beta = 100;
    }

    public void playCard(byte index, byte alpha, byte beta) {
        super.playCard(index);
        color *= -1;
        this.alpha = alpha;
        this.beta = beta;
    }

    public void revertPlayCard(byte cardPlace) {
        super.revertPlayCard(cardPlace);
        color *= -1;
    }

}
