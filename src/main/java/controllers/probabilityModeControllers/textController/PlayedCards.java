package controllers.probabilityModeControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;

import static engine.scene.SceneBean.getScene;

public class PlayedCards {

    private final Text symbol;

    PlayedCards(DrawableFactory drawableFactory, Drawable playedCardSymbolSlot) {
        symbol = drawableFactory.makeText(
                "Played cards:",
                14 + playedCardSymbolSlot.getX(),
                520 + playedCardSymbolSlot.getY(),
                "HBE32",
                "black");
        getScene().addObjectHigherThan(symbol, playedCardSymbolSlot);
    }

}
