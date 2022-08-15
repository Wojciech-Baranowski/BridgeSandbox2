package controllers.editGameControllers.buttonController;

import engine.button.SimpleButton;
import engine.common.Command;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;
import lombok.Getter;

import static controllers.editGameControllers.textController.EditGameTextController.getEditGameTextController;
import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;
import static gameLogic.game.GameConstants.MAX_CARDS_PER_PLAYER;

public class CardsNumberChanger {

    private class DecrementCardNumberCommand implements Command {

        @Override
        public void execute() {
            updateCardNumber((cardNumber + MAX_CARDS_PER_PLAYER - 2) % MAX_CARDS_PER_PLAYER + 1);
        }
    }

    private class IncrementCardNumberCommand implements Command {

        @Override
        public void execute() {
            updateCardNumber(cardNumber % MAX_CARDS_PER_PLAYER + 1);
        }

    }

    private final SimpleButton incCardsButton;
    private final SimpleButton decCardsButton;
    @Getter
    private int cardNumber;

    CardsNumberChanger(DrawableFactory drawableFactory, Drawable background) {
        cardNumber = 13;
        Drawable buttonBackground = drawableFactory.makeFramedRectangle(
                910 + background.getX(),
                490 + background.getY(),
                32,
                32,
                2,
                "gray",
                "lightBlue");

        Drawable buttonSymbol = drawableFactory.makeText(
                "-",
                10 + buttonBackground.getX(),
                buttonBackground.getY(),
                "HBE32",
                "black");

        Drawable decDrawable = new DrawableComposition(buttonBackground, buttonSymbol);

        buttonBackground = drawableFactory.makeFramedRectangle(
                50 + decDrawable.getX(),
                decDrawable.getY(),
                32,
                32,
                2,
                "gray",
                "lightBlue");

        buttonSymbol = drawableFactory.makeText(
                "+",
                7 + buttonBackground.getX(),
                buttonBackground.getY(),
                "HBE32",
                "black");

        Drawable incDrawable = new DrawableComposition(buttonBackground, buttonSymbol);

        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        incCardsButton = new SimpleButton(incDrawable, activationCombination, new IncrementCardNumberCommand());
        decCardsButton = new SimpleButton(decDrawable, activationCombination, new DecrementCardNumberCommand());
        getScene().addObjectHigherThan(incCardsButton, background);
        getScene().addObjectHigherThan(decCardsButton, background);
    }

    public void updateCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
        getEditGameTextController().getCardNumber().updateCardNumber(cardNumber);
    }

}
