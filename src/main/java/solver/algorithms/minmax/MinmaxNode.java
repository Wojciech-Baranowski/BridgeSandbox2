package solver.algorithms.minmax;

import gameLogic.game.Game;
import solver.algorithms.BaseNode;

public class MinmaxNode extends BaseNode {

    public boolean maximizing;

    MinmaxNode(Game game) {
        super(game);
        maximizing = game.getCurrentPlayer().ordinal() % 2 == 0;
    }

    public void playCard(byte index) {
        super.playCard(index);
        maximizing = !maximizing;
    }

    public void playDummyCard() {
        maximizing = !maximizing;
    }

    public void revertPlayCard(byte cardPlace) {
        super.revertPlayCard(cardPlace);
        maximizing = !maximizing;
    }

    public void revertPlayDummyCard() {
        maximizing = !maximizing;
    }

    public boolean isSummarizeParity(byte lastStartingPlayer) {
        return (lastStartingPlayer & 1) != (startingPlayer & 1);
    }

}
