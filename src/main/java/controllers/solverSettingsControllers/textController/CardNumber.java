package controllers.solverSettingsControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;

import static engine.scene.SceneBean.getScene;

public class CardNumber {

    private final Text cardNumber;

    CardNumber(DrawableFactory drawableFactory, Drawable background) {
        cardNumber = drawableFactory.makeText("Number of cards: 13",
                20 + background.getX(),
                35 + background.getY(),
                "HBE32",
                "black");
        getScene().addObjectHigherThan(cardNumber, background);
    }

    public void updateCardNumber(int cardNumber) {
        this.cardNumber.setText("Number of cards: " + cardNumber);
    }

}
