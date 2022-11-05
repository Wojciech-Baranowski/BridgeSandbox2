package controllers.probabilityModeControllers.cardController;

import engine.button.radioButton.RadioButton;
import engine.button.radioButton.RadioButtonBundle;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import gameLogic.player.Player;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static engine.scene.SceneBean.getScene;
import static gameLogic.card.Deck.getDeck;
import static gameLogic.game.GameConstants.*;

public class ChooseCards {

    private final ChooseCard[][] chooseCards;
    @Getter
    private final RadioButtonBundle[] chooseCardsBundles;

    ChooseCards(DrawableFactory drawableFactory, Drawable[] cardSlots) {
        chooseCards = new ChooseCard[FIGURE_NUMBER][PLAYER_NUMBER + 1];
        for (int i = 0; i < PLAYER_NUMBER + 1; i++) {
            for (int j = 0; j < FIGURE_NUMBER; j++) {
                chooseCards[j][i] = new ChooseCard(
                        drawableFactory,
                        cardSlots[i],
                        i,
                        getDeck().getCard(j + FIGURE_NUMBER * 2));

                getScene().addObjectHigherThan(chooseCards[j][i].getButton(),
                        (j % FIGURE_NUMBER == 0)
                                ? cardSlots[i]
                                : chooseCards[j - 1][i].getButton());
            }
        }

        chooseCardsBundles = new RadioButtonBundle[FIGURE_NUMBER];

        for (int i = 0; i < FIGURE_NUMBER; i++) {
            List<RadioButton> radioButtonsList = Arrays.stream(chooseCards[i])
                    .map(ChooseCard::getButton)
                    .toList();
            chooseCardsBundles[i] = new RadioButtonBundle(radioButtonsList);
        }

    }

    public void clearChosenCards() {
        for (RadioButtonBundle cardBundle : chooseCardsBundles) {
            cardBundle.unset();
        }
    }

}
