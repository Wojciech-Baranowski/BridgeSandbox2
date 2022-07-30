package controllers.cardController.handCard;

import controllers.main.assets.InactiveOverlay;
import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.DisplayBean;
import engine.display.Drawable;
import engine.input.inputCombination.InputCombination;
import gameLogic.card.Card;
import lombok.Getter;

import static controllers.main.assets.CardDrawables.getCardDrawable;
import static engine.display.DisplayBean.getDisplay;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class HandCard {


    @Getter
    private final SimpleButton button;
    @Getter
    private InactiveOverlay inactiveOverlay;
    @Getter
    private final Card card;

    HandCard(Card card) {
        this.card = card;
        Drawable drawable = getCardDrawable(card.getId());
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        Command command = new HandCardPlayCommand(this);
        button = new SimpleButton(drawable, activationCombination, command);
        inactiveOverlay = new InactiveOverlay(button);
    }

    void move(int x, int y) {
        button.getDrawable().setX(x);
        button.getDrawable().setY(y);
    }

    void activateOverlay() {
        int x = button.getDrawable().getX();
        int y = button.getDrawable().getY();
        inactiveOverlay.move(x, y);
        getScene().addObjectHigherThan(inactiveOverlay.getDrawable(), button);
    }

    void deactivateOverlay() {
        getScene().removeObject(inactiveOverlay.getDrawable());
    }

}
