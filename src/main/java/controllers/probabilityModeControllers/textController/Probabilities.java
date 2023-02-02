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
    private final Text equal;
    private final Text greaterOrEqual;

    Probabilities(DrawableFactory drawableFactory, Drawable background) {
        probabilities = new Text[FIGURE_NUMBER];
        for (int i = 0; i < FIGURE_NUMBER; i++) {
            probabilities[i] = drawableFactory.makeText(
                    "",
                    860 + background.getX(),
                    140 + 40 * i + background.getY(),
                    "HBE32",
                    "black");
            getScene().addObjectHigherThan(probabilities[i], background);
        }
        equal = drawableFactory.makeText(
                "=",
                940 + background.getX(),
                80 + background.getY(),
                "HBE48",
                "black"
        );
        getScene().addObjectHigherThan(equal, background);

        greaterOrEqual = drawableFactory.makeText(
                ">=",
                1070 + background.getX(),
                80 + background.getY(),
                "HBE48",
                "black"
        );
        getScene().addObjectHigherThan(greaterOrEqual, background);
    }

    public void updateProbabilities(List<CardProbability> cardProbabilities, List<CardProbability> cardProbabilitiesSums) {
        for (int i = 0; i < cardProbabilities.size(); i++) {
            String figure = cardProbabilities.get(i).getCard().getFigure().getSymbolString();
            StringBuilder text = new StringBuilder()
                    .append(figure)
                    .append(" - ")
                    .append(probabilityToConstLengthString(cardProbabilities.get(i).getProbability(), false))
                    .append(probabilityToConstLengthString(cardProbabilitiesSums.get(i).getProbability(), true));
            this.probabilities[i].setText(text.toString());
        }
        for (int i = cardProbabilities.size(); i < probabilities.length; i++) {
            this.probabilities[i].setText("");
        }
    }

    private String probabilityToConstLengthString(Double probability, boolean bracket) {
        StringBuilder probabilityText = new StringBuilder();
        if(bracket) {
            probabilityText.append(" (");
        }
        probabilityText.append(probability.toString());
        probabilityText.append("%");
        while(!bracket && probabilityText.length() < 7) {
            probabilityText.append(" ");
        }
        if(bracket) {
            probabilityText.append(")");
        }
        return probabilityText.toString();
    }
}
