package controllers.gameControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.display.text.Text;
import gameLogic.card.Color;

import static engine.display.DisplayBean.getDisplay;
import static engine.scene.SceneBean.getScene;
import static gameLogic.card.Color.DIAMOND;
import static gameLogic.card.Color.HEART;

public class Atu {

    private Drawable atu;
    private final Drawable prefix;
    private Text symbol;

    Atu(DrawableFactory drawableFactory, Drawable buttonsSpace, Color atu) {
        prefix = drawableFactory.makeText(
                "Atu: ",
                buttonsSpace.getX() + 10,
                buttonsSpace.getY() + 10,
                "HBE32",
                "black");

        symbol = drawableFactory.makeText(
                atu.getSymbolString(),
                buttonsSpace.getX() + 82,
                buttonsSpace.getY() + 10,
                "HBE32",
                (atu == DIAMOND || atu == HEART) ? "red" : "black");

        this.atu = new DrawableComposition(prefix, symbol);
        getScene().addObjectHigherThan(this.atu, buttonsSpace);

    }

    void updateAtu(Drawable background, Color atu) {
        DrawableFactory drawableFactory = getDisplay().getDrawableFactory();
        symbol = drawableFactory.makeText(
                atu.getSymbolString(),
                symbol.getX(),
                symbol.getY(),
                "HBE32",
                (atu == DIAMOND || atu == HEART) ? "red" : "black");

        getScene().removeObject(this.atu);
        this.atu = new DrawableComposition(prefix, symbol);
        getScene().addObjectHigherThan(this.atu, background);
    }

}
