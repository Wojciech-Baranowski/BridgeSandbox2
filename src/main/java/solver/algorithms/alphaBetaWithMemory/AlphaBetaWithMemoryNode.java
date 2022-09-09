package solver.algorithms.alphaBetaWithMemory;

import gameLogic.game.Game;
import solver.algorithms.BaseNode;

public class AlphaBetaWithMemoryNode extends BaseNode {

    public byte color;
    public byte alpha;
    public byte beta;

    AlphaBetaWithMemoryNode(Game game) {
        super(game);
        color = (byte) (game.getCurrentPlayer().ordinal() % 2 == 0 ? 1 : -1);
        alpha = -100;
        beta = 100;
    }

    public void playCard(byte index) {
        super.playCard(index);
        color *= -1;
        byte tempAlpha = alpha;
        alpha = (byte) -beta;
        beta = (byte) -tempAlpha;
    }

    public void revertPlayCard(byte cardPlace) {
        super.revertPlayCard(cardPlace);
        color *= -1;
    }

}
