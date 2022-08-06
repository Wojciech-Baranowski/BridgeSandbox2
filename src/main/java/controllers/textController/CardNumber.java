package controllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;

import static engine.scene.SceneBean.getScene;

public class CardNumber {

    private final Drawable cardNumber;

    public CardNumber(DrawableFactory drawableFactory, Drawable buttonsSpace) {
        cardNumber = drawableFactory.makeText("Cards",
                10 + buttonsSpace.getX(),
                128 + buttonsSpace.getY(),
                "HBE32",
                "black");
        getScene().addObjectHigherThan(cardNumber, buttonsSpace);
    }

}
