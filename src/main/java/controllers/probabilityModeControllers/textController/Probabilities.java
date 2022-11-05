package controllers.probabilityModeControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;
import gameLogic.card.Card;

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

    public void updateProbabilities(List<Card> cards, List<Double> probabilities) {
        for(int i = 0; i < cards.size(); i++) {
            String figure = cards.get(i).getFigure().getSymbolString();
            String color = cards.get(i).getColor().getSymbolString();
            this.probabilities[i].setText(figure + color + " - " + probabilities.get(i) + "%");
        }
    }
}
