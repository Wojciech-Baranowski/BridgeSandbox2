package controllers.editGameControllers.cardController;

import engine.button.radioButton.RadioButton;
import engine.button.radioButton.RadioButtonBundle;
import engine.display.Drawable;
import engine.display.DrawableFactory;
import gameLogic.player.Player;

import java.util.Arrays;
import java.util.List;

import static engine.scene.SceneBean.getScene;
import static gameLogic.card.Deck.getDeck;
import static gameLogic.game.GameConstants.*;

public class ChooseCards {

    private final ChooseCard[][] chooseCards;
    private final RadioButtonBundle[] chooseCardsBundles;

    ChooseCards(DrawableFactory drawableFactory, Drawable[][] cells) {
        chooseCards = new ChooseCard[DECK_SIZE][PLAYER_NUMBER];
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            for (int j = 0; j < DECK_SIZE; j++) {
                chooseCards[j][i] = new ChooseCard(
                        drawableFactory,
                        cells[1 + j / FIGURE_NUMBER][i],
                        Player.values()[i],
                        getDeck().getCard(j));

                getScene().addObjectHigherThan(chooseCards[j][i].getButton(),
                        (j % FIGURE_NUMBER == 0)
                                ? cells[1 + j / FIGURE_NUMBER][i]
                                : chooseCards[j - 1][i].getButton());
            }
        }

        chooseCardsBundles = new RadioButtonBundle[DECK_SIZE];

        for (int i = 0; i < DECK_SIZE; i++) {
            List<RadioButton> radioButtonsList = Arrays.stream(chooseCards[i])
                    .map(ChooseCard::getButton)
                    .toList();
            chooseCardsBundles[i] = new RadioButtonBundle(radioButtonsList);
        }

    }

}
