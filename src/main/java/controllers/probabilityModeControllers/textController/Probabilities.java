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
                    45 + 40 * i + background.getY(),
                    "HBE32",
                    "black");
            getScene().addObjectHigherThan(probabilities[i], background);
        }
    }

    public void updateProbabilities(List<CardProbability> cardProbabilities) {
        for (int i = 0; i < cardProbabilities.size(); i++) {
            String figure = cardProbabilities.get(i).getCard().getFigure().getSymbolString();
            this.probabilities[i].setText(figure + " - " + cardProbabilities.get(i).getProbability() + "%");
        }
        for (int i = cardProbabilities.size(); i < probabilities.length; i++) {
            this.probabilities[i].setText("");
        }
    }
}
