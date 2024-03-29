package solver.algorithms.mtd;

import gameLogic.game.Game;
import solver.algorithms.BaseNode;

public class MtdNode extends BaseNode {

    public byte color;
    public byte alpha;
    public byte beta;

    MtdNode(Game game) {
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
