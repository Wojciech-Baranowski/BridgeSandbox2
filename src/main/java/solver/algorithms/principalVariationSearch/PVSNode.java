package solver.algorithms.principalVariationSearch;

import gameLogic.game.Game;
import solver.algorithms.BaseNode;

public class PVSNode extends BaseNode {

    public byte color;
    public byte alpha;
    public byte beta;

    public PVSNode(Game game) {
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

    public void playDummyCard() {
        color *= -1;
        byte tempAlpha = alpha;
        alpha = (byte) -beta;
        beta = (byte) -tempAlpha;
    }

    public void revertPlayDummyCard() {
        color *= -1;
        byte tempAlpha = alpha;
        alpha = (byte) -beta;
        beta = (byte) -tempAlpha;
    }

    public boolean isSummarizeParity(byte lastStartingPlayer) {
        return (lastStartingPlayer & 1) != (startingPlayer & 1);
    }

}
