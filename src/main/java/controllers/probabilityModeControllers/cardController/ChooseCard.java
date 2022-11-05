package controllers.probabilityModeControllers.cardController;

import engine.button.radioButton.RadioButton;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import gameLogic.card.Card;
import gameLogic.player.Player;
import lombok.Getter;

import static controllers.main.assets.CardDrawables.getProbabilityModeCardDrawable;
import static engine.input.InputBean.getInput;

public class ChooseCard {

    @Getter
    private final RadioButton button;
    private final Card card;

    ChooseCard(DrawableFactory drawableFactory, Drawable cardSlot, int slotId, Card card) {
        this.card = card;
        Drawable inactiveDrawable = getProbabilityModeCardDrawable(slotId, card.getId());
        inactiveDrawable.setX(cardSlot.getX() + 17 * card.getFigure().ordinal() + 2);
        inactiveDrawable.setY(cardSlot.getY() + 2);
        Drawable activeOverlayDrawable = drawableFactory.makeFramedRectangle(
                inactiveDrawable.getX(),
                inactiveDrawable.getY(),
                inactiveDrawable.getW(),
                inactiveDrawable.getH(),
                2,
                "transparentBlue",
                "violet");
        Drawable activeDrawable = new DrawableComposition(inactiveDrawable, activeOverlayDrawable);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        button = new RadioButton(inactiveDrawable, activeDrawable, activationCombination);
    }

}
