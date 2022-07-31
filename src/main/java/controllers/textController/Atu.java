package controllers.textController;

import engine.display.Drawable;
import engine.display.DrawableComposition;
import engine.display.DrawableFactory;
import engine.display.text.Text;
import gameLogic.card.Color;

import static engine.scene.SceneBean.getScene;
import static gameLogic.card.Color.*;

public class Atu {

    private Drawable atu;
    private final Drawable prefix;
    private final Text symbol;

    Atu(DrawableFactory drawableFactory, Drawable background, Color atu) {
        prefix = drawableFactory.makeText(
                "Atu: ",
                background.getX() + 9,
                background.getY() + 4,
                "HBE32",
               "black");

        symbol = drawableFactory.makeText(
                atu.getSymbolString(),
                background.getX() + 76,
                background.getY() + 2,
                "HBE32",
                (atu == DIAMOND || atu == HEART) ? "red" : "black");

        this.atu = new DrawableComposition(prefix, symbol);
        getScene().addObjectHigherThan(this.atu, background);

    }

    void updateAtu(Drawable background, Color atu) {
        String text = new StringBuilder()
                .append("Atu: ")
                .append(atu.getSymbol())
                .toString();
        this.symbol.setText(text);
        getScene().removeObject(this.atu);
        this.atu = new DrawableComposition(prefix, symbol);
        getScene().addObjectHigherThan(this.atu, background);
    }

}
