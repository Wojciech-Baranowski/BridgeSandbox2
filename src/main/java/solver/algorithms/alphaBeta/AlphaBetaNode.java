package solver.algorithms.alphaBeta;

import gameLogic.game.Game;
import solver.algorithms.BaseNode;

public class AlphaBetaNode extends BaseNode {

    public byte color;
    public byte alpha;
    public byte beta;

    protected AlphaBetaNode(Game game) {
        super(game);
        color = (byte) (game.getCurrentPlayer().ordinal() % 2 == 0 ? 1 : -1);
        alpha = (byte) -100;
        beta = (byte) 100;
    }

    protected AlphaBetaNode(Game game, int numberOfPoints) {
        super(game);
        color = (byte) (game.getCurrentPlayer().ordinal() % 2 == 0 ? 1 : -1);
        alpha = (byte) (numberOfPoints - 1);
        beta = (byte) numberOfPoints;
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
