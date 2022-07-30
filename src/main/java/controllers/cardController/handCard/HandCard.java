package controllers.cardController.handCard;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.input.inputCombination.InputCombination;
import gameLogic.card.Card;
import gameLogic.player.Player;
import lombok.Getter;

import static controllers.main.assets.CardDrawables.getCardDrawable;
import static engine.input.InputBean.getInput;

public class HandCard {


    @Getter
    private final SimpleButton button;
    @Getter
    private final Card card;
    @Getter
    private final Player owner;

    HandCard(Card card, Player owner) {
        this.card = card;
        this.owner = owner;
        Drawable drawable = getCardDrawable(card.getId());
        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        Command command = new HandCardPlayCommand(this);
        button = new SimpleButton(drawable, activationCombination, command);
    }

    void move(int x, int y) {
        button.getDrawable().setX(x);
        button.getDrawable().setY(y);
    }

}
