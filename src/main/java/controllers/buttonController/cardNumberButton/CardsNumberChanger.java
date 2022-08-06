package controllers.buttonController.cardNumberButton;

import engine.button.SimpleButton;
import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.input.inputCombination.InputCombination;

import static engine.input.InputBean.getInput;
import static engine.scene.SceneBean.getScene;

public class CardsNumberChanger {

    private SimpleButton incCardsButton;
    private SimpleButton decCardsButton;

    public CardsNumberChanger(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        Drawable background = drawableFactory.makeFramedRectangle(
                110 + buttonsSpace.getX(),
                124 + buttonsSpace.getY(),
                32,
                32,
                2,
                "gray",
                "lightBlue");

        Drawable symbol = drawableFactory.makeText(
                "-",
                10 + background.getX(),
                background.getY(),
                "HBE32",
                "black");

        Drawable decDrawable = new DrawableComposition(background, symbol);

        background = drawableFactory.makeFramedRectangle(
                150 + buttonsSpace.getX(),
                124 + buttonsSpace.getY(),
                32,
                32,
                2,
                "gray",
                "lightBlue");

        symbol = drawableFactory.makeText(
                "+",
                7 + background.getX(),
                background.getY(),
                "HBE32",
                "black");

        Drawable incDrawable = new DrawableComposition(background, symbol);

        InputCombination activationCombination = getInput().getInputCombinationFactory().makeLmbCombination();
        incCardsButton = new SimpleButton(incDrawable, activationCombination, new IncrementCardNumberCommand());
        decCardsButton = new SimpleButton(decDrawable, activationCombination, new DecrementCardNumberCommand());
        getScene().addObjectHigherThan(incCardsButton, buttonsSpace);
        getScene().addObjectHigherThan(decCardsButton, buttonsSpace);
    }

}
