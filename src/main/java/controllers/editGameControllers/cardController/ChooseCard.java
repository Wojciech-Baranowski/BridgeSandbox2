package controllers.editGameControllers.cardController;

import engine.button.radioButton.RadioButton;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import gameLogic.card.Card;
import gameLogic.player.Player;
import lombok.Getter;

import static controllers.main.assets.CardDrawables.getGameEditCardDrawable;
import static engine.input.InputBean.getInput;

public class ChooseCard {

    @Getter
    private final RadioButton button;
    private final Card card;

    ChooseCard(DrawableFactory drawableFactory, Drawable cell, Player player, Card card) {
        this.card = card;
        Drawable inactiveDrawable = getGameEditCardDrawable(player, card.getId());
        inactiveDrawable.setX(cell.getX() + 17 * card.getFigure().ordinal() + 7);
        inactiveDrawable.setY(cell.getY() + 6);
        Drawable activeOverlayDrawable = drawableFactory.makeFramedRectangle(
                inactiveDrawable.getX(),
                inactiveDrawable.getY(),
                inactiveDrawable.getW(),
                inactiveDrawable.getH(),
                2,
                "transparentBlue",
                "lightBlue");
        Drawable activeDrawable = new DrawableComposition(inactiveDrawable, activeOverlayDrawable);
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        button = new RadioButton(inactiveDrawable, activeDrawable, activationCombination);
    }

}
