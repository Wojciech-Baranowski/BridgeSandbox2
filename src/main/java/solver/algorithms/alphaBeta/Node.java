package solver.algorithms.alphaBeta;

import gameLogic.game.Game;
import solver.algorithms.BaseNode;

public class Node extends BaseNode {

    public byte depth;
    public byte color;
    public byte alpha;
    public byte beta;

    Node(Game game) {
        super(game);
        depth = (byte) game.getPlayedCards().size();
        color = (byte) (game.getCurrentPlayer().ordinal() % 2 == 0 ? 1 : -1);
        alpha = -100;
        beta = 100;
    }

    public void playCard(byte index) {
        super.playCard(index);
        color *= -1;
        depth++;
        byte tempAlpha = alpha;
        alpha = (byte) -beta;
        beta = (byte) -tempAlpha;
    }

    public void revertPlayCard(byte cardPlace) {
        super.revertPlayCard(cardPlace);
        depth--;
        color *= -1;
        byte tempAlpha = alpha;
        alpha = (byte) -beta;
        beta = (byte) -tempAlpha;
    }

}
