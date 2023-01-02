package controllers.probabilityModeControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;
import solver.probabilitySolver.CardProbability;

import java.util.List;

import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.FIGURE_NUMBER;

public class Probabilities {

    private final Text[] probabilities;

    Probabilities(DrawableFactory drawableFactory, Drawable background) {
        probabilities = new Text[FIGURE_NUMBER];
        for (int i = 0; i < FIGURE_NUMBER; i++) {
            probabilities[i] = drawableFactory.makeText(
                    "",
                    860 + background.getX(),
                    80 + 40 * i + background.getY(),
                    "HBE32",
                    "black");
            getScene().addObjectHigherThan(probabilities[i], background);
        }
    }

    public void updateProbabilities(List<CardProbability> cardProbabilities, List<CardProbability> cardProbabilitiesSums) {
        for (int i = 0; i < cardProbabilities.size(); i++) {
            String figure = cardProbabilities.get(i).getCard().getFigure().getSymbolString();
            StringBuilder text = new StringBuilder()
                    .append(figure)
                    .append(" - ")
                    .append(cardProbabilities.get(i).getProbability())
                    .append("% (")
                    .append(cardProbabilitiesSums.get(i).getProbability())
                    .append("%)");
            this.probabilities[i].setText(text.toString());
        }
        for (int i = cardProbabilities.size(); i < probabilities.length; i++) {
            this.probabilities[i].setText("");
        }
    }
}
